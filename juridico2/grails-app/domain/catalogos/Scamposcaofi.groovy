package catalogos

class Scamposcaofi {
    Scampo municipio
    Scaofi oficialia
    
    static belongsTo = [Scampo,Scaofi]
    
    String toString()
    {
        return oficialia
    }

    static constraints = {
    }
    
      static mapping = {
        table 'Scamposcaofi'
        municipio column: 'municipio'
        oficialia column: 'oficialia'

        version false  
                //agregados
    }
}
