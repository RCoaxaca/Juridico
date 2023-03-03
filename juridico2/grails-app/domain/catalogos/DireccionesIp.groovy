package catalogos

class DireccionesIp {
    String ip
    static constraints = {
    }
    
    String toString()
    {
        
        return ip
    }
        static mapping={
        ip column: 'ip',sqlType: 'varchar(15)' 
        version false
    }
}
