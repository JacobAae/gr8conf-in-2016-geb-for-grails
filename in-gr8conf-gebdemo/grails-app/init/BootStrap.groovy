import in.gr8conf.gebdemo.Attendee
import in.gr8conf.gebdemo.Talk

class BootStrap {

    def init = { servletContext ->
        def talks = []
        [
            ['Keynote', 'Burt Beckwith', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 09:30' )],
            ['Functional Programming with Groovy ', 'Naresha K', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 10:30' )],
            ['Advantages of Groovy & Grails', 'Søren Berg Glasius', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 10:30' )],
            ['Concurrency with GPars', 'Paul King', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 11:30' )],
            ['Async Programming with RatPack', 'Prakash Balodi', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 11:30' )],
            ['Dockerize it all', 'Puneet Behl & Bhagwat Kumar', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 12:25' )],
            ['Geb for testing your Grails Application', 'Jacob Aae Mikkelsen', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 12:25' )],
            ['Designing with Groovy Traits', 'Naresha K', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 14:10' )],
            ['Tour de Plugin', 'Søren Berg Glasius', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 14:10' )],
            ['Fun with Spring Security', 'Burt Beckwith', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 15:05' )],
            ['Introducing Gradle', 'Paul King', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 15:05' )],
            ['The GR8 Road to #fame', 'Himanshu Seth', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 16:05' )],
            ['Panel Discussion', 'Multiple speakers', Date.parse( 'yyyy-MM-dd hh:mm', '2016-01-16 16:50' )],
        ].each {
            Talk talk = new Talk(title: it[0], presenter: it[1], startsAt: it[2])
            talk.save(failOnError: true)
            talks << talk
        }
        [
                ['Burt Beckwith', 'burt@beckwith.org', 'American'],
                ['Søren Berg Glasius' , 'søren@gr8conf.org', 'Danish'],
                ['Paul King' , 'paul@king.org', 'Australian'],
                ['Roni C. Thomas' , 'roni@TTND.com', 'Indian'],
        ].each {
            new Attendee(name: it[0], email: it[1], nationality: it[2], arrived: false, talks: talks[0..3]).save(failOnError: true)
        }


    }
    def destroy = {
    }
}
