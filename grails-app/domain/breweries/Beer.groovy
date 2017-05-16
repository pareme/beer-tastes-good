package breweries

class Beer {
    String name
    String beerType
    String beerStyle
    Brewery brewery

    static constraints = {
        name blank: false
        beerType blank: false
        beerStyle blank: false
        brewery blank: false
    }
}
