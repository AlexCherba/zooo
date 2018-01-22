package com.vidwel.zoo;

import java.util.TreeMap;

public class Zoo {
    private TreeMap<String, String> allZoo = new TreeMap<>();
    private String name;
    private String location;

    public boolean addZoo(String name, String location) {
        if (name.length() == 0) return false;
        if (DbTools.addZoo(name, location) == false) return false;
        allZoo.put(name, location);
        return true;
    }

    public static void init() {
        System.out.println("init of Zoo.init()");
        DbTools.init();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
