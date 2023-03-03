package catalogos

import org.springframework.dao.DataIntegrityViolationException

class ErroresperadoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
    def getDat = {
       def tipoerrorInstance = Tipoerror.get(params.id)
        def errorList = tipoerrorInstance?.error   
       // render(template:"vererror", model: [errorList:errorList])
       def val= params.id
       if (val == '1'){
           redirect(controller:'scaerr', action:'create')
            
       }
       if (val =='2'){
           redirect(controller:'scaact', action:'create')
       }
        
    }
    
    def getErrores = {   
       def tipoerrorInstance = Tipoerror.get(params.id)   
       def errorList = tipoerrorInstance?.error
       render(template:"vererror", model: [errorList:errorList] ) 
    }

    
 
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [erroresperadoInstanceList: Erroresperado.list(params), erroresperadoInstanceTotal: Erroresperado.count()]
    }

    def create() {
        [erroresperadoInstance: new Erroresperado(params)]
    }

    def save() {
        def erroresperadoInstance = new Erroresperado(params)
        if (!erroresperadoInstance.save(flush: true)) {
            render(view: "create", model: [erroresperadoInstance: erroresperadoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'erroresperado.label', default: 'Erroresperado'), erroresperadoInstance.id])
        redirect(action: "show", id: erroresperadoInstance.id)
    }

    def show(Long id) {
        def erroresperadoInstance = Erroresperado.get(id)
        if (!erroresperadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'erroresperado.label', default: 'Erroresperado'), id])
            redirect(action: "list")
            return
        }

        [erroresperadoInstance: erroresperadoInstance]
    }

    def edit(Long id) {
        def erroresperadoInstance = Erroresperado.get(id)
        if (!erroresperadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'erroresperado.label', default: 'Erroresperado'), id])
            redirect(action: "list")
            return
        }

        [erroresperadoInstance: erroresperadoInstance]
    }

    def update(Long id, Long version) {
        def erroresperadoInstance = Erroresperado.get(id)
        if (!erroresperadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'erroresperado.label', default: 'Erroresperado'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (erroresperadoInstance.version > version) {
                erroresperadoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'erroresperado.label', default: 'Erroresperado')] as Object[],
                          "Another user has updated this Erroresperado while you were editing")
                render(view: "edit", model: [erroresperadoInstance: erroresperadoInstance])
                return
            }
        }

        erroresperadoInstance.properties = params

        if (!erroresperadoInstance.save(flush: true)) {
            render(view: "edit", model: [erroresperadoInstance: erroresperadoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'erroresperado.label', default: 'Erroresperado'), erroresperadoInstance.id])
        redirect(action: "show", id: erroresperadoInstance.id)
    }

    def delete(Long id) {
        def erroresperadoInstance = Erroresperado.get(id)
        if (!erroresperadoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'erroresperado.label', default: 'Erroresperado'), id])
            redirect(action: "list")
            return
        }

        try {
            erroresperadoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'erroresperado.label', default: 'Erroresperado'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'erroresperado.label', default: 'Erroresperado'), id])
            redirect(action: "show", id: id)
        }
    }
}
