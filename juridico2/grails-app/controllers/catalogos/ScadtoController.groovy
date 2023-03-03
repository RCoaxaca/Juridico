package catalogos

import org.springframework.dao.DataIntegrityViolationException

class ScadtoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
    

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [scadtoInstanceList: Scadto.list(params), scadtoInstanceTotal: Scadto.count()]
    }

    def create() {
        [scadtoInstance: new Scadto(params)]
    }

    def save() {
        def scadtoInstance = new Scadto(params)
        if (!scadtoInstance.save(flush: true)) {
            render(view: "create", model: [scadtoInstance: scadtoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'scadto.label', default: 'Scadto'), scadtoInstance.id])
        redirect(action: "show", id: scadtoInstance.id)
    }

    def show(Long id) {
        def scadtoInstance = Scadto.get(id)
        if (!scadtoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scadto.label', default: 'Scadto'), id])
            redirect(action: "list")
            return
        }

        [scadtoInstance: scadtoInstance]
    }

    def edit(Long id) {
        def scadtoInstance = Scadto.get(id)
        if (!scadtoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scadto.label', default: 'Scadto'), id])
            redirect(action: "list")
            return
        }

        [scadtoInstance: scadtoInstance]
    }

    def update(Long id, Long version) {
        def scadtoInstance = Scadto.get(id)
        if (!scadtoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scadto.label', default: 'Scadto'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (scadtoInstance.version > version) {
                scadtoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scadto.label', default: 'Scadto')] as Object[],
                          "Another user has updated this Scadto while you were editing")
                render(view: "edit", model: [scadtoInstance: scadtoInstance])
                return
            }
        }

        scadtoInstance.properties = params

        if (!scadtoInstance.save(flush: true)) {
            render(view: "edit", model: [scadtoInstance: scadtoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'scadto.label', default: 'Scadto'), scadtoInstance.id])
        redirect(action: "show", id: scadtoInstance.id)
    }

    def delete(Long id) {
        def scadtoInstance = Scadto.get(id)
        if (!scadtoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scadto.label', default: 'Scadto'), id])
            redirect(action: "list")
            return
        }

        try {
            scadtoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scadto.label', default: 'Scadto'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scadto.label', default: 'Scadto'), id])
            redirect(action: "show", id: id)
        }
    }
}
