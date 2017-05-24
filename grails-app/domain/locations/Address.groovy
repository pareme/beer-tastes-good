package locations

class Address {
    String streetAddress
    String city
    String state
    String zipCode
    Location location

    static constraints = {
        streetAddress blank: false
        city blank: false
        state blank: false, size: 2..2
        zipCode blank: false, size: 5..5, matches: '[0-9]+'
    }


    String toString(){
        return "$streetAddress, $city, $state $zipCode"
    }
}
