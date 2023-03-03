package catalogos

class Base {
    Integer id
    Tipoactas acta
    Fields campo
    String base
    
    String toString()
    {
        return base
    }
    
    static belongsTo=[Tipoactas,Fields]
    
    static mapping={
        table 'base'
        base column: 'base',sqlType: 'varchar(1550)' 
        version false
    }

    static constraints = {
    }
}
