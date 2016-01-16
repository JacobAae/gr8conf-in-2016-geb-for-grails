package in.gr8conf.gebdemo.pages

import in.gr8conf.gebdemo.modules.AttendeeFormModule
import in.gr8conf.gebdemo.modules.NavigationBarModule
import geb.Page

class AttendeeEditPage extends Page {

    static url = "/attendee/edit"

    static at = {
        title ==~ /Edit Attendee/
    }

    static content = {
        menubar { module NavigationBarModule }
        form { module AttendeeFormModule }
        updateButton{ $("button.btn-primary") }
    }
}
