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
public class WebServiceClient {
    public static void main(String[] args) {
        WebServiceService webService = new WebServiceService();
        WebServiceInterface_1 hello = webService.getWebServicePort();
        System.out.println(hello.getHelloWorldAsString("Валера"));
    }
}
