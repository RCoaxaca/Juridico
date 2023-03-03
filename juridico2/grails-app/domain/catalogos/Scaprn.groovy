package catalogos

class Scaprn {
    String usrid
    char toja
    char mimp
    int tlet
    
    static constraints = {
    }
    
    static mapping = {
        table 'scaprn'
        userid column: 'userid',sqlType: 'varchar(3)'

        version false  
                //agregados
    }
}
