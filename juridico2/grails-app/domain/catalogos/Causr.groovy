package catalogos

class Causr {
    String clv 
    String nombre
    Date updates
    Date outdate
    String usrtype
    String status
    String passw

    static constraints = {
        passw(size:6..10)
       
    }
    
    static mapping = {
        table 'clausrr'
        clv column: 'clv',sqlType: 'varchar(3)'
        nombre column: 'nombre',sqlType: 'varchar(50)'
        updates column:'updates'
        outdate column:'outdate'
        usrtype column: 'usrtype',sqlType: 'varchar(3)'
        status column: 'status',sqlType: 'varchar(3)'
        passw column: 'passw',sqlType: 'varchar(10)'

        version false  
                //agregados
       
    }
    
}
