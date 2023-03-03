package catalogos

class Tipoactas {
    Integer id
    String tipoacta

 static hasMany=[actas:Fields,actacampo:Base]

    String toString(){
    return tipoacta
}
    static constraints = {
    }
    
        static mapping = {
        table 'tipoactas'
        tipoacta column: 'tipoacta',sqlType: 'varchar(30)'

        version false  
                //agregados
}
}
