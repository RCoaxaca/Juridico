package catalogos

class Tipoerror {
    String tipoerror
    int pant
    
      
    String toString(){
        
        return tipoerror
        
    }
    
   // static hasMany=[error:Erroresperado]
  
    
    static mapping = {
        table 'tipoerror'
        tipoerror column:'tipoerror',sqlType: 'varchar(60)'
        version false  
                //agregados
    }

    static constraints = {
    }
}
