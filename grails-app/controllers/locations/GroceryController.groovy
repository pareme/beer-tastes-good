package locations

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GrocerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Grocer.list(params), model:[grocerCount: Grocer.count()]
    }

    def show(Grocer grocer) {
        respond grocer
    }

    def create() {
        respond new Grocer(params)
    }

    @Transactional
    def save(Grocer grocer) {
        if (grocer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (grocer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond grocer.errors, view:'create'
            return
        }

        grocer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'grocer.label', default: 'Grocer'), grocer.id])
                redirect grocer
            }
            '*' { respond grocer, [status: CREATED] }
        }
    }

    def edit(Grocer grocer) {
        respond grocer
    }

    @Transactional
    def update(Grocer grocer) {
        if (grocer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (grocer.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond grocer.errors, view:'edit'
            return
        }

        grocer.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'grocer.label', default: 'Grocer'), grocer.id])
                redirect grocer
            }
            '*'{ respond grocer, [status: OK] }
        }
    }

    @Transactional
    def delete(Grocer grocer) {

        if (grocer == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        grocer.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'grocer.label', default: 'Grocer'), grocer.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'grocer.label', default: 'Grocer'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
