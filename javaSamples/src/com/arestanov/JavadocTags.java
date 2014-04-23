/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arestanov;

import java.util.Vector;

/**
 * This class introduces javadoc tags.
 * <p>{@link CollectionFramework#main Main method}</p>
 * <p>{@docRoot}</p>
 * <p>{@inheritDoc}</p>
 * @see CollectionFramework#main
 * @see java.lang.Object
 * @see java.util.Vector
 * @author varestanov
 */
public class JavadocTags {
    public static void main(String[] args) {
        
    }
    
    /**
     * 
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
}
