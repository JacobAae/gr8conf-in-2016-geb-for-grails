package in.gr8conf.gebdemo.pages

import in.gr8conf.gebdemo.modules.NavigationBarModule
import geb.Page

class AttendeeShowPage extends Page {

    static url = "/attendee/show"

    static at = {
        title ==~ /Show Attendee/
    }

    static content = {
        menubar { module NavigationBarModule }
        attendeeProperties { $('span.property-label') }
        name { attendeeProperties.find{ it.text() == 'Name' }.next().text() }
        email { attendeeProperties.find{ it.text() == 'Email' }.next().text() }
        editButton{ $("a.btn-primary") }

    }



}
