package locations

import beer.GeoLocationService
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class BreweryController {
    GeoLocationService geoLocationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Brewery.list(params), model: [breweryCount: Brewery.count()]
    }

    def show(Brewery brewery) {
        if (brewery.lng == null) {
            brewery = geoLocationService.getGeoLocationFromAddress(brewery)
            brewery.save()
        }
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

//        def results = [:]
//        if(brewery.address == null) {
//            results = geoLocationService.getGeoLocationFromName(brewery.name)
//        } else {
//            results = geoLocationService.getGeoLocationFromAddress(brewery.address)
//        }


        if (brewery.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond brewery.errors, view: 'create'
            return
        }

        brewery.save flush: true

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
            respond brewery.errors, view: 'edit'
            return
        }

        brewery.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'brewery.label', default: 'Brewery'), brewery.id])
                redirect brewery
            }
            '*' { respond brewery, [status: OK] }
        }
    }

    @Transactional
    def delete(Brewery brewery) {

        if (brewery == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        brewery.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'brewery.label', default: 'Brewery'), brewery.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'brewery.label', default: 'Brewery'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
