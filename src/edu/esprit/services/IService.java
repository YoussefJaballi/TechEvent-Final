/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.services.exeptions.ComposedIDExeption;
import java.util.List;

/**
 *
 * @author azer
 */
public interface IService<T> {

    T find(int id) throws ComposedIDExeption;

    List<T> findAll();

    boolean create(T obj);

    boolean edit(T obj);

    boolean delete(T obj);

}
