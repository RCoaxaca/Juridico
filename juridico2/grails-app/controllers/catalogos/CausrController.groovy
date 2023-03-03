package catalogos

import org.springframework.dao.DataIntegrityViolationException

class CausrController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [causrInstanceList: Causr.list(params), causrInstanceTotal: Causr.count()]
    }

    def create() {
        [causrInstance: new Causr(params)]
    }

    def save() {
        def causrInstance = new Causr(params)
        if (!causrInstance.save(flush: true)) {
            render(view: "create", model: [causrInstance: causrInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'causr.label', default: 'Causr'), causrInstance.id])
        redirect(action: "show", id: causrInstance.id)
    }

    def show(Long id) {
        def causrInstance = Causr.get(id)
        if (!causrInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'causr.label', default: 'Causr'), id])
            redirect(action: "list")
            return
        }

        [causrInstance: causrInstance]
    }

    def edit(Long id) {
        def causrInstance = Causr.get(id)
        if (!causrInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'causr.label', default: 'Causr'), id])
            redirect(action: "list")
            return
        }

        [causrInstance: causrInstance]
    }

    def update(Long id, Long version) {
        def causrInstance = Causr.get(id)
        if (!causrInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'causr.label', default: 'Causr'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (causrInstance.version > version) {
                causrInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'causr.label', default: 'Causr')] as Object[],
                          "Another user has updated this Causr while you were editing")
                render(view: "edit", model: [causrInstance: causrInstance])
                return
            }
        }

        causrInstance.properties = params

        if (!causrInstance.save(flush: true)) {
            render(view: "edit", model: [causrInstance: causrInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'causr.label', default: 'Causr'), causrInstance.id])
        redirect(action: "show", id: causrInstance.id)
    }

    def delete(Long id) {
        def causrInstance = Causr.get(id)
        if (!causrInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'causr.label', default: 'Causr'), id])
            redirect(action: "list")
            return
        }

        try {
            causrInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'causr.label', default: 'Causr'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'causr.label', default: 'Causr'), id])
            redirect(action: "show", id: id)
        }
    }
}
