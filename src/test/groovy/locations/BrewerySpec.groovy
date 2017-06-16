package locations

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Brewery)
class BrewerySpec extends Specification {
    def brewery

    def setup() {
        brewery = new Brewery(name: 'Name', lat: 232.232, lng: 232.232,
                address: new Address(streetAddress: "fill", city: "fill",
                        state: "VA", zipCode: 23226))
    }

//     Gotta figure out how to save this damn beer (maybe cant test relationship in here)
//    void "add a new beer and should validate"() {
//        when:
//        def beer = new Beer(name: "Tasty Beer Yum", beerStyle: null, beerType: 'fill')
//
//        then:
//        brewery.addToBeers(beer)
//        brewery.validate()
//    }
}
