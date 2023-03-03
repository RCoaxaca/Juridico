package catalogos

import tablas.Scasol
import org.apache.commons.io.FileUtils
import org.apache.pdfbox.util.PDFMergerUtility
import org.springframework.dao.DataIntegrityViolationException
import tablas.Nota
import tablas.Nota
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef
import org.krysalis.barcode4j.impl.code39.Code39Bean
import tablas.Scaact
import tablas.Scaerr
import catalogos.EncargadoJuridico
import catalogos.Papeleta
import com.testapp.User
import catalogos.Papeleta

class OpcionController {
    def edadletra=""
    Date fec1=new Date();
    def fech1=fec1.toTimestamp() 
    String [] hoy=fech1.toString().split(" ")
    String [] hoy2=hoy[0].toString().split("-")
    def jasperService
    def reportDef
    def barcode4jService
    def artmat=""
    def artmat2=""
    def legitimacion=""
    def reconocimiento=""
    String [] meses2=["","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"]
    def enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")  
    def enarchivo=EncargadoArchivo.find("from EncargadoArchivo where activo=true")
    def opcionService
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
    
    
    
def toPdf(){

        def usuactual=User.get(params.dato3)
 def usuario=""
 if(usuactual.nombre)
 {
     usuario+=usuactual.nombre.substring(0,1)
 }
 if(usuactual.apellpa)
 {
     usuario+=usuactual.apellpa.substring(0,1)
 }
 if(usuactual.apellma)
 {
     usuario+=usuactual.apellma.substring(0,1)
 }
        //println("Los de arriba son los datos")
        def textoInstance
        def notanota=""
         //println("Se imprime la unica que se guardo")
         textoInstance= Nota.findByExpanoAndExpro(params.dato2,params.dato1)
         /*println("----------------")
         println(textoInstance.nota)
         println("----------------")*/
         //textoInstance.nota=textoInstance.nota.replaceAll("'>","'> *")
         notanota=textoInstance.nota
         notanota=notanota.replaceAll(notanota.substring(0,(notanota.indexOf("</p>")+4)),"")
         notanota=notanota.replaceAll("'>","'> *")
         notanota=notanota.replaceAll("\">","\"> *")
         notanota=notanota.replaceAll("&nbsp;","")         
        notanota=cleanHtml(notanota,'simpleText')
        if(!textoInstance.equals())
        {
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
        notanota=notanota.replaceAll("&deg;","°")
        /*println(notanota.replaceAll("\\*","\n\
"))*/
            
      notanota=notanota.replaceAll("\\*","\n\
")
        notanota=notanota.replaceAll("R E S U E L V E ","                                                                 R  E  S  U  E  L  V  E")
        notanota=notanota.replaceAll("C O N S I D E R A N D O ","                                                                   C O N S I D E R A N D O ")
        
        def espacio="                                                                                                                                                      "
        int longitud=(espacio.toString().length()-((enjuridico.titulo+" "+enjuridico).toString().length()))/2
        espacio=espacio.substring(0,longitud)
        notanota=notanota.replaceAll(""+enjuridico.titulo+" "+enjuridico,espacio+""+enjuridico.titulo+" "+enjuridico)
        def mapa=[]
        def mapapapeleta=[]
        byte[] bytes
        


                 
        mapa << [
           
            //resolucion:reso,
            exp:"Expediente: "+params.dato1+"/"+params.dato2 ,
            /*pa2:notanota.replaceAll("\\*","\n\
")*/
            fecha:"Oaxaca de Juárez, Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
            copia1:notanota+"\n\
"+usuactual+"\n\n\n\n"
                ]

                
            def reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )

        try{
            bytes = jasperService.generateReport(reportDef).toByteArray()
            byte [] a =bytes
            
            PDFMergerUtility mergePdf = new PDFMergerUtility() 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            
            
               // println(textoInstance.idn.toString()+" Aqui me dice a donde se tiene que enviar")
                def scasolInstance
                scasolInstance=Scasol.findByExpanoAndExpro(params.dato2,params.dato1)
                def of1=Scaofi.findByDescrip(scasolInstance.ofi.toString())
            if(textoInstance.idn.toString().equals("A"))
            {
  
            def papeletaInstance 
            papeletaInstance=Papeleta.findByExpanoAndExpro(params.dato2,params.dato1)
            papeletaInstance.nota=papeletaInstance.nota.replaceAll("&nbsp;","")
                                def agegaespacioOfi=""
        def totalineasOficialia=0
        def longitud2=(papeletaInstance.nota.toString().length())
                 totalineasOficialia = longitud2.intdiv(120)
                 totalineasOficialia+=1
                 def espacioagregaOficialia=0                 
                 espacioagregaOficialia=28-totalineasOficialia
                // println(totalineasOficialia+" Aqui va el total de linas que tiene actualmente")
                // println(espacioagregaOficialia+" Aqui va el total de espacios que se van a agregar")
                 for(int y=0;y<espacioagregaOficialia;y++)
                 {
                    agegaespacioOfi+="\n\
        " 
                 }  
                        mapapapeleta << [
           
            expediente2:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,
            nombreoficialia:"        "+scasolInstance.ofi.toString()+""+of1.nombre,
            texto2:papeletaInstance.nota.replaceAll("&nbsp;","").replaceAll("&Uuml;","Ü")+"\n\n\n",
            texto:papeletaInstance.nota.replaceAll("&nbsp;","").replaceAll("&Uuml;","Ü")+"\n\n\n",
            boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id)+agegaespacioOfi,
            encargadojuridico:"\nComprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString(),
                oficialia:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+""+of1.nombre    
            
                ]
                                        mapapapeleta << [
            
            expediente2:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,
            nombreoficialia:"        "+"ARCHIVO CENTRAL",
            texto2:papeletaInstance.nota.replaceAll("&nbsp;","").replaceAll("&Uuml;","Ü")+"\n\n\n",
            texto:papeletaInstance.nota.replaceAll("&nbsp;","").replaceAll("&Uuml;","Ü")+"\n\n\n",
            boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id),
            encargadojuridico:"\nComprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                oficialia:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString()
                ]
                reportDef = new JasperReportDef(name: "imprimirTodo/texto3.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapapapeleta )
                byte [] b 
                b=jasperService.generateReport(reportDef).toByteArray()
                //reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapapapeleta )
                //byte [] c
                //c = jasperService.generateReport(reportDef).toByteArray()
                
            mergePdf.addSource(new ByteArrayInputStream(a));
            mergePdf.addSource(new ByteArrayInputStream(a));
            mergePdf.addSource(new ByteArrayInputStream(a));
            mergePdf.addSource(new ByteArrayInputStream(b));
            //mergePdf.addSource(new ByteArrayInputStream(c));
            }
            else{
                def papeletaInstance
                if(textoInstance.idn.toString().contains("O"))
                {
                   // println("Solo vamos a mandar a la oficialia y lo tengo que buscar -------------------------------")
                   // println(params.dato2)
                  //  println(params.dato1)
                  papeletaInstance=Papeleta.findByExpanoAndExproAndDonde(params.dato2,params.dato1,'O')  
                }
                else{papeletaInstance=Papeleta.findByExpanoAndExproAndDonde(params.dato2,params.dato1,'C')}
                papeletaInstance.nota=papeletaInstance.nota.replaceAll("&nbsp;","")

                
                    if(textoInstance.idn.toString().contains("O"))
                    {
                                                                mapapapeleta << [
            //expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"        "+"ARCHIVO CENTRAL",
            expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,
            nombreoficialia:"        "+scasolInstance.ofi.toString()+""+of1.nombre,
            texto2:papeletaInstance.nota.replaceAll("&nbsp;","").replaceAll("&Uuml;","Ü")+"\n\n\n",
            texto:papeletaInstance.nota.replaceAll("&nbsp;","").replaceAll("&Uuml;","Ü")+"\n\n\n",
            boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id),
            encargadojuridico:"\nComprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+""+of1.nombre    
            
                ]
                       reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapapapeleta )
                 
                    }
                    else{
                                                                mapapapeleta << [
            //expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"        "+"ARCHIVO CENTRAL",
            expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,
            nombreoficialia:"        "+"ARCHIVO CENTRAL",
            texto2:papeletaInstance.nota.replaceAll("&nbsp;","").replaceAll("&Uuml;","Ü")+"\n\n\n",
            texto:papeletaInstance.nota.replaceAll("&nbsp;","").replaceAll("&Uuml;","Ü")+"\n\n\n",
            boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id),
            encargadojuridico:"\nComprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString() 
            
                ]
                        reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapapapeleta )
                        }
                byte [] c
                c = jasperService.generateReport(reportDef).toByteArray()
                mergePdf.addSource(new ByteArrayInputStream(a));
                mergePdf.addSource(new ByteArrayInputStream(a));
                mergePdf.addSource(new ByteArrayInputStream(c));
            }
            mergePdf.setDestinationStream(byteArrayOutputStream);
            mergePdf.mergeDocuments();
            bytes= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Considerando'+params.dato1+'/'+params.dato2+'.pdf"')
	
        //-response.contentType='application/pdf'
	response.outputStream << bytes
        //response.outputStream << render(file: new File("c:\\reportes\\repge.pdf"), fileName: "Resolucion.pdf",contentType: "application/pdf")
        response.outputStream.flush()
	//response.out << bytes        
       
        response.outputStream.close()
        return false
            
       /* ToPdf topdf = new ToPdf(textoInstance.id, textoInstance.nota,textoInstance.expano,textoInstance.expro,usuario)
        topdf.generarPdf()
        File myFile = new File(topdf.getRuta())
        try{
            response.addHeader("Content-Disposition", "inline; filename=${myFile.name}")
            //response.setHeader "Content-disposition", "attachment; filename=${myFile.name}"
            //response.setHeader "Content-disposition", "inline; filename=${myFile.name}"
            //response.contentType = new MimetypesFileTypeMap().getContentType( myFile )
            response.contentType='application/pdf'
            response.outputStream << myFile.bytes
            response.outputStream.flush()
            response.outputStream.close()
        }catch (e){
            println(e.message)
        }*/
        }else
        {
            println("No encontro nada de nada")
        }
        
        
         
    }
    
    
/*def toPdfOficialia(){
    
         println("--------")
         println(params)
         println(params.dato1[0])
         println(params.dato2[0])
         println(params.dato3)
         println(params.archivo.toBoolean())
         println(params.oficialia.toBoolean())
         println(params.oficialia.size())
         println("-*-*-*-*-*-*-*-")
        //println(params.aniodoc)
        //println(params.numero)
        println("-----------") 
        //println(id)
        //println(aniodoc)
        def usuactual=User.get(params.dato3)

        println("Los de arriba son los datos")
        def textoInstance
            println("Se imprime la de oficialia")
            textoInstance= Nota.findByExpanoAndExproAndIdn(params.dato2,params.dato1,'O')


        
        if(!textoInstance.equals())
        {
        ToPdf topdf = new ToPdf(textoInstance.id, textoInstance.nota,textoInstance.expano,textoInstance.expro,usuactual.toString())
        topdf.generarPdf()
        File myFile = new File(topdf.getRuta())
        try{
            response.addHeader("Content-Disposition", "inline; filename=${myFile.name}")
            //response.setHeader "Content-disposition", "attachment; filename=${myFile.name}"
            //response.setHeader "Content-disposition", "inline; filename=${myFile.name}"
            //response.contentType = new MimetypesFileTypeMap().getContentType( myFile )
            response.contentType='application/pdf'
            response.outputStream << myFile.bytes
            response.outputStream.flush()
            response.outputStream.close()
        }catch (e){
            println(e.message)
        }
        }else
        {
            println("No encontro nada de nada")
        }
        
        
         
    }*/
    
def toPdfOficialia(){
def scasolInstance
scasolInstance=Scasol.findByExpanoAndExpro(params.dato2,params.dato1)
def of1=Scaofi.findByDescrip(scasolInstance.ofi.toString())
        def usuactual=User.get(params.dato3)
 def usuario=""
 if(usuactual.nombre)
 {
     usuario+=usuactual.nombre.substring(0,1)
 }
 if(usuactual.apellpa)
 {
     usuario+=usuactual.apellpa.substring(0,1)
 }
 if(usuactual.apellma)
 {
     usuario+=usuactual.apellma.substring(0,1)
 }
        //println("Los de arriba son los datos")
        def textoInstance
        def notanota=""         
         textoInstance= Nota.findByExpanoAndExproAndIdn(params.dato2,params.dato1,'O')         
         notanota=textoInstance.nota
         notanota=notanota.replaceAll(notanota.substring(0,(notanota.indexOf("</p>")+4)),"")
         notanota=notanota.replaceAll("'>","'> *")
         notanota=notanota.replaceAll("\">","\"> *")
         notanota=notanota.replaceAll("&nbsp;","")         
        notanota=cleanHtml(notanota,'simpleText')
        if(!textoInstance.equals())
        {
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
        notanota=notanota.replaceAll("&deg;","°")
        /*println(notanota.replaceAll("\\*","\n\
"))*/
            
            
        //println()    
        //notanota=notanota.replaceAll("Oaxaca de Júarez Oaxaca a 28 de ABRIL de 2016","<p align=center>Oaxaca de Júarez Oaxaca a 28 de ABRIL de 2016</p><br>")    
        notanota=notanota.replaceAll("\\*","\n\
")
        notanota=notanota.replaceAll("R E S U E L V E ","                                                                 R  E  S  U  E  L  V  E")
        //println(textoInstance.nota.substring(0,(textoInstance.nota.indexOf("</p>")+4))+" AQui viene algo para ver si funciona")
        notanota=notanota.replaceAll("C O N S I D E R A N D O ","                                                                   C O N S I D E R A N D O ")
        
        def espacio="                                                                                                                                                      "
        //def  enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")
        int longitud=(espacio.toString().length()-((enjuridico.titulo+" "+enjuridico).toString().length()))/2
         espacio=espacio.substring(0,longitud)
         //println(notanota.replaceAll(""+enjuridico.titulo+" "+enjuridico,"AQUI VA EL NOMBRE DEL JEFE"))
         notanota=notanota.replaceAll(""+enjuridico.titulo+" "+enjuridico,espacio+""+enjuridico.titulo+" "+enjuridico)
        def mapa=[]
        byte[] bytes
        def papeletaInstance
        papeletaInstance=Papeleta.findByExpanoAndExproAndDonde(params.dato2,params.dato1,'O')
        papeletaInstance.nota=papeletaInstance.nota.replaceAll("&nbsp;","")
        def mapapapeleta=[]
        
        
            
                                mapapapeleta << [
 //expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"        "+"ARCHIVO CENTRAL",
                expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,
                nombreoficialia:"        "+scasolInstance.ofi.toString()+", "+scasolInstance.dto.toString(),
                texto:papeletaInstance.nota.replaceAll("&nbsp;","").replaceAll("&Uuml;","Ü")+"\n\n\n",
                //texto:papeletaInstance.nota.replaceAll("&nbsp;","")+"\n\n",
                boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id),
                encargadojuridico:"Comprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                /*encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString()*/
                encargado:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+", "+scasolInstance.dto.toString()    
            
                ]    
        
        mapa << [
           
            //resolucion:reso,
            exp:"Expediente: "+params.dato1+"/"+params.dato2 ,
            /*pa2:notanota.replaceAll("\\*","\n\
")*/
            fecha:"Oaxaca de Juárez, Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
            pa2:notanota.replaceAll("&Uuml;","Ü")+"\n\
"+usuactual
                ]

                
            def reportDef = new JasperReportDef(name: "Prueba1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )

        try{
            bytes = jasperService.generateReport(reportDef).toByteArray()
             byte [] a =bytes
            reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapapapeleta )
                byte [] c
                c = jasperService.generateReport(reportDef).toByteArray()
            PDFMergerUtility mergePdf = new PDFMergerUtility() 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                mergePdf.addSource(new ByteArrayInputStream(a));
                mergePdf.addSource(new ByteArrayInputStream(a));
                mergePdf.addSource(new ByteArrayInputStream(c));
            
            mergePdf.setDestinationStream(byteArrayOutputStream);
            mergePdf.mergeDocuments();
            bytes= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Considerando "'+params.dato1+"/"+params.dato2+'".pdf"')
	
        //-response.contentType='application/pdf'
	response.outputStream << bytes
        //response.outputStream << render(file: new File("c:\\reportes\\repge.pdf"), fileName: "Resolucion.pdf",contentType: "application/pdf")
        response.outputStream.flush()
	//response.out << bytes        
       
        response.outputStream.close()
        return false
            
       /* ToPdf topdf = new ToPdf(textoInstance.id, textoInstance.nota,textoInstance.expano,textoInstance.expro,usuario)
        topdf.generarPdf()
        File myFile = new File(topdf.getRuta())
        try{
            response.addHeader("Content-Disposition", "inline; filename=${myFile.name}")
            //response.setHeader "Content-disposition", "attachment; filename=${myFile.name}"
            //response.setHeader "Content-disposition", "inline; filename=${myFile.name}"
            //response.contentType = new MimetypesFileTypeMap().getContentType( myFile )
            response.contentType='application/pdf'
            response.outputStream << myFile.bytes
            response.outputStream.flush()
            response.outputStream.close()
        }catch (e){
            println(e.message)
        }*/
        }else
        {
            println("No encontro nada de nada")
        }
        
        
         
    }

    
    
def toPdfArchivo(){
def scasolInstance
                scasolInstance=Scasol.findByExpanoAndExpro(params.dato2,params.dato1)
                def of1=Scaofi.findByDescrip(scasolInstance.ofi.toString())
    def usuactual=User.get(params.dato3)
    def usuario=""
    def textoInstance
    def notanota=""
    textoInstance= Nota.findByExpanoAndExproAndIdn(params.dato2,params.dato1,'C')
 
         notanota=textoInstance.nota
         notanota=notanota.replaceAll(notanota.substring(0,(notanota.indexOf("</p>")+4)),"")
         notanota=notanota.replaceAll("'>","'> *")
         notanota=notanota.replaceAll("\">","\"> *")
         notanota=notanota.replaceAll("&nbsp;","")

        notanota=cleanHtml(notanota,'simpleText')

        if(!textoInstance.equals())
        {
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
        notanota=notanota.replaceAll("&deg;","°")
       notanota=notanota.replaceAll("\\*","\n\
")
        notanota=notanota.replaceAll("R E S U E L V E ","                                                                 R  E  S  U  E  L  V  E")
        //println(textoInstance.nota.substring(0,(textoInstance.nota.indexOf("</p>")+4))+" AQui viene algo para ver si funciona")
        notanota=notanota.replaceAll("C O N S I D E R A N D O ","                                                                   C O N S I D E R A N D O ")
        
        def espacio="                                                                                                                                                     "
       
        int longitud=(espacio.toString().length()-((enjuridico.titulo+" "+enjuridico).toString().length()))/2
         espacio=espacio.substring(0,longitud)
         notanota=notanota.replaceAll(""+enjuridico.titulo+" "+enjuridico,espacio+""+enjuridico.titulo+" "+enjuridico)
        def mapa=[]
        byte[] bytes
        
            def papeletaInstance
        papeletaInstance=Papeleta.findByExpanoAndExproAndDonde(params.dato2,params.dato1,'C')
        papeletaInstance.nota=papeletaInstance.nota.replaceAll("&nbsp;","").replaceAll("&Uuml;","Ü")
        def mapapapeleta=[]
        
                                        mapapapeleta << [
                  expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,
                //expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"        "+scasolInstance.ofi.toString()+", "+scasolInstance.dto.toString(),
                nombreoficialia:"        "+"ARCHIVO CENTRAL",
                texto:papeletaInstance.nota.replaceAll("&nbsp;","").replaceAll("&Uuml;","Ü")+"\n\n\n",
                //texto:papeletaInstance.nota.replaceAll("&nbsp;","")+"\n\n",
                boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id),
                encargadojuridico:"Comprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString()
                //encargado:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+", "+scasolInstance.dto.toString()    
            
                ] 
            
                                /*mapapapeleta << [
            expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"        "+"ARCHIVO CENTRAL",
            expediente2:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"        "+"ARCHIVO CENTRAL",
            texto2:papeletaInstance.nota.replaceAll("&nbsp;",""),
            texto:papeletaInstance.nota.replaceAll("&nbsp;",""),
            boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+User.get(springSecurityService.currentUser.id),
            encargadojuridico:"\n\
Comprobante pago de derechos\n\
\n\
"+"\t\t"+"              Fecha:  \n\
\n\
EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"EL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString(),
                oficialia:"EL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+""+of1.nombre    
            
                ]*/    
        
        mapa << [           
            
            exp:"Expediente: "+params.dato1+"/"+params.dato2 ,
            fecha:"Oaxaca de Juárez, Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
            pa2:notanota+"\n\
"+usuactual
                ]

                
            def reportDef = new JasperReportDef(name: "Prueba1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )

        try{
            bytes = jasperService.generateReport(reportDef).toByteArray()
             byte [] a =bytes
             reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapapapeleta )
                byte [] c
                c = jasperService.generateReport(reportDef).toByteArray()
            
            PDFMergerUtility mergePdf = new PDFMergerUtility() 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            mergePdf.addSource(new ByteArrayInputStream(a));
            mergePdf.addSource(new ByteArrayInputStream(a));           
            mergePdf.addSource(new ByteArrayInputStream(c));
            mergePdf.setDestinationStream(byteArrayOutputStream);
            mergePdf.mergeDocuments();
            bytes= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Considerando "'+params.dato1+"/"+params.dato2+'".pdf"')
	response.outputStream << bytes
        response.outputStream.flush()
        response.outputStream.close()
        return false           
       
        }else
        {
            println("No encontro nada de nada")
        }
        
        
         
    }
    
    
/*def toPdfArchivo(){
    
         println("--------")
         println(params.dato1[0])
         println(params.dato2[0])
         println(params.dato3)
        
         println("-*-*-*-*-*-*-*-")
        //println(params.aniodoc)
        //println(params.numero)
        println("-----------") 
        //println(id)
        //println(aniodoc)
        def usuactual=User.get(params.dato3)
 def usuario=""
 if(usuactual.nombre)
 {
     usuario+=usuactual.nombre.substring(0,1)
 }
 if(usuactual.apellpa)
 {
     usuario+=usuactual.apellpa.substring(0,1)
 }
 if(usuactual.apellma)
 {
     usuario+=usuactual.apellma.substring(0,1)
 }
        println("Los de arriba son los datos")
        def textoInstance

            println("Se imprime la de archivo")
            textoInstance= Nota.findByExpanoAndExproAndIdn(params.dato2,params.dato1,'C')

        
        if(!textoInstance.equals())
        {
        ToPdf topdf = new ToPdf(textoInstance.id, textoInstance.nota,textoInstance.expano,textoInstance.expro,usuario)
        topdf.generarPdf()
        File myFile = new File(topdf.getRuta())
        try{
            response.addHeader("Content-Disposition", "inline; filename=${myFile.name}")
            //response.setHeader "Content-disposition", "attachment; filename=${myFile.name}"
            //response.setHeader "Content-disposition", "inline; filename=${myFile.name}"
            //response.contentType = new MimetypesFileTypeMap().getContentType( myFile )
            response.contentType='application/pdf'
            response.outputStream << myFile.bytes
            response.outputStream.flush()
            response.outputStream.close()
        }catch (e){
            println(e.message)
        }
        }else
        {
            println("No encontro nada de nada")
        }
        
        
         
    }*/
    

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [opcionInstanceList: Opcion.list(params), opcionInstanceTotal: Opcion.count()]
    }
    
    
    def imprimirSoloPapeleta()
    {
        def enjuridicoo=EncargadoJuridico.find("From EncargadoJuridico where activo=true")  
        def enarchivoo=EncargadoArchivo.find("from EncargadoArchivo where activo=true")

        //println(params)
        def papeletaOficialia=""
        def papeletaArchivo=""
        String [] oficialia
        def mapa=[]
        byte[] bytes
        //def reportDef
        def usuarioInstance=User.get(springSecurityService.currentUser.id)
        def scasolInstance=Scasol.get(params.id)
        def of1=Scaofi.findByDescrip(scasolInstance.ofi.toString())
        def reviso=""
        def compruebaPapeleta
        compruebaPapeleta=Papeleta.findAllByExpanoAndExpro(scasolInstance.expano,scasolInstance.expro)
            new File("D:/codigo.png").withOutputStream { out ->
    barcode4jService.render("code39Generator", scasolInstance.expro+"/"+scasolInstance.expano, out, "image/png")
}
        if(compruebaPapeleta){
           
        if(compruebaPapeleta.size()>1)
        {
            compruebaPapeleta.each
            {
                it.delete(flush: true)
            }
        }else {compruebaPapeleta[0].delete(flush: true)}
        }

        try
        {
            oficialia=opcionService.imprimeOficialia(params.id).split("\\.\\.\\.")
            println("**********************")
            println(opcionService.imprimeOficialia(params.id))
            println("**********************")
            if(oficialia[1].isEmpty())
            {
                //println("La oficialia no trae nada")
            }
            papeletaOficialia=oficialia[1]
        }catch(Exception e){e.printStackTrace()}
        try
        {
            oficialia=opcionService.imprimeArchivo(params.id).split("\\.\\.\\.")
            papeletaArchivo=oficialia[1]
        }catch(Exception e){e.printStackTrace()}
        
        //papeletaOficialia=imprimeOficialia(params.id).split("\\.\\.\\.",1)
        //println("**********************")
        //println(opcionService.imprimeOficialia(params.id))
        //println("**********************")
        //println("---------------------------")
        //println(papeletaOficialia)
        //println("---------------------------")
        //println(papeletaArchivo)
        //println("---------------------------")
        def agegaespacioOfi=""        
        def papeletaSave
        def papeletaInstance
        def totalineasOficialia=0
        
                def longitud=(papeletaArchivo.toString().length())
                 totalineasOficialia = longitud.intdiv(120)
                 totalineasOficialia+=1
                 def espacioagregaOficialia=0                 
                 espacioagregaOficialia=22-totalineasOficialia
                 //println(totalineasOficialia+" Aqui va el total de linas que tiene actualmente")
                 //println(espacioagregaOficialia+" Aqui va el total de espacios que se van a agregar")
                 for(int y=0;y<espacioagregaOficialia;y++)
                 {
                    agegaespacioOfi+="\n\
        " 
                 }
                 
        if(!papeletaOficialia.isEmpty() && !papeletaArchivo.isEmpty())
        {
           
           
           if(papeletaOficialia.toString().equals(papeletaArchivo.toString()))
           {
               //println("Es la misma papeleta") 
               //println("Aqui va el total de lineas que se tienen que agregar: "+espacioagregaOficialia+" ######################")
               papeletaSave=Papeleta.findByExpanoAndExpro(scasolInstance.expano,scasolInstance.expro)
               if(papeletaSave.equals(null))
               {   
                   papeletaInstance = new Papeleta()
                   papeletaInstance.nota="\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142").replaceAll(", 35 y  del"," y 35 del")+"...\""
                   papeletaInstance.nota=papeletaInstance.nota.replaceAll(",  y 142"," y 142").replaceAll(", 35 y  del"," y 35 del")
                   papeletaInstance.nota=papeletaInstance.nota.replaceAll("&quot;","\"").replaceAll("&Uuml;","Ü")
                   papeletaInstance.expano=scasolInstance.expano
                   papeletaInstance.expro=scasolInstance.expro
                   papeletaInstance.donde="A"
                   papeletaInstance.ip=request.getRemoteAddr().toString()
                   papeletaInstance.usuario=springSecurityService.currentUser.id
                   if (!papeletaInstance.save(flush: true)) {
                    //render(view: "create", model: [notaInstance: notaInstance])
                    //return
                    println("No se pudo guardar nada en papelta")
                }
               }
               else
               {
                    println("Solo se va a actualizar la nota existente")
                    papeletaSave.nota="\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142 ").replaceAll(", 35 y  del"," y 35 del")+"...\""
                    papeletaSave.donde="A"                   
                    if (!papeletaSave.save(flush: true)) {                   
                    println("No se pudo actualizar la papeleta")
                }
               }
           }
           else
           {
               //println("Son diferentes papeletas")
               papeletaSave=Papeleta.findByExpanoAndExproAndDonde(scasolInstance.expano,scasolInstance.expro,"C")
               if(papeletaSave.equals(null))
               {
                   //papeletaInstance  = new Papeleta()
                   papeletaInstance = new Papeleta()
                   papeletaInstance.nota="\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142").replaceAll(", 35 y  del"," y 35 y del")+"...\""
                   papeletaInstance.nota=papeletaInstance.nota.replaceAll("&quot;","\"").replaceAll("&Uuml;","Ü")
                    papeletaInstance.expano=scasolInstance.expano
                   papeletaInstance.expro=scasolInstance.expro
                   papeletaInstance.donde="C"
                   papeletaInstance.ip=request.getRemoteAddr().toString()
                   papeletaInstance.usuario=springSecurityService.currentUser.id
                   if (!papeletaInstance.save(flush: true)) {
                    //render(view: "create", model: [notaInstance: notaInstance])
                    //return
                    //println("No se pudo guardar nada en papelta")
                }
               }
               else
               {
                   papeletaSave.nota="\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142").replaceAll(", 35 y  del"," y 35 del")+"...\""
                   papeletaSave.nota=papeletaSave.nota.replaceAll(",  y 142"," y 142").replaceAll(", 35 y  del"," y 35 del")
                   papeletaSave.nota=papeletaSave.nota.replaceAll("&quot;","\"").replaceAll("&Uuml;","Ü")
                    if (!papeletaSave.save(flush: true)) {                   
                    //println("No se pudo actualizar la papeleta")
               }
               }
               papeletaSave=Papeleta.findByExpanoAndExproAndDonde(scasolInstance.expano,scasolInstance.expro,"O")
               if(papeletaSave.equals(null))
               {
                   //papeletaInstance  = new Papeleta()
                   papeletaInstance = new Papeleta()
                   papeletaInstance.nota="\"..."+papeletaOficialia.replaceAll(",  y 142"," y 142").replaceAll(", 35 y  del"," y 35 del")+"...\""
                   papeletaInstance.nota=papeletaInstance.nota.replaceAll(",  y 142"," y 142").replaceAll(", 35 y  del"," y 35 del")
                   papeletaInstance.nota=papeletaInstance.nota.replaceAll("&quot;","\"").replaceAll("&Uuml;","Ü")
                    papeletaInstance.expano=scasolInstance.expano
                   papeletaInstance.expro=scasolInstance.expro
                   papeletaInstance.donde="O"
                   papeletaInstance.ip=request.getRemoteAddr().toString()
                   papeletaInstance.usuario=springSecurityService.currentUser.id
                   if (!papeletaInstance.save(flush: true)) {
                    //render(view: "create", model: [notaInstance: notaInstance])
                    //return
                    //println("No se pudo guardar nada en papelta")
                }
               }
               else
               {
                   papeletaSave.nota="\"..."+papeletaOficialia.replaceAll(",  y 142"," y 142").replaceAll(", 35 y  del"," y 35 del").replaceAll("&Uuml;","Ü")+"...\""
                   if (!papeletaSave.save(flush: true)) {                   
                   // println("No se pudo actualizar la papeleta")
               }
           }
        
        }
        if(!papeletaOficialia.isEmpty() && !papeletaArchivo.isEmpty())
        {
             
            
            //println("Se envian papeletas al Archivo Central y la Oficialia")
            if(scasolInstance.sexintere.contains("F"))
                {
          papeletaOficialia=papeletaOficialia.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y LA REGISTRADA")
          papeletaOficialia=papeletaOficialia.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y LA REGISTRADA")
          papeletaArchivo=papeletaArchivo.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y LA REGISTRADA")
          papeletaArchivo=papeletaArchivo.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y LA REGISTRADA")
          
                }
            mapa << [
            expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,
            nombreoficialia:"        "+"ARCHIVO CENTRAL",
            texto:"\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142").replaceAll("&nbsp;","").replaceAll("&quot;","\"").replaceAll("&Uuml;","Ü").replaceAll(", 35 y  del"," y 35 del")+"...\""+"\n\n",
            boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap+"/"+User.get(springSecurityService.currentUser.id)+agegaespacioOfi,
            encargadojuridico:"Comprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString()
                
                ]
                
                            mapa << [
            expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,
            nombreoficialia:"        "+scasolInstance.ofi.toString()+""+of1.nombre,
            texto:"\"..."+papeletaOficialia.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"").replaceAll("&Uuml;","Ü").replaceAll("&nbsp;","").replaceAll(", 35 y  del"," y 35 del")+"...\""+"\n\n",
            boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap+"/"+User.get(springSecurityService.currentUser.id),
            encargadojuridico:"Comprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+""+of1.nombre    
            
                ]

                reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                try{
                bytes = jasperService.generateReport(reportDef).toByteArray()                
   
                }catch(Exception ex){
                println(ex.printStackTrace())
                }

        }
        
        
        
        
   
    
    
        }
        
        else {
            println("Solo va para una")
            if(!papeletaOficialia.isEmpty())
            {
                
                
               papeletaSave=Papeleta.findByExpanoAndExproAndDonde(scasolInstance.expano,scasolInstance.expro,"O")
               if(papeletaSave.equals(null))
               {   
                   papeletaInstance = new Papeleta()
                   papeletaInstance.nota="\"..."+papeletaOficialia.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"").replaceAll(", 35 y  del"," y 35 del")+"...\""
                   papeletaInstance.expano=scasolInstance.expano
                   papeletaInstance.expro=scasolInstance.expro
                   papeletaInstance.donde="O"
                   papeletaInstance.ip=request.getRemoteAddr().toString()
                   papeletaInstance.usuario=springSecurityService.currentUser.id
                   if (!papeletaInstance.save(flush: true)) {
                    //render(view: "create", model: [notaInstance: notaInstance])
                    //return
                    println("No se pudo guardar nada en papelta")
                }
               }
               else
               {
                   // println("Solo se va a actualizar la nota existente")
                    papeletaSave.nota="\"..."+papeletaOficialia.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"").replaceAll(", 35 y  del"," y 35 del")+"...\""
                    papeletaSave.donde="O"                   
                    if (!papeletaSave.save(flush: true)) {                   
                   // println("No se pudo actualizar la papeleta")
                }
               }
                
                
                //println("Solo va la papeleta para la Oficialia")
                if(scasolInstance.sexintere.contains("F"))
                {
                papeletaOficialia=papeletaOficialia.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y LA REGISTRADA")
                papeletaOficialia=papeletaOficialia.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y LA REGISTRADA")
                
                }
                            mapa << [
           
            //resolucion:reso,
            expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,  
            nombreoficialia:"        "+scasolInstance.ofi.toString()+""+of1.nombre,
            texto:"\"..."+papeletaOficialia.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"").replaceAll("&nbsp;","").replaceAll(", 35 y  del"," y 35 del")+"...\""+"\n\n",
            encargadojuridico:"\nComprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridicoo.titulo+" "+enjuridicoo,
            encargado:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+""+of1.nombre,
            boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap+"/"+User.get(springSecurityService.currentUser.id)
                ]
                
            }else {
                
               papeletaSave=Papeleta.findByExpanoAndExproAndDonde(scasolInstance.expano,scasolInstance.expro,"C")
               if(papeletaSave.equals(null))
               {   
                   papeletaInstance = new Papeleta()
                   papeletaInstance.nota="\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"").replaceAll(", 35 y  del"," y 35 del")+"...\""
                   papeletaInstance.expano=scasolInstance.expano
                   papeletaInstance.expro=scasolInstance.expro
                   papeletaInstance.donde="C"
                   papeletaInstance.ip=request.getRemoteAddr().toString()
                   papeletaInstance.usuario=springSecurityService.currentUser.id
                   if (!papeletaInstance.save(flush: true)) {
                    //render(view: "create", model: [notaInstance: notaInstance])
                    //return
                   // println("No se pudo guardar nada en papelta")
                }
               }
               else
               {
                    //println("Solo se va a actualizar la nota existente")
                    papeletaSave.nota="\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"").replaceAll(", 35 y  del"," y 35 del")+"...\""
                    papeletaSave.donde="C"                   
                    if (!papeletaSave.save(flush: true)) {                   
                    //println("No se pudo actualizar la papeleta")
                }
               }
                //println("Solo va la papeleta para el Archivo Central")
                            if(scasolInstance.sexintere.contains("F"))
                {
          papeletaArchivo=papeletaArchivo.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y LA REGISTRADA")
          papeletaArchivo=papeletaArchivo.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y LA REGISTRADA")
                }
            mapa << [
           
            //resolucion:reso,
            expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,  
            nombreoficialia:"                 ARCHIVO CENTRAL",
            texto:"\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"").replaceAll("&nbsp;","").replaceAll(", 35 y  del"," y 35 del")+"...\""+"\n\n",
            encargadojuridico:"Comprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridicoo.titulo+" "+enjuridicoo.toString(),
            encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString(),
            boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap+"/"+User.get(springSecurityService.currentUser.id)
                ]
                
            }
            reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                try{

            bytes = jasperService.generateReport(reportDef).toByteArray()
        }catch(Exception ex){
            println(ex.printStackTrace())
        }
        }
        
        
          
        response.addHeader("Content-Disposition", 'inline; filename="Papeleta"'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
	
        //-response.contentType='application/pdf'
	response.outputStream << bytes
        //response.outputStream << render(file: new File("c:\\reportes\\repge.pdf"), fileName: "Resolucion.pdf",contentType: "application/pdf")
        response.outputStream.flush()
	//response.out << bytes        
       
        response.outputStream.close()
        return false
    }
    
    def imprimirConsiderando()
    {

        //println(params)
        def texto2=""
        def Considerando1=""
        def Considerando2=""
        def scasolInstance
        Date fec1=new Date();     
        scasolInstance=Scasol.get(params.id)
        Considerando1=opcionService.imprimeOficialia(params.id)
        Considerando2=opcionService.imprimeArchivo(params.id)
        //println(((scasolInstance.ap2_intere.equals())?"No trae segundo apellido":"Si trae seundo apellido")+" HOLA MUNDO HOLA MUNDO")
        def compruebaConsiderando
        compruebaConsiderando=Nota.findAllByExpanoAndExpro(scasolInstance.expano,scasolInstance.expro)
        if(compruebaConsiderando){
            println("Ya hay un Considerando ???????????????????????????????????????")
        if(compruebaConsiderando.size()>1)
        {
            compruebaConsiderando.each
            {
                it.delete(flush: true)
            }
        }else {compruebaConsiderando[0].delete(flush: true)}
        }
        def espacio="                                                                                                                                                      "
        def  c=EncargadoJuridico.find("From EncargadoJuridico where activo=true")
        int longitud=(espacio.toString().length()-((enjuridico.titulo+" "+enjuridico).toString().length()))/2
         espacio=espacio.substring(0,longitud)
        //println(Considerando1)
        println("---------------------------"+enjuridico)
        //println(Considerando2)
        def compruebaNota
        def mapa=[]
        byte[] bytes
        if(!Considerando1.isEmpty() && !Considerando2.isEmpty())
        {
           def comparador=Considerando1.split("TERCERO")
           def comparador2=Considerando2.split("TERCERO") 
                   if(!comparador[1].isEmpty() && !comparador2[1].isEmpty())
        {
            println("Se va a mandar a los dos")
            if(comparador[1].equals(comparador2[1]))
            {
                compruebaNota=Nota.find("From Nota where expano=? and expro=? and idn=?", [scasolInstance.expano,scasolInstance.expro,"A".toCharacter()])
                println("Es la misma")
                //def ofi1=Scaofi.findBy
                def of1=Scaofi.findByDescrip(scasolInstance.ofi.toString())
               Considerando2=Considerando2.replaceAll("C. JEFE DEL ARCHIVO CENTRAL DEL REGISTRO CIVIL","C. OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi+""+of1.nombre+", OAXACA Y AL "+"C. JEFE DEL ARCHIVO CENTRAL DEL REGISTRO CIVIL")
               texto2="<p style='text-align: right; line-height: 1em; font-size: 8pt'>Oaxaca de Júarez Oaxaca a "+hoy2[2]+" de "+meses2[Integer.valueOf(hoy2[1])]+" de "+hoy2[0]+"</p>"+"<p>"+Considerando2.replaceAll("\n\
","</p><p>")
        texto2=texto2.replaceAll("<p>","<p style='text-align: justify; line-height: 1em; font-size: 8pt'>")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>C  O  N  S  I  D  E  R  A  N  D  O","<p style='text-align: center; line-height: 1em; font-size: 8pt'>C  O  N  S  I  D  E  R  A  N  D  O")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>R  E  S  U  E  L  V  E</p>","<p style='text-align: center; line-height: 1em; font-size: 8pt'>R  E  S  U  E  L  V  E</p>")
        texto2=texto2.replaceAll(", 35 y  del"," y 35 del")
                if(compruebaNota.equals(null))
        {
            println("Se va a crear la nota porque no existe ninguna")
            def notaInstance= new Nota()
            notaInstance.nota=texto2.replaceAll(", y 142"," y 142")
        notaInstance.expano=scasolInstance.expano
        notaInstance.expro=scasolInstance.expro
        notaInstance.idn='A'
        notaInstance.ip=request.getRemoteAddr().toString()
        notaInstance.usuario=springSecurityService.currentUser.id
        if (!notaInstance.save(flush: true)) {           
            println("No se pudo guardar nada en nota")
        }
        }
        else
        {
            println("Solo se va a actualizar la nota existente")
            compruebaNota.nota=texto2.replaceAll(", y 142"," y 142")
            if (!compruebaNota.save(flush: true)) {            
            println("No se pudo actualizar la nota")
        }
            
        }
                    
                    
                    
          Considerando2=Considerando2.replaceAll(",  y 142"," y 142")
          Considerando2=Considerando2.replaceAll(", 35 y  del"," y 35 del")
          if(scasolInstance.sexintere.contains("F"))
          {
          Considerando2=Considerando2.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y LA REGISTRADA")
          Considerando2=Considerando2.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA PADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y LA REGISTRADA")
          }
          mapa << [          
           
            exp:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano ,  
            fecha:"Oaxaca de Juárez,Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
            copia1:Considerando2.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O")
            
                ]
        

         reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
 try{

            bytes = jasperService.generateReport(reportDef).toByteArray()
            byte [] a =bytes
            
            PDFMergerUtility mergePdf = new PDFMergerUtility() 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            mergePdf.addSource(new ByteArrayInputStream(a));
            mergePdf.addSource(new ByteArrayInputStream(a));
            mergePdf.addSource(new ByteArrayInputStream(a));
           
            mergePdf.setDestinationStream(byteArrayOutputStream);
            mergePdf.mergeDocuments();
            bytes= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        }
            }
            else 
            {
                
                    
             compruebaNota=Nota.find("From Nota where expano=? and expro=? and idn=?", [scasolInstance.expano,scasolInstance.expro,"C".toCharacter()])       
texto2="<p style='text-align: right; line-height: 1em; font-size: 8pt'>Oaxaca de Júarez Oaxaca a "+hoy2[2]+" de "+meses2[Integer.valueOf(hoy2[1])]+" de "+hoy2[0]+"</p>"+"<p>"+Considerando2.replaceAll("\n\
","</p><p>")
        texto2=texto2.replaceAll("<p>","<p style='text-align: justify; line-height: 1em; font-size: 8pt'>")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","<p style='text-align: center; line-height: 1em; font-size: 8pt'>C  O  N  S  I  D  E  R  A  N  D  O")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                          R  E  S  U  E  L  V  E</p>","<p style='text-align: center; line-height: 1em; font-size: 8pt'>R  E  S  U  E  L  V  E</p>")
        if(compruebaNota.equals(null))
        {
            println("Se va a crear la nota porque no existe ninguna")
            def notaInstance= new Nota()
            notaInstance.nota=texto2.replaceAll(", y 142"," y 142")
        notaInstance.expano=scasolInstance.expano
        notaInstance.expro=scasolInstance.expro
        notaInstance.idn='C'
        notaInstance.ip=request.getRemoteAddr().toString()
        notaInstance.usuario=springSecurityService.currentUser.id
        if (!notaInstance.save(flush: true)) {
            println("No se pudo guardar nada en nota")
        }
        }
        else
        {
            println("Solo se va a actualizar la nota existente")
            compruebaNota.nota=texto2
            if (!compruebaNota.save(flush: true)) {
            println("No se pudo actualizar la nota")
        }
            
        }
        
              compruebaNota=Nota.find("From Nota where expano=? and expro=? and idn=?", [scasolInstance.expano,scasolInstance.expro,"O".toCharacter()])      
                    
                    texto2="<p style='text-align: right; line-height: 1em; font-size: 8pt'>Oaxaca de Júarez Oaxaca a "+hoy2[2]+" de "+meses2[Integer.valueOf(hoy2[1])]+" de "+hoy2[0]+"</p>"+"<p>"+Considerando1.replaceAll("\n\
","</p><p>")
        texto2=texto2.replaceAll("<p>","<p style='text-align: justify; line-height: 1em; font-size: 8pt'>")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","<p style='text-align: center; line-height: 1em; font-size: 8pt'>C  O  N  S  I  D  E  R  A  N  D  O")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                          R  E  S  U  E  L  V  E</p>","<p style='text-align: center; line-height: 1em; font-size: 8pt'>R  E  S  U  E  L  V  E</p>")
        if(compruebaNota.equals(null))
        {
            println("Se va a crear la nota porque no existe ninguna")
            def notaInstance= new Nota()
            notaInstance.nota=texto2.replaceAll(", y 142"," y 142")
        notaInstance.expano=scasolInstance.expano
        notaInstance.expro=scasolInstance.expro
        notaInstance.idn='O'
        notaInstance.ip=request.getRemoteAddr().toString()
        notaInstance.usuario=springSecurityService.currentUser.id
        if (!notaInstance.save(flush: true)) {
            //render(view: "create", model: [notaInstance: notaInstance])
            //return
            println("No se pudo guardar nada en nota")
        }
        }
        else
        {
            println("Solo se va a actualizar la nota existente")
            compruebaNota.nota=texto2.replaceAll(", y 142"," y 142")
            if (!compruebaNota.save(flush: true)) {
            //render(view: "create", model: [notaInstance: notaInstance])
            //return
            println("No se pudo actualizar la nota")
        }
            
        }
                    
                println("No es la misma")
                Considerando2=Considerando2.replaceAll(",  y 142"," y 142")
                Considerando1=Considerando1.replaceAll(",  y 142"," y 142")
                Considerando1=Considerando1.replaceAll(", 35 y  del"," y 35 del")
                if(scasolInstance.sexintere.contains("F"))
                {
          Considerando2=Considerando2.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y LA REGISTRADA")
          Considerando2=Considerando2.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y LA REGISTRADA")
          Considerando1=Considerando1.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y LA REGISTRADA")
          Considerando1=Considerando1.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y LA REGISTRADA")
          
                }
                
                mapa << [
            exp:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano ,  
            fecha:"Oaxaca de Juárez,Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
            pa2:Considerando2.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
            pa1:Considerando1.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O")
                        ]
        

         reportDef = new JasperReportDef(name: "SoloConsiderando1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
         JasperReportDef reportDef2= new JasperReportDef(name: "SoloConsiderando2.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
   try{

            bytes = jasperService.generateReport(reportDef).toByteArray()
            byte [] a =bytes
             bytes = jasperService.generateReport(reportDef2).toByteArray()
            byte [] b =bytes
            PDFMergerUtility mergePdf = new PDFMergerUtility() 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            mergePdf.addSource(new ByteArrayInputStream(a));
            mergePdf.addSource(new ByteArrayInputStream(a));
            mergePdf.addSource(new ByteArrayInputStream(b));
            mergePdf.addSource(new ByteArrayInputStream(b));
            mergePdf.setDestinationStream(byteArrayOutputStream);
            mergePdf.mergeDocuments();
            bytes= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        }
            }
        }
        }
        else
        {
            println("Solo va para uno")
            if(!Considerando1.isEmpty())
            {
                compruebaNota=Nota.find("From Nota where expano=? and expro=? and idn=?", [scasolInstance.expano,scasolInstance.expro,"O".toCharacter()])      
                    
                    texto2="<p style='text-align: right; line-height: 1em; font-size: 8pt'>Oaxaca de Júarez Oaxaca a "+hoy2[2]+" de "+meses2[Integer.valueOf(hoy2[1])]+" de "+hoy2[0]+"</p>"+"<p>"+Considerando1.replaceAll("\n\
","</p><p>")
        texto2=texto2.replaceAll("<p>","<p style='text-align: justify; line-height: 1em; font-size: 8pt'>")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","<p style='text-align: center; line-height: 1em; font-size: 8pt'>C  O  N  S  I  D  E  R  A  N  D  O")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                          R  E  S  U  E  L  V  E</p>","<p style='text-align: center; line-height: 1em; font-size: 8pt'>R  E  S  U  E  L  V  E</p>")
        
                if(compruebaNota.equals(null))
        {
            println("Se va a crear la nota porque no existe ninguna")
            def notaInstance= new Nota()
            notaInstance.nota=texto2.replaceAll(", y 142"," y 142")
        notaInstance.expano=scasolInstance.expano
        notaInstance.expro=scasolInstance.expro
        notaInstance.idn='O'
        notaInstance.ip=request.getRemoteAddr().toString()
        notaInstance.usuario=springSecurityService.currentUser.id
        if (!notaInstance.save(flush: true)) {
           
            println("No se pudo guardar nada en nota")
        }
        }
        else
        {
            println("Solo se va a actualizar la nota existente")
            compruebaNota.nota=texto2.replaceAll(", y 142"," y 142")
            if (!compruebaNota.save(flush: true)) {
            
            println("No se pudo actualizar la nota")
        }
            
        }
                println("Solo va para la Oficialia")
                
                Considerando1=Considerando1.replaceAll(",  y 142"," y 142")
                if(scasolInstance.sexintere.contains("F"))
                {
          Considerando1=Considerando1.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y LA REGISTRADA")
          Considerando1=Considerando1.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y LA REGISTRADA","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y LA REGISTRADA")
          
                }
                mapa << [
            exp:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano ,  
            fecha:"Oaxaca de Juárez,Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
            copia1:Considerando1.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O")
                        ]
        

         
            }else
            {
                             compruebaNota=Nota.find("From Nota where expano=? and expro=? and idn=?", [scasolInstance.expano,scasolInstance.expro,"C".toCharacter()])       
texto2="<p style='text-align: right; line-height: 1em; font-size: 8pt'>Oaxaca de Júarez Oaxaca a "+hoy2[2]+" de "+meses2[Integer.valueOf(hoy2[1])]+" de "+hoy2[0]+"</p>"+"<p>"+Considerando2.replaceAll("\n\
","</p><p>")
        texto2=texto2.replaceAll("<p>","<p style='text-align: justify; line-height: 1em; font-size: 8pt'>")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","<p style='text-align: center; line-height: 1em; font-size: 8pt'>C  O  N  S  I  D  E  R  A  N  D  O")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                          R  E  S  U  E  L  V  E</p>","<p style='text-align: center; line-height: 1em; font-size: 8pt'>R  E  S  U  E  L  V  E</p>")
        //println(guardatexto1)
                if(compruebaNota.equals(null))
        {
            println("Se va a crear la nota porque no existe ninguna")
            def notaInstance= new Nota()
            notaInstance.nota=texto2.replaceAll(", y 142"," y 142")
        notaInstance.expano=scasolInstance.expano
        notaInstance.expro=scasolInstance.expro
        notaInstance.idn='C'
        notaInstance.ip=request.getRemoteAddr().toString()
        notaInstance.usuario=springSecurityService.currentUser.id
        if (!notaInstance.save(flush: true)) {
            println("No se pudo guardar nada en nota")
        }
        }
        else
        {
            println("Solo se va a actualizar la nota existente")
            compruebaNota.nota=texto2.replaceAll(", y 142"," y 142")
            if (!compruebaNota.save(flush: true)) {
            println("No se pudo actualizar la nota")
        }
            
        }
                println("Solo va para el Archivo Central")
                Considerando2=Considerando2.replaceAll(", y 142"," y 142")
                if(scasolInstance.sexintere.contains("F"))
                {
          Considerando2=Considerando2.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE EL PADRE Y LA REGISTRADA")
          Considerando2=Considerando2.replaceAll("EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y EL REGISTRADO","EN VIRTUD DE QUE UNICAMENTE EXISTE RELACION DE FILIACION ENTRE LA MADRE Y LA REGISTRADA")
          
                }
                                mapa << [
           
            //resolucion:reso,
            exp:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano ,  
            fecha:"Oaxaca de Juárez,Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
            copia1:Considerando2.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O")
            
                ]
            }
            println("aqui entra----");
            reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
             try{

            bytes = jasperService.generateReport(reportDef).toByteArray()
            byte [] a =bytes
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
        }
             
        response.addHeader("Content-Disposition", 'inline; filename="Considerando"'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return false
        
    }
    
    
    
         def imprimirNegativa(){
       println (params)
        println("Aqui vamos a imprimir todo el texto de nuevo")
        def textonegativo
        def scasolInstance=Scasol.get(params.id)
        textonegativo=opcionService.negativa(scasolInstance.expro.toString(),scasolInstance.expano.toString())
        
        //def texto1=params.nota

        //def 
        def mapacompleto=[]
        def mapapendiente=[]
                byte[] bytes
        

                                  mapapendiente << [
                                      fecha:"Oaxaca de Juárez, Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0]+"\n\
                          \n\
Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano+"\n\
Resolución Negativa de Aclaración",
                                      texto:textonegativo,
                                      usuario:User.get(springSecurityService.currentUser.id).toString(),
                                      persona:enjuridico.titulo+" "+enjuridico.toString()
]

            
            
        try{
            HashMap<String, Object> params = new HashMap<String, Object>()
            params.put("logo", servletContext.getRealPath("/reports/"))
            
            def reportDef = new JasperReportDef(name: "Negativa.jasper",parameters:params ,fileFormat:JasperExportFormat.PDF_FORMAT, reportData  :  mapapendiente )
       
         mapacompleto = jasperService.generateReport(reportDef).toByteArray()

        }catch(Exception ex){
            println(ex.printStackTrace())
        }
        response.addHeader("Content-Disposition", 'inline; filename=Negativa.pdf')
        response.contentType='application/pdf'
    	response.outputStream << mapacompleto
        response.outputStream.close()
        response.outputStream.flush()
        return false

      
    }
    
    
def imprimirFinal(){
      
      if(!isLoggedIn())
        {
            println("No Esta logeado")
            redirect(controller: "Logout")
            return
        }
      println(params)
      def usuario=User.get(params.usuario1.toString())
      def usr=""
      try
      {
          usr+=usuario.nombre.substring(0,1)
      }catch(Exception e){}
            try
      {
          usr+=usuario.apellpa.substring(0,1)
      }catch(Exception e){}
            try
      {
          usr+=usuario.apellma.substring(0,1)
      }catch(Exception e){}
      println(usr +"Aqui van las iniciales del dictaminar vamos a ver que onda")
        println(usuario.toString() +" Vamos a ver si se envia el usuario o no ")
         def scasolInstance=Scasol.get(params.id)
         def espacio="                                                                                                                                                      "
        def  enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")
        int longitud=(espacio.toString().length()-((enjuridico.titulo+" "+enjuridico).toString().length()))/2
        espacio=espacio.substring(0,longitud)
        println(hoy2[2])
        println(meses2[Integer.parseInt(hoy2[1])])
        println(hoy2[0])
     
        String [] fec =scasolInstance.fchact.toString().split(" ")
        println(scasolInstance.fchact.toString()+" /*--*//*---*///*---*//")
        String [] fech=fec[0].split("-")
        
               new File("D:/codigo.png").withOutputStream { out ->
    barcode4jService.render("code39Generator", scasolInstance.expro+"/"+scasolInstance.expano, out, "image/png")
}  

        
        def texto1=opcionService.imprimeOficialia(params.id)
        //def texto2=saluda2(params.id)
        def texto2
        try{
            texto2=opcionService.imprimeArchivo(params.id)
        }catch(Exception e)
        {
            texto2=opcionService.imprimeOficialia(params.id)
        }
        System.out.println("texto2---"+texto2)
        if(texto2.length()>3){
         texto2=texto2.substring(0,(texto2.length()-3))
        }
        def guardatexto1="<p style='text-align: right; line-height: 1em; font-size: 8pt'>Oaxaca de Júarez Oaxaca a "+fech[2]+" de "+meses2[Integer.valueOf(fech[1])]+" de "+fech[0]+"</p>"+"<p>"+texto2.replaceAll("\n\
","</p><p>")
       /* println(texto2.replaceAll("\n\
","</p><p>"))*/
        guardatexto1=guardatexto1.replaceAll("<p>","<p style='text-align: justify; line-height: 1em; font-size: 8pt'>")
        guardatexto1=guardatexto1.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","<p style='text-align: center; line-height: 1em; font-size: 8pt'>C  O  N  S  I  D  E  R  A  N  D  O")
        guardatexto1=guardatexto1.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                          R  E  S  U  E  L  V  E</p>","<p style='text-align: center; line-height: 1em; font-size: 8pt'>R  E  S  U  E  L  V  E</p>")
        println(guardatexto1)        
        def compruebaNota=Nota.findByExpanoAndExpro(scasolInstance.expano,scasolInstance.expro)
        if(compruebaNota.equals(null))
        {
            println("Se va a crear la nota porque no existe ninguna")
            def notaInstance= new Nota()
            notaInstance.nota=guardatexto1
        notaInstance.expano=scasolInstance.expano
        notaInstance.expro=scasolInstance.expro
        notaInstance.ip=request.getRemoteAddr().toString()
        notaInstance.usuario=springSecurityService.currentUser.id
        if (!notaInstance.save(flush: true)) {
            //render(view: "create", model: [notaInstance: notaInstance])
            //return
            println("No se pudo guardar nada en nota")
        }
        }
        else
        {
            println("Solo se va a actualizar la nota existente")
            compruebaNota.nota=guardatexto1
            if (!compruebaNota.save(flush: true)) {
            //render(view: "create", model: [notaInstance: notaInstance])
            //return
            println("No se pudo actualizar la nota")
        }
            
        }
        
        
        def papeleta1
        def mapa=[] 
        def bytes=[]
       // def reportDef
         //println(texto1)
         println("------------------------")
         //println(texto2)
         if(!texto1.isEmpty() && !texto2.isEmpty())
         {
             println("Se va a mandar a los dos")
             def comparador=texto1.split("TERCERO")
             def comparador2=texto2.split("TERCERO")
             println(comparador.length+" ñññññññññññññññññññ")
             println(comparador2.length+" ñññññññññññññññññññ")
             println()
           //  println(comparador[1])
            // println(comparador2[1])
                   if(comparador[1].equals(comparador2[1]))
                    {
                    println("Son las mismas")
                    def remite=""
                    remite=texto1.substring(texto1.indexOf("TERCERO.- Remítase copia de esta resolucón al"),texto1.indexOf("para que después de haber hecho la anotación correspondiente"))
                    println(remite)
                    texto1=texto1.replaceAll(remite.toString(),remite.toString().substring(0,remite.toString().length()-2)+" y AL C.JEFE DEL ARCHIVO CENTRAL DEL REGISTRO CIVIL, ")
                    //println(texto1.substring(texto1.indexOf("TERCERO.- Remítase copia de esta resolucón al"),texto1.indexOf("para que después de haber hecho la anotación correspondiente\n\
                    //println(texto1)
                    papeleta1=texto1.split("\\.\\.\\.")
                    println(papeleta1.length+" -------- Aqui va el tamaño de la papeleta")
                    println(papeleta1[1]+" ------------ Aqui va lo que tiene la papeleta en pa posicion 1")                   
                                       
                    mapa << [
                    exp:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"",
                    fecha:"Oaxaca de Juárez,Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
                    copia1:texto1.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    copia2:texto1.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    copia3:texto1.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    expediente2:"Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano+"     "+scasolInstance.ofi+", "+scasolInstance.dto,
                    texto2:"\"..."+papeleta1[1].replaceAll("&nbsp;","")+"...\"\n\
\n\
\n\
\n\
Comprobante pago de derechos:\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id),
                    expediente:"Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano+"      ARCHIVO CENTRAL",
                    oficialia:"EL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+", "+scasolInstance.dto,
                    texto:"\"..."+papeleta1[1].replaceAll("&nbsp;","")+"...\"\n\
\n\
\n\
\n\
Comprobante pago de derechos:\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id),
                    encargadojuridico:"EL JEFE DE LA UNIDAD JURIDICA.\n\
\n\
\n\
"+enjuridico.titulo+" "+enjuridico,
                    encargado:"EL JEFE DEL ARCHIVO CENTRAL.\n\
\n\
\n\
"+enarchivo,
                    boleta:"** Esta papeleta solamente el valida adherida al libro original **"
                       
                             ]
                    def dondemando=3
                    println("aqui....");
                   reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   JasperReportDef copia2 = new JasperReportDef(name: "imprimirTodo/copia2.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   JasperReportDef copia3 = new JasperReportDef(name: "imprimirTodo/copia3.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   JasperReportDef segundoTexto = new JasperReportDef(name: "imprimirTodo/texto3.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   JasperReportDef texto = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                try{
                       bytes = jasperService.generateReport(reportDef).toByteArray()
                       byte [] a =bytes
                       bytes = jasperService.generateReport(copia2).toByteArray()
                       byte [] b =bytes
                        bytes = jasperService.generateReport(copia3).toByteArray()
                       byte [] c =bytes
                        bytes = jasperService.generateReport(segundoTexto).toByteArray()
                       byte [] d =bytes
                           bytes = jasperService.generateReport(texto).toByteArray()
                       byte [] e =bytes
                       PDFMergerUtility mergePdf = new PDFMergerUtility() 
                       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           mergePdf.addSource(new ByteArrayInputStream(a));
           mergePdf.addSource(new ByteArrayInputStream(b));
            mergePdf.addSource(new ByteArrayInputStream(c));
             mergePdf.addSource(new ByteArrayInputStream(d));
              mergePdf.addSource(new ByteArrayInputStream(e));
           mergePdf.setDestinationStream(byteArrayOutputStream);
           mergePdf.mergeDocuments();
                       bytes= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        } 
             
        //render("Hola mundo")
        //println(mapa)
       
        //response.addHeader("Content-Disposition", 'attachment; filename="Exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
	response.addHeader("Content-Disposition", 'inline; filename="Exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
	
        //-response.contentType='application/pdf'
	response.outputStream << bytes
        //response.outputStream << render(file: new File("c:\\reportes\\repge.pdf"), fileName: "Resolucion.pdf",contentType: "application/pdf")
        response.outputStream.flush()
	//response.out << bytes
        
        //println("antes de cerrar")
        response.outputStream.close()
        return

                    }
                    else 
                    {
                    println("Vas a generar mas de una")
                        
                    papeleta1=texto1.split("\\.\\.\\.")
                    def papeleta2=texto2.split("\\.\\.\\.")
                    println(papeleta1.length+" -------- Aqui va el tamaño de la papeleta")
                    println(papeleta1[1]+" ------------ Aqui va lo que tiene la papeleta en pa posicion 1")                   
                    //mapa=[]                    
                    mapa << [
                    exp:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"",
                    fecha:"Oaxaca de Juárez,Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
                    copia1:texto1.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    copia2:texto1.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    copia3:texto2.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    expediente2:"Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano+"     "+scasolInstance.ofi+", "+scasolInstance.dto,
                    texto2:"\"..."+papeleta1[1].replaceAll("&nbsp;","")+"...\"\n\
\n\
Comprobante pago de derechos:",
                    expediente:"Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano+"      ARCHIVO CENTRAL",
                    oficialia:"EL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+", "+scasolInstance.dto,
                    texto:"\"..."+papeleta2[1].replaceAll("&nbsp;","")+"...\"\n\
\n\
Comprobante pago de derechos:",
                    encargadojuridico:"EL JEFE DE LA UNIDAD JURIDICA.\n\
\n\
\n\
"+enjuridico.titulo+" "+enjuridico,
                    encargado:"EL JEFE DEL ARCHIVO CENTRAL.\n\
\n\
\n\
"+enarchivo,
                    boleta:"** Esta papeleta solamente el valida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap+"/"+User.get(springSecurityService.currentUser.id)
                       
                             ]                  
                                       mapa << [
                    exp:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"",
                    fecha:"Oaxaca de Juárez,Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
                    copia1:texto1.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    copia2:texto1.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    copia3:texto2.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    expediente2:"Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano+"     "+scasolInstance.ofi+", "+scasolInstance.dto,
                    texto2:"\"..."+papeleta1[1].replaceAll("&nbsp;","")+"...\"\n\
\n\
Comprobante pago de derechos:",
                    expediente:"Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano+"      ARCHIVO CENTRAL",
                    oficialia:"EL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+", "+scasolInstance.dto,
                    texto:"\"..."+papeleta1[1].replaceAll("&nbsp;","")+"...\"\n\
\n\
Comprobante pago de derechos:",
                    encargadojuridico:"EL JEFE DE LA UNIDAD JURIDICA.\n\
\n\
\n\
"+enjuridico.titulo+" "+enjuridico,
                    encargado:"EL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+", "+scasolInstance.dto,
                    boleta:"** Esta papeleta solamente el valida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap+"/"+User.get(springSecurityService.currentUser.id)
                       
                             ]   
                    def dondemando=3
                     reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   JasperReportDef copia2 = new JasperReportDef(name: "imprimirTodo/copia2.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   JasperReportDef copia3 = new JasperReportDef(name: "imprimirTodo/copia3.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   JasperReportDef segundoTexto = new JasperReportDef(name: "imprimirTodo/texto3.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   JasperReportDef texto = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                try{
            //FileUtils.writeByteArrayToFile(tempToPrint, jasperService.generateReport(reportDef).toByteArray())
           // println("se guardo en el disco")
                        bytes = jasperService.generateReport(reportDef).toByteArray()
                       byte [] a =bytes
                       bytes = jasperService.generateReport(copia2).toByteArray()
                       byte [] b =bytes
                        bytes = jasperService.generateReport(copia3).toByteArray()
                       byte [] c =bytes
                        bytes = jasperService.generateReport(segundoTexto).toByteArray()
                       byte [] d =bytes
                           bytes = jasperService.generateReport(texto).toByteArray()
                       byte [] e =bytes
                       PDFMergerUtility mergePdf = new PDFMergerUtility() 
                       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           mergePdf.addSource(new ByteArrayInputStream(a));
           mergePdf.addSource(new ByteArrayInputStream(b));
            mergePdf.addSource(new ByteArrayInputStream(c));
            mergePdf.addSource(new ByteArrayInputStream(c));
             mergePdf.addSource(new ByteArrayInputStream(d));
              mergePdf.addSource(new ByteArrayInputStream(e));
           mergePdf.setDestinationStream(byteArrayOutputStream);
           mergePdf.mergeDocuments();
                       bytes= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return
                    }
         }
         else 
         {
            if(!texto1.isEmpty())
            {
             println("Solo va para la oficialia")
             papeleta1=texto1.split("\\.\\.\\.")
                    println(papeleta1.length+" -------- Aqui va el tamaño de la papeleta")
                    println(papeleta1[1]+" ------------ Aqui va lo que tiene la papeleta en pa posicion 1") 
                    mapa << [
                    exp:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"",
                    fecha:"Oaxaca de Juárez,Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
                    copia1:texto1.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    copia2:texto1.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    copia3:texto1.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    expediente2:"",
                    texto2:"",
                    expediente:"Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano+"      "+scasolInstance.ofi+", "+scasolInstance.dto,
                    oficialia:"",
                    texto:"\"..."+papeleta1[1].replaceAll("&nbsp;","")+"...\"\n\
\n\
\n\
\n\
Comprobante pago de derechos:\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id),
                    encargadojuridico:"EL JEFE DE LA UNIDAD JURIDICA.\n\
\n\
\n\
"+enjuridico.titulo+" "+enjuridico,
                    encargado:"EL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi+", "+scasolInstance.dto,
                    boleta:"** Esta papeleta solamente el valida adherida al libro original **"
                       
                             ]
                            reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   JasperReportDef copia2 = new JasperReportDef(name: "imprimirTodo/copia2.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   JasperReportDef texto = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                try{
                       bytes = jasperService.generateReport(reportDef).toByteArray()
                       byte [] a =bytes
                       bytes = jasperService.generateReport(copia2).toByteArray()
                       byte [] b =bytes
                           bytes = jasperService.generateReport(texto).toByteArray()
                       byte [] e =bytes
                       PDFMergerUtility mergePdf = new PDFMergerUtility() 
                       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           mergePdf.addSource(new ByteArrayInputStream(a));
           mergePdf.addSource(new ByteArrayInputStream(b));
            mergePdf.addSource(new ByteArrayInputStream(e));
           mergePdf.setDestinationStream(byteArrayOutputStream);
           mergePdf.mergeDocuments();
                       bytes= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return
            }
            else {
             println("Solo va para el archivo central")
                    papeleta1=texto2.split("\\.\\.\\.")
                    println(papeleta1.length+" -------- Aqui va el tamaño de la papeleta")
                    println(papeleta1[1]+" ------------ Aqui va lo que tiene la papeleta en pa posicion 1")  
                    mapa << [
                    exp:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"",
                    fecha:"Oaxaca de Juárez,Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
                    copia1:texto2.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    copia2:texto2.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    copia3:texto2.replaceAll(("                                                                                        "+enjuridico.titulo+" "+enjuridico), (espacio+enjuridico.titulo+" "+enjuridico)).replaceAll("                                                                                                          R  E  S  U  E  L  V  E","                                                                   R  E  S  U  E  L  V  E").replaceAll("                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","                                                            C  O  N  S  I  D  E  R  A  N  D  O"),
                    expediente2:"",
                    texto2:"",
                    expediente:"Expediente: "+scasolInstance.expro+"/"+scasolInstance.expano+"      ARCHIVO CENTRAL",
                    oficialia:"",
                    texto:"\"..."+papeleta1[1].replaceAll("&nbsp;","")+"...\"\n\
\n\
\n\
\n\
Comprobante pago de derechos:\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id),
                    encargadojuridico:"EL JEFE DE LA UNIDAD JURIDICA.\n\
\n\
\n\
"+enjuridico.titulo+" "+enjuridico,
                    encargado:"EL JEFE DEL ARCHIVO\n\
"+enarchivo.titulo+" "+enarchivo,
                    boleta:"** Esta papeleta solamente el valida adherida al libro original **"
                       
                             ]
                           reportDef = new JasperReportDef(name: "imprimirTodo/copia1.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   JasperReportDef copia2 = new JasperReportDef(name: "imprimirTodo/copia2.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                   JasperReportDef texto = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                try{
                       bytes = jasperService.generateReport(reportDef).toByteArray()
                       byte [] a =bytes
                       bytes = jasperService.generateReport(copia2).toByteArray()
                       byte [] b =bytes
                           bytes = jasperService.generateReport(texto).toByteArray()
                       byte [] e =bytes
                       PDFMergerUtility mergePdf = new PDFMergerUtility() 
                       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           mergePdf.addSource(new ByteArrayInputStream(a));
           mergePdf.addSource(new ByteArrayInputStream(b));
            mergePdf.addSource(new ByteArrayInputStream(e));
           mergePdf.setDestinationStream(byteArrayOutputStream);
           mergePdf.mergeDocuments();
                       bytes= byteArrayOutputStream.toByteArray();
        }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return
            }
            
         }
                  

    }
    
    
    
    
    
    
    
     
    

    
    
    
    
    
    
    
   
    
     def imprimir(){
      
        artmat="" 
        artmat2=""
        legitimacion=""
        reconocimiento=""
        println(params.id+" hdasdasd") 
        def scasolInstance=Scasol.get(params.id)
        def fsol=scasolInstance.fechasol.toString().substring(0,10).split("-")
        println(fsol.length.toString()+"Aqui va la longitud del arreglo") 
      
        
      //String [] meses2=["","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"]
      def errorescivil=[0,0,0,0,0,0,0].toArray()
      def pa1=""
      pa1+="Visto el escrito presentado con fecha "+fsol[2]+" de "+meses2[Integer.parseInt(fsol[1])] +" de "+fsol[0]+" y la(s) copia(s) del Acta"+
        " que contiene el(los) error(es) que se menciona(n), y demás documentos que se acompañan, se tiene"+
        " a "
        //println(pa1)
        //println("********")
      def pa2="\n\
\n\
SEGUNDO.- En la especie, el promovente solicita la aclaración del Acta antes mencionada, aduciendo que existen los siguientes errores: "  
      def pa3=""
      def erroresreglamento=[0,0,0,0,0,0,0,0,0,0].toArray()
      def articulo68=[0,0,0,0,0,0,0,0,0,0].toArray()
      def articulo68l=["I","II","III","IV","V","VI","VII","VIII","IX","X"].toArray()
      def apli71=[0,0].toArray()
      def favore
      def conterrores=1
      def promover
      def erroreacta=""
      def lugar=""
      def errart71=""
      def aclarados=""
      def listaerr=""
      def textocompleto=""
      def testarofi=""
      
      def listaScaerr=Scaerr.findAllByExproAndProcede(params.id,"SI")
      def listaScaact=Scaact.findAllByExpproAndProcede(params.id,"SI")
      def listOpciones=Opcion.findAllByExapro(params.id)
       //listaScaact=Scaact.findAllByExppro(4)
     /*Date fec1=new Date();
     def fech1=fec1.toTimestamp() 
     String [] hoy=fech1.toString().split(" ")
     String [] hoy2=hoy[0].toString().split("-")*/
     
        String [] fec =scasolInstance.fchact.toString().split(" ")
        println(scasolInstance.fchact.toString()+" /*--*//*---*///*---*//")
        String [] fech=fec[0].split("-")
        
        String [] fecha1 =scasolInstance.fchsol.toString().split(" ")
        println(scasolInstance.fchsol.toString()+" /*--*//*---*///*---*//")
        String [] fech2=fecha1[0].split("-")
        
      if(listaScaerr.equals()){println("no tiene errores agregados")}
      else
      {
          def fieldsInstance
                   
          println("tiene: "+listaScaerr.size()+" errores")
          for(int i=0;i<listaScaerr.size();i++)
          {
              if( listaScaerr.campo[i].toString().contains("LUGAR DE REGISTRO") ||
                  listaScaerr.campo[i].toString().contains("LUGAR DE NACIMIENTO") ||
                  listaScaerr.campo[i].toString().contains("FECHA DE NACIMIENTO") ||
                  listaScaerr.campo[i].toString().contains("FECHA DE REGISTRO")
              )
              {
                  erroresreglamento[6]=1
              }
                if(listaScaerr.contiene[i].toString().equals("*") && listaScaerr.campo[i].equals())
                //if(listaScaerr.contiene[i].toString().equals("*"))
                {
                    println("encontro un testar de oficio °°°°°°°°°°°°°°°°")
                    println(listaScaerr.tcorrect[i].toString()+" Esta es la persona")
                    erroreacta+="CARECE(N) DE VALIDEZ LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+". "
                    //listaerr+=conterrores+".-"+"LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" "+listaScaerr.debeser[i].toString()+" POR LO QUE SE PROCEDE A TESTAR DE OFICIO"
                    listaerr+=conterrores+".-CARECE(N) DE VALIDEZ LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" POR LO QUE SE PROCEDE A TESTAR DE OFICIO.\n\
"
                    
                    aclarados+="CON FUNDAMENTO EN "+listaScaerr.base[i].toString()+" SE AUTORIZA TESTAR DE OFICIO LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" "+listaScaerr.debeser[i].toString()+".-"
                }else{
                    
                    if(listaScaerr.campo[i].toString().contains("EL SEXO"))
                    {
                        println("Vamos a ocuapar el 68 fraccion III")
                        //articulo68[2]=1
                    }
                    if(listaScaerr.campo[i].toString().contains("APELLIDO"))
                    {
                        println("Vamos a ocuapar el 68 fraccion V")
                       // articulo68[4]=1
                    }
               erroreacta+=listaScaerr.tcorrect[i].toString()+" DE "+listaScaerr.campo[i].toString()+". "
                  println(scasolInstance.typact)
                  println(listaScaerr[i].campo.toString())
                  println(scasolInstance.sexintere)
                  println(scasolInstance.typact.toString()+", "+listaScaerr[i].campo.toString()+" "+scasolInstance.sexintere.toString()+" }{}{}{}{}{}{}{}{}{}{}{}{}{´+´+´+´+´+´+´+")
                fieldsInstance=Fields.findByActaAndNombre(scasolInstance.typact,listaScaerr.campo[i].toString())
                //fieldsInstance=Fields.get(listaScaerr[i].campo)
               // fieldsInstance=Fields.find("From fields where acta=? and nombre=? and sexo=?",[scasolInstance.typact, listaScaerr[i].campo.toString(),scasolInstance.sexintere])
                    println(fieldsInstance)
                    //println(scasolInstance.typact+" "+listaScaerr.campo[i].toString()) 
                println (fieldsInstance.tipo)
                    if(fieldsInstance.tipo.toString().equals("N"))
                    {
                    erroresreglamento[9]=1
                    }
                
                println(listaScaerr.tcorrect[i])
                println(listaScaerr.tcorrect[i].toString()+" tcorrect")
                println(listaScaerr.terror[i])
                println(listaScaerr.campo[i].toString()+" Campo")
                
                if(listaScaerr.campo[i].toString().contains("EL REGIMEN"))
                {
                    artmat="y 206"
                }
                if((listaScaerr.campo[i].toString().contains("EL NOMBRE DEL CONTRAYENTE") || (listaScaerr.campo[i].toString().contains("EL NOMBRE DE LA CONTRAYENTE"))) && listaScaerr.base[i].contains("ACTA") && scasolInstance.typact.toString().equals("MATRIMONIO"))
                {   
                    println("Paso por la 100 fraccion 1 busca el error")
                    artmat2="100 Fraccion I, "
                }
              
                if(listaScaerr.tcorrect[i].toString().equals("OMISION"))
                {
                    
                    listaerr+=conterrores+".-"+listaScaerr.tcorrect[i]+" DE "+listaScaerr.campo[i]+" Y DE ACUERDO A "+listaScaerr.base[i]+", LO CORRECTO DEBE SER: "+listaScaerr.debeser[i].toString()+""+" \n\
"
                }
                else
                {
                     if(listaScaerr.tcorrect[i].toString().equals("ACLARACION POR USO"))
                {
                    errorescivil[6]=1
                    println("va a hacer aclaracion por uso")
                    aclarados+="SE AGREGA "+listaScaerr.campo[i]+" POR SIMPLE USO SIN CREAR FILIACION Y EL NOMBRE COMPLETO DEBE SER: "+listaScaerr.debeser[i].toString()+".-"
                    listaerr+=conterrores+".-"+listaScaerr.tcorrect[i]+" DE "+listaScaerr.campo[i]+" DEBIDO A "+listaScaerr.base[i]+" "+listaScaerr.debeser[i].toString()+""+" \n\
"
                    
                }else {
                    if(listaScaerr.base[i].toString().contains("APELLIDO POR SIMPLE USO"))
                    {
                        println("Agrega apellido por simple uso")
                        errorescivil[6]=1
                        listaerr+=conterrores+".-"+listaScaerr.tcorrect[i]+" DE "+listaScaerr.campo[i]+", TENEMOS QUE APARECE ASENTADO(A) COMO "+listaScaerr.contiene[i]+" Y DE ACUERDO A "+listaScaerr.base[i]+" "+listaScaerr.debeser[i].toString()+" \n\
"                   
                        conterrores++
                        if(listaScaerr.base[i].toString().contains("Y EL SEGUNDO SE APLICA DE ACUERDO AL ARTÍCULO 141"))
                        {
                            aclarados+="SE AGREGA EL SEGUNDO APELLIDO POR SIMPLE USO SIN CREAR FILIACION Y EL NOMBRE COMPLETO DEBE SER: "+listaScaerr.debeser[i].toString()+".-"
                    
                        }else{
                            aclarados+="SE AGREGA EL PRIMER APELLIDO POR SIMPLE USO SIN CREAR FILIACION Y EL NOMBRE COMPLETO DEBE SER: "+listaScaerr.debeser[i].toString()+".-"
                    
                                    
                        }
                        continue
                    }
                   // if(listaScaerr.tcorrect[i].toString().equals("ACLARACION POR USO"))
                   // continue
                    
                    listaerr+=conterrores+".-"+listaScaerr.tcorrect[i]+" DE "+listaScaerr.campo[i]+", TENEMOS QUE APARECE ASENTADO(A) COMO "+listaScaerr.contiene[i]+" Y DE ACUERDO A "+listaScaerr.base[i]+", LO CORRECTO DEBE SER: "+listaScaerr.debeser[i].toString()+" \n\
"                   }
                }
                 
                              
                if(listaScaerr.tcorrect[i].toString().equals("OMISION")){println("Tiene una omision")
                    errorescivil[4]=1 
                    erroresreglamento[6]=1}               
                if(listaScaerr.tcorrect[i].toString().equals("OMISION PARCIAL")){println("Tiene omision parcial")
                    errorescivil[4]=1 
                    erroresreglamento[6]=1}
                if(listaScaerr.tcorrect[i].toString().equals("ERROR ORTOGRAFICO")){println("Tiene error ortografico")
                    errorescivil[0]=1
                    erroresreglamento[0]=1
                }
                if(listaScaerr.tcorrect[i].toString().equals("ERROR LINGÜÍSTICO")){errorescivil[0]=1 
                    erroresreglamento[1]=1}
                if(listaScaerr.tcorrect[i].toString().equals("ERROR MECANOGRÁFICO")){errorescivil[2]=1 
                    erroresreglamento[3]=1}
                if(listaScaerr.tcorrect[i].toString().equals("INVERSIÓN DE APELLIDO")){errorescivil[3]=1 
                    erroresreglamento[5]=1}
                if(listaScaerr.tcorrect[i].toString().equals("CONTRADICCIÓN DE NOMBRE(S)")){errorescivil[3]=1 
                    erroresreglamento[4]=1}
                if(listaScaerr.tcorrect[i].toString().equals("ERROR DE ESCRITURA")){println("Tiene error de escritura")
                    errorescivil[2]=1 
                    erroresreglamento[3]=1}
                if(listaScaerr.terror[i].toString().equals("DATO ILEGIBLE")){errorescivil[2]=1 
                    erroresreglamento[3]=1}
                if(/*listaScaerr.campo[i].toString().equals("EL NOMBRE DEL REGISTRADO") ||*/ listaScaerr.campo[i].toString().equals("EL APELLIDO PATERNO DEL REGISTRADO") || listaScaerr.campo[i].toString().equals("EL APELLIDO MATERNO DEL REGISTRADO") ){erroresreglamento[7]=1}
                if(listaScaerr.campo[i].toString().equals("LA FECHA DE NACIMIENTO DEL REGISTRADO") || listaScaerr.campo[i].toString().equals("EL LUGAR DE NACIMIENTO DEL REGISTRADO") ){erroresreglamento[6]=1}            
                if(listaScaerr.campo[i].toString().equals("EL NOMBRE DEL PADRE DEL REGISTRADO") || listaScaerr.campo[i].toString().equals("EL APELLIDO PATERNO DEL PADRE DEL REGISTRADO") || listaScaerr.campo[i].toString().equals("EL APELLIDO MATERNO DEL PADRE DEL REGISTRADO") ){erroresreglamento[8]=1}
                if(listaScaerr.campo[i].toString().equals("EL NOMBRE DE LA MADRE DEL REGISTRADO") || listaScaerr.campo[i].toString().equals("EL APELLIDO PATERNO DE LA MADRE DEL REGISTRADO") || listaScaerr.campo[i].toString().equals("EL APELLIDO MATERNO DE LA MADRE DEL REGISTRADO") ){erroresreglamento[8]=1}
                if(/*listaScaerr.campo[i].toString().equals("EL NOMBRE DEL REGISTRADO") ||*/ listaScaerr.campo[i].toString().equals("EL APELLIDO PATERNO DE LA REGISTRADA") || listaScaerr.campo[i].toString().equals("EL APELLIDO MATERNO DE LA REGISTRADA") ){erroresreglamento[7]=1}
                if(listaScaerr.campo[i].toString().equals("LA FECHA DE NACIMIENTO DE LA REGISTRADA") || listaScaerr.campo[i].toString().equals("EL LUGAR DE NACIMIENTO DE LA REGISTRADA") ){erroresreglamento[6]=1}            
                if(listaScaerr.campo[i].toString().equals("EL NOMBRE DEL PADRE DE LA REGISTRADA") || listaScaerr.campo[i].toString().equals("EL APELLIDO PATERNO DEL PADRE DE LA REGISTRADA") || listaScaerr.campo[i].toString().equals("EL APELLIDO MATERNO DEL PADRE DE LA REGISTRADA") ){erroresreglamento[8]=1}
                if(listaScaerr.campo[i].toString().equals("EL NOMBRE DE LA MADRE DE LA REGISTRADA") || listaScaerr.campo[i].toString().equals("EL APELLIDO PATERNO DE LA MADRE DE LA REGISTRADA") || listaScaerr.campo[i].toString().equals("EL APELLIDO MATERNO DE LA MADRE DE LA REGISTRADA") ){erroresreglamento[8]=1}
                if(!listaScaerr.tcorrect[i].toString().equals("ACLARACION POR USO"))
                {
                    //aclarados+=listaScaerr.campo[i].toString()+" : "+listaScaerr.debeser[i].toString()+".- "
                     if((listaScaerr.campo[i].toString().contains("NOMBRE DE LA MADRE") || 
                        listaScaerr.campo[i].toString().contains("NOMBRE DEL PADRE")) && listaScaerr.base[i].toString().contains("ACTA DE NACIMIENTO") && scasolInstance.typact.toString().equals("NACIMIENTO"))
                    {
                        aclarados+=listaScaerr.campo[i].toString()+" EN BASE A SU ACTA DE NACIMIENTO: "+listaScaerr.debeser[i].toString()+".- "
                    }
                    else{
                        aclarados+=listaScaerr.campo[i].toString()+" : "+listaScaerr.debeser[i].toString()+".- "
                    }
                }
                    
                    
                }
              

              conterrores++
              println(erroreacta+" -.-.-.-.-.-.-.-.-.-.-.-.-.")
          }
      }

      if(listaScaact.equals()){println("no tiene aplicacion del articulo 71")}
      else
      {
/*          listaerr+="\n\
"*/
          errorescivil[5]=1 
          errorescivil[5]=1 
          erroresreglamento[8]=1
          println("tiene aplicacion del art. 71")
          for(int i=0; i<listaScaact.size();i++)
          {     
              println(listaScaact.tipoerresp[i].toString() +" este es el tipo del 71")
                if(listaScaact.tipoerresp[i].toString().equals("APLICACION DEL ARTICULO 71"))
                {
                     if(listaScaact.numacta[i].equals() || listaScaact.fechaacta[i].equals(null) || listaScaact.dto[i].equals() || listaScaact.mpo[i].equals() || listaScaact.loc[i].equals())
                    {
                        
                    }else
                    {
                         println("aplicacion del art. 71")
                                      //mm.nombre.toString()
                        aclarados+="EN VIRTUD DEL ACTA DE MATRIMONIO DE LOS PADRES DEL REGISTRADO, CON NUMERO DE REGISTRO "+listaScaact.numacta[i]+" LEVANTADA EN "
                        aclarados+=""+(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))?listaScaact.mpo[i].toString()+", "+listaScaact.dto[i].toString()+", OAXACA ":listaScaact.loc[i].toString()+", "+listaScaact.mpo[i].toString()+", "+listaScaact.dto[i].toString()+", OAXACA "
                        aclarados+="DE FECHA "+listaScaact.fechaacta[i].split("-")[2]+" DE "+meses2[Integer.valueOf(listaScaact.fechaacta[i].split("-")[1])]+" DE "+listaScaact.fechaacta[i].split("-")[0]+" ANTERIOR A LA FECHA DEL REGISTRO DE NACIMIENTO Y CON FUNDAMENTO "
                        aclarados+="EN EL ARTICULO 71 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE ANOTAN LOS SIGUIENTES DATOS: "
                        
                                         //aclarados+="CON FUNDAMENTO EN EL ARTICULO 71 DEL CÓDIGO CIVIL VIGENTE EN EL ESTADO, SE ANOTAN LOS SIGUIENTES DATOS: "
                                         if(!listaScaact.pnombre[i].equals()){apli71[0]=1
                                             aclarados+="EL NOMBRE DEL PADRE: "+listaScaact.pnombre[i]+".-"}
                                         if(!listaScaact.pap1[i].equals()){apli71[0]=1
                                             aclarados+="EL APELLIDO PATERNO DEL PADRE: "+listaScaact.pap1[i]+".-"}
                                         if(!listaScaact.pap2[i].equals()){apli71[0]=1
                                             aclarados+="EL APELLIDO MATERNO DEL PADRE: "+listaScaact.pap2[i]+".-"}
                                         if(!listaScaact.pedad[i].equals()){apli71[0]=1
                                             Letras letras = new Letras(Integer.valueOf(listaScaact.pedad[i]))
                                             edadletra=letras.convertirLetras(listaScaact.pedad[i])
                                             aclarados+="LA EDAD DEL PADRE AL MOMENTO DEL REGISTRO: "+listaScaact.pedad[i]+" "+edadletra.toString().toUpperCase()+" AÑOS.-"}
                                         if(!listaScaact.pnac[i].equals()){apli71[0]=1
                                             aclarados+="LA NACIONALIDAD DEL PADRE: "+listaScaact.pnac[i]+".-"}
                                         if(!listaScaact.pab1[i].equals()){apli71[0]=1
                                             aclarados+="EL NOMBRE DEL ABUELO PATERNO: "+listaScaact.pab1[i]+".-"}
                                         if(!listaScaact.pab1ap1[i].equals()){apli71[0]=1
                                             aclarados+="EL PRIMER APELLIDO DEL ABUELO PATERNO: "+listaScaact.pab1ap1[i]+".-"}
                                         if(!listaScaact.pab1ap2[i].equals()){apli71[0]=1
                                             aclarados+="EL SEGUNDO APELLIDO DEL ABUELO PATERNO: "+listaScaact.pab1ap2[i]+".-"}
                                         if(!listaScaact.pab1nac[i].equals()){apli71[0]=1
                                             aclarados+="LA NACIONALIDAD DEL ABUELO PATERNO: "+listaScaact.pab1nac[i]+".-"}
                                         if(!listaScaact.pab2[i].equals()){apli71[0]=1
                                             aclarados+="EL NOMBRE DE LA ABUELA PATERNA: "+listaScaact.pab2[i]+".-"}
                                         if(!listaScaact.pab2ap1[i].equals()){apli71[0]=1
                                             aclarados+="EL PRIMER APELLIDO DE LA ABUELA PATERNA: "+listaScaact.pab2ap1[i]+".-"}
                                         if(!listaScaact.pab2ap2[i].equals()){apli71[0]=1
                                             aclarados+="EL SEGUNDO APELLIDO DE LA ABUELA PATERNA: "+listaScaact.pab2ap2[i]+".-"}
                                         if(!listaScaact.pab1nac[i].equals() && !listaScaact.pab2[i].equals()){apli71[0]=1
                                             aclarados+="LA NACIONALIDAD DE LA ABUELA PATERNA: "+listaScaact.pab1nac[i]+".-"}                        
                                         if(!listaScaact.mnom[i].equals()){apli71[1]=1
                                             aclarados+="EL NOMBRE DE LA MADRE: "+listaScaact.mnom[i]+".-"}
                                         if(!listaScaact.map1[i].equals()){apli71[1]=1
                                             aclarados+="EL PRIMER APELLIDO DE LA MADRE: "+listaScaact.map1[i]+".-"}
                                         if(!listaScaact.map2[i].equals()){apli71[1]=1
                                             aclarados+="EL SEGUNDO APELLIDO DE LA MADRE: "+listaScaact.map2[i]+".-"}
                                         if(!listaScaact.medad[i].equals()){apli71[1]=1
                                             Letras letras = new Letras(Integer.valueOf(listaScaact.medad[i]))
                                             edadletra=letras.convertirLetras(listaScaact.medad[i])
                                             aclarados+="LA EDAD DE LA MADRE AL MOMENTO DEL REGISTRO: "+listaScaact.medad[i]+" "+edadletra.toString().toUpperCase()+" AÑOS.-"}
                                         if(!listaScaact.mnac[i].equals()){apli71[1]=1
                                             aclarados+="LA NACIONALIDAD DE LA MADRE: "+listaScaact.mnac[i]+".-"}
                                         if(!listaScaact.mab1[i].equals()){apli71[1]=1
                                             aclarados+="EL NOMBRE DEL ABUELO MATERNO: "+listaScaact.mab1[i]+".-"}
                                         if(!listaScaact.mab1ap1[i].equals()){apli71[1]=1
                                             aclarados+="EL PRIMER APELLIDO DEL ABUELO MATERNO: "+listaScaact.mab1ap1[i]+".-"}
                                         if(!listaScaact.mab1ap2[i].equals()){apli71[1]=1
                                             aclarados+="EL SEGUNDO APELLIDO DEL ABUELO MATERNO: "+listaScaact.mab1ap2[i]+".-"}
                                         if(!listaScaact.mab1nac[i].equals()){apli71[1]=1
                                             aclarados+="LA NACIONALIDAD DEL ABUELO MATERNO: "+listaScaact.mab1nac[i]+".-"}
                                         if(!listaScaact.mba2[i].equals()){apli71[1]=1
                                             aclarados+="EL NOMBRE DE LA ABUELA MATERNA: "+listaScaact.mba2[i]+".-"}
                                         if(!listaScaact.mab2ap1[i].equals()){apli71[1]=1
                                             aclarados+="EL PRIMER APELLIDO DE LA ABUELA MATERNA: "+listaScaact.mab2ap1[i]+".-"}
                                         if(!listaScaact.mab2ap2[i].equals()){apli71[1]=1
                                             aclarados+="EL SEGUNDO APELLIDO DE LA ABUELA MATERNA: "+listaScaact.mab2ap2+".-"}
                                         if(!listaScaact.mab2nac[i].equals()){apli71[1]=1
                                             aclarados+="LA NACIONALIDAD DE LA ABUELA MATERNA: "+listaScaact.mab2nac[i]+".-"}
                                        println(apli71[0]+" este es el del padre" )
                                        println(apli71[1]+" este es el de la madre")
                                        if(apli71[0]==1)
                                          {
                                              if(apli71[1]==1)
                                              {
                                                  errart71+="OMISIÓN DE DATOS RELATIVOS A EL PADRE Y LA MADRE.- "
                                                  listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A EL PADRE Y LA MADRE, Y EN VIRTUD DEL MATRIMONIO DE SUS PADRES REGISTRADA CON FECHA ANTERIOR,LA ACLARACIÓN ES PROCEDENTE.\n\
"
                                              }
                                              else{
                                                  errart71+="OMISION DE DATOS RELATIVOS A EL PADRE.- "
                                                  listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A EL PADRE, Y EN VIRTUD DEL MATRIMONIO DE SUS PADRES REGISTRADA CON FECHA ANTERIOR,LA ACLARACIÓN ES PROCEDENTE.\n\
"                                                 }
                                          }else{errart71+="OMISION DE DATOS RELATIVOS A LA MADRE.- "
                                              listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A LA MADRE, Y EN VIRTUD DEL MATRIMONIO DE SUS PADRES REGISTRADA CON FECHA ANTERIOR,LA ACLARACIÓN ES PROCEDENTE.\n\
"                                               }                                     
                                                         
                                        errorescivil[5]=1 
                                        errorescivil[5]=1 
                                        erroresreglamento[8]=1
                    }
                   
                                        
                                       
                                
            
                }
                
                
                
                
                if(listaScaact.tipoerresp[i].toString().equals("OFICIO DE LEGITIMACION"))
                {
                    
                    
                    println("oficio de legitimacion")
                    legitimacion="367 y 368"
                    def fechamatrimonio=listaScaact.fechaacta[i].toString().split("-")
                    def fechmatri=fechamatrimonio
                    println(fechmatri[0]+ "este el año")
                    println(fechmatri[1]+ "este el mes")
                    println(fechmatri[2]+ "este el dia")
                    println(fechamatrimonio[0]+" ************************************")
                    println(fechamatrimonio[1])
                    
                    
                    if(listaScaact.numacta[i].equals() || listaScaact.fechaacta[i].toString().equals("") || listaScaact.dto[i].equals() || listaScaact.mpo[i].equals() || listaScaact.loc[i].equals())
                    {
                        
                    }else
                    {
                        //if(!listaScaact.mnom[i].equals() || !listaScaact.map1[i].equals() || !listaScaact.map2[i].equals() || !listaScaact.medad[i].equals() || !listaScaact.mnac[i].equals() || !listaScaact.mab1[i].equals() || !listaScaact.mab1ap1[i].equals() || !listaScaact.mab1ap2[i].equals() || !listaScaact.mab1nac[i].equals() || !listaScaact.mba2[i].equals() || !listaScaact.mab2ap1[i].equals() || !listaScaact.mab2ap2[i].equals() || !listaScaact.mab2nac[i].equals())
                        if((!listaScaact.mnom[i].equals() || 
                                !listaScaact.map1[i].equals() || 
                                !listaScaact.map2[i].equals() || 
                                !listaScaact.medad[i].equals() || 
                                !listaScaact.mnac[i].equals() || 
                                !listaScaact.mab1[i].equals() || 
                                !listaScaact.mab1ap1[i].equals() || 
                                !listaScaact.mab1ap2[i].equals() || 
                                !listaScaact.mab1nac[i].equals() || 
                                !listaScaact.mba2[i].equals() || 
                                !listaScaact.mab2ap1[i].equals() || 
                                !listaScaact.mab2ap2[i].equals() || !listaScaact.mab2nac[i].equals()) 
                            && (listaScaact.pnombre[i].equals() && listaScaact.pap1[i].equals() && listaScaact.pap2[i].equals()
                                && listaScaact.pedad[i].equals() && listaScaact.pnac[i].equals() && listaScaact.pab1[i].equals()
                                && listaScaact.pab1ap1[i].equals() && listaScaact.pab1ap2[i].equals() && listaScaact.pab2[i].equals()
                                && listaScaact.pab2ap1[i].equals() && listaScaact.pab2ap2[i].equals() && listaScaact.pab2nac[i].equals()
                               )  
                          )
                        {
                        //println ("Datos relativos a la madre")
                        if(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))
                        {
                            //((scasolInstance.ap2_intere.equals())?"No trae segundo apellido":"Si trae seundo apellido")
                            aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+".-"
                        
                            //aclarados+=""+scasolInstance.nom_intere+" "+scasolInstance.ap1_intere+" "+scasolInstance.ap2_intere+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A) EN VIRTUD DE "+listaScaact.bases[i]+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+".-"
                        
                        }else{
                            aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"
                        
                        }
        
                        errart71+="OMISION DE DATOS RELATIVOS A LA MADRE.-  "
                        listaerr+=conterrores+"OMISION DE DATOS RELATIVOS A LA MADRE Y EN VIRTUD DEL MATRIMONIO SUBSECUENTE DE SUS PADRES, LA ACLARACIÓN ES PROCEDENTE \n\
"              
                        errorescivil[5]=1 
                        errorescivil[5]=1 
                        erroresreglamento[8]=1
                        
                       if(!listaScaact.mnom[i].equals())
                        {
                            def dmadre="EL NOMBRE DE LA MADRE: "
                            dmadre+=listaScaact.mnom[i]+""+(listaScaact.map1[i].equals() ? "":" "+listaScaact.map1[i])+""+(listaScaact.map2[i].equals() ? "":" "+listaScaact.map2[i])+".-"
                            aclarados+=dmadre
                        }
                        if(!listaScaact.medad[i].equals())
                        {
                            Letras letras = new Letras(Integer.valueOf(listaScaact.medad[i]))
                            edadletra=letras.convertirLetras(listaScaact.medad[i])
                            aclarados+="LA EDAD DE LA MADRE AL MOMENTO DEL REGISTRO: "+listaScaact.medad[i]+" "+edadletra.toString().toUpperCase()+" AÑOS.-"
                        }
                        if(!listaScaact.mnac[i].equals())
                        {
                            aclarados+="LA NACIONALIDAD DE LA MADRE: "+listaScaact.mnac[i]+".-"
                        }
                        if(!listaScaact.mab1[i].equals())
                        {
                            aclarados+="EL NOMBRE DEL ABUELO MATERNO: "+listaScaact.mab1[i]+""+(listaScaact.mab1ap1[i].equals() ? "":" "+listaScaact.mab1ap1[i])+""+(listaScaact.mab1ap2[i].equals() ? "": " "+listaScaact.mab1ap2[i])+".-"
                            aclarados=aclarados.replaceAll("  "," ")
                        }
                        if(!listaScaact.mab1nac[i].equals() && !listaScaact.mab1[i].equals())
                        {
                            aclarados+="LA NACIONALIDAD DEL ABUELO MATERNO: "+listaScaact.mab1nac[i]+".-"
                        }
                        if(!listaScaact.mba2[i].equals())
                        {
                            aclarados+="EL NOMBRE DE LA ABUELA MATERNA: "+listaScaact.mba2[i]+""+(listaScaact.mab2ap1[i].equals()? "":" "+listaScaact.mab2ap1[i])+""+(listaScaact.mab2ap2[i].equals()? "":" "+listaScaact.mab2ap2[i])+".-"
                            aclarados=aclarados.replaceAll("  "," ")
                        }
                        if(!listaScaact.mab2nac[i].equals() && !listaScaact.mba2[i].equals())
                        {
                            aclarados+="LA NACIONALIDAD DE LA ABUELA MATERNA: "+listaScaact.mab2nac[i]+".-"
                        }
                       
                        
                    }else
                        {
                            //if(!listaScaact.pnombre[i].equals() || !listaScaact.pap1[i].equals() || !listaScaact.pap2[i].equals() || !listaScaact.pedad[i].equals() || !listaScaact.pnac[i].equals() || !listaScaact.pab1[i].equals() || !listaScaact.pab1ap1[i].equals() || !listaScaact.pab1ap2[i].equals() || !listaScaact.pab1nac[i].equals() || !listaScaact.pab2[i].equals() || !listaScaact.pab2ap1[i].equals() || !listaScaact.pab2ap2[i].equals() || !listaScaact.pab2nac[i].equals())
                            if(listaScaact.mnom[i].equals() && 
                                listaScaact.map1[i].equals() && 
                                listaScaact.map2[i].equals() && 
                                listaScaact.medad[i].equals() && 
                                listaScaact.mnac[i].equals() && 
                                listaScaact.mab1[i].equals() && 
                                listaScaact.mab1ap1[i].equals() && 
                                listaScaact.mab1ap2[i].equals() && 
                                listaScaact.mab1nac[i].equals() && 
                                listaScaact.mba2[i].equals() && 
                                listaScaact.mab2ap1[i].equals() && 
                                listaScaact.mab2ap2[i].equals() && listaScaact.mab2nac[i].equals())
                            
                            
                            {         
                                 //println ("Datos relativos a el padre")
                                  if(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))
                        {
                            aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"
                        
                        }else{
                            aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"
                        
                        }
                                 errart71+="OMISION DE DATOS RELATIVOS A EL PADRE.- "
                                 listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A EL PADRE Y EN VIRTUD DEL MATRIMONIO SUBSECUENTE DE SUS PADRES, LA ACLARACIÓN ES PROCEDENTE \n\
"
                                errorescivil[5]=1 
                                errorescivil[5]=1 
                                erroresreglamento[8]=1
                                if(!listaScaact.pnombre[i].equals())
                        {
                            def dmadre="EL NOMBRE DEL PADRE: "
                            dmadre+=listaScaact.pnombre[i]+""+(listaScaact.pap1[i].equals() ? "":" "+listaScaact.pap1[i])+""+(listaScaact.pap2[i].equals() ? "":" "+listaScaact.pap2[i])+".-"
                            aclarados+=dmadre
                        }
                        if(!listaScaact.pedad[i].equals())
                        {
                            Letras letras = new Letras(Integer.valueOf(listaScaact.pedad[i]))
                            edadletra=letras.convertirLetras(listaScaact.pedad[i])
                            aclarados+="LA EDAD DEL PADRE AL MOMENTO DEL REGISTRO: "+listaScaact.pedad[i]+" "+edadletra.toString().toUpperCase()+" AÑOS.-"
                        }
                        if(!listaScaact.pnac[i].equals())
                        {
                            aclarados+="LA NACIONALIDAD DEL PADRE: "+listaScaact.pnac[i]+".-"
                        }
                        if(!listaScaact.pab1[i].equals())
                        {
                            aclarados+="EL NOMBRE DEL ABUELO PATERNO: "+listaScaact.pab1[i]+""+(listaScaact.pab1ap1[i].equals() ? "":" "+listaScaact.pab1ap1[i])+""+(listaScaact.pab1ap2[i].equals() ? "": " "+listaScaact.pab1ap2[i])+".-"
                            aclarados=aclarados.replaceAll("  "," ")
                        }
                        if(!listaScaact.pab1nac[i].equals())
                        {
                            aclarados+="LA NACIONALIDAD DEL ABUELO PATERNO: "+listaScaact.pab1nac[i]+".-"
                        }
                        if(!listaScaact.pab2[i].equals())
                        {
                            aclarados+="EL NOMBRE DE LA ABUELA PATERNA: "+listaScaact.pab2[i]+""+(listaScaact.pab2ap1[i].equals()? "":" "+listaScaact.pab2ap1[i])+""+(listaScaact.pab2ap2[i].equals()? "":" "+listaScaact.pab2ap2[i])+".-"
                            aclarados=aclarados.replaceAll("  "," ")
                        }
                        if(!listaScaact.pab2nac[i].equals() && !listaScaact.pab2[i].equals())
                        {
                            aclarados+="LA NACIONALIDAD DE LA ABUELA PATERNA: "+listaScaact.pab2nac[i]+".-"
                        }
                               
                                }else
                                    {  //mm.nombre.toString()                                                                      
                                             if(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))
                                                {
                                                aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+".-"
                        
                                                }else{
                                                aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+listaScaact.mpo[i]+", "+listaScaact.dto[i]+".-"
                        
                                                }
                                                                
                                        
                                       // println ("Datos relativos al padre y la madre") 
                                        errart71+="OMISION DE DATOS RELATIVOS A EL PADRE Y LA MADRE. "
                                        listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A EL PADRE Y LA MADRE Y EN VIRTUD DEL MATRIMONIO SUBSECUENTE DE SUS PADRES, LA ACLARACIÓN ES PROCEDENTE  \n\
"               
                                        errorescivil[5]=1 
                                        errorescivil[5]=1 
                                        erroresreglamento[8]=1
                                                                if(!listaScaact.pnombre[i].equals())
                        {
                            def dmadre="EL NOMBRE DEL PADRE: "
                            dmadre+=listaScaact.pnombre[i]+""+(listaScaact.pap1[i].equals() ? "":" "+listaScaact.pap1[i])+""+(listaScaact.pap2[i].equals() ? "":" "+listaScaact.pap2[i])+".-"
                            aclarados+=dmadre
                        }
                        if(!listaScaact.pedad[i].equals())
                        {
                            Letras letras = new Letras(Integer.valueOf(listaScaact.pedad[i]))
                            edadletra=letras.convertirLetras(listaScaact.pedad[i])
                            aclarados+="LA EDAD DEL PADRE AL MOMENTO DEL REGISTRO: "+listaScaact.pedad[i]+" "+edadletra.toString().toUpperCase()+" AÑOS.-"
                        }
                        if(!listaScaact.pnac[i].equals())
                        {
                            aclarados+="LA NACIONALIDAD DEL PADRE: "+listaScaact.pnac[i]+".-"
                        }
                        if(!listaScaact.pab1[i].equals())
                        {
                            aclarados+="EL NOMBRE DEL ABUELO PATERNO: "+listaScaact.pab1[i]+""+(listaScaact.pab1ap1[i].equals() ? "":" "+listaScaact.pab1ap1[i])+""+(listaScaact.pab1ap2[i].equals() ? "": " "+listaScaact.pab1ap2[i])+".-"
                            aclarados=aclarados.replaceAll("  "," ")
                        }
                        if(!listaScaact.pab1nac[i].equals())
                        {
                            aclarados+="LA NACIONALIDAD DEL ABUELO PATERNO: "+listaScaact.pab1nac[i]+".-"
                        }
                        if(!listaScaact.pab2[i].equals())
                        {
                            aclarados+="EL NOMBRE DE LA ABUELA PATERNA: "+listaScaact.pab2[i]+""+(listaScaact.pab2ap1[i].equals()? "":" "+listaScaact.pab2ap1[i])+""+(listaScaact.pab2ap2[i].equals()? "":" "+listaScaact.pab2ap2[i])+".-"
                            aclarados=aclarados.replaceAll("  "," ")
                        }
                        if(!listaScaact.pab2nac[i].equals() && !listaScaact.pab2[i].equals())
                        {
                            aclarados+="LA NACIONALIDAD DE LA ABUELA PATERNA: "+listaScaact.pab2nac[i]+".-"
                        }
                                                if(!listaScaact.mnom[i].equals())
                        {
                            def dmadre="EL NOMBRE DE LA MADRE: "
                            dmadre+=listaScaact.mnom[i]+""+(listaScaact.map1[i].equals() ? "":" "+listaScaact.map1[i])+""+(listaScaact.map2[i].equals() ? "":" "+listaScaact.map2[i])+".-"
                            aclarados+=dmadre
                        }
                        if(!listaScaact.medad[i].equals())
                        {
                            Letras letras = new Letras(Integer.valueOf(listaScaact.medad[i]))
                            edadletra=letras.convertirLetras(listaScaact.medad[i])
                            aclarados+="LA EDAD DE LA MADRE AL MOMENTO DEL REGISTRO: "+listaScaact.medad[i]+" "+edadletra.toString().toUpperCase()+" AÑOS.-"
                        }
                        if(!listaScaact.mnac[i].equals())
                        {
                            aclarados+="LA NACIONALIDAD DE LA MADRE: "+listaScaact.mnac[i]+".-"
                        }
                        if(!listaScaact.mab1[i].equals())
                        {
                            aclarados+="EL NOMBRE DEL ABUELO MATERNO: "+listaScaact.mab1[i]+""+(listaScaact.mab1ap1[i].equals() ? "":" "+listaScaact.mab1ap1[i])+""+(listaScaact.mab1ap2[i].equals() ? "": " "+listaScaact.mab1ap2[i])+".-"
                            aclarados=aclarados.replaceAll("  "," ")
                        }
                        if(!listaScaact.mab1nac[i].equals() && !listaScaact.mab1[i].equals())
                        {
                            aclarados+="LA NACIONALIDAD DEL ABUELO MATERNO: "+listaScaact.mab1nac[i]+".-"
                        }
                        if(!listaScaact.mba2[i].equals())
                        {
                            aclarados+="EL NOMBRE DE LA ABUELA MATERNA: "+listaScaact.mba2[i]+""+(listaScaact.mab2ap1[i].equals()? "":" "+listaScaact.mab2ap1[i])+""+(listaScaact.mab2ap2[i].equals()? "":" "+listaScaact.mab2ap2[i])+".-"
                            aclarados=aclarados.replaceAll("  "," ")
                        }
                        if(!listaScaact.mab2nac[i].equals() && !listaScaact.mba2[i].equals())
                        {
                            aclarados+="LA NACIONALIDAD DE LA ABUELA MATERNA: "+listaScaact.mab2nac[i]+".-"
                        }
                                        
                                       }
                                }
                    }
                    
                    
                    
                }
                if(listaScaact.tipoerresp[i].toString().equals("OFICIO DE RECONOCIMIENTO"))
                {
                    println("oficio de reconocmiento")
                    /*def fechamatrimonio=listaScaact.fechaacta[i].toString().split(" ")
                    def fechmatri=fechamatrimonio[0].split("-")*/
                    def fechamatrimonio=listaScaact.fechaacta[i].toString().split("-")
                    def fechmatri=fechamatrimonio
                    println(fechmatri[0]+ "este el año")
                    println(fechmatri[1]+ "este el mes")
                    println(fechmatri[2]+ "este el dia")
                    println(fechamatrimonio[0]+" ************************************")
                    println(fechamatrimonio[1])
                    if(listaScaact.numacta[i].equals() || listaScaact.fechaacta[i].equals(null) || listaScaact.dto[i].equals() || listaScaact.mpo[i].equals() || listaScaact.loc[i].equals())
                    {
                        
                    }else
                    {
                        if(listaScaact[i].dto.toString().equals(scasolInstance.dto.toString()) &&
                           listaScaact[i].mpo.toString().equals(scasolInstance.mpo.toString()) &&
                           listaScaact[i].loc.toString().equals(scasolInstance.loc.toString()))
                       {
                           println("El reconocimiento fue en el mismo lugar")
                           reconocimiento="84, "
                       }
                       else
                       {
                           println("El reconocimiento no fue en el mismo lugar")
                           reconocimiento="84, 85, "
                       }
                        println("Datos del acta de reconocimiento")
                        println("--------------------------------")
                        println(listaScaact[i].dto.toString())
                        println(listaScaact[i].mpo.toString())
                        println(listaScaact[i].loc.toString())
                        println("--------------------------------")
                        println("Datos del acta de registro")
                        println("--------------------------------")
                        println(scasolInstance.dto.toString())
                        println(scasolInstance.mpo.toString())
                        println(scasolInstance.loc.toString())
                        println("--------------------------------")
                        if(!listaScaact.mnom[i].equals() || !listaScaact.map1[i].equals() || !listaScaact.map2[i].equals() || !listaScaact.medad[i].equals() || !listaScaact.mnac[i].equals() || !listaScaact.mab1[i].equals() || !listaScaact.mab1ap1[i].equals() || !listaScaact.mab1ap2[i].equals() || !listaScaact.mab1nac[i].equals() || !listaScaact.mba2[i].equals() || !listaScaact.mab2ap1[i].equals() || !listaScaact.mab2ap2[i].equals() || !listaScaact.mab2nac[i].equals())
                    {
                        println ("Datos relativos LA MADRE")
                        if(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))
                                        {
                                         aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO SE HACE LA SIGUIENTE ANOTACION DE RECONOCIMIENTO: LA PERSONA A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) POR SU MADRE SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+".-"   
                                        }else{
                                         aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO SE HACE LA SIGUIENTE ANOTACION DE RECONOCIMIENTO: LA PERSONA A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) POR SU MADRE SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"   
                                        
                                            } 
                        errart71+="OMISION DE DATOS RELATIVOS A LA MADRE.  "
                        listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A LA MADRE, Y DE ACUERDO AL ACTA DE RECONOCIMIENTO EXHIBIDA, QUEDA RECONOCIDO(A) POR SU MADRE, POR TAL RAZÓN LA ACLARACIÓN ES PROCEDENTE \n\
"
                        errorescivil[5]=1 
                        errorescivil[5]=1 
                        erroresreglamento[8]=1
                        
                    }else
                        {
                            if(!listaScaact.pnombre[i].equals() || !listaScaact.pap1[i].equals() || !listaScaact.pap2[i].equals() || !listaScaact.pedad[i].equals() || !listaScaact.pnac[i].equals() || !listaScaact.pab1[i].equals() || !listaScaact.pab1ap1[i].equals() || !listaScaact.pab1ap2[i].equals() || !listaScaact.pab1nac[i].equals() || !listaScaact.pab2[i].equals() || !listaScaact.pab2ap1[i].equals() || !listaScaact.pab2ap2[i].equals() || !listaScaact.pab2nac[i].equals())
                            {         
                                 println ("Datos relativos a EL PADRE")
                                 if(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))
                                        {
                                             aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO SE HACE LA SIGUIENTE ANOTACION DE RECONOCIMIENTO: LA PERSONA A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) POR SU PADRE SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+".-"   
                                        
                                         }else{
                                             aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO SE HACE LA SIGUIENTE ANOTACION DE RECONOCIMIENYO: LA PERSONA A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) POR SU PADRE SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"   
                                        
                                            }                              
                                                               
                                 errart71+="OMISION DE DATOS RELATIVOS A EL PADRE. "
                                 listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A EL PADRE, Y DE ACUERDO AL ACTA DE RECONOCIMIENTO EXHIBIDA, QUEDA RECONOCIDO(A) POR SU PADRE, POR TAL RAZÓN LA ACLARACIÓN ES PROCEDENTE \n\
                 "
                                errorescivil[5]=1 
                                errorescivil[5]=1 
                                erroresreglamento[8]=1
                                
                                }else
                                    {  //mm.nombre.toString()
                                        if(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))
                                        {
                                             aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO SE HACE LA SIGUIENTE ANOTACION DE RECONOCIMIENTO: LA PERSONA A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) POR SUS PADRES SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+listaScaact.fechaacta[i]+", LEVANTADA EN "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"   
                                        }else{
                                             aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO SE HACE LA SIGUIENTE ANOTACION DE RECONOCIMIENTO: LA PERSONA A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) POR SUS PADRES SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+listaScaact.fechaacta[i]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"   
                                        
                                            }                                                                                     
                                        println ("Datos relativos al padre y la madre") 
                                        errart71+="OMISION DE DATOS RELATIVOS A EL PADRE Y LA MADRE. "
                                        listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A EL PADRE Y LA MADRE, Y DE ACUERDO AL ACTA DE RECONOCIMIENTO EXHIBIDA, QUEDA RECONOCIDO(A) POR SUS PADRES, POR TAL RAZÓN LA ACLARACIÓN ES PROCEDENTE  \n\
                        "
                                errorescivil[5]=1 
                                        errorescivil[5]=1 
                                        erroresreglamento[8]=1
                                        
                                       }
                                }
                    }
                    
                }
                conterrores++
          }
          
      }
        
        
        
        //def enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")
        //def enarchivo=EncargadoArchivo.find("From EncargadoArchivo where activo=true")
        def usuactual=User.get(params.usuario1)
 def usuario=""
 if(usuactual.nombre)
 {
     usuario+=usuactual.nombre.substring(0,1)
 }
 if(usuactual.apellpa)
 {
     usuario+=usuactual.apellpa.substring(0,1)
 }
 if(usuactual.apellma)
 {
     usuario+=usuactual.apellma.substring(0,1)
 }
        //def enjuridico=new EncargadoJuridico()
        //enjuridico.find("From EncargadoJuridico where activo=true")
         def afavor=scasolInstance.nom_intere+" "+scasolInstance.ap1_intere+" "+scasolInstance.ap2_intere
         if(afavor.equals(scasolInstance.promov))
         {
            favore=scasolInstance.promov+" promoviendo por su propio derecho." 
            promover=scasolInstance.promov+" por su propio derecho"
         }
         else
         {
            favore=scasolInstance.promov+" promoviendo a favor de "+afavor
            promover=scasolInstance.promov+" a favor del registrado"
                     
        }
        pa1+=favore
        pa1+="\n\
"+"Instaurado el procedimiento Administrativo de aclaración de cuyo expediente se cita al rubro;"+
"previsto por los artículos 141 del Código Civil vigente en el Estado y 26, 27 y 28 del Reglamento correspondiente.\n\
\n\
"+
"                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O"+"\n\
\n\
"+
"PRIMERO.- De conformidad con lo dispuesto por el Artículo 142 del Código Civil Vigente en el Estado, 26 y 30 del Reglamento interno del Registro civil en los que se faculta a esta Dirección a emitir las Resoluciones Administrativas de aclaración de acta."
pa2+=erroreacta+"\n\
\n\
"
        def paesp=""
        paesp+=pa1
        paesp+=pa2
pa3="TERCERO.- CON BASE EN EL ANÁLISIS A LAS FOTOCOPIAS CERTIFICADAS DEL ACTA DE NACIMIENTO, SE OBSERVAN LAS SIGUIENTES INCONSISTENCIAS:\n\
\n\
"
pa3+=listaerr
def pa4="\n\
CUARTO.- Esta Dirección es competente para conocer y resolver la petición planteada como en efecto.\n\
\n\
"+
        "                                                                                                          R  E  S  U  E  L  V  E\n\
\n\
"+
"PRIMERO.- La solicitud de aclaración del Acta del Estado Civil formulada por el compareciente en este expediente es procedente.\n\
\n\
"
       
        
        if(scasolInstance.loc.toString().equals(scasolInstance.mpo.toString()))
        {
            println("la localidad es igual al municipio")
            lugar+=scasolInstance.mpo.toString()+", "+scasolInstance.dto
        }else{
            lugar+=scasolInstance.loc.toString()+", "+scasolInstance.mpo.toString()+", "+scasolInstance.dto
        }
       // println(errart71+"{{{{{{{{")        
       // println(aclarados+"---------")
        println(listaerr+"*******")
        println(errorescivil.length+" aqui va el tamaño")
        for(int u=0;u<errorescivil.length;u++)
        {
            println(errorescivil[u])
            
        }
        println("///////////////////")
       errorescivil[4]=1;
       def artcodigo="141 Fracciones "
       def artreglamento="30 ,31, "
       if(errorescivil[0]==1){artcodigo+="I, "}
       if(errorescivil[1]==1){artcodigo+="II, "}
       if(errorescivil[2]==1){artcodigo+="III, "}
       if(errorescivil[3]==1){artcodigo+="IV, "}
       if(errorescivil[4]==1){artcodigo+="V, "}
       if(errorescivil[6]==1){
           errorescivil[5]=0
           artcodigo+="VI segunda parte, "}
       if(errorescivil[5]==1){artcodigo+="VI, "}
       
       if(erroresreglamento[0]==1){artreglamento+="32, "}
       if(erroresreglamento[1]==1){artreglamento+="33, "}
       if(erroresreglamento[2]==1){artreglamento+="34, "}
       if(erroresreglamento[3]==1){artreglamento+="35, "}
       if(erroresreglamento[4]==1)
       {
           if(erroresreglamento[5]==1){artreglamento+="36 Fraccion I, II" erroresreglamento[5]=0}
           else{artreglamento+="36 Fraccion I, "}
           
       }
       if(erroresreglamento[5]==1){artreglamento+="36 Fraccion II, "}
       def art37=""
       
       if(erroresreglamento[6]==1 || erroresreglamento[7]==1 || erroresreglamento[8]==1 || erroresreglamento[9]==1)
         {
         
         //artreglamento+="37 Fracc. "
         art37+="37 Fraccion "
         }
        
        
        if(erroresreglamento[6]==1)
        {
            if(erroresreglamento[7]==1 || erroresreglamento[8]==1 || erroresreglamento[9]==1)
            {
              //artreglamento+="I, "
              art37+="I, "
            }
            else {
                //artreglamento+="I"
                art37+="I"
            }
        }
        if(erroresreglamento[7]==1)
        {
            if(erroresreglamento[8]==1 || erroresreglamento[9]==1)
            {
              //artreglamento+="II, "
              art37+="II, "
            }else 
            {
                //artreglamento+="II"
                art37+="II"
            }
        }
        if(erroresreglamento[8]==1)
        {
            if(erroresreglamento[9]==1)
            {
              //artreglamento+="III, "
              art37+="III,"
            }else {//artreglamento+="III"
                art37+="III"
            }
        }
        if(erroresreglamento[9]==1)
        {
            art37+="IV"
        }
        println(art37 +"Aqui va el 37 *")
        println("Aqui va el reglamento "+artreglamento)
        for(int s=art37.toString().length();s>0;s--)
        {
            println(art37.charAt(s-1))
            if(art37.charAt(s-1)==44)
            {
                println("Esta es la ultima coma del art 37")
                art37=art37.substring(0,s-1)+" y "+art37.substring(s,art37.toString().length())
                break
            }
        }
        
        for(int f=artreglamento.toString().length();f>0;f--)
        {
            if(artreglamento.charAt(f-1)==44)
            {
                println("Esta es la ultima coma del reglamento")
                artreglamento=artreglamento.substring(0,f-1)+" y"+artreglamento.substring(f,artreglamento.toString().length())
                break
            }
        }
        
        artreglamento+=art37
        println(artreglamento)
        println("---------")  
        def art68letras=""
        for(int l=0;l<articulo68.length;l++)
        {
            
           //println(articulo68[l]+" ioioioioioioioo")
           if(articulo68[l]!=0)
           {
               art68letras+=articulo68l[l]+" "
           }
        
        }
       
        //println(art68letras+" --------------------???????????????")
         //def jefejuridico=Encargadojuridico.fin
         if(!art68letras.isEmpty())
         {
            art68letras=art68letras.replaceAll(" ",", ") 
            art68letras="68 FRACCIONES "+art68letras+" "
            //art68letras=art68letras.replaceAll(",,",", ") 
            println(art68letras+" ???????????????")
            
         }
        
        def pa5="SEGUNDO.- Aclarese el acta de "+scasolInstance.typact+" "+"de: "+afavor+" "+"levantada según registro número "+scasolInstance.numact.toString()+" "+"de fecha "+fech[2]+" de "+meses2[Integer.valueOf(fech[1])]+" de "+fech[0]+" en "+lugar+", OAXACA. a fin de que al margen de la misma se haga la anotación siguiente:\n\
\"...Con fundamento en lo dispuesto por los artículos "+""/*reconocimiento*/+""+artmat2+""+art68letras+""+artcodigo+" y 142 del Código Civil Vigente en el Estado, en relación a los artículos "+artreglamento+" del Reglamento del Registro Civil se aclaran los siguientes datos: "+aclarados+" Aclaración del acta del Registro Civil de fecha "+fech2[2]+" de "+meses2[Integer.valueOf(fech2[1])]+ " de "+fech2[0]+" .- Promovida por "+promover+" .-Expediente Número "+scasolInstance.expro.toString()+"/"+scasolInstance.expano.toString()+" "+".-Conste.- El jefe de la Unidad Jurídica del Registro Civil del Estado de Oaxaca.- "+"Lic."+" "+enjuridico+"...\"\n\
\n\
"
        if(!artmat.isEmpty())
        {
            pa5=pa5.replaceFirst("y 142 del C","142 y 206 del C")
        }
        if(!artmat.isEmpty())
        {
            pa5=pa5.replaceFirst("142 y 206 del C", "142, 206 del C")
        }
        if(!legitimacion.isEmpty())
        {
            pa5=pa5.replaceFirst("y 142 del C","142, 367 y 368 del C")
        }

        pa5=pa5.replaceFirst(",  ,",",")
def pa6="TERCERO.- Remítase copia de esta resolucón al C. Oficial del Registro Civil de "+scasolInstance.ofi+", "+scasolInstance.dto+", OAXACA, para que después de haber hecho la anotación correspondiente sea agregada al apéndice respectivo.\n\
 \n\
ASÍ LO RESOLVIÓ Y FIRMA EL C. JEFE DE LA UNIDAD JURÍDICA DEL REGISTRO CIVIL EN EL ESTADO DE OAXACA. DOY FE"
        def pa7="\n\
\n\
\n\
                                                                                        "+enjuridico.titulo+" "+enjuridico+"\n\
\n\
"+enjuridico.nombre.substring(0,1)+""+enjuridico.ape_pat.substring(0,1)+""+enjuridico.ape_mat.substring(0,1)+"/"
        
  def papa=pa5.split("\\.\\.\\.")
      
        def reso=papa[1]+"Comprobante pago de derechos:\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id)+"\n\
EL JEFE DE LA UNIDAD JURIDICA.                    EL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi+", "+scasolInstance.dto+" \n\
"+enjuridico+"\n\
\n\
\n\
**Esta papeleta solamente es válida adherida al libro original**"   
        
        
     def papeleta="\"...Con fundamento en lo dispuesto por los artículos "+artcodigo+" y 142 del Código Civil Vigente en el Estado, en relación a los artículos "+artreglamento+" del Reglamento del Registro Civil se aclaran los siguientes datos: "+aclarados+" Aclaración del acta del Registro Civil de fecha "+fech[2]+" de "+meses2[Integer.valueOf(fech[1])]+ " de "+fech[0]+" .- Promovida por "+promover+" .-Expediente Número "+scasolInstance.expro.toString()+"/"+fech[0]+" "+".-Conste.- El jefe de la Unidad Jurídica del Registro Civil del Estado de Oaxaca.- Lic. "+enjuridico+"...\""   
        //
        
        def guardapapeleta=Papeleta.findByExpanoAndExpro(scasolInstance.expano,scasolInstance.expro)
        println("----------------")
        println(guardapapeleta)
        println("----------------")
        if(!guardapapeleta || guardapapeleta.equals(""))
        {
            guardapapeleta= new Papeleta()
            guardapapeleta.expano=scasolInstance.expano
        guardapapeleta.expro=scasolInstance.expro
        guardapapeleta.nota=papeleta
        guardapapeleta.ip=request.getRemoteAddr().toString()
        guardapapeleta.usuario=springSecurityService.currentUser.id
        guardapapeleta.save(flus:true)
        }else
        {
        
        guardapapeleta.nota=papeleta
        guardapapeleta.save(flus:true)
        }
                

        new File("D:/codigo.png").withOutputStream { out ->
    barcode4jService.render("code39Generator", scasolInstance.expro+"/"+scasolInstance.expano, out, "image/png")
}       

 println(params.usuario1.toString() +"****************************** usuario")
 println(User.get(params.usuario1))
 
 
                def reso2=papa[1]+"Comprobante pago de derechos:\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id) 
        println(scasolInstance.val)
        //println(User.findByUsername(scasolInstance.val.toString()))
        
        //println(usua.nombre.substring(0,1)+""+usua.apellpa.substring(0,1)+""+usua.apellma.substring(0,1))
                //resolu=reso
                def resolu=papa[1]+"Comprobante pago de derechos:\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id)+"\n\
EL JEFE DE LA UNIDAD JURIDICA.                    EL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi+", "+scasolInstance.dto+" \n\
"+enjuridico.titulo+" "+enjuridico+"\n\
\n\
\n\
**Esta papeleta solamente es válida adherida al libro original**" 
        
        
        
        
       def mapa=[]
       byte[] bytes
       int dondemando=0
       if(!listaScaerr.equals())
       {
        dondemando=listaScaerr.donde[0]   
       }
       else
       {
           if(!listaScaact.equals())
           {
               //dondemando=listaScaact.donde[0]
               switch(listaScaact.donde[0].toString())
               {
                   case 'ARCHIVO CENTRAL Y OFICIALIA':
                       dondemando=3
                       break
                   case 'EN LA OFICIALIA':
                       dondemando=1
                       break
                   case 'EN EL ARCHIVO CENTRAL':
                       dondemando=2
                       break
                   
               }
               println(listaScaact.donde[0])
           }
       }
       println(listaScaerr.donde[0]+" Aqui vamos a ver a donde se tiene que enviar")
                             mapa << [
           texto:reso2.replaceAll("&nbsp;",""),
            //barr:buf,
            expediente2:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+" "+scasolInstance.ofi+", "+scasolInstance.dto,
            expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"     ARCHIVO CENTRAL",
            texto2:reso.replaceAll("&nbsp;",""),
            encargado:"EL JEFE DEL ARCHIVO CENTRAL\n\
"+enarchivo.titulo+" "+enarchivo,
            encargadojuridico:"EL JEFE DE LA UNIDAD JURIDICA\n\
            \n\
            \n\
"+enjuridico.titulo+" "+enjuridico,
            boleta:"** Esta papeleta solamente es valida adherida al libro original **",
            resolucion:reso,
            exp:"Expediente No.:"+scasolInstance.expro+"/"+scasolInstance.expano ,  
            fecha:"Oaxaca de Juárez,Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0],
            //pa1:pa1,
            pa2:pa1+pa2+pa3+pa4+pa5+pa6+pa7+usuactual.toString()//+pa5//+pa7
            //variable1:"Hola mundo"
                 ]
                // def reportDef
       switch(dondemando)
       {
        case 3: println("Se envia archivo y oficialia")
            reportDef = new JasperReportDef(name: "reso2.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
             break
        case 2: println("Se envia a archivo central")
            reportDef = new JasperReportDef(name: "reso3.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
             break
        case 1: println("Se envia a la oficialia")
            reportDef = new JasperReportDef(name: "reso4.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                 break
        default: println("No se manda a ninguna de la anteriores")
            return
            break
                     
       }
        

                 
        def par1=""
        def par7=""
        par7=pa7.replaceAll("\n\
\n\
\n\
","<p style='text-align: justify; line-height: 1em; font-size: 10pt'></p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: center; line-height: 1em; font-size: 10pt'>")
        par7=par7.replaceAll("\n\
\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>")
        //println()
        par1="<p style='text-align: justify; line-height: 1em; font-size: 10pt'>"+pa1.replaceAll("\n\
\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>").replaceAll("\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>")+"</p>"
        /*println("<p style='text-align: justify; line-height: 1em; font-size: 10pt'>"+pa1.replaceAll("\n\
\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>").replaceAll("\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>")+"</p>")

        println("******************")*/
        def par2=""
        def par4=""
        def par5=""
        def par6=""
        par4="<p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>"+pa4.replaceAll("\n\
\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>").replaceAll("\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>")+"</p>"
        def par3="<p style='text-align: justify; line-height: 1em; font-size: 10pt'>"+pa3.replaceAll("\n\
\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>").replaceAll("\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>")+"</p>"
        par2="<p style='text-align: justify; line-height: 1em; font-size: 10pt'>"+pa2.replaceAll("\n\
\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>").replaceAll("\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>")+"</p>"
        par5="<p style='text-align: justify; line-height: 1em; font-size: 10pt'>"+pa5.replaceAll("\n\
\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>").replaceAll("\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>")+"</p>"
        
        par6="<p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>"+pa6.replaceAll("\n\
\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>").replaceAll("\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>")+"</p>"
        //par6=par6.replaceAll("ASÍ LO RESOLV","<p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;<p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;<p>ASÍ LO RESOLV")
        par6=par6.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 10pt'>ASÍ LO RESOLVIÓ","<p style='text-align: justify; line-height: 1em; font-size: 8pt'></p><p style='text-align: justify; line-height: 1em; font-size: 8pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 8pt'>ASÍ LO RESOLVIÓ")
        //println("Todo")
        //println(par1+par2+par3+par4+par5+par6)
        //println()
        //println("Ahora el parrafo 6")
        //println(pa6)
        //println()
        /*println("<p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>"+pa6.replaceAll("\n\
\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>").replaceAll("\n\
","</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>")+"</p>")
        println()*/
        
        
        def texto=""
        def texto2=""
        def resultado=""
        texto+="\n\
"
        texto+="<p style='text-align: justify; line-height: 1em; font-size: 10pt'>"
        texto2+="<p style='text-align: right; line-height: 1em; font-size: 8pt'>"+"Oaxaca de Juárez,Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0]+"</p>"
        //texto2+="<p style='text-align: right; line-height: 1em; font-size: 8pt'>&nbsp;</p>"
        //texto+="<p style='text-align: left;'>&nbsp;</p>"
        
        //texto+=pa1+pa2+pa3+pa4+pa5+pa6+pa7
        texto=""
        resultado+=par1+par2+par3+par4+par5+par6+par7
        //println(resultado)
        resultado=resultado.replaceAll("ñ","&ntilde;")
        resultado=resultado.replaceAll("ó","&oacute;")
        resultado=resultado.replaceAll("í","&iacute;")
        resultado=resultado.replaceAll("á","&aacute;")
        resultado=resultado.replaceAll("é","&eacute;")
        resultado=resultado.replaceAll("ú","&uacute;")
        resultado=resultado.replaceAll("&deg;","°")
        //resultado=resultado.replaceAll("&nbsp;","")
        resultado=resultado.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 10pt'>                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O</p>","<p style='text-align: center; line-height: 1em; font-size: 10pt'>C  O  N  S  I  D  E  R  A  N  D  O</p>")
        resultado=resultado.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 10pt'>                                                                                                          R  E  S  U  E  L  V  E</p>","<p style='text-align: center; line-height: 1em; font-size: 10pt'>R  E  S  U  E  L  V  E</p>")
        texto+=resultado
        /*texto=texto.replaceAll("</p><p style='text-align: left; line-height: 1em; font-size: 10pt'>&nbsp;</p><p style='text-align: justify; line-height: 1em; font-size: 10pt'>","<p style='text-align: left; line-height: 1em; font-size: 10pt'></p>")
        texto=texto.replaceAll("\n\
","")
        texto=texto.replaceAll("</p>SEGUNDO","</p><br>SEGUNDO")
        texto=texto.replaceAll("</p>TERCERO","</p><br>TERCERO")
        texto=texto.replaceAll("</p>CUARTO","</p><br>CUARTO")
        texto=texto.replaceAll("<p style='text-align: left; line-height: 1em; font-size: 10pt'></p>                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","<br><p style='text-align: center; line-height: 1em; font-size: 10pt'>C O N S I D E R A N D O</p><br>")
        texto=texto.replaceAll("<p style='text-align: left; line-height: 1em; font-size: 10pt'></p>                                                                                                          R  E  S  U  E  L  V  E","<br><p style='text-align: center; line-height: 1em; font-size: 10pt'>R   E   S   U   E   L   V   E</p><br>")
        texto=texto.replaceAll("font-size: 10pt","font-size: 8pt")
        println("Aqui va despues del replace all")*/
        //println(resultado)
        texto=texto.replaceAll("font-size: 10pt","font-size: 8pt")
        //texto=texto.replaceAll("1em","0.7em")
        texto2+=texto
        def compruebaNota=Nota.findByExpanoAndExpro(scasolInstance.expano,scasolInstance.expro)
        if(compruebaNota.equals(null))
        {
            println("Se va a crear la nota porque no existe ninguna")
            def notaInstance= new Nota()
            notaInstance.nota=texto2
        notaInstance.expano=scasolInstance.expano
        notaInstance.expro=scasolInstance.expro
        notaInstance.ip=request.getRemoteAddr().toString()
        notaInstance.usuario=springSecurityService.currentUser.id
        
        if (!notaInstance.save(flush: true)) {
            //render(view: "create", model: [notaInstance: notaInstance])
            //return
            println("No se pudo guardar nada en nota")
        }
        }
        else
        {
            println("Solo se va a actualizar la nota existente")
            compruebaNota.nota=texto2
            if (!compruebaNota.save(flush: true)) {
            //render(view: "create", model: [notaInstance: notaInstance])
            //return
            println("No se pudo actualizar la nota")
        }
            
        }
        
        
        //notaInstance.save(flush: true)
//println("asdasdasdasd")
//println(mapa)
         
         //File tempToPrint = new File("c:\\reportes\\repge.pdf")
        //File tempToPrint = new File("/home/rcivil/outs/" + folioSolicitud + ".pdf")
        try{
            //FileUtils.writeByteArrayToFile(tempToPrint, jasperService.generateReport(reportDef).toByteArray())
           // println("se guardo en el disco")
            bytes = jasperService.generateReport(reportDef).toByteArray()
        }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        //render("Hola mundo")
        //println(mapa)
       
        //response.addHeader("Content-Disposition", 'attachment; filename="Exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
	response.addHeader("Content-Disposition", 'inline; filename="Exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
	
        //-response.contentType='application/pdf'
	response.outputStream << bytes
        //response.outputStream << render(file: new File("c:\\reportes\\repge.pdf"), fileName: "Resolucion.pdf",contentType: "application/pdf")
        response.outputStream.flush()
	//response.out << bytes
        
        //println("antes de cerrar")
        response.outputStream.close()
        return false
        //render("Hola mundo")
        //render(file: new File("c:\\reportes\\repge.pdf"), fileName: "Resolucion.pdf",contentType: "application/pdf")
        
        
//response.addHeader("Content-Disposition", 'attachment; filename="reso.pdf"')
	//response.contentType='application/pdf'
	//response.outputStream << bytes
	//response.out << bytes
       // response.outputStream.close()
        //return false
           
      
    }
    
      
    
    
    
    
def verificaPapeleta()
{
    println(params)
    render(template:"opciones")
}

def verificaResolucionArchivo()
{
    
    println(params)
    println("Llego al controlador de Archivo")
    //render("Hola mundo")
    render(template:"editaResolucionA",model:[notaInstance:Nota.findByExpanoAndExproAndIdn(params.anio2,params.expediente2,'C')])
    
}

    def verificaResolucionOficialia()
    {   
        def notaInstance=Nota.find("From Nota where expano=? and expro=? and (idn=? or idn=?)",[Integer.valueOf(params.anio2),Integer.valueOf(params.expediente2),'A'.toCharacter(),'O'.toCharacter()])
    println("Llego al controlador de Oficialia")
    //render("Hola mundo 2")
    render(template:"editarResolucionO",model:[notaInstance:notaInstance])
    
    }

    def verificaPapeleta2()
{

    println(params)
    render(template:"verifica")
}  
    
    
    
    
    
    
        

    
    def reimprimePapeleta()
    {
        println(params.expediente.substring(1,(params.expediente.size()-1)).replaceAll(" ","").split(",")[0])
        //println(params.anio.replaceAll(" ",""))
        println(params.anio.substring(1,(params.anio.size()-1)).replaceAll(" ","").split(",")[0])
        println(params.oficialia)
        println(params.archivo)
        println(params.notaInstance)
        def papeletaInstance
        println(params.expediente2)
        println(params.anio2)
        //[33, 33]
        //compruebaNota=Nota.find("From Nota where expano=? and expro=? and idn=?", [scasolInstance.expano,scasolInstance.expro,"A".toCharacter()])
                
        if(params.oficialia)
        {
            println("Viene para oficialia")
            //papeletaInstance=Papeleta.findByExpanoAndExproAndDonde(params.anio,params.expediente)
            papeletaInstance=Papeleta.find("From Papeleta where expano=? and expro=? and (donde=? or donde=?)",[Integer.valueOf(params.anio2),Integer.valueOf(params.expediente2),"A","O"])
        }
        if(params.archivo)
        {
            println("Viene para archivo")
            papeletaInstance=Papeleta.find("From Papeleta where expano=? and expro=?  and (donde=? or donde=?)",[Integer.valueOf(params.anio2),Integer.valueOf(params.expediente2),"A","C"])
        
        }
        /*if(params.archivo.equals() && params.oficialia.equals())
        {
            println("(X_X)")
            papeletaInstance=Papeleta.findByExpanoAndExpro(params.anio2,params.expano2)
        }*/
        if(!params.archivo && !params.oficialia)
        {
            println("SE ENVIA LA UNICA")
           //papeletaInstance=Papeleta.find("From Papeleta where expano=? and expro=?  and donde=? ",[Integer.valueOf(params.anio2),Integer.valueOf(params.expediente2),"A"])         
           papeletaInstance=Papeleta.findByExpanoAndExpro(params.anio2,params.expediente2)
           println(papeletaInstance.nota+" %&//&%%&//%&%%&/")
           if(papeletaInstance.donde.equals('O'))
           {
            params.oficialia=true   
           }
           if(papeletaInstance.donde.equals('C'))
           {params.archivo=true}
           if(papeletaInstance.donde.equals('A'))
           {
               params.oficialia=true
               params.archivo=true
           }
        }
        
        def scasolInstance=Scasol.findByExpanoAndExpro(params.anio2,params.expediente2)
        //papeletaInstance=Papeleta.findAllByExpanoAndExpro(params.anio,params.expediente)
        //def enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")
        //def enarchivo=EncargadoArchivo.find("From EncargadoArchivo where activo=true")
        def usua=User.findByUsername(scasolInstance.val.toString())
        
        def mapa=[]
       byte[] bytes
        
          mapa << [
           
                expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,
                nombreoficialia:"        "+"ARCHIVO CENTRAL",
            expediente2:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"        "+scasolInstance.ofi.toString()+", "+scasolInstance.dto.toString(),
                texto2:papeletaInstance.nota.replaceAll("&nbsp;","")+"\n\n\n",
                texto:papeletaInstance.nota.replaceAll("&nbsp;","")+"\n\n\n",
                boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id),
                encargadojuridico:"\nComprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString(),
                oficialia:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+", "+scasolInstance.dto.toString()    
            
         
                ]
        
//println("asdasdasdasd")
//println(mapa)
         //def reportDef
         System.out.println("entra2:::::::")
        if(papeletaInstance.donde.equals('A'))
            {
                System.out.println("entra aqui 1...");
            reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                        try{
            bytes = jasperService.generateReport(reportDef).toByteArray()
            byte[]a=bytes
            reportDef = new JasperReportDef(name: "imprimirTodo/texto2.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
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
        response.addHeader("Content-Disposition", 'inline; filename="Exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
	response.contentType='application/pdf'
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return false
                
            }
        else if(papeletaInstance.donde.equals('C')){
            System.out.println("entra aqui 2...");
                      mapa=[]
            
                      mapa << [
           
                expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,
                //expediente2:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"        "+scasolInstance.ofi.toString()+", "+scasolInstance.dto.toString(),
                nombreoficialia:"        "+"ARCHIVO CENTRAL",
                texto:papeletaInstance.nota.replaceAll("&nbsp;","")+"\n\n\n",
                //texto:papeletaInstance.nota.replaceAll("&nbsp;","")+"\n\n",
                boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id),
                encargadojuridico:"\nComprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString()
                //oficialia:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+", "+scasolInstance.dto.toString()    

          
                ]
                System.out.println((papeletaInstance.nota.replaceAll("&nbsp;","")+"\n\n").toString().length());
            reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                        try{
            bytes = jasperService.generateReport(reportDef).toByteArray()
            
                    }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
	response.contentType='application/pdf'
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return false
                    
            }
        else{
            System.out.println("entra aqui 3...");
                                  mapa=[]
            
                      mapa << [
           
                //expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano+"        "+"ARCHIVO CENTRAL",
                expediente:"Expediente No.: "+scasolInstance.expro+"/"+scasolInstance.expano,
                nombreoficialia:"        "+scasolInstance.ofi.toString()+", "+scasolInstance.dto.toString(),
                texto:papeletaInstance.nota.replaceAll("&nbsp;","")+"\n\n\n",
                //texto:papeletaInstance.nota.replaceAll("&nbsp;","")+"\n\n",
                boleta:"** Esta papeleta solamente es válida adherida al libro original **\n\
\n\
\n\
Elaboró: "+scasolInstance.cap.toString()+"/"+User.get(springSecurityService.currentUser.id),
                encargadojuridico:"\nComprobante pago de derechos:\n"+""+"\t                       Fecha:\n\n"+"EL JEFE DE LA UNIDAD JURIDICA.\n\
"+enjuridico.titulo+" "+enjuridico.toString(),
                /*encargado:"\n\n\n\nEL JEFE DEL ARCHIVO CENTRAL.\n\
"+enarchivo.titulo+" "+enarchivo.toString()*/
                encargado:"\n\n\n\nEL OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi.toString()+", "+scasolInstance.dto.toString()    

          
                ]
            
            reportDef = new JasperReportDef(name: "imprimirTodo/texto.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
            //reportDef = new JasperReportDef(name: "imprimirTodo/texto2.jasper",fileFormat:JasperExportFormat.PDF_FORMAT, reportData  : mapa )
                        try{
            bytes = jasperService.generateReport(reportDef).toByteArray()
            
                    }catch(Exception ex){
            println(ex.printStackTrace())
        } 
        response.addHeader("Content-Disposition", 'inline; filename="Exp "'+scasolInstance.expro+"/"+scasolInstance.expano+'".pdf"')
	response.contentType='application/pdf'
	response.outputStream << bytes
        response.outputStream.flush()
	response.outputStream.close()
        return false
            
        }
    }
    
     def buscar1dic(){

        [scasolInstanceList: Scasol.findAllByExproAndExpano(params.folio,params.anio), scasolInstanceTotal: Scasol.findAllByExproAndExpano(params.folio,params.anio).size() ]
    }     
    
    def buscar2dic(){
        //def max=Math.min(max ?: 10, 100)
        //params.max = Math.min(max ?: 10, 100)
        //params.nombresol=params.nombresol.replaceAll("  "," ")
        //println(params.nombresol+" &&&&&&&&&&&&&&&&&&&&&&&")
        //def valores = params.nombresol.split(' ')
        
        //def p = valores.length
        def lista
       
        if (params.nombre)
        {
            println("Tiene nombre")
            if(params.ape1 && params.ape2)
            {
             lista=Scasol.findAllByNom_intereLikeAndAp1_intereIlikeAndAp2_intereIlike(params.nombre,params.ape1,params.ape2)
            }
            else {
                if(params.ape1)
                {
                    println("Tiene primer apellido")
                 lista=Scasol.findAllByNom_intereLikeAndAp1_intereIlike(params.nombre,params.ape1)   
                }else if(params.ape2)
                {
                    println("Tiene segundo apellido")
                lista=Scasol.findAllByNom_intereLikeAndAp2_intereLike(params.nombre,params.ape2)       
                }
                else{
                  lista=Scasol.findAllByNom_intereLike(params.nombre)     
                }
            }
        }

        
        //def sql="";
        
        /*if(valores.length>3 )
        {
           lista=Scasol.findAllByNom_intereAndAp1_intereLikeAndAp2_intere("%"+valores[0]+" "+valores[1]+"%",valores[2],valores[3])  
        }else if(valores.length==3)
        {
         lista=Scasol.findAllByNom_intereLikeAndAp1_intereAndAp2_intere("%"+valores[0]+"%",valores[1],valores[2])   
        }
        else if(valores.length==2)
        {
           lista=Scasol.findAllByNom_intereAndAp1_intere(valores[0],valores[1])  
        }
        else
        {
            lista=Scasol.findAllByNom_intereLike("%"+params.nombresol+"%")  
        }
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
        }   */     

        
        //def result=lista.subList(params.offset,Math.min(params.offset+params.max, lista.size()))
        //[scasolInstanceList: result, scasolInstanceTotal: scasolEncontrado.size(),nombreint:params.nombreint]   
        
        
        [scasolInstanceList:lista , scasolInstanceTotal: lista.size()]       
    }
    
    def buscar3dic(){       
       println(params.nombresol)
       println("11111111111111")
         if (params.nombresol.length() != 0){
                     
             [scasolInstanceList: Scasol.findAllByFolio(params.nombresol), scasolInstanceTotal: 10] 
         }
         else{
             def p = 0 
             [scasolInstanceList: Scasol.findAllByFolio(p), scasolInstanceTotal: Scasol.count()] 
         }
    }
    
    
    def buscarporfecha(){       
            println(params.fechsol)
            println("33333333333333333")
            println(params.fechsol.toTimestamp() )
        if(Scasol.findAll("From Scasol where fechasol>=?",[params.fechsol.toTimestamp()]))
        {
            //[scasolInstanceList: Scasol.findAllByFechasolLike(params.fechsol+"%"), scasolInstanceTotal: Scasol.findAllByFechasol(params.fechsol).size()]
            [scasolInstanceList: Scasol.findAll("From Scasol where fechasol>=?",[params.fechsol.toTimestamp()]), scasolInstanceTotal: Scasol.findAll("From Scasol where fechasol>=?",[params.fechsol.toTimestamp()]).size()]
        }
        else 
        {
            [scasolInstanceList: Scasol.findAll("From Scasol where fechasol>=?",[params.fechsol.toTimestamp()]), scasolInstanceTotal: Scasol.findAll("From Scasol where fechasol>=?",[params.fechsol.toTimestamp()]).size()]
            //[scasolInstanceList: Scasol.findAllByFechasolLike(params.fechsol+"%"), scasolInstanceTotal: Scasol.count()]
            //return
            //redirect(action: "consulta", params: params)
        }
        
       
    }
    
    
    def buscarpromovio(){       
            println(params.nsolicita)
            println("/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*")
            def totales=Scasol.findAllByPromov(params.nsolicita)
            println(totales.size())
            
        if(totales.size()>0)
        {
            println(params.nsolicita)
            [scasolInstanceList: Scasol.findAllByPromov(params.nsolicita, [max: 10, offset: 0]), scasolInstanceTotal: Scasol.findAllByPromov(params.nsolicita).size()]
        }
        else 
        {
            //redirect(action: "consulta", params: params)
            [scasolInstanceList: Scasol.findAllByPromov(params.nsolicita, [max: 10, offset: 0]), scasolInstanceTotal: Scasol.count()]
            //return 
        }
                 
    }
    
    
    

    
    def consulta(){             
    }
    
    def buscaResolucion()
    {        
    }
    def buscaPapeleta()
    {        
    }
    
    def editarResolucion()
    {
        if(!isLoggedIn())
        {
            println("No Esta logeado")
            redirect(controller: "Logout")
            return
        }
        println("Buscando....")
        println(params.anio)
        println(params.expediente)
        def notaInstance
        try
        {
            notaInstance=Nota.findAllByExpanoAndExpro(params.anio,params.expediente)
        }
        catch(Exception e)
        {
            println(params.anio2)
            println(params.expediente2)
          notaInstance=Nota.findAllByExpanoAndExpro(params.anio2,params.expediente2) 
          e.printStackTrace()
        }
        def archivo
        def oficialia
        println(notaInstance.size()+" Total de busquedas")
        if(notaInstance.idn.equals("O") || notaInstance.idn.equals("A"))
        {
            archivo=false
            oficialia=true
        }
        else{
            oficialia=false
            archivo=true
        }
//        println(notaInstance)
notaInstance.each
{
 it.nota=it.nota.replaceAll("1em","2em")   
}

        
        try
        {
            if(params.flagofi)
        {
            oficialia=true
            archivo=false
        }
        }catch(Exception er)
        {
            er.printStackTrace()
        }
        

        //notaInstance=notaInstance.replaceAll("1em","2em")
        [notaInstance: notaInstance, notaInstanceTotal: notaInstance.size(),archivo:archivo,oficialia:oficialia ]
    
        
    }
    
    
    
        def editarResolucion2()
    {
        println(params)
        def scasolInstance
        scasolInstance=Scasol.get(params.id)
        println(scasolInstance.expano)
        println(scasolInstance.expro)
        def Considerando1=""
        def Considerando2=""
        def texto2=""
        
        Considerando1=opcionService.imprimeOficialia(params.id)
        Considerando2=opcionService.imprimeArchivo(params.id)
        def compruebaConsiderando
        compruebaConsiderando=Nota.findAllByExpanoAndExpro(scasolInstance.expano,scasolInstance.expro)
        if(compruebaConsiderando){
           
        if(compruebaConsiderando.size()>1)
        {
            compruebaConsiderando.each
            {
                it.delete(flush: true)
            }
        }else {compruebaConsiderando[0].delete(flush: true)}
        }
        def espacio="                                                                                                                                                      "
        def  enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")
        int longitud=(espacio.toString().length()-((enjuridico.titulo+" "+enjuridico).toString().length()))/2
         espacio=espacio.substring(0,longitud)
        def compruebaNota 
        if(!Considerando1.isEmpty() && !Considerando2.isEmpty())
        {
           def comparador=Considerando1.split("TERCERO")
           def comparador2=Considerando2.split("TERCERO") 
                   if(!comparador[1].isEmpty() && !comparador2[1].isEmpty())
        {
            println("Se va a mandar a los dos")
            if(comparador[1].equals(comparador2[1]))
            {
                compruebaNota=Nota.find("From Nota where expano=? and expro=? and idn=?", [scasolInstance.expano,scasolInstance.expro,"A".toCharacter()])
                println("Es la misma")
               Considerando2=Considerando2.replaceAll("C. JEFE DEL ARCHIVO CENTRAL DEL REGISTRO CIVIL","C. OFICIAL DEL REGISTRO CIVIL DE "+scasolInstance.ofi+", "+scasolInstance.dto+", OAXACA Y AL "+"C. JEFE DEL ARCHIVO CENTRAL DEL REGISTRO CIVIL")
               texto2="<p style='text-align: right; line-height: 1em; font-size: 8pt'>Oaxaca de Júarez Oaxaca a "+hoy2[2]+" de "+meses2[Integer.valueOf(hoy2[1])]+" de "+hoy2[0]+"</p>"+"<p>"+Considerando2.replaceAll("\n\
","</p><p>")
        texto2=texto2.replaceAll("<p>","<p style='text-align: justify; line-height: 1em; font-size: 8pt'>")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","<p style='text-align: center; line-height: 1em; font-size: 8pt'>C  O  N  S  I  D  E  R  A  N  D  O")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                          R  E  S  U  E  L  V  E</p>","<p style='text-align: center; line-height: 1em; font-size: 8pt'>R  E  S  U  E  L  V  E</p>")
        
                if(compruebaNota.equals(null))
        {
            println("Se va a crear la nota porque no existe ninguna")
            def notaInstance= new Nota()
            notaInstance.nota=texto2.replaceAll(", y"," y ")
        notaInstance.expano=scasolInstance.expano
        notaInstance.expro=scasolInstance.expro
        notaInstance.idn='A'
        notaInstance.ip=request.getRemoteAddr().toString()
        notaInstance.usuario=springSecurityService.currentUser.id
        if (!notaInstance.save(flush: true)) {           
            println("No se pudo guardar nada en nota")
        }
        }
        else
        {
            println("Solo se va a actualizar la nota existente")
            compruebaNota.nota=texto2.replaceAll(", y"," y ")
            if (!compruebaNota.save(flush: true)) {            
            println("No se pudo actualizar la nota")
        }
            
        }
                    
                    
                    
                    

        


            }
            else 
            {
                
                    
             compruebaNota=Nota.find("From Nota where expano=? and expro=? and idn=?", [scasolInstance.expano,scasolInstance.expro,"C".toCharacter()])       
texto2="<p style='text-align: right; line-height: 1em; font-size: 8pt'>Oaxaca de Júarez Oaxaca a "+hoy2[2]+" de "+meses2[Integer.valueOf(hoy2[1])]+" de "+hoy2[0]+"</p>"+"<p>"+Considerando2.replaceAll("\n\
","</p><p>")
        texto2=texto2.replaceAll("<p>","<p style='text-align: justify; line-height: 1em; font-size: 8pt'>")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","<p style='text-align: center; line-height: 1em; font-size: 8pt'>C  O  N  S  I  D  E  R  A  N  D  O")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                          R  E  S  U  E  L  V  E</p>","<p style='text-align: center; line-height: 1em; font-size: 8pt'>R  E  S  U  E  L  V  E</p>")
        if(compruebaNota.equals(null))
        {
            println("Se va a crear la nota porque no existe ninguna")
            def notaInstance= new Nota()
            notaInstance.nota=texto2.replaceAll(", y"," y ")
        notaInstance.expano=scasolInstance.expano
        notaInstance.expro=scasolInstance.expro
        notaInstance.idn='C'
        notaInstance.ip=request.getRemoteAddr().toString()
        notaInstance.usuario=springSecurityService.currentUser.id
        if (!notaInstance.save(flush: true)) {
            println("No se pudo guardar nada en nota")
        }
        }
        else
        {
            println("Solo se va a actualizar la nota existente")
            compruebaNota.nota=texto2.replaceAll(", y"," y ")
            if (!compruebaNota.save(flush: true)) {
            println("No se pudo actualizar la nota")
        }
            
        }
        
              compruebaNota=Nota.find("From Nota where expano=? and expro=? and idn=?", [scasolInstance.expano,scasolInstance.expro,"O".toCharacter()])      
                    
                    texto2="<p style='text-align: right; line-height: 1em; font-size: 8pt'>Oaxaca de Júarez Oaxaca a "+hoy2[2]+" de "+meses2[Integer.valueOf(hoy2[1])]+" de "+hoy2[0]+"</p>"+"<p>"+Considerando1.replaceAll("\n\
","</p><p>")
        texto2=texto2.replaceAll("<p>","<p style='text-align: justify; line-height: 1em; font-size: 8pt'>")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","<p style='text-align: center; line-height: 1em; font-size: 8pt'>C  O  N  S  I  D  E  R  A  N  D  O")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                          R  E  S  U  E  L  V  E</p>","<p style='text-align: center; line-height: 1em; font-size: 8pt'>R  E  S  U  E  L  V  E</p>")
        if(compruebaNota.equals(null))
        {
            println("Se va a crear la nota porque no existe ninguna")
            def notaInstance= new Nota()
            notaInstance.nota=texto2.replaceAll(",  y 142"," y 142")
        notaInstance.expano=scasolInstance.expano
        notaInstance.expro=scasolInstance.expro
        notaInstance.idn='O'
        notaInstance.ip=request.getRemoteAddr().toString()
        notaInstance.usuario=springSecurityService.currentUser.id
        if (!notaInstance.save(flush: true)) {
            //render(view: "create", model: [notaInstance: notaInstance])
            //return
            println("No se pudo guardar nada en nota")
        }
        }
        else
        {
            println("Solo se va a actualizar la nota existente")
            compruebaNota.nota=texto2.replaceAll(",  y 142"," y 142")
            if (!compruebaNota.save(flush: true)) {
            //render(view: "create", model: [notaInstance: notaInstance])
            //return
            println("No se pudo actualizar la nota")
        }
            
        }

            }
        }
        }
        else
        {
            println("Solo va para uno")
            if(!Considerando1.isEmpty())
            {
                compruebaNota=Nota.find("From Nota where expano=? and expro=? and idn=?", [scasolInstance.expano,scasolInstance.expro,"O".toCharacter()])      
                    
                    texto2="<p style='text-align: right; line-height: 1em; font-size: 8pt'>Oaxaca de Júarez Oaxaca a "+hoy2[2]+" de "+meses2[Integer.valueOf(hoy2[1])]+" de "+hoy2[0]+"</p>"+"<p>"+Considerando1.replaceAll("\n\
","</p><p>")
        texto2=texto2.replaceAll("<p>","<p style='text-align: justify; line-height: 1em; font-size: 8pt'>")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","<p style='text-align: center; line-height: 1em; font-size: 8pt'>C  O  N  S  I  D  E  R  A  N  D  O")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                          R  E  S  U  E  L  V  E</p>","<p style='text-align: center; line-height: 1em; font-size: 8pt'>R  E  S  U  E  L  V  E</p>")
        
                if(compruebaNota.equals(null))
        {
            println("Se va a crear la nota porque no existe ninguna")
            def notaInstance= new Nota()
            notaInstance.nota=texto2.replaceAll(",  y 142"," y 142")
        notaInstance.expano=scasolInstance.expano
        notaInstance.expro=scasolInstance.expro
        notaInstance.idn='O'
        notaInstance.ip=request.getRemoteAddr().toString()
        notaInstance.usuario=springSecurityService.currentUser.id
        if (!notaInstance.save(flush: true)) {
           
            println("No se pudo guardar nada en nota")
        }
        }
        else
        {
            println("Solo se va a actualizar la nota existente")
            compruebaNota.nota=texto2.replaceAll(",  y 142"," y 142")
            if (!compruebaNota.save(flush: true)) {
            
            println("No se pudo actualizar la nota")
        }
            
        }

        

         
            }else
            {
                             compruebaNota=Nota.find("From Nota where expano=? and expro=? and idn=?", [scasolInstance.expano,scasolInstance.expro,"C".toCharacter()])       
texto2="<p style='text-align: right; line-height: 1em; font-size: 8pt'>Oaxaca de Júarez Oaxaca a "+hoy2[2]+" de "+meses2[Integer.valueOf(hoy2[1])]+" de "+hoy2[0]+"</p>"+"<p>"+Considerando2.replaceAll("\n\
","</p><p>")
        texto2=texto2.replaceAll("<p>","<p style='text-align: justify; line-height: 1em; font-size: 8pt'>")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                             C  O  N  S  I  D  E  R  A  N  D  O","<p style='text-align: center; line-height: 1em; font-size: 8pt'>C  O  N  S  I  D  E  R  A  N  D  O")
        texto2=texto2.replaceAll("<p style='text-align: justify; line-height: 1em; font-size: 8pt'>                                                                                                          R  E  S  U  E  L  V  E</p>","<p style='text-align: center; line-height: 1em; font-size: 8pt'>R  E  S  U  E  L  V  E</p>")
        //println(guardatexto1)
                if(compruebaNota.equals(null))
        {
            println("Se va a crear la nota porque no existe ninguna")
            def notaInstance= new Nota()
            notaInstance.nota=texto2.replaceAll(",  y 142"," y 142")
        notaInstance.expano=scasolInstance.expano
        notaInstance.expro=scasolInstance.expro
        notaInstance.idn='C'
        notaInstance.ip=request.getRemoteAddr().toString()
        notaInstance.usuario=springSecurityService.currentUser.id
        if (!notaInstance.save(flush: true)) {
            println("No se pudo guardar nada en nota")
        }
        }
        else
        {
            println("Solo se va a actualizar la nota existente")
            compruebaNota.nota=texto2.replaceAll(",  y 142"," y 142")
            if (!compruebaNota.save(flush: true)) {
            println("No se pudo actualizar la nota")
        }
            
        }

            }

        }
        
        
        

        println(params)
        def papeletaOficialia=""
        def papeletaArchivo=""
        String [] oficialiaa
        def mapa=[]
        byte[] bytes
        //def reportDef
        def usuarioInstance=User.get(params.usuario1)

        def reviso=""
        def compruebaPapeleta
        compruebaPapeleta=Papeleta.findAllByExpanoAndExpro(scasolInstance.expano,scasolInstance.expro)
            new File("D:/codigo.png").withOutputStream { out ->
    barcode4jService.render("code39Generator", scasolInstance.expro+"/"+scasolInstance.expano, out, "image/png")
}
        if(compruebaPapeleta){
           
        if(compruebaPapeleta.size()>1)
        {
            compruebaPapeleta.each
            {
                it.delete(flush: true)
            }
        }else {compruebaPapeleta[0].delete(flush: true)}
        }
        try{
            reviso+=usuarioInstance.nombre.substring(0,1)
        }catch(Exception e){}
        
        try{
            reviso+=usuarioInstance.apellpa.substring(0,1)
        }catch(Exception e){}
        try {
            reviso+=usuarioInstance.apellma.substring(0,1)
        }catch(Exception e){}
        try
        {
            oficialiaa=opcionService.imprimeOficialia(params.id).split("\\.\\.\\.")
            if(oficialiaa[1].isEmpty())
            {
                println("La oficialia no trae nada")
            }
            papeletaOficialia=oficialiaa[1]
        }catch(Exception e){}
                try
        {
            oficialiaa=opcionService.imprimeArchivo(params.id).split("\\.\\.\\.")
            papeletaArchivo=oficialiaa[1]
        }catch(Exception e){}
        
        //papeletaOficialia=imprimeOficialia(params.id).split("\\.\\.\\.",1)
        println("---------------------------")
        println(papeletaOficialia)
        println("---------------------------")
        println(papeletaArchivo)
        println("---------------------------")
        def papeletaSave
        def papeletaInstance
        if(!papeletaOficialia.isEmpty() && !papeletaArchivo.isEmpty())
        {
           
           
           if(papeletaOficialia.toString().equals(papeletaArchivo.toString()))
           {
               println("Es la misma papeleta")
               papeletaSave=Papeleta.findByExpanoAndExpro(scasolInstance.expano,scasolInstance.expro)
               if(papeletaSave.equals(null))
               {   
                   papeletaInstance = new Papeleta()
                   papeletaInstance.nota="\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"")+"...\"".replaceAll(", y"," y ")
                   papeletaInstance.expano=scasolInstance.expano
                   papeletaInstance.expro=scasolInstance.expro
                   papeletaInstance.donde="A"
                   papeletaInstance.ip=request.getRemoteAddr().toString()
                   papeletaInstance.usuario=springSecurityService.currentUser.id
                   if (!papeletaInstance.save(flush: true)) {
                    //render(view: "create", model: [notaInstance: notaInstance])
                    //return
                    println("No se pudo guardar nada en papelta")
                }
               }
               else
               {
                    println("Solo se va a actualizar la nota existente")
                    papeletaSave.nota="\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"")+"...\"".replaceAll(", y"," y ")
                    papeletaSave.donde="A"                   
                    if (!papeletaSave.save(flush: true)) {                   
                    println("No se pudo actualizar la papeleta")
                }
               }
           }
           else
           {
               println("Son diferentes papeletas")
               papeletaSave=Papeleta.findByExpanoAndExproAndDonde(scasolInstance.expano,scasolInstance.expro,"C")
               if(papeletaSave.equals(null))
               {
                   //papeletaInstance  = new Papeleta()
                   papeletaInstance = new Papeleta()
                   papeletaInstance.nota="\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"")+"...\"".replaceAll(", y"," y ")
                   papeletaInstance.expano=scasolInstance.expano
                   papeletaInstance.expro=scasolInstance.expro
                   papeletaInstance.donde="C"
                   papeletaInstance.ip=request.getRemoteAddr().toString()
                   papeletaInstance.usuario=springSecurityService.currentUser.id
                   if (!papeletaInstance.save(flush: true)) {
                    //render(view: "create", model: [notaInstance: notaInstance])
                    //return
                    println("No se pudo guardar nada en papelta")
                }
               }
               else
               {
                   papeletaSave.nota="\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142")+"...\"".replaceAll(", y"," y ")
                   if (!papeletaSave.save(flush: true)) {                   
                    println("No se pudo actualizar la papeleta")
               }
               }
               papeletaSave=Papeleta.findByExpanoAndExproAndDonde(scasolInstance.expano,scasolInstance.expro,"O")
               if(papeletaSave.equals(null))
               {
                   //papeletaInstance  = new Papeleta()
                   papeletaInstance = new Papeleta()
                   papeletaInstance.nota="\"..."+papeletaOficialia.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"")+"...\"".replaceAll(", y"," y ")
                   papeletaInstance.expano=scasolInstance.expano
                   papeletaInstance.expro=scasolInstance.expro
                   papeletaInstance.donde="O"
                   papeletaInstance.ip=request.getRemoteAddr().toString()
                   papeletaInstance.usuario=springSecurityService.currentUser.id
                   if (!papeletaInstance.save(flush: true)) {
                    //render(view: "create", model: [notaInstance: notaInstance])
                    //return
                    println("No se pudo guardar nada en papelta")
                }
               }
               else
               {
                   papeletaSave.nota="\"..."+papeletaOficialia.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"")+"...\"".replaceAll(", y"," y ")
                   if (!papeletaSave.save(flush: true)) {                   
                    println("No se pudo actualizar la papeleta")
               }
           }
        
        }
        if(!papeletaOficialia.isEmpty() && !papeletaArchivo.isEmpty())
        {
             
            
            println("Se envian papeletas al Archivo Central y la Oficialia")


        }
        
        }
        
        else {
            println("Solo va para una")
            if(!papeletaOficialia.isEmpty())
            {
                
                
               papeletaSave=Papeleta.findByExpanoAndExproAndDonde(scasolInstance.expano,scasolInstance.expro,"O")
               if(papeletaSave.equals(null))
               {   
                   papeletaInstance = new Papeleta()
                   papeletaInstance.nota="\"..."+papeletaOficialia.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"")+"...\"".replaceAll(", y"," y ")
                   papeletaInstance.expano=scasolInstance.expano
                   papeletaInstance.expro=scasolInstance.expro
                   papeletaInstance.donde="O"
                   papeletaInstance.ip=request.getRemoteAddr().toString()
                   papeletaInstance.usuario=springSecurityService.currentUser.id
                   if (!papeletaInstance.save(flush: true)) {
                    //render(view: "create", model: [notaInstance: notaInstance])
                    //return
                    println("No se pudo guardar nada en papelta")
                }
               }
               else
               {
                    println("Solo se va a actualizar la nota existente")
                    papeletaSave.nota="\"..."+papeletaOficialia.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"")+"...\"".replaceAll(", y"," y ")
                    papeletaSave.donde="O"                   
                    if (!papeletaSave.save(flush: true)) {                   
                    println("No se pudo actualizar la papeleta")
                }
               }
                
                
                println("Solo va la papeleta para la Oficialia")

                
            }else {
                
               papeletaSave=Papeleta.findByExpanoAndExproAndDonde(scasolInstance.expano,scasolInstance.expro,"C")
               if(papeletaSave.equals(null))
               {   
                   papeletaInstance = new Papeleta()
                   papeletaInstance.nota="\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"")+"...\"".replaceAll(", y"," y ")
                   papeletaInstance.expano=scasolInstance.expano
                   papeletaInstance.expro=scasolInstance.expro
                   papeletaInstance.donde="C"
                   papeletaInstance.ip=request.getRemoteAddr().toString()
                   papeletaInstance.usuario=springSecurityService.currentUser.id
                   if (!papeletaInstance.save(flush: true)) {
                    //render(view: "create", model: [notaInstance: notaInstance])
                    //return
                    println("No se pudo guardar nada en papelta")
                }
               }
               else
               {
                    println("Solo se va a actualizar la nota existente")
                    papeletaSave.nota="\"..."+papeletaArchivo.replaceAll(",  y 142"," y 142").replaceAll("&quot;","\"")+"...\"".replaceAll(", y"," y ")
                    papeletaSave.donde="C"                   
                    if (!papeletaSave.save(flush: true)) {                   
                    println("No se pudo actualizar la papeleta")
                }
               }
                println("Solo va la papeleta para el Archivo Central")

                
            }

        }

        
        println("Buscando....")
        println(scasolInstance.expano)
        println(scasolInstance.expro)
        def notaInstance
        try
        {
            notaInstance=Nota.findAllByExpanoAndExpro(scasolInstance.expano,scasolInstance.expro)
        }
        catch(Exception e)
        {
            println(params.anio2)
            println(params.expediente2)
          notaInstance=Nota.findAllByExpanoAndExpro(scasolInstance.expano,scasolInstance.expro) 
          e.printStackTrace()
        }
        def archivo
        def oficialia
        println(notaInstance.size()+" Total de busquedas")
        if(notaInstance.idn.equals("O") || notaInstance.idn.equals("A"))
        {
            archivo=false
            oficialia=true
        }
        else{
            oficialia=false
            archivo=true
        }
//        println(notaInstance)
        notaInstance.each
        {
            it.nota=it.nota.replaceAll("1em","2em")
            it.nota=it.nota.replaceAll(",  y 142"," y 142")
        }
        [notaInstance: notaInstance, notaInstanceTotal: notaInstance.size(),archivo:archivo,oficialia:oficialia,id:params.id ]
    
        
    }
    
      def editarPapeleta()
    {
        if(!isLoggedIn())
        {
            println("No Esta logeado")
            redirect(controller: "Logout")
            return
        }
        println("Buscando....")
        println(params.anio)
        println(params.expediente)
        def notaInstance=Papeleta.findAllByExpanoAndExpro(params.anio,params.expediente)
//        println(notaInstance)
        //[notaInstance: Papeleta.findByExpanoAndExpro(params.anio,params.expediente), notaInstanceTotal: Papeleta.findAllByExpanoAndExpro(params.anio,params.expediente).size() ]
        [notaInstance: notaInstance, notaInstanceTotal: notaInstance.size() ]
    
        
    }
    
    /*def editarResolucion(int anio, int expediente)
    {
        println(anio)
        println(expediente)
        def notaInstance=Nota.findByExpanoAndExpro(anio,expediente)
        println(notaInstance)
        [notaInstance: Nota.findByExpanoAndExpro(anio,expediente), notaInstanceTotal: Nota.findAllByExpanoAndExpro(anio,expediente).size() ]
    
        
    }*/
    def update3()
    {
        println(params.expediente)
        println(params.anio)
        println(params.expediente2)
        println(params.anio2)
        println("Vamos a ver si viene para archivo o para oficialia")
        println(params.oficialia)
        println("Arriba va oficialia")
        println(params.archivo)
        println("Arriba va archivo")
        //println("Vamos bien")
        //println(params.nota)
        def nuevanota=""
        
        nuevanota=params.nota
        println("-------------------")
//        println(params.nota)
        println("-------------------")
       // println(nuevanota)
       
        
       //println("*-/*/*/*/*/*/*/*/*/")
        //println(nuevanota)
   
       
        //println("Voy a mostar la nota sin codigo html")
        String cleaned = cleanHtml(nuevanota, 'simpleText') 
        cleaned=cleaned.replaceAll("&Iacute;","Í")
        cleaned=cleaned.replaceAll("&Aacute;","Á")
        cleaned=cleaned.replaceAll("&Eacute;","É")
        cleaned=cleaned.replaceAll("&Uacute;","Ú")
        cleaned=cleaned.replaceAll("&Ntilde;","Ñ")
        cleaned=cleaned.replaceAll("&Oacute;","Ó")
        cleaned=cleaned.replaceAll("&uacute;","ú")
        cleaned=cleaned.replaceAll("&ntilde;","ñ")
        cleaned=cleaned.replaceAll("&oacute;","ó")
        cleaned=cleaned.replaceAll("&iacute;","í")
        cleaned=cleaned.replaceAll("&aacute;","á")
        cleaned=cleaned.replaceAll("&eacute;","é")
        cleaned=cleaned.replaceAll("&quot;","\"")
        cleaned=cleaned.replaceAll("&deg;","°")
        //cleaned=cleaned.replaceAll("...Con fundamento por lo dispuesto en los","=...Con fundamento por lo dispuesto en los")
        /*cleaned=cleaned.replaceAll("&nbsp;","\n\
")*/
        //println(cleaned)
        def buscapapeleta=cleaned.split("\\.\\.\\.")
        println(buscapapeleta.length)
        //println(buscapapeleta.length+" Aqui va el tamaño de buscapapeleta")
       
        def guardaNota
        try
        {
             if(params.oficialia)
        {
           //guardaNota=Nota.findByExpanoAndExproAndIdn(params.anio2,params.expediente2) 
             guardaNota=Nota.find("From Nota where expano=? and expro=? and (idn=? or idn=?)",[Integer.valueOf(params.anio2),Integer.valueOf(params.expediente2),'A'.toCharacter(),'O'.toCharacter()])
        }
        else
        {
           //guardaNota=Nota.findByExpanoAndExproAndIdn(params.anio2,params.expediente2)
           guardaNota=Nota.find("From Nota where expano=? and expro=? and (idn=? or idn=?)",[Integer.valueOf(params.anio2),Integer.valueOf(params.expediente2),'A'.toCharacter(),'C'.toCharacter()])
           if(!guardaNota)
           {
              guardaNota=Nota.findByExpanoAndExpro(Integer.valueOf(params.anio2),Integer.valueOf(params.expediente2)) //("From Nota where expano=? and expro=?",[Integer.valueOf(params.anio2),Integer.valueOf(params.expediente2)])
             
           }
         }
        }
        catch(Exception a){
            a.printStackTrace()
            guardaNota=Nota.findByExpanoAndExpro(Integer.valueOf(params.anio2),Integer.valueOf(params.expediente2)) //("From Nota where expano=? and expro=?",[Integer.valueOf(params.anio2),Integer.valueOf(params.expediente2)])
            println("Entro al catch")
        }       
        guardaNota.nota=params.nota
        guardaNota.ip=request.getRemoteAddr().toString()
        guardaNota.usuario=springSecurityService.currentUser.id
        if(!guardaNota.save(flush:true))
        {
            println("Error no se puedieron guardar los cambios")
        }
        def papeleta=Papeleta.findByExpanoAndExpro(params.anio2,params.expediente2)
        //=new Papeleta()
        if(!papeleta || papeleta.equals(null))
        {
            papeleta=new Papeleta()
            papeleta.nota="...\""+buscapapeleta[1]+"...\""
        papeleta.expano=Integer.valueOf(params.anio2)
        papeleta.expro=Integer.valueOf(params.expediente2)
        papeleta.ip=request.getRemoteAddr().toString()
        papeleta.usuario=springSecurityService.currentUser.id
        if(!papeleta.save(flush : true))
        {
            println("No se guardo la papeleta")
        }
        }
        else
        {
            println("Se va a actualizar la papeleta existente")
            papeleta.nota="\"..."+buscapapeleta[1]+"...\""
            if(!papeleta.save(flush:true))
            {
                println("No se pudo guardar la papeleta nueva")
            }
        }
        
        //return
        redirect(controller:'Opcion', action:'editarResolucion',params:[anio:params.anio2,expediente:params.expediente2]) 
        //buscaResolucion(params.anio,params.anio)
        //chain action: 'buscaResolucion', model: [params:params]
    }

    
        def update3Sinespacios()
    {
        println(params.expediente)
        println(params.anio)
        println("******************")
        //println(params)
        println("******************")
       /* try
        {
            if(params.oficialia)
            {
                
            }
            else {
                
                if(params.archivo)
                {
                    
                }
            }
        }catch(Exception r)
        {
            r.printStackTrace()
        }*/
        println(params.oficialia)
        println(params.archivo)
        //println("Vamos bien")
        //println(params.nota)
        def nuevanota=""
        
        nuevanota=params.nota
        println("-------------------")
//        println(params.nota)
        println("-------------------")
       // println(nuevanota)
       
        
       //println("*-/*/*/*/*/*/*/*/*/")
        //println(nuevanota)
   
        nuevanota=nuevanota.replaceAll("2em","1em")
        //println("Voy a mostar la nota sin codigo html")
        String cleaned = cleanHtml(nuevanota, 'simpleText') 
        cleaned=cleaned.replaceAll("&Iacute;","Í")
        cleaned=cleaned.replaceAll("&Aacute;","Á")
        cleaned=cleaned.replaceAll("&Eacute;","É")
        cleaned=cleaned.replaceAll("&Uacute;","Ú")
        cleaned=cleaned.replaceAll("&Ntilde;","Ñ")
        cleaned=cleaned.replaceAll("&Oacute;","Ó")
        cleaned=cleaned.replaceAll("&uacute;","ú")
        cleaned=cleaned.replaceAll("&ntilde;","ñ")
        cleaned=cleaned.replaceAll("&oacute;","ó")
        cleaned=cleaned.replaceAll("&iacute;","í")
        cleaned=cleaned.replaceAll("&aacute;","á")
        cleaned=cleaned.replaceAll("&eacute;","é")
        cleaned=cleaned.replaceAll("&quot;","\"")
        //cleaned=cleaned.replaceAll("...Con fundamento por lo dispuesto en los","=...Con fundamento por lo dispuesto en los")
        /*cleaned=cleaned.replaceAll("&nbsp;","\n\
")*/
        //println(cleaned)
        def buscapapeleta=cleaned.split("\\.\\.\\.")
        println(buscapapeleta.length)
        //println(buscapapeleta.length+" Aqui va el tamaño de buscapapeleta")
       
        def guardaNota //=Nota.findByExpanoAndExpro(params.anio2,params.expediente2)
        if(params.oficialia)
        {
            guardaNota=Nota.findByExpanoAndExproAndIdn(params.anio2,params.expediente2,'O')
        }
        else{
            if(params.archivo)
            {
              guardaNota=Nota.findByExpanoAndExproAndIdn(params.anio2,params.expediente2,'C')  
            }
            else {
                guardaNota=Nota.findByExpanoAndExpro(params.anio2,params.expediente2)
            }
        }
        //guardaNota.nota=guardaNota.nota.replaceAll("2em","1em")
        params.nota=params.nota.replaceAll("2em","1em")
        guardaNota.nota=params.nota.replaceAll("<p style=\"text-align: justify; line-height: 1em; font-size: 8pt\">&nbsp;</p>","")
        guardaNota.ip=request.getRemoteAddr().toString()
        guardaNota.usuario=springSecurityService.currentUser.id
        if(!guardaNota.save(flush:true))
        {
            println("Error no se puedieron guardar los cambios")
        }
        def papeleta //=Papeleta.findByExpanoAndExpro(params.anio2,params.expediente2)
                if(params.oficialia)
        {
            papeleta=Papeleta.findByExpanoAndExproAndDonde(params.anio2,params.expediente2,'O')
            //guardaNota=Nota.findByExpanoAndExproAndIdn(params.anio2,params.expediente2,'O')
        }
        else{
            if(params.archivo)
            {
              papeleta=Papeleta.findByExpanoAndExproAndDonde(params.anio2,params.expediente2,'C')
              //guardaNota=Nota.findByExpanoAndExproAndIdn(params.anio2,params.expediente2,'C')  
            }
            else {
                papeleta=Papeleta.findByExpanoAndExpro(params.anio2,params.expediente2)
                //guardaNota=Nota.findByExpanoAndExpro(params.anio2,params.expediente2)
            }
        }
        //=new Papeleta()
        if(!papeleta || papeleta.equals(null))
        {
            papeleta=new Papeleta()
            papeleta.nota="...\""+buscapapeleta[1]+"...\""
        papeleta.expano=Integer.valueOf(params.anio)
        papeleta.expro=Integer.valueOf(params.expediente)
        papeleta.ip=request.getRemoteAddr().toString()
        papeleta.usuario=springSecurityService.currentUser.id
        if(!papeleta.save(flush : true))
        {
            println("No se guardo la papeleta")
        }
        }
        else
        {
            println("Se va a actualizar la papeleta existente")
            papeleta.nota="\"..."+buscapapeleta[1]+"...\""
            if(!papeleta.save(flush:true))
            {
                println("No se pudo guardar la papeleta nueva")
            }
        }
        
        //return
        redirect(controller:'Opcion', action:'editarResolucion',params:[anio:params.anio2,expediente:params.expediente2]) 
        //buscaResolucion(params.anio,params.anio)
        //chain action: 'buscaResolucion', model: [params:params]
    }
    
    
    def update3SinespaciosOfi()
    {
        println(params.expediente)
        println(params.anio)
        //println("Vamos bien")
        //println(params.nota)
        def nuevanota=""
        
        nuevanota=params.nota
        println("-------------------")
//        println(params.nota)
        println("-------------------")
       // println(nuevanota)
       
        
       //println("*-/*/*/*/*/*/*/*/*/")
        //println(nuevanota)
   
       
        //println("Voy a mostar la nota sin codigo html")
        String cleaned = cleanHtml(nuevanota, 'simpleText') 
        cleaned=cleaned.replaceAll("&Iacute;","Í")
        cleaned=cleaned.replaceAll("&Aacute;","Á")
        cleaned=cleaned.replaceAll("&Eacute;","É")
        cleaned=cleaned.replaceAll("&Uacute;","Ú")
        cleaned=cleaned.replaceAll("&Ntilde;","Ñ")
        cleaned=cleaned.replaceAll("&Oacute;","Ó")
        cleaned=cleaned.replaceAll("&uacute;","ú")
        cleaned=cleaned.replaceAll("&ntilde;","ñ")
        cleaned=cleaned.replaceAll("&oacute;","ó")
        cleaned=cleaned.replaceAll("&iacute;","í")
        cleaned=cleaned.replaceAll("&aacute;","á")
        cleaned=cleaned.replaceAll("&eacute;","é")
        cleaned=cleaned.replaceAll("&quot;","\"")
        //cleaned=cleaned.replaceAll("...Con fundamento por lo dispuesto en los","=...Con fundamento por lo dispuesto en los")
        /*cleaned=cleaned.replaceAll("&nbsp;","\n\
")*/
        //println(cleaned)
        def buscapapeleta=cleaned.split("\\.\\.\\.")
        println(buscapapeleta.length)
        //println(buscapapeleta.length+" Aqui va el tamaño de buscapapeleta")
       
        def guardaNota=Nota.findByExpanoAndExproAndIdn(params.anio2,params.expediente2,'O')
        params.nota=params.nota.replaceAll("2em","1em")
        guardaNota.nota=params.nota.replaceAll("<p style=\"text-align: justify; line-height: 1em; font-size: 8pt\">&nbsp;</p>","")
        guardaNota.ip=request.getRemoteAddr().toString()
        guardaNota.usuario=springSecurityService.currentUser.id
        if(!guardaNota.save(flush:true))
        {
            println("Error no se puedieron guardar los cambios")
        }
        def papeleta=Papeleta.findByExpanoAndExproAndDonde(params.anio2,params.expediente2,'O')
        //=new Papeleta()
        if(!papeleta || papeleta.equals(null))
        {
            papeleta=new Papeleta()
            papeleta.nota="...\""+buscapapeleta[1]+"...\""
        papeleta.expano=Integer.valueOf(params.anio)
        papeleta.expro=Integer.valueOf(params.expediente)
        papeleta.ip=request.getRemoteAddr().toString()
        papeleta.usuario=springSecurityService.currentUser.id
        if(!papeleta.save(flush : true))
        {
            println("No se guardo la papeleta")
        }
        }
        else
        {
            println("Se va a actualizar la papeleta existente")
            papeleta.nota="\"..."+buscapapeleta[1]+"...\""
            if(!papeleta.save(flush:true))
            {
                println("No se pudo guardar la papeleta nueva")
            }
        }
        
        //return
        redirect(controller:'Opcion', action:'editarResolucion',params:[anio:params.anio2,expediente:params.expediente2]) 
        //buscaResolucion(params.anio,params.anio)
        //chain action: 'buscaResolucion', model: [params:params]
    }
    
    def update3SinespaciosArc()
    {
        println(params.expediente)
        println(params.anio)
        //println("Vamos bien")
        //println(params.nota)
        def nuevanota=""
        
        nuevanota=params.nota
        println("-------------------")
//        println(params.nota)
        println("-------------------")
       // println(nuevanota)
       
        
       //println("*-/*/*/*/*/*/*/*/*/")
        //println(nuevanota)
   
       
        //println("Voy a mostar la nota sin codigo html")
        String cleaned = cleanHtml(nuevanota, 'simpleText') 
        cleaned=cleaned.replaceAll("&Iacute;","Í")
        cleaned=cleaned.replaceAll("&Aacute;","Á")
        cleaned=cleaned.replaceAll("&Eacute;","É")
        cleaned=cleaned.replaceAll("&Uacute;","Ú")
        cleaned=cleaned.replaceAll("&Ntilde;","Ñ")
        cleaned=cleaned.replaceAll("&Oacute;","Ó")
        cleaned=cleaned.replaceAll("&uacute;","ú")
        cleaned=cleaned.replaceAll("&ntilde;","ñ")
        cleaned=cleaned.replaceAll("&oacute;","ó")
        cleaned=cleaned.replaceAll("&iacute;","í")
        cleaned=cleaned.replaceAll("&aacute;","á")
        cleaned=cleaned.replaceAll("&eacute;","é")
        cleande=cleaned.replaceAll("&quot;","\"")
        //cleaned=cleaned.replaceAll("...Con fundamento por lo dispuesto en los","=...Con fundamento por lo dispuesto en los")
        /*cleaned=cleaned.replaceAll("&nbsp;","\n\
")*/
        //println(cleaned)
        def buscapapeleta=cleaned.split("\\.\\.\\.")
        println(buscapapeleta.length)
        //println(buscapapeleta.length+" Aqui va el tamaño de buscapapeleta")
       
        def guardaNota=Nota.findByExpanoAndExproAndIdn(params.anio2,params.expediente2,'C')
        params.nota=params.nota.replaceAll("2em","1em")
        guardaNota.nota=params.nota.replaceAll("<p style=\"text-align: justify; line-height: 1em; font-size: 8pt\">&nbsp;</p>","")
        guardaNota.ip=request.getRemoteAddr().toString()
        guardaNota.usuario=springSecurityService.currentUser.id
        if(!guardaNota.save(flush:true))
        {
            println("Error no se puedieron guardar los cambios")
        }
        def papeleta=Papeleta.findByExpanoAndExproAndDonde(params.anio2,params.expediente2,'C')
        //=new Papeleta()
        if(!papeleta || papeleta.equals(null))
        {
            papeleta=new Papeleta()
            papeleta.nota="...\""+buscapapeleta[1]+"...\""
        papeleta.expano=Integer.valueOf(params.anio)
        papeleta.expro=Integer.valueOf(params.expediente)
        papeleta.ip=request.getRemoteAddr().toString()
        papeleta.usuario=springSecurityService.currentUser.id
        if(!papeleta.save(flush : true))
        {
            println("No se guardo la papeleta")
        }
        }
        else
        {
            println("Se va a actualizar la papeleta existente")
            papeleta.nota="\"..."+buscapapeleta[1]+"...\""
            if(!papeleta.save(flush:true))
            {
                println("No se pudo guardar la papeleta nueva")
            }
        }
        
        //return
        redirect(controller:'Opcion', action:'editarResolucion',params:[anio:params.anio2,expediente:params.expediente2]) 
        //buscaResolucion(params.anio,params.anio)
        //chain action: 'buscaResolucion', model: [params:params]
    }
    
    
    def updatePapeletaArchivo()
    {
        println(params.expediente)
        println(params.anio)
        def nuevanota=""
        
        nuevanota=params.nota


        String cleaned = cleanHtml(nuevanota, 'simpleText') 
        cleaned=cleaned.replaceAll("&Iacute;","Í")
        cleaned=cleaned.replaceAll("&Aacute;","Á")
        cleaned=cleaned.replaceAll("&Eacute;","É")
        cleaned=cleaned.replaceAll("&Uacute;","Ú")
        cleaned=cleaned.replaceAll("&Ntilde;","Ñ")
        cleaned=cleaned.replaceAll("&Oacute;","Ó")
        cleaned=cleaned.replaceAll("&uacute;","ú")
        cleaned=cleaned.replaceAll("&ntilde;","ñ")
        cleaned=cleaned.replaceAll("&oacute;","ó")
        cleaned=cleaned.replaceAll("&iacute;","í")
        cleaned=cleaned.replaceAll("&aacute;","á")
        cleaned=cleaned.replaceAll("&eacute;","é")
        cleaned=cleaned.replaceAll("&quot;","\"")

        def buscapapeleta=cleaned.split("\\.\\.\\.")
        println(buscapapeleta.length)
       
        def guardaNota=Nota.findByExpanoAndExproAndIdn(params.anio.toString(),params.expediente.toString(),'C'.toCharacter())
        guardaNota.nota=params.nota
        guardaNota.ip=request.getRemoteAddr().toString()
        guardaNota.usuario=springSecurityService.currentUser.id
        if(!guardaNota.save(flush:true))
        {
            println("Error no se puedieron guardar los cambios")
        }
        def papeleta=Papeleta.findByExpanoAndExproAndDonde(params.anio,params.expediente,"C")
        if(!papeleta || papeleta.equals(null))
        {
            papeleta=new Papeleta()
            papeleta.nota="...\""+buscapapeleta[1]+"...\""
        papeleta.expano=Integer.valueOf(params.anio)
        papeleta.expro=Integer.valueOf(params.expediente)
        papeleta.ip=request.getRemoteAddr().toString()
        papeleta.usuario=springSecurityService.currentUser.id
        if(!papeleta.save(flush : true))
        {
            println("No se guardo la papeleta")
        }
        }
        else
        {
            println("Se va a actualizar la papeleta existente")
            papeleta.nota="\"..."+buscapapeleta[1]+"...\""
            if(!papeleta.save(flush:true))
            {
                println("No se pudo guardar la papeleta nueva")
            }
        }
        
        //return
        redirect(controller:'Opcion', action:'editarResolucion',params:[anio:params.anio,expediente:params.expediente]) 
        
    }
    
    
        def updatePapeletaOficialia()
    {
        println(params.expediente)
        println(params.anio)
        println(params)
        //println("Vamos bien")
        //println(params.nota)
        def nuevanota=""
        
        nuevanota=params.nota
     
        String cleaned = cleanHtml(nuevanota, 'simpleText') 
        cleaned=cleaned.replaceAll("&Iacute;","Í")
        cleaned=cleaned.replaceAll("&Aacute;","Á")
        cleaned=cleaned.replaceAll("&Eacute;","É")
        cleaned=cleaned.replaceAll("&Uacute;","Ú")
        cleaned=cleaned.replaceAll("&Ntilde;","Ñ")
        cleaned=cleaned.replaceAll("&Oacute;","Ó")
        cleaned=cleaned.replaceAll("&uacute;","ú")
        cleaned=cleaned.replaceAll("&ntilde;","ñ")
        cleaned=cleaned.replaceAll("&oacute;","ó")
        cleaned=cleaned.replaceAll("&iacute;","í")
        cleaned=cleaned.replaceAll("&aacute;","á")
        cleaned=cleaned.replaceAll("&eacute;","é")
        cleaned=cleaned.replaceAll("&quot;","\"")

        def buscapapeleta=cleaned.split("\\.\\.\\.")
        println(buscapapeleta.length)
        def guardaNota=Nota.findByExpanoAndExproAndIdn(params.anio.toString(),params.expediente.toString(),'O'.toCharacter())
        guardaNota.nota=params.nota
        guardaNota.ip=request.getRemoteAddr().toString()
        guardaNota.usuario=springSecurityService.currentUser.id
        if(!guardaNota.save(flush:true))
        {
            println("Error no se puedieron guardar los cambios")
        }
        def papeleta=Papeleta.findByExpanoAndExproAndDonde(params.anio,params.expediente,"O")
        if(!papeleta || papeleta.equals(null))
        {
            papeleta=new Papeleta()
            papeleta.nota="...\""+buscapapeleta[1]+"...\""
        papeleta.expano=Integer.valueOf(params.anio)
        papeleta.expro=Integer.valueOf(params.expediente)
        papeleta.ip=request.getRemoteAddr().toString()
        papeleta.usuario=springSecurityService.currentUser.id
        if(!papeleta.save(flush : true))
        {
            println("No se guardo la papeleta")
        }
        }
        else
        {
            println("Se va a actualizar la papeleta existente")
            papeleta.nota="\"..."+buscapapeleta[1]+"...\""
            if(!papeleta.save(flush:true))
            {
                println("No se pudo guardar la papeleta nueva")
            }
        }
        def flagofi=true
        redirect(controller:'Opcion', action:'editarResolucion',params:[anio:params.anio,expediente:params.expediente,flagofi:flagofi]) 
        
    }

    
    
    def consul(){ 
        def id= params.id
        def scasolInstance = Scasol.get(params.id)
        render(template:"cajita",model:[scasolInstance :scasolInstance ])
    }
    
    def listado(Long id) {
        
        [opcionInstanceList: Opcion.findAllByExapro(id), opcionInstanceTotal: Opcion.count()]
    }
       
    def opciones(Integer max) {
        
        def maximo=Scasol.findAll("From Scasol where id=(select max(id) from Scasol)") 
        def progresivo2= maximo.id.toString()
        def progresivo=""
  
        for(int i=0;i<progresivo2.length();i++){
            
            if(progresivo2[i]>47 && progresivo2[i]<58){
                
                progresivo+=progresivo2[i]
            }
            
        }
        params.max = Math.min(max ?: 10, 100)
        [opcionInstanceList: Opcion.findAllByExapro(progresivo), opcionInstanceTotal: Opcion.count()]
    } 
    
    def opciones2(Integer max , Long id) {
        println(id+" ****************************")
        println(params)
        def progresivo= id
            params.max = Math.min(max ?: 18, 100) 
         [scaerrInstanceList:  Scaerr.findAllByExpro(progresivo) ,scaactInstanceList: Scaact.findAllByExppro(progresivo),numeroexpediente:params.numeroexpediente ]
       
    }              
    
    def getDat() {

        def val = params.id
        def mensaj = params.exap
         flash.message = mensaj
        switch(params.id.toString())
        {
            
            case "1": 
               
                redirect(controller:'scaerr', action:'create')
                break
            case "2":                
                redirect(controller:'scaact', action:'create') 
                break
            case "3":               
                redirect(controller:'scaact', action:'create2')
                break
            case "4":                
                redirect(controller:'scaact', action:'create3')
                break
            case "5":
                def nuev = Scasol.get(params.exap)
                def  dat = nuev.typact?.id  
                println dat
                println("se ha seleccionado testar de oficio")
                println(dat+" {}{}{}{}{}{}{}")
                if (dat == 1){
                 
                 redirect(controller:'scaerr', action:'create2')
                 
                }else if (dat == 2 ){
                 
                redirect(controller:'scaerr', action:'create22')
                
                }else if (dat == 7) {
                
                redirect(controller:'scaerr', action:'create23')
               
                }else if(dat==4){
                    
                redirect(controller:'scaerr', action:'create235')
                }else {
               
                redirect(controller:'scaerr', action:'create23')                              
                
             }
             break
                
                
        }
               

        
    }
    def mostrar() {
        def val= params.id
        flash.message = val
        redirect(action: "create",params: [numeroexpediente:params.id])
         
    }
    def create() {
        [opcionInstance: new Opcion(params)]
    }
    def verProgresivo(){
        println(params.id)
        def val= params.id
        def val2= params.exap  
        println(val +" "+val2)
        render(template:"progre",model:[val:val ,val2:val2 ])
    }
    
    def save() {
        def val = params.evaluar
        def msan = 0
       if (params.evaluar == "1" || params.evaluar =="5"){
                 
            try
            {
                /*if(params.agregarbase=="1"){
                def nuevavase=new Base()
                def acta=Tipoactas.get(1)
                println(params.campo.id.toString()+" Aqui va el id del campo")
                def campo=Fields.get(params.campo.id)
                
                nuevavase.campo=campo
                nuevavase.acta=acta
                nuevavase.base=params.base
                nuevavase.save(flush:true , failOnError : true)
                }*/
                
            }catch(Exception e)
            {
               println(e)
               println("x_x x_x")
            }
            def scaerr = new Scaerr(params) 
          
            println(params.base.toString())
            println("***************************")
            scaerr.ip=request.getRemoteAddr().toString()
            scaerr.usuario=springSecurityService.currentUser.id
            scaerr.save(flush:true , failOnError : true)
            msan  =   scaerr.expro
            println params
            
        }else { 
           
            def scaact = new Scaact(params)
            scaact.ip=request.getRemoteAddr().toString()
            scaact.usuario=springSecurityService.currentUser.id
            scaact.save(flush : true, failOnError : true)
            
            msan =  scaact.exppro
            println params
        }
                redirect(action: "opciones2", id: msan )
                flash.message =   msan 
    }

    def show(Long id) {
        def opcionInstance = Opcion.get(id)
        if (!opcionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'opcion.label', default: 'Opcion'), id])
            redirect(action: "list")
            return
        }

        [opcionInstance: opcionInstance]
    }
    

    def edit(Long id) {
        def opcionInstance = Opcion.get(id)
        if (!opcionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'opcion.label', default: 'Opcion'), id])
            redirect(action: "list")
            return
        }

        [opcionInstance: opcionInstance]
    }

    def update(Long id, Long version) {
        def opcionInstance = Opcion.get(id)
        if (!opcionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'opcion.label', default: 'Opcion'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (opcionInstance.version > version) {
                opcionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'opcion.label', default: 'Opcion')] as Object[],
                          "Another user has updated this Opcion while you were editing")
                render(view: "edit", model: [opcionInstance: opcionInstance])
                return
            }
        }

        opcionInstance.properties = params

        if (!opcionInstance.save(flush: true)) {
            render(view: "edit", model: [opcionInstance: opcionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'opcion.label', default: 'Opcion'), opcionInstance.id])
        redirect(action: "show", id: opcionInstance.id)
    }

    def delete(Long id) {
        def opcionInstance = Opcion.get(id)
        if (!opcionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'opcion.label', default: 'Opcion'), id])
            redirect(action: "list")
            return
        }

        try {
            opcionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'opcion.label', default: 'Opcion'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'opcion.label', default: 'Opcion'), id])
            redirect(action: "show", id: id)
        }
    }
    
    def autoinc(){
          println "hola mundo" 
      //  println maximo

    // def nuemr =Scaact.executeQuery("SHOW TABLE STATUS LIKE 'scaact'") 
  
        
        //def datosss=Scaact.findAll("from information_schema.TABLES where TABLE_SCHEMA='juridico43' and TABLE_NAME='scaact'")
     //
     //def datosextraido=Scaact.executeQuery("select AUTO_INCREMENT from information_schema.TABLES where TABLE_SCHEMA='juridico43' and TABLE_NAME='scaact'") 
   // Scaact.findAll("SHOW TABLE STATUS LIKE 'scaact'") 
    }
    
    def eliminar1(Long id) {
        def scaactInstance = Scaact.get(id)
        def num = scaactInstance.exppro
        println num
        if (!scaactInstance) {
            flash.message = num
            redirect(action: "opciones2", id:num )
             return
        }

        try {
            scaactInstance.delete(flush: true)
            flash.message = scaactInstance.exppro
            redirect(action: "opciones2", id:num )

        }
        catch (DataIntegrityViolationException e) {
            flash.message = scaactInstance.exppro
            redirect(action: "opciones2", id:num )
        }
        
    }
    
    def modificaNegativa(){
        println("Tienes que buscar los errores")
        println(params)
        def textonegativo
        textonegativo=opcionService.negativa(params.expediente,params.anio)
        println(textonegativo)
        [textonegativo: textonegativo, anio : params.anio,expediente:params.expediente]
    }
    
    def imprimeNegativa()
    {
        println (params)
        println("Aqui vamos a imprimir todo el texto de nuevo")
        
        //def texto1=params.nota

        //def 
        def mapacompleto=[]
        def mapapendiente=[]
                byte[] bytes
        

                                  mapapendiente << [
                                      fecha:"Oaxaca de Juárez, Oaxaca a "+hoy2[2]+" de "+meses2[Integer.parseInt(hoy2[1])]+" de "+hoy2[0]+"\n\
                          \n\
Expediente: "+params.expediente2+"/"+params.anio2+"\n\
Resolución Negativa de Aclaración",
                                      texto:params.nota,
                                      usuario:User.get(springSecurityService.currentUser.id).toString(),
                                      persona:enjuridico.titulo+" "+enjuridico.toString()
]

            
            
        try{
            HashMap<String, Object> params = new HashMap<String, Object>()
            params.put("logo", servletContext.getRealPath("/reports/"))
            
            def reportDef = new JasperReportDef(name: "Negativa.jasper",parameters:params ,fileFormat:JasperExportFormat.PDF_FORMAT, reportData  :  mapapendiente )
       
         mapacompleto = jasperService.generateReport(reportDef).toByteArray()

        }catch(Exception ex){
            println(ex.printStackTrace())
        }
        response.addHeader("Content-Disposition", 'attachment; filename=Negativa.pdf')
        response.contentType='application/pdf'
    	response.outputStream << mapacompleto
        response.outputStream.close()
        response.outputStream.flush()
        return false
    }
    
    def buscaResolucionNegativa(){
        
    }
    
    def eliminar2(Long id) {
        def scaerrInstance = Scaerr.get(id)
        if (!scaerrInstance) {
            flash.message = scaerrInstance.expro
            redirect(action: "opciones2", id:scaerrInstance.expro )
            return
        }

        try {
            scaerrInstance.delete(flush: true)
            flash.message = scaerrInstance.expro
            redirect(action: "opciones2", id:scaerrInstance.expro )
        }
        catch (DataIntegrityViolationException e) {
            flash.message = scaerrInstance.expro
            redirect(action: "opciones2", id:scaerrInstance.expro )
        }
    }       
} 
        