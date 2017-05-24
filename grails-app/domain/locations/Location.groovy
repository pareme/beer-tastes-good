package locations

class Location {
    String name

    static hasOne = [address: Address]

    static constraints = {
        name blank: false
    }
}
