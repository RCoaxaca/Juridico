package tablas

import org.apache.commons.io.FileUtils
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.springframework.dao.DataIntegrityViolationException
import org.apache.pdfbox.util.PDFMergerUtility
import catalogos.Venta
import org.krysalis.barcode4j.impl.code39.Code39Bean
import tablas.Scasol
import com.testapp.User
import java.text.DateFormat
import catalogos.Docesta
import catalogos.Localidadofi
import catalogos.Scaofi
import catalogos.EncargadoJuridico
import catalogos.EncargadoArchivo
import catalogos.Opcion
import catalogos.Papeleta
import catalogos.Fields
import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.lang.ArrayUtils

class ScasolController {


    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def jasperService
    def reportDef
    def barcode4jService
    def springSecurityService
    def anio= new Date()
    def fech1=anio.toTimestamp() 
    String [] hoy=fech1.toString().split(" ")
    String [] hoy2=hoy[0].toString().split("-")
    String [] meses2=["","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"]
    def enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")  
    def enarchivo=EncargadoArchivo.find("from EncargadoArchivo where activo=true")
    

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [scasolInstanceList: Scasol.list(params), scasolInstanceTotal: Scasol.count()]
    }
    
    def listado(Integer max) { 
        params.max = Math.min(max ?: 10, 100)
        [scasolInstanceList: Scasol.list(params), scasolInstanceTotal: Scasol.count()]
    }
    
    def reporte(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        Date fec=new Date();
        fec.setHours(00)
        fec.setMinutes(00)
        fec.setSeconds(00)
        def fech=fec.toTimestamp() 
        
        [scasolInstanceList: Scasol.findAllByFechasolAndEstado(fech,Docesta.get(1),params), scasolInstanceTotal: Scasol.findAllByFechasolAndEstado(fech,Docesta.get(1)).size()]
    }
    def seguimiento(Integer max){
        params.max = Math.min(max ?: 10, 100)
        [scasolInstanceList: Scasol.list(params), scasolInstanceTotal: Scasol.count()]        
        
    }
    
    
    def imprimirPendiente()
    {
      
    }
    
    def impresas(Integer max){ 
         params.max = Math.min(max ?: 10, 100)         
        [scasolInstanceList: Scasol.findAllByIsprint(1,params.max), scasolInstanceTotal: Scasol.findAllByIsprint(1).size()]                 
    }
    def resolucionesnega(Integer max){
        def stat= Docesta.get(2)
        params.max = Math.min(max ?: 10, 100)
        [scasolInstanceList: Scasol.findAllByEstado(stat,params.max), scasolInstanceTotal: Scasol.findAllByEstado(stat).size()]          
    }
    def resolucionesproce(Integer max){        
        def stat= Docesta.get(3)
        params.max = Math.min(max ?: 10, 100)        
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        def scasolEncontrado=Scasol.findAllByEstado(stat)
        def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
        [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size()]                  
    }
    def resolucionespendiente (Integer max){
         params.max = Math.min(max ?: 10, 100)
         def stat= Docesta.get(2)
         [scasolInstanceList: Scasol.findAllByEstadoAndIsprint(stat, 0,params.max), scasolInstanceTotal: Scasol.findAllByEstadoAndIsprint(stat, 0).size()] 
        
    }
    def validar(){
        if(Scasol.findByFolio(params.id)){
            render (template:"folio", model:[p:params.id])            
        }else{
            render (template:"folio1", model:[p:params.id])
        }   
    }        
   def valsliata(Integer max){
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        def stat= Docesta.get(2)
        def nue = Docesta.get(3)        
        def scasolEncontrado=Scasol.findAllByEstadoOrEstadoOrExpano(stat,nue,(anio.year+1900))
        def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
        [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size()] 
       
   }
      def valsliata2(Integer max){
        def stat= Docesta.get(3)
        params.max = Math.min(max ?: 10, 100)
        [scasolInstanceList: Scasol.findAllByEstadoAndExpano(stat,anio.year+1900,params), scasolInstanceTotal: Scasol.findAllByEstadoAndExpano(stat,anio.year+1900).size()]        
   }
   
      def valsliata3(Integer max){
        def stat= Docesta.get(2)
        params.max = Math.min(max ?: 10, 100)
        [scasolInstanceList: Scasol.findAllByEstadoAndExpano(stat,anio.year+1900,params), scasolInstanceTotal: Scasol.findAllByEstadoAndExpano(stat,anio.year+1900).size()]        
   }   



def imprimir(){
        String [] mesesdia=["","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"]     
        def fecha=""
        Date fec=new Date();
        fec.setHours(00)
        fec.setMinutes(00)
        fec.setSeconds(00)
        def fech=fec.toTimestamp()  
        def userInstance=User.get(params.idusu)
        String [] fecha2=fech.toString().split(" ")
        String [] fecha3=fecha2[0].toString().split("-")
        def mes=Integer.parseInt(fecha3[1])
        def ventaInstance=Scasol.findAllByFechasolAndEstado(fech,Docesta.get(1))
        def solicitudes=[]
        def expro
        def maparepdia=[]
        for(def solicitud:ventaInstance)
        {
            maparepdia<<["expro":solicitud.expro,
                         "solicitante":(solicitud.nomb?:""+" "+solicitud.apepa?:""+" "+solicitud.apema?:""),
                         "interesado":((solicitud?.nom_intere?:"")+" "+(solicitud?.ap1_intere?:"")+" "+(solicitud?.ap2_intere?:""))?:"",
                         "estado":solicitud.estado.docuestado,
                         "solicitud":solicitud.folio ,
                         "fecha":fecha?.toUpperCase(),
                         "entrego":(userInstance?.nombre+" "+userInstance?.apellpa+" "+userInstance?.apellma?:"").toUpperCase() ,
                         "recibio":(params.encargado?:"")?.toUpperCase()]
        }        
          byte[] mapacompleto
        def reportDef = new JasperReportDef(name: "reportedia.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  :  maparepdia )
        try{
           mapacompleto = jasperService.generateReport(reportDef).toByteArray()
        
             println("se completo la tarea")
        }catch(Exception ex){
            println(ex.printStackTrace())
        }
        response.addHeader("Content-Disposition", 'inline; filename="reporte.pdf"')
	response.contentType='application/pdf'
	response.outputStream << mapacompleto
        response.outputStream.flush
        response.outputStream.close()
        return false
    }
    
    
    
   def  impresion (Long id, Long version){
       
             def scasolInstance = Scasol.get(id)
        if (!scasolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
            redirect(action: "edit2", id: scasolInstance.id)
            return
        }

        if (version != null) {
            if (scasolInstance.version > version) {
                scasolInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scasol.label', default: 'Scasol')] as Object[],
                          "Another user has updated this Scasol while you were editing")
                render(view: "edit", model: [scasolInstance: scasolInstance])
                return
            }
        }

        scasolInstance.properties = params
        scasolInstance.ip=request.getRemoteAddr().toString()
        scasolInstance.usuario=springSecurityService.currentUser.id
        if (!scasolInstance.save(flush: true)) {
            render(view: "edit", model: [scasolInstance: scasolInstance])
            return
        }

    }
    
    
    
    def captura(){
     [scasolInstance: new Scasol(params)]
    }
    
       
    def listadodic(Integer max) {
        def status= Docesta.get(1)
        def nuevo = Docesta.get(4)
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        params.sort=params.sort?:'expro'
        def scasolEncontrado=Scasol.findAllByEstadoOrEstado(status,nuevo)
        def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
        [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size()]      
    }
    
    def listadodic2(Integer max) {
        def stat= Docesta.get(2)
        def nue = Docesta.get(3)
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        params.sort=params.sort?:'expro'
        def scasolEncontrado=Scasol.findAllByEstadoOrEstado(stat,nue)
        def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
        [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size()]     
    }
    def busqueda(){        
    }
    
    def buscar23(){
        params.nombresol
        def p = Scasol.get(params.nombresol)
        render (template:"busque", model:[p:p])
    }
    
    def buscar1(){
        def max=10
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        params.sort=params.sort?:'expro'
        if(!params.folio || !params.year){
            redirect(controller:"scasol", action: "busqueda")
            flash.message = "Especificar No. Exp. o Año"   
        }
        else{
            def scasolEncontrado=Scasol.findAllByExproAndExpano(params.folio, params.year)
            def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
            [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size(),folio:params.folio, year:params.year]      
        }
    }      
    def buscar2(){
        def max=10
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        params.sort=params.sort?:'expro'
        if(!params.nombresol){
            redirect(controller:"scasol", action: "busqueda")
            flash.message = "Especificar el nombre de Promovente"   
        }
        else{
           
             println(params.nombresol)
             def scasolEncontrado= Scasol.findAllByPromov(params.nombresol.toString())
             def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
             [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size(),nombresol:params.nombresol]      
        }
        
    }
    
    def buscar5(){
        def max=10
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        params.sort=params.sort?:'expro'
        println("------------------")
        println(params)
        11531
        println("------------------")
        def scasolEncontrado
        if(params.nombreint && params.apepa && params.apema)
        {
            println("Trae todos los datos")
            //scasolEncontrado=Scasol.findAll("From Scasol where nom_intere like ? and ap1_intere like ? and ap2_intere like ",[params.nombreint.toUpperCase(),params.apepa,params.apema])
            scasolEncontrado=Scasol.findAllByNom_intereLikeAndAp1_intereLikeAndAp2_intereLike(params.nombreint.toUpperCase(),params.apepa,params.apema)
        }
        else if(params.nombreint && params.apepa)
        {
           scasolEncontrado=Scasol.findAllByNom_intereLikeAndAp1_intereLike(params.nombreint,params.apepa) 
        }
        else if(params.nombreint && params.apema)
        {
            scasolEncontrado=Scasol.findAllByNom_intereLikeAndAp2_intereLike(params.nombreint, params.apema)
        }
        else
        {
          scasolEncontrado=Scasol.findAllByNom_intereLike(params.nombreint)  
        }
        
        /*if(!params.nombreint){
            redirect(controller:"scasol", action: "busqueda")
            flash.message = "Especificar el nombre del Intersado"   
        }
        else{
                    def valores = params.nombreint.split(' ')
                     def p = valores.length 
                     println p
                     def nombr = valores[0]
                     def apellipater
                     
                     def apellidomate = ""
                     if (p == 1){
                         scasolEncontrado=Scasol.findAllByNom_intereLike("%"+valores[0]+"%")
                         
                     }
                     if (p == 2){
                         scasolEncontrado=Scasol.findAllByNom_intereAndAp1_intere(valores[0],valores[1])
                                
                     }
                     if (p == 3){
                         scasolEncontrado=Scasol.findAllByNom_intereAndAp1_intereAndAp2_intere(valores[0],valores[1],valores[2])
           
                     }        

                        
        }*/
        def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
        [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size(),nombreint:params.nombreint]   
    }
    
    def buscar5dic(){
        def max=10
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        params.sort=params.sort?:'expro'
        if(!params.nombreint){
            redirect(controller:"scasol", action: "busquedic")
            flash.message = "Especificar el nombre del Intersado"   
        }
        else{
                    def valores = params.nombreint.split(' ')
                     def p = valores.length 
                     println p
                     def nombr = valores[0]
                     def apellipater
                     def apellidomate = ""
                     if (p == 1){
                         apellipater = ""
                         apellidomate = ""
                     }
                     if (p == 2){
                          apellipater = valores[1]
                          apellidomate = ""             
                     }
                     if (p == 3){
                         apellipater = valores[1]
                         apellidomate = valores[2]              
                     }        

                     def scasolEncontrado= Scasol.findAllByNom_intereOrAp1_intereOrAp2_intere(nombr,apellipater ,apellidomate)
                     def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
                     [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size(),nombreint:params.nombreint]      
        }
    }
    
    def buscar4(){
        def max=10
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        params.sort=params.sort?:'expro'
        if(params.fchsol.toString().equals("null")){
            redirect(controller:"scasol", action: "busqueda")
            flash.message = "Fecha invalida"   
        }
        else{
            def fecha
                try{
                    Date date =  params.fchsol
                    fecha = date.format( 'yyyy-MM-dd' )
                }
                catch(Exception e) {
                    fecha=params.fchsol
                }
            def scasolEncontrado=Scasol.findAll("From Scasol where DATE_FORMAT(fechasol,'%Y-%m-%d')='"+fecha+"'")
            def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
            [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size(),fechaSol:fecha]         
        }
    }   
    
    def buscar4dic(){
        def max=10
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        params.sort=params.sort?:'expro'
        if(params.fchsol.toString().equals("null")){
            redirect(controller:"scasol", action: "busquedic")
            flash.message = "Fecha invalida"   
        }
        else{
            def fecha
                try{
                    Date date =  params.fchsol
                    fecha = date.format( 'yyyy-MM-dd' )
                }
                catch(Exception e) {
                    fecha=params.fchsol
                }
            def scasolEncontrado=Scasol.findAll("From Scasol where DATE_FORMAT(fechasol,'%Y-%m-%d')='"+fecha+"'")
            def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
            [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size(),fechaSol:fecha]         
        }
    }   
    
    def buscar3(){        
         def max=10
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        params.sort=params.sort?:'expro'
        def scasolEncontrado
        if(!params.nsoli){
            redirect(controller:"scasol", action: "busqueda")
            flash.message = "Especificar el No. de Solicitud"   
        }
        else{
                if (params.nsoli.length() != 0){

                    scasolEncontrado=Scasol.findAllByFolio(params.nsoli)
                }
                else{
                    def p = 0 
                    scasolEncontrado=Scasol.findAllByFolio(p)
                }
               // println p
               def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
                   [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size(),nsoli:params.nsoli]     
        }
    } 
    
    def buscar1dic(){
        def max=10
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        params.sort=params.sort?:'expro'
        if(!params.folio || !params.year){
            redirect(controller:"scasol", action: "busquedic")
            flash.message = "Especificar No. Exp. o Año"   
        }
        else{
            def scasolEncontrado=Scasol.findAllByExproAndExpano(params.folio, params.year)
            def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
            [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size(),folio:params.folio, year:params.year]      
        }
    }     
    
    def buscar2dic(){
         def max=10
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        params.sort=params.sort?:'expro'
        if(!params.nombresol){
            redirect(controller:"scasol", action: "busquedic")
            flash.message = "Especificar el nombre de Promovente"   
        }
        else{
            def valores = params.nombresol.split(' ')
             def p = valores.length 
             println p
             def nombr = valores[0]
             def apellipater
             def apellidomate = ""
             if (p == 1){
                 apellipater = ""
                 apellidomate = ""
             }
             if (p == 2){
                  apellipater = valores[1]
                  apellidomate = ""             
             }
             if (p == 3){
                 apellipater = valores[1]
                 apellidomate = valores[2]              
             }        

             def scasolEncontrado= Scasol.findAllByNombOrApepaOrApema(nombr,apellipater ,apellidomate)
             def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
             [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size(),nombresol:params.nombresol]      
        }
    }
    def buscar3dic(){       
       
        def max=10
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.offset =params.offset ? params.offset as int:0
        params.sort=params.sort?:'expro'
        def scasolEncontrado
        if(!params.nsoli){
            redirect(controller:"scasol", action: "busquedic")
            flash.message = "Especificar el No. de Solicitud"   
        }
        else{
                if (params.nsoli.length() != 0){

                    scasolEncontrado=Scasol.findAllByFolio(params.nsoli)
                }
                else{
                    def p = 0 
                    scasolEncontrado=Scasol.findAllByFolio(p)
                }
                def result=scasolEncontrado.subList(params.offset,Math.min(params.offset+params.max, scasolEncontrado.size()))
                   [scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size(),nsoli:params.nsoli]     
        }
    }
    

    def buscar(){
    
      def valores = params.id.split(' ')
       println valores[0]
       println valores[1]
       println valores[2]
     
      
       def nombr = valores[0]
       def apellipater = valores[1]
       def apellidomate = valores[2]

      def p = Scasol.findByNombAndApepaAndApema(nombr, apellipater , apellidomate)

      render (template:"busque", model:[p:p]) 
    }
    
    def busquedic(){

    }    
    
    def create() {
        [scasolInstance: new Scasol(params)]
    }

    def save() {
        println("----------------------------------")
        println (params)
        println("----------------------------------")
        println(params.estado.toString()+" Aqui va el estado de la solicitud")
        Date fecha = new Date()
        
          if (!params.folioexp)
         {

             
                
                println(fecha.toTimestamp().toString()+" Aqui la hora exacta de la creacion")
                def ano = fecha.year +1900                
                def incremento = Scasol.executeQuery("Select coalesce(max(expro),0) from Scasol where expano="+ano)
             if(incremento[0].toString().equals("0")){
                   incremento=Scapro.executeQuery("Select COALESCE(max(prog),0) from Scapro where year="+ano)
               }
               //def validaexpediente=Scasol.findByExproAndExpano()
               def nott=Integer.parseInt(incremento[0].toString())
               def val= nott +1
                
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

            params.expro=val
            params.expano=ano

         }
         
         
        def scasolInstance = new Scasol(params)
        //scasolInstance.fchsol=fecha.toTimestamp()
        //scasolInstance.fechasol=fecha.toTimestamp()
        println(params.nomb)
        println("////")
        if(params.nomb.isEmpty())
        {
            println("Si viene vacio")
            println(params.promov)
            println("11111111")
            def nombrefalta=params.promov.toString().split(" ")
            println(nombrefalta.length)
            if(nombrefalta.length>3)
            {
                scasolInstance.apema=nombrefalta[nombrefalta.length-1].toUpperCase()
                scasolInstance.apepa=nombrefalta[nombrefalta.length-2].toUpperCase()
                println(nombrefalta[nombrefalta.length-1])
                println(nombrefalta[nombrefalta.length-2])
                def n=""
                //int h=nombrefalta.length
                for(int y=0;y<nombrefalta.length-2;y++)
                {
                    n+=nombrefalta[y].toString()
                    n+=" "
                    println("paso por aquiiiiii")

                }
                scasolInstance.nomb=n.toUpperCase()
                
            }
            if(nombrefalta.length==3)
            {
                println(nombrefalta[0])
                println(nombrefalta[1])
                println(nombrefalta[2])
                println("paso por la tres")
                scasolInstance.nomb=nombrefalta[0].toUpperCase()
                scasolInstance.apepa=nombrefalta[1].toUpperCase()
                scasolInstance.apema=nombrefalta[2].toUpperCase()
            }
            if(nombrefalta.length==2)
            {
                println("paso por la dos")
                scasolInstance.nomb=nombrefalta[0].toUpperCase()
                scasolInstance.apepa=nombrefalta[1].toUpperCase()
                //params.apema=nombrefalta[2]
            }
            if(nombrefalta.length==1)
            {
                println("paso por a uno")
                scasolInstance.nomb=nombrefalta[0].toUpperCase()

            }
            println("##########")
        }
        
            scasolInstance.promov=params.promov.toUpperCase()
            scasolInstance.ap1_intere=params.ap1_intere.trim().toUpperCase()
            scasolInstance.ap2_intere=params.ap2_intere.trim().toUpperCase()
            scasolInstance.nom_intere=params.nom_intere.trim().toUpperCase()
            scasolInstance.fchsol=fecha.toTimestamp()
            scasolInstance.fechasol=fecha.toTimestamp()
            scasolInstance.promov=params.promov.trim().toUpperCase()
            scasolInstance.ip=request.getRemoteAddr().toString()
            scasolInstance.usuario=springSecurityService.currentUser.id
        if (!scasolInstance.save(flush: true)) {
            render(view: "create", model: [scasolInstance: scasolInstance])
            return
        }
       
         if(params.estado.id.toString().equals("2"))
         {
             println("El Status es rechazado")
             redirect(controller:"user",action:"index")
             return
         }
         if(params.estado.id.toString().equals("3"))
        {         
            redirect(controller:"opcion", action: "opciones2" ,params: [id: scasolInstance.id,numeroexpediente:scasolInstance.expro])
            flash.message = scasolInstance.id
            flash.args=scasolInstance.expro
        }else if(!params.estado.id.toString().equals("1"))
        {
            if(params.estado.id.toString().equals("4"))
            { 
                 redirect(action: "show", id: scasolInstance.id)
            }else
            {
                   
            }
            

        
    }
    else{
       flash.message  = "Solicitud creada: " + scasolInstance.folioexp
       redirect(controller:"user" ,action: "index",params: [progresivo: scasolInstance.folioexp])
     }
}

    def show(Long id) {
        def scasolInstance = Scasol.get(id)
        if (!scasolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
            redirect(action: "list")
            return
        }

        [scasolInstance: scasolInstance]
    }

    def edit(Long id) {
        def scasolInstance = Scasol.get(id)
        if (!scasolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
            redirect(action: "list")
            return
        }

        [scasolInstance: scasolInstance]
    }
        
    def edit2(Long id) {
        def scasolInstance = Scasol.get(id)
        if (!scasolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
            redirect(action: "list")
            return
        }

        [scasolInstance: scasolInstance]
    }
    
    def archivo(Long id){
        def idexpediente=id
        def encargado=EncargadoJuridico.find("From EncargadoJuridico where activo=true")
        println(idexpediente+" Llego al controlador de usuario y va a buscar el registro")
        def solicitud=Scasol.get(idexpediente)
        Date fecha= new Date()
        println("Vamos a ver todos lo datos que envio")
        println (params)
        println(solicitud.val.toString())
        println("-------------------------------------")
        
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
            
            p1+=solicitud.nom_intere+""+((!solicitud.ap1_intere.equals())?" "+solicitud.ap1_intere:"")+((!solicitud.ap2_intere.equals())?" "+solicitud.ap2_intere:"")+" según acta número "+solicitud.numact.toString()
            p1+=" de fecha "+solicitud.fchact.toString().substring(8,10)+" de "+meses2[Integer.valueOf(solicitud.fchact.toString().substring(5,7))]
            p1+=" de "+solicitud.fchact.toString().substring(0,4)
            p1+=" levantada en "
            if(solicitud.loc.toString().equals(solicitud.mpo.toString())){
            p1+=solicitud.loc.toString()+", "+solicitud.dto.toString()+", Oaxaca "    
            }else{
                p1+=solicitud.loc.toString()+", "+solicitud.mpo.toString()+", "+solicitud.dto.toString()+", Oaxaca " 
            }
            p1+=", y recibida por el Departamento Jurídico a mi cargo con fecha "+factual[2]+" de "+mesesdia[Integer.parseInt(factual[1])]+" de "+factual[0]
            def persona="C."
            persona+=solicitud.promov.toString()//solicitud.nom_intere+""+((!solicitud.ap1_intere.equals())?" "+solicitud.ap1_intere:"")+((!solicitud.ap2_intere.equals())?" "+solicitud.ap1_intere:"")
          def mapapendiente=[]
          byte[] mapacompleto

                          mapapendiente << [
            fecha:"Oaxaca de Juárez, Oax., a "+factual[2]+" de "+mesesdia[Integer.parseInt(factual[1])]+" de "+factual[0],    
            num:"Expediente No.: "+solicitud.expro+"/"+solicitud.expano, 
            persona:persona,
            usuario:solicitud.cap.toString(),
            firma:"A T E N T A M E N T E\n\
SUFRAGIO EFECTIVO, NO REELECCION\n\
EL RESPETO AL DERECHO AJENO ES LA PAZ\n\
EL JEFE DE LA UNIDAD JURIDICA\n\
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
          

                     
        
        try{
            HashMap<String, Object> params = new HashMap<String, Object>()
            params.put("logo", servletContext.getRealPath("/reports/"))
            
            def reportDef = new JasperReportDef(name: "pendiente.jasper",parameters:params ,fileFormat:JasperExportFormat.PDF_FORMAT, reportData  :  mapapendiente )
       
         mapacompleto = jasperService.generateReport(reportDef).toByteArray()

        }catch(Exception ex){
            println(ex.printStackTrace())
        }
        response.addHeader("Content-Disposition", 'attachment; filename="pendiente exp "'+solicitud.expro+"/"+solicitud.expano+'".pdf"')
        response.contentType='application/pdf'
    	response.outputStream << mapacompleto
        response.outputStream.close()
        response.outputStream.flush()
          return false
    }
    def editval(Long id) {
           
        def scasolInstance = Scasol.get(id)
        if(scasolInstance.fecharesolucion.toString().equals() || scasolInstance.fecharesolucion.equals(null))
        {
           scasolInstance.fecharesolucion=fech1 
        }
        else
        {
            def fechaOriginal
            def fechaActual=hoy2[0]
            def anio
        fechaOriginal=scasolInstance.fechasol 
        fechaOriginal=fechaOriginal.toString().split(" ")[0]
        fechaOriginal=fechaOriginal.toString().split("-")
        anio=fechaOriginal[0]
        if(anio<fechaActual)
        {
           scasolInstance.fecharesolucion=fech1  
        }
          
        }
            //scasolInstance.fecharesolucion=fech1
            scasolInstance.save(flush: true)
        if (!scasolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
            redirect(action: "list")
            return
        }
        println(scasolInstance.expro)
        flash.message =scasolInstance.expro
        [scasolInstance: scasolInstance]
    }
    
    def actualizar(Long id, Long version) {
        def scasolInstance = Scasol.get(id)
        if (!scasolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
             redirect(controller:"user", action: "index")
            return
        }

        if (version != null) {
            if (scasolInstance.version > version) {
                scasolInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scasol.label', default: 'Scasol')] as Object[],
                          "Another user has updated this Scasol while you were editing")
                render(view: "edit", model: [scasolInstance: scasolInstance])
                return
            }
        }

        scasolInstance.properties = params
        scasolInstance.ip=request.getRemoteAddr().toString()
        scasolInstance.usuario=springSecurityService.currentUser.id
        if (!scasolInstance.save(flush: true)) {
            render(view: "edit", model: [scasolInstance: scasolInstance])
            return
        }
        flash.message = message(code: 'default.updated.message', args: [message(code: 'scasol.label', default: 'Scasol'), scasolInstance.id])
        redirect(controller:"user", action: "index")
    }
    
    def update(Long id, Long version) { 
        def scasolInstance = Scasol.get(id)
        if (!scasolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
            redirect(action: "edit2", id: scasolInstance.id)
            return
        }
        println(params.estado.id.toString())

        if (version != null) {
            if (scasolInstance.version > version) {
                scasolInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scasol.label', default: 'Scasol')] as Object[],
                          "Another user has updated this Scasol while you were editing")
                render(view: "edit", model: [scasolInstance: scasolInstance])
                return
            }
        }
        scasolInstance.properties = params
        scasolInstance.ip=request.getRemoteAddr().toString()
        scasolInstance.usuario=springSecurityService.currentUser.id
        if (!scasolInstance.save(flush: true)) {
            render(view: "edit", model: [scasolInstance: scasolInstance])
            return
        }
        
        redirect(action: "edit2", id: scasolInstance.id)
        


       
    }
    
    def update2(Long id, Long version) {
        
        def scasolInstance = Scasol.get(id)     
        def var = scasolInstance.id
    
        if (!scasolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
             redirect(controller: "opcion", action:"opciones" )
            return
        }

        if (version != null) {
            if (scasolInstance.version > version) {
                scasolInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scasol.label', default: 'Scasol')] as Object[],
                          "Another user has updated this Scasol while you were editing")
                render(view: "edit", model: [scasolInstance: scasolInstance])
                return
            }
        }

        scasolInstance.properties = params
        scasolInstance.ip=request.getRemoteAddr().toString()
        scasolInstance.usuario=springSecurityService.currentUser.id
        if (!scasolInstance.save(flush: true)) {
            render(view: "edit", model: [scasolInstance: scasolInstance])
            return
        }

       flash.message = var
          redirect(controller:"opcion", action: "opciones2", id: scasolInstance.id)
         flash.message = scasolInstance.id
     
    }    

    def vali(Long id, Long version) {
        
        def scasolInstance = Scasol.get(id)     
        def var = scasolInstance.id
        println(scasolInstance.sexintere+" Aqui va le sexo para que busques todo lo que tiene")
        def listadeerrores=Scaerr.findAllByExpro(scasolInstance.id)
        
        try{
            if(scasolInstance.sexintere.equals(params.sexintere))
            {
                println("No se cambio para nada el sexo todo sigue normal")
            }
            else
            {
                if(scasolInstance.typact.toString().contains("NACIMIENTO"))
                {
                    println("Se cambio el sexo tienes que reubicar casilleros")
                if(params.sexintere.contains("M")){
                    println("Cambio de niña a niño")
                    for(int i=0;i<listadeerrores.size();i++)
                    {
                        println(listadeerrores.campo[i].id.toString()+" ??????????????????")
                        println(Fields.get((listadeerrores.campo[i].id-301)))
                        listadeerrores[i].campo=Fields.get((listadeerrores.campo[i].id-301))
                        listadeerrores[i].save(flush: true)
                    }
                }
                else{
                    println("Cambio de niño a niña")
                    println(listadeerrores.size()+" %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
                    for(int i=0;i<listadeerrores.size();i++)
                    {
                        println(listadeerrores.campo[i].id.toString()+" &&&&&&&&&&&&&&&&&&&&&&&&&&")
                        println(Fields.get((listadeerrores.campo[i].id+301)))
                        listadeerrores[i].campo=Fields.get((listadeerrores.campo[i].id+301))
                        listadeerrores[i].save(flush: true)
                    }
                }
                }
                
                
            }
        }catch(Exception lol)
        {
            lol.printStackTrace()   
        }
        
        /*try{
                    if(scasolInstance.sexintere.equals(params.sexintere))
        {
          println("No se cambio el sexo todo sigue normal")  
        }
        else {
            println("Se cambio el sexo vamos a tener que cambiar todo")
                    //scasolInstance.sexintere.equals(params.sexintere)
        println("**************************")
        println(listadeerrores)
        println(listadeerrores.campo)
        listadeerrores.each{
            //println(listadeerrores.it.campo.replaceAll("REGISTRADA","REGISTRADO"))
            println(listadeerrores.campo.replaceAll("REGISTRADA","REGISTRADO"))
        }
        def list=new ArrayList()
        def listaposiciones= new ArrayList()
        def listanuevaposcicion = new ArrayList()
        for(int i=0;i<listadeerrores.size();i++)
        {
            if(params.sexintere.contains("M"))
            {
            println(listadeerrores.campo[i].toString().replaceAll("LA REGISTRADA","DEL REGISTRADO"))
            list.add(listadeerrores.campo[i].toString().replaceAll("LA REGISTRADA","DEL REGISTRADO"))
            }
            else{
            println(listadeerrores.campo[i].toString().replaceAll("DEL REGISTRADO","DE LA REGISTRADA"))
            list.add(listadeerrores.campo[i].toString().replaceAll("DEL REGISTRADO","DE LA REGISTRADA"))
            }
            //println(listadeerrores.campo[i].toString().replaceAll("DE LA REGISTRADA","DEL REGISTRADO"))
            //list.add(listadeerrores.campo[i].toString().replaceAll("DE LA REGISTRADA","DEL REGISTRADO"))
            listaposiciones.add(listadeerrores.id[i])

        }
        for(int n=0;n<listadeerrores.size();n++)
        {
            println(Fields.findByNombre(list[n].toString()))
            listanuevaposcicion.add(Fields.findByNombre(list[n].toString()).id)

        }
        def scaerrInstace
        for(int o=0;o<listadeerrores.size();o++)
        {
            //Scasol.executeQuery("update Scaerr set campo=? where id=?",[listaposiciones[o],listanuevaposcicion[0]])
           scaerrInstace=Scaerr.get(listadeerrores.id[o])
           scaerrInstace.campo=Fields.get((Integer.valueOf(listaposiciones[o])+301))
           scaerrInstance.ip=request.getRemoteAddr().toString()
           scaerrInstance.usuario=springSecurityService.currentUser.id
           scaerrInstace.save(flush:true)
           println(listadeerrores.id)
        }
        println(listadeerrores.id)
        println("**************************")
        }
        }catch(Exception lo)
        {
            //lo.printStackTrace()
        }*/
        


        if (!scasolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
             redirect(controller: "opcion", action:"opciones" )
            return
        }

        if (version != null) {
            if (scasolInstance.version > version) {
                scasolInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scasol.label', default: 'Scasol')] as Object[],
                          "Another user has updated this Scasol while you were editing")
                render(view: "edit", model: [scasolInstance: scasolInstance])
                return
            }
        }

        scasolInstance.properties = params
        scasolInstance.ip=request.getRemoteAddr().toString()
        scasolInstance.usuario=springSecurityService.currentUser.id
        if (!scasolInstance.save(flush: true)) {
            render(view: "edit", model: [scasolInstance: scasolInstance])
            return
        }
    
       flash.message = scasolInstance.id
       flash.args=scasolInstance.expro
          redirect(controller:"opcion", action: "opciones2", id: scasolInstance.id)
          
     
    } 
    

    
    
def reimprimeConsiderando()
{
 if(!isLoggedIn())
        {
            println("No Esta logeado")
            redirect(controller: "Logout")
            return
        }
        def solicitud=Scasol.get(params.id)
        new File("D:/codigo.png").withOutputStream { out ->
    barcode4jService.render("code39Generator", solicitud.expro+"/"+solicitud.expano, out, "image/png")
}  
    println("Lego al controlador de reimpresion")
    println(params)
    
    println(solicitud.expano)
    println(solicitud.expro)
    println(params.param2)
    def fechaOriginal
    def fechaActual
    def anio
    def jefeOriginal
    def jefeActual
    jefeActual=enjuridico
    fechaOriginal=solicitud.fechasol  
    println(fechaOriginal.toString().split(" ")[0])
    fechaOriginal=fechaOriginal.toString().split(" ")[0]
    fechaOriginal=fechaOriginal.toString().split("-")        
    fechaOriginal=fechaOriginal[2]+" de "+meses2[Integer.parseInt(fechaOriginal[1])]+" de "+fechaOriginal[0]
    println("!!!!!!!!!!!!!!!!!!!!!!")
    println(solicitud.fecharesolucion)
    println("!!!!!!!!!!!!!!!!!!!!!!")
    if(solicitud.fecharesolucion.toString().equals() || solicitud.fecharesolucion.equals(null))
    {
        solicitud.fecharesolucion=fech1
        //jefeOriginal=EncargadoJuridico.find("From EncargadoJuridico where inicio<='"+solicitud.fechasol+"' and (fin>='"+solicitud.fechasol+"' or fin is null)") 
        //println("select * From EncargadoJuridico where inicio<='"+solicitud.fechasol+"' and (fin>='"+solicitud.fechasol+"' or fin is null)")
    //fechaOriginal=solicitud.fechasol
    fechaActual=hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0]   
    }else{
        //jefeOriginal=EncargadoJuridico.find("From EncargadoJuridico where inicio<='"+solicitud.fecharesolucion+"' and (fin>='"+solicitud.fecharesolucion+"' or fin is null)") 
        def anioO
        def anioA
        anioA=hoy2[0]
        anioO=solicitud.fecharesolucion.toString().split(" ")[0]
        anioO=anioO.split("-")
        anioO=anioO[0]
        if(anioO<anioA)
        {
           solicitud.fecharesolucion=fech1   
        }
        else
        {
          
        }
        
        fechaActual=solicitud.fecharesolucion  
        println(fechaActual.toString().split(" ")[0])
        fechaActual=solicitud.fecharesolucion.toString().split(" ")[0]
        fechaActual=fechaActual.split("-")        
        fechaActual=fechaActual[2]+" de "+meses2[Integer.parseInt(fechaActual[1])]+" de "+fechaActual[0]      
    }
    println("select * From EncargadoJuridico where inicio<='"+solicitud.fechasol+"' and (fin>='"+solicitud.fechasol+"' or fin is null)")
    
    jefeOriginal=EncargadoJuridico.find("From EncargadoJuridico where inicio<='"+solicitud.fechasol+"' and (fin>='"+solicitud.fechasol+"' or fin is null)") 
        
    println(jefeActual)
    println(jefeOriginal)
    solicitud.save(flush: true)
    /*def fechaOriginal
    def fechaActual
    def anio
        fechaOriginal=solicitud.fechasol
        fechaActual=hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0]
        println("||||||||||||||||||||")
        //println(fechaOriginal)
        println(fechaOriginal.toString().split(" ")[0])
        fechaOriginal=fechaOriginal.toString().split(" ")[0]
        fechaOriginal=fechaOriginal.toString().split("-")
        anio=fechaOriginal[0]
        fechaOriginal=fechaOriginal[2]+" de "+meses2[Integer.parseInt(fechaOriginal[1])]+" de "+fechaOriginal[0]
        if(anio.toString().equals(hoy2[0].toString()))
        {
            fechaActual=fechaOriginal
        }
        
        println(fechaOriginal)
        println(fechaActual)
        println("||||||||||||||||||||")*/
    def notanota
    def considerando=Nota.findAllByExpanoAndExpro(solicitud.expano,solicitud.expro)
    if(considerando)
    {
        if(considerando.size()>1)
        {
        notanota=considerando.nota[0]
        notanota=notanota.replaceAll(notanota.substring(0,(notanota.indexOf("</p>")+4)),"")
        notanota=notanota.replaceAll(fechaOriginal,fechaActual)
        notanota=notanota.replaceAll("'>","'> *")
        notanota=notanota.replaceAll("\">","\"> *")
        notanota=notanota.replaceAll("&nbsp;","")         
        notanota=cleanHtml(notanota,'simpleText')
        notanota=notanota.replaceAll("&Iacute;","Í")
        notanota=notanota.replaceAll("&Aacute;","Á")
        notanota=notanota.replaceAll("&Eacute;","É")
        notanota=notanota.replaceAll("&Uacute;","Ú")
        notanota=notanota.replaceAll("&Ntilde;","Ñ")
        notanota=notanota.replaceAll("&Oacute;","Ó")
        notanota=notanota.replaceAll("&uacute;","ú")
        notanota=notanota.replaceAll("&ntilde;","ñ")
        notanota=notanota.replaceAll("&oacute;","ó")
        notanota=notanota.replaceAll("&iacute;","í")
        notanota=notanota.replaceAll("&aacute;","á")
        notanota=notanota.replaceAll("&eacute;","é")
        notanota=notanota.replaceAll("&deg;","°")
        notanota=notanota.replaceAll("&Uuml;","Ü")
        notanota=notanota.replaceAll(fechaOriginal,fechaActual)
        notanota=notanota.replaceAll(jefeOriginal.toString(),jefeActual.toString())
        notanota=notanota.replaceAll("&quot;","\"")
        notanota=notanota.replaceAll("\\*","\n\
")
        notanota=notanota.replaceAll("R E S U E L V E ","                                                                 R  E  S  U  E  L  V  E")
        notanota=notanota.replaceAll("C O N S I D E R A N D O ","                                                                   C O N S I D E R A N D O ")
        
        def espacio="                                                                                                                                                      "
        int longitud=(espacio.toString().length()-((enjuridico.titulo+" "+enjuridico).toString().length()))/2
        espacio=espacio.substring(0,longitud)
        notanota=notanota.replaceAll(""+enjuridico.titulo+" "+enjuridico,espacio+""+enjuridico.titulo+" "+enjuridico)
        
        def notanota2
        notanota2=considerando.nota[1]
        notanota2=notanota2.replaceAll(notanota2.substring(0,(notanota2.indexOf("</p>")+4)),"")
        notanota2=notanota2.replaceAll("'>","'> *")
        notanota2=notanota2.replaceAll("\">","\"> *")
        notanota2=notanota2.replaceAll("&nbsp;","")
        notanota2=notanota2.replaceAll(fechaOriginal,fechaActual)
        notanota2=cleanHtml(notanota2,'simpleText')
        notanota2=notanota2.replaceAll("&Iacute;","Í")
        notanota2=notanota2.replaceAll("&Aacute;","Á")
        notanota2=notanota2.replaceAll("&Eacute;","É")
        notanota2=notanota2.replaceAll("&Uacute;","Ú")
        notanota2=notanota2.replaceAll("&Ntilde;","Ñ")
        notanota2=notanota2.replaceAll("&Oacute;","Ó")
        notanota2=notanota2.replaceAll("&uacute;","ú")
        notanota2=notanota2.replaceAll("&ntilde;","ñ")
        notanota2=notanota2.replaceAll("&oacute;","ó")
        notanota2=notanota2.replaceAll("&iacute;","í")
        notanota2=notanota2.replaceAll("&aacute;","á")
        notanota2=notanota2.replaceAll("&eacute;","é")
        notanota2=notanota2.replaceAll("&quot;","\"")
        notanota2=notanota2.replaceAll("&Uuml;","Ü")
        notanota2=notanota2.replaceAll("&deg;","°")
        notanota2=notanota2.replaceAll(fechaOriginal,fechaActual)
        notanota2=notanota2.replaceAll(jefeOriginal.toString(),jefeActual.toString())
        notanota2=notanota2.replaceAll("\\*","\n\
")
        notanota2=notanota2.replaceAll("R E S U E L V E ","                                                                 R  E  S  U  E  L  V  E")
        notanota2=notanota2.replaceAll("C O N S I D E R A N D O ","                                                                   C O N S I D E R A N D O ")
        //notanota=notanota.replaceAll(""+enjuridico.titulo+" "+enjuridico,espacio+""+enjuridico.titulo+" "+enjuridico)
        notanota2=notanota2.replaceAll(""+enjuridico.titulo+" "+enjuridico,espacio+""+enjuridico.titulo+" "+enjuridico)
        //println(notanota2)
        def mapa=[]
        byte[] bytes
        
        mapa << [
            exp:"Expediente: "+solicitud.expro+"/"+solicitud.expano ,
            fecha:"Oaxaca de Juárez, Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
            copia2:notanota+"\n\
"+User.get(params.param2)+"\n\n\n",
                    copia1:notanota2+"\n\
"+User.get(params.param2)+"\n\n\n"
                    
                ]

                
            def reportDef = new JasperReportDef(name: "imprimirTodo/copia2.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
            JasperReportDef copia = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   

        try{
             bytes = jasperService.generateReport(reportDef).toByteArray()
             byte [] a =bytes
             bytes=jasperService.generateReport(copia).toByteArray()
             byte [] b=bytes
            PDFMergerUtility mergePdf = new PDFMergerUtility() 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
            mergePdf.addSource(new ByteArrayInputStream(a))
            mergePdf.addSource(new ByteArrayInputStream(a))
            mergePdf.addSource(new ByteArrayInputStream(b))
            mergePdf.addSource(new ByteArrayInputStream(b))
            mergePdf.setDestinationStream(byteArrayOutputStream)
            mergePdf.mergeDocuments();
            bytes= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Considerando '+solicitud.expro+'/'+solicitud.expano+'.pdf"')
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return false
            
        }
        else
        {
        notanota=considerando.nota[0]
        notanota=notanota.replaceAll(notanota.substring(0,(notanota.indexOf("</p>")+4)),"")
        notanota=notanota.replaceAll("'>","'> *")
        notanota=notanota.replaceAll("\">","\"> *")
        notanota=notanota.replaceAll("&nbsp;","")         
        notanota=cleanHtml(notanota,'simpleText')
        notanota=notanota.replaceAll("&Iacute;","Í")
        notanota=notanota.replaceAll("&Aacute;","Á")
        notanota=notanota.replaceAll("&Eacute;","É")
        notanota=notanota.replaceAll("&Uacute;","Ú")
        notanota=notanota.replaceAll("&Ntilde;","Ñ")
        notanota=notanota.replaceAll("&Oacute;","Ó")
        notanota=notanota.replaceAll("&uacute;","ú")
        notanota=notanota.replaceAll("&ntilde;","ñ")
        notanota=notanota.replaceAll("&oacute;","ó")
        notanota=notanota.replaceAll("&iacute;","í")
        notanota=notanota.replaceAll("&aacute;","á")
        notanota=notanota.replaceAll("&eacute;","é")
        notanota=notanota.replaceAll("&quot;","\"")
        notanota=notanota.replaceAll("&Uuml;","Ü")
        notanota=notanota.replaceAll(fechaOriginal,fechaActual)
        notanota=notanota.replaceAll(jefeOriginal.toString(),jefeActual.toString())
        notanota=notanota.replaceAll("\\*","\n\
")
        notanota=notanota.replaceAll("R E S U E L V E ","                                                                 R  E  S  U  E  L  V  E")
        notanota=notanota.replaceAll("C O N S I D E R A N D O ","                                                                   C O N S I D E R A N D O ")
        
        def espacio="                                                                                                                                                      "
        int longitud=(espacio.toString().length()-((enjuridico.titulo+" "+enjuridico).toString().length()))/2
        espacio=espacio.substring(0,longitud)
        notanota=notanota.replaceAll(""+enjuridico.titulo+" "+enjuridico,espacio+""+enjuridico.titulo+" "+enjuridico)
        def mapa=[]
        byte[] bytes
        
        mapa << [
            exp:"Expediente: "+solicitud.expro+"/"+solicitud.expano ,
            fecha:"Oaxaca de Juárez, Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
            copia1:notanota+"\n\
"+User.get(params.param2)+"\n\n\n"
                ]

              println(notanota)  
            def reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )

        try{
             bytes = jasperService.generateReport(reportDef).toByteArray()
             byte [] a =bytes
            PDFMergerUtility mergePdf = new PDFMergerUtility() 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream() 
            if(considerando.idn.toString().contains("A"))
            {
            mergePdf.addSource(new ByteArrayInputStream(a))
            mergePdf.addSource(new ByteArrayInputStream(a))
            mergePdf.addSource(new ByteArrayInputStream(a))
            }
            else{
                mergePdf.addSource(new ByteArrayInputStream(a))
                mergePdf.addSource(new ByteArrayInputStream(a))
            }
            mergePdf.setDestinationStream(byteArrayOutputStream)
            mergePdf.mergeDocuments();
            bytes= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Considerando '+solicitud.expro+'/'+solicitud.expano+'.pdf"')
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return false
        }

    }
    else {

        redirect(controller: "opcion", action: "imprimirConsiderando", params: [id: params.id,usuario1:params.param2])
     
    }
    
}

    
    
def reimprimePapeleta(){
     if(!isLoggedIn())
        {
            println("No Esta logeado")
            redirect(controller: "Logout")
            return
        }
    println("Llego al controlador de reimpresion")
    println(params)
    def solicitud=Scasol.get(params.id)
    def of1=Scaofi.findByDescrip(solicitud.ofi.toString())
    new File("D:/codigo.png").withOutputStream { out ->
    barcode4jService.render("code39Generator", solicitud.expro+"/"+solicitud.expano, out, "image/png")
}
    def papeleta=Papeleta.findAllByExproAndExpano(solicitud.expro,solicitud.expano)
    def usuario=User.get(params.param2)
    def mapa=[]
    byte[] bytes
    def fechaOriginal
    def fechaActual
    def anio
    def jefeOriginal
    def jefeActual
    jefeActual=enjuridico
    fechaOriginal=solicitud.fechasol  
    println(fechaOriginal.toString().split(" ")[0])
    fechaOriginal=fechaOriginal.toString().split(" ")[0]
    fechaOriginal=fechaOriginal.toString().split("-")        
    fechaOriginal=fechaOriginal[2]+" de "+meses2[Integer.parseInt(fechaOriginal[1])]+" de "+fechaOriginal[0]
    println("!!!!!!!!!!!!!!!!!!!!!!")
    println(solicitud.fecharesolucion)
    println("!!!!!!!!!!!!!!!!!!!!!!")
    if(solicitud.fecharesolucion.toString().equals() || solicitud.fecharesolucion.equals(null))
    {
        solicitud.fecharesolucion=fech1
        //jefeOriginal=EncargadoJuridico.find("From EncargadoJuridico where inicio<='"+solicitud.fechasol+"' and (fin>='"+solicitud.fechasol+"' or fin is null)") 
        //println("select * From EncargadoJuridico where inicio<='"+solicitud.fechasol+"' and (fin>='"+solicitud.fechasol+"' or fin is null)")
    //fechaOriginal=solicitud.fechasol
    fechaActual=hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0]   
    }else{
        //jefeOriginal=EncargadoJuridico.find("From EncargadoJuridico where inicio<='"+solicitud.fecharesolucion+"' and (fin>='"+solicitud.fecharesolucion+"' or fin is null)") 
        def anioO
        def anioA
        anioA=hoy2[0]
        anioO=solicitud.fecharesolucion.toString().split(" ")[0]
        anioO=anioO.split("-")
        anioO=anioO[0]
        if(anioO<anioA)
        {
           solicitud.fecharesolucion=fech1   
        }
        else
        {
          
        }
        
        fechaActual=solicitud.fecharesolucion  
        println(fechaActual.toString().split(" ")[0])
        fechaActual=solicitud.fecharesolucion.toString().split(" ")[0]
        fechaActual=fechaActual.split("-")        
        fechaActual=fechaActual[2]+" de "+meses2[Integer.parseInt(fechaActual[1])]+" de "+fechaActual[0]      
    }
    println("select * From EncargadoJuridico where inicio<='"+solicitud.fechasol+"' and (fin>='"+solicitud.fechasol+"' or fin is null)")
    
    jefeOriginal=EncargadoJuridico.find("From EncargadoJuridico where inicio<='"+solicitud.fechasol+"' and (fin>='"+solicitud.fechasol+"' or fin is null)") 
        
    println(jefeActual)
    println(jefeOriginal)
    //.replaceAll(jefeOriginal,jefeActual)
    //jefeActual    
    /*if(solicitud.fecharesolucion.toString().equals())
    {
        solicitud.fecharesolucion=fech1          
    }
    else
    {
        def anioO
        def anioA
        anioA=hoy2[0]
        anioO=solicitud.fecharesolucion.toString().split(" ")[0]
        anioO=anioO.split("-")
        anioO=anioO[0]
        if(anioO<anioA)
        {
           solicitud.fecharesolucion=fech1   
        }
        else
        {
            
        }
    }*/
    solicitud.save(flush: true)
        
        /*fechaActual=hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0]       
        println(fechaOriginal.toString().split(" ")[0])
        fechaOriginal=fechaOriginal.toString().split(" ")[0]
        fechaOriginal=fechaOriginal.toString().split("-")*/
        /*anio=fechaOriginal[0]
        fechaOriginal=fechaOriginal[2]+" de "+meses2[Integer.parseInt(fechaOriginal[1])]+" de "+fechaOriginal[0]
        if(anio.toString().equals(hoy2[0].toString()))
        {
            fechaActual=fechaOriginal
        }*/
        println("||||||||||||||||||||")
        println(fechaOriginal)
        println(fechaActual)
        println("||||||||||||||||||||")
    if(papeleta)
    {
        if(papeleta.size()>1)
        {
                int oficialia=0
                int archivo=0
                if(papeleta.donde[0].equals('O'))
                {
                    archivo=1
                }
                else{oficialia=1}
                println("Se encontro mas de uno")
                        def totalineasOficialia=0
                        def agegaespacioOfi=""
                def longitud=(papeleta.nota[archivo].toString().length())
                 totalineasOficialia = longitud.intdiv(120)
                 totalineasOficialia+=1
                 def espacioagregaOficialia=0                 
                 espacioagregaOficialia=22-totalineasOficialia
                 println(totalineasOficialia+" Aqui va el total de linas que tiene actualmente")
                 println(espacioagregaOficialia+" Aqui va el total de espacios que se van a agregar")
                 for(int y=0;y<espacioagregaOficialia;y++)
                 {
                    agegaespacioOfi+="\n\
        " 
                 }
                mapa << [
                expediente:"Expediente No.: "+solicitud.expro+"/"+solicitud.expano,
                nombreoficialia:"        "+"ARCHIVO CENTRAL",
                texto:papeleta.nota[archivo].replaceAll(fechaOriginal,fechaActual).replaceAll(jefeOriginal.toString(),jefeActual.toString())+"\n\n\n",
                boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+solicitud.cap+"/"+User.get(springSecurityService.currentUser.id)+agegaespacioOfi,
                encargadojuridico:"Comprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString(),
                
                ]
                
                                mapa << [
                expediente:"Expediente No.: "+solicitud.expro+"/"+solicitud.expano,
                nombreoficialia:"        "+solicitud.ofi.toString()+""+of1.nombre,
                texto:papeleta.nota[oficialia].replaceAll(fechaOriginal,fechaActual).replaceAll(jefeOriginal.toString(),jefeActual.toString())+"\n\n\n",
                boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+solicitud.cap+"/"+User.get(springSecurityService.currentUser.id)+agegaespacioOfi,
                encargadojuridico:"Comprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+solicitud.ofi.toString()+""+of1.nombre,
                
                ]
                 
            reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                        try{
            bytes = jasperService.generateReport(reportDef).toByteArray()

            //b+=a

            

                    }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Exp "'+solicitud.expro+"/"+solicitud.expano+'".pdf"')
	response.contentType='application/pdf'
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return false
            
        }
        else
        {
            println("Solo encontramos uno")
            println(papeleta.donde)
                                            mapa << [
                expediente:"Expediente No.: "+solicitud.expro+"/"+solicitud.expano+"        "+"ARCHIVO CENTRAL",
                expediente2:"Expediente No.: "+solicitud.expro+"/"+solicitud.expano+"        "+solicitud.ofi.toString()+""+of1.nombre,
                texto2:papeleta.nota[0].replaceAll(fechaOriginal,fechaActual).replaceAll(jefeOriginal.toString(),jefeActual.toString()).replaceAll("&Uuml;","Ü")+"\n\n\n",,
                texto:papeleta.nota[0]+"\n\n\n",
                boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+solicitud.cap+"/"+User.get(springSecurityService.currentUser.id)+agegaespacioOfi,
                encargadojuridico:"Comprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString(),
                oficialia:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+solicitud.ofi.toString()+""+of1.nombre    
            
                ]
                System.out.println("entra:::")
            if(papeleta.donde[0].toString().equals('A'))
            {
                def totalineasOficialia=0
                def agegaespacioOfi=""
                def longitud=(papeleta.nota[0].toString().length())
                 totalineasOficialia = longitud.intdiv(120)
                 totalineasOficialia+=1
                 def espacioagregaOficialia=0                 
                 espacioagregaOficialia=22-totalineasOficialia
                 println(totalineasOficialia+" Aqui va el total de linas que tiene actualmente")
                 println(espacioagregaOficialia+" Aqui va el total de espacios que se van a agregar")
                 for(int y=0;y<espacioagregaOficialia;y++)
                 {
                    agegaespacioOfi+="\n\
        " 
                 }
                def mapa2=[]
                                                            mapa2 << [
                expediente:"Expediente No.: "+solicitud.expro+"/"+solicitud.expano,
                nombreoficialia:"        "+"ARCHIVO CENTRAL",
                //expediente2:"Expediente No.: "+solicitud.expro+"/"+solicitud.expano+"        "+solicitud.ofi.toString()+""+of1.nombre,
                //texto2:papeleta.nota[0].replaceAll("&Uuml;","Ü")+"\n\n",
                texto:papeleta.nota[0].replaceAll(fechaOriginal,fechaActual).replaceAll(jefeOriginal.toString(),jefeActual.toString())+"\n\n\n",
                boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+solicitud.cap+"/"+User.get(springSecurityService.currentUser.id)+agegaespacioOfi,
                encargadojuridico:"Comprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString()
                //oficialia:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+solicitud.ofi.toString()+""+of1.nombre    
            
                ]
                                                            mapa2 << [
                expediente:"Expediente No.: "+solicitud.expro+"/"+solicitud.expano,
                //texto2:papeleta.nota[0].replaceAll("&Uuml;","Ü")+"\n\n",
                nombreoficialia:"        "+solicitud.ofi.toString()+""+of1.nombre,
                texto:papeleta.nota[0].replaceAll(fechaOriginal,fechaActual).replaceAll(jefeOriginal.toString(),jefeActual.toString())+"\n\n\n",
                boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+solicitud.cap+"/"+User.get(springSecurityService.currentUser.id)+agegaespacioOfi,
                encargadojuridico:"Comprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+solicitud.ofi.toString()+""+of1.nombre, 

                //oficialia:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+solicitud.ofi.toString()+""+of1.nombre    
            
                ]
            reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa2 )
              try{
                bytes = jasperService.generateReport(reportDef).toByteArray()                
   
                }catch(Exception ex){
                println(ex.printStackTrace())
                }
                response.addHeader("Content-Disposition", 'inline; filename="Papeleta"'+solicitud.expro+"/"+solicitud.expano+'".pdf"')
	
        //-response.contentType='application/pdf'
	response.outputStream << bytes
        //response.outputStream << render(file: new File("c:\\reportes\\repge.pdf"), fileName: "Resolucion.pdf",contentType: "application/pdf")
        response.outputStream.flush()
	//response.out << bytes        
       
        response.outputStream.close()
        return false
                    //reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
            /*try{
            bytes = jasperService.generateReport(reportDef).toByteArray()
            byte[]a=bytes
            reportDef = new JasperReportDef(name: "imprimirTodo/texto1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
            bytes = jasperService.generateReport(reportDef).toByteArray()
            byte [] b =bytes
            PDFMergerUtility mergePdf = new PDFMergerUtility() 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            mergePdf.addSource(new ByteArrayInputStream(a));
            mergePdf.addSource(new ByteArrayInputStream(b));
            mergePdf.setDestinationStream(byteArrayOutputStream);
            mergePdf.mergeDocuments();
            bytes= byteArrayOutputStream.toByteArray();
                    }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Exp "'+solicitud.expro+"/"+solicitud.expano+'".pdf"')
	response.contentType='application/pdf'
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return false*/
                
            }
        else if(papeleta.donde[0].toString().equals('C')){
            mapa=[]
                                                        mapa << [
                expediente:"Expediente No.: "+solicitud.expro+"/"+solicitud.expano,
                nombreoficialia:"ARCHIVO CENTRAL",
                texto:papeleta.nota[0].replaceAll(fechaOriginal,fechaActual).replaceAll(jefeOriginal.toString(),jefeActual.toString()).replaceAll("&Uuml;","Ü")+"\n\n\n",                
                boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+solicitud.cap+"/"+User.get(springSecurityService.currentUser.id)+agegaespacioOfi,
                encargadojuridico:"Comprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString()
                  
            
                ]
                reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                        try{
            bytes = jasperService.generateReport(reportDef).toByteArray()
            
                    }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Exp "'+solicitud.expro+"/"+solicitud.expano+'".pdf"')
	response.contentType='application/pdf'
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return false
                    
            }
        else{
            reportDef = new JasperReportDef(name: "imprimirTodo/texto2.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                        try{
            bytes = jasperService.generateReport(reportDef).toByteArray()
            
                    }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Exp "'+solicitud.expro+"/"+solicitud.expano+'".pdf"')
	response.contentType='application/pdf'
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return false
            
        }
             

            
        }
        
    }
    else{
        println("vamos a redireccionar a otro controlador")
        redirect(controller: "opcion", action: "imprimirSoloPapeleta", params:[id:params.id,usuario1:params.param2])
    }
    
   } 

    
    
    
def vali2(Long id, Long version) {
        
        def scasolInstance = Scasol.get(id)     
        def var = scasolInstance.id
        println(scasolInstance.estado)
        println(params.estado.toString()+" ¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨")
        
         if (!scasolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
            redirect(action: "edit2", id: scasolInstance.id)
            return
        }
        println(params.estado.id.toString())

        if (version != null) {
            if (scasolInstance.version > version) {
                scasolInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scasol.label', default: 'Scasol')] as Object[],
                          "Another user has updated this Scasol while you were editing")
                render(view: "edit", model: [scasolInstance: scasolInstance])
                return
            }
        }
        scasolInstance.properties = params
        scasolInstance.ip=request.getRemoteAddr().toString()
        scasolInstance.usuario=springSecurityService.currentUser.id
        if (!scasolInstance.save(flush: true)) {
            render(view: "edit", model: [scasolInstance: scasolInstance])
            return
        }
    
        if(params.estado.id.toString().equals("4"))
        {  
                println(scasolInstance.id+"Aqui va el id de la solicitud")
                redirect(action: "show", id: scasolInstance.id)
        }
        
        
       
    }
    
    def reporteAclaraciones()
    {
        
    }
    def totales()
    {

        def totales
        def totalesuso       
         totales=Scasol.findAll("From Scasol where fechasol>=? and fechasol<=?",[params.fechainicio,params.fechafinal+1])
        if(!totales.equals())
        {
           totalesuso=Scaerr.findAll("From Scaerr where expro between ? and ? and base like '%APELLIDO POR SIMPLE USO%' group by expro",[totales[0].id,totales[totales.size()-1].id])
         
        }         

        [totales:totales,totalesuso:totalesuso]
    }

    def delete(Long id) {
        def scasolInstance = Scasol.get(id)
        if (!scasolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
            redirect(action: "list")
            return
        }

        try {
            scasolInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scasol.label', default: 'Scasol'), id])
            redirect(action: "show", id: id)
        }
    }
}
