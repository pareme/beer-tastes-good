package locations

import beer.GeoLocationService
import grails.test.mixin.TestFor
import spock.lang.Ignore
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(GeoLocationService)
class GeoLocationServiceSpec extends Specification {
    def geoLocationService
    def brewery = new Brewery(name: 'The Veil Brewing Co.', address: new Address(streetAddress: '1301 Roseneath Rd', city: 'Richmond', state: 'VA', zipCode: '23230'))

    @Ignore
    void "Integration Test for veil"() {
        setup:
        geoLocationService = new GeoLocationService()

        when:
        brewery = geoLocationService.getGeoLocationFromAddress(brewery)

        then:
        brewery.lat == 37.568317
        brewery.lng == -77.47534139999999
    }
}
