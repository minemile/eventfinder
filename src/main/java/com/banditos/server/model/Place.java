package com.banditos.server.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="place")
public class Place {
    @Id
    private Long id;
    private String name;
    private String address;
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
}
