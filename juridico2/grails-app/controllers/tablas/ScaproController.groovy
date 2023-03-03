package tablas

import org.springframework.dao.DataIntegrityViolationException

class ScaproController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [scaproInstanceList: Scapro.list(params), scaproInstanceTotal: Scapro.count()]
    }

    def create() {
        [scaproInstance: new Scapro(params)]
    }

    def save() {
        def scaproInstance = new Scapro(params)
        if (!scaproInstance.save(flush: true)) {
            render(view: "create", model: [scaproInstance: scaproInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'scapro.label', default: 'Scapro'), scaproInstance.id])
        redirect(action: "show", id: scaproInstance.id)
    }

    def show(Long id) {
        def scaproInstance = Scapro.get(id)
        if (!scaproInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scapro.label', default: 'Scapro'), id])
            redirect(action: "list")
            return
        }

        [scaproInstance: scaproInstance]
    }

    def edit(Long id) {
        def scaproInstance = Scapro.get(id)
        if (!scaproInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scapro.label', default: 'Scapro'), id])
            redirect(action: "list")
            return
        }

        [scaproInstance: scaproInstance]
    }

    def update(Long id, Long version) {
        def scaproInstance = Scapro.get(id)
        if (!scaproInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scapro.label', default: 'Scapro'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (scaproInstance.version > version) {
                scaproInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scapro.label', default: 'Scapro')] as Object[],
                          "Another user has updated this Scapro while you were editing")
                render(view: "edit", model: [scaproInstance: scaproInstance])
                return
            }
        }

        scaproInstance.properties = params

        if (!scaproInstance.save(flush: true)) {
            render(view: "edit", model: [scaproInstance: scaproInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'scapro.label', default: 'Scapro'), scaproInstance.id])
        redirect(action: "show", id: scaproInstance.id)
    }

    def delete(Long id) {
        def scaproInstance = Scapro.get(id)
        if (!scaproInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scapro.label', default: 'Scapro'), id])
            redirect(action: "list")
            return
        }

        try {
            scaproInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scapro.label', default: 'Scapro'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scapro.label', default: 'Scapro'), id])
            redirect(action: "show", id: id)
        }
    }
}
