package locations

class Grocery extends Location {

    static hasMany = [address: Address]

    static constraints = {
    }
}
