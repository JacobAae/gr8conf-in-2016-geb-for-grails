package in.gr8conf.gebdemo

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(AttendeeService)
@Mock(Attendee)
class AttendeeServiceSpec extends Specification {

    @Unroll
    void "test saveAttendee"() {
        when:
        attendee?.validate()
        Result result = service.saveAttendee(attendee)

        then:
        result
        result.status == status

        where:
        attendee                                                | status
        null                                                    | Status.NOT_FOUND
        new Attendee()                                          | Status.HAS_ERRORS
        new Attendee(name: 'Jacob', email:'jacob@gr8conf.org')  | Status.OK
    }

    @Unroll
    void "test deleteAttendee"() {
        when:
        attendee?.save()
        Result result = service.deleteAttendee(attendee)

        then:
        result
        result.status == status

        where:
        attendee                                                | status
        null                                                    | Status.NOT_FOUND
        new Attendee(name: 'Jacob', email:'jacob@gr8conf.org')  | Status.OK
    }

}
