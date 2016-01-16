package in.gr8conf.gebdemo

import grails.test.mixin.*
import spock.lang.*

@TestFor(AttendeeController)
@Mock([Attendee,Talk])
class AttendeeControllerSpec extends Specification {

    def setup() {
        controller.attendeeService = Mock(AttendeeService)
    }

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'Jacob'
        params["email"] = 'jacob@gr8conf.org'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.attendeeList
            model.attendeeCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.attendee!= null
    }

    void "Test the save action correctly persists an instance"() {
        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def attendee = new Attendee()
            attendee.validate()
            controller.save(attendee)

        then:"The create view is rendered again with the correct model"
            1 * controller.attendeeService.saveAttendee(_) >> new Result(status: Status.HAS_ERRORS, item: new Attendee())
        model.attendee!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            attendee = new Attendee(params)

            controller.save(attendee)

        then:"A redirect is issued to the show action"
            1 * controller.attendeeService.saveAttendee(_) >> { Attendee a -> a.save(); new Result(status: Status.OK, item: a) }
            response.redirectedUrl == '/attendee/show/1'
            controller.flash.message != null
            Attendee.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def attendee = new Attendee(params)
            controller.show(attendee)

        then:"A model is populated containing the domain instance"
            model.attendee == attendee
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def attendee = new Attendee(params)
            controller.edit(attendee)

        then:"A model is populated containing the domain instance"
            model.attendee == attendee
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            1 * controller.attendeeService.saveAttendee(_) >> { new Result(status: Status.NOT_FOUND) }
            response.redirectedUrl == '/attendee/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def attendee = new Attendee()
            attendee.validate()
            controller.update(attendee)

        then:"The edit view is rendered again with the invalid instance"
            1 * controller.attendeeService.saveAttendee(_) >> { Attendee a -> a.validate(); new Result(status: Status.HAS_ERRORS, item: a) }
            view == 'edit'
            model.attendee == attendee

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            attendee = new Attendee(params).save(flush: true)
            controller.update(attendee)

        then:"A redirect is issued to the show action"
            1 * controller.attendeeService.saveAttendee(_) >> { Attendee a -> a.save(); new Result(status: Status.OK, item: a) }
            attendee != null
            response.redirectedUrl == "/attendee/show/$attendee.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            1 * controller.attendeeService.deleteAttendee(_) >> { new Result(status: Status.NOT_FOUND) }
            response.redirectedUrl == '/attendee/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def attendee = new Attendee(params).save(flush: true)

        then:"It exists"
            Attendee.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(attendee)

        then:"The instance is deleted"
        1 * controller.attendeeService.deleteAttendee(_) >> { Attendee a -> a.delete(flush:true); new Result(status: Status.OK) }
            Attendee.count() == 0
            response.redirectedUrl == '/attendee/index'
            flash.message != null
    }
}
