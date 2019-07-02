/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services.implementation;

import edu.esprit.models.Rating;
import edu.esprit.models.Session;
import edu.esprit.services.IRatingService;
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
public class RatingService extends ServiceUtils implements IRatingService {

    @Override
    public List<Rating> findByEvent(int id) {
        List<Rating> l = new ArrayList<>();
        try {
            ResultSet rs = executeSelect("select * from EVENT_RATING where EVENT_ID_FK_PK="+id+" and isdeleted=0");
            while (rs.next()) {
                Rating ra = new Rating(rs.getInt("USER_ID_FK_PK"),
                        rs.getInt("EVENT_ID_FK_PK"), rs.getDouble("EVENT_RATE")
                );
                l.add(ra);
            }
        } catch (Exception ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public Rating find(int id) throws ComposedIDExeption {
        throw new ComposedIDExeption();
    }

    @Override
    public List<Rating> findAll() {
        List<Rating> l = new ArrayList<>();
        try {
            ResultSet rs = executeSelect("select * from EVENT_RATING where isdeleted=0");
            while (rs.next()) {
                Rating ra = new Rating(rs.getInt("USER_ID_FK_PK"),
                        rs.getInt("EVENT_ID_FK_PK"), rs.getDouble("EVENT_RATE")
                );
                l.add(ra);
            }
        } catch (Exception ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean create(Rating obj) {
        String sql = "insert into EVENT_RATING (`USER_ID_FK_PK`,"
                + "`EVENT_ID_FK_PK`,"
                + "`EVENT_RATE`,"
                + "`ISDELETED`)"
                + "values ("
                + obj.getUserId()
                + "," + obj.getEventId()
                + "," + obj.getRate()
                + "',0"
                + ")";

        return execute(sql);
    }

    @Override
    public boolean edit(Rating obj) {
        String req = "UPDATE EVENT_RATING"
                + "SET"
                + "`EVENT_RATE` = '" + obj.getRate()
                + "' WHERE `USER_ID_FK_PK` ='" + obj.getUserId() + "' and EVENT_ID_FK_PK = '" + obj.getEventId() + "'";

        return execute(req);
    }

    @Override
    public boolean delete(Rating obj) {
        return execute("update event_comment set isdeleted='1' WHERE `USER_ID_FK_PK` ='" + obj.getUserId() + "' and EVENT_ID_FK_PK = '" + obj.getEventId());
    }

}
