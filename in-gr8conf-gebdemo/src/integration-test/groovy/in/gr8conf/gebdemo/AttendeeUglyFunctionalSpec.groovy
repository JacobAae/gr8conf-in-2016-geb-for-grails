package in.gr8conf.gebdemo

import grails.test.mixin.integration.Integration
import grails.transaction.*

import spock.lang.*
import geb.spock.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@Integration
@Rollback
@Stepwise
class AttendeeUglyFunctionalSpec extends GebReportingSpec {

    void "Go to list page - check initial state"() {
        when:"The home page is visited"
        go '/attendee/index'

        then:
        title == "Attendee List"
    }

    void "Click new attendee button"() {
        when:
        $("a.create").click()

        then:
        title == "Create Attendee"

    }

    void "Submit form with errors"() {
        when:
        $("button.btn-primary").click()

        then:
        title == "Create Attendee"


    }

    void "Submit form with no errors"() {
        when:
        $('form').name = 'Bobby'
        $('form').email = 'bobby@somemail.com'

        and:
        $("button.btn-primary").click()

        then:
        title == 'Show Attendee'
        $('div.property-value').find{ it.text() == 'Bobby'}
        $('div.property-value').find{ it.text() == 'bobby@somemail.com'}
    }

    void "Click Edit Button"() {
        when:
        $("a.btn-primary").click()

        then:
        title == 'Edit Attendee'
    }

    void "Update Attendee"() {
        when:
        $('form').name = 'Alison'
        $('form').email = 'alison@somemail.com'

        and:
        $("button.btn-primary").click()

        then:
        title == 'Show Attendee'
        $('div.property-value').find{ it.text() == 'Alison'}
        $('div.property-value').find{ it.text() == 'alison@somemail.com'}
    }



}
