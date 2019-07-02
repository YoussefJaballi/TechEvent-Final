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
public class RoleParticipation {

    private int id;
    private String description;

    public RoleParticipation(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public RoleParticipation(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoleParticipation{" + "id=" + id + ", description=" + description + '}';
    }

}
