package breweries

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Address)
class AddressSpec extends Specification {
    def address

    void 'Test valid address'() {
        when: 'the street address is neither null nor blank'
        address = new Address(streetAddress: "fill", city: "fill",
                state: "VA", zipCode: "23226")

        then: 'the validation should fail'
        address.validate()
    }

    void 'Test null for street address'() {
        when: 'the street address is null'
        address = new Address(streetAddress: null, city: "fill",
                state: "VA", zipCode: "23226")

        then: 'the validation should fail'
        !address.validate()
    }

    void 'Test blank for street address'() {
        when: 'the street address is blank'
        address = new Address(streetAddress: "", city: "fill",
                state: "VA", zipCode: "23226")

        then: 'the validation should fail'
        !address.validate()
    }

    void 'Test null for city'() {
        when: 'the city is null'
        address = new Address(streetAddress: 'fill', city: null,
                state: "VA", zipCode: "23226")

        then: 'the validation should fail'
        !address.validate()
    }

    void 'Test blank for city'() {
        when: 'the city is blank'
        address = new Address(streetAddress: "", city: "",
                state: "VA", zipCode: "23226")

        then: 'the validation should fail'
        !address.validate()
    }

    void 'Test null for state'() {
        when: 'the state is null'
        address = new Address(streetAddress: 'fill', city: 'fill',
                state: null, zipCode: "23226")

        then: 'the validation should fail'
        !address.validate()
    }

    void 'Test blank for state'() {
        when: 'the city is blank'
        address = new Address(streetAddress: "", city: "fill",
                state: "", zipCode: "23226")

        then: 'the validation should fail'
        !address.validate()
    }

    void 'Test Size Constraint for state'() {
        when: 'the city is blank'
        address = new Address(streetAddress: "", city: "fill",
                state: "VAA", zipCode: "23226")

        then: 'the validation should fail'
        !address.validate()
    }

    void 'Test null for zipCode'() {
        when: 'the zipCode is null'
        address = new Address(streetAddress: 'fill', city: 'fill',
                state: "VA", zipCode: null)

        then: 'the validation should fail'
        !address.validate()
    }

    void 'Test blank for zipCode'() {
        when: 'the city is blank'
        address = new Address(streetAddress: "fill", city: "fill",
                state: "VA", zipCode: "")

        then: 'the validation should fail'
        !address.validate()
    }

    void 'Test Size Constraint for zipCode'() {
        when: 'the city is blank'
        address = new Address(streetAddress: "fill", city: "fill",
                state: "VA", zipCode: "2322222")

        then: 'the validation should fail'
        !address.validate()
    }

    void 'Test Character Constraint for zipCode'() {
        when: 'the city is blank'
        address = new Address(streetAddress: "fill", city: "fill",
                state: "VA", zipCode: "2322L")

        then: 'the validation should fail'
        !address.validate()
    }
}
