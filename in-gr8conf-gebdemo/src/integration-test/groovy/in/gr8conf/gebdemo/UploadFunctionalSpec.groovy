package in.gr8conf.gebdemo

import in.gr8conf.gebdemo.pages.UploadExamplePage
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
class UploadFunctionalSpec extends GebReportingSpec {



    void "Demo upload testing"() {
        setup:
        deleteFile()
        prepareFile()

        when:
        to UploadExamplePage

        then:
        at UploadExamplePage

        when:
        uploadFile('inputfile.txt')

        then:
        waitFor {
            new File("/tmp/outputfile.txt").exists()
        }
    }

    def prepareFile() {
        File file = new File("/tmp/inputfile.txt")
        file << "Hi I'm a file for upload"
    }

    def deleteFile() {
        ['inputfile.txt', 'outputfile.txt'].each {
            File file = new File("/tmp/$it")
            if( file.exists()) {
                file.delete()
            }

        }
    }

}



