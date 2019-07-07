/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.models;

import java.util.List;

/**
 *
 * @author azer
 */
public class Event extends Reportable {

    private int id;
    private String title;
    private String description;
    private String photoURL;
    private User organisator;
    private Location location;
    private Category category;
    private double price;
    private List<Rating> ratings;

    
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    private List<Participation> participations;
    private List<Session> sessions;
    private List<Comment> comments;

    public Event(int id, String title, String description, String photoURL, User organisator, Location location, Category category, double price, List<Rating> ratings, List<Participation> participations, List<Session> sessions, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photoURL = photoURL;
        this.organisator = organisator;
        this.location = location;
        this.category = category;
        this.price = price;
        this.ratings = ratings;
        this.participations = participations;
        this.sessions = sessions;
        this.comments = comments;
    }

    public Event(String title, String description, String photoURL, User organisator, Location location, Category category, double price, List<Rating> ratings, List<Participation> participations, List<Session> sessions, List<Comment> comments) {
        this.title = title;
        this.description = description;
        this.photoURL = photoURL;
        this.organisator = organisator;
        this.location = location;
        this.category = category;
        this.price = price;
        this.ratings = ratings;
        this.participations = participations;
        this.sessions = sessions;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public User getOrganisator() {
        return organisator;
    }

    public void setOrganisator(User organisator) {
        this.organisator = organisator;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Participation> getParticipations() {
        return participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public double getRating() {
        return ratings.stream().mapToDouble(r -> r.getRate()).average().getAsDouble();
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", title=" + title + ", description=" + description + ", photoURL=" + photoURL + ", organisator=" + organisator + ", locationID=" + location + ", categoryID=" + category + ", price=" + price + ", ratings=" + ratings + ", participations=" + participations + ", sessions=" + sessions + '}';
    }

}
