package tablas

import catalogos.Erroresperado
import catalogos.Fields
import catalogos.Tipoerror
import catalogos.Scampo
import catalogos.Scadto
import catalogos.Localidades

class Scaerr {
   int expano
    long expro
    Tipoerror tcorrect
    Fields campo  
    Erroresperado terror
    String contiene
    String debeser
    String procede
    String base
    int donde
    int usuario
    String ip

    static constraints = {
            campo(nullable:true)
    }
    
        static mapping = {
        table 'scaerr'
        contiene column: 'contiene',sqlType: 'varchar(350)'
        debeser column: 'debeser',sqlType:'varchar(100)'
        procede column: 'procede',sqlType:'varchar(2)'
        base column: 'base',sqlType:'varchar(1550)'
        version false  
                //agregados
    }
}
