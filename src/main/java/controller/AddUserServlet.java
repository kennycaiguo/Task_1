package controller;


import factory.AbstractFactory;
import service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static factory.AbstractFactory.getFactory;

@WebServlet("/add")
public class AddUserServlet extends HttpServlet {

    private AbstractFactory factory;


    public AddUserServlet() {
        try {
            FileInputStream fileInputStream;
            Properties properties=new Properties();
            fileInputStream=new FileInputStream(
                    "J:\\Java_Web_Projects\\Task_1\\src\\main\\resources\\factoryName.properties");
            properties.load(fileInputStream);
            String factoryName=properties.getProperty("factoryName");
            factory = getFactory(factoryName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Service service=factory.getService();
        service.addUser(name,email,password);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view");
        dispatcher.forward(request, response);
    }
}

