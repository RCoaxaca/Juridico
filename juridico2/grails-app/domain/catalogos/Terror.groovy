package catalogos
import tablas.Scaact

class Terror {
  String donde
 

 static hasMany = [scaact:Scaact]
String toString(){
    return donde
}
  
    static constraints = {
       
    }
    static mapping = {
                 table 'terror'
         donde column: 'donde',sqlType: 'varchar(60)'
         version false 
        
    }
}
