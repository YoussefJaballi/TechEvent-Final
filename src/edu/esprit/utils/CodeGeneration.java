/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Ayoub
 */
public class CodeGeneration {

    public static String usingMathClass() {
        String number = "";
        for (int i = 0; i < 6; i++) {
            double randomDouble = Math.random();
            randomDouble = randomDouble *10;
            int randomInt = (int) randomDouble;
            number+=randomInt;
        }

        return number;
    }

}
