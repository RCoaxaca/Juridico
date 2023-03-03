package catalogos

import com.testapp.User

class Resguardo {
    String numero_expediente
    String ubicacion
    Date fecha_entrada
    String entrega
    boolean papeleta
    boolean resolucion
    User usuario

    static constraints = {
     papeleta (nullable:true)
     resolucion (nullable:true)
    }
}
