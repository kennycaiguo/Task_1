package service;

import dao.UserDaoHibImpl;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class ServiceHibImpl implements Service{

    private SessionFactory sessionFactory;
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    public ServiceHibImpl() {
        Configuration configuration = getConnection();
        this.sessionFactory = createSessionFactory(configuration);
    }

    @Override
    public SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public Configuration getConnection() {


        try {
            FileInputStream fileInputStream;
            Properties properties = new Properties();
            fileInputStream = new FileInputStream(
                    "J:\\Java_Web_Projects\\Task_1\\src\\main\\resources\\hibernate.properties");
            properties.load(fileInputStream);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");


            String hib_dialect = properties.getProperty("hib_dialect");
            String hib_dialect2 = properties.getProperty("hib_dialect2");
            String hib_connection = properties.getProperty("hib_connection");
            String hib_url = properties.getProperty("hib_url");
            String hib_user = properties.getProperty("hib_user");
            String hib_password = properties.getProperty("hib_password");


            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);
            configuration.setProperty(hib_dialect, hib_dialect2);
            configuration.setProperty(hib_connection, driver);
            configuration.setProperty(hib_url, url);
            configuration.setProperty(hib_user, user);
            configuration.setProperty(hib_password, password);
            configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
            configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
            return configuration;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void addUser(String name, String email, String password) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDaoHibImpl dao = new UserDaoHibImpl(session);
            dao.insertUser(name,email,password);
            transaction.commit();
            session.close();
        }catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDaoHibImpl dao = new UserDaoHibImpl(session);
            dao.deleteUser(id);
            transaction.commit();
            session.close();
        }catch (HibernateException | SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllUser() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDaoHibImpl dao = new UserDaoHibImpl(session);
            dao.deleteAll();
            transaction.commit();
            session.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> listAllUser() {
        List<User> userList;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDaoHibImpl dao = new UserDaoHibImpl(session);
        userList=dao.listAllUser();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public User getUser(int id) {
        User user=null;
        try {
            Session session = sessionFactory.openSession();

            UserDaoHibImpl dao = new UserDaoHibImpl(session);
            user =dao.getId(id);
            session.close();
            return user;
        } catch (HibernateException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public void updateUser(int id, String name, String email, String password) throws IOException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UserDaoHibImpl dao = new UserDaoHibImpl(session);
            dao.update(id,name,email,password);
            transaction.commit();
            session.close();
        } catch (HibernateException | SQLException e) {
            e.printStackTrace();
        }
    }
}
