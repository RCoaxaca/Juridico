package tablas
import com.testapp.User


import catalogos.*

class Scasol {
    int expano
    int expro
    User dic2 //usuario
    User val //usuario validador
    Date fchsol
    String promov
    String nom_intere
    String ap1_intere
    String ap2_intere
    Tipoactas typact
    int numact
    String fchact
    String anexo
    Boolean proced
    Scadto dto
    Scampo mpo
    Localidades loc
    Date fchcam
    int isprint
    Scaofi  ofi 
    String condonado
    int ofi_recibido
    String sexintere
    
    String nomb
    Date fechasol
    String apepa
    String apema
    int folio
    String folioexp
    Docesta estado
    String  obser
    User cap 
    User dicc  
    String ip
    String usuario
    Date fecharesolucion
static belongsTo = Tipoactas
    
    static constraints = {
     condonado(inList:['NO','SI'])
     sexintere(inList:['M','F'])
     
        folio (nullable:true)
        folioexp (nullable:true)
        estado (nullable:true)
        obser (nullable:true)
        dicc (nullable:true)
        fecharesolucion(nullable:true)
        expano (nullable:true)
        expro (nullable:true)
        dic2 (nullable:true)
        val (nullable:true)
        fchsol (nullable:true)
        promov (nullable:true)
        nom_intere (nullable:true)
        ap1_intere (nullable:true)
        ap2_intere (nullable:true)
        typact (nullable:true)
        numact (nullable:true)
        fchact (nullable:false)
        anexo (nullable:true)
        proced (nullable:true)
        dto (nullable:true)
        mpo (nullable:true)
        loc (nullable:true)
        fchcam (nullable:true)
        isprint (nullable:true)
        ofi (nullable:true)
        ofi_recibido (nullable:true)

        
    }
    
 
        static mapping = {
        table 'scasol'
        
    dic column: 'dic',sqlType: 'varchar(3)'
   // val column: 'val',sqlType: 'varchar(3)'
    promov column: 'promov',sqlType: 'varchar(65)'
    nom_intere column: 'nom_intere',sqlType: 'varchar(40)'
    ap1_intere column: 'ap1_intere',sqlType: 'varchar(40)'
    ap2_intere column: 'ap2_intere',sqlType: 'varchar(40)'
    anexo column: 'anexo',sqlType: 'varchar(250)'
    fchact column: 'fchact',sqlType: 'date'
    obser column: 'obser',sqlType: 'text'
    version false  
                //agregados
    }
    
}
