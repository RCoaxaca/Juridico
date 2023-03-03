package catalogos

import org.springframework.dao.DataIntegrityViolationException

class VentaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
    
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ventaInstanceList: Venta.list(params), ventaInstanceTotal: Venta.count()]
    }
    
    def listado(Integer max) {
        params.max = Math.min(max ?: 25, 100)
        [ventaInstanceList: Venta.list(params), ventaInstanceTotal: Venta.count()]
    }
    
   def listadodic(Integer max) {
        params.max = Math.min(max ?: 25, 100)
        [ventaInstanceList: Venta.list(params), ventaInstanceTotal: Venta.count()]
    }
    
    def create() {
        [ventaInstance: new Venta(params)]
    }
    
    def busqueda(){
      // vista  
    }
    
    def busquedic(){
      // vista  
    }
        
    def buscar(){
     def p = Venta.get(params.id)

     //  println  p
        //def ventaInstance = Venta.get(params.id)
      render (template:"busque", model:[p:p]) 
    } 
    
    def buscardic(){
     def p = Venta.get(params.id)
     //  println  p
        //def ventaInstance = Venta.get(params.id)
      render (template:"busquedic", model:[p:p]) 
    } 
    
    def save() {
        
         if (!params.folioexp)
         {
                def incremento = Venta.executeQuery("select max(id) from Venta")
                def nott=Integer.parseInt(incremento[0].toString())
                def val= nott +1
                
            Date fecha = new Date()
            def ano = fecha.year +1900
             
            if (val < 9){
               params.folioexp = "DRC"+"/"+"UJ/"+"000"+val +"/"+ ano
            }else
            if(val >= 10 && val <= 99 ){
               params.folioexp = "DRC"+"/"+"UJ/"+"00"+val+"/"+ ano
            }else
            if(val >= 100 && val <= 999){
                 params.folioexp = "DRC"+"/"+"UJ/"+"0"+val +"/"+ ano
            }else
            {
                 params.folioexp = "DRC"+"/"+"UJ/"+""+val +"/"+ ano
            }   
         }

        def ventaInstance = new Venta(params)
        
        if (!ventaInstance.save(flush: true)) {
            render(view: "create", model: [ventaInstance: ventaInstance])
            return
        }
        def mess = "La solucitud "+ ventaInstance.id + " se guardo correctamente" 
        flash.message = mess
        redirect(controller:"user" ,action: "index")
    }

    def show(Long id) {
        def ventaInstance = Venta.get(id)
        if (!ventaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'venta.label', default: 'Venta'), id])
            redirect(action: "list")
            return
        }
        [ventaInstance: ventaInstance]
    }

    def edit(Long id) {
        def ventaInstance = Venta.get(id)
        if (!ventaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'venta.label', default: 'Venta'), id])
            redirect(action: "list")
            return
        }

        [ventaInstance: ventaInstance]
    }

    def update(Long id, Long version) {
        def ventaInstance = Venta.get(id)
        def ventani = Venta.get(id)
        def model =[ventani:ventani]
        if (!ventaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'venta.label', default: 'Venta'), id])
            chain (controller:"scasol", action:"create", model:model)
            return
        }
        if (version != null) {
            if (ventaInstance.version > version) {
                ventaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'venta.label', default: 'Venta')] as Object[],
                          "Another user has updated this Venta while you were editing")
                render(view: "edit", model:[ventaInstance: ventaInstance])
                return
            }
        }

        ventaInstance.properties = params

        if (!ventaInstance.save(flush: true)) {
            render(view: "edit", model: [ventaInstance: ventaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'venta.label', default: 'Venta'), ventaInstance.id])
            chain (controller:"scasol", action:"create", model:model)    
         
    }

    def delete(Long id) {
        def ventaInstance = Venta.get(id)
        if (!ventaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'venta.label', default: 'Venta'), id])
            redirect(action: "list")
            return
        }

        try {
            ventaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'venta.label', default: 'Venta'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'venta.label', default: 'Venta'), id])
            redirect(action: "show", id: id)
        }
    }
}
