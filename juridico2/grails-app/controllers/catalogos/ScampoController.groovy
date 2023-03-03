package catalogos

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class ScampoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
    
def getMunicipio () {     
    def scadtoInstance=Scadto.get(params.id)
        def municipiosList = scadtoInstance?.municipios       
       //  println(municipiosList)       
        render(template:"getMunicipios", model: [municipiosList:municipiosList])
    }
    
def getMunicipio3 () {     
    def scadtoInstance=Scadto.get(params.id)
    def municipiosList
     municipiosList = scadtoInstance?.municipios   
    
     render municipiosList as JSON
    }
    
      def getMunicipio2 = {
      
        def scadtoInstance = Scadto.get(params.id)
        def municipiosList = scadtoInstance?.municipios
       render(template:"getMunicipios2", model: [municipiosList:municipiosList])
   
    }
  def getMunici = {
      
        def scadtoInstance = Scadto.get(params.id)
        def municipiosList = scadtoInstance?.municipios
       render(template:"getMunici", model: [municipiosList:municipiosList])
   
    }
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [scampoInstanceList: Scampo.list(params), scampoInstanceTotal: Scampo.count()]
    }

    def create() {
        [scampoInstance: new Scampo(params)]
    }

    def save() {
        def scampoInstance = new Scampo(params)
        if (!scampoInstance.save(flush: true)) {
            render(view: "create", model: [scampoInstance: scampoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'scampo.label', default: 'Scampo'), scampoInstance.id])
        redirect(action: "show", id: scampoInstance.id)
    }

    def show(Long id) {
        def scampoInstance = Scampo.get(id)
        if (!scampoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scampo.label', default: 'Scampo'), id])
            redirect(action: "list")
            return
        }
        [scampoInstance: scampoInstance]
    }

    def edit(Long id) {
        def scampoInstance = Scampo.get(id)
        if (!scampoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scampo.label', default: 'Scampo'), id])
            redirect(action: "list")
            return
        }

        [scampoInstance: scampoInstance]
    }

    def update(Long id, Long version) {
        def scampoInstance = Scampo.get(id)
        if (!scampoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scampo.label', default: 'Scampo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (scampoInstance.version > version) {
                scampoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'scampo.label', default: 'Scampo')] as Object[],
                          "Another user has updated this Scampo while you were editing")
                render(view: "edit", model: [scampoInstance: scampoInstance])
                return
            }
        }

        scampoInstance.properties = params

        if (!scampoInstance.save(flush: true)) {
            render(view: "edit", model: [scampoInstance: scampoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'scampo.label', default: 'Scampo'), scampoInstance.id])
        redirect(action: "show", id: scampoInstance.id)
    }

    def delete(Long id) {
        def scampoInstance = Scampo.get(id)
        if (!scampoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'scampo.label', default: 'Scampo'), id])
            redirect(action: "list")
            return
        }

        try {
            scampoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'scampo.label', default: 'Scampo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'scampo.label', default: 'Scampo'), id])
            redirect(action: "show", id: id)
        }
    }
}
