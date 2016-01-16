package in.gr8conf.gebdemo

import grails.transaction.Transactional

@Transactional
class AttendeeService {

    Result saveAttendee(Attendee attendee) {
        if (attendee == null) {
            transactionStatus.setRollbackOnly()
            return new Result(status: Status.NOT_FOUND)
        }

        if (attendee.hasErrors()) {
            transactionStatus.setRollbackOnly()
            return new Result(status: Status.HAS_ERRORS, item: attendee)
        }

        attendee.save flush:true
        return new Result(status: Status.OK, item: attendee)
    }

    Result deleteAttendee(Attendee attendee) {
        if (attendee == null) {
            return new Result(status: Status.NOT_FOUND)
        }

        attendee.delete flush:true
        return new Result(status: Status.OK, item: attendee.id)
    }

}
