/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.utils.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azer
 */
public class ServiceUtils {

    protected Connection cnx;

    public ServiceUtils() {
        try {
            cnx = DBConnection.getInstance().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(ServiceUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected ResultSet executeSelect(String select) {
        Statement st;
        try {
            st = cnx.createStatement();
            return st.executeQuery(select);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    protected boolean execute(String sql) {
        Statement st;
        try {
            st = cnx.createStatement();
            return st.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtils.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
