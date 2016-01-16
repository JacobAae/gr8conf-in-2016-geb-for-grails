package in.gr8conf.gebdemo

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Talk)
class TalkSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Test talk with valid input"() {
        expect:
        new Talk(title: 'Title', presenter: 'Presenter', startsAt: new Date() ).validate()
    }

    void "Test talk with invalid input"() {
        expect:
        new Talk(title: 'Title', presenter: 'Presenter', startsAt: new Date() ).validate()

        where:
        title   | presenter     | startsAt
        null    | 'Presenter'   | new Date()
        ''      | 'Presenter'   | new Date()
        'Title' | null          | new Date()
        'Title' | ''            | new Date()
        'Title' | 'Presenter'   | null
    }

}
