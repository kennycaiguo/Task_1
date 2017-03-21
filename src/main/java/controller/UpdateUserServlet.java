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

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet{

    private AbstractFactory factory;


    public UpdateUserServlet() {
        factory = getFactory("hibernate");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Service service=factory.getService();
        service.updateUser(id,name,email,password);


        RequestDispatcher dispatcher = req.getRequestDispatcher("viewUserServlet");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
