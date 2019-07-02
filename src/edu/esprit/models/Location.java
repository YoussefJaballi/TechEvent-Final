/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.models;

/**
 *
 * @author azer
 */
public class Location {

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private double zipCode;

    public Location(int id, String name, double latitude, double longitude, double zipCode) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipCode = zipCode;
    }

    public Location(String name, double latitude, double longitude, double zipCode) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zipCode = zipCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getZipCode() {
        return zipCode;
    }

    public void setZipCode(double zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", zipCode=" + zipCode + '}';
    }

}
