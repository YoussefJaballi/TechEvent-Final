/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import edu.esprit.models.Participation;
import edu.esprit.models.User;
import java.util.List;

/**
 *
 * @author yjaballi
 */
public class UserManager {

    private static User user;
    private static List<Participation> participation;

    public static void setRegisterUser(User RegisterUser) {
        UserManager.RegisterUser = RegisterUser;
    }

    public static User getRegisterUser() {
        return RegisterUser;
    }
    private static User RegisterUser;
    private UserManager() {
    }

    public static List<Participation> getParticipation() {
        return participation;
    }

    public static void setParticipation(List<Participation> participation) {
        UserManager.participation = participation;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserManager.user = user;
    }

}
