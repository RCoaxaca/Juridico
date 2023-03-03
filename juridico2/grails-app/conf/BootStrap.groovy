import com.testapp.Role
import com.testapp.User
import com.testapp.UserRole
import catalogos.Docesta
import catalogos.Entidades
import catalogos.Docesta
import catalogos.Campo
import catalogos.Terror
import catalogos.Tipoactas
import catalogos.Tipoerror
import catalogos.Erroresperado
import catalogos.Fields
import catalogos.Base
import catalogos.Scadto
import grails.util.Environment
import catalogos.Nacionalidad
import catalogos.Scaofi
import catalogos.Scampo
import catalogos.Localidades
import catalogos.EncargadoJuridico
import catalogos.EncargadoArchivo
import catalogos.Localidadofi
import org.codehaus.groovy.grails.commons.GrailsApplication
class BootStrap {
   
    def init = { servletContext ->
        def currentEnv= grails.util.Environment.current
        if(currentEnv==Environment.PRODUCTION){
            
            
        }else if(currentEnv==Environment.DEVELOPMENT){
//            def userInstance
//            def roleInstance
//            def roles=["ROLE_ADMIN","ROLE_USER","ROLE_DICTAMI","ROLE_VALI","ROLE_VENTA","ROLE_ARCHIVO"]
//            if(!Role.count()){
//                for(def rol:roles){
//                    roleInstance = new Role(authority:rol)
//                    roleInstance.save(flush: true, failOnError: true)
//                }
//            }
//            if(!User.findByUsername("admin")){
//                roleInstance=Role.findByAuthority("ROLE_ADMIN")
//                if(!roleInstance){
//                    roleInstance = new Role(authority:"ROLE_ADMIN")
//                    roleInstance.save(flush: true, failOnError: true)
//                }
//                userInstance=new User(username:'admin',password:'admin' , enabled:true,accountExpired:false,accountLocked:false,passwordExpired:false, apellpa:'',apellma:'',nombre:'Administrador')
//                userInstance.save(flush: true, failOnError: true)
//                roleInstance=Role.findByAuthority("ROLE_ADMIN")
//                if(!UserRole.findByUserAndRole( userInstance,roleInstance)){
//                    def userRoleInstance=new UserRole(user:userInstance, role:roleInstance)
//                    userRoleInstance.save(flush: true, failOnError: true)
//                }
//            }
//            def entidadInstance
//            if(!Entidades.findByNombre('OAXACA')){
//                entidadInstance=new Entidades(nombre:'OAXACA', clave:'20')
//                entidadInstance.save(flush: true, failOnError: true)
//            }
//            if(!Docesta.count()){
//                def docestados=["NUEVO","RECHAZADO","PROCEDE","PENDIENTE ACLARACION"]
//                def docestaInstance
//                for(def docesta:docestados){
//                    docestaInstance = new Docesta(docuestado:docesta,dc:1)
//                    docestaInstance.save(flush: true, failOnError: true)
//                }
//            }
//            if(!Campo.count()){
//                def campos=["DATOS RELATIVOS AL PADRE","DATOS RELATIVOS A LA MADRE","DATOS RELATIVOS AL PADRE Y LA MADRE"]
//                def campoInstance
//                for(def campo:campos){
//                    campoInstance = new Campo(campo:campo)
//                    campoInstance.save(flush: true, failOnError: true)
//                }
//            }
//            if(!Terror.count()){
//                def terrors=["EN LA OFICIALIA","EN EL ARCHIVO CENTRAL","ARCHIVO CENTRAL Y OFICIALIA"]
//                def terrorInstance
//                for(def terror:terrors){
//                    terrorInstance = new Terror(donde:terror)
//                    terrorInstance.save(flush: true, failOnError: true)
//                }
//            }
//            if(!Tipoactas.count()){
//                def tiposActas=["NACIMIENTO","RECONOCIMIENTO","ADOPCIÓN","MATRIMONIO","DIVORCIO","DEFUNCIÓN","INSCRIPCION DE.."]
//                def tipoactaInstance
//                for(def tipoacta:tiposActas){
//                    tipoactaInstance = new Tipoactas(tipoacta:tipoacta)
//                    tipoactaInstance.save(flush: true, failOnError: true)
//                }
//            }
//            if(!Tipoerror.count()){
//                def tiposError=["OMISION","OMISION PARCIAL","ERROR ORTOGRAFICO","ERROR LINGUISTICO","ERROR MECANOGRAFICO","INVERSION DE APELLIDO","CONTRADICCION DE NOMBRES","ERROR DE ESCRITURA","DATO ILEGIBLE","ACLARACION POR USO"]
//                def pant=1
//                def tipoerrorInstance
//                for(def tipoerror:tiposError){
//                    tipoerrorInstance = new Tipoerror(tipoerror:tipoerror, pant:pant)
//                    tipoerrorInstance.save(flush: true, failOnError: true)
//                }
//                tiposError=["EL PADRE Y ABUELOS PATERNOS","LA MADRE Y LOS ABUELOS MATERNOS","EL PADRE","LA MADRE","EL ABUELO PATERNO","LA ABUELA PATERNA","EL ABUELO MATERNO","LA ABUELA MATERNA","LA ANOTACION MARGINAL DE RECONOCIMIENTO","LA ANOTACION DE LEGITIMACION","LA ANOTACION MARGINAL DE ACLARACION","LOS ABUELOS MATERNOS"]
//                pant=2
//                for(def tipoerror:tiposError){
//                    tipoerrorInstance = new Tipoerror(tipoerror:tipoerror, pant:pant)
//                    tipoerrorInstance.save(flush: true, failOnError: true)
//                }
//                tiposError=["LOS PADRE DEL CONTRAYENTE","LOS PADRES DE LA CONTRAYENTE","EL PADRE DE CONTRAYENTE","LA MADRE DEL  CONTRAYENTE","EL PADRE DE LA CONTRAYENTE","LA MADRE DE LA CONTRAYENTE","LA ANOTACION MARGINAL DE ACLARACION","LA ANOTACION MARGINAL"]
//                pant=3
//                for(def tipoerror:tiposError){
//                    tipoerrorInstance = new Tipoerror(tipoerror:tipoerror, pant:pant)
//                    tipoerrorInstance.save(flush: true, failOnError: true)
//                }
//            }
//            
//            if(!Erroresperado.count()){
//                def tipoerrorInstance=Tipoerror.findByTipoerror('OMISION')
//                def errores=["ACLARACION","APLICACION DEL ARTICULO 71","OFICIO DE LEGITIMACION","OFICIO DE RECONOCIMIENTO","TESTAR OFICIO"]
//                def erroresperadoInstance
//                for(def error:errores){
//                    erroresperadoInstance = new Erroresperado(tipodeerror:error,tipoerr:tipoerrorInstance )
//                    erroresperadoInstance.save(flush: true, failOnError: true)
//                }
//                
//            }
//            if(!Fields.count()){
//                def array=[ "4--1--LA CURP--1--M--X","5--1--LA CRIP--1--M--X","6--1--LA OFICIALIA DE REGISTRO--2--M--X","7--1--EL NUMERO DE ACTA--3--M--X","8--1--EL LUGAR DE REGISTRO--4--M--L",
//                            "9--1--LA FECHA DE REGISTRO--5--M--F","10--1--EL SEXO--6--M--X","11--1--EL NOMBRE DEL REGISTRADO--7--M--X","12--1--EL(LOS) NOMBRE(S) PROPIO(S) DEL REGISTRADO--8--M--X",
//                            "13--1--EL APELLIDO PATERNO DEL REGISTRADO--9--M--X","14--1--EL APELLIDO MATERNO DEL REGISTRADO--10--M--X","15--1--LA FECHA DE NACIMIENTO DEL REGISTRADO--11--M--F",
//                            "16--1--EL LUGAR DE NACIMIENTO DEL REGISTRADO--12--M--L","17--1--EL ESTADO BIOLOGICO--13--M--X","18--1--LOS QUE COMPARECIERON--14--M--X",
//                            "19--1--EL NOMBRE DEL PADRE DEL REGISTRADO--15--M--C","20--1--EL(LOS) NOMBRE(S) PROPIO(S) DEL PADRE DEL REGISTRADO--16--M--X",
//                            "21--1--EL APELLIDO PATERNO DEL PADRE DEL REGISTRADO--17--M--X","22--1--EL APELLIDO MATERNO DEL PADRE DEL REGISTRADO--18--M--X","23--1--LA EDAD DEL PADRE--19--M--X",
//                            "24--1--EL ESTADO CIVIL DEL PADRE--20--M--X","25--1--LA NACIONALIDAD DEL PADRE--21--M--N","26--1--EL NOMBRE DE LA MADRE DEL REGISTRADO--22--M--C",
//                            "27--1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA MADRE DEL REGISTRADO--23--M--X","28--1--EL APELLIDO PATERNO DE LA MADRE DEL REGISTRADO--24--M--X",
//                            "29--1--EL APELLIDO MATERNO DE LA MADRE DEL REGISTRADO--25--M--X","30--1--LA EDAD DE LA MADRE--26--M--X","31--1--EL ESTADO CIVIL DE LA MADRE--27--M--X",
//                            "32--1--LA NACIONALIDAD DE LA MADRE--28--M--N","33--1--EL NOMBRE DEL ABUELO PATERNO--29--M--C","34--1--EL(LOS) NOMBRE(S) PROPIO(S) DEL ABUELO PATERNO--30--M--X",
//                            "35--1--EL APELLIDO PATERNO DEL ABUELO PATERNO--31--M--X","36--1--EL APELLIDO MATERNO DEL ABUELO PATERNO--32--M--X","37--1--LA NACIONALIDAD DEL ABUELO PATERNO--33--M--N",
//                            "38--1--EL NOMBRE DE LA ABUELA PATERNA--34--M--C","39--1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA ABUELA PATERNA--35--M--X","40--1--EL APELLIDO PATERNO DE LA ABUELA PATERNA--36--M--X",
//                            "41--1--EL APELLIDO MATERNO DE LA ABUELA PATERNA--37--M--X","42--1--LA NACIONALIDAD DE LA ABUELA PATERNA--38--M--N","43--1--EL NOMBRE COMPLETO DEL ABUELO MATERNO--39--M--C",
//                            "44--1--EL(LOS) NOMBRE(S) PROPIO(S) DEL ABUELO MATERNO--40--M--X","45--1--EL APELLIDO PATERNO DEL ABUELO MATERNO--41--M--X",
//                            "46--1--EL APELLIDO MATERNO DEL ABUELO MATERNO--42--M--X","47--1--LA NACIONALIDAD DEL ABUELO MATERNO--43--M--N","48--1--EL NOMBRE DE LA ABUELA MATERNA--44--M--C",
//                            "49--1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA ABUELA MATERNA--45--M--X","50--1--EL APELLIDO PATERNO DE LA ABUELA MATERNA--46--M--X",
//                            "51--1--EL APELLIDO MATERNO DE LA ABUELA MATERNA--47--M--X","52--1--LA NACIONALIDAD DE LA ABUELA MATERNA--48--M--N","53--1--EL NOMBRE DEL 1er. TESTIGO--49--M--X",
//                            "54--1--LA NACIONALIDAD DEL 1er. TESTIGO--50--M--N","55--1--EL NOMBRE DEL 2o. TESTIGO--51--M--X","56--1--LA NACIONALIDAD DEL 2o. TESTIGO--52--M--N",
//                            "57--1--EL NOMBRE DE LA PERSONA DISTINTA--53--M--X","58--1--EL PARENTESCO DE LA PERSONA DISTINTA--54--M--X","59--2--LA CURP--1--M--X",
//                            "60--2--LA OFICIALIA DE REGISTRO--2--M--X","61--2--EL NUMERO DE ACTA--3--M--X","62--2--EL LUGAR DE REGISTRO DEL RECONOCIDO--4--M--L","63--2--LA FECHA DE REGISTRO--5--M--F",
//                            "64--2--EL SEXO DEL RECONOCIDO--6--M--X","65--2--EL NOMBRE DEL RECONOCIDO--7--M--C","66--2--EL(LOS) NOMBRE(S) PROPIO(S) DEL RECONOCIDO--8--M--X",
//                            "67--2--EL APELLIDO PATERNO DEL RECONOCIDO--9--M--X","68--2--EL APELLIDO MATERNO DEL RECONOCIDO--10--M--X","69--2--LA FECHA DE NACIMIENTO DEL RECONOCIDO--11--M--F",
//                            "70--2--LA EDAD DEL RECONOCIDO--12--M--X","71--2--EL LUGAR DE NACIMIENTO DEL RECONOCEDOR--13--M--L","72--2--EL NOMBRE DEL RECONOCEDOR--14--M--C",
//                            "73--2--EL (LOS) NOMBRE(S) PROPIO(S) DEL RECONOCEDOR--15--M--X","74--2--EL APELLIDO PATERNO DEL RECONOCEDOR--16--M--X","75--2--EL APELLIDO MATERNO DEL RECONOCEDOR--17--M--X",
//                            "76--2--LA NACIONALIDAD DEL RECONOCEDOR--18--M--N","77--2--LA EDAD DEL RECONOCEDOR--19--M--X","78--2--EL NOMBRE DEL PADRE DEL RECONOCEDOR--20--M--C",
//                            "79--2--LA NACIONALIDAD DEL PADRE DEL RECONOCEDOR--21--M--N","80--2--EL NOMBRE DE LA MADRE DEL RECONOCEDOR--22--M--C",
//                            "81--2--LA NACIONALIDAD DE LA MADRE DEL RECONOCEDOR--23--M--N","82--2--EL NOMBRE 1a. PERSONA QUE DA SU CONSENTIMIENTO--24--M--X",
//                            "83--2--LA NACIONALIDAD 1a. PERSONA QUE DA SU CONSENTIMIENTO--25--M--N","84--2--LA EDAD 1a. PERSONA QUE DA SU CONSENTIMIENTO--26--M--X",
//                            "85--2--EL PARENTESCO 1a. PERSONA QUE DA SU CONSENTIMIENTO--27--M--X","86--2--EL NOMBRE 2a. PERSONA QUE DA SU CONSENTIMIENTO--28--M--X",
//                            "87--2--LA NACIONALIDAD 2a. PERSONA QUE DA SU CONSENTIMIENTO--29--M--N","88--2--LA EDAD 2a. PERSONA QUE DA SU CONSENTIMIENTO--30--M--X",
//                            "89--2--EL PARENTESCO 2a. PERSONA QUE DA SU CONSENTIMIENTO--31--M--X","90--2--EL NOMBRE DEL 1er. TESTIGO DEL RECONOCIDO--32--M--X",
//                            "91--2--EL NOMBRE DEL 2o. TESTIGO DEL RECONOCIDO--33--M--X","92--3--LA CURP--1--M--X","93--3--LA OFICIALIA DE REGISTRO--2--M--X",
//                            "94--3--EL NUMERO DE ACTA--3--M--X","95--3--EL LUGAR DE REGISTRO DEL ADOPTADO--4--M--L","96--3--LA FECHA DE REGISTRO--5--M--F",
//                            "97--3--EL SEXO--6--M--X","98--3--EL NOMBRE DEL ADOPTADO--7--M--C","99--3--EL(LOS) NOMBRE(S) PROPIO(S) DEL ADOPTADO--8--M--X","100--3--EL APELLIDO PATERNO DEL ADOPTADO--9--M--X",
//                            "101--3--EL APELLIDO MATERNO DEL ADOPTADO--10--M--X","102--3--LA EDAD DEL ADOPTADO--11--M--X","103--3--EL ESTADO CIVIL DEL ADOPTADO--12--M--X",
//                            "104--3--LA NACIONALIDAD DEL ADOPTADO--13--M--N","105--3--EL NOMBRE DEL ADOPTANTE--14--M--C","106--3--EL(LOS) NOMBRE(S) PROPIO(S) DEL ADOPTANTE--15--M--X",
//                            "107--3--EL APELLIDO PATERNO DEL ADOPTANTE--16--M--X","108--3--EL APELLIDO MATERNO DEL ADOPTANTE--17--M--X","109--3--EL NOMBRE DE LA ADOPTANTE--18--M--C",
//                            "110--3--EL(LOS) NOMBRE(S) PROPIO(S) DE LA ADOPTANTE--18--M--X","111--3--EL APELLIDO PATERNO DE LA ADOPTANTE--19--M--X","112--3--EL APELLIDO MATERNO DE LA ADOPTANTE--20--M--X",
//                            "113--4--LA CURP DE EL--1--M--X","114--4--LA CURP DE ELLA--2--M--X","115--4--LA OFICIALIA DE REGISTRO--3--M--X","116--4--EL NUMERO DE ACTA--4--M--X",
//                            "117--4--EL LUGAR DE REGISTRO--5--M--L","118--4--LA FECHA DE REGISTRO--6--M--F","119--4--EL NOMBRE DEL CONTRAYENTE--7--M--C",
//                            "120--4--EL(LOS) NOMBRE(S) PROPIO(S) DEL CONTRAYENTE--8--M--X","121--4--EL APELLIDO PATERNO DEL CONTRAYENTE--9--M--X",
//                            "122--4--EL APELLIDO MATERNO DEL CONTRAYENTE--10--M--X","123--4--LA EDAD DEL CONTRAYENTE--11--M--X","124--4--LA NACIONALIDAD DEL CONTRAYENTE--12--M--N",
//                            "125--4--LA OCUPACION DEL CONTRAYENTE--13--M--X","126--4--EL NOMBRE DE LA CONTRAYENTE--14--M--C","127--4--EL(LOS) NOMBRE(S) PROPIO(S) DE LA CONTRAYENTE--15--M--X",
//                            "128--4--EL APELLIDO PATERNO DE LA CONTRAYENTE--16--M--X","129--4--EL APELLIDO MATERNO DE LA CONTRAYENTE--17--M--X","130--4--LA EDAD DE LA CONTRAYENTE--18--M--X",
//                            "131--4--LA NACIONALIDAD DE LA CONTRAYENTE--19--M--N","132--4--LA OCUPACION DE LA CONTRAYENTE--20--M--X","133--4--EL NOMBRE DEL PADRE DEL CONTRAYENTE--21--M--C",
//                            "134--4--EL(LOS) NOMBRE(S) PROPIO(S) DEL PADRE DEL CONTRAYENTE--22--M--X","135--4--EL APELLIDO PATERNO DEL PADRE DEL CONTRAYENTE--23--M--X",
//                            "136--4--EL APELLIDO MATERNO DEL PADRE DEL CONTRAYENTE--24--M--X","137--4--LA NACIONALIDAD DEL PADRE DEL CONTRAYENTE--25--M--N",
//                            "138--4--EL NOMBRE DE LA MADRE DEL CONTRAYENTE--26--M--C","139--4--EL(LOS) NOMBRE(S) PROPIO(S) DE LA MADRE DEL CONTRAYENTE--27--M--X",
//                            "140--4--EL APELLIDO PATERNO DE LA MADRE DEL CONTRAYENTE--28--M--X","141--4--EL APELLIDO MATERNO DE LA MADRE DEL CONTRAYENTE--29--M--X",
//                            "142--4--LA NACIONALIDAD DE LA MADRE DEL CONTRAYENTE--30--M--N","143--4--EL NOMBRE DEL PADRE DE LA CONTRAYENTE--31--M--C",
//                            "144--4--EL(LOS) NOMBRE(S) PROPIO(S) DEL PADRE DE LA CONTRAYENTE--32--M--X","145--4--EL APELLIDO PATERNO DEL PADRE DE LA CONTRAYENTE--33--M--X",
//                            "146--4--EL APELLIDO MATERNO DEL PADRE DE LA CONTRAYENTE--34--M--X","147--4--LA NACIONALIDAD DEL PADRE DE LA CONTRAYENTE--35--M--N",
//                            "148--4--EL NOMBRE DE LA MADRE DE LA CONTRAYENTE--36--M--C","149--4--EL(LOS) NOMBRE(S) PROPIO(S) DE LA MADRE DE LA CONTRAYE--37--M--X",
//                            "150--4--EL APELLIDO PATERNO DE LA MADRE DE LA CONTRAYENTE--38--M--X","151--4--EL APELLIDO MATERNO DE LA MADRE DE LA CONTRAYENTE--39--M--X",
//                            "152--4--LA NACIONALIDAD DE LA MADRE DE LA CONTRAYENTE--40--M--N","153--4--EL NOMBRE DEL 1er. TESTIGO DE LOS CONTRAYENTES--41--M--X",
//                            "154--4--LA NACIONALIDAD DEL 1er. TESTIGO DE LOS CONTRAYENTES--42--M--N","155--4--EL PARENTESCO DEL 1er. TESTIGO DE LOS CONTRAYENTES--43--M--X",
//                            "156--4--EL NOMBRE DEL 2o. TESTIGO DE LOS CONTRAYENTES--44--M--X","157--4--LA NACIONALIDAD DEL 2o. TESTIGO DE LOS CONTRAYENTES--45--M--N",
//                            "158--4--EL PARENTESCO DEL 2o. TESTIGO DE LOS CONTRAYENTES--46--M--X","159--4--EL NOMBRE DEL 3er. TESTIGO DE LOS CONTRAYENTES--47--M--X",
//                            "160--4--LA NACIONALIDAD DEL 3er. TESTIGO DE LOS CONTRAYENTES--48--M--N","161--4--EL PARENTESCO DEL 3er. TESTIGO DE LOS CONTRAYENTES--49--M--X",
//                            "162--4--EL NOMBRE DEL 4o. TESTIGO DE LOS CONTRAYENTES--50--M--X","163--4--LA NACIONALIDAD DEL 4o. TESTIGO DE LOS CONTRAYENTES--51--M--N",
//                            "164--4--EL PARENTESCO DEL 4o. TESTIGO DE LOS CONTRAYENTES--52--M--X","165--4--LOS NOMBRES DE LAS PERSONAS QUE DAN SU CONSENTIMIENTO--53--M--X",
//                            "166--4--LA AUTORIZACION DE LA SRIA. DE GOB. SI SON EXTRANJEROS--54--M--X","167--4--EL REGIMEN DE CONTRATO DE MATRIMONIO--55--M--X","168--5--LA CURP DE EL--1--M--X",
//                            "169--5--LA CURP DE ELLA--2--M--X","170--5--LA OFICIALIA DE REGISTRO--3--M--X","171--5--EL NUMERO DE ACTA--4--M--X","172--5--EL LUGAR DE REGISTRO--5--M--L",
//                            "173--5--LA FECHA DE REGISTRO--6--M--F","174--5--EL NOMBRE DEL DIVORCIADO--7--M--C","175--5--EL(LOS) NOMBRE(S) PROPIO(S) DEL DIVORCIADO--8--M--X",
//                            "176--5--EL APELLIDO PATERNO DEL DIVORCIADO--9--M--X","177--5--EL APELLIDO MATERNO DEL DIVORCIADO--10--M--X","178--5--LA NACIONALIDAD DEL DIVORCIADO--11--M--N",
//                            "179--5--LA EDAD DEL DIVORCIADO--12--M--X","180--5--EL LUGAR DE NACIMIENTO DEL DIVORCIADO--13--M--L","181--5--LA OCUPACION DEL DIVORCIADO--14--M--X",
//                            "182--5--EL NOMBRE DE LA DIVORCIADA--15--M--C","183--5--EL(LOS) NOMBRE(S) PROPIO(S) DE LA DIVORCIADA--16--M--X","184--5--EL APELLIDO PATERNO DE LA DIVORCIADA--17--M--X",
//                            "185--5--EL APELLIDO MATERNO DE LA DIVORCIADA--18--M--X","186--5--LA NACIONALIDAD DE LA DIVORCIADA--19--M--N","187--5--LA EDAD DE LA DIVORCIADA--20--M--X",
//                            "188--5--EL LUGAR DE NACIMIENTO DE LA DIVORCIADA--21--M--L","189--5--LA OCUPACION DE LA DIVORCIADA--22--M--X","190--5--LA FECHA DE REGISTRO DEL ACTA DE MATRIMONIO--23--M--F",
//                            "191--5--LA OFICIALIA DE REGISTRO DEL ACTA DE MATRIMONIO--24--M--X","192--5--EL NUMERO DE LIBRO DEL ACTA DE MATRIMONIO--25--M--X",
//                            "193--5--EL NUMERO DE ACTA DE MATRIMONIO--26--M--X","194--5--EL LUGAR DE REGISTRO DEL ACTA DE MATRIMONIO--27--M--L","195--6--LA CURP--1--M--X",
//                            "196--6--LA OFICIALIA DE REGISTRO--2--M--X","197--6--EL NUMERO DE ACTA--3--M--X","198--6--EL LUGAR DE REGISTRO--4--M--L","199--6--LA FECHA DE REGISTRO--5--M--F",
//                            "200--6--EL SEXO--6--M--X","201--6--EL NOMBRE DEL FINADO--7--M--C","202--6--EL(LOS) NOMBRE(S) PROPIO(S) DEL FINADO--8--M--X","203--6--EL APELLIDO PATERNO DEL FINADO--9--M--X",
//                            "204--6--EL APELLIDO MATERNO DEL FINADO--10--M--X","205--6--EL ESTADO CIVIL DEL FINADO--11--M--X","206--6--LA NACIONALIDAD DEL FINADO--12--M--N",
//                            "207--6--LOS AÑOS DEL FINADO--13--M--X","208--6--LOS MESES DEL FINADO--14--M--X","209--6--LOS DIAS DEL FINADO--15--M--X","210--6--LAS HORAS DEL FINADO--16--M--X",
//                            "211--6--LA FECHA DE NACIMIENTO DEL FINADO--17--M--F","212--6--EL NOMBRE DE LA CONYUGE DEL FINADO--18--M--C","213--6--LA NACIONALIDAD DE LA CONYUGE DEL FINADO--19--M--N",
//                            "214--6--EL NOMBRE DEL PADRE DEL FINADO--20--M--C","215--6--EL(LOS) NOMBRE(S) PROPIO(S) DEL PADRE DEL FINADO--21--M--X",
//                            "216--6--EL APELLIDO PATERNO DEL PADRE DEL FINADO--22--M--X","217--6--EL APELLIDO MATERNO DEL PADRE DEL FINADO--23--M--X",
//                            "218--6--EL NOMBRE DE LA MADRE DEL FINADO--24--M--C","219--6--EL(LOS) NOMBRE(S) PROPIO(S) DE LA MADRE DEL FINADO--25--M--X",
//                            "220--6--EL APELLIDO PATERNO DE LA MADRE DEL FINADO--26--M--X","221--6--EL APELLIDO MATERNO DE LA MADRE DEL FINADO--27--M--X",
//                            "222--6--LA FECHA DE DEFUNCION--28--M--F","223--6--LA HORA DEL FALLECIMIENTO--29--M--X","224--6--EL LUGAR DEL FALLECIMIENTO--30--M--L",
//                            "225--6--EL No. DE CERTIFICADO DE LA DEFUNCION--31--M--X","226--6--EL DESTINO DEL CADAVER--32--M--X","227--6--EL NOMBRE DEL PANTEON O CREMATORIO--33--M--X",
//                            "228--6--EN DONDE FALLECIO--34--M--X","229--6--LA CAUSA DE LA MUERTE--35--M--X","230--6--EL TIPO DE DEFUNCION--36--M--X",
//                            "231--6--EL NOMBRE DEL MEDICO QUE CERTIFICA LA DEFUNCION--37--M--X","232--6--EL No. DE CEDULA PROFESIONAL--38--M--X","233--6--EL NOMBRE DEL DECLARANTE--39--M--X",
//                            "234--6--EL PARENTESCO DEL DECLARANTE--40--M--X","235--6--EL NOMBRE DEL 1er. TESTIGO--41--M--X","236--6--LA NACIONALIDAD DEL 1er. TESTIGO--42--M--N",
//                            "237--6--EL PARENTESCO DEL 1er. TESTIGO--43--M--X","238--6--EL NOMBRE DEL 2o. TESTIGO--44--M--X","239--6--LA NACIONALIDAD DEL 2o. TESTIGO--45--M--N",
//                            "240--6--EL PARENTESCO DEL 2o. TESTIGO--46--M--X","241--7--LA OFICIALIA DE REGISTRO--1--M--X","242--7--EL NUMERO DE ACTA--2--M--X","243--7--EL LUGAR DE REGISTRO--3--M--L",
//                            "244--7--LA FECHA DE REGISTRO--4--M--F","245--1--LA NACIONALIDAD DE LOS ABUELOS MATERNOS Y PATERNOS--55--M--N","246--1--LA NACIONALIDAD DEL 1er. Y 2o. TESTIGO--56--M--N",
//                            "247--1--LA NACIONALIDAD DE LOS ABUELOS PATERNOS--57--M--N","248--1--LA NACIONALIDAD DE LOS ABUELOS MATERNOS--58--M--N",
//                            "249--1--EL ESTADO LEGAL DE HIJO EN EL MOMENTO DEL REGISTRO--59--M--X","250--1--LOS APELLIDOS DEL REGISTRADO--60--M--X",
//                            "251--1--LOS APELLIDOS DEL PROGENITOR--61--M--X","252--1--LOS APELLIDOS DE LA PROGENITORA--62--M--X","253--1--EL PRIMER NOMBRE PROPIO DEL REGISTRADO--63--M--X",
//                            "254--1--EL SEGUNDO NOMBRE PROPIO DEL REGISTRADO--64--M--X","255--1--EL TERCER NOMBRE PROPIO DEL REGISTRADO--65--M--X","256--1--EL PRIMER NOMBRE PROPIO DEL PADRE--66--M--X",
//                            "257--1--EL SEGUNDO NOMBRE PROPIO DEL PADRE--67--M--X","258--1--EL TERCER NOMBRE PROPIO DEL PADRE--68--M--X","259--1--EL PRIMER NOMBRE PROPIO DE LA MADRE--69--M--X",
//                            "260--1--EL SEGUNDO NOMBRE PROPIO DE LA MADRE--70--M--X","261--1--EL TERCER NOMBRE PROPIO DE LA MADRE--71--M--X",
//                            "262--1--NACIONALIDAD DE PADRES, ABUELOS PATERNOS Y MATERNOS--72--M--N","263--1--NACIONALIDAD DE PADRES, AB.PATERNOS, MATERNOS Y TESTIGOS--73--M--N",
//                            "264--4--EL(LOS) NOMBRE(S) PROPIO(S) DEL LEGITIMADO--56--M--X","265--4--EL PRIMER NOMBRE PROPIO DEL LEGITIMADO--57--M--X",
//                            "266--4--EL SEGUNDO NOMBRE PROPIO DEL LEGITIMADO--58--M--X","267--4--LA FECHA DE NACIMIENTO DEL LEGITIMADO--59--M--F","268--1--LA HORA DE NACIMIENTO DEL REGISTRADO--74--M--X",
//                            "269--1--LA HORA DE REGISTRO--75--M--X","270--1--LA NACIONALIDAD DE LOS PROGENITORES--76--M--N","271--1--LA NACIONALIDAD DE LOS PADRES--77--M--N",
//                            "272--4--EL LUGAR DE NACIMIENTO DEL CONTRAYENTE--60--M--L","273--4--LUGAR DE NACIMIENTO DE LA CONTRAYENTE--61--M--L","275--4--LA NACIONALIDAD DE LOS CONTRAYENTES--62--M--N",
//                            "276--4--LA NACIONALIDAD DE LOS TESTIGOS--63--M--N","277--4--LA NACIONALIDAD DE LOS PADRES--64--M--N","278--1--EL NOMBRE DE LA MADRE DEL REGISTRADO--79--M--X",
//                            "279--1--EL NOMBRE DE EL PADRE DEL REGISTRADO--80--M--X","280--6--EL LUGAR DE NACIMIENTO DEL FINADO--47--M--X","281--4--LA EDAD DE LA MADRE DE EL CONTRAYENTE--65--M--N",
//                            "282--4--LA EDAD DE LA MADRE DE LA CONTRAYENTE--66--M--N","283--1--EL NUMERO DE LIBRO DE ACTA DE NACIMIENTO--81--M--X",
//                            "284--2--EL NUMERO DE LIBRO DE ACTA DE RECONOCIMIENTO--34--M--X","285--3--EL NUMERO DE LIBRO DE ACTA DE ADOPCION--21--M--X",
//                            "286--4--EL NUMERO DE LIBRO DE ACTA DE MATRIMONIO--67--M--X","287--5--EL NUMERO DE LIBRO DE DIVORCIO--28--M--X","288--6--EL NUMERO DE LIBRO DE DEFUNCION--48--M--X",
//                            "289--7--EL NUMERO DE LIBRO DE INSCRIPCION DE--5--M--X","290--7--EL NOMBRE(S) PROPIO(S) DE LA REGISTRADO--6--M--X",
//                            "306--7--LA FECHA DE NACIMIENTO DE LA MADRE--22--M--X","320--7--EL LUGAR DE NACIMIENTO DE LA MADRE--21--M--X","322--7--LOS QUE COMPARECIERON--7--M--X",
//                            "323--7--EL PRIMER NOMBRE DEL REGISTRADO--8--M--X","324--7--EL SEGUNDO NOMBRE DEL REGISTRADO --10--M--X","325--7--EL PRIMER APELLIDO DEL REGISTRADO--11--M--X",
//                            "326--7--EL SEGUNDO APELLIDO DEL REGISTRADO--12--M--X","327--7--EL SEXO DEL REGISTRADO--13--M--X","328--7--LA FECHA DE NACIMIENTO DEL REGISTRADO--14--M--X",
//                            "329--7--EL LUGAR DE NACIMIENTO DEL REGISTRADO--15--M--X","330--7--EL NOMBRE DEL PADRE--16--M--X","331--7--EL LUGAR DE NACIMIENTO DEL PADRE--17--M--X",
//                            "332--7--LA FECHA DE NACIMIENTO DEL PADRE--18--M--X","333--7--EL NOMBRE DEL REGISTRADO--19--M--X","334--7--EL NOMBRE DE LA MADRE--20--M--X","335--1--LA CURP--1--F--X",
//                            "336--1--LA CRIP--1--F--X","337--1--LA OFICIALIA DE REGISTRO--2--F--X","338--1--EL NUMERO DE ACTA--3--F--X","339--1--EL LUGAR DE REGISTRO--4--F--L",
//                            "340--1--LA FECHA DE REGISTRO--5--F--F","341--1--EL SEXO--6--F--X","342--1--EL NOMBRE DE LA REGISTRADA--7--F--X","343--1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA REGISTRADA--8--F--X",
//                            "344--1--EL APELLIDO PATERNO DE LA REGISTRADA--9--F--X","345--1--EL APELLIDO MATERNO DE LA REGISTRADA--10--F--X","346--1--LA FECHA DE NACIMIENTO DE LA REGISTRADA--11--F--F",
//                            "347--1--EL LUGAR DE NACIMIENTO DE LA REGISTRADA--12--F--L","348--1--EL ESTADO BIOLOGICO--13--F--X","349--1--LOS QUE COMPARECIERON--14--F--X",
//                            "350--1--EL NOMBRE DEL PADRE DE LA REGISTRADA--15--F--C","351--1--EL(LOS) NOMBRE(S) PROPIO(S) DEL PADRE DE LA REGISTRADA--16--F--X",
//                            "352--1--EL APELLIDO PATERNO DEL PADRE DE LA REGISTRADA--17--F--X","353--1--EL APELLIDO MATERNO DEL PADRE DE LA REGISTRADA--18--F--X",
//                            "354--1--LA EDAD DEL PADRE--19--F--X","355--1--EL ESTADO CIVIL DEL PADRE--20--F--X","356--1--LA NACIONALIDAD DEL PADRE--21--F--N",
//                            "357--1--EL NOMBRE DE LA MADRE DE LA REGISTRADA--22--F--C","358--1--EL(LOS)NOMBRE(S) PROPIO(S) DE LA MADRE DE LA REGISTRADA--23--F--X",
//                            "359--1--EL APELLIDO PATERNO DE LA MADRE DE LA REGISTRADA--24--F--X","360--1--EL APELLIDO MATERNO DE LA MADRE DE LA REGISTRADA--25--F--X",
//                            "361--1--LA EDAD DE LA MADRE--26--F--X","362--1--EL ESTADO CIVIL DE LA MADRE--27--F--X","363--1--LA NACIONALIDAD DE LA MADRE--28--F--N",
//                            "364--1--EL NOMBRE DEL ABUELO PATERNO--29--F--C","365--1--EL(LOS) NOMBRE(S) PROPIO(S) DEL ABUELO PATERNO--30--F--X",
//                            "366--1--EL APELLIDO PATERNO DEL ABUELO PATERNO--31--F--X","367--1--EL APELLIDO MATERNO DEL ABUELO PATERNO--32--F--X",
//                            "368--1--LA NACIONALIDAD DEL ABUELO PATERNO--33--F--N","369--1--EL NOMBRE DE LA ABUELA PATERNA--34--F--C",
//                            "370--1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA ABUELA PATERNA--35--F--X","371--1--EL APELLIDO PATERNO DE LA ABUELA PATERNA--36--F--X",
//                            "372--1--EL APELLIDO MATERNO DE LA ABUELA PATERNA--37--F--X","373--1--LA NACIONALIDAD DE LA ABUELA PATERNA--38--F--N","374--1--EL NOMBRE DEL ABUELO MATERNO--39--F--C",
//                            "375--1--EL(LOS) NOMBRE(S) PROPIO(S) DEL ABUELO MATERNO--40--F--X","376--1--EL APELLIDO PATERNO DEL ABUELO MATERNO--41--F--X",
//                            "377--1--EL APELLIDO MATERNO DEL ABUELO MATERNO--42--F--X","378--1--LA NACIONALIDAD DEL ABUELO MATERNO--43--F--N","379--1--EL NOMBRE DE LA ABUELA MATERNA--44--F--C",
//                            "380--1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA ABUELA MATERNA--45--F--X","381--1--EL APELLIDO PATERNO DE LA ABUELA MATERNA--46--F--X",
//                            "382--1--EL APELLIDO MATERNO DE LA ABUELA MATERNA--47--F--X","383--1--LA NACIONALIDAD DE LA ABUELA MATERNA--48--F--N","384--1--EL NOMBRE DEL 1er. TESTIGO--49--F--X",
//                            "385--1--LA NACIONALIDAD DEL 1er. TESTIGO--50--F--N","386--1--EL NOMBRE DEL 2o. TESTIGO--51--F--X",
//                            "387--1--LA NACIONALIDAD DEL 2o. TESTIGO--52--F--N","388--1--EL NOMBRE DE LA PERSONA DISTINTA--53--F--X",
//                            "389--1--EL PARENTESCO DE LA PERSONA DISTINTA--54--F--X","390--2--LA CURP--1--F--X","391--2--LA OFICIALIA DE REGISTRO--2--F--X",
//                            "392--2--EL NUMERO DE ACTA--3--F--X","393--2--EL LUGAR DE REGISTRO DE LA RECONOCIDA--4--F--L","394--2--LA FECHA DE REGISTRO--5--F--F",
//                            "395--2--EL SEXO DE LA RECONOCIDA--6--F--X","396--2--EL NOMBRE DE LA RECONOCIDA--7--F--C","397--2--EL(LOS) NOMBRE(S) PROPIO(S) DE LA RECONOCIDA--8--F--X",
//                            "398--2--EL APELLIDO PATERNO DE LA RECONOCIDA--9--F--X","399--2--EL APELLIDO MATERNO DE LA RECONOCIDA--10--F--X","400--2--LA FECHA DE NACIMIENTO DE LA RECONOCIDA--11--F--F",
//                            "401--2--LA EDAD DE LA RECONOCIDA--12--F--X","402--2--EL LUGAR DE NACIMIENTO DEL RECONOCEDOR--13--F--L","403--2--EL NOMBRE DEL RECONOCEDOR--14--F--C",
//                            "404--2--EL(LOS) NOMBRE(S) PROPIO(S) DEL RECONOCEDOR--15--F--X","405--2--EL APELLIDO PATERNO DEL RECONOCEDOR--16--F--X","406--2--EL APELLIDO MATERNO DEL RECONOCEDOR--17--F--X",
//                            "407--2--LA NACIONALIDAD DEL RECONOCEDOR--18--F--N","408--2--LA EDAD DEL RECONOCEDOR--19--F--X","409--2--EL NOMBRE DEL PADRE DEL RECONOCEDOR--20--F--C",
//                            "410--2--LA NACIONALIDAD DEL PADRE DEL RECONOCEDOR--21--F--N","411--2--EL NOMBRE DE LA MADRE DEL RECONOCEDOR--22--F--C",
//                            "412--2--LA NACIONALIDAD DE LA MADRE DEL RECONOCEDOR--23--F--N","413--2--EL NOMBRE 1a. PERSONA QUE DA SU CONSENTIMIENTO--24--F--X",
//                            "414--2--LA NACIONALIDAD 1a. PERSONA QUE DA SU CONSENTIMIENTO--25--F--N","415--2--LA EDAD 1a. PERSONA QUE DA SU CONSENTIMIENTO--26--F--X",
//                            "416--2--EL PARENTESCO 1a. PERSONA QUE DA SU CONSENTIMIENTO--27--F--X","417--2--EL NOMBRE 2a. PERSONA QUE DA SU CONSENTIMIENTO--28--F--X",
//                            "418--2--LA NACIONALIDAD 2a. PERSONA QUE DA SU CONSENTIMIENTO--29--F--N","419--2--LA EDAD 2a. PERSONA QUE DA SU CONSENTIMIENTO--30--F--X",
//                            "420--2--EL PARENTESCO 2a. PERSONA QUE DA SU CONSENTIMIENTO--31--F--X","421--2--EL NOMBRE DEL 1er. TESTIGO DE LA RECONOCIDA--32--F--X",
//                            "422--2--EL NOMBRE DEL 2o. TESTIGO DE LA RECONOCIDA--33--F--X","423--3--LA CURP--1--F--X","424--3--LA OFICIALIA DE REGISTRO--2--F--X","425--3--EL NUMERO DE ACTA--3--F--X",
//                            "426--3--EL LUGAR DE REGISTRO DE LA ADOPTADA--4--F--L","427--3--LA FECHA DE REGISTRO--5--F--F","428--3--EL SEXO--6--F--X","429--3--EL NOMBRE DE LA ADOPTADA--7--F--C",
//                            "430--3--EL(LOS) NOMBRE(S) PROPIO(S) DE LA ADOPTADA--8--F--X","431--3--EL APELLIDO PATERNO DE LA ADOPTADA--9--F--X","432--3--EL APELLIDO MATERNO DE LA ADOPTADA--10--F--X",
//                            "433--3--LA EDAD DE LA ADOPTADA--11--F--X","434--3--EL ESTADO CIVIL DE LA ADOPTADA--12--F--X","435--3--LA NACIONALIDAD DE LA ADOPTADA--13--F--N",
//                            "436--3--EL NOMBRE DEL ADOPTANTE--14--F--C","437--3--EL(LOS) NOMBRE(S) PROPIO(S) DEL ADOPTANTE--15--F--X","438--3--EL APELLIDO PATERNO DEL ADOPTANTE--16--F--X",
//                            "439--3--EL APELLIDO MATERNO DEL ADOPTANTE--17--F--X","440--3--EL NOMBRE DE LA ADOPTANTE--18--F--C","441--3--EL(LOS) NOMBRE(S) PROPIO(S) DE LA ADOPTANTE--18--F--X",
//                            "442--3--EL APELLIDO PATERNO DE LA ADOPTANTE--19--F--X","443--3--EL APELLIDO MATERNO DE LA ADOPTANTE--20--F--X","444--4--LA CURP DE EL--1--F--X","445--4--LA CURP DE ELLA--2--F--X",
//                            "446--4--LA OFICIALIA DE REGISTRO--3--F--X","447--4--EL NUMERO DE ACTA--4--F--X","448--4--EL LUGAR DE REGISTRO--5--F--L","449--4--LA FECHA DE REGISTRO--6--F--F",
//                            "450--4--EL NOMBRE DEL CONTRAYENTE--7--F--C","451--4--EL(LOS) NOMBRE(S) PROPIO(S) DEL CONTRAYENTE--8--F--X","452--4--EL APELLIDO PATERNO DEL CONTRAYENTE--9--F--X",
//                            "453--4--EL APELLIDO MATERNO DEL CONTRAYENTE--10--F--X","454--4--LA EDAD DEL CONTRAYENTE--11--F--X","455--4--LA NACIONALIDAD DEL CONTRAYENTE--12--F--N",
//                            "456--4--LA OCUPACION DEL CONTRAYENTE--13--F--X","457--4--EL NOMBRE DE LA CONTRAYENTE--14--F--C","458--4--EL(LOS) NOMBRE(S) PROPIO(S) DE LA CONTRAYENTE--15--F--X",
//                            "459--4--EL APELLIDO PATERNO DE LA CONTRAYENTE--16--F--X","460--4--EL APELLIDO MATERNO DE LA CONTRAYENTE--17--F--X","461--4--LA EDAD DE LA CONTRAYENTE--18--F--X",
//                            "462--4--LA NACIONALIDAD DE LA CONTRAYENTE--19--F--N","463--4--LA OCUPACION DE LA CONTRAYENTE--20--F--X","464--4--EL NOMBRE DEL PADRE DEL CONTRAYENTE--21--F--C",
//                            "465--4--EL(LOS) NOMBRE(S) PROPIO(S) DEL PADRE DEL CONTRAYENTE--22--F--X","466--4--EL APELLIDO PATERNO DEL PADRE DEL CONTRAYENTE--23--F--X",
//                            "467--4--EL APELLIDO MATERNO DEL PADRE DEL CONTRAYENTE--24--F--X","468--4--LA NACIONALIDAD DEL PADRE DEL CONTRAYENTE--25--F--N",
//                            "469--4--EL NOMBRE DE LA MADRE DEL CONTRAYENTE--26--F--C","470--4--EL(LOS) NOMBRE(S) PROPIO(S) DE LA MADRE DEL CONTRAYENTE--27--F--X",
//                            "471--4--EL APELLIDO PATERNO DE LA MADRE DEL CONTRAYENTE--28--F--X","472--4--EL APELLIDO MATERNO DE LA MADRE DEL CONTRAYENTE--29--F--X",
//                            "473--4--LA NACIONALIDAD DE LA MADRE DEL CONTRAYENTE--30--F--N","474--4--EL NOMBRE DEL PADRE DE LA CONTRAYENTE--31--F--C",
//                            "475--4--EL(LOS) NOMBRE(S) PROPIO(S) DEL PADRE DE LA CONTRAYENTE--32--F--X","476--4--EL APELLIDO PATERNO DEL PADRE DE LA CONTRAYENTE--33--F--X",
//                            "477--4--EL APELLIDO MATERNO DEL PADRE DE LA CONTRAYENTE--34--F--X","478--4--LA NACIONALIDAD DEL PADRE DE LA CONTRAYENTE--35--F--N",
//                            "479--4--EL NOMBRE DE LA MADRE DE LA CONTRAYENTE--36--F--C","480--4--EL(LOS) NOMBRE(S) PROPIO(S) DE LA MADRE LA CONTRAYENTE--37--F--X",
//                            "481--4--EL APELLIDO PATERNO DE LA MADRE DE LA CONTRAYENTE--38--F--X","482--4--EL APELLIDO MATERNO DE LA MADRE DE LA CONTRAYENTE--39--F--X",
//                            "483--4--LA NACIONALIDAD DE LA MADRE DE LA CONTRAYENTE--40--F--N","484--4--EL NOMBRE DEL 1er. TESTIGO DE LOS CONTRAYENTES--41--F--X",
//                            "485--4--LA NACIONALIDAD DEL 1er. TESTIGO DE LOS CONTRAYENTES--42--F--N","486--4--EL PARENTESCO DEL 1er. TESTIGO DE LOS CONTRAYENTES--43--F--X",
//                            "487--4--EL NOMBRE DEL 2o. TESTIGO DE LOS CONTRAYENTES--44--F--X","488--4--LA NACIONALIDAD DEL 2o. TESTIGO DE LOS CONTRAYENTES--45--F--N",
//                            "489--4--EL PARENTESCO DEL 2o. TESTIGO DE LOS CONTRAYENTES--46--F--X","490--4--EL NOMBRE DEL 3er. TESTIGO DE LOS CONTRAYENTES--47--F--X",
//                            "491--4--LA NACIONALIDAD DEL 3er. TESTIGO DE LOS CONTRAYENTES--48--F--N","492--4--EL PARENTESCO DEL 3er. TESTIGO DE LOS CONTRAYENTES--49--F--X",
//                            "493--4--EL NOMBRE DEL 4o. TESTIGO DE LOS CONTRAYENTES--50--F--X","494--4--LA NACIONALIDAD DEL 4o. TESTIGO DE LOS CONTRAYENTES--51--F--N",
//                            "495--4--EL PARENTESCO DEL 4o. TESTIGO DE LOS CONTRAYENTES--52--F--X","496--4--LOS NOMBRES DE LAS PERSONAS QUE DAN SU CONSENTIMIENTO--53--F--X",
//                            "497--4--LA AUTORIZACION DE LA SRIA. DE GOB. SI SON EXTRANJEROS--54--F--X","498--4--EL REGIMEN DE CONTRATO DE MATRIMONIO--55--F--X",
//                            "499--5--LA CURP DE EL--1--F--X","500--5--LA CURP DE ELLA--2--F--X","501--5--LA OFICIALIA DE REGISTRO--3--F--X","502--5--EL NUMERO DE ACTA--4--F--X",
//                            "503--5--EL LUGAR DE REGISTRO--5--F--L","504--5--LA FECHA DE REGISTRO--6--F--F","505--5--EL NOMBRE DEL DIVORCIADO--7--F--C",
//                            "506--5--EL(LOS) NOMBRE(S) PROPIO(S) DEL DIVORCIADO--8--F--X","507--5--EL APELLIDO PATERNO DEL DIVORCIADO--9--F--X",
//                            "508--5--EL APELLIDO MATERNO DEL DIVORCIADO--10--F--X","509--5--LA NACIONALIDAD DEL DIVORCIADO--11--F--N","510--5--LA EDAD DEL DIVORCIADO--12--F--X",
//                            "511--5--EL LUGAR DE NACIMIENTO DEL DIVORCIADO--13--F--L","512--5--LA OCUPACION DEL DIVORCIADO--14--F--X","513--5--EL NOMBRE DE LA DIVORCIADA--15--F--C",
//                            "514--5--EL(LOS) NOMBRE(S) PROPIO(S) DE LA DIVORCIADA--16--F--X","515--5--EL APELLIDO PATERNO DE LA DIVORCIADA--17--F--X",
//                            "516--5--EL APELLIDO MATERNO DE LA DIVORCIADA--18--F--X","517--5--LA NACIONALIDAD DE LA DIVORCIADA--19--F--N","518--5--LA EDAD DE LA DIVORCIADA--20--F--X",
//                            "519--5--EL LUGAR DE NACIMIENTO DE LA DIVORCIADA--21--F--L","520--5--LA OCUPACION DE LA DIVORCIADA--22--F--X","521--5--LA FECHA DE REGISTRO DEL ACTA DE MATRIMONIO--23--F--F",
//                            "522--5--LA OFICIALIA DE REGISTRO DEL ACTA DE MATRIMONIO--24--F--X","523--5--EL NUMERO DE LIBRO DEL ACTA DE MATRIMONIO--25--F--X",
//                            "524--5--EL NUMERO DE ACTA DE MATRIMONIO--26--F--X","525--5--EL LUGAR DE REGISTRO DEL ACTA DE MATRIMONIO--27--F--L","526--6--LA CURP--1--F--X",
//                            "527--6--LA OFICIALIA DE REGISTRO--2--F--X","528--6--EL NUMERO DE ACTA--3--F--X","529--6--EL LUGAR DE REGISTRO--4--F--L","530--6--LA FECHA DE REGISTRO--5--F--F",
//                            "531--6--EL SEXO--6--F--X","532--6--EL NOMBRE DE LA FINADA--7--F--C","533--6--EL(LOS) NOMBRE(S) PROPIO(S) DE LA FINADA--8--F--X","534--6--EL APELLIDO PATERNO DE LA FINADA--9--F--X",
//                            "535--6--EL APELLIDO MATERNO DE LA FINADA--10--F--X","536--6--EL ESTADO CIVIL DE LA FINADA--11--F--X","537--6--LA NACIONALIDAD DE LA FINADA--12--F--N",
//                            "538--6--LOS AÑOS DE LA FINADA--13--F--X","539--6--LOS MESES DE LA FINADA--14--F--X","540--6--LOS DIAS DE LA FINADA--15--F--X","541--6--LAS HORAS DE LA FINADA--16--F--X",
//                            "542--6--LA FECHA DE NACIMIENTO DE LA FINADA--17--F--F","543--6--EL NOMBRE DEL CONYUGE DE LA FINADA--18--F--C","544--6--LA NACIONALIDAD DEL CONYUGE DE LA FINADA--19--F--N",
//                            "545--6--EL NOMBRE DEL PADRE DE LA FINADA--20--F--C","546--6--EL(LOS) NOMBRE(S) PROPIO(S) DEL PADRE DE LA FINADA--21--F--X",
//                            "547--6--EL APELLIDO PATERNO DEL PADRE DE LA FINADA--22--F--X","548--6--EL APELLIDO MATERNO DEL PADRE DE LA FINADA--23--F--X",
//                            "549--6--EL NOMBRE DE LA MADRE DE LA FINADA--24--F--C","550--6--EL(LOS) NOMBRE(S) PROPIO(S) DE LA MADRE DE LA FINADA--25--F--X",
//                            "551--6--EL APELLIDO PATERNO DE LA MADRE DE LA FINADA--26--F--X","552--6--EL APELLIDO MATERNO DE LA MADRE DE LA FINADA--27--F--X",
//                            "553--6--LA FECHA DE DEFUNCION--28--F--F","554--6--LA HORA DEL FALLECIMIENTO--29--F--X","555--6--EL LUGAR DEL FALLECIMIENTO--30--F--L",
//                            "556--6--EL No. DE CERTIFICADO DE LA DEFUNCION--31--F--X","557--6--EL DESTINO DEL CADAVER--32--F--X","558--6--EL NOMBRE DEL PANTEON O CREMATORIO--33--F--X",
//                            "559--6--EN DONDE FALLECIO--34--F--X","560--6--LA CAUSA DE LA MUERTE--35--F--X","561--6--EL TIPO DE DEFUNCION--36--F--X",
//                            "562--6--EL NOMBRE DEL MEDICO QUE CERTIFICA LA DEFUNCION--37--F--X","563--6--EL No. DE CEDULA PROFESIONAL--38--F--X","564--6--EL NOMBRE DEL DECLARANTE--39--F--X",
//                            "565--6--EL PARENTESCO DEL DECLARANTE--40--F--X","566--6--EL NOMBRE DEL 1er. TESTIGO--41--F--X","567--6--LA NACIONALIDAD DEL 1er. TESTIGO--42--F--N",
//                            "568--6--EL PARENTESCO DEL 1er. TESTIGO--43--F--X","569--6--EL NOMBRE DEL 2o. TESTIGO--44--F--X","570--6--LA NACIONALIDAD DEL 2o. TESTIGO--45--F--N",
//                            "571--6--EL PARENTESCO DEL 2o. TESTIGO--46--F--X","572--7--LA OFICIALIA DE REGISTRO--1--F--X","573--7--EL NUMERO DE ACTA--2--F--X","574--7--EL LUGAR DE REGISTRO--3--F--L",
//                            "575--7--LA FECHA DE REGISTRO--4--F--F","576--1--LA NACIONALIDAD DE LOS ABUELOS MATERNOS Y PATERNOS--55--F--N","577--1--LA NACIONALIDAD DEL 1er. Y 2o. TESTIGO--56--F--N",
//                            "578--1--LA NACIONALIDAD DE LOS ABUELOS PATERNOS--57--F--N","579--1--LA NACIONALIDAD DE LOS ABUELOS MATERNOS--58--F--N",
//                            "580--1--EL ESTADO LEGAL DE HIJO EN EL MOMENTO DEL REGISTRO--59--F--X","581--1--LOS APELLIDOS DE LA REGISTRADA--60--F--X","582--1--LOS APELLIDOS DEL PROGENITOR--61--F--X",
//                            "583--1--LOS APELLIDOS DE LA PROGENITORA--62--F--X","584--1--EL PRIMER NOMBRE PROPIO DE LA REGISTRADA--63--F--X","585--1--EL SEGUNDO NOMBRE PROPIO DE LA REGISTRADA--64--F--X",
//                            "586--1--EL TERCER NOMBRE PROPIO DE LA REGISTRADA--65--F--X","587--1--EL PRIMER NOMBRE PROPIO DEL PADRE--66--F--X","588--1--EL SEGUNDO NOMBRE PROPIO DEL PADRE--67--F--X",
//                            "589--1--EL TERCER NOMBRE PROPIO DEL PADRE--68--F--X","590--1--EL PRIMER NOMBRE PROPIO DE LA MADRE--69--F--X","591--1--EL SEGUNDO NOMBRE PROPIO DE LA MADRE--70--F--X",
//                            "592--1--EL TERCER NOMBRE PROPIO DE LA MADRE--71--F--X","593--1--NACIONALIDAD DE PADRES, ABUELOS PATERNOS Y MATERNOS--72--F--N",
//                            "594--1--NACIONALIDAD DE PADRES, AB.PATERNOS, MATERNOS Y TESTIGOS--73--F--N","595--4--EL(LOS) NOMBRE(S) PROPIO(S) DEL LEGITIMADO--56--F--X",
//                            "596--4--EL PRIMER NOMBRE PROPIO DEL LEGITIMADO--57--F--X","597--4--EL SEGUNDO NOMBRE PROPIO DEL LEGITIMADO--58--F--X",
//                            "598--4--LA FECHA DE NACIMIENTO DEL LEGITIMADO--59--F--F","599--1--LA HORA DE NACIMIENTO DE LA REGISTRADA--74--F--X",
//                            "600--1--LA HORA DE REGISTRO--75--F--X","601--1--LA NACIONALIDAD DE LOS PROGENITORES--76--F--N","602--1--LA NACIONALIDAD DE LOS PADRES--77--F--N",
//                            "603--4--EL LUGAR DE NACIMIENTO DEL CONTRAYENTE--60--F--L","604--4--LUGAR DE NACIMIENTO DE LA CONTRAYENTE--61--F--L","606--4--LA NACIONALIDAD DE LOS CONTRAYENTES--62--F--N",
//                            "607--4--LA NACIONALIDAD DE LOS TESTIGOS--63--F--N","608--4--LA NACIONALIDAD DE LOS PADRES--64--F--N","611--6--EL LUGAR DE NACIMIENTO DEL FINADO--47--F--X",
//                            "612--4--LA EDAD DE LA MADRE DE EL CONTRAYENTE--65--F--N","613--4--LA EDAD DE LA MADRE DE LA CONTRAYENTE--66--F--N",
//                            "614--1--EL NUMERO DE LIBRO DE ACTA DE NACIMIENTO--81--F--X","615--2--EL NUMERO DE LIBRO DE ACTA DE RECONOCIMIENTO--34--F--X",
//                            "616--3--EL NUMERO DE LIBRO DE ACTA DE ADOPCION--21--F--X","617--4--EL NUMERO DE LIBRO DE ACTA DE MATRIMONIO--67--F--X",
//                            "618--5--EL NUMERO DE LIBRO DE ACTA DE DIVORCIO--28--F--X","619--6--EL NUMERO DE LIBRO DE ACTA DE DEFUNCION--48--F--X",
//                            "620--7--EL NUMERO DE LIBRO DE ACTA DE INSCRIPCION DE--5--F--X","621--7--EL NOMBRE(S) PROPIO(S) DE LA REGISTRADA--6--F--X","622--7--LOS QUE COMPARECIERON--7--F--X",
//                            "623--7--EL PRIMER NOMBRE DE LA REGISTRADA--8--F--X","624--7--EL SEGUNDO NOMBRE DE LA REGISTRADA --10--F--X",
//                            "625--7--EL PRIMER APELLIDO DE LA REGISTRADA--11--F--X","626--7--EL SEGUNDO APELLIDO DE LA REGISTRADA--12--F--X","627--7--EL SEXO DE LA REGISTRADA--13--F--X",
//                            "628--7--LA FECHA DE NACIMIENTO DE LA REGISTRADA--14--F--X","629--7--EL LUGAR DE NACIMIENTO DE LA REGISTRADA--15--F--X","630--7--EL NOMBRE DEL PADRE--16--F--X",
//                            "631--7--EL LUGAR DE NACIMIENTO DEL PADRE--17--F--X","632--7--LA FECHA DE NACIMIENTO DEL PADRE--18--F--X","633--7--EL NOMBRE DE LA REGISTRADA--19--F--X",
//                            "634--7--EL NOMBRE DE LA MADRE--20--F--X","635--7--EL LUGAR DE NACIMIENTO DE LA MADRE--21--F--X","636--7--LA FECHA DE NACIMIENTO DE LA MADRE--22--F--X",
//                            "637--1--EL PRIMER APELLIDO DEL REGISTRADO--1--M--U","638--1--EL SEGUNDO APELLIDO DEL REGISTRADO--2--M--U"]
//                for (def uno: array){
//                    def dos=uno.split("--")
//                    def tipoActaInstance= Tipoactas.findById(dos[1])
//                    def fieldsInstance = new Fields(num:dos[3],nombre:dos[2],tipo:dos[5], sexo:dos[4],acta:tipoActaInstance)
//                    fieldsInstance.save(flush: true, failOnError: true)
//                }
//            }
//            if(!Base.count()){
//                def array=["1--LA CRIP--M--LA ETIQUETA ADHERIBLE DE LA MISMA QUE APARECE EN EL CASILLERO PARTE SUPERIOR A ESTA",
//                            "1--LA CRIP--F--LA ETIQUETA ADHERIBLE DE LA MISMA QUE APARECE EN EL CASILLERO PARTE SUPERIOR A ESTA",
//                            "1--LA CRIP--M--SU ESCRITURA EN EL REGISTRO DE LA OFICIALIA",
//                            "1--LA CRIP--F--SU ESCRITURA EN EL REGISTRO DE LA OFICIALIA",
//                            "1--LA OFICIALIA DE REGISTRO--M--EL SELLO QUE APARECE AL MARGEN  DEL ACTA",
//                            "1--LA OFICIALIA DE REGISTRO--F--EL SELLO QUE APARECE AL MARGEN  DEL ACTA",
//                            "1--EL NUMERO DE ACTA--M--EL REGISTRO ANTERIOR Y POSTERIOR DEL CITADO REGISTRO",
//                            "1--EL NUMERO DE ACTA--F--EL REGISTRO ANTERIOR Y POSTERIOR DEL CITADO REGISTRO",
//                            "1--EL NUMERO DE ACTA--M--EL EJEMPLAR DE OFICIALIA DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NUMERO DE ACTA--F--EL EJEMPLAR DE OFICIALIA DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NUMERO DE ACTA--M--EL EJEMPLAR DE ARCHIVO CENTRAL DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NUMERO DE ACTA--F--EL EJEMPLAR DE ARCHIVO CENTRAL DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL LUGAR DE REGISTRO--M--EL DECRTO QUE FIJA LA DIVISIÓN TERRITORIAL DEL ESTADO DE OAXACA ACTUALIZADA",
//                            "1--EL LUGAR DE REGISTRO--F--LA LEY TERRITORIAL QUE FIJA LAS DENOMINACIONES Y CATEGORIAS DE LOS PUEBLOS QUE INTEGRAN LA ENTIDAD",
//                            "1--EL SEXO--M--EL NOMBRE PROPIO DEL REGISTRADO",
//                            "1--EL SEXO--F--EL NOMBRE PROPIO DE LA REGISTRADA",
//                            "1--EL SEXO--M--EL ESTADO BIOLOGICO EXPRESADO EN EL REGISTRO",
//                            "1--EL SEXO--F--EL ESTADO BIOLOGICO EXPRESADO EN EL REGISTRO",
//                            "1--EL SEXO--M--EL DATO QUE APARECE AL MARGEN DEL ACTA",
//                            "1--EL SEXO--F--LA BOLETA DE REGISTRO",
//                            "1--EL SEXO--M--LA FOTOCOPIA PRESENTADA DE LA OFICIALIA",
//                            "1--EL SEXO--F--LA FOTOCOPIA PRESENTADA DEL ARCHIVO CENTRAL",
//                            "1--LA FECHA DE REGISTRO--M--EL REGISTRO ANTERIOR Y POSTERIOR DEL CITADO REGISTRO",
//                            "1--LA FECHA DE REGISTRO--F--EL REGISTRO ANTERIOR Y POSTERIOR DEL CITADO REGISTRO",
//                            "1--LA FECHA DE REGISTRO--M--EL EJEMPLAR DE OFICIALIA DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--LA FECHA DE REGISTRO--F--EL EJEMPLAR DE OFICIALIA DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--LA FECHA DE REGISTRO--M--EL EJEMPLAR DE ARCHIVO CENTRAL DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--LA FECHA DE REGISTRO--F--EL EJEMPLAR DE ARCHIVO CENTRAL DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--LA FECHA DE REGISTRO--M--LA FECHA DE REGISTRO DE NACIMIENTO EXISTIENDO RELACION CRONOLOGICA ENTRE AMBAS FECHAS ES PROCEDENTE",
//                            "1--LA FECHA DE REGISTRO--F--LA FECHA DE REGISTRO DE NACIMIENTO EXISTIENDO RELACION CRONOLOGICA ENTRE AMBAS FECHAS ES PROCEDENTE",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DEL REGISTRADO--M--EL TEXTO DEL ACTA",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DEL REGISTRADO--M--LA PARTE SUPERIOR DEL ACTA",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DEL REGISTRADO--M--LA FOTOCOPIA DEL INDICE ALFABETICO",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DEL REGISTRADO--M--LA BOLETA DE REGISTRO",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DEL REGISTRADO--M--EL EJEMPLAR DE LA OFICIALIA",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DEL REGISTRADO--M--EL EJEMPLAR DE ARCHIVO CENTRAL",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DEL REGISTRADO--M--LA PARTE SUPERIOR DE ACTA, REGLAS ORTOGRAFICAS DE ESCRITURA Y DE LOS DOCUMENTOS QUE PRESENTA",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA REGISTRADA--F--EL TEXTO DEL ACTA",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA REGISTRADA--F--LA PARTE SUPERIOR DEL ACTA",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA REGISTRADA--F--LA FOTOCOPIA DEL INDICE ALFABETICO",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA REGISTRADA--F--LA BOLETA DE REGISTRO",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA REGISTRADA--F--EL EJEMPLAR DE LA OFICIALIA",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA REGISTRADA--F--EL EJEMPLAR DE ARCHIVO CENTRAL",
//                            "1--EL(LOS) NOMBRE(S) PROPIO(S) DE LA REGISTRADA--F--LA PARTE SUPERIOR DE ACTA, REGLAS ORTOGRAFICAS DE ESCRITURA Y DE LOS DOCUMENTOS QUE PRESENTA",
//                            "1--EL APELLIDO PATERNO DEL REGISTRADO--M--LAS REGLAS ORTOGRAFICAS DE ESCRITURA",
//                            "1--EL APELLIDO PATERNO DEL REGISTRADO--M--QUE SE DEDUCE DEL APELLIDO DEL PROGENITOR QUIEN COMPARECIO AL MOMENTO DEL REGISTRO",
//                            "1--EL APELLIDO PATERNO DEL REGISTRADO--M--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL APELLIDO MATERNO DEL REGISTRADO--M--LAS REGLAS ORTOGRAFICAS DE ESCRITURA",
//                            "1--EL APELLIDO MATERNO DEL REGISTRADO--M--QUE SE DEDUCE DEL APELLIDO DEL PROGENITOR QUIEN COMPARECIO AL MOMENTO DEL REGISTRO",
//                            "1--EL APELLIDO MATERNO DEL REGISTRADO--M--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL APELLIDO PATERNO DE LA REGISTRADA--F--LAS REGLAS ORTOGRAFICAS DE ESCRITURA",
//                            "1--EL APELLIDO PATERNO DE LA REGISTRADA--F--QUE SE DEDUCE DEL APELLIDO DEL PROGENITOR QUIEN COMPARECIO AL MOMENTO DEL REGISTRO",
//                            "1--EL APELLIDO PATERNO DE LA REGISTRADA--F--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL APELLIDO MATERNO DE LA REGISTRADA--F--LAS REGLAS ORTOGRAFICAS DE ESCRITURA",
//                            "1--EL APELLIDO MATERNO DE LA REGISTRADA--F--QUE SE DEDUCE DEL APELLIDO DEL PROGENITOR QUIEN COMPARECIO AL MOMENTO DEL REGISTRO",
//                            "1--EL APELLIDO MATERNO DE LA REGISTRADA--F--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--LA FECHA DE NACIMIENTO DEL REGISTRADO--M--LA RELACION CRONOLOGICA QUE EXISTE ENTRE LA FECHA DE REGISTRO Y NACIMIENTO",
//                            "1--LA FECHA DE NACIMIENTO DEL REGISTRADO--M--LA BOLETA DE REGISTRO",
//                            "1--LA FECHA DE NACIMIENTO DEL REGISTRADO--M--LA FOTOCOPIA DE LA OFICIALIA",
//                            "1--LA FECHA DE NACIMIENTO DEL REGISTRADO--M--LA FOTOCOPIA DE ARCHIVO CENTRAL",
//                            "1--LA FECHA DE NACIMIENTO DE LA REGISTRADA--F--LA RELACION CRONOLOGICA QUE EXISTE ENTRE LA FECHA DE REGISTRO Y NACIMIENTO",
//                            "1--LA FECHA DE NACIMIENTO DE LA REGISTRADA--F--LA BOLETA DE REGISTRO",
//                            "1--LA FECHA DE NACIMIENTO DE LA REGISTRADA--F--LA FOTOCOPIA DE LA OFICIALIA",
//                            "1--LA FECHA DE NACIMIENTO DE LA REGISTRADA--F--LA FOTOCOPIA DE ARCHIVO CENTRAL",
//                            "1--EL LUGAR DE NACIMIENTO DEL REGISTRADO--M--QUE JURIDICAMENTE EL LUGAR DE NACIMIENTO ES LA LOCALIDAD Y NO EL DOMICILIO QUE SE MENCIONA",
//                            "1--EL LUGAR DE NACIMIENTO DEL REGISTRADO--M--LA LEY TERRITORIAL QUE FIJA LAS DENOMINACIONES Y CATEGORIAS DE LOS PUEBLOS QUE INTEGRAN LA ENTIDAD",
//                            "1--EL LUGAR DE NACIMIENTO DEL REGISTRADO--M--QUE EL NACIMIENTO OCURRIO EN EL MISMO LUGAR DE REGISTRO",
//                            "1--EL LUGAR DE NACIMIENTO DEL REGISTRADO--M--LA BOLETA DE REGISTRO",
//                            "1--EL LUGAR DE NACIMIENTO DEL REGISTRADO--M--COMO SE DESPRENDE DEL LIBRO DE LA OFICIALIA IGUALANDO EJEMPLARES",
//                            "1--EL LUGAR DE NACIMIENTO DEL REGISTRADO--M--COMO SE DESPRENDE DEL LIBRO DEL ARCHIVO IGUALANDO EJEMPLARES",
//                            "1--EL LUGAR DE NACIMIENTO DE LA REGISTRADA--F--QUE JURIDICAMENTE EL LUGAR DE NACIMIENTO ES LA LOCALIDAD Y NO EL DOMICILIO QUE SE MENCIONA",
//                            "1--EL LUGAR DE NACIMIENTO DE LA REGISTRADA--F--LA LEY TERRITORIAL QUE FIJA LAS DENOMINACIONES Y CATEGORIAS DE LOS PUEBLOS QUE INTEGRAN LA ENTIDAD",
//                            "1--EL LUGAR DE NACIMIENTO DE LA REGISTRADA--F--QUE EL NACIMIENTO OCURRIO EN EL MISMO LUGAR DE REGISTRO",
//                            "1--EL LUGAR DE NACIMIENTO DE LA REGISTRADA--F--LA BOLETA DE REGISTRO",
//                            "1--EL LUGAR DE NACIMIENTO DE LA REGISTRADA--F--COMO SE DESPRENDE DEL LIBRO DE LA OFICIALIA IGUALANDO EJEMPLARES",
//                            "1--EL LUGAR DE NACIMIENTO DE LA REGISTRADA--F--COMO SE DESPRENDE DEL LIBRO DEL ARCHIVO IGUALANDO EJEMPLARES",
//                            "1--EL ESTADO BIOLOGICO--M--EL ARTICULO 68 FRACCIÓN VII DEL CODIGO CIVIL VIGENTE EN EL ESTADO",
//                            "1--EL ESTADO BIOLOGICO--M--LOS DOCUMENTOS PUBLICOS Y PRIVADOS PRESENTADOS",
//                            "1--EL ESTADO BIOLOGICO--M--LA CONSTANCIA DE ORIGEN Y VECINDAD",
//                            "1--EL ESTADO BIOLOGICO--M--QUE EL MISMO REGISTRADO PROMUEVE POR SU PROPIO DERECHO",
//                            "1--EL ESTADO BIOLOGICO--F--EL ARTICULO 68 FRACCIÓN VII DEL CODIGO CIVIL VIGENTE EN EL ESTADO",
//                            "1--EL ESTADO BIOLOGICO--F--LOS DOCUMENTOS PUBLICOS Y PRIVADOS PRESENTADOS",
//                            "1--EL ESTADO BIOLOGICO--F--LA CONSTANCIA DE ORIGEN Y VECINDAD",
//                            "1--EL ESTADO BIOLOGICO--F--QUE LA MISMA REGISTRADA PROMUEVE POR SU PROPIO DERECHO",
//                            "1--LOS QUE COMPARECIERON--M--QUE SE DEDUCE DEL TEXTO DEL ACTA",
//                            "1--LOS QUE COMPARECIERON--M--LAS FIRMAS QUE APARECEN AL CALCE DEL ATESTADO",
//                            "1--LOS QUE COMPARECIERON--F--QUE SE DEDUCE DEL TEXTO DEL ACTA",
//                            "1--LOS QUE COMPARECIERON--F--LAS FIRMAS QUE APARECEN AL CALCE DEL ATESTADO",
//                            "1--EL NOMBRE DEL PADRE DEL REGISTRADO--M--EL ACTA DE NACIMIENTO DEL PADRE",
//                            "1--EL NOMBRE DEL PADRE DEL REGISTRADO--M--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL NOMBRE DEL PADRE DEL REGISTRADO--M--LA FIRMA QUE APARECE AL CALCE DEL REGISTRO",
//                            "1--EL NOMBRE DEL PADRE DE LA REGISTRADA--F--EL ACTA DE NACIMIENTO DEL PADRE",
//                            "1--EL NOMBRE DEL PADRE DE LA REGISTRADA--F--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL NOMBRE DEL PADRE DE LA REGISTRADA--F--LA FIRMA QUE APARECE AL CALCE DEL REGISTRO",
//                            "1--EL APELLIDO PATERNO DEL PADRE DEL REGISTRADO--M--EL ACTA DE NACIMIENTO DEL PADRE",
//                            "1--EL APELLIDO PATERNO DEL PADRE DEL REGISTRADO--M--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL APELLIDO PATERNO DEL PADRE DEL REGISTRADO--M--LA FIRMA QUE APARECE AL CALCE DEL REGISTRO",
//                            "1--EL APELLIDO PATERNO DEL PADRE DEL REGISTRADO--M--QUE SE DESPRENDE DE LOS APELLIDOS DE LOS ABUELOS",
//                            "1--EL APELLIDO PATERNO DEL PADRE DE LA REGISTRADA--F--EL ACTA DE NACIMIENTO DEL PADRE",
//                            "1--EL APELLIDO PATERNO DEL PADRE DE LA REGISTRADA--F--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL APELLIDO PATERNO DEL PADRE DE LA REGISTRADA--F--LA FIRMA QUE APARECE AL CALCE DEL REGISTRO",
//                            "1--EL APELLIDO PATERNO DEL PADRE DE LA REGISTRADA--F--QUE SE DESPRENDE DE LOS APELLIDOS DE LOS ABUELOS",
//                            "1--EL APELLIDO MATERNO DEL PADRE DEL REGISTRADO--M--EL ACTA DE NACIMIENTO DEL PADRE",
//                            "1--EL APELLIDO MATERNO DEL PADRE DEL REGISTRADO--M--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL APELLIDO MATERNO DEL PADRE DEL REGISTRADO--M--LA FIRMA QUE APARECE AL CALCE DEL REGISTRO",
//                            "1--EL APELLIDO MATERNO DEL PADRE DEL REGISTRADO--M--QUE SE DESPRENDE DE LOS APELLIDOS DE LOS ABUELOS",
//                            "1--EL APELLIDO MATERNO DEL PADRE DE LA REGISTRADA--F--EL ACTA DE NACIMIENTO DEL PADRE",
//                            "1--EL APELLIDO MATERNO DEL PADRE DE LA REGISTRADA--F--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL APELLIDO MATERNO DEL PADRE DE LA REGISTRADA--F--LA FIRMA QUE APARECE AL CALCE DEL REGISTRO",
//                            "1--EL APELLIDO MATERNO DEL PADRE DE LA REGISTRADA--F--QUE SE DESPRENDE DE LOS APELLIDOS DE LOS ABUELOS",
//                            "1--LA EDAD DEL PADRE--M--EL ACTA DE NACIMIENTO DEL PADRE",
//                            "1--LA EDAD DEL PADRE--M--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--LA EDAD DEL PADRE--F--EL ACTA DE NACIMIENTO DEL PADRE",
//                            "1--LA EDAD DEL PADRE--F--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL NOMBRE DEL MADRE DEL REGISTRADO--M--EL ACTA DE NACIMIENTO DE LA MADRE",
//                            "1--EL NOMBRE DEL MADRE DEL REGISTRADO--M--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL NOMBRE DEL MADRE DEL REGISTRADO--M--LA FIRMA QUE APARECE AL CALCE DEL REGISTRO",
//                            "1--EL NOMBRE DEL MADRE DEL REGISTRADO--F--EL ACTA DE NACIMIENTO DE LA MADRE",
//                            "1--EL NOMBRE DEL MADRE DEL REGISTRADO--F--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL NOMBRE DEL MADRE DEL REGISTRADO--F--LA FIRMA QUE APARECE AL CALCE DEL REGISTRO",
//                            "1--EL APELLIDO PATERNO DE LA MADRE DEL REGISTRADO--M--EL ACTA DE NACIMIENTO DE LA MADRE",
//                            "1--EL APELLIDO PATERNO DE LA MADRE DEL REGISTRADO--M--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL APELLIDO PATERNO DE LA MADRE DEL REGISTRADO--M--LA FIRMA QUE APARECE AL CALCE DEL REGISTRO",
//                            "1--EL APELLIDO PATERNO DE LA MADRE DEL REGISTRADO--M--QUE SE DESPRENDE DE LOS APELLIDOS DE LOS ABUELOS",
//                            "1--EL APELLIDO PATERNO DE LA MADRE DE LA REGISTRADA--F--EL ACTA DE NACIMIENTO DE LA MADRE",
//                            "1--EL APELLIDO PATERNO DE LA MADRE DE LA REGISTRADA--F--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL APELLIDO PATERNO DE LA MADRE DE LA REGISTRADA--F--LA FIRMA QUE APARECE AL CALCE DEL REGISTRO",
//                            "1--EL APELLIDO PATERNO DE LA MADRE DE LA REGISTRADA--F--QUE SE DESPRENDE DE LOS APELLIDOS DE LOS ABUELOS",
//                            "1--EL APELLIDO MATERNO DE LA MADRE DEL REGISTRADO--M--EL ACTA DE NACIMIENTO DE LA MADRE",
//                            "1--EL APELLIDO MATERNO DE LA MADRE DEL REGISTRADO--M--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL APELLIDO MATERNO DE LA MADRE DEL REGISTRADO--M--LA FIRMA QUE APARECE AL CALCE DEL REGISTRO",
//                            "1--EL APELLIDO MATERNO DE LA MADRE DEL REGISTRADO--M--QUE SE DESPRENDE DE LOS APELLIDOS DE LOS ABUELOS",
//                            "1--EL APELLIDO MATERNO DE LA MADRE DE LA REGISTRADA--F--EL ACTA DE NACIMIENTO DE LA MADRE",
//                            "1--EL APELLIDO MATERNO DE LA MADRE DE LA REGISTRADA--F--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--EL APELLIDO MATERNO DE LA MADRE DE LA REGISTRADA--F--LA FIRMA QUE APARECE AL CALCE DEL REGISTRO",
//                            "1--EL APELLIDO MATERNO DE LA MADRE DE LA REGISTRADA--F--QUE SE DESPRENDE DE LOS APELLIDOS DE LOS ABUELOS",
//                            "1--LA EDAD DE LA MADRE--M--EL ACTA DE NACIMIENTO DE LA MADRE",
//                            "1--LA EDAD DE LA MADRE--M--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--LA EDAD DE LA MADRE--F--EL ACTA DE NACIMIENTO DE LA MADRE",
//                            "1--LA EDAD DE LA MADRE--F--EL ACTA DE MATRIMONIO DE LOS PADRES DE FECHA ANTERIOR AL REGISTRO",
//                            "1--LA NACIONALIDAD DEL PADRE--M--EL ACTA DE NACIMIENTO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DE LA MADRE--M--EL ACTA DE NACIMIENTO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DEL ABUELO PATERNO--M--EL ACTA DE NACIMIENTO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DE LA ABUELA PATERNA--M--EL ACTA DE NACIMIENTO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DEL ABUELO MATERNO--M--EL ACTA DE NACIMIENTO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DE LA ABUELA MATERNA--M--EL ACTA DE NACIMIENTO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DEL 1er. TESTIGO--M--EL ACTA DE NACIMIENTO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DEL 2o. TESTIGO--M--EL ACTA DE NACIMIENTO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DEL PADRE--F--EL ACTA DE MATRIMONIO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DE LA MADRE--F--EL ACTA DE MATRIMONIO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DEL ABUELO PATERNO--F--EL ACTA DE MATRIMONIO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DE LA ABUELA PATERNA--F--EL ACTA DE MATRIMONIO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DEL ABUELO MATERNO--F--EL ACTA DE MATRIMONIO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DE LA ABUELA MATERNA--F--EL ACTA DE MATRIMONIO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DEL 1er. TESTIGO--F--EL ACTA DE MATRIMONIO DE FECHA ANTERIOR",
//                            "1--LA NACIONALIDAD DEL 2o. TESTIGO--F--EL ACTA DE MATRIMONIO DE FECHA ANTERIOR",
//                            "1--EL NOMBRE DEL 1er. TESTIGO--M--LA FIRMA QUE APARECE AL CALCE DEL ACTA",
//                            "1--EL NOMBRE DEL 2o. TESTIGO--M--LA FIRMA QUE APARECE AL CALCE DEL ACTA",
//                            "1--EL NOMBRE DE LA PERSONA DISTINTA--M--LA FIRMA QUE APARECE AL CALCE DEL ACTA",
//                            "1--EL NOMBRE DEL 1er. TESTIGO--M--EL EJEMPLAR DE OFICIALIA DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NOMBRE DEL 2o. TESTIGO--M--EL EJEMPLAR DE OFICIALIA DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NOMBRE DE LA PERSONA DISTINTA--M--EL EJEMPLAR DE OFICIALIA DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NOMBRE DEL 1er. TESTIGO--M--EL EJEMPLAR DE ARCHIVO CENTRAL DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NOMBRE DEL 2o. TESTIGO--M--EL EJEMPLAR DE ARCHIVO CENTRAL DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NOMBRE DE LA PERSONA DISTINTA--M--EL EJEMPLAR DE ARCHIVO CENTRAL DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NOMBRE DEL 1er. TESTIGO--F--LA FIRMA QUE APARECE AL CALCE DEL ACTA",
//                            "1--EL NOMBRE DEL 2o. TESTIGO--F--LA FIRMA QUE APARECE AL CALCE DEL ACTA",
//                            "1--EL NOMBRE DE LA PERSONA DISTINTA--F--LA FIRMA QUE APARECE AL CALCE DEL ACTA",
//                            "1--EL NOMBRE DEL 1er. TESTIGO--F--EL EJEMPLAR DE OFICIALIA DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NOMBRE DEL 2o. TESTIGO--F--EL EJEMPLAR DE OFICIALIA DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NOMBRE DE LA PERSONA DISTINTA--F--EL EJEMPLAR DE OFICIALIA DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NOMBRE DEL 1er. TESTIGO--F--EL EJEMPLAR DE ARCHIVO CENTRAL DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NOMBRE DEL 2o. TESTIGO--F--EL EJEMPLAR DE ARCHIVO CENTRAL DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL NOMBRE DE LA PERSONA DISTINTA--F--EL EJEMPLAR DE ARCHIVO CENTRAL DONDE SE ENCUENTRA CORRECTAMENTE",
//                            "1--EL ESTADO LEGAL DE HIJO EN EL MOMENTO DEL REGISTRO--M--EL ACTA DE MATRIMONIO CIVIL DE LOS PADRES DE FECHA ANTERIOR",
//                            "1--EL ESTADO LEGAL DE HIJO EN EL MOMENTO DEL REGISTRO--M--EL ESTADO CIVIL DE LOS PADRES QUE SE MENCIONA EN EL TEXTO DEL ACTA",
//                            "1--EL ESTADO LEGAL DE HIJO EN EL MOMENTO DEL REGISTRO--M--QUE SE DEDUCE DE LA NOTA QUE OBRA AL MARGEN DEL ACTA, LA CUAL MENCIONA UNIDOS POR DOS LAZOS",
//                            "1--EL ESTADO LEGAL DE HIJO EN EL MOMENTO DEL REGISTRO--M--LA CONSTANCIA DE SOLTERIA QUE SE ANEXA",
//                            "1--EL ESTADO LEGAL DE HIJO EN EL MOMENTO DEL REGISTRO--F--EL ACTA DE MATRIMONIO CIVIL DE LOS PADRES DE FECHA ANTERIOR",
//                            "1--EL ESTADO LEGAL DE HIJO EN EL MOMENTO DEL REGISTRO--F--EL ESTADO CIVIL DE LOS PADRES QUE SE MENCIONA EN EL TEXTO DEL ACTA",
//                            "1--EL ESTADO LEGAL DE HIJO EN EL MOMENTO DEL REGISTRO--F--QUE SE DEDUCE DE LA NOTA QUE OBRA AL MARGEN DEL ACTA, LA CUAL MENCIONA UNIDOS POR DOS LAZOS",
//                            "1--EL ESTADO LEGAL DE HIJO EN EL MOMENTO DEL REGISTRO--F--LA CONSTANCIA DE SOLTERIA QUE SE ANEXA",
//                            "1--LA HORA DE NACIMIENTO DEL REGISTRADO--M--LO QUE SE DEDUCE DEL TEXTO DEL ACTA",
//                            "1--LA HORA DE NACIMIENTO DEL REGISTRADO--M--LAS REGLAS DE ESCRITUTA",
//                            "1--LA HORA DE NACIMIENTO DEL REGISTRADO--M--LA BOLETA DE REGISTRO",
//                            "1--LA HORA DE NACIMIENTO DE LA REGISTRADA--F--LO QUE SE DEDUCE DEL TEXTO DEL ACTA",
//                            "1--LA HORA DE NACIMIENTO DE LA REGISTRADA--F--LAS REGLAS DE ESCRITUTA",
//                            "1--LA HORA DE NACIMIENTO DE LA REGISTRADA--F--LA BOLETA DE REGISTRO",
//                            "1--EL NUMERO DE LIBRO DE ACTA DE NACIMIENTO--M--AL ARTICULO 43 DEL CODIGO CIVIL DEL ESTADO",
//                            "1--EL NUMERO DE LIBRO DE ACTA DE NACIMIENTO--F--AL ARTICULO 43 DEL CODIGO CIVIL DEL ESTADO"]
//                for (def uno: array){
//                    def dos=uno.split("--")
////                    def actaInstance=TipoActas.get(Long.parseLong(dos[0]))
//                    def fieldsInstance=Fields.findByNombreAndSexo(dos[1],dos[2])
//                    if(fieldsInstance){
//                        def baseInstance = new Base(acta:fieldsInstance.acta,campo:fieldsInstance, base:dos[3])
//                        baseInstance.save(flush: true, failOnError: true)
//                    }
//                        
//                }
//            }
//            if(!Nacionalidad.count()){
//                    use(CSVParser.class) {  
//                        File file = new File("web-app/catalogos/nacionalidades.csv") 
//                        def nacionalidadInstance
//                        file.parseCSV { index,field ->  
//                            nacionalidadInstance=new Nacionalidad(clave:field[0], nombre:field[1])
//                            nacionalidadInstance.save(flush: true, failOnError: true)
//                    }  
//                }  
//            }
//            
//            if(!Scadto.count()){
//                    use(CSVParser.class) {  
//                        File file = new File("web-app/catalogos/cat_distrito.csv") 
//                        def distritoInstance
//                        file.parseCSV { index,field ->  
//                            distritoInstance=new Scadto(clv:field[0],clvreg:field[1], descc:field[2])
//                            distritoInstance.save(flush: true, failOnError: true)
//                    }  
//                }  
//            }
//            
//            if(!Scaofi.count()){
//                    use(CSVParser.class) {  
//                        File file = new File("web-app/catalogos/cat_oficialias.csv") 
//                        def oficialiaInstance
//                        file.parseCSV { index,field ->  
//                            oficialiaInstance=new Scaofi(clv:Scadto.findByClv(field[1]),clv2:field[0], descrip:field[3])
//                            oficialiaInstance.save(flush: true, failOnError: true)
//                    }  
//                }  
//            }
//            if(!Scampo.count()){
//                    use(CSVParser.class) {  
//                        File file = new File("web-app/catalogos/cat_munpio.csv") 
//                        def munpioInstance
//                        file.parseCSV { index,field ->  
//                            munpioInstance=new Scampo(mpo:field[0],descrip:field[1], distrito:Scadto.findByClv(field[2]),oficialia: Scaofi.findByClv2(field[3]))
//                            munpioInstance.save(flush: true, failOnError: true)
//                    }  
//                }  
//            }
//            
//            if(!Localidades.count()){
//                    use(CSVParser.class) {  
//                        File file = new File("web-app/catalogos/cat_localidades.csv") 
//                        def localidadInstance
//                        file.parseCSV { index,field ->  
//                            localidadInstance=new Localidades(loc_clave:field[0],mpo:Scampo.findByMpo(Integer.parseInt(field[1])), localidad:field[2])
//                            localidadInstance.save(flush: true, failOnError: true)
//                    }  
//                }  
//            }
//            if(!Localidadofi.count()){
//                    use(CSVParser.class) {  
//                        File file = new File("web-app/catalogos/cat_localidadofi.csv") 
//                        def localidadOfiInstance
//                        def scampoInstance
//                        def oficialiaInstance
//                        def localidadInstance
//                        file.parseCSV { index,field ->  
//                            scampoInstance=Scampo.findByMpo(field[1]);
//                            oficialiaInstance=Scaofi.findByClv2(field[2]);
//                            localidadInstance=Localidades.findByMpoAndLoc_clave(scampoInstance,Integer.parseInt(field[0]));
//                            localidadOfiInstance=new Localidadofi(oficialia:oficialiaInstance,munpio:scampoInstance, localidad:localidadInstance)
//                            localidadOfiInstance.save(flush: true, failOnError: true)
//                    }  
//                }  
//            }
//   
//            if(!EncargadoJuridico.count()){
//                def encJuridicoInstance=new EncargadoJuridico(titulo:"LIC.",nombre:"IGNACION MANUEL",ape_pat:"MARTINEZ", ape_mat:"MORALES", inicio:new Date(), activo:true)
//                    encJuridicoInstance.save(flush: true, failOnError: true)
//            }
//            if(!EncargadoArchivo.count()){
//                def encArchivoInstance=new EncargadoArchivo(titulo:"LIC." , nombre:"MARCELO",ape_pat:"CARREÑO", ape_mat:"GOPAR", inicio:new Date(), activo:true)
//                    encArchivoInstance.save(flush: true, failOnError: true)
//            }                       
           
            
        }
     
    }
    
}
class CSVParser {  
    static def parseCSV(file,closure) {  
        def lineCount = 0  
        file.eachLine() { line ->  
            def field = line.tokenize(",")  
            lineCount++  
            closure(lineCount,field)  
        }  
    }  
}
