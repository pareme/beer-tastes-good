package beer

import locations.Address
import org.springframework.beans.factory.annotation.Value


class GeoLocationService {
    boolean transactional = true

    @Value('${apikeys.google.maps}')
    String apiKey

    @Value('${endpoint.google.maps.address}')
    String addressURL

    @Value('${endpoint.google.maps.name}')
    String nameURL

    def getGeoLocationFromAddress(Address address) {
//        def restBuilder = new Rest


    }

    def getGeoLocationFromName(String name) {

    }
}
