package com.vidwel.zoo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateZooServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CreateZooServlet");
        System.out.println("ADD ZOO " + DatabaseTools.addZoo("Киевский зоопарк", "Киев"));
        req.getRequestDispatcher("/html/create_zoo.html").forward(req,resp);
    }
}
