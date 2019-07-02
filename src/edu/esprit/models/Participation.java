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
public class Participation {

    private int userId;
    private String userName;
    private int eventId;
    private RoleParticipation role;

    public Participation(int user, int eventId, RoleParticipation role) {
        this.userId = user;
        this.eventId = eventId;
        this.role = role;
    }

    public Participation(int eventId, RoleParticipation role) {
        this.eventId = eventId;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public RoleParticipation getRole() {
        return role;
    }

    public void setRole(RoleParticipation role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Participation{" + "user=" + userId + ", event=" + eventId + ", role=" + role + '}';
    }

}
