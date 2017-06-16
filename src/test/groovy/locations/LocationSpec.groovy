package locations

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Location)
class LocationSpec extends Specification {
    def location

    void 'Test valid Location'() {
        when: 'the name is anything but null or blank'
        location = new Location(name: 'Name', lat: 232.232, lng: 232.232,
                address: new Address(streetAddress: "fill", city: "fill",
                        state: "VA", zipCode: 23226))

        then: 'validation should pass'
        location.validate()
    }

    void 'Test null name for Location'() {
        when: 'the name is null'
        location = new Location(name: null, lat: 232.232, lng: 232.232,
                address: new Address(streetAddress: "fill", city: "fill",
                        state: "VA", zipCode: 23226))

        then: 'validation should fail'
        !location.validate()
    }
    void 'Test blank name for Location'() {
        when: 'the name is blank'
        location = new Location(name: '', lat: 232.232, lng: 232.232,
                address: new Address(streetAddress: "fill", city: "fill",
                        state: "VA", zipCode: 23226))

        then: 'validation should fail'
        !location.validate()
    }

    void 'Test null address for Location'() {
        when: 'the address is null'
        location = new Location(name: 'name', address: null, lat: 232.232, lng: 232.232,)

        then: 'validation should fail'
        !location.validate()
    }
}
