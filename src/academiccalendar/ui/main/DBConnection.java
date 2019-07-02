/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academiccalendar.ui.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author yjaballi
 */
public class DBConnection {
     Connection connexion;
    static DBConnection instance;

    private DBConnection() throws Exception {
        
        String url="jdbc:mysql://esprit123.mysql.database.azure.com:3306/teck_event?useSSL=true&requireSSL=false";
     String user="root123@esprit123";
     String password="VermegEsprit-2019";
           
        
        try {
            connexion
                    = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public static  DBConnection getInstance() throws Exception{
        if(instance == null)
            instance = new DBConnection();
        return instance;
    }
    
    public Connection getConnection(){
        return connexion;
    }
}
