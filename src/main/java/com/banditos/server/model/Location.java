package com.banditos.server.model;

import com.banditos.server.bot.geolocation.GoogleMapsApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.LatLng;

import java.io.IOException;

public class Location {
    private LatLng latLng;

    private String address;

    public Location(double latitude, double longitude) throws InterruptedException, ApiException, IOException {
        latLng = new LatLng(latitude, longitude);
        this.address = GoogleMapsApi.getAddressByCoordinates(latLng);
    }

    public Location(String address) {
        this.address = address;
        // TODO: this.latLng = GoogleMapsApi.getCoordByAddr(String address);
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
