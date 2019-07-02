/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Ben-o
 */
public class DBConnection {

    Connection connexion;
    static DBConnection instance;

    private DBConnection() throws Exception {

        String url = "";
        String user = "";
        String password = "";
        try (InputStream input = new FileInputStream("resources\\DBCredantials.properties")) {

            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value 
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            connexion = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static DBConnection getInstance() throws Exception {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connexion;
    }
}
