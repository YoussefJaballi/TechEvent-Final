/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services.implementation;

import edu.esprit.models.Report;
import edu.esprit.services.IReportService;
import edu.esprit.services.ServiceUtils;
import edu.esprit.services.exeptions.ComposedIDExeption;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Djo
 */
public class ReportService extends ServiceUtils implements IReportService {

    @Override
    public Report find(int id) throws ComposedIDExeption {
        Report c = null;
        try {
            ResultSet rs = executeSelect("select * from report where  REPORT_ID_PK=" + id + " and isdeleted=0");
            while (rs.next()) {
                c = new Report(rs.getInt("REPORT_ID_PK"),
                        rs.getInt("REPORTER_ID_FK"),
                        rs.getString("REPORT_BODY"),
                        rs.getInt("REPORT_TARGET_ID"),
                        rs.getString("REPORT_TARGET"));
            }
        } catch (Exception ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;

    }

    @Override
    public List<Report> findAll() {
        List<Report> reports = new ArrayList<Report>();
        try {
            ResultSet rs = executeSelect("select * from report where `isdeleted`=0");

            while (rs.next()) {
                Report reportevent = new Report(rs.getInt("REPORT_ID_PK"),
                        rs.getInt("REPORTER_ID_FK"),
                        rs.getString("REPORT_BODY"),
                        rs.getInt("REPORT_TARGET_ID"),
                        rs.getString("REPORT_TARGET"));
                reports.add(reportevent);

            }

        } catch (Exception ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reports;
    }

    @Override
    public boolean create(Report obj) {
        String req = "insert into `teck_event`.`report` (`REPORTER_ID_FK`"
                + ",`REPORT_BODY`,`REPORT_TARGET_ID`,"
                + "`REPORT_TARGET`)"
                + "Values("
                + obj.getReporterId()
                + "," + obj.getBody()
                + "," + obj.getTargetId()
                + ",'" + obj.getTargettype()
                + ");";
        return execute(req);
    }

    @Override
    public boolean edit(Report obj) {
        String req = "UPDATE report "
                + "SET "
                + " `REPORTER_ID_FK` =" + obj.getReporterId() + ","
                + " `REPORT_BODY` ='" + obj.getBody() + "',"
                + " `REPORT_TARGET` = '" + obj.getTargettype() + "',"
                + " `REPORT_TARGET_ID` = " + obj.getTargetId()
                + " WHERE `REPORT_ID_PK`=" + obj.getId() + ";";
        System.out.println(req);
        return execute(req);
    }

    @Override
    public boolean delete(Report obj) {
        return execute("UPDATE `teck_event`.`report` SET isdeleted='1' where REPORT_ID_PK=" + obj.getId() + ";");
    }

    @Override
    public List<Report> findByTarget(String targettype) {
        List<Report> o = findAll().stream()
                .filter(c -> c.getClass().getName().equals(targettype))
                .collect(Collectors.toList());

        return o;
    }

    @Override
    public List<Report> findByEvent(int id) {
        return findAll().stream()
                .filter(c -> c.getTargettype().equals("event") && c.getTargetId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> findByUser(int id) {
        return findAll().stream()
                .filter(c -> c.getTargettype().equals("user") && c.getReporterId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> findByComment(int id) {
        return findAll().stream()
                .filter(r -> r.getTargettype().equals("comment") && r.getId() == id)
                .collect(Collectors.toList());
    }

}
