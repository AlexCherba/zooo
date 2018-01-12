package com.vidwel.zoo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.sendRedirect("/html/index.html");
        //RequestDispatcher rd = req.getRequestDispatcher("/html/index.html");
        //rd.include(req, resp);
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/html/index.html");
//        requestDispatcher.forward(req, resp);

        req.getRequestDispatcher("/index.html").forward(req,resp);
    }
}
