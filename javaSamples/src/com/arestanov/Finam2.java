/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arestanov;

abstract class C_ {
    public C_() {
        System.out.println(value());
    }
    abstract Object value();
}

/**
 *
 * @author Valera
 */
public class Finam2 extends C_ {
    
    private final Object value = new Object();
    
    @Override
    Object value() {
        return value;
    }
    
    public static void main(String[] args) {
        new Finam2();
    }
    
}