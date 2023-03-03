package catalogos
import tablas.Scaact

class Campo {
    
String campo

  static hasMany = [scaact:Scaact]
  
String toString(){
    return campo
}
 
    static constraints = {

    }
      static mapping = {
                  table 'campo'
         campo column: 'campo',sqlType: 'varchar(60)'
        
      }
}
