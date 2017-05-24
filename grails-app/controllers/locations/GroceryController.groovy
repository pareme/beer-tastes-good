package locations

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GroceryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Grocery.list(params), model:[groceryCount: Grocery.count()]
    }

    def show(Grocery grocery) {
        respond grocery
    }

    def create() {
        respond new Grocery(params)
    }

    @Transactional
    def save(Grocery grocery) {
        if (grocery == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (grocery.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond grocery.errors, view:'create'
            return
        }

        grocery.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'grocery.label', default: 'Grocery'), grocery.id])
                redirect grocery
            }
            '*' { respond grocery, [status: CREATED] }
        }
    }

    def edit(Grocery grocery) {
        respond grocery
    }

    @Transactional
    def update(Grocery grocery) {
        if (grocery == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (grocery.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond grocery.errors, view:'edit'
            return
        }

        grocery.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'grocery.label', default: 'Grocery'), grocery.id])
                redirect grocery
            }
            '*'{ respond grocery, [status: OK] }
        }
    }

    @Transactional
    def delete(Grocery grocery) {

        if (grocery == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        grocery.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'grocery.label', default: 'Grocery'), grocery.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'grocery.label', default: 'Grocery'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
