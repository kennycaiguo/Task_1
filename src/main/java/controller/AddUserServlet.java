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
import java.io.PrintWriter;

import static factory.AbstractFactory.getFactory;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {

    private AbstractFactory factory;
    private String factoryName="hibernate";

    public AddUserServlet() {
        factory = getFactory(factoryName);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Service service=factory.getService();
        service.addUser(name,email,password);

        RequestDispatcher dispatcher = request.getRequestDispatcher("viewUserServlet");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}

