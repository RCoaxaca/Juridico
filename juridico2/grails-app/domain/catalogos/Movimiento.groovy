package catalogos

import com.testapp.User

class Movimiento {
    Date fecha
    String numero_expediente
    Date entrada
    Date salida
    String usuarioentrega
    User usuariorecibe
    User usuariopresta
    

    static constraints = {
        entrada (nullable:true)
        salida (nullable:true)
        usuariopresta(nullable:true)
        usuariorecibe(nullable:true)
        
    }
    
    
            static mapping = {
       
    //entrada column: 'entrada',sqlType: 'timestamp'
    //salida column: 'salida',sqlType: 'timestamp'

        //version false  
                //agregados
    }
}
