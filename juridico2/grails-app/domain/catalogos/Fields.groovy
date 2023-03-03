package catalogos

class Fields {
    Integer id
    Tipoactas acta
    int num
    String nombre
    String tipo
    char sexo
    
    static belongsTo = Tipoactas
   static hasMany=Base
   
    
    String toString(){
        return nombre
    }

    static constraints = {
    }
    
        static mapping = {
        table 'Fields'
        nombre column: 'nombre',sqlType: 'varchar(65)'
        tipo column: 'tipo',sqlType: 'varchar(1)'
        nombre2 column: 'nombre2',sqlType: 'varchar(55)'
      

        version false  
                //agregados
       
    }
}
