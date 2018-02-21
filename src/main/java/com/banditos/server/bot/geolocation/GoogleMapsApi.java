package com.banditos.server.bot.geolocation;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author xk
 * Class for workaround with Google Maps API.
 * Use this methods when need to evaluate location of Place
 * or return its coordinates to end user.
 */

@Component
public class GoogleMapsApi {

    private static GeoApiContext geoApiContext;

    /**
     * Constructor. Sets token for
     * @see GoogleMapsApi#geoApiContext
     * This object used for making requests.
     * @param env used for getting Google Maps API token from application.properties.
     */
    @Autowired
    public GoogleMapsApi(Environment env) {
        String token = env.getProperty("token.google");
        geoApiContext = new GeoApiContext.Builder()
                .apiKey(token)
                .build();
    }

    /**
     * The method used for getting address string by coordinates.
     * @param location an object which represents coordinates.
     *                 @see com.google.maps.model.LatLng
     * @return address string.
     */
    public static String getAddressByCoordinates(LatLng location) {
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.reverseGeocode(geoApiContext, location).await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // note that results variable is an array. results[0] is the most valid result.
        return results[0].formattedAddress;
    }

    /**
     * The method used to find coordinates by address. Ofc address should be valid. Nice and easy.
     * @param address String representing address
     * @return LatLng location
     */
    public static LatLng getCoordinatesByAddress(String address) {
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(geoApiContext, address).await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results[0].geometry.location;
    }
}
