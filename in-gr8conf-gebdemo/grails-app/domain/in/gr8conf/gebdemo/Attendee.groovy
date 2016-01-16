package in.gr8conf.gebdemo

class Attendee {

    String name
    String email
    String nationality
    Boolean arrived = false

    Date dateCreated
    Date lastUpdated

    static hasMany = [talks: Talk]

    static constraints = {
        name blank: false
        email blank: false, unique: true, email: true
        nationality nullable: true
        arrived nullable: true
    }

    @Override
    String toString() {
        name
    }

}
