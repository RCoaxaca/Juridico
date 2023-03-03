package catalogos

import org.springframework.dao.DataIntegrityViolationException

class EncargadoAclaracionesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [encargadoAclaracionesInstanceList: EncargadoAclaraciones.list(params), encargadoAclaracionesInstanceTotal: EncargadoAclaraciones.count()]
    }

    def create() {
        [encargadoAclaracionesInstance: new EncargadoAclaraciones(params)]
    }

    def save() {
           println(params.activo)
        //if(params.activo){println("sssssssssssss")}
        if(params.activo){
            println("Si lo activo")
        def encargadoanterior=EncargadoAclaraciones.find("From EncargadoAclaraciones where activo=true and fin is null")
        println(encargadoanterior)
        if(!encargadoanterior.equals())
        {
            println("No viene vacio y vamos a actualizar al jefe anterior")
            def anterior=EncargadoAclaraciones.get(encargadoanterior.id)
            Date hoy= new Date();
            anterior.fin=hoy
            anterior.activo=false
            anterior.save(flush: true)
            println(anterior)
            println("******-----")
        }
        }
        def encargadoAclaracionesInstance = new EncargadoAclaraciones(params)
        if (!encargadoAclaracionesInstance.save(flush: true)) {
            render(view: "create", model: [encargadoAclaracionesInstance: encargadoAclaracionesInstance])
            return
        }

        //flash.message = message(code: 'default.created.message', args: [message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo'), encargadoArchivoInstance.id])
        //redirect(action: "show", id: encargadoArchivoInstance.id)
        redirect(controller :"user", action: "index")
    }

    def show(Long id) {
        def encargadoAclaracionesInstance = EncargadoAclaraciones.get(id)
        if (!encargadoAclaracionesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encargadoAclaraciones.label', default: 'EncargadoAclaraciones'), id])
            redirect(action: "list")
            return
        }

        [encargadoAclaracionesInstance: encargadoAclaracionesInstance]
    }

    def edit(Long id) {
        def encargadoAclaracionesInstance = EncargadoAclaraciones.get(id)
        if (!encargadoAclaracionesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encargadoAclaraciones.label', default: 'EncargadoAclaraciones'), id])
            redirect(action: "list")
            return
        }

        [encargadoAclaracionesInstance: encargadoAclaracionesInstance]
    }

    def update(Long id, Long version) {
        def encargadoAclaracionesInstance = EncargadoAclaraciones.get(id)
        if (!encargadoAclaracionesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encargadoAclaraciones.label', default: 'EncargadoAclaraciones'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (encargadoAclaracionesInstance.version > version) {
                encargadoAclaracionesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'encargadoAclaraciones.label', default: 'EncargadoAclaraciones')] as Object[],
                          "Another user has updated this EncargadoAclaraciones while you were editing")
                render(view: "edit", model: [encargadoAclaracionesInstance: encargadoAclaracionesInstance])
                return
            }
        }

        encargadoAclaracionesInstance.properties = params

        if (!encargadoAclaracionesInstance.save(flush: true)) {
            render(view: "edit", model: [encargadoAclaracionesInstance: encargadoAclaracionesInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'encargadoAclaraciones.label', default: 'EncargadoAclaraciones'), encargadoAclaracionesInstance.id])
        redirect(action: "list", id: encargadoAclaracionesInstance.id)
    }

    def delete(Long id) {
        def encargadoAclaracionesInstance = EncargadoAclaraciones.get(id)
        if (!encargadoAclaracionesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encargadoAclaraciones.label', default: 'EncargadoAclaraciones'), id])
            redirect(action: "list")
            return
        }

        try {
            encargadoAclaracionesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'encargadoAclaraciones.label', default: 'EncargadoAclaraciones'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'encargadoAclaraciones.label', default: 'EncargadoAclaraciones'), id])
            redirect(action: "show", id: id)
        }
    }
}
