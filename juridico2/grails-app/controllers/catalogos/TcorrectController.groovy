package catalogos

import org.springframework.dao.DataIntegrityViolationException

class TcorrectController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [tcorrectInstanceList: Tcorrect.list(params), tcorrectInstanceTotal: Tcorrect.count()]
    }

    def create() {
        [tcorrectInstance: new Tcorrect(params)]
    }

    def save() {
        def tcorrectInstance = new Tcorrect(params)
        if (!tcorrectInstance.save(flush: true)) {
            render(view: "create", model: [tcorrectInstance: tcorrectInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tcorrect.label', default: 'Tcorrect'), tcorrectInstance.id])
        redirect(action: "show", id: tcorrectInstance.id)
    }

    def show(Long id) {
        def tcorrectInstance = Tcorrect.get(id)
        if (!tcorrectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tcorrect.label', default: 'Tcorrect'), id])
            redirect(action: "list")
            return
        }

        [tcorrectInstance: tcorrectInstance]
    }

    def edit(Long id) {
        def tcorrectInstance = Tcorrect.get(id)
        if (!tcorrectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tcorrect.label', default: 'Tcorrect'), id])
            redirect(action: "list")
            return
        }

        [tcorrectInstance: tcorrectInstance]
    }

    def update(Long id, Long version) {
        def tcorrectInstance = Tcorrect.get(id)
        if (!tcorrectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tcorrect.label', default: 'Tcorrect'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tcorrectInstance.version > version) {
                tcorrectInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tcorrect.label', default: 'Tcorrect')] as Object[],
                          "Another user has updated this Tcorrect while you were editing")
                render(view: "edit", model: [tcorrectInstance: tcorrectInstance])
                return
            }
        }

        tcorrectInstance.properties = params

        if (!tcorrectInstance.save(flush: true)) {
            render(view: "edit", model: [tcorrectInstance: tcorrectInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tcorrect.label', default: 'Tcorrect'), tcorrectInstance.id])
        redirect(action: "show", id: tcorrectInstance.id)
    }

    def delete(Long id) {
        def tcorrectInstance = Tcorrect.get(id)
        if (!tcorrectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tcorrect.label', default: 'Tcorrect'), id])
            redirect(action: "list")
            return
        }

        try {
            tcorrectInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tcorrect.label', default: 'Tcorrect'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tcorrect.label', default: 'Tcorrect'), id])
            redirect(action: "show", id: id)
        }
    }
}
