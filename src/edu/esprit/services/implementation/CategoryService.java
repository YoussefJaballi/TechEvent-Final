/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services.implementation;

import edu.esprit.models.Category;
import edu.esprit.models.Comment;
import edu.esprit.models.Event;
import edu.esprit.services.ICategoryService;
import edu.esprit.services.IEventService;
import edu.esprit.services.ServiceUtils;
import edu.esprit.services.exeptions.ComposedIDExeption;
import edu.esprit.utils.ServiceManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abkhaldi
 */
public class CategoryService extends ServiceUtils implements ICategoryService {

    @Override
    public Category find(int id) throws ComposedIDExeption {
        Category ca = null;
        try {
            ResultSet rs = executeSelect("select * from event_category where EVENT_CATEGORY_ID_PK=" + id + " and isdeleted=0");
            while (rs.next()) {
                 ca = new Category(
                        rs.getInt("EVENT_CATEGORY_ID_PK"),
                        rs.getString("EVENT_CATEGORY_NAME"));
            }
        } catch (Exception ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ca;
    }

    @Override
    public List<Category> findAll() {
        List<Category> ca = new ArrayList<>();
        try {
            ResultSet rs = executeSelect("select * from event_category where isdeleted=0");
            while (rs.next()) {
                Category c = new Category(
                        rs.getInt("EVENT_CATEGORY_ID_PK"),
                        rs.getString("EVENT_CATEGORY_NAME"));

                ca.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ca;
    }

    @Override
    public boolean create(Category obj) {
        String sql = "insert into event_category (`EVENT_CATEGORY_ID_PK`,"
                + "`EVENT_CATEGORY_NAME`,"
                + "`ISDELETED`)"
                + "values ("
                + obj.getId()
                + "," + obj.getId()
                + ",'" + obj.getName()
                + "',0"
                + ")";

        return execute(sql);
    }

    @Override
    public boolean edit(Category obj) {
        String req = "UPDATE event_category"
                + "SET"
                + "`EVENT_CATEGORY_NAME` = '" + obj.getName()
                + "' WHERE `EVENT_CATEGORY_ID_PK` ='" + obj.getId() + "'";

        return execute(req);
    }

    @Override
    public boolean delete(Category obj) {

        return execute("update event_category set isdeleted='1' where EVENT_CATEGORY_ID_PK=" + obj.getId());
    }

}
