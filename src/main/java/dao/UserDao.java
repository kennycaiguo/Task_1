package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
     void insertUser(String name, String email, String password);
     List<User> listAllUser();
     void deleteUser(int id) throws SQLException;
     User getId(int id) throws SQLException;
     void update(int id,String name, String email, String password) throws SQLException;
     void deleteAll() throws SQLException;
}
