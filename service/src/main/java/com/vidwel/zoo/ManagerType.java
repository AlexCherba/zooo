package com.vidwel.zoo;

import java.util.Map;

public class ManagerType {
    private Map<String,String> typeMap;

    public boolean addType(Map<String,String> typeMap) {
        if (typeMap.get("name").length() == 0) return false;
        return DbTools.addType(typeMap);
    }

}
