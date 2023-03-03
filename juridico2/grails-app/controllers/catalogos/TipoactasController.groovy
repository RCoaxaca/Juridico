package catalogos

import org.springframework.dao.DataIntegrityViolationException

class TipoactasController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [tipoactasInstanceList: Tipoactas.list(params), tipoactasInstanceTotal: Tipoactas.count()]
    }

    def create() {
        [tipoactasInstance: new Tipoactas(params)]
    }

    def save() {
        def tipoactasInstance = new Tipoactas(params)
        if (!tipoactasInstance.save(flush: true)) {
            render(view: "create", model: [tipoactasInstance: tipoactasInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tipoactas.label', default: 'Tipoactas'), tipoactasInstance.id])
        redirect(action: "show", id: tipoactasInstance.id)
    }

    def show(Long id) {
        def tipoactasInstance = Tipoactas.get(id)
        if (!tipoactasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoactas.label', default: 'Tipoactas'), id])
            redirect(action: "list")
            return
        }

        [tipoactasInstance: tipoactasInstance]
    }

    def edit(Long id) {
        def tipoactasInstance = Tipoactas.get(id)
        if (!tipoactasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoactas.label', default: 'Tipoactas'), id])
            redirect(action: "list")
            return
        }

        [tipoactasInstance: tipoactasInstance]
    }

    def update(Long id, Long version) {
        def tipoactasInstance = Tipoactas.get(id)
        if (!tipoactasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoactas.label', default: 'Tipoactas'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tipoactasInstance.version > version) {
                tipoactasInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipoactas.label', default: 'Tipoactas')] as Object[],
                          "Another user has updated this Tipoactas while you were editing")
                render(view: "edit", model: [tipoactasInstance: tipoactasInstance])
                return
            }
        }

        tipoactasInstance.properties = params

        if (!tipoactasInstance.save(flush: true)) {
            render(view: "edit", model: [tipoactasInstance: tipoactasInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoactas.label', default: 'Tipoactas'), tipoactasInstance.id])
        redirect(action: "show", id: tipoactasInstance.id)
    }

    def delete(Long id) {
        def tipoactasInstance = Tipoactas.get(id)
        if (!tipoactasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoactas.label', default: 'Tipoactas'), id])
            redirect(action: "list")
            return
        }

        try {
            tipoactasInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoactas.label', default: 'Tipoactas'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoactas.label', default: 'Tipoactas'), id])
            redirect(action: "show", id: id)
        }
    }
}
