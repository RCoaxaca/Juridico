package catalogos
import com.testapp.User

class Venta { 
    int id
    String nomb
    Date fechasol
    String apepa
    String apema
    int folio
    String folioexp
    Docesta estado
    String  obser
    User cap
    User dic 
    
    
    static constraints = {
        folio (nullable:true)
        folioexp (nullable:true)
        estado (nullable:true)
        obser (nullable:true)
        dic (nullable:true)
        
    }
    
    
}
