/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services.implementation;

import edu.esprit.models.Location;
import edu.esprit.services.ILocationService;
import edu.esprit.services.ServiceUtils;
import edu.esprit.services.exeptions.ComposedIDExeption;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yjaballi
 */
public class LocationService extends ServiceUtils implements ILocationService {

    @Override
    public Location find(int id) throws ComposedIDExeption {
        Location c = null;
        try {
            ResultSet rs = executeSelect("select * from EVENT_LOCATION where  EVENT_LOCATION_ID_PK=" + id + " and isdeleted=0");
            while (rs.next()) {
                c = new Location(rs.getInt("EVENT_LOCATION_ID_PK"),
                        rs.getString("EVENT_LOCATION_NAME"), rs.getDouble("EVENT_LOCATION_LAT"), rs.getDouble("EVENT_LOCATION_LONG"),
                        rs.getDouble("EVENT_LOCATION_ZIP_CODE")
                );
            }
        } catch (Exception ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public List<Location> findAll() {
        List<Location> l = new ArrayList<>();
        try {
            ResultSet rs = executeSelect("select * from EVENT_LOCATION where isdeleted=0");
            while (rs.next()) {
                Location lo = new Location(rs.getInt("EVENT_LOCATION_ID_PK"),
                        rs.getString("EVENT_LOCATION_NAME"), rs.getDouble("EVENT_LOCATION_LAT"), rs.getDouble("EVENT_LOCATION_LONG"),
                        rs.getDouble("EVENT_LOCATION_ZIP_CODE")
                );
                l.add(lo);
            }
        } catch (Exception ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean create(Location obj) {
        String sql = "insert into EVENT_LOCATION ("
                + "`EVENT_LOCATION_NAME`,"
                + "`EVENT_LOCATION_LAT`,"
                + "`EVENT_LOCATION_LONG`,"
                + "`EVENT_LOCATION_ZIP_CODE`,"
                + "`ISDELETED`)"
                + "values ("
                + "'" + obj.getName()
                + "'," + obj.getLatitude()
                + "," + obj.getLongitude()
                + ",'" + obj.getZipCode()
                + "',0"
                + ")";

        return execute(sql);
    }

    @Override
    public boolean edit(Location obj) {
        String req = "UPDATE EVENT_LOCATION"
                + " SET "
                + "`EVENT_LOCATION_NAME`='" + obj.getName()
                + "',`EVENT_LOCATION_LAT`=" + obj.getLatitude()
                + ",`EVENT_LOCATION_LONG`=" + obj.getLongitude()
                + ",`EVENT_LOCATION_ZIP_CODE`='" + obj.getZipCode()
                + "' WHERE EVENT_LOCATION_ID_PK=" + obj.getId();
        return execute(req);
    }

    @Override
    public boolean delete(Location obj) {
        return execute("update EVENT_LOCATION set isdeleted='1' WHERE `EVENT_LOCATION_ID_PK` =" + obj.getId());
    }

}
