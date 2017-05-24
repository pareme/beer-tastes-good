package locations

import locations.Brewery

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BreweryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Brewery.list(params), model:[breweryCount: Brewery.count()]
    }

    def show(Brewery brewery) {
        respond brewery
    }

    def create() {
        respond new Brewery(params)
    }

    @Transactional
    def save(Brewery brewery) {
        if (brewery == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (brewery.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond brewery.errors, view:'create'
            return
        }

        brewery.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'brewery.label', default: 'Brewery'), brewery.id])
                redirect brewery
            }
            '*' { respond brewery, [status: CREATED] }
        }
    }

    def edit(Brewery brewery) {
        respond brewery
    }

    @Transactional
    def update(Brewery brewery) {
        if (brewery == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (brewery.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond brewery.errors, view:'edit'
            return
        }

        brewery.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'brewery.label', default: 'Brewery'), brewery.id])
                redirect brewery
            }
            '*'{ respond brewery, [status: OK] }
        }
    }

    @Transactional
    def delete(Brewery brewery) {

        if (brewery == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        brewery.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'brewery.label', default: 'Brewery'), brewery.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'brewery.label', default: 'Brewery'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
