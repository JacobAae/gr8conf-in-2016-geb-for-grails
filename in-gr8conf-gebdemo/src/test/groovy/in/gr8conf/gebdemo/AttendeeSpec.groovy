package in.gr8conf.gebdemo

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Attendee)
class AttendeeSpec extends Specification {

    @Unroll
    void "Test valid properties for attendee"() {
        when:
        Attendee attendee = new Attendee(name: name, email: email, nationality: nationality)

        then:
        attendee.validate()

        where:
        name        | email                 | nationality
        'Jacob'     | 'jacob@gr8conf.org'   | 'Danish'
        'Guillaume' | 'glaforge@gmail.com'  | null
    }

    @Unroll
    void "Test invalid properties for attendee: #comment"() {
        when:
        Attendee attendee = new Attendee(name: name, email: email, nationality: nationality)

        then:
        !attendee.validate()

        where:
        name        | email                 | nationality   | comment
        ''          | 'jacob@gr8conf.org'   | 'Danish'      | 'Blank name'
        null        | 'jacob@gr8conf.org'   | null          | 'null name'
        'Jacob'     | null                  | 'Danish'      | 'null email'
        'Jacob'     | ''                    | 'Danish'      | 'Blank email'
        'Jacob'     | 'jacob(a)gr8conf.org' | 'Danish'      | 'Invalid email'
    }

}
