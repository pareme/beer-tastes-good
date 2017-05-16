package firstgrailsapp

import breweries.Address
import breweries.Beer
import breweries.Brewery

class BootStrap {

    def init = { servletContext ->
        def brewery1 = new Brewery(name: 'The Veil Brewing Co.', address: new Address(streetAddress: '1301 Roseneath Rd', city: 'Richmond', state: 'VA', zipCode: '23230')).save()
        new Beer(name: 'Master Shredder', beerType: 'Ale', beerStyle: 'Double IPA', brewery: brewery1).save()
        new Beer(name: 'We Ded Mon', beerType: 'Ale', beerStyle: 'Triple IPA', brewery: brewery1).save()
    }
    def destroy = {
    }
}
