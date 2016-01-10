@Grapes([
		@Grab("org.gebish:geb-core:0.12.2"),
		@GrabExclude('org.codehaus.groovy:groovy-all'),
		@Grab("org.seleniumhq.selenium:selenium-chrome-driver:2.40.0"),
		@Grab("org.seleniumhq.selenium:selenium-support:2.40.0")
])

import geb.Browser
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.Keys

System.setProperty("webdriver.chrome.driver", "/home/jacob/repositories/gr8conf/gr8conf-in-2016-geb-for-grails/chromedriver");

Browser browser = new Browser(driver: new ChromeDriver())

class DuckDuckGoPage extends geb.Page {

	static url = "http://duckduckgo.com"

	static at = { title ==~ /DuckDuckGo/ }

	static content = {
		inputField{ $('input', name: 'q') }
	}

	def submit() {
		inputField << Keys.ENTER
	}
}

class DuckDuckGoResultPage extends geb.Page {

	static url = "http://duckduckgo.com"

	static at = { $("#links").displayed }

	static content = {
		links{ $("h2.result__title") }
	}

	def clickLink(int linkNumber) {
		links[linkNumber].click()
	}
}

class GR8ConfIndiaPage extends geb.Page {
	static at = { title == "GR8Conf IN-2016" }
}

browser.with {
    to DuckDuckGoPage

	inputField << "GR8Conf India"
	submit()

	waitFor(10, 0.5) {
		at DuckDuckGoResultPage
	}

	clickLink(0)

	waitFor {
		at GR8ConfIndiaPage
	}
}

sleep(10000)

browser.close()