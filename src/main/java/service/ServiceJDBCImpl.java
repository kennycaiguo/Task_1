package service;

import dao.UserDaoJDBCImplements;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ServiceJDBCImpl implements Service{
    private final Connection connection;

    public ServiceJDBCImpl() {
        this.connection = getConnection();
    }

    @Override
    public SessionFactory createSessionFactory(Configuration configuration) {
        return null;
    }

    @Override
    public Connection getConnection() {
        try {
            FileInputStream fileInputStream;
            Properties properties=new Properties();
            fileInputStream=new FileInputStream(
                    "J:\\Java_Web_Projects\\Task_1\\src\\main\\resources\\jdbc.properties");
            properties.load(fileInputStream);
            String driver=properties.getProperty("driver");
            String url=properties.getProperty("url");
            String user=properties.getProperty("user");
            String password=properties.getProperty("password");

//            System.out.println("Driver: " + driver
//                    + ", Url: " + url
//                    + ", User: " + user
//                    + ", Password: " + password);

            Class.forName(driver);
            Connection connection = DriverManager.getConnection
                    (url, user, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("------- Подключение НЕ установлено -------");
            return null;
        }
    }

    @Override
    public void addUser(String name, String email, String password){
        try {
            connection.setAutoCommit(false);
            UserDaoJDBCImplements dao = new UserDaoJDBCImplements(connection);
            dao.insertUser(name, email, password);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<User> listAllUser(){
        List<User> list=new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            UserDaoJDBCImplements dao = new UserDaoJDBCImplements(connection);
            list=dao.listAllUser();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
        public User getUser(int id) throws IOException {
        try {
            return (new UserDaoJDBCImplements(connection).getId(id));
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }


    public void deleteUser(int id) throws IOException {
//        User user=new User();
        try {
            UserDaoJDBCImplements dao = new UserDaoJDBCImplements(connection);
            dao.deleteUser(id);
        } catch (SQLException e) {
            throw new IOException(e);
        }
//        return user;
    }

    @Override
    public void updateUser(int id,String name,String email,String password) throws IOException {
        try {
            UserDaoJDBCImplements dao = new UserDaoJDBCImplements(connection);
            dao.update(id,name,email,password);
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    @Override
    public void deleteAllUser() throws IOException {
        try {
            UserDaoJDBCImplements dao = new UserDaoJDBCImplements(connection);
            dao.deleteAll();
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }
}
