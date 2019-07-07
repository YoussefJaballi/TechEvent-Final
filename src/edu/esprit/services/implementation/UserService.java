/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services.implementation;

import edu.esprit.models.Entreprise;
import edu.esprit.models.Participation;
import edu.esprit.models.Report;
import edu.esprit.models.RoleUser;
import edu.esprit.models.User;
import edu.esprit.services.IUserService;
import edu.esprit.services.ServiceUtils;
import edu.esprit.services.exeptions.ComposedIDExeption;
import edu.esprit.utils.Hasher;
import edu.esprit.utils.ServiceManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService extends ServiceUtils implements IUserService {

    @Override
    public User find(int id) throws ComposedIDExeption {
        Optional<User> o = findAll().stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        return o.isPresent() ? o.get() : null;
        

    }

    /**
     *
     * @param Login
     * @param password
     * @return
     */
    @Override
    public User login(String Login, String password){

        User c = null;
        try {
            ResultSet rs = executeSelect("select * from user_account where  USER_LOGIN='" + Login + "' and USER_PASSWORD='" + Hasher.generatePasswordHash(password) + "' and isdeleted=0");
            while (rs.next()) {
                c = new User(rs.getInt("USER_ID_PK"),
                        rs.getString("USER_EMAIL"), rs.getString("USER_NAME"), rs.getString("USER_LAST_NAME"),
                        rs.getString("USER_LOGIN"), rs.getString("USER_PASSWORD"),
                        new SimpleDateFormat("dd/mm/yy").parse(rs.getString("USER_BIRTHDATE")),
                        rs.getString("USER_ADRESS"), rs.getString("USER_PHOTO_URL"),
                        ServiceManager.getInstance().getEntrepriseService().find(rs.getInt("USER_ENTREPRISE_ID_FK")),
                        ServiceManager.getInstance().getRoleUserService().find(rs.getInt("USER_ROLE_ID_FK")),
                        rs.getBoolean("USER_ACTIVATED"),
                        rs.getString("USER_PHONE")
                );

            }
        } catch (Exception ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;

    }

    @Override
    public List<User> findAll() {
        List<User> l = new ArrayList<>();
        try {
            ResultSet rs = executeSelect("select * from `teck_event`.`user_account` where `isdeleted`=0");

            while (rs.next()) {
                User user = new User(rs.getInt("USER_ID_PK"),
                        rs.getString("USER_EMAIL"), rs.getString("USER_NAME"), rs.getString("USER_LAST_NAME"),
                        rs.getString("USER_LOGIN"), rs.getString("USER_PASSWORD"),
                        new SimpleDateFormat("dd/mm/yy").parse(rs.getString("USER_BIRTHDATE")),
                        rs.getString("USER_ADRESS"), rs.getString("USER_PHOTO_URL"),
                        ServiceManager.getInstance().getEntrepriseService().find(rs.getInt("USER_ENTREPRISE_ID_FK")),
                        ServiceManager.getInstance().getRoleUserService().find(rs.getInt("USER_ROLE_ID_FK")),
                        rs.getBoolean("USER_ACTIVATED"),
                        rs.getString("USER_PHONE")
                );

                user.setReports(ServiceManager.getInstance().getReportService().findByUser(rs.getInt("USER_ID_PK")));

                l.add(user);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    @Override
    public boolean create(User obj) {
        String sql = "insert into `teck_event`.`user_account` ("
                + "`USER_EMAIL`,"
                + "`USER_NAME`,"
                + "`USER_LAST_NAME`,"
                + "`USER_LOGIN`,"
                + "`USER_PASSWORD`,"
                + "`USER_BIRTHDATE`,"
                + "`USER_ADRESS`,"
                + "`USER_PHOTO_URL`,"
                + "`USER_ENTREPRISE_ID_FK`,"
                + "`USER_ROLE_ID_FK`)"
                + " values ("
                + "'" + obj.getEmail()
                + "','" + obj.getName()
                + "','" + obj.getLastName()
                + "','" + obj.getLogin()
                + "','" + Hasher.generatePasswordHash(obj.getPassword())
                + "','" + obj.getBirthday().getDate() + "/" + (obj.getBirthday().getMonth() + 1) + "/" + (obj.getBirthday().getYear() - 100)
                + "','" + obj.getAdress()
                + "','" + obj.getPhotoURL()
                + "'," + obj.getEntreprise().getId()
                + "," + obj.getRole().getId()
                + ");";

        boolean r = execute(sql);

        for (Participation p : obj.getParticipations()) {
            if (!ServiceManager.getInstance().getParticipationService().create(p)) {
                r = false;
            }
        }

        return r;
    }

    @Override
    public boolean edit(User obj) {

        String req1 = "UPDATE `teck_event`.`user_account`"
                + "SET"
                + "`USER_EMAIL` ='" + obj.getEmail() + "',"
                + "`USER_NAME` ='" + obj.getName() + "',"
                + "`USER_LAST_NAME` = '" + obj.getLastName() + "',"
                + "`USER_LOGIN` = '" + obj.getLogin() + "',"
                + "`USER_BIRTHDATE` = '" + obj.getBirthday().getDate() + "/" + (obj.getBirthday().getMonth() + 1) + "/" + (obj.getBirthday().getYear() - 100) + "',"
                + "`USER_ADRESS` = '" + obj.getAdress() + "',"
                + "`USER_ACTIVATED` = " + obj.isIsActivated() +" "
                + "WHERE `USER_ID_PK` = " + obj.getId() + ";";

        return execute(req1);
    }

    @Override
    public boolean delete(User obj) {
        return execute("update `teck_event`.`user_account` set `isdeleted`=1 where `USER_ID_PK`=" + obj.getId() + ";");
    }

    @Override
    public User findByLogin(String login) {
                User c = null;
        try {
            ResultSet rs = executeSelect("select * from user_account where  USER_LOGIN='" + login + "' and isdeleted=0");
            while (rs.next()) {
                c = new User(rs.getInt("USER_ID_PK"),
                        rs.getString("USER_EMAIL"), rs.getString("USER_NAME"), rs.getString("USER_LAST_NAME"),
                        rs.getString("USER_LOGIN"), rs.getString("USER_PASSWORD"),
                        new SimpleDateFormat("dd/mm/yy").parse(rs.getString("USER_BIRTHDATE")),
                        rs.getString("USER_ADRESS"), rs.getString("USER_PHOTO_URL"),
                        ServiceManager.getInstance().getEntrepriseService().find(rs.getInt("USER_ENTREPRISE_ID_FK")),
                        ServiceManager.getInstance().getRoleUserService().find(rs.getInt("USER_ROLE_ID_FK")),
                        rs.getBoolean("USER_ACTIVATED"),
                        rs.getString("USER_PHONE")
                );

            }
        } catch (Exception ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    @Override
    public boolean editPassword(User obj) {
         String req1 = "UPDATE `teck_event`.`user_account`"
                + "SET"
                + "`USER_PASSWORD` = '" + Hasher.generatePasswordHash(obj.getPassword()) + "',"
                 + "WHERE `USER_ID_PK` = " + obj.getId() + ";";
         return execute(req1);
    }

  

}
