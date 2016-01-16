package in.gr8conf.gebdemo.modules

import geb.Module

class AttendeeListItemModule extends Module {

    static content = {
        data { $("td", it) }
        name { data(0).text() }
        email { data(1).text() }
        nationality { data(2).text() }
        dateCreated { data(3).text() }
        lastUpdated { data(4).text() }
    }
}
