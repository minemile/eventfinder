package com.banditos.server.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tusovka")
public class Tusovka {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private Date date;
    private String description;
    private String place;
    private int price;

    protected Tusovka() {}

    public Tusovka(Date date, String name, String description, String place, int price) {
        this.date = date;
        this.name = name;
        this.description = description;
        this.place = place;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }
}
