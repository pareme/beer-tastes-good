package beerisgood

import locations.Address
import beer.Beer
import locations.Brewery

class BootStrap {

    def init = { servletContext ->
        def brewery1 = new Brewery(name: 'The Veil Brewing Co.', address: new Address(streetAddress: '1301 Roseneath Rd', city: 'Richmond', state: 'VA', zipCode: '23230')).save()
        new Beer(name: 'Master Shredder', type: 'Ale', style: 'Double IPA', brewery: brewery1).save()
        new Beer(name: 'We Ded Mon', type: 'Ale', style: 'Triple IPA', brewery: brewery1).save()
        def brewery2 = new Brewery(name: 'Triple Crossing Brewing', address: new Address(streetAddress: '5203 Hatcher Str', city: 'Richmond', state: 'VA', zipCode: '23231')).save()
        new Beer(name: 'Clever Girl', type: 'Ale', style: 'IPA', brewery: brewery2).save()
        new Beer(name: 'Falcon Smash', type: 'Ale', style: 'IPA', brewery: brewery2).save()
    }
    def destroy = {
    }
}
