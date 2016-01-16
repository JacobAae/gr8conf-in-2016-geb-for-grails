@Grapes([
		@Grab("org.gebish:geb-core:0.12.2"),
		@GrabExclude('org.codehaus.groovy:groovy-all'),
		@Grab("org.seleniumhq.selenium:selenium-chrome-driver:2.49.0"),
		@Grab("org.seleniumhq.selenium:selenium-support:2.49.0")
])

import geb.Browser
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.Keys

// Update this with location of your downloaded Chrome Driver
System.setProperty("webdriver.chrome.driver", "/home/jacob/repositories/gr8conf/gr8conf-in-2016-geb-for-grails/chromedriver")

Browser browser = new Browser(driver: new ChromeDriver())

browser.with {
// tag::standalone1[]
	go "http://duckduckgo.com"

	$('input', name: 'q').value("GR8Conf India")
	$('input', name: 'q') << Keys.ENTER

	waitFor(10, 1) { $("#links").displayed }
    sleep(3000) // For demo reasons

	$("h2.result__title").first().click()

	waitFor { title = "GR8Conf IN-2016" }
// end::standalone1[]
}

sleep(10000)

browser.close()

