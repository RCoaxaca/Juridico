package catalogos

class Opcion {
 int exapro
 Tcorrect error
 String  tipo 
 int tablaid
    static constraints = {
         //tipo  (nullable:true)
         error  (nullable:true)
    }
    
    static mapping={
         tipo column:'tipo',sqlType:'varchar(40)'
        
    }
    
}
    