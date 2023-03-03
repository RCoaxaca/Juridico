package catalogos

import org.springframework.dao.DataIntegrityViolationException

class EncargadoArchivoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [encargadoArchivoInstanceList: EncargadoArchivo.list(params), encargadoArchivoInstanceTotal: EncargadoArchivo.count()]
    }

    def create() {
        [encargadoArchivoInstance: new EncargadoArchivo(params)]
    }

    def save() {
        println(params.activo)
        //if(params.activo){println("sssssssssssss")}
        if(params.activo){
            println("Si lo activo")
        def encargadoanterior=EncargadoArchivo.find("From EncargadoArchivo where activo=true and fin is null")
        println(encargadoanterior)
        if(!encargadoanterior.equals())
        {
            println("No viene vacio y vamos a actualizar al jefe anterior")
            def anterior=EncargadoArchivo.get(encargadoanterior.id)
            Date hoy= new Date();
            anterior.fin=hoy
            anterior.activo=false
            anterior.save(flush: true)
            println(anterior)
            println("******-----")
        }
        }
        def encargadoArchivoInstance = new EncargadoArchivo(params)
        if (!encargadoArchivoInstance.save(flush: true)) {
            render(view: "create", model: [encargadoArchivoInstance: encargadoArchivoInstance])
            return
        }

        //flash.message = message(code: 'default.created.message', args: [message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo'), encargadoArchivoInstance.id])
        //redirect(action: "show", id: encargadoArchivoInstance.id)
        redirect(controller :"user", action: "index")
    }

    def show(Long id) {
        def encargadoArchivoInstance = EncargadoArchivo.get(id)
        if (!encargadoArchivoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo'), id])
            redirect(action: "list")
            return
        }

        [encargadoArchivoInstance: encargadoArchivoInstance]
    }

    def edit(Long id) {
        def encargadoArchivoInstance = EncargadoArchivo.get(id)
        if (!encargadoArchivoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo'), id])
            redirect(action: "list")
            return
        }

        [encargadoArchivoInstance: encargadoArchivoInstance]
    }

    def update(Long id, Long version) {
        def encargadoArchivoInstance = EncargadoArchivo.get(id)
        if (!encargadoArchivoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (encargadoArchivoInstance.version > version) {
                encargadoArchivoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo')] as Object[],
                          "Another user has updated this EncargadoArchivo while you were editing")
                render(view: "edit", model: [encargadoArchivoInstance: encargadoArchivoInstance])
                return
            }
        }

        encargadoArchivoInstance.properties = params

        if (!encargadoArchivoInstance.save(flush: true)) {
            render(view: "edit", model: [encargadoArchivoInstance: encargadoArchivoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo'), encargadoArchivoInstance.id])
        redirect(action: "list", id: encargadoArchivoInstance.id)
    }

    def delete(Long id) {
        def encargadoArchivoInstance = EncargadoArchivo.get(id)
        if (!encargadoArchivoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo'), id])
            redirect(action: "list")
            return
        }

        try {
            encargadoArchivoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'encargadoArchivo.label', default: 'EncargadoArchivo'), id])
            redirect(action: "show", id: id)
        }
    }
}
