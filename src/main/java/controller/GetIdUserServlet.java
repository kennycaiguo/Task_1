package controller;

import factory.AbstractFactory;
import model.User;
import service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static factory.AbstractFactory.getFactory;

@WebServlet("/getIdUserServlet")
public class GetIdUserServlet extends HttpServlet {


    private AbstractFactory factory;


    public GetIdUserServlet() {
        factory = getFactory("hibernate");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");


        int id = Integer.parseInt(req.getParameter("id"));
        Service service=factory.getService();
        User newUser=null;
        newUser=service.getUser(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("User.jsp");
        req.setAttribute("users_hib", newUser);
        dispatcher.forward(req, resp);

    }
    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

