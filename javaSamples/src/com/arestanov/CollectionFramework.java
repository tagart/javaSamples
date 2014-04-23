/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arestanov;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Vector;

/**
 *
 * @author varestanov
 */
public class CollectionFramework {
    public static void main(String[] args) {
        Collection<String> c = new ArrayList<>();
        System.out.println(c.add(null));
        System.out.println(c.add(null));
        System.out.println(c.add("Gleb"));
        System.out.println(c.add("Gleb"));
        System.out.println(c.addAll(new Vector<String>(){{add("Andrew");add("Andrew");}}));
        System.out.println("contains: " + c.contains("Gleb"));
        System.out.println("containsAll: " + c.containsAll(new Vector<String>(){{add("Andrew");add("Gleb");}}));
        System.out.println("equals: " + c.equals(new ArrayList<String>(){{add(null);add(null);add("Gleb");add("Gleb");add("Andrew");add("Andrew");}}));
        System.out.println("hash1: " + c.hashCode());
        System.out.println("hash2: " + new ArrayList<String>(){{add(null);add(null);add("Gleb");add("Gleb");add("Andrew");add("Andrew");}}.hashCode());
        System.out.println("isEmpty: " + c.isEmpty());
        System.out.println("iterator: " + c.iterator());
        System.out.println("parallelStream: ");
        c.parallelStream().forEach(p->System.out.print(p + ", "));
        System.out.println();
        System.out.println("stream: ");
        c.parallelStream().forEach(p->System.out.print(p + ", "));
        System.out.println();
        System.out.println("removed: " + c.remove(null));
        System.out.println("removed: " + c.remove("Andrew"));
        System.out.println("removed: " + c.remove("Nikita"));
        System.out.println("removed: " + c.removeIf(p->p!=null && p.charAt(1)=='l'));
        System.out.println(c.add("Vladislav"));
        System.out.println(c.add("Victor"));
        System.out.println("retainAll: " + c.retainAll(new Vector<String>(){{add("Victor");add("Andrew");add("Gleb");}}));
        System.out.println("size: " + c.size());
        System.out.println("spliterator: " + c.spliterator());
        System.out.println("toArray: " + c.toArray()[1]);
        String[] b = c.toArray(new String[3]);
        System.out.println("toArray_: " + b.length + " " + b[0] + " " + b[1] + " " + b[2]);
        System.out.println("forEach: ");
        c.forEach(p->System.out.print(p + " "));
        System.out.println();
        System.out.println(c.toString());
        c.clear();
        
        System.out.println("\n=======================\n");
        
        c = new HashSet<>();
        System.out.println(c.add(null));
        System.out.println(c.add(null));
        System.out.println(c.add("Gleb"));
        System.out.println(c.add("Gleb"));
        System.out.println(c.addAll(new Vector<String>(){{add("Andrew");add("Andrew");}}));
        System.out.println("contains: " + c.contains("Igor"));
        System.out.println("containsAll: " + c.containsAll(new Vector<String>(){{add("Andrew");add("Igor");}}));
        System.out.println("equals: " + c.equals(new HashSet<String>(){{add("Andrew");add(null);add("Gleb");}}));
        System.out.println("hash1: " + c.hashCode());
        System.out.println("hash2: " + new HashSet<String>(){{add("Andrew");add(null);add("Gleb");}}.hashCode());
        System.out.println("isEmpty: " + c.isEmpty());
        System.out.println("iterator: " + c.iterator());
        System.out.println("parallelStream: ");
        c.parallelStream().forEach(p->System.out.print(p + ", "));
        System.out.println();
        System.out.println("stream: ");
        c.parallelStream().forEach(p->System.out.print(p + ", "));
        System.out.println();
        System.out.println("removed: " + c.remove(null));
        System.out.println("removed: " + c.remove("Andrew"));
        System.out.println("removed: " + c.remove("Nikita"));
        System.out.println("size: " + c.size());
        System.out.println("spliterator: " + c.spliterator());
        System.out.println("toArray: " + c.toArray()[0]);
        System.out.println(c.toString());
        c.clear();
    }
}
