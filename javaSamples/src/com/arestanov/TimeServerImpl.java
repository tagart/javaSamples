/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arestanov;

import java.util.Date;

/**
 *
 * @author varestanov
 */
@javax.jws.WebService(endpointInterface = "com.arestanov.TimeServer")
public class TimeServerImpl implements TimeServer {
    @Override
    public String getTimeAsString() {
        return new Date().toString();
    }
    @Override
    public long getTimeAsElapsed() {
        return new Date().getTime();
    }
}
