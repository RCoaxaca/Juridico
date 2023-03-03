package catalogos
import catalogos.Tipoactas
import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class FieldsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [fieldsInstanceList: Fields.list(params), fieldsInstanceTotal: Fields.count()]
    }
    
    def buscaFields()
    {
        /*def scadtoInstance=Tipoactas.get(params.id)
    def municipiosList
     municipiosList = scadtoInstance?.municipios   
     System.out.println("2. munpios"+municipiosList);
     render municipiosList as JSON*/
     println(params.id)
     def fieldsList=Fields.findAllByActa(Tipoactas.get(params.id),[sort:'nombre',order:'asc'])
     println(fieldsList)
     render fieldsList as JSON
    }
    
  

    def create() {
        [fieldsInstance: new Fields(params)]
    }

    def save() {
        def fieldsInstance = new Fields(params)
        if (!fieldsInstance.save(flush: true)) {
            render(view: "create", model: [fieldsInstance: fieldsInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fields.label', default: 'Fields'), fieldsInstance.id])
        redirect(action: "show", id: fieldsInstance.id)
    }

    def show(Long id) {
        def fieldsInstance = Fields.get(id)
        if (!fieldsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fields.label', default: 'Fields'), id])
            redirect(action: "list")
            return
        }

        [fieldsInstance: fieldsInstance]
    }

    def edit(Long id) {
        def fieldsInstance = Fields.get(id)
        if (!fieldsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fields.label', default: 'Fields'), id])
            redirect(action: "list")
            return
        }

        [fieldsInstance: fieldsInstance]
    }

    def update(Long id, Long version) {
        def fieldsInstance = Fields.get(id)
        if (!fieldsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fields.label', default: 'Fields'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (fieldsInstance.version > version) {
                fieldsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'fields.label', default: 'Fields')] as Object[],
                          "Another user has updated this Fields while you were editing")
                render(view: "edit", model: [fieldsInstance: fieldsInstance])
                return
            }
        }

        fieldsInstance.properties = params

        if (!fieldsInstance.save(flush: true)) {
            render(view: "edit", model: [fieldsInstance: fieldsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fields.label', default: 'Fields'), fieldsInstance.id])
        redirect(action: "show", id: fieldsInstance.id)
    }

    def delete(Long id) {
        def fieldsInstance = Fields.get(id)
        if (!fieldsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fields.label', default: 'Fields'), id])
            redirect(action: "list")
            return
        }

        try {
            fieldsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fields.label', default: 'Fields'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fields.label', default: 'Fields'), id])
            redirect(action: "show", id: id)
        }
    }
}
