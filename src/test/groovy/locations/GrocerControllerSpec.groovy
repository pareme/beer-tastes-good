package locations

import grails.test.mixin.*
import spock.lang.*

@TestFor(GrocerController)
@Mock(Grocer)
class GrocerControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params['name'] = 'fill'
        params['lng'] = 232.232
        params['lat'] = 232.232
        params['address'] = new Address(streetAddress: 'fill', city: 'fill', state: 'VA', zipCode: 23226)
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.grocerList
            model.grocerCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.grocer!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def grocer = new Grocer()
            grocer.validate()
            controller.save(grocer)

        then:"The create view is rendered again with the correct model"
            model.grocer!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            grocer = new Grocer(params)

            controller.save(grocer)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/grocer/show/1'
            controller.flash.message != null
            Grocer.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def grocer = new Grocer(params)
            controller.show(grocer)

        then:"A model is populated containing the domain instance"
            model.grocer == grocer
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def grocer = new Grocer(params)
            controller.edit(grocer)

        then:"A model is populated containing the domain instance"
            model.grocer == grocer
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/grocer/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def grocer = new Grocer()
            grocer.validate()
            controller.update(grocer)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.grocer == grocer

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            grocer = new Grocer(params).save(flush: true)
            controller.update(grocer)

        then:"A redirect is issued to the show action"
            grocer != null
            response.redirectedUrl == "/grocer/show/$grocer.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/grocer/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def grocer = new Grocer(params).save(flush: true)

        then:"It exists"
            Grocer.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(grocer)

        then:"The instance is deleted"
            Grocer.count() == 0
            response.redirectedUrl == '/grocer/index'
            flash.message != null
    }
}
