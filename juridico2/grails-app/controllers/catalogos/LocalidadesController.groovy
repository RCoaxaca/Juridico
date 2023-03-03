package catalogos

import org.springframework.dao.DataIntegrityViolationException
import catalogos.Scampo
import catalogos.Scaofi 
import grails.converters.JSON

class LocalidadesController { 

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
    
    def getOficialia(){
       println (params.id)
       println(params.mpo)
        def locali=Localidades.get(params.id)
        def ofi=Localidadofi.findAllByLocalidad(locali)
        println(ofi+" Aqui va ofi")
        if(ofi.equals())
        {      //return    
            println(locali.mpo.toString()+" Aqui va el toString")
            def mp=Scampo.findByDescrip(locali.mpo.toString())
            println("Aqui va mp "+mp)
            println(params.mpo.toString()+" Aqui va la clave del municipio")
            ofi=Scamposcaofi.findAllByMunicipio(mp)
            println(ofi +"Aqui va ofi")
            if(ofi.equals())
            {
            ofi=mp.oficialia
            println("Nuevamente aqui va ofi "+ofi)
            render(template:"oficialia", model:[ofi:ofi])
            }
            else{render(template:"oficialia", model:[ofi:ofi])}
        }
        else
        {
            render(template:"oficialia", model:[ofi:ofi])
        }
    }    
    
     def getLocalidades () {
      
       def scampoInstance = Scampo.get(params.id)
       
       def oficialiasList
      
       def oficialias=Scamposcaofi.findAllByMunicipio(scampoInstance)
       
       if(oficialias.size>0)
       {
           //println("tiene mas de una oficialia")
       oficialiasList=oficialias
       }
       else{println("solo tiene una")   
           oficialiasList=scampoInstance.oficialia
           }
       
        def localidadesList = scampoInstance?.localidad    
        //oficialiasList=scampoInstance.oficialia
       
        render(template:"clocalidades", model: [localidadesList:localidadesList, oficialiasList:oficialiasList])
    }
    
    def getOficialiaLocalidad(){
        def locali=Localidades.get(params.id)
        def ofi=Localidadofi.findAllByLocalidad(locali)
        if(!ofi){
            println("entra...")
            def mp=locali?.mpo
            ofi=Scamposcaofi.findAllByMunicipio(mp)
            if(!ofi)
                ofi<<mp?.oficialia
            else
                ofi=ofi?.oficialia
        }
        else
           ofi= ofi?.oficialia
        def data=[localidades:[], oficialias:ofi] 
        render data as JSON
    }    
    
    def getLocalidadesMunpio () {
        
        def scampoInstance = Scampo.get(params.id)
        def oficialiasList=[]
        def localidadesList=[]
       localidadesList=scampoInstance?.localidad
        if(localidadesList){
          def localidad
          for(def locali:localidadesList){
                 localidad=locali; break
          }
            oficialiasList=Localidadofi.findAllByLocalidad(localidad)
                if(!oficialiasList){
                    oficialiasList=Scamposcaofi.findAllByMunicipio(scampoInstance)
                    if(!oficialiasList)
                        oficialiasList<<scampoInstance?.oficialia
                    else
                        oficialiasList=oficialiasList?.oficialia
                }
                else
                   oficialiasList= oficialiasList?.oficialia
        }
        else
           oficialiasList=scampoInstance?.munici?.oficialia
        def data=[localidades:localidadesList, oficialias:oficialiasList] 
        render data as JSON
    }
    
    
      def getLocal = {
      
        def scampoInstance = Scampo.get(params.id)
        def localidadesList = scampoInstance?.localidad
       render(template:"cloc", model: [localidadesList:localidadesList])
    }
    
    def getLocalidades2 = {
      def scampoInstance = Scampo.get(params.id)      
        def localidadesList = scampoInstance?.localidad     
       render(template:"clocalidades2", model: [localidadesList:localidadesList])
   
    }    

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [localidadesInstanceList: Localidades.list(params), localidadesInstanceTotal: Localidades.count()]
    }

    def create() {
        [localidadesInstance: new Localidades(params)]
    }

    def save() {
        def localidadesInstance = new Localidades(params)
        if (!localidadesInstance.save(flush: true)) {
            render(view: "create", model: [localidadesInstance: localidadesInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'localidades.label', default: 'Localidades'), localidadesInstance.id])
        redirect(action: "show", id: localidadesInstance.id)
    }

    def show(Long id) {
        def localidadesInstance = Localidades.get(id)
        if (!localidadesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localidades.label', default: 'Localidades'), id])
            redirect(action: "list")
            return
        }

        [localidadesInstance: localidadesInstance]
    }

    def edit(Long id) {
        def localidadesInstance = Localidades.get(id)
        if (!localidadesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localidades.label', default: 'Localidades'), id])
            redirect(action: "list")
            return
        }

        [localidadesInstance: localidadesInstance]
    }

    def update(Long id, Long version) {
        def localidadesInstance = Localidades.get(id)
        if (!localidadesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localidades.label', default: 'Localidades'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (localidadesInstance.version > version) {
                localidadesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'localidades.label', default: 'Localidades')] as Object[],
                          "Another user has updated this Localidades while you were editing")
                render(view: "edit", model: [localidadesInstance: localidadesInstance])
                return
            }
        }

        localidadesInstance.properties = params

        if (!localidadesInstance.save(flush: true)) {
            render(view: "edit", model: [localidadesInstance: localidadesInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'localidades.label', default: 'Localidades'), localidadesInstance.id])
        redirect(action: "show", id: localidadesInstance.id)
    }

    def delete(Long id) {
        def localidadesInstance = Localidades.get(id)
        if (!localidadesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localidades.label', default: 'Localidades'), id])
            redirect(action: "list")
            return
        }

        try {
            localidadesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'localidades.label', default: 'Localidades'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'localidades.label', default: 'Localidades'), id])
            redirect(action: "show", id: id)
        }
    }
}
