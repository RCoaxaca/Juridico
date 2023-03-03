package catalogos

class EncargadoArchivo {
    String nombre
    String ape_pat
    String ape_mat
    Date inicio
    Date fin
    Boolean activo
    String titulo;
    
    
    
    
    String toString()
    {
        
        return nombre+" "+ape_pat+" "+ape_mat
    } 

    static mapping={
        table 'jefearchivo'
        nombre column: 'nombre',sqlType: 'varchar(50)' 
        ape_pat column: 'ape_pat',sqlType: 'varchar(70)'
        ape_mat column: 'ape_mat',sqlType: 'varchar(70)'
        //version false
    }
    
    static constraints = {
        fin(nullable:true)
        ape_mat(nullable:true)
    }
}
