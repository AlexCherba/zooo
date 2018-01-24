package com.vidwel.zoo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("IndexServlet");
        req.getRequestDispatcher("/html/index.html").forward(req,resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init of IndexServlet");
        ManagerZoo.init();
    }
}
