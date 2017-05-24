package beer

import grails.test.mixin.TestFor
import locations.Address
import locations.Brewery
import spock.lang.Specification
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Beer)
class BeerSpec extends Specification {
    def beer

    void 'Test blank name for Beer'() {
        when: 'the name is blank'
        beer = new Beer(name: "", beerStyle: 'fill', beerType: 'fill',
                brewery: new Brewery(name: 'fill',
                        address: new Address(streetAddress: "fill", city: "fill",
                                state: "VA", zipCode: 23226)))

        then: 'validation should fail'
        !beer.validate()
    }

    void 'Test null name for Beer'() {
        when: 'the name is null'
        beer = new Beer(name: null, beerStyle: 'fill', beerType: 'fill',
                brewery: new Brewery(name: 'fill',
                        address: new Address(streetAddress: "fill", city: "fill",
                                state: "VA", zipCode: 23226)))

        then: 'validation should fail'
        !beer.validate()
    }

    void 'Test blank style for Beer'() {
        when: 'the style is blank'
        beer = new Beer(name: "Tasty Beer Yum", beerStyle: '', beerType: 'fill',
                brewery: new Brewery(name: 'fill',
                        address: new Address(streetAddress: "fill", city: "fill",
                                state: "VA", zipCode: 23226)))

        then: 'validation should fail'
        !beer.validate()
    }

    void 'Test null style for Beer'() {
        when: 'the style is null'
        beer = new Beer(name: "Tasty Beer Yum", beerStyle: null, beerType: 'fill',
                brewery: new Brewery(name: 'fill',
                        address: new Address(streetAddress: "fill", city: "fill",
                                state: "VA", zipCode: 23226)))

        then: 'validation should fail'
        !beer.validate()
    }

    void 'Test blank type for beer'() {
        when: 'the type is blank'
        beer = new Beer(name: "Tasty Beer Yum", beerStyle: 'fill', beerType: '',
                brewery: new Brewery(name: 'fill',
                        address: new Address(streetAddress: "fill", city: "fill",
                                state: "VA", zipCode: 23226)))

        then: 'validation should fail'
        !beer.validate()
    }

    void 'Test null type for Beer'() {
        when: 'the type is null'
        beer = new Beer(name: "Tasty Beer Yum", beerStyle: 'fill', beerType: null,
                brewery: new Brewery(name: 'fill',
                        address: new Address(streetAddress: "fill", city: "fill",
                                state: "VA", zipCode: 23226)))

        then: 'validation should fail'
        !beer.validate()
    }

    void 'Test valid Beer'() {
        when: 'the type is anything but null or blank'
        beer = new Beer(name: "Tasty Beer Yum", beerStyle: 'fill', beerType: 'fill',
                brewery: new Brewery(name: 'fill',
                        address: new Address(streetAddress: "fill", city: "fill",
                                state: "VA", zipCode: 23226)))

        then: 'validation should pass'
        beer.validate()
    }
}
