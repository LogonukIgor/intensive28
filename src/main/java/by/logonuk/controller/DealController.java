package by.logonuk.controller;

import by.logonuk.service.DealService;
import by.logonuk.service.ServiceInterface;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DealController extends HttpServlet {

    private ServiceInterface service;

    @Override
    public void init() throws ServletException {
        super.init();
        this.service = new DealService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer userId = Integer.parseInt(req.getParameter("id"));
        resp.getWriter().print(service.findById(userId));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().print("Success create: " + service.create(req));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().print("Success update: " + service.update(req));
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("id"));
        resp.getWriter().print("Success delete deal with id: " + service.delete(userId));
    }
}
