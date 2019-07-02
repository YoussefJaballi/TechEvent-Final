/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services.implementation;

import edu.esprit.models.Event;
import edu.esprit.models.Participation;
import edu.esprit.services.IEventService;
import edu.esprit.services.IParticipationService;
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
 * @author abkhaldi
 */
public class ParticipationService extends ServiceUtils implements IParticipationService {

    @Override
    public Participation find(int id) throws ComposedIDExeption {
        throw new ComposedIDExeption();
    }

    @Override
    public List<Participation> findAll() {
        List<Participation> l = new ArrayList<>();
        try {
            ResultSet rs = executeSelect("select * from event_participation where isdeleted=0");
            while (rs.next()) {
                Participation p = new Participation(
                        rs.getInt("USER_ID_FK_PK"),
                        rs.getInt("EVENT_ID_FK_PK"),
                        ServiceManager.getInstance().getRoleParticipationService().find(rs.getInt("ROLE_PARTICIPATION_FK"))
                );
                l.add(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean create(Participation obj) {

        String sql = "INSERT INTO `event_participation`"
                + "(`EVENT_ID_FK_PK`,"
                + "`USER_ID_FK_PK`,"
                + "`ROLE_PARTICIPATION_FK`)"
                + "values ("
                + obj.getEventId()
                + "," + obj.getUserId()
                + "," + obj.getRole().getId()
                + ""
                + ")";
        System.out.println(sql);
        return execute(sql);

    }

    @Override
    public boolean edit(Participation obj) {
        String req = "UPDATE event_participation"
                + "SET"
                + "`ROLE_PARTICIPATION_FK` = '" + obj.getRole().getId()
                + "' WHERE `EVENT_ID_FK_PK` ='" + obj.getEventId() + "'"
                + "'AND `USER_ID_FK_PK` ='" + obj.getUserId() + "'";

        return execute(req);
    }

    @Override
    public boolean delete(Participation obj) {
        return execute("update event_participation set isdeleted='1' where `EVENT_ID_FK_PK`=" + obj.getEventId() + "AND `USER_ID_FK_PK` =" + obj.getUserId());

    }

    @Override
    public List<Participation> findByEvent(int id) {
        return findAll().stream()
                .filter(c -> c.getEventId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public List<Participation> findByUser(int id) {
        return findAll().stream()
                .filter(c -> c.getUserId() == id)
                .collect(Collectors.toList());
    }
}
