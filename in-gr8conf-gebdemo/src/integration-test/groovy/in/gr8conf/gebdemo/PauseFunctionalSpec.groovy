package in.gr8conf.gebdemo

import in.gr8conf.gebdemo.pages.AttendeeIndexPage
import grails.test.mixin.integration.Integration
import grails.transaction.*
import org.openqa.selenium.JavascriptExecutor
import spock.lang.*
import geb.spock.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@Integration
@Rollback
@Stepwise
@Ignore
class PauseFunctionalSpec extends GebReportingSpec {


    void "Demo pause button"() {
        when:
        to AttendeeIndexPage

        then:
        !hasPagination()

        // tag::pause[]
        when:
        pause() // Pause Geb until button pressed
        // end::pause[]

        then:
        true
    }

    def js( String script ){
        (driver as JavascriptExecutor).executeScript( script )
    }

    // https://github.com/tomaslin/grails-test-recipes
    // tag::pause-geb[]
    private void pause() {
        js.exec """(function() {
          window.__gebPaused = true;
          var div = document.createElement("div");
          div.setAttribute('style',"position: absolute; top:0px;\\
            z-index: 3000; padding: 10px; background-color: red;);
          var button = document.createElement("button");
          button.innerHTML = "Unpause Geb";
          button.onclick = function() {
              window.__gebPaused = false;
          }
          div.appendChild(button);
          document.getElementsByTagName("body")[0].appendChild(div);
        })();"""
        waitFor(300) { !js.__gebPaused }
    }
    // end::pause-geb[]

}



