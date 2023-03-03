package catalogos

class Entidades {
    int clave
    String nombre
    

    static constraints = {
    }
    
        static mapping = {
        table 'Entidades'
        id column: 'ID' // No esta en soliddb.extr_nac
        nombre column: 'nombre',sqlType: 'varchar(45)'
    

        version false  
                //agregados
       
    }
}
