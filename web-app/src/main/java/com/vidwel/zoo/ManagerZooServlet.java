package com.vidwel.zoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ManagerZooServlet extends HttpServlet {

    private void addZoo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = "";
        String name = req.getParameter("nameZoo");
        String location = req.getParameter("locationZoo");
        System.out.println("name " + name + ", location " + location);
        boolean isAddZoo = new ManagerZoo().addZoo(name, location);

        System.out.println("Add new zoo is " + isAddZoo);

        List<Map<Integer, String>> mapList = ManagerZoo.getAllZoo();

        ObjectMapper mapper = new ObjectMapper();
        json = mapper.writeValueAsString(mapList);
        mapper.writeValue(resp.getOutputStream(), json);
    }

    private void addType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = "";
        String name = req.getParameter("nameType");
        String location = req.getParameter("locationZoo");
        System.out.println("name " + name + ", location " + location);
        boolean isAddZoo = new ManagerZoo().addZoo(name, location);

        System.out.println("Add new zoo is " + isAddZoo);

        List<Map<Integer, String>> mapList = ManagerZoo.getAllZoo();

        ObjectMapper mapper = new ObjectMapper();
        json = mapper.writeValueAsString(mapList);
        mapper.writeValue(resp.getOutputStream(), json);

    }

        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String makeWhat = req.getParameter("makeWhat");
        String makeWho = req.getParameter("makeWho");

        switch (makeWhat) {
            case "add":
                switch (makeWho) {
                    case "zoo":
                        addZoo(req, resp);
                        break;
                    case "type":
                        addType(req,resp);
                        break;
                    case "animal":
                        break;
                }
                break;
            case "delete":
                break;
            case "view":
                break;
            default:
                break;
        }


// 1. get received JSON data from request
//        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//        if(br != null) json = br.readLine();
//        System.out.println(json);
//        json = "{\"one\":\"odin\",\"two\":\"dva\"}";
/*

        System.out.println("ManagerZooServlet");
        String name = req.getParameter("nameZoo");
        String location = req.getParameter("locationZoo");
        if (new Zoo().addZoo(name,location)) System.out.println("Add new zoo is " + true);
        else System.out.println("Add new zoo is " +  false);

        PrintWriter writer = resp.getWriter();
        writer.print("Hedllo my darling!");
*/

//        Zoo zoo = new Zoo();
//        zoo.addZoo(name,location);
//        System.out.println(req.getParameter("nameZoo"));
//        System.out.println(req.getParameter("locationZoo"));
//        System.out.println("ADD ZOO " + DbTools.addZoo("Киевский зоопарк", "Киев"));
//        req.getRequestDispatcher("/html/manager_zoo.html").forward(req,resp);
    }
}
