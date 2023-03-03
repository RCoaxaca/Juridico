package catalogos

import org.springframework.dao.DataIntegrityViolationException

class EntidadesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [entidadesInstanceList: Entidades.list(params), entidadesInstanceTotal: Entidades.count()]
    }

    def create() {
        [entidadesInstance: new Entidades(params)]
    }

    def save() {
        def entidadesInstance = new Entidades(params)
        if (!entidadesInstance.save(flush: true)) {
            render(view: "create", model: [entidadesInstance: entidadesInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'entidades.label', default: 'Entidades'), entidadesInstance.id])
        redirect(action: "show", id: entidadesInstance.id)
    }

    def show(Long id) {
        def entidadesInstance = Entidades.get(id)
        if (!entidadesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entidades.label', default: 'Entidades'), id])
            redirect(action: "list")
            return
        }

        [entidadesInstance: entidadesInstance]
    }

    def edit(Long id) {
        def entidadesInstance = Entidades.get(id)
        if (!entidadesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entidades.label', default: 'Entidades'), id])
            redirect(action: "list")
            return
        }

        [entidadesInstance: entidadesInstance]
    }

    def update(Long id, Long version) {
        def entidadesInstance = Entidades.get(id)
        if (!entidadesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entidades.label', default: 'Entidades'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (entidadesInstance.version > version) {
                entidadesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'entidades.label', default: 'Entidades')] as Object[],
                          "Another user has updated this Entidades while you were editing")
                render(view: "edit", model: [entidadesInstance: entidadesInstance])
                return
            }
        }

        entidadesInstance.properties = params

        if (!entidadesInstance.save(flush: true)) {
            render(view: "edit", model: [entidadesInstance: entidadesInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'entidades.label', default: 'Entidades'), entidadesInstance.id])
        redirect(action: "show", id: entidadesInstance.id)
    }

    def delete(Long id) {
        def entidadesInstance = Entidades.get(id)
        if (!entidadesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entidades.label', default: 'Entidades'), id])
            redirect(action: "list")
            return
        }

        try {
            entidadesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'entidades.label', default: 'Entidades'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'entidades.label', default: 'Entidades'), id])
            redirect(action: "show", id: id)
        }
    }
}
