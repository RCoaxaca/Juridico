package catalogos



import org.junit.*
import grails.test.mixin.*

@TestFor(EncargadoArchivoController)
@Mock(EncargadoArchivo)
class EncargadoArchivoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/encargadoArchivo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.encargadoArchivoInstanceList.size() == 0
        assert model.encargadoArchivoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.encargadoArchivoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.encargadoArchivoInstance != null
        assert view == '/encargadoArchivo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/encargadoArchivo/show/1'
        assert controller.flash.message != null
        assert EncargadoArchivo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/encargadoArchivo/list'

        populateValidParams(params)
        def encargadoArchivo = new EncargadoArchivo(params)

        assert encargadoArchivo.save() != null

        params.id = encargadoArchivo.id

        def model = controller.show()

        assert model.encargadoArchivoInstance == encargadoArchivo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/encargadoArchivo/list'

        populateValidParams(params)
        def encargadoArchivo = new EncargadoArchivo(params)

        assert encargadoArchivo.save() != null

        params.id = encargadoArchivo.id

        def model = controller.edit()

        assert model.encargadoArchivoInstance == encargadoArchivo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/encargadoArchivo/list'

        response.reset()

        populateValidParams(params)
        def encargadoArchivo = new EncargadoArchivo(params)

        assert encargadoArchivo.save() != null

        // test invalid parameters in update
        params.id = encargadoArchivo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/encargadoArchivo/edit"
        assert model.encargadoArchivoInstance != null

        encargadoArchivo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/encargadoArchivo/show/$encargadoArchivo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        encargadoArchivo.clearErrors()

        populateValidParams(params)
        params.id = encargadoArchivo.id
        params.version = -1
        controller.update()

        assert view == "/encargadoArchivo/edit"
        assert model.encargadoArchivoInstance != null
        assert model.encargadoArchivoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/encargadoArchivo/list'

        response.reset()

        populateValidParams(params)
        def encargadoArchivo = new EncargadoArchivo(params)

        assert encargadoArchivo.save() != null
        assert EncargadoArchivo.count() == 1

        params.id = encargadoArchivo.id

        controller.delete()

        assert EncargadoArchivo.count() == 0
        assert EncargadoArchivo.get(encargadoArchivo.id) == null
        assert response.redirectedUrl == '/encargadoArchivo/list'
    }
}
