package catalogos

import org.springframework.dao.DataIntegrityViolationException

class ScaofiController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [scaofiInstanceList: Scaofi.list(params), scaofiInstanceTotal: Scaofi.count()]
    }

    def create() {
        [scaofiInstance: new Scaofi(params)]
    }

    def save() {
        def scaofiInstance = new Scaofi(params)
        if (!scaofiInstance.save(flush: true)) {
            render(view: "create", model: [scaofiInstance: scaofiInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'scaofi.label', default: 'Scaofi'), scaofiInstance.id])
        redirect(action: "show", id: scaofiInstance.id)
    }

    def show(Long id) {
        def scaofiInstance = Scaofi.get(id)
        if (!scaofiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaofi.label', default: 'Scaofi'), id])
            redirect(action: "list")
            return
        }

        [scaofiInstance: scaofiInstance]
    }

    def edit(Long id) {
        def scaofiInstance = Scaofi.get(id)
        if (!scaofiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaofi.label', default: 'Scaofi'), id])
            redirect(action: "list")
            return
        }

        [scaofiInstance: scaofiInstance]
    }

    def update(Long id, Long version) {
        def scaofiInstance = Scaofi.get(id)
        if (!scaofiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaofi.label', default: 'Scaofi'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (scaofiInstance.version > version) {
                scaofiInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scaofi.label', default: 'Scaofi')] as Object[],
                          "Another user has updated this Scaofi while you were editing")
                render(view: "edit", model: [scaofiInstance: scaofiInstance])
                return
            }
        }

        scaofiInstance.properties = params

        if (!scaofiInstance.save(flush: true)) {
            render(view: "edit", model: [scaofiInstance: scaofiInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'scaofi.label', default: 'Scaofi'), scaofiInstance.id])
        redirect(action: "show", id: scaofiInstance.id)
    }

    def delete(Long id) {
        def scaofiInstance = Scaofi.get(id)
        if (!scaofiInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaofi.label', default: 'Scaofi'), id])
            redirect(action: "list")
            return
        }

        try {
            scaofiInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scaofi.label', default: 'Scaofi'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scaofi.label', default: 'Scaofi'), id])
            redirect(action: "show", id: id)
        }
    }
}
