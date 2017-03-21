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
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static factory.AbstractFactory.getFactory;

@WebServlet("/view")
public class ViewUserServlet extends HttpServlet {
    private AbstractFactory factory;


    public ViewUserServlet() {
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
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        Service service=factory.getService();
        List<User> newUserList;
        newUserList=service.listAllUser();
        //        newUserList.forEach(System.out::println);
        RequestDispatcher dispatcher = req.getRequestDispatcher("UserList.jsp");
        req.setAttribute("userList", newUserList);
        dispatcher.forward(req, resp);
    }
}
