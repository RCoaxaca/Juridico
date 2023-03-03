package catalogos

import org.springframework.dao.DataIntegrityViolationException

class TipoerrorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [tipoerrorInstanceList: Tipoerror.list(params), tipoerrorInstanceTotal: Tipoerror.count()]
    }
    def filtra(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [tipoerrorInstanceList: Tipoerror.list(params), tipoerrorInstanceTotal: Tipoerror.count()]
    }
    def create() {
        [tipoerrorInstance: new Tipoerror(params)]
    }

    def save() {
        def tipoerrorInstance = new Tipoerror(params)
        if (!tipoerrorInstance.save(flush: true)) {
            render(view: "create", model: [tipoerrorInstance: tipoerrorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tipoerror.label', default: 'Tipoerror'), tipoerrorInstance.id])
        redirect(action: "show", id: tipoerrorInstance.id)
    }

    def show(Long id) {
        def tipoerrorInstance = Tipoerror.get(id)
        if (!tipoerrorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoerror.label', default: 'Tipoerror'), id])
            redirect(action: "list")
            return
        }

        [tipoerrorInstance: tipoerrorInstance]
    }

    def edit(Long id) {
        def tipoerrorInstance = Tipoerror.get(id)
        if (!tipoerrorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoerror.label', default: 'Tipoerror'), id])
            redirect(action: "list")
            return
        }

        [tipoerrorInstance: tipoerrorInstance]
    }

    def update(Long id, Long version) {
        def tipoerrorInstance = Tipoerror.get(id)
        if (!tipoerrorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoerror.label', default: 'Tipoerror'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tipoerrorInstance.version > version) {
                tipoerrorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipoerror.label', default: 'Tipoerror')] as Object[],
                          "Another user has updated this Tipoerror while you were editing")
                render(view: "edit", model: [tipoerrorInstance: tipoerrorInstance])
                return
            }
        }

        tipoerrorInstance.properties = params

        if (!tipoerrorInstance.save(flush: true)) {
            render(view: "edit", model: [tipoerrorInstance: tipoerrorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoerror.label', default: 'Tipoerror'), tipoerrorInstance.id])
        redirect(action: "show", id: tipoerrorInstance.id)
    }

    def delete(Long id) {
        def tipoerrorInstance = Tipoerror.get(id)
        if (!tipoerrorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoerror.label', default: 'Tipoerror'), id])
            redirect(action: "list")
            return
        }

        try {
            tipoerrorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoerror.label', default: 'Tipoerror'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoerror.label', default: 'Tipoerror'), id])
            redirect(action: "show", id: id)
        }
    }
}
