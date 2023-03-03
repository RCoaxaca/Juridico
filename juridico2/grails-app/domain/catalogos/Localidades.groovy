package catalogos

class Localidades {
    
    int loc_clave
    Scampo mpo
    String localidad

     static belongsTo = Scampo
    static hasMany=[locofic:Localidadofi] 
  
String toString(){
    return localidad
}
    static constraints = {
    }
    
    static mapping = {
        table 'localidades'
        mpo column:'municipio'
        localidad column: 'localidad',sqlType: 'varchar(60)'

        version false  
                //agregados
    }
}

