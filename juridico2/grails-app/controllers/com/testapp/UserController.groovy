package com.testapp

import org.springframework.dao.DataIntegrityViolationException
import tablas.Scasol
import org.apache.commons.io.FileUtils
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.springframework.dao.DataIntegrityViolationException
import catalogos.EncargadoJuridico
import catalogos.DireccionesIp


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

class UserController {
    def jasperService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def valida() {
        def valida=false
        def usuario=DireccionesIp.findAll()
        println(usuario.size())
        for(int i=0;i<usuario.size();i++){
            println(usuario.ip[i].toString())
        if(usuario.ip[i].toString().equals(request.getRemoteAddr().toString()))
        {
            //break
            valida=true
            println("Encontro algo")
            //redirect(action:'index')
            break
        }

    }
       println(request.getRemoteAddr())
       
        if(valida)
        {
            redirect(action:'index')
        }
        else
        {
            redirect(controller: "Logout")
        }
       //println(request.getLocalAddr())
    }
    
    
    def index()
    {
        
        
    }
    def index3(Long idexpediente)
    {
       
    }
    def index2(Long idexpediente)
    {
        def encargado=EncargadoJuridico.find("From EncargadoJuridico where activo=true")
        println(idexpediente+" Llego al controlador de usuario y va a buscar el registro")
        def solicitud=Scasol.get(idexpediente)
        Date fecha= new Date()
        
        println(fecha.calendarDate)
        def f=fecha.calendarDate.toString()
        println(f.substring(0, 10))
      
        def factual=f.substring(0, 10).split("-")
        println(factual[0])
        println(factual[1])
        println(factual[2])
        println("/*--*/")
        
        String [] mesesdia=["","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"]  
        println(mesesdia[Integer.parseInt(factual[1])])
        println("//***---")
            def p1="En virtud de encontrarse pendiente de resolver el expediente a nombre de "
            
            p1+=solicitud.promov.toString()+" según acta número "+solicitud.numact.toString()
            p1+=" de fecha "+solicitud.fchact.toString().substring(8,10)+" de "+mesesdia[Integer.parseInt(solicitud.fchact.toString().substring(5,7))]
            p1+=" de "+solicitud.fchact.toString().substring(0,4)
            p1+=" levantada en "
            if(solicitud.loc.toString().equals(solicitud.mpo.toString())){
            p1+=solicitud.loc.toString()+", "+solicitud.dto.toString()+", Oaxaca "    
            }else{
                p1+=solicitud.loc.toString()+", "+solicitud.mpo.toString()+", "+solicitud.dto.toString()+", Oaxaca " 
            }
            p1+=", y recibida por el Departamento Jurídico a mi cargo con fecha "+factual[2]+" de "+mesesdia[Integer.parseInt(factual[1])]+" de "+factual[0]
            def persona="C."
            persona+=solicitud.promov
          def mapapendiente=[]
          byte[] mapacompleto
            mapapendiente << [
            fecha:"Oaxaca de Juárez, Oax., a "+factual[2]+" de "+mesesdia[Integer.parseInt(factual[1])]+" de "+factual[0],    
            num:"Expediente No.: "+solicitud.expro+"/"+solicitud.expano, 
            persona:persona,
            firma:"A T E N T A M E N T E\n\
SUFRAGIO EFECTIVO, NO REELECCION\n\
EL RESPETO AL DERECHO AJENO ES LA PAZ\n\
EL JEFE DE LA OFICINA DE ACLARACIONES\n\
LIC. "+encargado,
            pa1:p1,
            pa2:solicitud.obser+"\n\
\n\
Asimismo se le apercibe de que en caso de no presentar los documentos requerido(s)"+
" en el término de 10 días se procederá a resolver  LO PROCEDENTE. Lo anterior con fundamento"+
"en los artículos 20 y 29 "+\
"del Reglamento del Registro Civil en el Estado de Oaxaca.\n\
\n\
\n\
\n\
"]
                     
         println mapapendiente
        // File tempToPrint = new File("c:\\reportes\\reportedia.pdf")
        //File tempToPrint = new File("/home/rcivil/outs/" + folioSolicitud + ".pdf")
        try{
            def reportDef = new JasperReportDef(name: "pendiente.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  :  mapapendiente )
       
         //  mapacompleto= FileUtils.writeByteArrayToFile(tempToPrint, jasperService.generateReport(reportDef).toByteArray())
           mapacompleto = jasperService.generateReport(reportDef).toByteArray()
        
             println("se completo la tarea")
             //redirect(action: "index") 
        }catch(Exception ex){
            println(ex.printStackTrace())
        }
        //response.addHeader("Content-Disposition", 'attachment; filename="pendiente.pdf"')
      //response.addHeader("Content-Disposition", 'attachment; filename="pendiente exp "'+solicitud.expro+"/"+solicitud.expano+'".pdf"')
      response.addHeader("Content-Disposition", 'attachment; filename="pendiente exp "'+solicitud.expro+"/"+solicitud.expano+'".pdf"')
        response.contentType='application/pdf'
    	response.outputStream << mapacompleto
       response.outputStream.close()
      response.outputStream.flush()
        //response.flushBuffer()
	//response.out << bytes
       println("antes de cerrar")
        
        //response.close()
        //redirect(action: "index") 
        //return redirect(action: "index")
        //return false
        //redirect(action:"index3",params: [idexpediente:idexpediente])  
        //def vuelvealindex={
        //    redirect (action: "index")
       // }
       
        
        
        
       
        
        
    }
    def actilizar(){
        
        
    }
    def  creado(){
        
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
        [userInstance: new User(params)]
    }

    def save() {
        def usuario
        def userInstance = new User(params)
        println(params.roleid)
        println("---------------")
        println(params.pervalidador)
        println(params.perarchivo)
        println(params.perdictaminador)
        println(params.peradmin)
        println(params.perventanilla)
        println("---------------")
        
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }
        
        if(params.pervalidador)
        {
            //usuario=usuario=User.get(userInstance.id)
            def userRoleInstance = new UserRole()
                userRoleInstance.user=User.get(userInstance.id)
                userRoleInstance.role=Role.get(2)
                if(!userRoleInstance.save(flush: true))
                {
                    return
                    println("*********")
                }
                //userRoleInstance.save()
        }
        
        
         if(params.perventanilla)
        {
            //usuario=usuario=User.get(userInstance.id)
            def userRoleInstance = new UserRole()
                userRoleInstance.user=User.get(userInstance.id)
                userRoleInstance.role=Role.get(5)
                if(!userRoleInstance.save(flush: true))
                {
                    return
                    println("*********")
                }
                //userRoleInstance.save()
        }
        
        
        
        if(params.perarchivo)
        {
            //usuario=usuario=User.get(userInstance.id)
            def userRoleInstance = new UserRole()
                userRoleInstance.user=User.get(userInstance.id)
                userRoleInstance.role=Role.get(6)
                if(!userRoleInstance.save(flush: true))
                {
                    return
                    println("*********")
                }
                //userRoleInstance.save()
        }
        
        
        if(params.perdictaminador)
        {
            //usuario=usuario=User.get(userInstance.id)
            def userRoleInstance = new UserRole()
                userRoleInstance.user=User.get(userInstance.id)
                userRoleInstance.role=Role.get(3)
                if(!userRoleInstance.save(flush: true))
                {
                    return
                    println("*********")
                }
                //userRoleInstance.save()
        }
        
        
        if(params.peradmin)
        {
            //usuario=usuario=User.get(userInstance.id)
            def userRoleInstance = new UserRole()
                userRoleInstance.user=User.get(userInstance.id)
                userRoleInstance.role=Role.get(1)
                if(!userRoleInstance.save(flush: true))
                {
                    return
                    println("*********")
                }
                //userRoleInstance.save()
        }
        
        
        //def userrole=UserRole.executeQuery("insert into UserRole(user,role) values(?,?)",[userInstance.id,params.roleid])
        //def mapa=new UserRole()
        //mapa.user=userInstance.id
        //mapa.role=params.roleid
        //mapa.user.putAt("user",userInstance.username)
        //mapa.role.putAt("role",params.roleid)
        //println(userInstance.id)
        //def userRoleInstance=new UserRole(mapa)
        //println(params.roleid+"----******-------")
        //userRoleInstance.setRole(params.roleid)
        //def userRoleInstance= new UserRole()
        //def userRoleInstance=UserRole.findAllByRole(params.role.id)
        //def rol=Role.findAllByAuthority(params.role.id)
        //def rol2=new Role()
        //rol2=Role.get(params.role.id)
        //def userr=User.get(userInstance.id)
        //println("Aqui va el num de usuario "+userr)
        //println("Aqui va el rol2 "+rol2)
        //println(rol.id+" "+rol.authority+" ALgo del rol")
        //println(userRoleInstance)
        //def al=new UserRole()//=UserRole.create(rol2.id,userr.id)
        //al=UserRole.setRole(rol2)
        //al=UserRole.setUser(User.get(userInstance.id))
            //executeQuery("insert into UserRole values")
        //[userRoleInstance: new UserRole(params)]
        //def rol=params.role.id
        //def usuario=userInstance.id
        //def userRoleInstance=new UserRole()
        //userRoleInstance.role=rol
        //userRoleInstance.user=usuario
        
        //if(!userRoleInstance.save(flush:true))
        //{
        //    println("No se pudo guardar")
            
        //}
        //def m=['user':userInstance.id,'rol':params.roleid]
        //chain(action:'usuariorol',m:m)
        flash.message = "Usuario " + userInstance.username + " creado correctamente"
        //render(view: "create", model: [userInstance: userInstance])
        redirect(controller:'user',action:'creado')
        println(userInstance.id+" id usuario antes de enviar")
        println(params.roleid+" id rol antes de enviar")
        //redirect(controller:"UserRole", action: "save2",params:[iduser:userInstance.id,idrole:params.roleid])
    }
    
 

    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }
    def edit2(Long id) {
        def userInstance = User.get(id)
        def permisoarchivo
        def permisovalidador
        def permisodictaminador
        def permisoadmin
        def permisoventanilla
        //=UserRole.findAllByUserAndRole(userInstance,Role.get(6))
        if(UserRole.findAllByUserAndRole(userInstance,Role.get(6)))
        {
            permisoarchivo=true
        }
        
        if(UserRole.findAllByUserAndRole(userInstance,Role.get(5)))
        {
            permisoventanilla=true
        }
        
        if(UserRole.findAllByUserAndRole(userInstance,Role.get(2)))
        {
            permisovalidador=true
        }
        
        if(UserRole.findAllByUserAndRole(userInstance,Role.get(3)))
        {
            permisodictaminador=true
        }
        
         if(UserRole.findAllByUserAndRole(userInstance,Role.get(1)))
        {
            permisoadmin=true
        }
        
        
        
        
        
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance,permisoarchivo:permisoarchivo,permisodictaminador:permisodictaminador,permisovalidador:permisovalidador,permisoadmin:permisoadmin,permisoventanilla:permisoventanilla]
    }
    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params
        def elimina=UserRole.removeAll(userInstance)
        def permisos=UserRole.findAllByUser(userInstance)
        //for(int o=0;o<permisos.size();o++)
        //{
//            def permiso=UserRole.findByUser(userInstance)
          //  permisos[o].delete(flush: true)
        //}

        println(permisos)
        //permisos.delete()
        println("Aqui van todos los permisos")
        
        
        
         if(params.peradmin)
        {
            def prueba1=User.findByAuthority("ROLE_ADMIN")
            println(rueba1)
            //usuario=usuario=User.get(userInstance.id)
            def userRoleInstance = new UserRole()
            println("*********"+userInstance.id)
                userRoleInstance.user=User.get(userInstance.id)
                userRoleInstance.role=Role.get(1)
                if(!userRoleInstance.save(flush: true))
                {
                    return
                    println("*********")
                }
                //userRoleInstance.save()
        }
        
        
        
                if(params.pervalidador)
        {
            //usuario=usuario=User.get(userInstance.id)
            def userRoleInstance = new UserRole()
                userRoleInstance.user=User.get(userInstance.id)
                userRoleInstance.role=Role.get(2)
                if(!userRoleInstance.save(flush: true))
                {
                    return
                    println("*********")
                }
                //userRoleInstance.save()
        }
        
        
         if(params.perdictaminador)
        {
            //usuario=usuario=User.get(userInstance.id)
            def userRoleInstance = new UserRole()
                userRoleInstance.user=User.get(userInstance.id)
                userRoleInstance.role=Role.get(3)
                if(!userRoleInstance.save(flush: true))
                {
                    return
                    println("*********")
                }
                //userRoleInstance.save()
        }
        
        
         if(params.perventanilla)
        {
            //usuario=usuario=User.get(userInstance.id)
            def userRoleInstance = new UserRole()
                userRoleInstance.user=User.get(userInstance.id)
                userRoleInstance.role=Role.get(5)
                if(!userRoleInstance.save(flush: true))
                {
                    return
                    println("*********")
                }
                //userRoleInstance.save()
        }
        
        
        
        if(params.perarchivo)
        {
            //usuario=usuario=User.get(userInstance.id)
            def userRoleInstance = new UserRole()
                userRoleInstance.user=User.get(userInstance.id)
                userRoleInstance.role=Role.get(6)
                if(!userRoleInstance.save(flush: true))
                {
                    return
                    println("*********")
                }
                //userRoleInstance.save()
        }
        
        
       
        
        
       
        
        

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

                flash.message = "Se actualizo correctamente"
        redirect(action: "actilizar")
    }
    
    
    
    def update2(Long id, Long version)
    {
        
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }
        
        userInstance.properties = params
        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

                flash.message = "Se actualizo correctamente"
        redirect(action: "actilizar")
        
    }

    

    
    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }
}
