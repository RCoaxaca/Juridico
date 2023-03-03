package catalogos

class Scadto {
    int clv
    int clvreg
    String descc
    
 static hasMany = [municipios:Scampo,oficialias:Scaofi] 
 
String toString(){
    return descc
}
    
    static constraints = {
    }
    
    static mapping = {
        table 'scadto'
        descc column: 'descc',sqlType: 'varchar(30)'
        municipios sort:'descrip',order:'asc'

        version false  
                //agregados
}
}