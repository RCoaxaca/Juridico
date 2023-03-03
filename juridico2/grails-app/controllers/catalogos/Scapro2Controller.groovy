package catalogos

import org.springframework.dao.DataIntegrityViolationException

class Scapro2Controller {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [scapro2InstanceList: Scapro2.list(params), scapro2InstanceTotal: Scapro2.count()]
    }

    def create() {
        [scapro2Instance: new Scapro2(params)]
    }

    def save() {
        def scapro2Instance = new Scapro2(params)
        if (!scapro2Instance.save(flush: true)) {
            render(view: "create", model: [scapro2Instance: scapro2Instance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'scapro2.label', default: 'Scapro2'), scapro2Instance.id])
        redirect(action: "show", id: scapro2Instance.id)
    }

    def show(Long id) {
        def scapro2Instance = Scapro2.get(id)
        if (!scapro2Instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scapro2.label', default: 'Scapro2'), id])
            redirect(action: "list")
            return
        }

        [scapro2Instance: scapro2Instance]
    }

    def edit(Long id) {
        def scapro2Instance = Scapro2.get(id)
        if (!scapro2Instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scapro2.label', default: 'Scapro2'), id])
            redirect(action: "list")
            return
        }

        [scapro2Instance: scapro2Instance]
    }

    def update(Long id, Long version) {
        def scapro2Instance = Scapro2.get(id)
        if (!scapro2Instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scapro2.label', default: 'Scapro2'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (scapro2Instance.version > version) {
                scapro2Instance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scapro2.label', default: 'Scapro2')] as Object[],
                          "Another user has updated this Scapro2 while you were editing")
                render(view: "edit", model: [scapro2Instance: scapro2Instance])
                return
            }
        }

        scapro2Instance.properties = params

        if (!scapro2Instance.save(flush: true)) {
            render(view: "edit", model: [scapro2Instance: scapro2Instance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'scapro2.label', default: 'Scapro2'), scapro2Instance.id])
        redirect(action: "show", id: scapro2Instance.id)
    }

    def delete(Long id) {
        def scapro2Instance = Scapro2.get(id)
        if (!scapro2Instance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scapro2.label', default: 'Scapro2'), id])
            redirect(action: "list")
            return
        }

        try {
            scapro2Instance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scapro2.label', default: 'Scapro2'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scapro2.label', default: 'Scapro2'), id])
            redirect(action: "show", id: id)
        }
    }
}
