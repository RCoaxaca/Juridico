package catalogos

import org.springframework.dao.DataIntegrityViolationException

class EncargadoJuridicoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [encargadoJuridicoInstanceList: EncargadoJuridico.list(params), encargadoJuridicoInstanceTotal: EncargadoJuridico.count()]
    }

    def create() {
        [encargadoJuridicoInstance: new EncargadoJuridico(params)]
    }

    def save() {
         println(params.activo)
        //if(params.activo){println("sssssssssssss")}
        if(params.activo){
            println("Si lo activo")
        def encargadoanterior=EncargadoJuridico.find("From EncargadoJuridico where activo=true and fin is null")
        println(encargadoanterior)
        if(!encargadoanterior.equals())
        {
            println("No viene vacio y vamos a actualizar al jefe anterior")
            def anterior=EncargadoJuridico.get(encargadoanterior.id)
            Date hoy= new Date();
            anterior.fin=hoy
            anterior.activo=false
            anterior.save(flush: true)
            println(anterior)
            println("******-----")
        }
        }
        
        
        def encargadoJuridicoInstance = new EncargadoJuridico(params)
        if (!encargadoJuridicoInstance.save(flush: true)) {
            render(view: "create", model: [encargadoJuridicoInstance: encargadoJuridicoInstance])
            return
        }

        //flash.message = message(code: 'default.created.message', args: [message(code: 'encargadoJuridico.label', default: 'EncargadoJuridico'), encargadoJuridicoInstance.id])
        redirect(controller:"user",action: "index")
    }

    def show(Long id) {
        def encargadoJuridicoInstance = EncargadoJuridico.get(id)
        if (!encargadoJuridicoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encargadoJuridico.label', default: 'EncargadoJuridico'), id])
            redirect(action: "list")
            return
        }

        [encargadoJuridicoInstance: encargadoJuridicoInstance]
    }

    def edit(Long id) {
        def encargadoJuridicoInstance = EncargadoJuridico.get(id)
        if (!encargadoJuridicoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encargadoJuridico.label', default: 'EncargadoJuridico'), id])
            redirect(action: "list")
            return
        }

        [encargadoJuridicoInstance: encargadoJuridicoInstance]
    }

    def update(Long id, Long version) {
        def encargadoJuridicoInstance = EncargadoJuridico.get(id)
        if (!encargadoJuridicoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encargadoJuridico.label', default: 'EncargadoJuridico'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (encargadoJuridicoInstance.version > version) {
                encargadoJuridicoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'encargadoJuridico.label', default: 'EncargadoJuridico')] as Object[],
                          "Another user has updated this EncargadoJuridico while you were editing")
                render(view: "edit", model: [encargadoJuridicoInstance: encargadoJuridicoInstance])
                return
            }
        }

        encargadoJuridicoInstance.properties = params

        if (!encargadoJuridicoInstance.save(flush: true)) {
            render(view: "edit", model: [encargadoJuridicoInstance: encargadoJuridicoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'encargadoJuridico.label', default: 'EncargadoJuridico'), encargadoJuridicoInstance.id])
        redirect(action: "list", id: encargadoJuridicoInstance.id)
    }

    def delete(Long id) {
        def encargadoJuridicoInstance = EncargadoJuridico.get(id)
        if (!encargadoJuridicoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encargadoJuridico.label', default: 'EncargadoJuridico'), id])
            redirect(action: "list")
            return
        }

        try {
            encargadoJuridicoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'encargadoJuridico.label', default: 'EncargadoJuridico'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'encargadoJuridico.label', default: 'EncargadoJuridico'), id])
            redirect(action: "show", id: id)
        }
    }
}
