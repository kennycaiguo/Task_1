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

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    private AbstractFactory factory;


    public DeleteUserServlet() {
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Service service=factory.getService();
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteUser(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view");
        dispatcher.forward(req, resp);
    }
}

