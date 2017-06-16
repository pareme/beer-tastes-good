package beer

import com.budjb.httprequests.HttpRequest
import com.budjb.httprequests.jersey1.JerseyHttpClientFactory
import groovy.json.JsonSlurper
import locations.Address
import locations.Location
import org.springframework.beans.factory.annotation.Value

import javax.xml.ws.http.HTTPException

class GeoLocationService {
    boolean transactional = true

//    @Value('${apikeys.google.maps}')
    String apiKey = 'AIzaSyDnN-S05ieYO1w2Na2fltMvGLLIAyzqWZo';

//    @Value('${endpoint.google.maps.address}')
    String addressURL = 'https://maps.googleapis.com/maps/api/geocode/json'

    @Value('${endpoint.google.maps.name}')
    String nameURL

    def getGeoLocationFromAddress(Location location) {
        try {
            def responseJson = executeGeoLocationRequest(location.address)

            if (responseJson['status'] == 'OK') {
                location.lat = Double.valueOf(responseJson.results.geometry.location.lat.get(0))
                location.lng = Double.valueOf(responseJson.results.geometry.location.lng.get(0))
            } else {
                log.info(responseJson['error_message'])
            }
        } catch (HTTPException e) {
            log.info("Call to GeoLocation failed {$e.getLocalizedMessage()}")
        } catch (Exception e) {
            log.info($e.getLocalizedMessage())
        }
        return location
    }

    private Object executeGeoLocationRequest(Address address) {
        def request = HttpRequest.build {
            uri = addressURL
            accept = 'application/json'
            addQueryParameter('address', address.toString())
            addQueryParameter('key', apiKey)
        }

        def client = new JerseyHttpClientFactory().createHttpClient()

        def response = client.get(request)
        if (response.hasEntity()) {
            return new JsonSlurper().parseText(response.getEntity(String.class))
        } else {
            throw new HTTPException(response.getStatus())
        }
    }

//    Going to need to call google place api to get location based off of name (hoping untappd passes addresses)
//    def getGeoLocationFromName(String name) {
//        def request = HttpRequest.build {
//            uri = addressURL
//            accept = 'application/json'
//            addQueryParameter('address', address)
//            addQueryParameter('key', apiKey)
//        }
//
//        def client = new JerseyHttpClientFactory().createHttpClient()
//
//        def responseJson = new JsonSlurper().parseText(client.get(request))
//
//        if (responseJson['status'].equals('OK')) {
//            address.location.lat = responseJson['results']['geometry']['location']['lat']
//            address.location.lng = responseJson['results']['geometry']['location']['lng']
//            address.save()
//        } else {
//            responseJson['error_message']
//            System
//        }
//
//        return address
//    }
}
