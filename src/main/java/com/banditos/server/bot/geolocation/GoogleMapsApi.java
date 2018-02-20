package com.banditos.server.bot.geolocation;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class GoogleMapsApi {

    private static GeoApiContext geoApiContext;

    @Autowired
    public GoogleMapsApi(Environment env) {
        String token = env.getProperty("token.google");
        geoApiContext = new GeoApiContext.Builder()
                .apiKey(token)
                .build();
    }

    public static String getAddressByCoordinates(LatLng location) {
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.reverseGeocode(geoApiContext, location).await();
            //Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results[0].formattedAddress;
    }

    public static LatLng getCoordinatesByAddress(String address) {
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(geoApiContext, address).await();
            //Thread.sleep(800);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results[0].geometry.location;
    }
}
