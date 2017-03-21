package dao;


import model.User;
import org.hibernate.Session;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibImpl implements UserDao{
    private Session session;

    public UserDaoHibImpl(Session session) {
        this.session = session;
    }

    @Override
    public void insertUser(String name, String email, String password) {
        session.save(new User(name,email,password));
    }

    @Override
    public List<User> listAllUser() {
        List<User> userList=session.createQuery("from model.User").list();
//        Session session=sessionFactory.openSession();
//        Criteria criteria=session.createCriteria(User.class);
        return userList;
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        session.delete(new User(id));
    }

    @Override
    public User getId(int id) throws SQLException {
        return session.get(User.class, id);
    }

    @Override
    public void update(int id, String name, String email, String password) throws SQLException {


        String stringQuery = "UPDATE model.User SET name='" + name + "'," +
                "email='" + email + "', password='" + password + "' WHERE id='" + id + "'";
        Query query = session.createQuery(stringQuery);
        query.executeUpdate();
//        session.createSQLQuery("UPDATE db_test.users_hib SET name='" + name + "'," +
//                "email='" + email + "', password='" + password + "' WHERE id='" + id + "'").executeUpdate();
    }

    @Override
    public void deleteAll() throws SQLException {
        String stringQuery = "DELETE FROM model.User";
        Query query = session.createQuery(stringQuery);
        query.executeUpdate();
//        session.createSQLQuery("truncate table users").executeUpdate();
    }
}
