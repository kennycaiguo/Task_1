package service;


import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public interface Service <T>{
    SessionFactory createSessionFactory(Configuration configuration);
    T getConnection();
    void addUser(String name, String email, String password);
    void deleteUser(int id) throws IOException;
    void deleteAllUser() throws IOException;
    List<User> listAllUser();
    User getUser(int id) throws IOException;
    void updateUser(int id, String name, String email, String password) throws IOException;
}
