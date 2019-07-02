/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services.implementation;

import edu.esprit.models.RoleUser;
import edu.esprit.services.IRoleUserService;
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
public class RoleUserService extends ServiceUtils implements IRoleUserService {

    @Override
    public RoleUser find(int id) throws ComposedIDExeption {
        RoleUser c = null;
        try {
            ResultSet rs = executeSelect("select * from role_user where  ROLE_ID_PK=" + id + " and isdeleted=0");
            while (rs.next()) {
                c = new RoleUser(
                        rs.getInt("ROLE_ID_PK"),
                        rs.getString("ROLE_DESCRIPTION")
                );
            }
        } catch (Exception ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public List<RoleUser> findAll() {
        List<RoleUser> l = new ArrayList<>();
        try {
            ResultSet rs = executeSelect("select * from `teck_event`.`role_user` where `isdeleted`=0");
            while (rs.next()) {
                RoleUser u = new RoleUser(
                        rs.getInt("ROLE_ID_PK"),
                        rs.getString("ROLE_DESCRIPTION")
                );
                l.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return l;
    }

    @Override
    public boolean create(RoleUser obj) {
        String sql = "insert `teck_event`.`role_user` ("
                + "`ROLE_DESCRIPTION`"
                + ")"
                + "values ("
                + "'" + obj.getDescription()
                + "');";
        return execute(sql);
    }

    @Override
    public boolean edit(RoleUser obj) {
        String sql = "update `teck_event`.`role_user` set "
                + "`ROLE_DESCRIPTION`='" + obj.getDescription()
                + "' where `ROLE_ID_PK`=" + obj.getId() + ";";
        return execute(sql);
    }

    @Override
    public boolean delete(RoleUser obj) {
        return execute("update `teck_event`.`role_user` set `isdeleted` =1 where `ROLE_ID_PK`=" + obj.getId() + ";");
    }

}
