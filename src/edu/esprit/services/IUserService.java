/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.models.User;

/**
 *
 * @author azer
 */
public interface IUserService extends IService<User> {

    User login(String login, String password);
    User findByLogin(String login);

}
