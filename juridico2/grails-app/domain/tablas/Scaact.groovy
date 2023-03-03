package tablas

import catalogos.Localidades
import catalogos.Nacionalidad
import catalogos.Scadto
import catalogos.Scampo
import catalogos.Campo
import catalogos.Erroresperado
import catalogos.Terror


class Scaact {
   	
        
    Integer expano
    Integer exppro
    Erroresperado tipoerresp 
    Campo datofalta
    Terror donde
    String procede
    String bases
    Integer numacta
    String fechaacta
    Scadto dto 
    Scampo mpo
    Localidades loc
    String pnombre 
    String pap1
    String pap2
    Integer pedad
    Nacionalidad pnac
    String pab1
    String pab1ap1
    String pab1ap2
    Nacionalidad pab1nac
    String pab2
    String pab2ap1
    String pab2ap2
    Nacionalidad pab2nac
    String mnom
    String map1
    String map2
    Integer medad
    Nacionalidad mnac
    String mab1
    String mab1ap1
    String mab1ap2
    Nacionalidad mab1nac
    String mba2
    String mab2ap1
    String mab2ap2
    Nacionalidad mab2nac
    int usuario
    String ip
    

 static constraints = {  
    numacta (nullable:true)
    fechaacta(nullable:true)
     datofalta(nullable:true)
    donde(nullable:true)
    dto (nullable:true)
    mpo(nullable:true)
    loc (nullable:true)
    pnombre (nullable:true)
    pap1(nullable:true)
    pap2 (nullable:true)
    pedad(nullable:true)
    pnac(nullable:true)
    pab1(nullable:true)
    pab1ap1(nullable:true)
    pab1ap2(nullable:true)
    pab1nac(nullable:true)
    pab2(nullable:true)
    pab2ap1(nullable:true)
    pab2ap2(nullable:true)
    pab2nac(nullable:true)
    mnom(nullable:true)
    map1(nullable:true)
    map2(nullable:true)
    medad(nullable:true)
    mnac(nullable:true)
    mab1(nullable:true)
    mab1ap1(nullable:true)
    mab1ap2(nullable:true)
    mab1nac(nullable:true)
    mba2(nullable:true)
    mab2ap1(nullable:true)
    mab2ap2(nullable:true)
    mab2nac(nullable:true)
    
    procede(inList:['SI','NO'])
   
    }
    
    static mapping = {
        
        table 'scaact'
         expano column: 'expano'
         exppro column: 'exppro'
         tipoerresp column: 'tipoerresp'
        
        precede column: 'procede',sqlType: 'varchar(2)'
        bases column:'bases',sqlType:'varchar(1500)'
        pap1 column:'pap1',sqlType:'varchar(40)'
        pap2 column:'pap2',sqlType:'varchar(40)'
        pab1 column:'pab1',sqlType:'varchar(40)'
        pab1ap1 column:'pab1ap1',sqlType:'varchar(40)'
        pab1ap2 column:'pab1ap2',sqlType:'varchar(40)'
        pab2 column:'pab2',sqlType:'varchar(40)'
        pab2ap1 column:'pab2ap1',sqlType:'varchar(40)'
        pab2ap2 column:'pab2ap2',sqlType:'varchar(40)'
        mnom column:'mnom',sqlType:'varchar(40)'
        map1 column:'map1',sqlType:'varchar(40)'
        map2 column:'map2',sqlType:'varchar(40)'
        mab1 column:'mab1',sqlType:'varchar(40)'
        mab1ap1 column:'mab1ap1',sqlType:'varchar(40)'
        mab1ap2 column:'mab1ap2',sqlType:'varchar(40)'
        mab2 column:'mab2',sqlType:'varchar(40)'
        mab2ap1 column:'mab2ap1',sqlType:'varchar(40)'
        mab2ap2 column:'mab2ap2',sqlType:'varchar(40)'
        

        version false  
                //agregados
    }
    
}
