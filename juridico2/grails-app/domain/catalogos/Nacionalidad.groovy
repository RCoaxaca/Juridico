package catalogos
import tablas.Scaact
class Nacionalidad {
    int clave
    String nombre

    
    static mappedBy = [nacionalidades:'pnac',nacionalidades:'pab1nac',nacionalidades:'pab2nac',nacionalidades:'mnac',nacionalidades:'mab1nac',nacionalidades:'mab2nac']
    static hasMany =[nacionalidades:Scaact]
    
    	
    String toString()
    {
        return nombre
    }
    
    static constraints = {
    }
    
        static mapping = {
        table 'Nacionalidad'
        nombre column: 'nombre',sqlType: 'varchar(50)'

        version false  
                //agregados
       
    }
}
