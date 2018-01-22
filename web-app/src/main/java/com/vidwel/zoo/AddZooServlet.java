package com.vidwel.zoo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddZooServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddZooServlet");
        String name = req.getParameter("nameZoo");
        String location = req.getParameter("locationZoo");
        if (new Zoo().addZoo(name,location)) System.out.println("Add new zoo is " + true);
        else System.out.println("Add new zoo is " +  false);
//        Zoo zoo = new Zoo();
//        zoo.addZoo(name,location);
//        System.out.println(req.getParameter("nameZoo"));
//        System.out.println(req.getParameter("locationZoo"));
//        System.out.println("ADD ZOO " + DbTools.addZoo("Киевский зоопарк", "Киев"));
//        req.getRequestDispatcher("/html/add_zoo.html").forward(req,resp);
    }
}
