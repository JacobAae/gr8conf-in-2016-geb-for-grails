package in.gr8conf.gebdemo

import grails.test.mixin.*
import spock.lang.*

@TestFor(TalkController)
@Mock(Talk)
class TalkControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["title"] = 'Some Title'
        params["presenter"] = 'Some Presenter'
        params["startsAt"] = new Date()
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.talkList
            model.talkCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.talk!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def talk = new Talk()
            talk.validate()
            controller.save(talk)

        then:"The create view is rendered again with the correct model"
            model.talk!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            talk = new Talk(params)

            controller.save(talk)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/talk/show/1'
            controller.flash.message != null
            Talk.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def talk = new Talk(params)
            controller.show(talk)

        then:"A model is populated containing the domain instance"
            model.talk == talk
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def talk = new Talk(params)
            controller.edit(talk)

        then:"A model is populated containing the domain instance"
            model.talk == talk
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/talk/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def talk = new Talk()
            talk.validate()
            controller.update(talk)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.talk == talk

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            talk = new Talk(params).save(flush: true)
            controller.update(talk)

        then:"A redirect is issued to the show action"
            talk != null
            response.redirectedUrl == "/talk/show/$talk.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/talk/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def talk = new Talk(params).save(flush: true)

        then:"It exists"
            Talk.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(talk)

        then:"The instance is deleted"
            Talk.count() == 0
            response.redirectedUrl == '/talk/index'
            flash.message != null
    }
}
