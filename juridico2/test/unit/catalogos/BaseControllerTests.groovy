package catalogos



import org.junit.*
import grails.test.mixin.*

@TestFor(BaseController)
@Mock(Base)
class BaseControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/base/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.baseInstanceList.size() == 0
        assert model.baseInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.baseInstance != null
    }

    void testSave() {
        controller.save()

        assert model.baseInstance != null
        assert view == '/base/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/base/show/1'
        assert controller.flash.message != null
        assert Base.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/base/list'

        populateValidParams(params)
        def base = new Base(params)

        assert base.save() != null

        params.id = base.id

        def model = controller.show()

        assert model.baseInstance == base
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/base/list'

        populateValidParams(params)
        def base = new Base(params)

        assert base.save() != null

        params.id = base.id

        def model = controller.edit()

        assert model.baseInstance == base
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/base/list'

        response.reset()

        populateValidParams(params)
        def base = new Base(params)

        assert base.save() != null

        // test invalid parameters in update
        params.id = base.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/base/edit"
        assert model.baseInstance != null

        base.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/base/show/$base.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        base.clearErrors()

        populateValidParams(params)
        params.id = base.id
        params.version = -1
        controller.update()

        assert view == "/base/edit"
        assert model.baseInstance != null
        assert model.baseInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/base/list'

        response.reset()

        populateValidParams(params)
        def base = new Base(params)

        assert base.save() != null
        assert Base.count() == 1

        params.id = base.id

        controller.delete()

        assert Base.count() == 0
        assert Base.get(base.id) == null
        assert response.redirectedUrl == '/base/list'
    }
}
