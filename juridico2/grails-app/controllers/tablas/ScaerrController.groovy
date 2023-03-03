package tablas

import org.springframework.dao.DataIntegrityViolationException
import catalogos.Scampo
import catalogos.Scadto
import tablas.Scaerr
import catalogos.Localidades
import catalogos.LocalidadesController
import catalogos.Fields
import catalogos.Nacionalidad
import tablas.Scasol
import catalogos.Tipoactas
import catalogos.Tipoerror
import catalogos.Letras
//import catalogos.Erroresperado
class ScaerrController { 
    def debeser=""
    def nombrec=""
    def nombre=""
    def apellidop=""
    def apellidom=""
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
                   

def buscarTipoErr(){
    println(params)
    def tipo=Fields.get(params.id)
    println(tipo.tipo)
       switch(tipo.tipo)
            {
                case 'X':                    
                    render(template:"igual",model:[debeser:params.contiene])
                    break
                case 'F':
                    println("Vamos a cambiar el formulario")
                    render(template:"fechita",model:[params:params])
                    break
                case 'C':
                    render(template:"nombre",model:[params:params])
                    break
                case 'L':
                    def lugar=params.contiene.split(", ")
                    if(lugar.length>3)
                    {
                        
                     println(params.contiene)
                    println(params.contiene.split(", ")[2])
                    def distrito=Scadto.findByDescc(params.contiene.split(", ")[2].replaceAll(", ",""))
                    println(distrito)
                    println(params.contiene.split(", ")[1].replaceAll(", ",""))
                    def mpo=Scampo.findByDescrip(params.contiene.split(", ")[1].replaceAll(", ",""))
                    def listLoc=Localidades.findAllByMpo(mpo)
                    println(mpo)
                    println(listLoc)
               
                    render(template:"lugar2",model:[distrito:distrito,mpo:mpo,listLoc:listLoc])
                    }
                    else
                    {
                     render(template:"lugar",model:[params:params])   
                
                    }
                   
                    break
                case 'N':
                    render(template:"nacionalidad",model:[params:params])
                    break
            }
}
   
     def verTipo(){         
        println("/*--*//*--*/")
        println(params.id)
        println("/*--*//*--*/")
        //def erp=params.id
        def control=Fields.get(params.id)        
        println control.tipo
       
        try
        {
            if(control.nombre.equals("EL REGIMEN DE CONTRATO DE MATRIMONIO"))
            {
                debeser="SOCIEDAD CONYUGAL LEGAL"
                render(template:"formMatrimonio",model:[params:params,debeser:debeser])
                return
                
            }
            if(control.nombre.contains("EL ESTADO BIOLOGICO"))
            {
                debeser="VIVO"
                render(template:"formMatrimonio",model:[params:params,debeser:debeser])
                return
            }
            switch(control.tipo)
            {
                case 'X':
                    render(template:"xx",model:[params:params])
                    break
                case 'F':
                    render(template:"fecha",model:[params:params])
                    break
                case 'C':
                    render(template:"nombre",model:[params:params])
                    break
                case 'L':
                    render(template:"lugar",model:[params:params])
                    break
                case 'N':
                    render(template:"nacionalidad",model:[params:params])
                    break
            }
            
          /*  if((control.tipo).equals('X') || (control.tipo).equals('U')){
              render(template:"xx",model:[params:params])
        }        
         if((control.tipo).equals('F')){
            
            render(template:"fecha",model:[params:params])
        }  
         if((control.tipo).equals('C')){
           
            render(template:"nombre",model:[params:params])
        }     
         if((control.tipo).equals('L')){
            
              render(template:"lugar",model:[params:params])
        }     
           if((control.tipo).equals('N')){
            
            render(template:"nacionalidad",model:[params:params])
        }       */
        }catch(Exception e)
        {
            println("PAso algo")
            e.printStackTrace()
            
        }
         
    }
    
    def getNombre()
    {
        println(params.nombre)
        println(params.appat)
        println(params.apmat)
        debeser=""
        try
        {
            debeser+=params.nombre+" "
        }catch(Exception e){}
        try
        {
            debeser+=params.appat+" "
        }catch(Exception e){}
        try
        {
            debeser+=params.apmat
        }catch(Exception e){}
        debeser=debeser.toUpperCase()
        //debeser=params.nombre+" "+params.appat+" "+params.apmat.toUpperCase()
        //render (template:"")
        render(template:"retornar",model:[debeser:debeser])
    }
    
        def getCont(){
        def maximo=Scaerr.executeQuery("select max(id) from Scaerr")        
        def nott=Integer.parseInt(maximo[0].toString())
        def val= nott +1
        print val
   
      render(template:"continuo",model:[val:val])           
    }
    def getEnvierro(){
        def tipoerrorInstance = Tipoerror.get(params.id) 
      render(template:"vari",model:[tipoerrorInstance:tipoerrorInstance])
 
    }
        
  def tipoActa(){
    println "adentro"
    println(params.id+" Aqui va el id")
    println(params.err +" Aqui va err")
    println(params.err.toString())
    def usos=Tipoerror.get(params.err)
    println(usos)
    println()
        def errorh
        def eroresList
        if(usos.toString().equals("ACLARACION POR USO"))
        {
            println("Es aclaracion por uso")
            eroresList=Fields.findAll("From Fields where tipo='U'")
            errorh =eroresList 
            println(errorh)
            render(template:"errorFields", model: [errorh:errorh]) 
            return
        }
          def scasolInstance = Scasol.get(params.id)
          println scasolInstance
//          println(scasolInstance.sexintere)
//          println(scasolInstance.typact)
          eroresList=Fields.findAllByActaAndSexo(scasolInstance.typact,scasolInstance.sexintere)
          
        errorh =eroresList 
        
          
           
            render(template:"errorFields", model: [errorh:errorh]) 
        
               
    }
    
    
def verFecha2(){
        
         String [] meses=["","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"]
        
        def fechaletra=""
        String [] fecha_p=params.fecha.split("-")
        Letras letras = new Letras(Integer.valueOf(fecha_p[2]))
        def fe=params.fecha
        String [] fech=fe.split("-")
        fechaletra+=letras.convertirLetras(Integer.valueOf(fecha_p[2]))
        fechaletra+=" DE "+meses[Integer.valueOf(fecha_p[1])]+" DE "
        Letras letras2 = new Letras(Integer.valueOf(fecha_p[0]))
        fechaletra+=letras2.convertirLetras(Integer.valueOf(fecha_p[0]))
        println(fechaletra)
        debeser=''+fech[2]+"/"+fech[1]+"/"+fech[0]+" "+fechaletra.toUpperCase()
        debeser=debeser.replaceAll("  "," ")
        render(template:"retornar2",model:[debeser:debeser])
        

      
    }  
 
     def verFecha(){
        
         String [] meses=["","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"]
        
        def fechaletra=""
        String [] fecha_p=params.fecha.split("-")
        Letras letras = new Letras(Integer.valueOf(fecha_p[2]))
        def fe=params.fecha
        String [] fech=fe.split("-")
        fechaletra+=letras.convertirLetras(Integer.valueOf(fecha_p[2]))
        fechaletra+=" DE "+meses[Integer.valueOf(fecha_p[1])]+" DE "
        Letras letras2 = new Letras(Integer.valueOf(fecha_p[0]))
        fechaletra+=letras2.convertirLetras(Integer.valueOf(fecha_p[0]))
        println(fechaletra)
        debeser=''+fech[2]+"/"+fech[1]+"/"+fech[0]+" "+fechaletra.toUpperCase()
        debeser=debeser.replaceAll("  "," ")
        render(template:"retornar",model:[debeser:debeser])
        
       /* def anio=fech[0]
        def dai=fech[2]
        def f=[] 
        def d=[]
        for(int i=0;i<anio.length();i++)
        {
           
            f[i]=Integer.parseInt(anio[i])
           
            
        }
        
        
         for(int g=0;g<fech[2].length();g++)
        {
            
            d[g]=Integer.parseInt(dai[g])
           
            
        }
        
      
        def mm=Integer.parseInt(fech[1])
        def dd=Integer.parseInt(fech[2])
     
        String [] unidades=["","UNO","DOS","TRES","CUATRO","CINCO","SEIS","SIETE","OCHO","NUEVE","DIEZ","ONCE","DOCE","TRECE","CATORCE","QUINCE","DIECISEIS","DIECISIETE","DIECIOCHO","DIECINUEVE","VEINTE"]
        String [] decenas=["","VEINTI","TREINTA","CUARENTA","CINCUENTA","SESENTA","SETENTA","OCHENTA","NOVENTA"]
        String [] centenas=["","CIENTO","DOSCIENTOS","TRESCIENTOS","CUATROCIENTOS","QUINIENTOS","SEISCIENTOS","SETECIENTOS","OCHOCIENTOS","NOVECIENTOS"]
        String [] millares=["MIL","DOS MIL","TRES MIL"]
        def ultimo=f[2]+""+f[3]
       
        def ul=Integer.parseInt(ultimo)
        println ul+" Aqui va ul"
        
        
        
        if(ul>0 && ul<=20){
            
        
            if(dd>0 && dd<=20){
           fechaletra+=unidades[dd]+" DE "+meses[mm]+" DE "+millares[f[0]-1]+" "+centenas[f[1]]+" "+unidades[ul]
           }
           else{
               if(dd>30)
               {
                    fechaletra+=decenas[d[0]-1]+" Y UNO"+ " DE "+meses[mm]+" DE "+millares[f[0]-1]+" "+centenas[f[1]]+" "+unidades[ul]
                
               }
               else{
                fechaletra+=decenas[d[0]-1]+unidades[d[1]]+ " DE "+meses[mm]+" DE "+millares[f[0]-1]+" "+centenas[f[1]]+" "+unidades[ul]
                }
           }
         
            println fechaletra
            debeser=''+fech[2]+"/"+fech[1]+"/"+fech[0]+" "+fechaletra
             render(template:"retornar",model:[debeser:debeser])
            
        }
        
        else{
            
            if(ul==0){
                println("aqui no es")
                if(dd>0 && dd<=20){
                     fechaletra+=unidades[dd]+" DE "+meses[mm]+" DE "+millares[f[0]-1]
                }
                else{
                    fechaletra+=decenas[d[0]-1]+unidades[d[1]]+ " DE "+meses[mm]+" DE "+millares[f[0]-1]+centenas[f[1]]
                   
                
                }
                
           
        }
            else{
            
            
               if(dd>0 && dd<=20){
                   println("aqui si es")
                   println ul
                   if(ul>20 && ul<30)
                   {println("Modifica todo aqui")
                   fechaletra+=unidades[dd]+" DE "+meses[mm]+" DE "+millares[f[0]-1]+" "+centenas[f[1]]+" "+decenas[f[2]-1]+""+unidades[f[3]]
                
                   }
                   
                    
                   if(ul%10==0){
                       fechaletra+=unidades[dd]+" DE "+meses[mm]+" DE "+millares[f[0]-1]+" "+centenas[f[1]]+" "+decenas[f[2]-1]+""+unidades[f[3]]
                
                   }
                   else{
                   
                fechaletra+=unidades[dd]+" DE "+meses[mm]+" DE "+millares[f[0]-1]+" "+centenas[f[1]]+" "+decenas[f[2]-1]+" Y "+unidades[f[3]]
                }
                }
                
           else{
               if(dd>30)
               {
                   if(ul%10==0)
                   {
                       fechaletra+=decenas[d[0]-1]+" Y UNO"+ " DE "+meses[mm]+" DE "+millares[f[0]-1]+" "+centenas[f[1]]+" "+decenas[f[2]-1]+" "+unidades[f[3]]
                   }
                   else{
                       if(ul>20 && ul<30)
                       {
                          fechaletra+=decenas[d[0]-1]+" Y UNO"+ " DE "+meses[mm]+" DE "+millares[f[0]-1]+" "+centenas[f[1]]+" "+decenas[f[2]-1]+""+unidades[f[3]]
                     
                       }
                       else{
                       fechaletra+=decenas[d[0]-1]+" Y UNO"+ " DE "+meses[mm]+" DE "+millares[f[0]-1]+" "+centenas[f[1]]+" "+decenas[f[2]-1]+" Y "+unidades[f[3]]
                   }
                        }
                    
                
               }
               else{
                   if(ul%10==0)
                   {
                    fechaletra+=decenas[d[0]-1]+unidades[d[1]]+ " DE "+meses[mm]+" DE "+millares[f[0]-1]+" "+centenas[f[1]]+" "+decenas[f[2]-1]+""+unidades[f[3]]
                   }
                else
                {
                    
                        if(dd>20 && dd<30)
                        {
                         fechaletra+=decenas[d[0]-1]+unidades[d[1]]+ " DE "+meses[mm]+" DE "+millares[f[0]-1]+" "+centenas[f[1]]+" "+decenas[f[2]-1]+""+unidades[f[3]]
                    
                        }
                         else
                         {   
                           fechaletra+=decenas[d[0]-1]+unidades[d[1]]+ " DE "+meses[mm]+" DE "+millares[f[0]-1]+" "+centenas[f[1]]+" "+decenas[f[2]-1]+" Y "+unidades[f[3]]
                         }
                }
                    }
           }
            }
            println fechaletra
            debeser=''+fech[2]+"/"+fech[1]+"/"+fech[0]+" "+fechaletra
             render(template:"retornar",model:[debeser:debeser])
            
      
        }*/
        
        
      
    }  
 
    def nacionalidad(){
        
        
        def nc=Nacionalidad.get(params.id)
     //   println nc
        debeser=nc.nombre
        render(template:"retornar",model:[debeser:debeser])
    }
    
    
    
    def muestra(){
       
      println(params)
       def localidad=Localidades.get(params.id)
       def municipio=Scampo.get(params.municipio)
       def distrito=Scadto.get(params.distrito)
        //def vert=Localidades.get(localidad)
       if(!localidad.toString().equals(municipio.toString()))
       {
           
           debeser=localidad.toString()+", "+municipio.toString()+", "+distrito.toString()+", OAXACA" 
       }
       else {
           
            debeser=municipio.toString()+", "+distrito.toString()+", OAXACA"
       }
      /* def locc=''+vert+''
      
        def muni=''+vert.mpo+''
        
        def vers=Scampo.findByDescrip(muni)
        
       // println vers.distrito
        def dto=''+vers.distrito+''
        
        if(locc.equals(muni)){
            debeser=''+vert+', '+dto+', OAXACA'
             render(template:"retornar",model:[debeser:debeser])
        }
        else{
         debeser=''+vert+', '+muni+', '+dto+', OAXACA'
        render(template:"retornar",model:[debeser:debeser])}*/
    render(template:"retornar",model:[debeser:debeser])
    }
    
    
    
    
    
    
        def muestra2(){
       
      println(params)
       def localidad=Localidades.get(params.id)
       def municipio=Scampo.get(params.municipio)
       def distrito=Scadto.get(params.distrito)
        //def vert=Localidades.get(localidad)
       if(!localidad.toString().equals(municipio.toString()))
       {
           
           debeser=localidad.toString()+", "+municipio.toString()+", "+distrito.toString()+", OAXACA" 
       }
       else {
           
            debeser=municipio.toString()+", "+distrito.toString()+", OAXACA"
       }
      /* def locc=''+vert+''
      
        def muni=''+vert.mpo+''
        
        def vers=Scampo.findByDescrip(muni)
        
       // println vers.distrito
        def dto=''+vers.distrito+''
        
        if(locc.equals(muni)){
            debeser=''+vert+', '+dto+', OAXACA'
             render(template:"retornar",model:[debeser:debeser])
        }
        else{
         debeser=''+vert+', '+muni+', '+dto+', OAXACA'
        render(template:"retornar",model:[debeser:debeser])}*/
    render(template:"retornar",model:[debeser:debeser])
    }  
    
    
    def verNombre(){
      //  println params.app
       // println params.apm
    }
    
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [scaerrInstanceList: Scaerr.list(params), scaerrInstanceTotal: Scaerr.count()]
    }

    def create() { 
        [scaerrInstance: new Scaerr(params)]
    }
    def create2() {
        [scaerrInstance: new Scaerr(params)]
    }
    def create21() {
        [scaerrInstance: new Scaerr(params)]
    }
    def create22() {
        [scaerrInstance: new Scaerr(params)]
    }
    def create23() {
        [scaerrInstance: new Scaerr(params)]
    }
        def create235() {
        [scaerrInstance: new Scaerr(params)]
    }
    def save() {
        println(params.base)
        println("-------------------")
        def scaerrInstance = new Scaerr(params)
        scaerrInstance.ip=request.getRemoteAddr().toString()
        scaerrInstance.usuario=springSecurityService.currentUser.id
        if (!scaerrInstance.save(flush: true)) {
            render(view: "create", model: [scaerrInstance: scaerrInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'scaerr.label', default: 'Scaerr'), scaerrInstance.id])
        redirect(controller: "opcion",action:"create")
    }
    
    
    def testarOficio()
    {
        println("Vamos a buscar todas las bases para testar de oficio")
        println(params.campo.toString())
        println(params.err.toString())
        def error=Tipoerror.get(params.campo)
        println(error.toString())
        String lista
        def scaerrInstance
        if(error.toString().contains("ANOTACION"))
        {
          render(template:"formTestar",model: [scaerrInstance: scaerrInstance]) 
          return
        }
        else{
          render(template:"formTestar2",model: [scaerrInstance: scaerrInstance])
          return
        }
        /*if(error.toString().contains("ANOTACION"))
        {
            //class="form-control input-sm" required=""
            //onchange="buscaBase()"
            lista=""
            lista+="<select name='debeser'  onchange='buscaBase()' class='form-control input-sm' id='debeser' class='form-control- input-sm'>"
            lista+="<option value=''></option>"
            lista+="<option value='POR CARECER DE LOS ELEMENTOS ESCENCIALES PARA TENER VALIDEZ PLENA'>POR CARECER DE LOS ELEMENTOS ESCENCIALES PARA TENER VALIDEZ PLENA</option>"
            lista+="<option value='POR SER IMPROCEDENTE'>POR SER IMPROCEDENTE</option>"
            lista+="<option value='OTRO'>OTRO</option>"
            render lista
            
        }
        else{
            lista=""
            lista+="<select name='debeser' onchange='buscaBase()' id='debeser' class='form-control input-sm'>"
            lista+="<option value=''></option>"
            lista+="<option value='EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PROGENITOR Y EL REGISTRADO'>"
            lista+="EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PROGENITOR Y EL REGISTRADO</option>"
            lista+="<option value='EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA PROGENITORA Y EL REGISTRADO'>"
            lista+="EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA PROGENITORA Y EL REGISTRADO</option>"
            lista+="<option value='EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PROGENITOR Y LA REGISTRADA'>"
            lista+="EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PROGENITOR Y LA REGISTRADA</option>"
            lista+="<option value='EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA PROGENITORA Y LA REGISTRADA'>"
            lista+="EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA PROGENITORA Y LA REGISTRADA</option>"
            
            lista+=""
            render lista
        }*/
        
    }

    def show(Long id) {
        def scaerrInstance = Scaerr.get(id)
        if (!scaerrInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaerr.label', default: 'Scaerr'), id])
            redirect(action: "list")
            return
        }

        [scaerrInstance: scaerrInstance]
    }

    def edit(Long id) {
        def scaerrInstance = Scaerr.get(id)
        println(params)
        println(scaerrInstance)
        println(scaerrInstance.tcorrect)
        println(scaerrInstance.expro)
        println(Scasol.get(scaerrInstance.expro))
        def scasolInstance=Scasol.get(scaerrInstance.expro)
        if (!scaerrInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaerr.label', default: 'Scaerr'), id])
            redirect(action: "list")
            return
        }

        [scaerrInstance: scaerrInstance,scasolInstance:scasolInstance]
    }
    def edit2(Long id) {
        def scaerrInstance = Scaerr.get(id)
        if (!scaerrInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaerr.label', default: 'Scaerr'), id])
            redirect(action: "list")
            return
        }

        [scaerrInstance: scaerrInstance]
    }
    def update(Long id, Long version) {
        println(params.tcorrect.id)
        //println(tecorrect)
        def scaerrInstance = Scaerr.get(id)
        
        if (!scaerrInstance) {
            flash.message = scaerrInstance.expro
            redirect(controller: "opcion", action:"opciones2" )
            return
        }

        if (version != null) {
            if (scaerrInstance.version > version) {
                scaerrInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scaerr.label', default: 'Scaerr')] as Object[],
                          "Another user has updated this Scaerr while you were editing")
                render(view: "edit", model: [scaerrInstance: scaerrInstance])
                return
            }
        }

        scaerrInstance.properties = params
        println(Tipoerror.get(params.tcorrect.id))
        scaerrInstance.tcorrect=Tipoerror.get(params.tcorrect.id)
        scaerrInstance.ip=request.getRemoteAddr().toString()
        scaerrInstance.usuario=springSecurityService.currentUser.id
        if (!scaerrInstance.save(flush: true)) {
            render(view: "edit", model: [scaerrInstance: scaerrInstance])
            return
        }

            flash.message = scaerrInstance.expro
            redirect(controller: "opcion", action:"opciones2",id:scaerrInstance.expro )
    }
    def update2(Long id, Long version) {
        def scaerrInstance = Scaerr.get(id)
        
        if (!scaerrInstance) {
             flash.message = scaerrInstance.expro
              redirect(controller: "opcion", action:"opciones2")
            return
        }

        if (version != null) {
            if (scaerrInstance.version > version) {
                scaerrInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scaerr.label', default: 'Scaerr')] as Object[],
                          "Another user has updated this Scaerr while you were editing")
                render(view: "edit", model: [scaerrInstance: scaerrInstance])
                return
            }
        }

        scaerrInstance.properties = params
        scaerrInstance.ip=request.getRemoteAddr().toString()
        scaerrInstance.usuario=springSecurityService.currentUser.id
        if (!scaerrInstance.save(flush: true)) {
            render(view: "edit", model: [scaerrInstance: scaerrInstance])
            return
        }

         flash.message = scaerrInstance.expro
          redirect(controller: "opcion", action:"opciones2")
    }
    def delete(Long id) {
        def scaerrInstance = Scaerr.get(id)
        if (!scaerrInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scaerr.label', default: 'Scaerr'), id])
            redirect(action: "list")
            return
        }

        try {
            scaerrInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scaerr.label', default: 'Scaerr'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scaerr.label', default: 'Scaerr'), id])
            redirect(action: "show", id: id)
        }
    }
}
