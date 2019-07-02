/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services.implementation;

import edu.esprit.models.RoleParticipation;
import edu.esprit.services.IRoleParticipationService;
import edu.esprit.services.ServiceUtils;
import edu.esprit.services.exeptions.ComposedIDExeption;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azer
 */
public class RoleParticipationService extends ServiceUtils implements IRoleParticipationService {

    @Override
    public RoleParticipation find(int id) throws ComposedIDExeption {
       RoleParticipation c = null;
        try {
            ResultSet rs = executeSelect("select * from role_participation where  ROLE_PARTICIPATION_ID=" + id + " and isdeleted=0");
            while (rs.next()) {
                c = new RoleParticipation(
                        rs.getInt("ROLE_PARTICIPATION_ID"),
                        rs.getString("ROLE_PARTICIPATION_DESCRIPTION")
                );
            }
        } catch (Exception ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public List<RoleParticipation> findAll() {
        List<RoleParticipation> l = new ArrayList<>();
        try {
            ResultSet rs = executeSelect("select * from role_participation where isdeleted=0");
            while (rs.next()) {
                RoleParticipation p = new RoleParticipation(
                        rs.getInt("ROLE_PARTICIPATION_ID"),
                        rs.getString("ROLE_PARTICIPATION_DESCRIPTION")
                );
                l.add(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean create(RoleParticipation obj) {
        String sql = "insert into role_participation ("
                + "`ROLE_PARTICIPATION_DESCRIPTION`"
                + ")"
                + "values ("
                + "'" + obj.getDescription()
                + "',0"
                + ")";
        return execute(sql);
    }

    @Override
    public boolean edit(RoleParticipation obj) {
        String sql = "update role_participation set "
                + "ROLE_PARTICIPATION_DESCRIPTION='" + obj.getDescription()
                + "' where ROLE_PARTICIPATION_ID=" + obj.getId();
        return execute(sql);
    }

    @Override
    public boolean delete(RoleParticipation obj) {
        String sql = "update role_participation set "
                + "isdeleted=1"
                + "where ROLE_PARTICIPATION_ID=" + obj.getId();
        return execute(sql);
    }

}
