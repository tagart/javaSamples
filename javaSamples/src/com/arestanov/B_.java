/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tool | Template
 * and open the template in the editor. Привет
 */

package com.arestanov;

/**
 *
 * @author Valera
 */
abstract class B {
    B(float f) {
    }
    B() {
        System.out.println(f());
    }
    abstract float f();
}

public class B_ extends B {
    float f = 2f;
    public B_() {
        //super(f);
    }
    float f() {
        return f;
    }
    public static void main(String[] args) {
        new B_();
    }
}
