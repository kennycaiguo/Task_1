package factory;

import service.Service;
import service.ServiceJDBCImpl;


public class FactoryJDBC extends AbstractFactory {

    @Override
    public Service getService() {
        return new ServiceJDBCImpl();
    }
}
