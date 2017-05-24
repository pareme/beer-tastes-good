package locations

import beer.Beer


class Brewery extends Location {

    static hasMany = [beer: Beer]

    static constraints = {
        name unique = true
    }
}
