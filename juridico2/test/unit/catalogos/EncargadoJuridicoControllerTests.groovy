package catalogos



import org.junit.*
import grails.test.mixin.*

@TestFor(EncargadoJuridicoController)
@Mock(EncargadoJuridico)
class EncargadoJuridicoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/encargadoJuridico/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.encargadoJuridicoInstanceList.size() == 0
        assert model.encargadoJuridicoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.encargadoJuridicoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.encargadoJuridicoInstance != null
        assert view == '/encargadoJuridico/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/encargadoJuridico/show/1'
        assert controller.flash.message != null
        assert EncargadoJuridico.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/encargadoJuridico/list'

        populateValidParams(params)
        def encargadoJuridico = new EncargadoJuridico(params)

        assert encargadoJuridico.save() != null

        params.id = encargadoJuridico.id

        def model = controller.show()

        assert model.encargadoJuridicoInstance == encargadoJuridico
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/encargadoJuridico/list'

        populateValidParams(params)
        def encargadoJuridico = new EncargadoJuridico(params)

        assert encargadoJuridico.save() != null

        params.id = encargadoJuridico.id

        def model = controller.edit()

        assert model.encargadoJuridicoInstance == encargadoJuridico
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/encargadoJuridico/list'

        response.reset()

        populateValidParams(params)
        def encargadoJuridico = new EncargadoJuridico(params)

        assert encargadoJuridico.save() != null

        // test invalid parameters in update
        params.id = encargadoJuridico.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/encargadoJuridico/edit"
        assert model.encargadoJuridicoInstance != null

        encargadoJuridico.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/encargadoJuridico/show/$encargadoJuridico.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        encargadoJuridico.clearErrors()

        populateValidParams(params)
        params.id = encargadoJuridico.id
        params.version = -1
        controller.update()

        assert view == "/encargadoJuridico/edit"
        assert model.encargadoJuridicoInstance != null
        assert model.encargadoJuridicoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/encargadoJuridico/list'

        response.reset()

        populateValidParams(params)
        def encargadoJuridico = new EncargadoJuridico(params)

        assert encargadoJuridico.save() != null
        assert EncargadoJuridico.count() == 1

        params.id = encargadoJuridico.id

        controller.delete()

        assert EncargadoJuridico.count() == 0
        assert EncargadoJuridico.get(encargadoJuridico.id) == null
        assert response.redirectedUrl == '/encargadoJuridico/list'
    }
}
