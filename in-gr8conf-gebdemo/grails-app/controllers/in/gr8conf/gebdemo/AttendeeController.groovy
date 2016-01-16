package in.gr8conf.gebdemo

import static org.springframework.http.HttpStatus.*

class AttendeeController {

    AttendeeService attendeeService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Attendee.list(params), model:[attendeeCount: Attendee.count()]
    }

    def list() {
        respond Attendee.list(), model:[attendeeCount: Attendee.count()]
    }

    def show(Attendee attendee) {
        respond attendee
    }

    def display(Long id) {
        Attendee attendee = Attendee.get(id)
        respond attendee
    }


    def create() {
        respond new Attendee(params)
    }

    def save(Attendee attendee) {
        Result result = attendeeService.saveAttendee(attendee)
        switch( result.status) {
            case Status.NOT_FOUND:
                notFound()
                break
            case Status.HAS_ERRORS:
                respond result.item.errors, view:'create'
                break
            case Status.OK:
                request.withFormat {
                    form multipartForm {
                        flash.message = message(code: 'default.created.message', args: [message(code: 'attendee.label', default: 'Attendee'), attendee.id])
                        println attendee.dump()
                        redirect attendee
                    }
                    '*' { respond attendee, [status: CREATED] }
                }
                break
        }
    }

    def edit(Attendee attendee) {
        respond attendee
    }

    def update(Attendee attendee) {
        Result result = attendeeService.saveAttendee(attendee)
        switch( result.status) {
            case Status.NOT_FOUND:
                notFound()
                break
            case Status.HAS_ERRORS:
                respond result.item.errors, view:'edit'
                break
            case Status.OK:
                request.withFormat {
                    form multipartForm {
                        flash.message = message(code: 'default.updated.message', args: [message(code: 'attendee.label', default: 'Attendee'), result.item.id])
                        redirect result.item
                    }
                    '*'{ respond result.item, [status: OK] }
                }
                break
        }

    }

    def delete(Attendee attendee) {
        Result result = attendeeService.deleteAttendee(attendee)
        switch( result.status) {
            case Status.NOT_FOUND:
                notFound()
                break
            case Status.OK:
                request.withFormat {
                    form multipartForm {
                        flash.message = message(code: 'default.deleted.message', args: [message(code: 'attendee.label', default: 'Attendee'), result.item])
                        redirect action:"index", method:"GET"
                    }
                    '*'{ render status: NO_CONTENT }
                }
                break
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'attendee.label', default: 'Attendee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
