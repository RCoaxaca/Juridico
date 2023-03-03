package catalogos

import org.springframework.dao.DataIntegrityViolationException

class ScaprnController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [scaprnInstanceList: Scaprn.list(params), scaprnInstanceTotal: Scaprn.count()]
    }

    def create() {
        [scaprnInstance: new Scaprn(params)]
    }

    def save() {
        def scaprnInstance = new Scaprn(params)
        if (!scaprnInstance.save(flush: true)) {
            render(view: "create", model: [scaprnInstance: scaprnInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'scaprn.label', default: 'Scaprn'), scaprnInstance.id])
        redirect(action: "show", id: scaprnInstance.id)
    }

    def show(Long id) {
        def scaprnInstance = Scaprn.get(id)
        if (!scaprnInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaprn.label', default: 'Scaprn'), id])
            redirect(action: "list")
            return
        }

        [scaprnInstance: scaprnInstance]
    }

    def edit(Long id) {
        def scaprnInstance = Scaprn.get(id)
        if (!scaprnInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaprn.label', default: 'Scaprn'), id])
            redirect(action: "list")
            return
        }

        [scaprnInstance: scaprnInstance]
    }

    def update(Long id, Long version) {
        def scaprnInstance = Scaprn.get(id)
        if (!scaprnInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaprn.label', default: 'Scaprn'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (scaprnInstance.version > version) {
                scaprnInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scaprn.label', default: 'Scaprn')] as Object[],
                          "Another user has updated this Scaprn while you were editing")
                render(view: "edit", model: [scaprnInstance: scaprnInstance])
                return
            }
        }

        scaprnInstance.properties = params

        if (!scaprnInstance.save(flush: true)) {
            render(view: "edit", model: [scaprnInstance: scaprnInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'scaprn.label', default: 'Scaprn'), scaprnInstance.id])
        redirect(action: "show", id: scaprnInstance.id)
    }

    def delete(Long id) {
        def scaprnInstance = Scaprn.get(id)
        if (!scaprnInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaprn.label', default: 'Scaprn'), id])
            redirect(action: "list")
            return
        }

        try {
            scaprnInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scaprn.label', default: 'Scaprn'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scaprn.label', default: 'Scaprn'), id])
            redirect(action: "show", id: id)
        }
    }
}
