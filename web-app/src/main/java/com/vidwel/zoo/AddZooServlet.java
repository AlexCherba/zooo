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
import java.util.List;

public class AddZooServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 1. get received JSON data from request
        String json = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        if(br != null){
            json = br.readLine();
        }
        System.out.println(json);
        json = "{\"one\":\"odin\",\"two\":\"dva\"}";
        //json = "\'name\"=\"coco\"";

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getOutputStream(),json);
        return;

/*

        System.out.println("AddZooServlet");
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
//        req.getRequestDispatcher("/html/add_zoo.html").forward(req,resp);
    }
}
