package catalogos

class Scaofi {
    Scadto clv
    int clv2
    //Scadto clvdto
    String descrip
    String nombre
    static belongsTo = Scadto
    
    static hasMany=[oficialias:Scamposcaofi,locofis:Localidadofi]
     String toString(){
        return descrip
    }

    static constraints = {
    }
    
    static mapping = {
        table 'Scaofi'
        descrip column: 'descrip',sqlType: 'varchar(60)'
        nombre column: 'nombre',sqlType: 'varchar(250)'
        version false  
                //agregados
    }
    
}
