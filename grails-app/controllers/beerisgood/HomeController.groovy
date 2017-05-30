package beerisgood

import locations.Brewery
import locations.Grocer

class HomeController {

    def index() {
        respond([name        : session.name ?: 'User',
                 breweryTotal: Brewery.count(),
                 breweryList : Brewery.list(),
                 grocerTotal : Grocer.count(),
                 grocerList  : Grocer.list()
        ])
    }

    def updateName(String name) {
        session.name = name

        flash.message = "Name has been updated"

        redirect action: 'index'
    }
}
