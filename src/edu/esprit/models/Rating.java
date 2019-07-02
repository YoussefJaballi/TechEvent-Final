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
public class Rating {

    private int userId;
    private int eventId;
    private double rate;

    public Rating(int userId, int eventId, double rate) {
        this.userId = userId;
        this.eventId = eventId;
        this.rate = rate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user) {
        this.userId = user;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int event) {
        this.eventId = event;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rating{" + "user=" + userId + ", event=" + eventId + ", rate=" + rate + '}';
    }

}
