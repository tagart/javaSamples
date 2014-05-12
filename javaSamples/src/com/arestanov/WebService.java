/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arestanov;

/**
 *
 * @author varestanov
 */
@javax.jws.WebService(endpointInterface = "com.arestanov.WebServiceInterface")
public class WebService implements WebServiceInterface {
    @Override
    public String getHelloWorldAsString(String name) {
        return "Привет, " + name;
    }
}
