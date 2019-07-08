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
public class Comment extends Reportable {

    private int id;
    private int eventId;
    private User User;
    private String body;
    private List<Comment> Childcomments ;

    public List<Comment> getChildcomments() {
        return Childcomments;
    }

    public void setChildcomments(List<Comment> Childcomments) {
        this.Childcomments = Childcomments;
    }

    public Comment(int id, int eventId, User User, String body) {
        this.id = id;
        this.eventId = eventId;
        this.User = User;
        this.body = body;
    }

    public Comment(int eventId, User User, String body) {
        this.eventId = eventId;
        this.User = User;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", event=" + eventId + ", User=" + User + ", body=" + body + '}';
    }

}
