package in.gr8conf.gebdemo.pages

import in.gr8conf.gebdemo.modules.AttendeeListItemModule
import in.gr8conf.gebdemo.modules.NavigationBarModule
import geb.Page

class AttendeeListPage extends Page {

    static url = "/attendee/list"

    static at = {
        title ==~ /Attendee List/
    }

    static content = {
        menubar { module NavigationBarModule }
        attendees { moduleList AttendeeListItemModule, $("table tr").tail() }
        fadeInMessage{ $('div.fade-me-in') }
    }

}
