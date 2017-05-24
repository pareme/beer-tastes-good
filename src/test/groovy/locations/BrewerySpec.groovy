package locations

import grails.test.mixin.TestFor
import spock.lang.Specification
/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Brewery)
class BrewerySpec extends Specification {
    def brewery

    void 'Test valid Brewery'() {
        when: 'the name is anything but null or blank'
        brewery = new Brewery(name: 'Name',
                address: new Address(streetAddress: "fill", city: "fill",
                        state: "VA", zipCode: 23226))

        then: 'validation should pass'
        brewery.validate()
    }

    void 'Test null name for Brewery'() {
        when: 'the name is null'
        brewery = new Brewery(name: null,
                              address: new Address(streetAddress: "fill", city: "fill",
                                                   state: "VA", zipCode: 23226))

        then: 'validation should fail'
        !brewery.validate()
    }
    void 'Test blank name for Brewery'() {
        when: 'the name is blank'
        brewery = new Brewery(name: '',
                address: new Address(streetAddress: "fill", city: "fill",
                        state: "VA", zipCode: 23226))

        then: 'validation should fail'
        !brewery.validate()
    }

    void 'Test null address for Brewery'() {
        when: 'the address is null'
        brewery = new Brewery(name: 'name', address: null)

        then: 'validation should fail'
        !brewery.validate()
    }
}
