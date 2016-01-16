package in.gr8conf.gebdemo

import in.gr8conf.gebdemo.pages.AttendeeCreatePage
import in.gr8conf.gebdemo.pages.AttendeeEditPage
import in.gr8conf.gebdemo.pages.AttendeeIndexPage
import in.gr8conf.gebdemo.pages.AttendeeShowPage
import grails.test.mixin.integration.Integration
import grails.transaction.*

import spock.lang.*
import geb.spock.*

@Integration
@Rollback
@Stepwise
class AttendeeFunctionalSpec extends GebReportingSpec {

    void "Go to list page - check initial state"() {
        when:
        to AttendeeIndexPage

        then:
        at AttendeeIndexPage
    }

    void "Click new attendee button"() {
        when:
        menubar.newAttendee.click()

        then:
        at AttendeeCreatePage
    }

    void "Submit form with errors"() {
        when:
        submitButton.click()

        then:
        at AttendeeCreatePage
    }

    void "Submit form with no errors"() {
        when:
        form.name = 'Bob'
        form.email = 'bob@somemail.com'

        and:
        submitButton.click()

        then:
        at AttendeeShowPage
        name == 'Bob'
        email == 'bob@somemail.com'
    }

    void "Click Edit Button"() {
        when:
        editButton.click()

        then:
        at AttendeeEditPage
    }

    void "Update Attendee"() {
        when:
        form.name = 'Alice'
        form.email = 'alice@somemail.com'

        and:
        updateButton.click()

        then:
        at AttendeeShowPage
        title == 'Show Attendee'
        name == 'Alice'
        email == 'alice@somemail.com'
    }




}
