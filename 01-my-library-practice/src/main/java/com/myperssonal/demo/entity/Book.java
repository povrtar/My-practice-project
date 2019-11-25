package com.myperssonal.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String autorFirstName;

    @Column(name = "last_name")
    private String autorLastName;

    @Column(name = "title")
    private String title;

    @Column(name = "field")
    private String sector;

    @Column(name = "unit_strength")
    private int unitStrength;

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutorFirstName() {
        return autorFirstName;
    }

    public void setAutorFirstName(String autorFirstName) {
        this.autorFirstName = autorFirstName;
    }

    public String getAutorLastName() {
        return autorLastName;
    }

    public void setAutorLastName(String autorLastName) {
        this.autorLastName = autorLastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getUnitStrength() {
        return unitStrength;
    }

    public void setUnitStrength(int unitStrength) {
        this.unitStrength = unitStrength;
    }

}
