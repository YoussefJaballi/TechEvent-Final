/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services.implementation;

import edu.esprit.models.Entreprise;
import edu.esprit.services.IEntrepriseService;
import edu.esprit.services.ServiceUtils;
import edu.esprit.services.exeptions.ComposedIDExeption;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abkhaldi
 */
public class EntrepriseService extends ServiceUtils implements IEntrepriseService {

    @Override
    public Entreprise find(int id) throws ComposedIDExeption {
        Entreprise etp = null;
        try {
            ResultSet rs = executeSelect("select * from entreprise where  ENTREPRISE_ID_PK=" + id + " and isdeleted=0");
            while (rs.next()) {
                etp =  new Entreprise(
                        rs.getInt("ENTREPRISE_ID_PK"),
                        rs.getString("ENTREPRISE_NAME"));
            }
        } catch (Exception ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return etp;
    }

    @Override
    public List<Entreprise> findAll() {
        List<Entreprise> ent = new ArrayList<>();
        try {
            ResultSet rs = executeSelect("select * from entreprise where isdeleted=0");
            while (rs.next()) {
                Entreprise c = new Entreprise(
                        rs.getInt("ENTREPRISE_ID_PK"),
                        rs.getString("ENTREPRISE_NAME"));

                ent.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ent;
    }

    @Override
    public boolean create(Entreprise obj) {
        String sql = "insert into `teck_event`.`entreprise` "
                + "(`ENTREPRISE_NAME`) "
                + "values "
                + "('" + obj.getName()
                + "');";

        return execute(sql);
    }

    @Override
    public boolean edit(Entreprise obj) {
        String req = "UPDATE `teck_event`.`entreprise`"
                + "SET"
                + "`ENTREPRISE_NAME` = '" + obj.getName() + "'"
                + " WHERE `ENTREPRISE_ID_PK` =" + obj.getId() + ";";

        return execute(req);
    }

    @Override
    public boolean delete(Entreprise obj) {

        return execute("update `teck_event`.`entreprise` set `isdeleted` =1 where `ENTREPRISE_ID_PK`=" + obj.getId() + ";");
    }

}
