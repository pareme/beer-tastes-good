package locations

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(BreweryController)
@Mock(Brewery)
class BreweryControllerSpec extends Specification {

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
            !model.breweryList
            model.breweryCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.brewery!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def brewery = new Brewery()
            brewery.validate()
            controller.save(brewery)

        then:"The create view is rendered again with the correct model"
            model.brewery!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            brewery = new Brewery(params)

            controller.save(brewery)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/brewery/show/1'
            controller.flash.message != null
            Brewery.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def brewery = new Brewery(params)
            controller.show(brewery)

        then:"A model is populated containing the domain instance"
            model.brewery == brewery
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def brewery = new Brewery(params)
            controller.edit(brewery)

        then:"A model is populated containing the domain instance"
            model.brewery == brewery
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/brewery/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def brewery = new Brewery()
            brewery.validate()
            controller.update(brewery)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.brewery == brewery

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            brewery = new Brewery(params).save(flush: true)
            controller.update(brewery)

        then:"A redirect is issued to the show action"
            brewery != null
            response.redirectedUrl == "/brewery/show/$brewery.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == "/brewery/index"
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def brewery = new Brewery(params).save(flush: true)

        then:"It exists"
            Brewery.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(brewery)

        then:"The instance is deleted"
            Brewery.count() == 0
            response.redirectedUrl == "/brewery/index"
            flash.message != null
    }
}
