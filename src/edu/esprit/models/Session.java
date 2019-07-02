/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.models;

import java.util.Date;

/**
 *
 * @author azer
 */
public class Session {

    private int eventId;
    private Date startTime;
    private Date endTime;
    private String name;
    private String description;

    public Session(int eventId, Date startTime, Date endTime, String name, String description) {
        this.eventId = eventId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        this.description = description;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Session{" + "event=" + eventId + ", startTime=" + startTime + ", endTime=" + endTime + ", name=" + name + ", description=" + description + '}';
    }

}
