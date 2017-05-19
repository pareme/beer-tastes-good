package breweries


class Brewery {
    String name

    static  hasMany = [beer:Beer]
    static  hasOne = [address:Address]

    static constraints = {
        name blank: false, unique: true
    }
}
