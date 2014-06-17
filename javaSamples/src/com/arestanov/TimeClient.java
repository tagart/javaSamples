/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arestanov;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author varestanov
 */
public class TimeClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:9876/ts?wsdl");
        QName qname = new QName("http://arestanov.com/", "TimeServerImplService");
        Service service = Service.create(url, qname);
        TimeServer elf = service.getPort(TimeServer.class);
        System.out.println(elf.getTimeAsString());
        System.out.println(elf.getTimeAsElapsed());
    }
}
