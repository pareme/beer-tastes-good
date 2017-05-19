package beerisgood

import breweries.Brewery

class HomeController {

    def index() {
        respond([name: session.name ?: 'User', breweryTotal: Brewery.count()])
    }

    def updateName(String name) {
        session.name = name

        flash.message = "Name has been updated"

        redirect action: 'index'
    }
}
