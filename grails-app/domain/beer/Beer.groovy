package beer

import locations.Brewery

class Beer {
    String name
    String type
    String style
    Brewery brewery

    static constraints = {
        name blank: false
        type blank: false
        style blank: false
        brewery blank: false
    }
}
