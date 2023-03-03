package tablas



import org.junit.*
import grails.test.mixin.*

@TestFor(ScasolController)
@Mock(Scasol)
class ScasolControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/scasol/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.scasolInstanceList.size() == 0
        assert model.scasolInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.scasolInstance != null
    }

    void testSave() {
        controller.save()

        assert model.scasolInstance != null
        assert view == '/scasol/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/scasol/show/1'
        assert controller.flash.message != null
        assert Scasol.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/scasol/list'

        populateValidParams(params)
        def scasol = new Scasol(params)

        assert scasol.save() != null

        params.id = scasol.id

        def model = controller.show()

        assert model.scasolInstance == scasol
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/scasol/list'

        populateValidParams(params)
        def scasol = new Scasol(params)

        assert scasol.save() != null

        params.id = scasol.id

        def model = controller.edit()

        assert model.scasolInstance == scasol
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/scasol/list'

        response.reset()

        populateValidParams(params)
        def scasol = new Scasol(params)

        assert scasol.save() != null

        // test invalid parameters in update
        params.id = scasol.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/scasol/edit"
        assert model.scasolInstance != null

        scasol.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/scasol/show/$scasol.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        scasol.clearErrors()

        populateValidParams(params)
        params.id = scasol.id
        params.version = -1
        controller.update()

        assert view == "/scasol/edit"
        assert model.scasolInstance != null
        assert model.scasolInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/scasol/list'

        response.reset()

        populateValidParams(params)
        def scasol = new Scasol(params)

        assert scasol.save() != null
        assert Scasol.count() == 1

        params.id = scasol.id

        controller.delete()

        assert Scasol.count() == 0
        assert Scasol.get(scasol.id) == null
        assert response.redirectedUrl == '/scasol/list'
    }
}
