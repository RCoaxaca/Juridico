package catalogos

class Erroresperado {
    String tipodeerror
    //Tipoerror tipoerr
  
    
    String toString(){
        return tipodeerror
    }
    
    //static belongsTo=Tipoerror
    
    
  
    static mapping = {
        table 'erroresperado'
        tipodeerror column:'tipodeerror',sqlType: 'varchar(60)'
        version false  
                //agregados
    }
    
    static constraints = {
    }
}
