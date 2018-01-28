package com.banditos.server.model;

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

    @Override
    public String toString() {
        return name;
    }
}
