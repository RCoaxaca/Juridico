package catalogos


import org.springframework.dao.DataIntegrityViolationException
import catalogos.Tipoactas
import catalogos.Fields

class BaseController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)        
        [baseInstanceList: Base.findAllByBaseNotEqual("OTRO",params), baseInstanceTotal: Base.findAllByBaseNotEqual("OTRO").size()]
    }

    def create() {
        [baseInstance: new Base(params)]
    }
    
      def getBases()
    {
        def baseInstanceList
        println(params.id)
        def campo=Fields.get(params.id)
        def campo2=Fields.findAllByNombre(campo.nombre)
        println(campo2.size()+" Aqui va el total de bases")
        println(campo2.nombre)
        println(campo2.id)
        def listacompleta
        baseInstanceList=Base.findAllByCampo(Fields.get(campo2.id[0]))
               
        for(int i=0;i<campo2.size();i++)
        {
            if(i>0){
            println(campo2.id[i])
            println(Fields.get(campo2.id[i]))
            println(Base.findAllByCampo(Fields.get(campo2.id[i])))
            if(Base.findAllByCampo(Fields.get(campo2.id[i])))
            {
                def auxiliar=Base.findAllByCampo(Fields.get(campo2.id[i]))
                baseInstanceList+=auxiliar
                //def auxiliar2=Fields.get(campo2.id[i])
                //println(auxiliar2.sexo)
                //listasexo+=auxiliar2
                
                println("Agrego algo")
            }
            
            }
            //baseInstanceList=Base.findAll("From base where campo=?",[Fields.get(campo2.id[i])])
            //println(baseInstanceList.base)
        }
   
        //baseInstanceList=Base.findAll("From base where campo=?",[campo2.id])
        //baseInstanceList=Base.findAllByBase(campo.nombre,[sort:'nombre',order:'asc'])
        render(template:"bases2", model: [baseInstanceList:baseInstanceList])
    }
    
    def getCampo(){
        println(params.id)
        def actaInstance = Tipoactas.get(params.id)
        println(actaInstance)
        def camposList = Fields.findAllByActa(actaInstance,[sort:'nombre',order:'asc'])
      
        println(camposList)
        //def camposList = actaInstance?.localidad
       render(template:"camposlist", model: [camposList:camposList])
    }
    
    def buscaBase()
    {
        println(params.id)
        println(params.err)
        if(params.id.equals('SI'))
        {
            def bases=Base.findAll("From Base where campo="+params.err+"")
         
            def basess=bases.base
            basess.add("OTRO")
//               println(bases)
            //def ba=bases.base
            render(template:"bases", model: [basess:basess])
            //return
        }
        return
    }
    
    def verBase()
    {
        println(params.id)
       
        if(params.id.toString().equals("LA FOTOCOPIA DEL LIBRO DE"))
        {
            render(template:"baseFotocopia",model:[null:null])
            //return
        }
        else if(params.id.equals("OTRO"))
        {
            render(template:"otro", model: [null:null])
        }
        return
        
    }
    
    
    def buscaBaseAnotacion()
    {
        String retorna=""
        println("LLego al controlador para buscar las bases ")
        println(params)
        if(params.id.toString().contains("OTRO"))
        {
            //render""
            render(template:"otroParaTestar", model: [null:null])
        }
        else{
            if(params.id.toString().contains("IMPROCEDENTE"))
            {
                retorna+="<div class=\"form-group\">"
                retorna+="<label for=\"debeser\" class=\"col-md-3\">En virtud de:</label>"
                retorna+="<div class=\"col-md-9\">"
                retorna+= "<input type=\"text\" name=\"debeser\" class=\"form-control input-sm\" required=\"\" value=\"POR SER IMPROCEDENTE\"/>"
                retorna+="</div>"
                retorna+="</div>"
                retorna+="<div class=\"form-group\">"                
                retorna+="<label for=\"base\" class=\"col-md-3\">Con fundamento en:</label>"
                retorna+="<div class=\"col-md-9\">"
                retorna+="<input type=\"text\" name=\"base\" class=\"form-control input-sm\" required=\"\" value=\"EL ARTICULO 47 DEL CODIGO CIVIL VIGENTE EN EL ESTADO\"/>"
                retorna+="</div>"
                retorna+="</div>"
                render retorna
                //render "<div class=\"col-md-14\"><g:textField name=\"debeser\" class=\"form-control input-sm\" required=\"\" value=\"LA CIRCULAR 002 DE FECHA 15 DE JUNIO DE 1988\" onChange=\"conMayusculas(this)\" >LA CIRCULAR 002 DE FECHA 15 DE JUNIO DE 1988</g:textField></div>"
            }
            else if(params.id.toString().contains("CARECER"))
            {
                retorna+="<div class=\"form-group\">"
                retorna+="<label for=\"debeser\" class=\"col-md-3\">En virtud de:</label>"
                retorna+="<div class=\"col-md-9\">"
                retorna+= "<input type=\"text\" name=\"debeser\" class=\"form-control input-sm\" required=\"\" value=\"POR CARECER DE LOS ELEMENTOS ESCENCIALES PARA TENER VALIDEZ PLENA\"/>"
                retorna+="</div>"
                retorna+="</div>"
                retorna+="<div class=\"form-group\">"                
                retorna+="<label for=\"base\" class=\"col-md-3\">Con fundamento en:</label>"
                retorna+="<div class=\"col-md-9\">"
                retorna+="<input type=\"text\" name=\"base\" class=\"form-control input-sm\" required=\"\" value=\"LA CIRCULAR 002 DE FECHA 15 DE JUNIO DE 1988\"/>"
                retorna+="</div>"
                retorna+="</div>"
                render retorna
            }
            else
            {
                retorna+="<div class=\"form-group\">"
                retorna+="<label for=\"debeser\" class=\"col-md-3\">En virtud de:</label>"
                retorna+="<div class=\"col-md-9\">"
                retorna+= "<input type=\"text\" name=\"debeser\" class=\"form-control input-sm\" required=\"\" value=\""+params.id+"\"/>"
                retorna+="</div>"
                retorna+="</div>"
                retorna+="<div class=\"form-group\">"                
                retorna+="<label for=\"base\" class=\"col-md-3\">Con fundamento en:</label>"
                retorna+="<div class=\"col-md-9\">"
                retorna+="<input type=\"text\" name=\"base\" class=\"form-control input-sm\" required=\"\" value=\"EL ARTICULO 385 PARRAFO PRIMERO DEL CODIGO CIVIL VIGENTE EN EL ESTADO\"/>"
                retorna+="</div>"
                retorna+="</div>"
                render retorna
                
            }
            
        }
    }

    
    
     def verBase2()
    {
        println(params.id)
       def base=params.id
  
            render(template:"fotocopia",model:[base:base])
            //return
        
        
    }
    
    def save() {
        def baseInstance = new Base(params)
        if (!baseInstance.save(flush: true)) {
            render(view: "create", model: [baseInstance: baseInstance])
            return
        }

        flash.message = message(code: 'default.Agregrada.message', args: [message(code: 'base.label', default: 'Base'), baseInstance.id])
        //redirect(action: "show", id: baseInstance.id)
        redirect(action: "list")
    }

    def show(Long id) {
        def baseInstance = Base.get(id)
        if (!baseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'base.label', default: 'Base'), id])
            redirect(action: "list")
            return
        }

        [baseInstance: baseInstance]
    }

    def edit(Long id) {
        def baseInstance = Base.get(id)
        if (!baseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'base.label', default: 'Base'), id])
            redirect(action: "list")
            return
        }

        [baseInstance: baseInstance]
    }

    def update(Long id, Long version) {
        def baseInstance = Base.get(id)
        if (!baseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'base.label', default: 'Base'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (baseInstance.version > version) {
                baseInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'base.label', default: 'Base')] as Object[],
                          "Another user has updated this Base while you were editing")
                render(view: "edit", model: [baseInstance: baseInstance])
                return
            }
        }

        baseInstance.properties = params

        if (!baseInstance.save(flush: true)) {
            render(view: "edit", model: [baseInstance: baseInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'base.label', default: 'Base'), baseInstance.id])
        redirect(action: "list", id: baseInstance.id)
    }

    def delete(Long id) {
        def baseInstance = Base.get(id)
        if (!baseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'base.label', default: 'Base'), id])
            redirect(action: "list")
            return
        }

        try {
            baseInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'base.label', default: 'Base'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'base.label', default: 'Base'), id])
            redirect(action: "show", id: id)
        }
    }
}
