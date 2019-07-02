/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services.implementation;

import edu.esprit.models.Event;
import edu.esprit.models.Session;
import edu.esprit.services.ISessionService;
import edu.esprit.services.ServiceUtils;
import edu.esprit.services.exeptions.ComposedIDExeption;
import edu.esprit.utils.ServiceManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author yjaballi
 */
public class SessionService extends ServiceUtils implements ISessionService {

    @Override
    public List<Session> findByEvent(int id) {
          List<Session> l = new ArrayList<>();
        try {
            ResultSet rs = executeSelect("select * from EVENT_SESSION where  EVENT_SESSION_ID_FK_PK=" + id + " and isdeleted=0");
            while (rs.next()) {
                Session se = new Session(rs.getInt("EVENT_SESSION_ID_FK_PK"),
                        rs.getTimestamp("EVENT_SESSION_START_TIME_PK"), rs.getTimestamp("EVENT_SESSION_END_TIME_PK"), rs.getString("EVENT_SESSION_NAME"),
                        rs.getString("EVENT_SESSION_DESCRIPTION")
                );
                l.add(se);
            }
        } catch (Exception ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public Session find(int id) throws ComposedIDExeption {
        throw new ComposedIDExeption();
    }

    @Override
    public List<Session> findAll() {
        List<Session> l = new ArrayList<>();
        try {
            ResultSet rs = executeSelect("select * from EVENT_SESSION where isdeleted=0");
            while (rs.next()) {
                Session se = new Session(rs.getInt("EVENT_SESSION_ID_FK_PK"),
                        rs.getTimestamp("EVENT_SESSION_START_TIME_PK"), rs.getTimestamp("EVENT_SESSION_END_TIME_PK"), rs.getString("EVENT_SESSION_NAME"),
                        rs.getString("EVENT_SESSION_DESCRIPTION")
                );
                l.add(se);
            }
        } catch (Exception ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean create(Session obj) {
        String sql = "insert into EVENT_SESSION(`EVENT_SESSION_ID_FK_PK`,"
                + "`EVENT_SESSION_START_TIME_PK`,"
                + "`EVENT_SESSION_END_TIME_PK`,"
                + "`EVENT_SESSION_NAME`,"
                + "`ISDELETED`)"
                + "values ("
                + obj.getEventId()
                + ",'" + obj.getStartTime()
                + "','" + obj.getEndTime()
                + "','" + obj.getName()
                + "',0"
                + ")";
        System.out.println(sql);
        return execute(sql);
    }

    @Override
    public boolean edit(Session obj) {
        // a vérifier
        String req = "UPDATE event_comment"
                + "SET"
                + "`EVENT_SESSION_START_TIME_PK` = '" + obj.getStartTime()
                + "`EVENT_SESSION_END_TIME_PK` = '" + obj.getEndTime()
                + "`EVENT_SESSION_NAME VARCHAR` = '" + obj.getName()
                + "' WHERE `EVENT_SESSION_ID_FK_PK` ='" + obj.getEventId() + "' and EVENT_SESSION_START_TIME_PK = '" + obj.getStartTime() + "' and EVENT_SESSION_END_TIME_PK= '" + obj.getEndTime() + "'";

        return execute(req);
    }

    @Override
    public boolean delete(Session obj) {
        // a vérifier
        return execute("update event_comment set isdeleted='1' WHERE `EVENT_SESSION_ID_FK_PK` ='" + obj.getEventId() + "' and EVENT_SESSION_START_TIME_PK = '" + obj.getStartTime() + "' and EVENT_SESSION_END_TIME_PK= '" + obj.getEndTime());
    }

}
