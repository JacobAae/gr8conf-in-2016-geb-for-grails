package in.gr8conf.gebdemo.pages

import in.gr8conf.gebdemo.modules.AttendeeFormModule
import in.gr8conf.gebdemo.modules.NavigationBarModule
import geb.Page

class AttendeeCreatePage extends Page {

    static url = "/attendee/create"

    static at = {
        title ==~ /Create Attendee/
    }

    static content = {
        menubar { module NavigationBarModule }
        form { module AttendeeFormModule }
        submitButton{ $("button.btn-primary") }
    }
}
