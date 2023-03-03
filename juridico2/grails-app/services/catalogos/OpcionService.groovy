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

class OpcionService {
    
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
    def springSecurityService

    def serviceMethod() {

    }
    
    

public String imprimeOficialia(String expediente)
    {
        artmat2=""
        artmat=""
        legitimacion=""
        println(expediente +" ()() ()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()")
            
        def scasolInstance=Scasol.get(expediente)
        def fsol=scasolInstance.fechasol.toString().substring(0,10).split("-")
        new File("D:/codigo.png").withOutputStream { out ->
    barcode4jService.render("code39Generator", scasolInstance.expro+"/"+scasolInstance.expano, out, "image/png")
}  
        
     // String [] meses2=["","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"]
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
      
      def listaScaerr //=Scaerr.findAllByExproAndProcede(params.id,"SI")
      listaScaerr=Scaerr.findAll("From Scaerr where expro=? and procede=? and (donde=? or donde=?)",[Long.parseLong(expediente),"SI",1,3])
      def listaScaact //=Scaact.findAllByExpproAndProcede(params.id,"SI")
      listaScaact=Scaact.findAll("From Scaact where exppro=? and procede=? and (donde=? or donde=?)",[Integer.parseInt(expediente),"SI",Terror.get(1),Terror.get(3)])
      //def listOpciones=Opcion.findAllByExapro(params.id)
       //listaScaact=Scaact.findAllByExppro(4)
       //println(listaScaact.size()+" |||||||||||||||||||||||||||||")
       //println(listaScaerr.size()+" |||||||||||||||||||||||||||||")
      if(listaScaerr.size()==0 && listaScaact.size()==0)
       {
           return ""
       }
    /* Date fec1=new Date();
     def fech1=fec1.toTimestamp() 
     String [] hoy=fech1.toString().split(" ")
     String [] hoy2=hoy[0].toString().split("-")*/
        //println(scasolInstance.fchact.toString()+" ###############################")
        String [] fec =scasolInstance.fchact.toString().split(" ")
        //println(scasolInstance.fchact.toString()+" /*--*//*---*///*---*//")
        String [] fech=fec[0].split("-")
        
        String [] fecha1 
        
        if(!scasolInstance.fecharesolucion.equals(null))
        {
          fecha1 =scasolInstance.fecharesolucion.toString().split(" ") 
            
        }
        else
        {
            
            fecha1 =scasolInstance.fchsol.toString().split(" ")
        }
        
        //println(scasolInstance.fchsol.toString()+" /*--*//*---*///*---*//")
        String [] fech2=fecha1[0].split("-")
        
      if(listaScaerr.equals()){println("no tiene errores agregados")}
      else
      {
          def fieldsInstance
                   
          //println("tiene: "+listaScaerr.size()+" errores")
          for(int i=0;i<listaScaerr.size();i++)
          {
              if(listaScaerr.tcorrect[i].toString().equals("INADECUADA UBICACION DE DATOS"))
              {
                errorescivil[1]=1
                erroresreglamento[2]=1
              }
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
            //        println("encontro un testar de oficio °°°°°°°°°°°°°°°°")
              //      println(listaScaerr.tcorrect[i].toString()+" Esta es la persona")
                    erroreacta+="TESTAR DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+". "
                    //listaerr+=conterrores+".-"+"LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" "+listaScaerr.debeser[i].toString()+" POR LO QUE SE PROCEDE A TESTAR DE OFICIO"
                    /*listaerr+=conterrores+".-CARECE(N) DE VALIDEZ LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" POR LO QUE SE PROCEDE A TESTAR DE OFICIO.\n\
"*/
                    listaerr+=conterrores+".-SE AUTORIZA TESTAR DE OFICIO LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" DENTRO DEL TEXTO DEL ACTA "+listaScaerr.debeser[i]+".\n\
"
                     if(listaScaerr.base[i].equals("LA CIRCULAR 002 DE FECHA 15 DE JUNIO DE 1988"))
                    {
                      aclarados+="CON FUNDAMENTO EN LA CIRCULAR 002 DE FECHA 15 DE JUNIO DE 1988, SE AUTORIZA TESTAR  "
                      aclarados+=""+listaScaerr.tcorrect[i].toString()+" EN VIRTUD DE QUE CARECE DE LOS ELEMENTOS ESENCIALES"
                      aclarados+=" PARA QUE TENGA VALIDEZ PLENA.-"
                     //aclarados+="EN EL ESTADO, SE AUTORIZA TESTAR LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" DENTRO DEL TEXTO DEL ACTA, "+listaScaerr.debeser[i].toString()+".-"
                      
                    }
                   else if(listaScaerr.debeser[i].contains("RELACION DE FILIACION")){
                    aclarados+="CON FUNDAMENTO EN EL ARTICULO 385 PARRAFO PRIMERO DEL CODIGO CIVIL VIGENTE "
                    aclarados+="EN EL ESTADO, SE AUTORIZA TESTAR LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" DENTRO DEL TEXTO DEL ACTA, "+listaScaerr.debeser[i].toString()+".-"
                    
                    }
                    else {
                        aclarados+="CON FUNDAMENTO EN EL ARTICULO 47 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE AUTORIZA TESTAR "
                        aclarados+=""+listaScaerr.tcorrect[i].toString()+" EN VIRTUD DE SER IMPROCEDENTE.-"
                    }
                    //aclarados+="CON FUNDAMENTO EN EL ARTICULO 385 PARRAFO PRIMERO DEL CODIGO CIVIL VIGENTE "
                    //aclarados+="EN EL ESTADO, SE AUTORIZA TESTAR LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" DENTRO DEL TEXTO DEL ACTA, "+listaScaerr.debeser[i].toString()+".-"
                }else{
                    
                    if(listaScaerr.campo[i].toString().contains("EL SEXO"))
                    {
                //        println("Vamos a ocuapar el 68 fraccion III")
                        //articulo68[2]=1
                    }
                    if(listaScaerr.campo[i].toString().contains("APELLIDO"))
                    {
                  //      println("Vamos a ocuapar el 68 fraccion V")
                       // articulo68[4]=1
                    }
               erroreacta+=listaScaerr.tcorrect[i].toString()+" DE "+listaScaerr.campo[i].toString()+". "
                  //println(scasolInstance.typact)
                  //println(listaScaerr[i].campo.toString())
                  //println(scasolInstance.sexintere)
                  //println(scasolInstance.typact.toString()+", "+listaScaerr[i].campo.toString()+" "+scasolInstance.sexintere.toString()+" }{}{}{}{}{}{}{}{}{}{}{}{}{´+´+´+´+´+´+´+")
                fieldsInstance=Fields.findByNombre(listaScaerr.campo[i].toString())
                //fieldsInstance=Fields.get(listaScaerr[i].campo)
               // fieldsInstance=Fields.find("From fields where acta=? and nombre=? and sexo=?",[scasolInstance.typact, listaScaerr[i].campo.toString(),scasolInstance.sexintere])
                    //println(fieldsInstance)
                    //println(scasolInstance.typact+" "+listaScaerr.campo[i].toString()) 
                //println (fieldsInstance.tipo)
                    if(fieldsInstance.tipo.toString().equals("N"))
                    {
                    erroresreglamento[9]=1
                    }
                
                //println(listaScaerr.tcorrect[i])
                //println(listaScaerr.tcorrect[i].toString()+" tcorrect")
                //println(listaScaerr.terror[i])
                //println(listaScaerr.campo[i].toString()+" Campo")
                
                if(listaScaerr.campo[i].toString().contains("EL REGIMEN"))
                {
                    artmat="y 206"
                }
                if((listaScaerr.campo[i].toString().equals("EL NOMBRE DEL CONTRAYENTE") ||
                        listaScaerr.campo[i].toString().equals("EL NOMBRE DE LA CONTRAYENTE")
                    || listaScaerr.campo[i].toString().contains("APELLIDO")
                    || listaScaerr.campo[i].toString().equals("EL(LOS) NOMBRE(S) PROPIO(S) DEL CONTRAYENTE")
                    || listaScaerr.campo[i].toString().equals("EL(LOS) NOMBRE(S) PROPIO(S) DE LA CONTRAYENTE"))
                    && (listaScaerr.base[i].contains("ACTA DE") || listaScaerr.base[i].contains("DOCUMENTOS")) 
                    && scasolInstance.typact.toString().equals("MATRIMONIO")){   
                    //println("Paso por la 100 fraccion 1 busca el error")
                    artmat2="100 Fraccion I, "
                    //aclarados=aclarados.replaceAll("EL NOMBRE DEL CONTRAYENTE","EL NOMBRE DEL CONTRAYENTE EN BASE A SU ACTA DE NACIMIENTO")
                    //aclarados=aclarados.replaceAll("EL NOMBRE DE LA CONTRAYENTE","EL NOMBRE DE LA CONTRAYENTE EN BASE A SU ACTA DE NACIMIENTO")
                    //aclarados=aclarados.replaceAll("EL NOMBRE DEL CONTRAYENTE","EL NOMBRE DEL CONTRAYENTE DE ACUERDO A SU ACTA DE NACIMIENTO DE FECHA ANTERIOR AL MATRIMONIO")
                    //aclarados=aclarados.replaceAll("EL NOMBRE DE LA CONTRAYENTE","EL NOMBRE DE LA CONTRAYENTE DE ACUERDO A SU ACTA DE NACIMIENTO DE FECHA ANTERIOR AL MATRIMONIO")
                
                }
              
     if(listaScaerr.tcorrect[i].toString().equals("OMISION") && !listaScaerr.campo[i].toString().contains("APELLIDO") && !listaScaerr.base[i].toString().contains("REFORMA AL ARTICULO 141"))
                {
                    
                    listaerr+=conterrores+".-"+listaScaerr.tcorrect[i]+" DE "+listaScaerr.campo[i]+" Y DE ACUERDO A "+listaScaerr.base[i]+", LO CORRECTO DEBE SER: "+listaScaerr.debeser[i].toString()+""+" \n\
"
                }
                else
                {
                     if(listaScaerr.tcorrect[i].toString().equals("OMISION") && listaScaerr.campo[i].toString().contains("APELLIDO")
                       && listaScaerr.base[i].toString().contains("LA REFORMA AL ARTICULO 141"))
                {
                    errorescivil[6]=1
                  //  println("va a hacer aclaracion por uso")
                    aclarados+="SE AGREGA "+listaScaerr.campo[i]+" POR SIMPLE USO Y SIN CREAR FILIACION : "+listaScaerr.debeser[i].toString()+".-"
                    //aclarados+="SE AGREGA EL SEGUNDO APELLIDO POR SIMPLE USO SIN CREAR FILIACION: "+listaScaerr.debeser[i].toString()+".-"
                    listaerr+=conterrores+".-"+"OMISION"+" DE "+listaScaerr.campo[i]+" Y DE ACUERDO A "+listaScaerr.base[i]+", LO CORRECTO DEBE SER: "+listaScaerr.debeser[i].toString()+""+" \n\
"                   
                   conterrores++
                   continue 
                }else {
                    if(listaScaerr.base[i].toString().contains("APELLIDO POR SIMPLE USO"))
                    {
                    //    println("Agrega apellido por simple uso")
                        errorescivil[6]=1
                        listaerr+=conterrores+".-"+listaScaerr.tcorrect[i]+" DE "+listaScaerr.campo[i]+", TENEMOS QUE APARECE ASENTADO(A) COMO "+listaScaerr.contiene[i]+" Y DE ACUERDO A "+listaScaerr.base[i]+", LO CORRECTO DEBE SER: "+listaScaerr.debeser[i].toString()+" \n\
"                   
                        conterrores++
                        if(listaScaerr.base[i].toString().contains("SE AGREGA UN SEGUNDO APELLIDO POR SIMPLE USO"))
                        {
                            if(scasolInstance.sexintere.contains("F"))
                            {
                              aclarados+="SE AGREGA EL SEGUNDO APELLIDO POR SIMPLE USO SIN CREAR FILIACION, SIENDO EL NOMBRE COMPLETO DE LA REGISTRADA: "+listaScaerr.debeser[i].toString()+".-"
                      
                            }
                            else 
                            {
                              aclarados+="SE AGREGA EL SEGUNDO APELLIDO POR SIMPLE USO SIN CREAR FILIACION, SIENDO EL NOMBRE COMPLETO DEL REGISTRADO: "+listaScaerr.debeser[i].toString()+".-"
                     
                            }
                            
                        }else{
                            if(scasolInstance.sexintere.contains("F"))
                            {
                              aclarados+="SE AGREGA EL PRIMER APELLIDO POR SIMPLE USO SIN CREAR FILIACION, SIENDO EL NOMBRE COMPLETO DE LA REGISTRADA: "+listaScaerr.debeser[i].toString()+".-"
                      
                            }
                            else
                            {
                              aclarados+="SE AGREGA EL PRIMER APELLIDO POR SIMPLE USO SIN CREAR FILIACION, SIENDO EL NOMBRE COMPLETO DEL REGISTRADO: "+listaScaerr.debeser[i].toString()+".-"
                      
                            }
                            
                                    
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
                    if((listaScaerr.campo[i].toString().contains("NOMBRE DE LA MADRE") || 
                        listaScaerr.campo[i].toString().contains("NOMBRE DEL PADRE")) && listaScaerr.base[i].toString().contains("ACTA DE NACIMIENTO DE") && scasolInstance.typact.toString().equals("NACIMIENTO"))
                    {
                        aclarados+=listaScaerr.campo[i].toString()+" EN BASE A SU ACTA DE NACIMIENTO: "+listaScaerr.debeser[i].toString()+".- "
                    }
                    else{
                        aclarados+=listaScaerr.campo[i].toString()+" : "+listaScaerr.debeser[i].toString()+".- "
                    }
                    
                }
                    
                    
                }
              

              conterrores++
              //println(erroreacta+" -.-.-.-.-.-.-.-.-.-.-.-.-.")
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
             // println(listaScaact.tipoerresp[i].toString() +" este es el tipo del 71")
                if(listaScaact.tipoerresp[i].toString().equals("APLICACION DEL ARTICULO 71"))
                {
                     if(listaScaact.numacta[i].equals() || listaScaact.fechaacta[i].equals(null) || listaScaact.dto[i].equals() || listaScaact.mpo[i].equals() || listaScaact.loc[i].equals())
                    {
                        
                    }else
                    {
                        aclarados+="CON FUNDAMENTO EN EL ARTICULO 71 DEL CODIGO CIVIL VIGENTE EN EL ESTADO Y EN VIRTUD "
                        aclarados+="DEL ACTA DE MATRIMONIO DE LOS PADRES DEL REGISTRADO CON NUMERO "+listaScaact.numacta[i]+" LEVANTADA "
                        aclarados+="EN "
                        aclarados+=""+(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))?listaScaact.mpo[i].toString()+", "+listaScaact.dto[i].toString()+", OAXACA ":listaScaact.loc[i].toString()+", "+listaScaact.mpo[i].toString()+", "+listaScaact.dto[i].toString()+", OAXACA "
                        aclarados+="DE FECHA "+listaScaact.fechaacta[i].split("-")[2]+" DE "+meses2[Integer.valueOf(listaScaact.fechaacta[i].split("-")[1])]+" DE "+listaScaact.fechaacta[i].split("-")[0]+" "
                        aclarados+="ANTERIOR AL REGISTRO, SE ANOTAN LOS SIGUIENTES DATOS: "
                        /*aclarados+="EN VIRTUD DEL ACTA DE MATRIMONIO DE LOS PADRES DEL REGISTRADO, CON NUMERO DE REGISTRO "+listaScaact.numacta[i]+" LEVANTADA EN "
                        aclarados+=""+(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))?listaScaact.mpo[i].toString()+", "+listaScaact.dto[i].toString()+", OAXACA ":listaScaact.loc[i].toString()+", "+listaScaact.mpo[i].toString()+", "+listaScaact.dto[i].toString()+", OAXACA "
                        aclarados+="DE FECHA "+listaScaact.fechaacta[i].split("-")[2]+" DE "+meses2[Integer.valueOf(listaScaact.fechaacta[i].split("-")[1])]+" DE "+listaScaact.fechaacta[i].split("-")[0]+" ANTERIOR A LA FECHA DEL REGISTRO DE NACIMIENTO Y CON FUNDAMENTO "
                        aclarados+="EN EL ARTICULO 71 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE ANOTAN LOS SIGUIENTES DATOS: "*/
                        //aclarados+="CON FUNDAMENTO EN EL ARTICULO 71 DEL CÓDIGO CIVIL VIGENTE EN EL ESTADO, SE ANOTAN LOS SIGUIENTES DATOS: "
                                         if(!listaScaact.pnombre[i].equals()){
                                             apli71[0]=1
                                             aclarados+="EL NOMBRE DEL PADRE: "+listaScaact.pnombre[i]+""+(listaScaact.pap1[i].equals()? "":" "+listaScaact.pap1[i])+""+(listaScaact.pap2[i].equals() ? "":" "+listaScaact.pap2[i])+""+".-"}
                                         if(!listaScaact.pedad[i].equals()){apli71[0]=1
                                             Letras letras = new Letras(Integer.valueOf(listaScaact.pedad[i]))
                                             edadletra=letras.convertirLetras(listaScaact.pedad[i])
                                             aclarados+="LA EDAD DEL PADRE AL MOMENTO DEL REGISTRO: "+listaScaact.pedad[i]+" "+edadletra.toString().toUpperCase()+" AÑOS.-"}
                                         if(!listaScaact.pnac[i].equals()){apli71[0]=1
                                             aclarados+="LA NACIONALIDAD DEL PADRE: "+listaScaact.pnac[i]+".-"}
                                         if(!listaScaact.pab1[i].equals()){apli71[0]=1
                                             aclarados+="EL NOMBRE DEL ABUELO PATERNO: "+listaScaact.pab1[i]+(listaScaact.pab1ap1[i].equals()?"": " "+listaScaact.pab1ap1[i])+(listaScaact.pab1ap2[i].equals()? "":" "+listaScaact.pab1ap2[i])+".-"}                                         
                                         if(!listaScaact.pab1nac[i].equals()){apli71[0]=1
                                             aclarados+="LA NACIONALIDAD DEL ABUELO PATERNO: "+listaScaact.pab1nac[i]+".-"}
                                         if(!listaScaact.pab2[i].equals()){apli71[0]=1
                                             aclarados+="EL NOMBRE DE LA ABUELA PATERNA: "+listaScaact.pab2[i]+(listaScaact.pab2ap1[i].equals()? "":" "+listaScaact.pab2ap1[i])+(listaScaact.pab2ap2[i].equals()?"":" "+listaScaact.pab2ap2[i])+".-"}
                                         if(!listaScaact.pab1nac[i].equals() && !listaScaact.pab2[i].equals()){apli71[0]=1
                                             aclarados+="LA NACIONALIDAD DE LA ABUELA PATERNA: "+listaScaact.pab1nac[i]+".-"}                        
                                         if(!listaScaact.mnom[i].equals()){apli71[1]=1
                                             aclarados+="EL NOMBRE DE LA MADRE: "+listaScaact.mnom[i]+(listaScaact.map1[i].equals()? "":" "+listaScaact.map1[i])+(listaScaact.map2[i].equals()? "":" "+listaScaact.map2[i])+".-"}
                                         if(!listaScaact.medad[i].equals()){apli71[1]=1
                                             Letras letras = new Letras(Integer.valueOf(listaScaact.medad[i]))
                                             edadletra=letras.convertirLetras(listaScaact.medad[i])
                                             aclarados+="LA EDAD DE LA MADRE AL MOMENTO DEL REGISTRO: "+listaScaact.medad[i]+" "+edadletra.toString().toUpperCase()+" AÑOS.-"}
                                         if(!listaScaact.mnac[i].equals()){apli71[1]=1
                                             aclarados+="LA NACIONALIDAD DE LA MADRE: "+listaScaact.mnac[i]+".-"}
                                         if(!listaScaact.mab1[i].equals()){apli71[1]=1
                                             aclarados+="EL NOMBRE DEL ABUELO MATERNO: "+listaScaact.mab1[i]+(listaScaact.mab1ap1[i].equals()?"":" "+listaScaact.mab1ap1[i])+(listaScaact.mab1ap2[i].equals() ? "":" "+listaScaact.mab1ap2[i])+".-"}
                                         if(!listaScaact.mab1nac[i].equals() && !listaScaact.mab1[i].equals()){apli71[1]=1
                                             aclarados+="LA NACIONALIDAD DEL ABUELO MATERNO: "+listaScaact.mab1nac[i]+".-"}
                                         if(!listaScaact.mba2[i].equals()){apli71[1]=1
                                             aclarados+="EL NOMBRE DE LA ABUELA MATERNA: "+listaScaact.mba2[i]+(listaScaact.mab2ap1[i].equals()?"":" "+listaScaact.mab2ap1[i])+(listaScaact.mab2ap2[i].equals()?"":" "+listaScaact.mab2ap2[i])+".-"}
                                         if(!listaScaact.mab2nac[i].equals() && !listaScaact.mba2[i].equals()){apli71[1]=1
                                             aclarados+="LA NACIONALIDAD DE LA ABUELA MATERNA: "+listaScaact.mab2nac[i]+".-"}
                                        //println(apli71[0]+" este es el del padre" )
                                        //println(apli71[1]+" este es el de la madre")
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
                    //legitimacion="367 y 368"
                    def fechamatrimonio=listaScaact.fechaacta[i].toString().split("-")
                    def fechmatri=fechamatrimonio
                    //println(fechmatri[0]+ "este el año")
                    //println(fechmatri[1]+ "este el mes")
                    //println(fechmatri[2]+ "este el dia")
                    //println(fechamatrimonio[0]+" ************************************")
                    //println(fechamatrimonio[1])
                    
                    
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
                            //aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+".-"
                            //aclarados+=""+scasolInstance.nom_intere+" "+scasolInstance.ap1_intere+" "+scasolInstance.ap2_intere+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A) EN VIRTUD DE "+listaScaact.bases[i]+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+".-"
                            aclarados+="CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE "
                            aclarados+="LA SIGUIENTE ANOTACION: LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDA Y EN "
                            aclarados+="VIRTUD DEL MATRIMONIO SUBSECUENTE DE LOS PADRES SE LE TIENE COMO HIJO NACIDO DE MATRIMONIO, "
                            aclarados+="SEGÚN ACTA NUMERO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+", OAXACA.-"
                        
                            }else{
                            //aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"
                            
                            aclarados+="CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE "
                            aclarados+="LA SIGUIENTE ANOTACION: LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDA Y EN "
                            aclarados+="VIRTUD DEL MATRIMONIO SUBSECUENTE DE LOS PADRES SE LE TIENE COMO HIJO NACIDO DE MATRIMONIO, "
                            aclarados+="SEGÚN ACTA NUMERO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+", OAXACA.-"
                            
                        }
        
                        errart71+="OMISION DE DATOS RELATIVOS A LA MADRE.-  "
                        listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A LA MADRE Y EN VIRTUD DEL MATRIMONIO SUBSECUENTE DE SUS PADRES, LA ACLARACIÓN ES PROCEDENTE \n\
"              
                        //errorescivil[5]=1 
                        errorescivil[5]=0 
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
                            //aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"
                            aclarados+="CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE "
                            aclarados+="LA SIGUIENTE ANOTACION: LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDA Y EN "
                            aclarados+="VIRTUD DEL MATRIMONIO SUBSECUENTE DE LOS PADRES SE LE TIENE COMO HIJO NACIDO DE MATRIMONIO, "
                            aclarados+="SEGÚN ACTA NUMERO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+", OAXACA.-"
                        
                        }else{
                            //aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"
                            aclarados+="CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE "
                            aclarados+="LA SIGUIENTE ANOTACION: LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDA Y EN "
                            aclarados+="VIRTUD DEL MATRIMONIO SUBSECUENTE DE LOS PADRES SE LE TIENE COMO HIJO NACIDO DE MATRIMONIO, "
                            aclarados+="SEGÚN ACTA NUMERO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+", OAXACA.-"
                            
                        }
                                 errart71+="OMISION DE DATOS RELATIVOS A EL PADRE.- "
                                 listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A EL PADRE Y EN VIRTUD DEL MATRIMONIO SUBSECUENTE DE SUS PADRES, LA ACLARACIÓN ES PROCEDENTE \n\
"
                                //errorescivil[5]=1 
                                errorescivil[5]=0 
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
                                        errorescivil[5]=0 
                                        errorescivil[5]=0 
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
                    //println("oficio de reconocmiento")
                    def fechamatrimonio=listaScaact.fechaacta[i].toString().split("-")
                    def fechmatri=fechamatrimonio

                    if(listaScaact.numacta[i].equals() || listaScaact.fechaacta[i].equals(null) || listaScaact.dto[i].equals() || listaScaact.mpo[i].equals() || listaScaact.loc[i].equals())
                    {
                        
                    }else
                    {
                        if(listaScaact[i].dto.toString().equals(scasolInstance.dto.toString()) &&
                           listaScaact[i].mpo.toString().equals(scasolInstance.mpo.toString()) &&
                           listaScaact[i].loc.toString().equals(scasolInstance.loc.toString()))
                       {
                           //println("El reconocimiento fue en el mismo lugar")
                           reconocimiento="84, "
                       }
                       else
                       {
                           //println("El reconocimiento no fue en el mismo lugar")
                           reconocimiento="84, 85, "
                       }

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
                        //println ("Datos relativos LA MADRE")
                        if(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))
                                        {
                                            aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE LA SIGUIENTE ANOTACION : "+"LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDO(A) POR SU MADRE SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+", OAXACA. "   
                                        }else{
                                            aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE LA SIGUIENTE ANOTACION : "+"LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDO(A) POR SU MADRE SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+", OAXACA. "   
                                        
                                            } 
                        errart71+="OMISION DE DATOS RELATIVOS A LA MADRE.  "
                        listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A LA MADRE, Y DE ACUERDO AL ACTA DE RECONOCIMIENTO EXHIBIDA, QUEDA RECONOCIDO(A) POR SU MADRE, POR TAL RAZÓN LA ACLARACIÓN ES PROCEDENTE \n\
"
                        errorescivil[5]=0 
                        errorescivil[5]=0 
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
                                 //println ("Datos relativos a EL PADRE")
                                 if(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))
                                        {
                                             aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE LA SIGUIENTE ANOTACION : LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDO(A) POR SU PADRE SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+", OAXACA.-"   
                                        
                                         }else{
                                             aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE LA SIGUIENTE ANOTACION : LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDO(A) POR SU PADRE SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+", OAXACA.-"   
                                        
                                            }                              
                                                               
                                 errart71+="OMISION DE DATOS RELATIVOS A EL PADRE. "
                                 listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A EL PADRE, Y DE ACUERDO AL ACTA DE RECONOCIMIENTO EXHIBIDA, QUEDA RECONOCIDO(A) POR SU PADRE, POR TAL RAZÓN LA ACLARACIÓN ES PROCEDENTE \n\
"
                                //errorescivil[5]=0 
                                errorescivil[5]=0 
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
                                         aclarados+=""+scasolInstance.nom_intere+" "+scasolInstance.ap1_intere+" "+scasolInstance.ap2_intere+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) POR SUS PADRES SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"   
                                        }else{
                                             aclarados+=""+scasolInstance.nom_intere+" "+scasolInstance.ap1_intere+" "+scasolInstance.ap2_intere+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) POR SUS PADRES SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"   
                                             
                                            }                                                                                     
                                       // println ("Datos relativos al padre y la madre") 
                                        errart71+="OMISION DE DATOS RELATIVOS A EL PADRE Y LA MADRE. "
                                        listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A EL PADRE Y LA MADRE, Y DE ACUERDO AL ACTA DE RECONOCIMIENTO EXHIBIDA, QUEDA RECONOCIDO(A) POR SUS PADRES, POR TAL RAZÓN LA ACLARACIÓN ES PROCEDENTE  \n\
"
                                //errorescivil[5]=1 
                                        errorescivil[5]=0 
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
                conterrores++
          }
          
      }
        //aclarados=aclarados.replaceAll("EL NOMBRE DEL CONTRAYENTE","EL NOMBRE DEL CONTRAYENTE EN BASE A SU ACTA DE NACIMIENTO")
        //aclarados=aclarados.replaceAll("EL NOMBRE DE LA CONTRAYENTE","EL NOMBRE DE LA CONTRAYENTE EN BASE A SU ACTA DE NACIMIENTO")
         aclarados=aclarados.replaceAll("EL NOMBRE DEL CONTRAYENTE","EL NOMBRE DEL CONTRAYENTE DE ACUERDO A SU ACTA DE NACIMIENTO DE FECHA ANTERIOR AL MATRIMONIO")
         aclarados=aclarados.replaceAll("EL NOMBRE DE LA CONTRAYENTE","EL NOMBRE DE LA CONTRAYENTE DE ACUERDO A SU ACTA DE NACIMIENTO DE FECHA ANTERIOR AL MATRIMONIO")
                 
        
        
        //enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")
        //def enarchivo=EncargadoArchivo.find("From EncargadoArchivo where activo=true")
        def usuactual=User.get(springSecurityService.currentUser.id)
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
         def afavor=""
         afavor+=(scasolInstance.nom_intere.equals())?"":scasolInstance.nom_intere
         afavor+=(scasolInstance.ap1_intere.equals() || scasolInstance.ap1_intere.isEmpty())?"":" "+scasolInstance.ap1_intere
         afavor+=(scasolInstance.ap2_intere.equals() || scasolInstance.ap2_intere.isEmpty())?"":" "+scasolInstance.ap2_intere
         //println(afavor)
         //println(scasolInstance.promov)
        if(afavor.equals(scasolInstance.promov))
         {
            favore=scasolInstance.promov+" promoviendo por su propio derecho." 
            promover=scasolInstance.promov+" por su propio derecho"
         }
         else
         {
             if(scasolInstance.typact.toString().equals("MATRIMONIO"))
             {
                favore=scasolInstance.promov+" promoviendo a favor de "+afavor
            promover=scasolInstance.promov+" a favor de los contrayentes" 
             }
             else{
                favore=scasolInstance.promov+" promoviendo a favor de "+afavor
                promover=scasolInstance.promov+" a favor del registrado" 
                if(scasolInstance.sexintere.toString().contains("F"))
                {
                favore=scasolInstance.promov+" promoviendo a favor de "+afavor
                promover=scasolInstance.promov+" a favor de la registrada"  
                }
             }
            
                     
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
pa2+=erroreacta+errart71+"\n\
\n\
"
        def paesp=""
        paesp+=pa1
        paesp+=pa2
pa3="TERCERO.- CON BASE EN EL ANÁLISIS A LAS FOTOCOPIAS CERTIFICADAS DEL ACTA DE "+scasolInstance.typact.toString()+", SE OBSERVAN LAS SIGUIENTES INCONSISTENCIAS:\n\
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
            //println("la localidad es igual al municipio")
            lugar+=scasolInstance.mpo.toString()+", "+scasolInstance.dto
        }else{
            lugar+=scasolInstance.loc.toString()+", "+scasolInstance.mpo.toString()+", "+scasolInstance.dto
        }
       // println(errart71+"{{{{{{{{")        
       // println(aclarados+"---------")
        //println(listaerr+"*******")
        //println(errorescivil.length+" aqui va el tamaño")
        for(int u=0;u<errorescivil.length;u++)
        {
            //println(errorescivil[u])
            
        }
        //println("///////////////////")
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
       // println(art37 +"Aqui va el 37 *")
        //println("Aqui va el reglamento "+artreglamento)
        for(int s=art37.toString().length();s>0;s--)
        {
            //println(art37.charAt(s-1))
            if(art37.charAt(s-1)==44)
            {
              //  println("Esta es la ultima coma del art 37")
                art37=art37.substring(0,s-1)+" y "+art37.substring(s,art37.toString().length())
                break
            }
        }
        
        for(int f=artreglamento.toString().length();f>0;f--)
        {
            if(artreglamento.charAt(f-1)==44)
            {
                //println("Esta es la ultima coma del reglamento")
                artreglamento=artreglamento.substring(0,f-1)+" y"+artreglamento.substring(f,artreglamento.toString().length())
                break
            }
        }
        
        artreglamento+=art37
        //println(artreglamento)
        //println("---------")  
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
          //  println(art68letras+" ???????????????")
            
         }
        
        def pa5="SEGUNDO.- Aclarese el acta de "+scasolInstance.typact+" "+"de: "+afavor+" "+"levantada según registro número "+scasolInstance.numact.toString()+" "+"de fecha "+fech[2]+" de "+meses2[Integer.valueOf(fech[1])]+" de "+fech[0]+" en "+lugar+", OAXACA. a fin de que al margen de la misma se haga la anotación siguiente:\n\
\"...Con fundamento en lo dispuesto por los artículos "+""+""/*reconocimiento*/+""+artmat2+""+art68letras+""+artcodigo+" y 142 del Código Civil Vigente en el Estado, en relación a los artículos "+artreglamento+" del Reglamento del Registro Civil se aclaran los siguientes datos: "+aclarados+" Aclaración del acta del Registro Civil de fecha "+fech2[2]+" de "+meses2[Integer.valueOf(fech2[1])]+ " de "+fech2[0]+" .- Promovida por "+promover+" .-Expediente Número "+scasolInstance.expro.toString()+"/"+scasolInstance.expano.toString()+" "+".-Conste.- El jefe de la Unidad Jurídica del Registro Civil del Estado de Oaxaca.- "+"Lic."+" "+enjuridico+"...\"\n\
\n\
"
        if(!artmat.isEmpty())
        {
            
            pa5=pa5.replaceFirst("y 142 del C","142, 181 y 206 del C")
            if(!legitimacion.isEmpty())
            {
                pa5=pa5.replaceFirst("142 y 206 del C","142, 181, 206, 367 Y 368 del C")
            }
            
        }
        else {
            if(!legitimacion.isEmpty())
        {
            pa5=pa5.replaceFirst("y 142 del C","142, 367 y 368 del C")
        }
        }
/*        if(!artmat.isEmpty() && !legitimacion.isEmpty())
        {
            pa5=pa5.replaceFirst("142 y 206 del C", "142, 206 del C")
        }*/
        
       def of1=Scaofi.findByDescrip(scasolInstance.ofi.toString())
        //println(of1.nombre+" AQui va toda la Descripcion pára la oficialia ¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿")
        pa5=pa5.replaceFirst(",  ,",",")
def pa6="TERCERO.- Remítase copia de esta resolucón al C. Oficial del Registro Civil de "+scasolInstance.ofi+""+of1.nombre+", OAXACA, para que después de haber hecho la anotación correspondiente sea agregada al apéndice respectivo.\n\
 \n\
ASÍ LO RESOLVIÓ Y FIRMA EL C. JEFE DE LA UNIDAD JURÍDICA DEL REGISTRO CIVIL EN EL ESTADO DE OAXACA. DOY FE"
        def pa7="\n\
\n\
\n\
                                                                                        "+enjuridico.titulo+" "+enjuridico+"\n\
\n\
"+enjuridico.nombre.substring(0,1)+""+enjuridico.ape_pat.substring(0,1)+""+enjuridico.ape_mat.substring(0,1)+"/"
        
  def papa=pa5.split("\\.\\.\\.")
  
        
        
        artmat2=""
        return pa1+pa2+pa3+pa4+pa5+pa6+pa7+scasolInstance.cap.toString()

    }
    
    
    
         public String imprimeArchivo(String expediente)
    {
        artmat2=""
        artmat=""
        legitimacion=""
        def scasolInstance=Scasol.get(expediente)
        def fsol=scasolInstance.fechasol.toString().substring(0,10).split("-")
               new File("D:/codigo.png").withOutputStream { out ->
    barcode4jService.render("code39Generator", scasolInstance.expro+"/"+scasolInstance.expano, out, "image/png")
}  
        
     // String [] meses2=["","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"]
      def errorescivil=[0,0,0,0,0,0,0].toArray()
      def pa1=""
      pa1+="Visto el escrito presentado con fecha "+fsol[2]+" de "+meses2[Integer.parseInt(fsol[1])] +" de "+fsol[0]+" y la(s) copia(s) del Acta"+
        " que contiene el(los) error(es) que se menciona(n), y demás documentos que se acompañan, se tiene"+
        " a "
        
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
      
      def listaScaerr //=Scaerr.findAllByExproAndProcede(params.id,"SI")
      listaScaerr=Scaerr.findAll("From Scaerr where expro=? and procede=? and (donde=? or donde=?)",[Long.parseLong(expediente),"SI",2,3])
      def listaScaact //=Scaact.findAllByExpproAndProcede(params.id,"SI")
      listaScaact=Scaact.findAll("From Scaact where exppro=? and procede=? and (donde=? or donde=?)",[Integer.parseInt(expediente),"SI",Terror.get(2),Terror.get(3)])
      //def listOpciones=Opcion.findAllByExapro(params.id)
       //listaScaact=Scaact.findAllByExppro(4)
       //println(listaScaact.size()+" |||||||||||||||||||||||||||||")
       //println(listaScaerr.size()+" |||||||||||||||||||||||||||||")
      if(listaScaerr.size()==0 && listaScaact.size()==0)
       {
           return ""
       }
         
        String [] fec =scasolInstance.fchact.toString().split(" ")        
        String [] fech=fec[0].split("-")        
        
        
        
                String [] fecha1 
        
        if(!scasolInstance.fecharesolucion.equals(null))
        {
          fecha1 =scasolInstance.fecharesolucion.toString().split(" ")
            
        }
        else
        {
            
            fecha1 =scasolInstance.fchsol.toString().split(" ")
        }
        //String [] fecha1 =scasolInstance.fchsol.toString().split(" ")        
        String [] fech2=fecha1[0].split("-")
        
        
      if(listaScaerr.equals()){println("no tiene errores agregados")}
      else
      {
          def fieldsInstance
                   
          for(int i=0;i<listaScaerr.size();i++)
          {
              if(listaScaerr.tcorrect[i].toString().equals("INADECUADA UBICACION DE DATOS"))
              {
                errorescivil[1]=1  
                erroresreglamento[2]=1
              }
              if( listaScaerr.campo[i].toString().contains("LUGAR DE REGISTRO") ||
                  listaScaerr.campo[i].toString().contains("LUGAR DE NACIMIENTO") ||
                  listaScaerr.campo[i].toString().contains("FECHA DE NACIMIENTO") ||
                  listaScaerr.campo[i].toString().contains("FECHA DE REGISTRO")
              )
              {
                  erroresreglamento[6]=1
              }
                if(listaScaerr.contiene[i].toString().equals("*") && listaScaerr.campo[i].equals())                
                {
                erroreacta+="TESTAR DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+". "
                    listaerr+=conterrores+".-SE AUTORIZA TESTAR DE OFICIO LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" DENTRO DEL TEXTO DEL ACTA "+listaScaerr.debeser[i]+".\n\
"
                    if(listaScaerr.base[i].equals("LA CIRCULAR 002 DE FECHA 15 DE JUNIO DE 1988"))
                    {
                      aclarados+="CON FUNDAMENTO EN LA CIRCULAR 002 DE FECHA 15 DE JUNIO DE 1988, SE AUTORIZA TESTAR  "
                      aclarados+=""+listaScaerr.tcorrect[i].toString()+" EN VIRTUD DE QUE CARECE DE LOS ELEMENTOS ESENCIALES"
                      aclarados+=" PARA QUE TENGA VALIDEZ PLENA.-"
                     //aclarados+="EN EL ESTADO, SE AUTORIZA TESTAR LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" DENTRO DEL TEXTO DEL ACTA, "+listaScaerr.debeser[i].toString()+".-"
                      
                    }
                    else if(listaScaerr.debeser[i].contains("RELACION DE FILIACION")){
                    aclarados+="CON FUNDAMENTO EN EL ARTICULO 385 PARRAFO PRIMERO DEL CODIGO CIVIL VIGENTE "
                    aclarados+="EN EL ESTADO, SE AUTORIZA TESTAR LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" DENTRO DEL TEXTO DEL ACTA, "+listaScaerr.debeser[i].toString()+".-"
                    
                    }
                    else {
                        aclarados+="CON FUNDAMENTO EN EL ARTICULO 47 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE AUTORIZA TESTAR "
                        aclarados+=""+listaScaerr.tcorrect[i].toString()+" EN VIRTUD DE SER IMPROCEDENTE.-"
                    }
                    //aclarados+="CON FUNDAMENTO EN EL ARTICULO 385 PARRAFO PRIMERO DEL CODIGO CIVIL VIGENTE "
                    //aclarados+="EN EL ESTADO, SE AUTORIZA TESTAR LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" DENTRO DEL TEXTO DEL ACTA, "+listaScaerr.debeser[i].toString()+".-"
                    //aclarados+="CON FUNDAMENTO EN "+listaScaerr.base[i].toString()+" SE AUTORIZA TESTAR DE OFICIO LOS DATOS RELATIVOS A "+listaScaerr.tcorrect[i].toString()+" "+listaScaerr.debeser[i].toString()+".-"
                }else{
                    
                    if(listaScaerr.campo[i].toString().contains("EL SEXO"))
                    {
                //        println("Vamos a ocuapar el 68 fraccion III")
                       // articulo68[2]=1
                    }
                    if(listaScaerr.campo[i].toString().contains("APELLIDO"))
                    {
                  //      println("Vamos a ocuapar el 68 fraccion V")
                        //articulo68[4]=1
                    }
               erroreacta+=listaScaerr.tcorrect[i].toString()+" DE "+listaScaerr.campo[i].toString()+". "
                  fieldsInstance=Fields.findByNombre(listaScaerr.campo[i].toString())
                
                    if(fieldsInstance.tipo.toString().equals("N"))
                    {
                    erroresreglamento[9]=1
                    }
                if(listaScaerr.campo[i].toString().contains("EL REGIMEN"))
                {
                    artmat="y 206"
                }
                if((listaScaerr.campo[i].toString().equals("EL NOMBRE DEL CONTRAYENTE") ||
                        listaScaerr.campo[i].toString().equals("EL NOMBRE DE LA CONTRAYENTE")
                    || listaScaerr.campo[i].toString().contains("APELLIDO")
                    || listaScaerr.campo[i].toString().equals("EL(LOS) NOMBRE(S) PROPIO(S) DEL CONTRAYENTE")
                    || listaScaerr.campo[i].toString().equals("EL(LOS) NOMBRE(S) PROPIO(S) DE LA CONTRAYENTE"))
                    && (listaScaerr.base[i].contains("ACTA DE") || listaScaerr.base[i].contains("DOCUMENTOS")) 
                    && scasolInstance.typact.toString().equals("MATRIMONIO")){   
                    //println("Paso por la 100 fraccion 1 busca el error")
                    artmat2="100 Fraccion I, "
                    //aclarados=aclarados.replaceAll("EL NOMBRE DEL CONTRAYENTE","EL NOMBRE DEL CONTRAYENTE EN BASE A SU ACTA DE NACIMIENTO")
                    //aclarados=aclarados.replaceAll("EL NOMBRE DE LA CONTRAYENTE","EL NOMBRE DE LA CONTRAYENTE EN BASE A SU ACTA DE NACIMIENTO")
                    //aclarados=aclarados.replaceAll("EL NOMBRE DEL CONTRAYENTE","EL NOMBRE DEL CONTRAYENTE DE ACUERDO A SU ACTA DE NACIMIENTO DE FECHA ANTERIOR AL MATRIMONIO")
                    //aclarados=aclarados.replaceAll("EL NOMBRE DE LA CONTRAYENTE","EL NOMBRE DE LA CONTRAYENTE DE ACUERDO A SU ACTA DE NACIMIENTO DE FECHA ANTERIOR AL MATRIMONIO")
                
                }
              
     if(listaScaerr.tcorrect[i].toString().equals("OMISION") && !listaScaerr.campo[i].toString().contains("APELLIDO") && !listaScaerr.base[i].toString().contains("REFORMA AL ARTICULO 141"))
                {
                    
                    listaerr+=conterrores+".-"+listaScaerr.tcorrect[i]+" DE "+listaScaerr.campo[i]+" Y DE ACUERDO A "+listaScaerr.base[i]+", LO CORRECTO DEBE SER: "+listaScaerr.debeser[i].toString()+""+" \n\
"
                }
                else
                {
                     if(listaScaerr.tcorrect[i].toString().equals("OMISION") && listaScaerr.campo[i].toString().contains("APELLIDO")
                       && listaScaerr.base[i].toString().contains("LA REFORMA AL ARTICULO 141"))
                {
                    errorescivil[6]=1
                  //  println("va a hacer aclaracion por uso")
                    aclarados+="SE AGREGA "+listaScaerr.campo[i]+" POR SIMPLE USO Y SIN CREAR FILIACION : "+listaScaerr.debeser[i].toString()+".-"
                    //aclarados+="SE AGREGA EL SEGUNDO APELLIDO POR SIMPLE USO SIN CREAR FILIACION: "+listaScaerr.debeser[i].toString()+".-"
                    listaerr+=conterrores+".-"+"OMISION"+" DE "+listaScaerr.campo[i]+" Y DE ACUERDO A "+listaScaerr.base[i]+", LO CORRECTO DEBE SER: "+listaScaerr.debeser[i].toString()+""+" \n\
"                   
                   conterrores++
                   continue
                }else {
                    if(listaScaerr.base[i].toString().contains("APELLIDO POR SIMPLE USO"))
                    {
                    //    println("Agrega apellido por simple uso")
                        errorescivil[6]=1
                        listaerr+=conterrores+".-"+listaScaerr.tcorrect[i]+" DE "+listaScaerr.campo[i]+", TENEMOS QUE APARECE ASENTADO(A) COMO "+listaScaerr.contiene[i]+" Y DE ACUERDO A "+listaScaerr.base[i]+", LO CORRECTO DEBE SER: "+listaScaerr.debeser[i].toString()+" \n\
"                   
                        conterrores++
                        if(listaScaerr.base[i].toString().contains("SE AGREGA UN SEGUNDO APELLIDO POR SIMPLE USO"))
                        {
                            if(scasolInstance.sexintere.contains("F"))
                            {
                              aclarados+="SE AGREGA EL SEGUNDO APELLIDO POR SIMPLE USO SIN CREAR FILIACION, SIENDO EL NOMBRE COMPLETO DE LA REGISTRADA: "+listaScaerr.debeser[i].toString()+".-"
                      
                            }
                            else
                            {
                               aclarados+="SE AGREGA EL SEGUNDO APELLIDO POR SIMPLE USO SIN CREAR FILIACION, SIENDO EL NOMBRE COMPLETO DEL REGISTRADO: "+listaScaerr.debeser[i].toString()+".-"
                       
                            }
                            
                        }else{
                            if(scasolInstance.sexintere.contains("F"))
                            {
                            aclarados+="SE AGREGA EL PRIMER APELLIDO POR SIMPLE USO SIN CREAR FILIACION, SIENDO EL NOMBRE COMPLETO DE LA REGISTRADA: "+listaScaerr.debeser[i].toString()+".-"
                    
                            }
                            else 
                            {
                            aclarados+="SE AGREGA EL PRIMER APELLIDO POR SIMPLE USO SIN CREAR FILIACION, SIENDO EL NOMBRE COMPLETO DEL REGISTRADO: "+listaScaerr.debeser[i].toString()+".-"
                       
                            }
                            
                                    
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
                        listaScaerr.campo[i].toString().contains("NOMBRE DEL PADRE")) && listaScaerr.base[i].toString().contains("ACTA DE NACIMIENTO DE") && scasolInstance.typact.toString().equals("NACIMIENTO"))
                    {
                        aclarados+=listaScaerr.campo[i].toString()+" EN BASE A SU ACTA DE NACIMIENTO: "+listaScaerr.debeser[i].toString()+".- "
                    }
                    else{
                        aclarados+=listaScaerr.campo[i].toString()+" : "+listaScaerr.debeser[i].toString()+".- "
                    }
                }
                    
                    
                }
              

              conterrores++
              //println(erroreacta+" -.-.-.-.-.-.-.-.-.-.-.-.-.")
          }
      }

      if(listaScaact.equals()){println("no tiene aplicacion del articulo 71")}
      else
      {
/*          listaerr+="\n\
"*/
          errorescivil[5]=0
          errorescivil[5]=0 
          erroresreglamento[8]=1
          //println("tiene aplicacion del art. 71")
          for(int i=0; i<listaScaact.size();i++)
          {     
             // println(listaScaact.tipoerresp[i].toString() +" este es el tipo del 71")
                if(listaScaact.tipoerresp[i].toString().equals("APLICACION DEL ARTICULO 71"))
                {
                     if(listaScaact.numacta[i].equals() || listaScaact.fechaacta[i].equals(null) || listaScaact.dto[i].equals() || listaScaact.mpo[i].equals() || listaScaact.loc[i].equals())
                    {
                        
                    }else
                    {
                        aclarados+="CON FUNDAMENTO EN EL ARTICULO 71 DEL CODIGO CIVIL VIGENTE EN EL ESTADO Y EN VIRTUD "
                        aclarados+="DEL ACTA DE MATRIMONIO DE LOS PADRES DEL REGISTRADO CON NUMERO "+listaScaact.numacta[i]+" LEVANTADA "
                        aclarados+="EN "
                        aclarados+=""+(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))?listaScaact.mpo[i].toString()+", "+listaScaact.dto[i].toString()+", OAXACA ":listaScaact.loc[i].toString()+", "+listaScaact.mpo[i].toString()+", "+listaScaact.dto[i].toString()+", OAXACA "
                        aclarados+="DE FECHA "+listaScaact.fechaacta[i].split("-")[2]+" DE "+meses2[Integer.valueOf(listaScaact.fechaacta[i].split("-")[1])]+" DE "+listaScaact.fechaacta[i].split("-")[0]+" "
                        aclarados+="ANTERIOR AL REGISTRO, SE ANOTAN LOS SIGUIENTES DATOS: "
                        
                        /*aclarados+="EN VIRTUD DEL ACTA DE MATRIMONIO DE LOS PADRES DEL REGISTRADO, CON NUMERO DE REGISTRO "+listaScaact.numacta[i]+" LEVANTADA EN "
                        aclarados+=""+(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))?listaScaact.mpo[i].toString()+", "+listaScaact.dto[i].toString()+", OAXACA ":listaScaact.loc[i].toString()+", "+listaScaact.mpo[i].toString()+", "+listaScaact.dto[i].toString()+", OAXACA "
                        aclarados+="DE FECHA "+listaScaact.fechaacta[i].split("-")[2]+" DE "+meses2[Integer.valueOf(listaScaact.fechaacta[i].split("-")[1])]+" DE "+listaScaact.fechaacta[i].split("-")[0]+" ANTERIOR A LA FECHA DEL REGISTRO DE NACIMIENTO Y CON FUNDAMENTO "
                        aclarados+="EN EL ARTICULO 71 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE ANOTAN LOS SIGUIENTES DATOS: "*/
                        
                        //aclarados+="CON FUNDAMENTO EN EL ARTICULO 71 DEL CÓDIGO CIVIL VIGENTE EN EL ESTADO, SE ANOTAN LOS SIGUIENTES DATOS: "
                                         if(!listaScaact.pnombre[i].equals()){
                                             apli71[0]=1
                                             aclarados+="EL NOMBRE DEL PADRE: "+listaScaact.pnombre[i]+""+(listaScaact.pap1[i].equals()? "":" "+listaScaact.pap1[i])+""+(listaScaact.pap2[i].equals() ? "":" "+listaScaact.pap2[i])+""+".-"}
                                         if(!listaScaact.pedad[i].equals()){apli71[0]=1
                                             Letras letras = new Letras(Integer.valueOf(listaScaact.pedad[i]))
                                             edadletra=letras.convertirLetras(listaScaact.pedad[i])
                                             aclarados+="LA EDAD DEL PADRE AL MOMENTO DEL REGISTRO: "+listaScaact.pedad[i]+" "+edadletra.toString().toUpperCase()+" AÑOS.-"}
                                         if(!listaScaact.pnac[i].equals()){apli71[0]=1
                                             aclarados+="LA NACIONALIDAD DEL PADRE: "+listaScaact.pnac[i]+".-"}
                                         if(!listaScaact.pab1[i].equals()){apli71[0]=1
                                             aclarados+="EL NOMBRE DEL ABUELO PATERNO: "+listaScaact.pab1[i]+(listaScaact.pab1ap1[i].equals()?"": " "+listaScaact.pab1ap1[i])+(listaScaact.pab1ap2[i].equals()? "":" "+listaScaact.pab1ap2[i])+".-"}                                         
                                         if(!listaScaact.pab1nac[i].equals()){apli71[0]=1
                                             aclarados+="LA NACIONALIDAD DEL ABUELO PATERNO: "+listaScaact.pab1nac[i]+".-"}
                                         if(!listaScaact.pab2[i].equals()){apli71[0]=1
                                             aclarados+="EL NOMBRE DE LA ABUELA PATERNA: "+listaScaact.pab2[i]+(listaScaact.pab2ap1[i].equals()? "":" "+listaScaact.pab2ap1[i])+(listaScaact.pab2ap2[i].equals()?"":" "+listaScaact.pab2ap2[i])+".-"}
                                         if(!listaScaact.pab1nac[i].equals() && !listaScaact.pab2[i].equals()){apli71[0]=1
                                             aclarados+="LA NACIONALIDAD DE LA ABUELA PATERNA: "+listaScaact.pab1nac[i]+".-"}                        
                                         if(!listaScaact.mnom[i].equals()){apli71[1]=1
                                             aclarados+="EL NOMBRE DE LA MADRE: "+listaScaact.mnom[i]+(listaScaact.map1[i].equals()? "":" "+listaScaact.map1[i])+(listaScaact.map2[i].equals()? "":" "+listaScaact.map2[i])+".-"}
                                         if(!listaScaact.medad[i].equals()){apli71[1]=1
                                             Letras letras = new Letras(Integer.valueOf(listaScaact.medad[i]))
                                             edadletra=letras.convertirLetras(listaScaact.medad[i])
                                             aclarados+="LA EDAD DE LA MADRE AL MOMENTO DEL REGISTRO: "+listaScaact.medad[i]+" "+edadletra.toString().toUpperCase()+" AÑOS.-"}
                                         if(!listaScaact.mnac[i].equals()){apli71[1]=1
                                             aclarados+="LA NACIONALIDAD DE LA MADRE: "+listaScaact.mnac[i]+".-"}
                                         if(!listaScaact.mab1[i].equals()){apli71[1]=1
                                             aclarados+="EL NOMBRE DEL ABUELO MATERNO: "+listaScaact.mab1[i]+(listaScaact.mab1ap1[i].equals()?"":" "+listaScaact.mab1ap1[i])+(listaScaact.mab1ap2[i].equals() ? "":" "+listaScaact.mab1ap2[i])+".-"}
                                         if(!listaScaact.mab1nac[i].equals() && !listaScaact.mab1[i].equals()){apli71[1]=1
                                             aclarados+="LA NACIONALIDAD DEL ABUELO MATERNO: "+listaScaact.mab1nac[i]+".-"}
                                         if(!listaScaact.mba2[i].equals()){apli71[1]=1
                                             aclarados+="EL NOMBRE DE LA ABUELA MATERNA: "+listaScaact.mba2[i]+(listaScaact.mab2ap1[i].equals()?"":" "+listaScaact.mab2ap1[i])+(listaScaact.mab2ap2[i].equals()?"":" "+listaScaact.mab2ap2[i])+".-"}
                                         if(!listaScaact.mab2nac[i].equals() && !listaScaact.mba2[i].equals()){apli71[1]=1
                                             aclarados+="LA NACIONALIDAD DE LA ABUELA MATERNA: "+listaScaact.mab2nac[i]+".-"}
                                        //println(apli71[0]+" este es el del padre" )
                                        //println(apli71[1]+" este es el de la madre")
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
                    //legitimacion="367 y 368"
                    def fechamatrimonio=listaScaact.fechaacta[i].toString().split("-")
                    def fechmatri=fechamatrimonio
                   /* println(fechmatri[0]+ "este el año")
                    println(fechmatri[1]+ "este el mes")
                    println(fechmatri[2]+ "este el dia")
                    println(fechamatrimonio[0]+" ************************************")
                    println(fechamatrimonio[1])*/
                    
                    
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
                            //aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+".-"
                            //aclarados+=""+scasolInstance.nom_intere+" "+scasolInstance.ap1_intere+" "+scasolInstance.ap2_intere+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A) EN VIRTUD DE "+listaScaact.bases[i]+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+".-"
                            aclarados+="CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE "
                            aclarados+="LA SIGUIENTE ANOTACION: LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDA Y EN "
                            aclarados+="VIRTUD DEL MATRIMONIO SUBSECUENTE DE LOS PADRES SE LE TIENE COMO HIJO NACIDO DE MATRIMONIO, "
                            aclarados+="SEGÚN ACTA NUMERO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+", OAXACA.-"
                        
                            }else{
                            //aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"
                            
                            aclarados+="CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE "
                            aclarados+="LA SIGUIENTE ANOTACION: LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDA Y EN "
                            aclarados+="VIRTUD DEL MATRIMONIO SUBSECUENTE DE LOS PADRES SE LE TIENE COMO HIJO NACIDO DE MATRIMONIO, "
                            aclarados+="SEGÚN ACTA NUMERO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+", OAXACA.-"
                            
                        }
        
                        errart71+="OMISION DE DATOS RELATIVOS A LA MADRE.-  "
                        listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A LA MADRE Y EN VIRTUD DEL MATRIMONIO SUBSECUENTE DE SUS PADRES, LA ACLARACIÓN ES PROCEDENTE \n\
"              
                        errorescivil[5]=0 
                        errorescivil[5]=0 
                        erroresreglamento[8]=1
                        //errorescivil[5]==1
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
                            //aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"
                            aclarados+="CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE "
                            aclarados+="LA SIGUIENTE ANOTACION: LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDA Y EN "
                            aclarados+="VIRTUD DEL MATRIMONIO SUBSECUENTE DE LOS PADRES SE LE TIENE COMO HIJO NACIDO DE MATRIMONIO, "
                            aclarados+="SEGÚN ACTA NUMERO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+", OAXACA.-"
                        
                        }else{
                            //aclarados+=""+"CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO: LA PERSONA"+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) Y LEGITIMADO(A)"+""+" SEGÚN ACTA DE MATRIMONIO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"
                        aclarados+="CON FUNDAMENTO EN LOS ARTICULOS 367 Y 368 DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE "
                            aclarados+="LA SIGUIENTE ANOTACION: LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDA Y EN "
                            aclarados+="VIRTUD DEL MATRIMONIO SUBSECUENTE DE LOS PADRES SE LE TIENE COMO HIJO NACIDO DE MATRIMONIO, "
                            aclarados+="SEGÚN ACTA NUMERO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+ meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+", OAXACA.-"
                            
                        }
                                 errart71+="OMISION DE DATOS RELATIVOS A EL PADRE.- "
                                 listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A EL PADRE Y EN VIRTUD DEL MATRIMONIO SUBSECUENTE DE SUS PADRES, LA ACLARACIÓN ES PROCEDENTE \n\
"
                                errorescivil[5]=0 
                                errorescivil[5]=0 
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
                                        errorescivil[5]=0
                                        errorescivil[5]=0 
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
                    //println("oficio de reconocmiento")
                    def fechamatrimonio=listaScaact.fechaacta[i].toString().split("-")
                    def fechmatri=fechamatrimonio

                    if(listaScaact.numacta[i].equals() || listaScaact.fechaacta[i].equals(null) || listaScaact.dto[i].equals() || listaScaact.mpo[i].equals() || listaScaact.loc[i].equals())
                    {
                        
                    }else
                    {
                        if(listaScaact[i].dto.toString().equals(scasolInstance.dto.toString()) &&
                           listaScaact[i].mpo.toString().equals(scasolInstance.mpo.toString()) &&
                           listaScaact[i].loc.toString().equals(scasolInstance.loc.toString()))
                       {
                           //println("El reconocimiento fue en el mismo lugar")
                           reconocimiento="84, "
                       }
                       else
                       {
                           //println("El reconocimiento no fue en el mismo lugar")
                           reconocimiento="84, 85, "
                       }

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
                        //println ("Datos relativos LA MADRE")
                        if(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))
                                        {
                                            aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE LA SIGUIENTE ANOTACION : "+"LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDO(A) POR SU MADRE SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+", OAXACA. "   
                                        }else{
                                            aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE LA SIGUIENTE ANOTACION : "+"LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDO(A) POR SU MADRE SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+", OAXACA. "   
                                        
                                            } 
                        errart71+="OMISION DE DATOS RELATIVOS A LA MADRE.  "
                        listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A LA MADRE, Y DE ACUERDO AL ACTA DE RECONOCIMIENTO EXHIBIDA, QUEDA RECONOCIDO(A) POR SU MADRE, POR TAL RAZÓN LA ACLARACIÓN ES PROCEDENTE \n\
"
                        errorescivil[5]=0 
                        errorescivil[5]=0 
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
                                 //println ("Datos relativos a EL PADRE")
                                 if(listaScaact.loc[i].toString().equals(listaScaact.mpo[i].toString()))
                                        {
                                             aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE LA SIGUIENTE ANOTACION : LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDO(A) POR SU PADRE SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+", "+listaScaact.dto[i]+", OAXACA.-"   
                                        
                                         }else{
                                             aclarados+=""+" CON FUNDAMENTO EN EL ARTICULO "+reconocimiento.substring(0,(reconocimiento.length()-2)).replaceAll(", "," Y ")+" DEL CODIGO CIVIL VIGENTE EN EL ESTADO, SE HACE LA SIGUIENTE ANOTACION : LA PERSONA A QUIEN SE REFIERE LA PRESENTE ACTA QUEDO RECONOCIDO(A) POR SU PADRE SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+", OAXACA.-"   
                                        
                                            }                              
                                                               
                                 errart71+="OMISION DE DATOS RELATIVOS A EL PADRE. "
                                 listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A EL PADRE, Y DE ACUERDO AL ACTA DE RECONOCIMIENTO EXHIBIDA, QUEDA RECONOCIDO(A) POR SU PADRE, POR TAL RAZÓN LA ACLARACIÓN ES PROCEDENTE \n\
"
                                errorescivil[5]=0
                                errorescivil[5]=0 
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
                                         aclarados+=""+scasolInstance.nom_intere+" "+scasolInstance.ap1_intere+" "+scasolInstance.ap2_intere+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) POR SUS PADRES SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"   
                                        }else{
                                             aclarados+=""+scasolInstance.nom_intere+" "+scasolInstance.ap1_intere+" "+scasolInstance.ap2_intere+" A QUIEN SE REFIERE EL ACTA QUEDO RECONOCIDO(A) POR SUS PADRES SEGÚN ACTA DE RECONOCIMIENTO CON NÚMERO DE REGISTRO "+listaScaact.numacta[i]+" DE FECHA "+fechmatri[2]+" DE "+meses2[Integer.valueOf(fechmatri[1])]+" DE "+fechmatri[0]+", LEVANTADA EN "+listaScaact.loc[i]+", "+listaScaact.mpo[i]+" ,"+listaScaact.dto[i]+".-"   
                                             
                                            }                                                                                     
                                       // println ("Datos relativos al padre y la madre") 
                                        errart71+="OMISION DE DATOS RELATIVOS A EL PADRE Y LA MADRE. "
                                        listaerr+=conterrores+".-OMISION DE DATOS RELATIVOS A EL PADRE Y LA MADRE, Y DE ACUERDO AL ACTA DE RECONOCIMIENTO EXHIBIDA, QUEDA RECONOCIDO(A) POR SUS PADRES, POR TAL RAZÓN LA ACLARACIÓN ES PROCEDENTE  \n\
"
                                errorescivil[5]=0
                                        errorescivil[5]=0 
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
                conterrores++
          }
             
      }
        
        
        //aclarados=aclarados.replaceAll("EL NOMBRE DEL CONTRAYENTE","EL NOMBRE DEL CONTRAYENTE EN BASE A SU ACTA DE NACIMIENTO")
        //aclarados=aclarados.replaceAll("EL NOMBRE DE LA CONTRAYENTE","EL NOMBRE DE LA CONTRAYENTE EN BASE A SU ACTA DE NACIMIENTO")
         aclarados=aclarados.replaceAll("EL NOMBRE DEL CONTRAYENTE","EL NOMBRE DEL CONTRAYENTE DE ACUERDO A SU ACTA DE NACIMIENTO DE FECHA ANTERIOR AL MATRIMONIO")
         aclarados=aclarados.replaceAll("EL NOMBRE DE LA CONTRAYENTE","EL NOMBRE DE LA CONTRAYENTE DE ACUERDO A SU ACTA DE NACIMIENTO DE FECHA ANTERIOR AL MATRIMONIO")
                       
        //enjuridico=EncargadoJuridico.find("From EncargadoJuridico where activo=true")
        enarchivo=EncargadoArchivo.find("From EncargadoArchivo where activo=true")
        def usuactual=User.get(springSecurityService.currentUser.id)
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
         //def afavor=scasolInstance.nom_intere+" "+scasolInstance.ap1_intere+" "+scasolInstance.ap2_intere
         def afavor=""
         afavor+=(scasolInstance.nom_intere.equals())?"":scasolInstance.nom_intere
         afavor+=(scasolInstance.ap1_intere.equals() || scasolInstance.ap1_intere.isEmpty())?"":" "+scasolInstance.ap1_intere
         afavor+=(scasolInstance.ap2_intere.equals() || scasolInstance.ap2_intere.isEmpty())?"":" "+scasolInstance.ap2_intere
         
         if(afavor.equals(scasolInstance.promov))
         {
            favore=scasolInstance.promov+" promoviendo por su propio derecho." 
            promover=scasolInstance.promov+" por su propio derecho"
         }
         else
         {
               if(scasolInstance.typact.toString().equals("MATRIMONIO"))
             {
                favore=scasolInstance.promov+" promoviendo a favor de "+afavor
            promover=scasolInstance.promov+" a favor de los contrayentes" 
             }
             else{
                favore=scasolInstance.promov+" promoviendo a favor de "+afavor
                promover=scasolInstance.promov+" a favor del registrado" 
                if(scasolInstance.sexintere.toString().contains("F"))
                {
                favore=scasolInstance.promov+" promoviendo a favor de "+afavor
                promover=scasolInstance.promov+" a favor de la registrada"  
                }
             }
            //favore=scasolInstance.promov+" promoviendo a favor de "+afavor
            //promover=scasolInstance.promov+" a favor del registrado"
                     
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
pa2+=erroreacta+errart71+"\n\
\n\
"
        def paesp=""
        paesp+=pa1
        paesp+=pa2
pa3="TERCERO.- CON BASE EN EL ANÁLISIS A LAS FOTOCOPIAS CERTIFICADAS DEL ACTA DE "+scasolInstance.typact.toString()+", SE OBSERVAN LAS SIGUIENTES INCONSISTENCIAS:\n\
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
            //println("la localidad es igual al municipio")
            lugar+=scasolInstance.mpo.toString()+", "+scasolInstance.dto
        }else{
            lugar+=scasolInstance.loc.toString()+", "+scasolInstance.mpo.toString()+", "+scasolInstance.dto
        }
       // println(errart71+"{{{{{{{{")        
       // println(aclarados+"---------")
        //println(listaerr+"*******")
        //println(errorescivil.length+" aqui va el tamaño")
        for(int u=0;u<errorescivil.length;u++)
        {
            println(errorescivil[u])
            
        }
        //println("///////////////////")
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
       // println(art37 +"Aqui va el 37 *")
        //println("Aqui va el reglamento "+artreglamento)
        for(int s=art37.toString().length();s>0;s--)
        {
            //println(art37.charAt(s-1))
            if(art37.charAt(s-1)==44)
            {
              //  println("Esta es la ultima coma del art 37")
                art37=art37.substring(0,s-1)+" y "+art37.substring(s,art37.toString().length())
                break
            }
        }
        
        for(int f=artreglamento.toString().length();f>0;f--)
        {
            if(artreglamento.charAt(f-1)==44)
            {
                //println("Esta es la ultima coma del reglamento")
                artreglamento=artreglamento.substring(0,f-1)+" y"+artreglamento.substring(f,artreglamento.toString().length())
                break
            }
        }
        
        artreglamento+=art37
        //println(artreglamento)
        //println("---------")  
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
          //  println(art68letras+" ???????????????")
            
         }
        
        def pa5="SEGUNDO.- Aclarese el acta de "+scasolInstance.typact+" "+"de: "+afavor+" "+"levantada según registro número "+scasolInstance.numact.toString()+" "+"de fecha "+fech[2]+" de "+meses2[Integer.valueOf(fech[1])]+" de "+fech[0]+" en "+lugar+", OAXACA. a fin de que al margen de la misma se haga la anotación siguiente:\n\
\"...Con fundamento en lo dispuesto por los artículos "+""/*reconocimiento*/+""+artmat2+""+art68letras+""+artcodigo+" y 142 del Código Civil Vigente en el Estado, en relación a los artículos "+artreglamento+" del Reglamento del Registro Civil se aclaran los siguientes datos: "+aclarados+" Aclaración del acta del Registro Civil de fecha "+fech2[2]+" de "+meses2[Integer.valueOf(fech2[1])]+ " de "+fech2[0]+" .- Promovida por "+promover+" .-Expediente Número "+scasolInstance.expro.toString()+"/"+scasolInstance.expano.toString()+" "+".-Conste.- El jefe de la Unidad Jurídica del Registro Civil del Estado de Oaxaca.- "+"Lic."+" "+enjuridico+"...\"\n\
\n\
"
        if(!artmat.isEmpty())
        {
            
            pa5=pa5.replaceFirst("y 142 del C","142, 181 y 206 del C")
            if(!legitimacion.isEmpty())
            {
                pa5=pa5.replaceFirst("142 y 206 del C","142, 181, 206, 367 Y 368 del C")
            }
            
        }
        else {
            if(!legitimacion.isEmpty())
        {
            pa5=pa5.replaceFirst("y 142 del C","142, 367 y 368 del C")
        }
        }
/*        if(!artmat.isEmpty() && !legitimacion.isEmpty())
        {
            pa5=pa5.replaceFirst("142 y 206 del C", "142, 206 del C")
        }*/
        

        pa5=pa5.replaceFirst(",  ,",",")
def pa6="TERCERO.- Remítase copia de esta resolucón al C. JEFE DEL ARCHIVO CENTRAL DEL REGISTRO CIVIL, para que después de haber hecho la anotación correspondiente sea agregada al apéndice respectivo.\n\
 \n\
ASÍ LO RESOLVIÓ Y FIRMA EL C. JEFE DE LA UNIDAD JURÍDICA DEL REGISTRO CIVIL EN EL ESTADO DE OAXACA. DOY FE"
        def pa7="\n\
\n\
\n\
                                                                                        "+enjuridico.titulo+" "+enjuridico+"\n\
\n\
"+enjuridico.nombre.substring(0,1)+""+enjuridico.ape_pat.substring(0,1)+""+enjuridico.ape_mat.substring(0,1)+"/"
        
  def papa=pa5.split("\\.\\.\\.")
  
   
      artmat2=""
        return pa1+pa2+pa3+pa4+pa5+pa6+pa7+scasolInstance.cap.toString()

    }
    
    
    
    
    
   public String negativa(String expediente,String anio){
        def scasolInstance
        scasolInstance=Scasol.findByExpanoAndExpro(anio,expediente)
        println(scasolInstance)
        def scaerrInstance
        scaerrInstance=Scaerr.findAllByExproAndProcede(scasolInstance.id,"NO")
        println(scaerrInstance)
        def fsol=scasolInstance.fechasol.toString().substring(0,10).split("-")
        def texto=""
        texto+="<p align=left>"+scasolInstance.promov+"</p>"
        texto+="<p align=left>P  R  E  S  E  N  T  E</p>"
        texto+="<br>"
        texto+="<br>"
        texto+="<p align=justify>Visto el escrito con fecha "+fsol[2]+" de "+meses2[Integer.parseInt(fsol[1])] +" de "+fsol[0]+" promovida por "
        texto+=""+scasolInstance.promov+" en el que solicita la aclaración de acta de "+scasolInstance.typact+" de "+scasolInstance.nom_intere
        texto+=" "+scasolInstance.ap1_intere+" "+scasolInstance.ap2_intere+".</p>"
        texto+="<p align=justify>Aduciendo que existen los siguientes errores:</p>"
        texto+="<br>"
        texto+="<br>"
        
        for(int i=0;i<scaerrInstance.size();i++)
        {
          texto+="<p align=justify>"+scaerrInstance.campo[i]+". "+scaerrInstance.base[i]
          texto+="</p>"
        }
        texto+="<br>"
        texto+="<br>"
        texto+="<p align=justify>Motivo por el cual.</p>"
        texto+="<p align=justify>Está Dirección no es competente para resolver sobre "
        texto+="la corrección de dicha acta de "+scasolInstance.typact+" Toda vez que "
        texto+="la situación plateada no se encuentra dentro de lo previsto por el Artículo 141 "
        texto+="del Código Civil vigente en el Estado en sus cinco fracciones, por tratarse de un caso "
        texto+="que se resolverá mediante el Juicio Ordinario de Rectificación de Acta del Registro "
        texto+="Civil, que deberá promoverse ante la Autoridad Judicial conforme al Código Civil Vigente en el Estado."
        texto+="<br>"
        texto+="<br>"
        texto+="Así lo resolvió y firma el C. Jefe de la Unidad Jurídica del Registro Civil en el Estado."
        texto+="<br>"
        texto+="<br>"
        texto+="<br>"
        texto+="<br>"
        println(texto)
        return texto
    }
}
