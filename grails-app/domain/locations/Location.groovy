package locations

class Location {

    String name
    String lat
    String lon

    static hasOne = [address: Address]

    static constraints = {
        name blank: false
    }
}
