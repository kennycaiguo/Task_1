package controller;

import factory.AbstractFactory;
import service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static factory.AbstractFactory.getFactory;

@WebServlet("/deleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private AbstractFactory factory;


    public DeleteUserServlet() {
        factory = getFactory("hibernate");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        Service service=factory.getService();
        int id = Integer.parseInt(req.getParameter("id"));

        service.deleteUser(id);

        RequestDispatcher dispatcher = req.getRequestDispatcher("viewUserServlet");

        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

