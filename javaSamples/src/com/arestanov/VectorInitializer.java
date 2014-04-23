/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arestanov;

import java.util.Vector;

/**
 *
 * @author varestanov
 */
public class VectorInitializer {
    public static void main(String[] args) {
        System.out.println(new Vector<String>(){{add("Ivan");add("Anna");add("Sergey");}}.toString());
    }
}
