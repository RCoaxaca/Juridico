package tablas

import org.springframework.dao.DataIntegrityViolationException
import tablas.Scasol
import catalogos.Campo
import catalogos.Opcion


class ScaactController {
    def springSecurityService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
    
        def beforeInterceptor = [
        action:this.&auth,

        ]
    
    
    private auth(){
                    /*if(!session.user){
                    redirect(action:'login')
                        return false;
                    }*/
                    if(!isLoggedIn())
                    {
                        println("No Esta logeado")
                        redirect(controller: "Logout")
                        return false
                    }
                   }
    
    def progreerro(){
     
    }
  
    def verProgresivo(){
        def maximo=Scasol.findAll("From Scasol where id=(select max(id) from Scasol)") 
        def progresivo2= maximo.id.toString()
        def progresivo=""     
        for(int i=0;i<progresivo2.length();i++){
            
            if(progresivo2[i]>47 && progresivo2[i]<58){
                
               progresivo+=progresivo2[i]
            }
            
        }      
//        println "------------------------------------------ "
//        println progresivo    
        render(template:"continuio",model:[progresivo:progresivo])
    }
    
    def getContinuo(){
           def maximo=Scasol.findAll("From Scasol where id=(select max(id) from Scasol)") 
        def progresivo2= maximo.id.toString()
        def progresivo=""     
        for(int i=0;i<progresivo2.length();i++){
            
            if(progresivo2[i]>47 && progresivo2[i]<58){
                
               progresivo+=progresivo2[i]
            }
            
        }      
       int entero = Integer.parseInt(progresivo)
       def val = entero 
        
       render(template:"progresivo",model:[val:val])   
        
    }
       def getCont(){
        def maximo=Scaact.executeQuery("select max(id) from Scaact")
       
        def nott=Integer.parseInt(maximo[0].toString())
        def val= nott +1
       println val
    
       render(template:"continuo",model:[val:val])   
        
    }
    

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [scaactInstanceList: Scaact.list(params), scaactInstanceTotal: Scaact.count()]
    }

    def getMostrar(){
        def scaactInstance = new Scaact(params)
      //  println params.exapro
       // println params.id
         def vari=params.id
         //println vari
         if(vari=='1'){
              render(template:"padre", model:[scaactInstance: scaactInstance], layout:"ajax")
              render(template:"acta", model:[scaactInstance: scaactInstance], layout:"ajax")
         }
         
         if(vari=='2'){
              render(template:"madre", model:[scaactInstance: scaactInstance], layout:"ajax")
               render(template:"acta", model:[scaactInstance: scaactInstance], layout:"ajax")
         }
         
         if(vari=='3'){
              render(template:"padre", model:[scaactInstance: scaactInstance], layout:"ajax")
              render(template:"madre", model:[scaactInstance: scaactInstance], layout:"ajax")
              render(template:"acta", model:[scaactInstance: scaactInstance], layout:"ajax")
         }
    }
    def getMostrar2(){
        def scaactInstance = new Scaact(params)
 
         def vari=params.id
     
         if(vari=='1'){
              render(template:"padre", model:[scaactInstance: scaactInstance], layout:"ajax")
              render(template:"acta2", model:[scaactInstance: scaactInstance], layout:"ajax")
         }
         
         if(vari=='2'){
              render(template:"madre", model:[scaactInstance: scaactInstance], layout:"ajax")
              render(template:"acta2", model:[scaactInstance: scaactInstance], layout:"ajax")
         }
         
         if(vari=='3'){
              render(template:"padre", model:[scaactInstance: scaactInstance], layout:"ajax")
              render(template:"madre", model:[scaactInstance: scaactInstance], layout:"ajax")
               render(template:"acta2", model:[scaactInstance: scaactInstance], layout:"ajax")
         }
    }    
    
    def getEnvierro(){
       
        def campoInstance = Campo.get(params.id)
         
       //def  vari = campoInstance.campo
   
      render(template:"vari",model:[campoInstance:campoInstance])
       // println vari
    }
    def create() {
        [scaactInstance: new Scaact(params)]
    }
   def create2() { 
        [scaactInstance: new Scaact(params)]
    }
    def create3() {
        [scaactInstance: new Scaact(params)]
    }
    def save() {
        def scaactInstance = new Scaact(params)
        scaactInstance.ip=request.getRemoteAddr().toString()
        scaactInstance.usuario=springSecurityService.currentUser.id
        if (!scaactInstance.save(flush: true)) {
            render(view: "create", model: [scaactInstance: scaactInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'scaact.label', default: 'Scaact'), scaactInstance.id])
        redirect(action: "show", id: scaactInstance.id)
    }

    def show(Long id) {
        def scaactInstance = Scaact.get(id)
        if (!scaactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaact.label', default: 'Scaact'), id])
            redirect(action: "list")
            return
        }

        [scaactInstance: scaactInstance]
    }

    def edit(Long id) {
        def scaactInstance = Scaact.get(id)
        if (!scaactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaact.label', default: 'Scaact'), id])
            redirect(action: "list")
            return
        }

        [scaactInstance: scaactInstance]
    }
    
     def edit2(Long id) {
        def scaactInstance = Scaact.get(id)
        if (!scaactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaact.label', default: 'Scaact'), id])
            redirect(action: "list")
            return
        }

        [scaactInstance: scaactInstance]
    }

    def update(Long id, Long version) {
        def scaactInstance = Scaact.get(id)
        if (!scaactInstance) {
            flash.message = scaactInstance.exppro
             
          
             redirect(controller: "opcion",action:"opciones")
            return
        }

        if (version != null) {
            if (scaactInstance.version > version) {
                scaactInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scaact.label', default: 'Scaact')] as Object[],
                          "Another user has updated this Scaact while you were editing")
                render(view: "edit", model: [scaactInstance: scaactInstance])
                return
            }
        }

        scaactInstance.properties = params
        scaactInstance.ip=request.getRemoteAddr().toString()
        scaactInstance.usuario=springSecurityService.currentUser.id
        if (!scaactInstance.save(flush: true)) {
            render(view: "edit", model: [scaactInstance: scaactInstance])
            return
        }

         flash.message = scaactInstance.exppro
         redirect(controller:"opcion", action: "opciones2", id: scaactInstance.exppro)
     
    }
    
    def update2(Long id, Long version) {
        def scaactInstance = Scaact.get(id)     
        if (!scaactInstance) {
               flash.message = scaactInstance.exppro
         redirect(controller:"opcion", action: "opciones2", id: scaactInstance.exppro)          
            return
        }

        if (version != null) {
            if (scaactInstance.version > version) {
                scaactInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scaact.label', default: 'Scaact')] as Object[],
                          "Another user has updated this Scaact while you were editing")
                render(view: "edit2", model: [scaactInstance: scaactInstance])
                return
            }
        }

        scaactInstance.properties = params
        scaactInstance.ip=request.getRemoteAddr().toString()
        scaactInstance.usuario=springSecurityService.currentUser.id
        if (!scaactInstance.save(flush: true)) {
            render(view: "edit2", model: [scaactInstance: scaactInstance])
            return
        }

          flash.message = scaactInstance.exppro
         redirect(controller:"opcion", action: "opciones2", id: scaactInstance.exppro)
    }
    def delete(Long id) {
        def scaactInstance = Scaact.get(id)
        if (!scaactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaact.label', default: 'Scaact'), id])
            redirect(action: "list")
            return
        }

        try {
            scaactInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scaact.label', default: 'Scaact'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scaact.label', default: 'Scaact'), id])
            redirect(action: "show", id: id)
        }
    }
}
