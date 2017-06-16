package locations

class Location {

    String name
    Double lat
    Double lng

    static hasOne = [address: Address]

    static constraints = {
        name blank: false
    }
}
