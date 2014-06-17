/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arestanov;

class A {

    private final Object value;

    public A(Object value) {
        this.value = value;
        System.out.println(value);
    }
}

/**
 *
 * @author varestanov
 */
public class Finam1 extends A {

    private final Object value = new Object();
    
    public Finam1() {
        super(null);//можно
        //super(value);//нельзя
    }
    
    public static void main(String[] args) {

    }
}
