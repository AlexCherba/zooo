package com.vidwel.zoo;

import java.util.ArrayList;
import java.util.List;

public class ManagerZoo {
    private List<Zoo> zooArrayList;

    public static void init() {
        System.out.println("init of ManagerZoo.init()");
        DbTools.init();
    }

    public boolean addZoo(String name, String location) {
        if (name.length() == 0) return false;
        if (DbTools.addZoo(name, location) == false) return false;
        return true;
    }

    public List<Zoo> getAllZoo() {
        zooArrayList = DbTools.getAllZoo();
        return zooArrayList;
    }
}
