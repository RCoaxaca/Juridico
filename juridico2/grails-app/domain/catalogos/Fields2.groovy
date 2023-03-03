package catalogos

class Fields2 {

   Integer id
    Tipoactas acta
    int num
    String nombre
    String tipo
    String nombre2
    
    static belongsTo = Tipoactas
   
    
    String toString(){
        return nombre
    }

    static constraints = {
    }
    
        static mapping = {
        table 'Fields2'
        nombre column: 'nombre',sqlType: 'varchar(55)'
        tipo column: 'tipo',sqlType: 'varchar(1)'
        nombre2 column: 'nombre2',sqlType: 'varchar(55)'
      

        version false  
                //agregados
}
}