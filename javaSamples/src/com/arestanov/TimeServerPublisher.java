/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arestanov;

import javax.xml.ws.Endpoint;

/**
 *
 * @author varestanov
 */
public class TimeServerPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9876/ts", new TimeServerImpl());
    }
}
