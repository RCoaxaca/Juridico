package catalogos

class Papeleta {
    int expano
    int expro
    //char idn
    String nota
    String donde
    int usuario
    String ip
    //String namofi
    //String dic

    static constraints = {
        //namofi (nullable:true)
        //dic (nullable:true)
        //idn (nullable:true)
    }
    static mapping = {
        table 'papeleta'
        //idn column: 'idn',sqlType: 'char(1)'
        nota column: 'nota',sqlType: 'MediumText'
        //namofi column: 'namofi',sqlType:'varchar(50)'
        //dic column: 'dic',sqlType:'varchar(20)'
        version false  
                //agregados
    }
}
