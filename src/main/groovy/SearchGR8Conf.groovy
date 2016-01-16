@Grapes([
        @Grab("org.gebish:geb-core:0.9.2"),
        @Grab("org.seleniumhq.selenium:selenium-chrome-driver:2.33.0"),
        @Grab("org.seleniumhq.selenium:selenium-support:2.31.0")
])
import geb.Browser
import geb.Page
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.Keys

System.setProperty("webdriver.chrome.driver", "/home/jacob/repositories/gr8conf/gr8conf-in-2016-geb-for-grails/chromedriver");

Browser browser = new Browser(driver: new ChromeDriver())


class SearchPage  extends Page {

    static url = "https://duckduckgo.com/"
    static content = {
        inputField(required: false) { $("index") }
    }
}
class ResultPage  extends Page {

    static url = "https://duckduckgo.com/"
    static content = {

    }
}

browser.drive {
    to SearchPage
    assert title.startsWith( 'DuckDuckGo' )

    sleep(1000);

    inputField << "GR8conf"
    inputField << Keys.ENTER



}

sleep(5000);

println "So far so good"
