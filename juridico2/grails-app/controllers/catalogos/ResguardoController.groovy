package catalogos

import org.springframework.dao.DataIntegrityViolationException

class ResguardoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [resguardoInstanceList: Resguardo.list(params), resguardoInstanceTotal: Resguardo.count()]
    }

    def create() {
        [resguardoInstance: new Resguardo(params)]
    }

    def save() {
        def busca=Resguardo.findByNumero_expediente(params.numero_expediente)
        if(!busca){
            
        def resguardoInstance = new Resguardo(params)
        if (!resguardoInstance.save(flush: true)) {
            render(view: "create", model: [resguardoInstance: resguardoInstance])
            return
        }

        //flash.message = message(code: 'default.created.message', args: [message(code: 'resguardo.label', default: 'Resguardo'), resguardoInstance.id])
        redirect(action: "list")
        }else
        {
            //render ("function suma_y_muestra() {alert('Este archivo y afue agregado anteriormente')}")
             flash.message = "Este archivo ya fue agregado anteriormente"
            redirect(action:"list")
        }
       
    }

    def show(Long id) {
        def resguardoInstance = Resguardo.get(id)
        if (!resguardoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'resguardo.label', default: 'Resguardo'), id])
            redirect(action: "list")
            return
        }

        [resguardoInstance: resguardoInstance]
    }

    def edit(Long id) {
        def resguardoInstance = Resguardo.get(id)
        if (!resguardoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'resguardo.label', default: 'Resguardo'), id])
            redirect(action: "list")
            return
        }

        [resguardoInstance: resguardoInstance]
    }

    def update(Long id, Long version) {
        def resguardoInstance = Resguardo.get(id)
        if (!resguardoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'resguardo.label', default: 'Resguardo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (resguardoInstance.version > version) {
                resguardoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'resguardo.label', default: 'Resguardo')] as Object[],
                          "Another user has updated this Resguardo while you were editing")
                render(view: "edit", model: [resguardoInstance: resguardoInstance])
                return
            }
        }

        resguardoInstance.properties = params

        if (!resguardoInstance.save(flush: true)) {
            render(view: "edit", model: [resguardoInstance: resguardoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'resguardo.label', default: 'Resguardo'), resguardoInstance.id])
        redirect(action: "show", id: resguardoInstance.id)
    }

    def delete(Long id) {
        def resguardoInstance = Resguardo.get(id)
        if (!resguardoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'resguardo.label', default: 'Resguardo'), id])
            redirect(action: "list")
            return
        }

        try {
            resguardoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'resguardo.label', default: 'Resguardo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'resguardo.label', default: 'Resguardo'), id])
            redirect(action: "show", id: id)
        }
    }
}
