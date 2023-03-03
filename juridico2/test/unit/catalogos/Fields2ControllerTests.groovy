package catalogos



import org.junit.*
import grails.test.mixin.*

@TestFor(Fields2Controller)
@Mock(Fields2)
class Fields2ControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fields2/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fields2InstanceList.size() == 0
        assert model.fields2InstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.fields2Instance != null
    }

    void testSave() {
        controller.save()

        assert model.fields2Instance != null
        assert view == '/fields2/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fields2/show/1'
        assert controller.flash.message != null
        assert Fields2.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fields2/list'

        populateValidParams(params)
        def fields2 = new Fields2(params)

        assert fields2.save() != null

        params.id = fields2.id

        def model = controller.show()

        assert model.fields2Instance == fields2
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fields2/list'

        populateValidParams(params)
        def fields2 = new Fields2(params)

        assert fields2.save() != null

        params.id = fields2.id

        def model = controller.edit()

        assert model.fields2Instance == fields2
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fields2/list'

        response.reset()

        populateValidParams(params)
        def fields2 = new Fields2(params)

        assert fields2.save() != null

        // test invalid parameters in update
        params.id = fields2.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fields2/edit"
        assert model.fields2Instance != null

        fields2.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fields2/show/$fields2.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fields2.clearErrors()

        populateValidParams(params)
        params.id = fields2.id
        params.version = -1
        controller.update()

        assert view == "/fields2/edit"
        assert model.fields2Instance != null
        assert model.fields2Instance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fields2/list'

        response.reset()

        populateValidParams(params)
        def fields2 = new Fields2(params)

        assert fields2.save() != null
        assert Fields2.count() == 1

        params.id = fields2.id

        controller.delete()

        assert Fields2.count() == 0
        assert Fields2.get(fields2.id) == null
        assert response.redirectedUrl == '/fields2/list'
    }
}
