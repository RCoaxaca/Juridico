// Place your Spring DSL code here
import org.krysalis.barcode4j.impl.code39.Code39Bean

beans = {
     code39Generator(Code39Bean) {
            height = 10
            fontSize=0
            //showText=false
        }
}
