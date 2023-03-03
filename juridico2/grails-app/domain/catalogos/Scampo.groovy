package catalogos

class Scampo {
    int mpo

    Scadto distrito
    String descrip
    Scaofi oficialia
    
   // int region se le quito region 
   static belongsTo = [Scadto,Scaofi]
   static hasMany=[localidad:Localidades,munici:Scamposcaofi,locofi:Localidadofi]



String toString(){
    return descrip
}
    static constraints = {
    }
    
    static mapping = {
        table 'Scampo'
        descrip column: 'descrip',sqlType: 'varchar(60)'
        localidad sort:'localidad',order:'asc'

        version false  
                //agregados
            }
}
