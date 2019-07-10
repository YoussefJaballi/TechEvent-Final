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
public class Report {

    private int id;
    private User reporterId;
    private String body;
    private int targetId;
    private String targettype;

    public Report(int id, User reporterId, String body, int targetId, String targettype) {
        this.id = id;
        this.reporterId = reporterId;
        this.body = body;
        this.targetId = targetId;
        this.targettype = targettype;
    }

    public Report(User reporterId, String body, int targetId, String targettype) {
        this.reporterId = reporterId;
        this.body = body;
        this.targetId = targetId;
        this.targettype = targettype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getReporterId() {
        return reporterId;
    }

    public void setReporterId(User reporterId) {
        this.reporterId = reporterId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getTargettype() {
        return targettype;
    }

    public void setTargettype(String targettype) {
        this.targettype = targettype;
    }

    @Override
    public String toString() {
        return "Report{" + "id=" + id + ", reporterId=" + reporterId + ", body=" + body + ", targetId=" + targetId + ", targettype=" + targettype + '}';
    }

}
