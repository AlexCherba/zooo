package com.vidwel.zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static List<Map<Integer,String>> getAllZoo() {
        return DbTools.getAllZoo();
    }
}
