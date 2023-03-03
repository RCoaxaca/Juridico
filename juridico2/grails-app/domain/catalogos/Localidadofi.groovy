package catalogos

class Localidadofi {
    Scaofi oficialia
    Scampo munpio
    Localidades localidad
    
    static belongsTo = [Scampo,Scaofi,Localidades]
    
    String toString(){
    return oficialia
}

    static constraints = {
    }
        static mapping = {
        table 'localidadofi'
        oficialia column: 'oficialia'
        munpio column:'munpio'
        localidad column:'localidad'

        version false  
                //agregados
}
}
