/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import java.security.MessageDigest;

/**
 *
 * @author yjaballi
 */
public class Hasher {
    
    public static String generatePasswordHash(String pass) {
        String hashedPassword="";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pass.getBytes());

            byte bytes[] = md.digest();
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                buffer.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            hashedPassword = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashedPassword;
        //System.out.println(password);

    }
    
}
