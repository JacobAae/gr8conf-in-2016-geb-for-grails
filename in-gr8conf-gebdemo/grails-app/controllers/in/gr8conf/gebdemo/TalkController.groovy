package in.gr8conf.gebdemo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TalkController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Talk.list(params), model:[talkCount: Talk.count()]
    }

    def show(Talk talk) {
        respond talk
    }

    def create() {
        respond new Talk(params)
    }

    @Transactional
    def save(Talk talk) {
        if (talk == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (talk.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond talk.errors, view:'create'
            return
        }

        talk.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'talk.label', default: 'Talk'), talk.id])
                redirect talk
            }
            '*' { respond talk, [status: CREATED] }
        }
    }

    def edit(Talk talk) {
        respond talk
    }

    @Transactional
    def update(Talk talk) {
        if (talk == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (talk.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond talk.errors, view:'edit'
            return
        }

        talk.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'talk.label', default: 'Talk'), talk.id])
                redirect talk
            }
            '*'{ respond talk, [status: OK] }
        }
    }

    @Transactional
    def delete(Talk talk) {

        if (talk == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        talk.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'talk.label', default: 'Talk'), talk.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'talk.label', default: 'Talk'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
