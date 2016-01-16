package in.gr8conf.gebdemo.modules

import geb.Module

class AttendeeFormModule extends Module {

    static base = { $('form') }

    static content = {
        nameInput { $('#name').value() }
        emailInput { $('#email').value() }
    }
}
