package catalogos

import org.springframework.dao.DataIntegrityViolationException

class NacionalidadController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [nacionalidadInstanceList: Nacionalidad.list(params), nacionalidadInstanceTotal: Nacionalidad.count()]
    }

    def create() {
        [nacionalidadInstance: new Nacionalidad(params)]
    }

    def save() {
        def nacionalidadInstance = new Nacionalidad(params)
        if (!nacionalidadInstance.save(flush: true)) {
            render(view: "create", model: [nacionalidadInstance: nacionalidadInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'nacionalidad.label', default: 'Nacionalidad'), nacionalidadInstance.id])
        redirect(action: "show", id: nacionalidadInstance.id)
    }

    def show(Long id) {
        def nacionalidadInstance = Nacionalidad.get(id)
        if (!nacionalidadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nacionalidad.label', default: 'Nacionalidad'), id])
            redirect(action: "list")
            return
        }

        [nacionalidadInstance: nacionalidadInstance]
    }

    def edit(Long id) {
        def nacionalidadInstance = Nacionalidad.get(id)
        if (!nacionalidadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nacionalidad.label', default: 'Nacionalidad'), id])
            redirect(action: "list")
            return
        }

        [nacionalidadInstance: nacionalidadInstance]
    }

    def update(Long id, Long version) {
        def nacionalidadInstance = Nacionalidad.get(id)
        if (!nacionalidadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nacionalidad.label', default: 'Nacionalidad'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (nacionalidadInstance.version > version) {
                nacionalidadInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'nacionalidad.label', default: 'Nacionalidad')] as Object[],
                          "Another user has updated this Nacionalidad while you were editing")
                render(view: "edit", model: [nacionalidadInstance: nacionalidadInstance])
                return
            }
        }

        nacionalidadInstance.properties = params

        if (!nacionalidadInstance.save(flush: true)) {
            render(view: "edit", model: [nacionalidadInstance: nacionalidadInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'nacionalidad.label', default: 'Nacionalidad'), nacionalidadInstance.id])
        redirect(action: "show", id: nacionalidadInstance.id)
    }

    def delete(Long id) {
        def nacionalidadInstance = Nacionalidad.get(id)
        if (!nacionalidadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nacionalidad.label', default: 'Nacionalidad'), id])
            redirect(action: "list")
            return
        }

        try {
            nacionalidadInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'nacionalidad.label', default: 'Nacionalidad'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'nacionalidad.label', default: 'Nacionalidad'), id])
            redirect(action: "show", id: id)
        }
    }
}
