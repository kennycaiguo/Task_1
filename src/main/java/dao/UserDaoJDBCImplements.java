package dao;

import executor.Executor;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImplements implements UserDao {
    private Executor executor;


    public UserDaoJDBCImplements(Connection connection) {
        this.executor = new Executor(connection);
    }

    @Override
    public void insertUser(String name, String email, String password) {
        try {
            executor.execUpdate("INSERT INTO users_jdbc (name , email, password)" +
                    "VALUES ('" + name + "', '" + email + "', '" + password + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> listAllUser() {
        String query = "SELECT * FROM users_jdbc";

        List<User> userList = null;
        try {
            userList = executor.execQuery(
                    query, result -> {
                        List<User> users = new ArrayList<>();
                        while (result.next()) {
                            users.add(new User(result.getInt(1), result.getString(2),
                                    result.getString(3), result.getString(4)));
                        }
                        return users;
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        String query = "DELETE FROM users_jdbc where id in ('" + id + "')";
        executor.execUpdate(query);
    }

    @Override
    public User getId(int id) throws SQLException {
        return executor.execQuery("select * from users_jdbc where id=" + id, result -> {
            result.next();
            return new User(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4));
        });
    }

    @Override
    public void update(int id, String name, String email, String password) throws SQLException {
        executor.execUpdate("UPDATE `db_test`.`users_jdbc` SET `name`='" + name + "'," +
                "`email`='" + email + "', `password`='" + password + "' WHERE `id`='" + id + "'");
    }

    @Override
    public void deleteAll() throws SQLException {
        executor.execUpdate("TRUNCATE TABLE users_jdbc");
    }
}
