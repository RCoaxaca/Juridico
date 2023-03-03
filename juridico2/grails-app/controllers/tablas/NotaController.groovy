package tablas

import org.springframework.dao.DataIntegrityViolationException
import tablas.Scasol
import com.testapp.User
import catalogos.EncargadoJuridico
import catalogos.EncargadoArchivo
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.apache.commons.io.FileUtils
import org.apache.pdfbox.util.PDFMergerUtility
import org.krysalis.barcode4j.impl.code39.Code39Bean
import catalogos.Papeleta
class NotaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def springSecurityService
    def jasperService
    def barcode4jService
    def reportDef
    def enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")  
    String [] meses2=["","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"]
    
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

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [notaInstanceList: Nota.list(params), notaInstanceTotal: Nota.count()]
    }

    def create() {
        [notaInstance: new Nota(params)]
    }

    def save() {
        def notaInstance = new Nota(params)
        if (!notaInstance.save(flush: true)) {
            render(view: "create", model: [notaInstance: notaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'nota.label', default: 'Nota'), notaInstance.id])
        redirect(action: "show", id: notaInstance.id)
    }

    def show(Long id) {
        def notaInstance = Nota.get(id)
        if (!notaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "list")
            return
        }

        [notaInstance: notaInstance]
    }

    def edit(Long id) {
        def notaInstance = Nota.get(id)
        if (!notaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "list")
            return
        }

        [notaInstance: notaInstance]
    }

    def update(Long id, Long version) {
        def notaInstance = Nota.get(id)
        if (!notaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (notaInstance.version > version) {
                notaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'nota.label', default: 'Nota')] as Object[],
                          "Another user has updated this Nota while you were editing")
                render(view: "edit", model: [notaInstance: notaInstance])
                return
            }
        }

        notaInstance.properties = params

        if (!notaInstance.save(flush: true)) {
            render(view: "edit", model: [notaInstance: notaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'nota.label', default: 'Nota'), notaInstance.id])
        redirect(action: "show", id: notaInstance.id)
    }
    
    
    def quitarEspacios()
    {
        println(params)
        PDFMergerUtility mergePdf = new PDFMergerUtility() 
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()     
        def scasolInstance = Scasol.get(params.id)
        //return
        def nota=Nota.findAllByExpanoAndExpro(scasolInstance.expano,scasolInstance.expro)
                            new File("D:/codigo.png").withOutputStream { out ->
    barcode4jService.render("code39Generator", scasolInstance.expro+"/"+scasolInstance.expano, out, "image/png")
}
        println(nota)
        def notanota
        def mapa=[]
        for(int i=0; i<nota.size();i++)
        {
            println(nota.nota[i].replaceAll("<p style=\"text-align: justify; line-height: 1em; font-size: 8pt\">&nbsp;</p>",""))
            notanota=nota.nota[i]
            //notanota=notanota
         notanota=notanota.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'></p>","")   
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
        notanota=notanota.replaceAll("&deg;","°")
        notanota=notanota.replaceAll("&quot;","\"")
        def espacio="                                                                                                                                                      "
        int longitud=(espacio.toString().length()-((enjuridico.titulo+" "+enjuridico).toString().length()))/2
        espacio=espacio.substring(0,longitud)
        notanota=notanota.replaceAll(""+enjuridico.titulo+" "+enjuridico,espacio+""+enjuridico.titulo+" "+enjuridico)
       notanota=notanota.replaceAll("\\*","\n\
")
        notanota=notanota.replaceAll("\n\
\n\
","\n\
")
        notanota=notanota.replaceAll("R E S U E L V E ","                                                                 R  E  S  U  E  L  V  E")
        //println(textoInstance.nota.substring(0,(textoInstance.nota.indexOf("</p>")+4))+" AQui viene algo para ver si funciona")
        notanota=notanota.replaceAll("C O N S I D E R A N D O ","                                                                   C O N S I D E R A N D O ")
        println(notanota)
        nota.nota[i]=notanota

            switch(nota.idn[i].toString())
            {
                case 'A':
                    println("Se envia para ambos")
 
             mapa <<             [  exp:"Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano ,
            fecha:nota.nota[i].substring(63,(nota.nota[i].indexOf("</p>"))),
            copia1:notanota+"\n\
"+User.get(springSecurityService.currentUser.id).toString()+"\n\n\n"
                ]
                                try{
            
            
            reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                  
         mapa = jasperService.generateReport(reportDef).toByteArray()
         byte [] a =mapa
            
            mergePdf.addSource(new ByteArrayInputStream(a))
            mergePdf.addSource(new ByteArrayInputStream(a))
            mergePdf.addSource(new ByteArrayInputStream(a))            
            mergePdf.setDestinationStream(byteArrayOutputStream)
            mergePdf.mergeDocuments();
            mapa= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        }
        response.addHeader("Content-Disposition", 'inline; filename="exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
        response.contentType='application/pdf'
    	response.outputStream << mapa
        response.outputStream.close()
        response.outputStream.flush()
        return false
                break
                case 'O':
                    println("Pasa por la oficialia")
                    mapa=[]

           mapa <<     [            exp:"Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano ,
            fecha:nota.nota[i].substring(63,(nota.nota[i].indexOf("</p>"))),
            copia1:notanota+"\n\
"+User.get(springSecurityService.currentUser.id).toString()+"\n"+"\n"+"\n"
                ]
                
                                try{
            
            
            reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                  
         mapa = jasperService.generateReport(reportDef).toByteArray()
         byte [] a =mapa
            
            mergePdf.addSource(new ByteArrayInputStream(a))
            mergePdf.addSource(new ByteArrayInputStream(a))                       
            //mergePdf.setDestinationStream(byteArrayOutputStream)
            //mergePdf.mergeDocuments();
            //mapa= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        }
        //response.addHeader("Content-Disposition", 'inline; filename="exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
        //response.contentType='application/pdf'
    	//response.outputStream << mapa
        //response.outputStream.close()
        //response.outputStream.flush()
        //return false
                break
                case 'C':  
                    println("Pasa por el archivo")
                    mapa=[]
 
           mapa <<     [            exp:"Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano ,
            fecha:nota.nota[i].substring(63,(nota.nota[i].indexOf("</p>"))),
            copia1:notanota+"\n\
"+User.get(springSecurityService.currentUser.id).toString()+"\n"+"\n"+"\n"
                ]
                
                                try{
            
            
            reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                  
         mapa = jasperService.generateReport(reportDef).toByteArray()
         byte [] a =mapa
            //PDFMergerUtility mergePdf = new PDFMergerUtility() 
            //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()           
            mergePdf.addSource(new ByteArrayInputStream(a))
            mergePdf.addSource(new ByteArrayInputStream(a))            
            //mergePdf.setDestinationStream(byteArrayOutputStream)
            //mergePdf.mergeDocuments();
            //mapa= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        }
        //response.addHeader("Content-Disposition", 'inline; filename="exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
        //response.contentType='application/pdf'
    	//response.outputStream << mapa
        //response.outputStream.close()
        //response.outputStream.flush()
        //return false
                break
            }
                /*mapa << [           
            
            exp:"Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano ,
            fecha:nota.nota[i].substring(63,(nota.nota[i].indexOf("</p>"))),
            copia1:notanota+"\n\
"+User.get(springSecurityService.currentUser.id).toString()
                ] */   
        }
        //render(notanota)
                /*try{
            
            
            reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                  
         mapa = jasperService.generateReport(reportDef).toByteArray()
         byte [] a =mapa
            PDFMergerUtility mergePdf = new PDFMergerUtility() 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
            mergePdf.addSource(new ByteArrayInputStream(a))
            mergePdf.addSource(new ByteArrayInputStream(a))
            mergePdf.addSource(new ByteArrayInputStream(a))            
            mergePdf.setDestinationStream(byteArrayOutputStream)
            mergePdf.mergeDocuments();
            mapa= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        }
        response.addHeader("Content-Disposition", 'inline; filename="exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
        response.contentType='application/pdf'
    	response.outputStream << mapa
        response.outputStream.close()
        response.outputStream.flush()
        return false*/
                        
        mergePdf.setDestinationStream(byteArrayOutputStream)
        mergePdf.mergeDocuments();
        mapa= byteArrayOutputStream.toByteArray();
        response.addHeader("Content-Disposition", 'inline; filename="exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
        response.contentType='application/pdf'
    	response.outputStream << mapa
        response.outputStream.close()
        response.outputStream.flush()
        return false

    }

    def delete(Long id) {
        def notaInstance = Nota.get(id)
        if (!notaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "list")
            return
        }

        try {
            notaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'nota.label', default: 'Nota'), id])
            redirect(action: "show", id: id)
        }
    }
    
    
    
    def busca(){
        def user = springSecurityService.currentUser
        println(user.id)
        println(params)
        Date fecha = new Date()
        fecha.setHours(00)
        fecha.setMinutes(00)
        fecha.toTimestamp()
        println(fecha.toTimestamp())
        def scaactInstanceList
        if(user.username.toString().equals("admin"))
        {
           scaactInstanceList=Scasol.findAll("From Scasol where fechasol>=? ",[fecha]) 
        }
        else
        {
          scaactInstanceList=Scasol.findAll("From Scasol where fechasol>=? and cap=?",[fecha,User.get(user.id)])  
        }
        
        //scaactInstanceList=Scasol.findAllByFechasolGreaterThanEquals(fecha.toTimestamp())
        
        println(scaactInstanceList)
        [scaactInstanceList: scaactInstanceList]
    }
    
    
    
    def muestraDocumentos(){
        def user = springSecurityService.currentUser
        def enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")  
        def enarchivo=EncargadoArchivo.find("from EncargadoArchivo where activo=true")
        println("Vamos a mandar todos los expedientes que tenmos")
        println(params.progresivos)
        def fecha
        def resolucion
        fecha=new Date()
        fecha.toTimestamp()
        println(fecha.year+1900)
        println("***********************")
        println(fecha.year+1900)
        println(fecha.month)
        println(fecha.day)
        println(fecha.getDay())
        println(fecha.getMonth())
        println("***********************")
        def mapa=[]
        byte[] bytes
        byte [] a
        PDFMergerUtility mergePdf = new PDFMergerUtility() 
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if(params.progresivos)
        {
            for(int i=0;i<params.progresivos.size();i++)
            {
                               new File("D:/codigo.png").withOutputStream { out ->
    barcode4jService.render("code39Generator", params.progresivos[i]+"/"+(fecha.year+1900), out, "image/png")
} 
                println("------------------")
                println(params.progresivos[i])
                println("------------------")
                resolucion=Nota.findAllByExproAndExpano(Integer.valueOf(params.progresivos[i]),Integer.valueOf((fecha.year+1900)))
                //println(Nota.findByExproAndExpano(Integer.valueOf(params.progresivos[i]),Integer.valueOf((fecha.year+1900))))
                
                for(int o=0; o<resolucion.size();o++)
                {
                //println(resolucion.nota[o])
                def notanota=resolucion.nota[o]
                def fecharesolucion=notanota.substring(0,(notanota.indexOf("</p>")+4))
                fecharesolucion=cleanHtml(fecharesolucion,'simpleText')
                fecharesolucion=fecharesolucion.replaceAll("&Aacute;","Á")
                fecharesolucion=fecharesolucion.replaceAll("&Uacute;","Ú")
                fecharesolucion=fecharesolucion.replaceAll("&aacute;","á")
                fecharesolucion=fecharesolucion.replaceAll("&uacute;","ú")
                
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
                notanota=notanota.replaceAll("&deg;","°")
                notanota=notanota.replaceAll("&quot;","\"")
        notanota=notanota.replaceAll("\\*","\n\
")
                notanota=notanota.replaceAll("R E S U E L V E ","                                                                 R  E  S  U  E  L  V  E")
        //println(textoInstance.nota.substring(0,(textoInstance.nota.indexOf("</p>")+4))+" AQui viene algo para ver si funciona")
                notanota=notanota.replaceAll("C O N S I D E R A N D O ","                                                                   C O N S I D E R A N D O ")
                //println(notanota)
        def espacio="                                                                                                                                                      "
        //def  enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")
        int longitud=(espacio.toString().length()-((enjuridico.titulo+" "+enjuridico).toString().length()))/2
         espacio=espacio.substring(0,longitud)
         //println(notanota.replaceAll(""+enjuridico.titulo+" "+enjuridico,"AQUI VA EL NOMBRE DEL JEFE"))
         notanota=notanota.replaceAll(""+enjuridico.titulo+" "+enjuridico,espacio+""+enjuridico.titulo+" "+enjuridico)
        println(resolucion.expro[o]+" ////////////////////////////////////////")
                mapa << [
           
            //resolucion:reso,
            exp:"Expediente: "+resolucion.expro[o]+"/"+(fecha.year+1900).toString() ,
            /*pa2:notanota.replaceAll("\\*","\n\
")*/
            fecha:fecharesolucion,
            copia1:notanota+"\n\
"+user.username+"\n\n\n"
                ]
                def reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                
                    try{
                        bytes = jasperService.generateReport(reportDef).toByteArray()
                        a =bytes
                        mapa=[]    
                    }catch(Exception e)
                    {
                        e.printStackTrace()
                    }
                    println(resolucion.idn+" *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-")
                switch(resolucion.idn[o])
                {
                 case "A":
                    println("Se envia a los dos tiene que crear tres tantos")
                    mergePdf.addSource(new ByteArrayInputStream(a));
                    mergePdf.addSource(new ByteArrayInputStream(a));
                    mergePdf.addSource(new ByteArrayInputStream(a));
                    break
                case "C":
                    println("Se envia al archivo tienes que crear dos tantos")
                    mergePdf.addSource(new ByteArrayInputStream(a));
                    mergePdf.addSource(new ByteArrayInputStream(a));
                    break
                case "O":
                    println("Se envia a la oficialia tienes que crear dos tantos")
                    mergePdf.addSource(new ByteArrayInputStream(a));
                    mergePdf.addSource(new ByteArrayInputStream(a));
                    break
                } 
                    
                }
                

                   
            }
            try{
               mergePdf.setDestinationStream(byteArrayOutputStream);
            mergePdf.mergeDocuments();
            bytes= byteArrayOutputStream.toByteArray(); 
            }catch(Exception i)
            {
                i.printStackTrace()
            }
            
            
            //bytes= byteArrayOutputStream.toByteArray();
        }
        
        response.addHeader("Content-Disposition", 'attachment; filename="Considerandos.pdf"')
	
        //-response.contentType='application/pdf'
	response.outputStream << bytes
        //response.outputStream << render(file: new File("c:\\reportes\\repge.pdf"), fileName: "Resolucion.pdf",contentType: "application/pdf")
        response.outputStream.flush()
	//response.out << bytes        
       
        response.outputStream.close()
        return false
        //println(params.)
    }
    
 
def buscaPapeleta(){
    println("Si llego a tu controlador")
    def fecha
    fecha=new Date()
    def user = springSecurityService.currentUser
    def totalPapeletas
    def scasolInstance
    PDFMergerUtility mergePdf = new PDFMergerUtility() 
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
    def mapa=[]
        byte[] bytes
        byte [] a
        byte [] b
        def enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")  
        def enarchivo=EncargadoArchivo.find("from EncargadoArchivo where activo=true")
        if(params.progresivos)
        {
          for(int i=0;i<params.progresivos.size();i++)
          {
            //println(params.progresivos[i])
            scasolInstance=Scasol.findAllByExproAndExpano(Integer.valueOf(params.progresivos[i]),Integer.valueOf((fecha.year+1900)))
            totalPapeletas=Papeleta.findAllByExproAndExpano(Integer.valueOf(params.progresivos[i]),Integer.valueOf((fecha.year+1900)))
            for(int o=0;o<totalPapeletas.size();o++)
            {
                //println(totalPapeletas)
                switch(totalPapeletas.donde[o])
                {
                    case "A":
                        println("Va para ambos")
                                                    mapa << [
            expediente:"Expediente No.: "+scasolInstance.expro[0]+"/"+scasolInstance.expano[0],
            nombreoficialia:"        "+scasolInstance.ofi[0].toString()+", "+scasolInstance.dto[0].toString(),
            texto:totalPapeletas.nota[o].replaceAll(", y"," y ")+"\n\n\n",
            boleta:"** Esta papeleta solamente es válida adherida al libro original **",
            encargadojuridico:"Comprobante pago de derechos\n\
\n\
Elaboró: "+user.username+"              Fecha:  \n\
\n\
EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"EL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi[0].toString()+", "+scasolInstance.dto[0].toString()    
            
                ]
                 try{
                reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                bytes = jasperService.generateReport(reportDef).toByteArray()
                a =bytes
                mapa=[]
                            
                                                                    mapa << [
            expediente:"Expediente No.: "+scasolInstance.expro[0]+"/"+scasolInstance.expano[0],
            nombreoficialia:"        "+"ARCHIVO CENTRAL",
            texto:totalPapeletas.nota[o].replaceAll(", y"," y ")+"\n\n\n",
            boleta:"** Esta papeleta solamente es válida adherida al libro original **",
            encargadojuridico:"Comprobante pago de derechos\n\
\n\
Elaboró: "+user.username+"              Fecha:  \n\
\n\
EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),               
                encargado:"EL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString()    
            
                ]            
                reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                bytes = jasperService.generateReport(reportDef).toByteArray()
                b =bytes                
                mergePdf.addSource(new ByteArrayInputStream(a));
                mergePdf.addSource(new ByteArrayInputStream(b));
                /*mergePdf.setDestinationStream(byteArrayOutputStream);
                mergePdf.mergeDocuments();
                bytes= byteArrayOutputStream.toByteArray();*/
                }catch(Exception ex){
                println(ex.printStackTrace())
                }
                        
                        break
                    case "C":
                        println("Va para el archivo")
                                                                            mapa << [
            expediente:"Expediente No.: "+scasolInstance.expro[0]+"/"+scasolInstance.expano[0],
            nombreoficialia:"        "+"ARCHIVO CENTRAL",
            texto2:totalPapeletas.nota[o].replaceAll(", y"," y ")+"\n\n\n",
            texto:totalPapeletas.nota[o].replaceAll(", y"," y ")+"\n\n\n",
            boleta:"** Esta papeleta solamente es válida adherida al libro original **",
            encargadojuridico:"Comprobante pago de derechos\n\
\n\
Elaboró: "+user.username+"              Fecha:  \n\
\n\
EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"EL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString()
                
                ]
                                 try{
                reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                bytes = jasperService.generateReport(reportDef).toByteArray()
                a =bytes               
                mergePdf.addSource(new ByteArrayInputStream(a));                
                /*mergePdf.setDestinationStream(byteArrayOutputStream);
                mergePdf.mergeDocuments();
                bytes= byteArrayOutputStream.toByteArray();*/
                }catch(Exception ex){
                println(ex.printStackTrace())
                }
                        break
                case "O":
                        println("Va para la oficialia")
                                                                            mapa << [
           
            expediente:"Expediente No.: "+scasolInstance.expro[0]+"/"+scasolInstance.expano[0],
            nombreoficialia:"        "+scasolInstance.ofi[0].toString()+", "+scasolInstance.dto[0].toString(),
            texto2:totalPapeletas.nota[o].replaceAll(", y"," y ")+"\n\n\n",
            texto:totalPapeletas.nota[o].replaceAll(", y"," y ")+"\n\n\n",
            boleta:"** Esta papeleta solamente es válida adherida al libro original **",
            encargadojuridico:"Comprobante pago de derechos\n\
\n\
Elaboró: "+user.username+"              Fecha:  \n\
\n\
EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                oficial:"EL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString(),
                encargado:"EL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi[0].toString()+", "+scasolInstance.dto[0].toString()    
            
                ]
                try{
                reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                bytes = jasperService.generateReport(reportDef).toByteArray()
                a =bytes               
                mergePdf.addSource(new ByteArrayInputStream(a));                
                /*mergePdf.setDestinationStream(byteArrayOutputStream);
                mergePdf.mergeDocuments();
                bytes= byteArrayOutputStream.toByteArray();*/
                }catch(Exception ex){
                println(ex.printStackTrace())
                }
                break
                }
                println(scasolInstance)
                
                    mapa=[]
            }
            
          }
          mergePdf.setDestinationStream(byteArrayOutputStream)
          mergePdf.mergeDocuments()
          bytes= byteArrayOutputStream.toByteArray()
          response.addHeader("Content-Disposition", 'attachment; filename="Papeletas.pdf"')
	
        //-response.contentType='application/pdf'
	response.outputStream << bytes
        //response.outputStream << render(file: new File("c:\\reportes\\repge.pdf"), fileName: "Resolucion.pdf",contentType: "application/pdf")
        response.outputStream.flush()
	//response.out << bytes        
       
        response.outputStream.close()
        return false
        //println(params.)
        }
        else{
            return
        }
}    
    
    
}
