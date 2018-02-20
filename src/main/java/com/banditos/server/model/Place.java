package com.banditos.server.model;

import org.telegram.telegrambots.api.objects.Venue;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="place")
public class Place {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @Column(length = 2000)
    private String description;

    @OneToMany(mappedBy = "place")
    private List<Tusovka> tusovkas;

    private Float latitude;

    private Float longitude;

    public Place() {
    }


    public Place(String name, String address, String description, List<Tusovka> tusovkas, Float latitude, Float longitude) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.tusovkas = tusovkas;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tusovka> getTusovkas() {
        return tusovkas;
    }

    public void setTusovkas(List<Tusovka> tusovkas) {
        this.tusovkas = tusovkas;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return name;
    }
}
