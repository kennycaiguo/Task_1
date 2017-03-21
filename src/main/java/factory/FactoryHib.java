package factory;


import service.Service;
import service.ServiceHibImpl;

public class FactoryHib extends AbstractFactory {

    @Override
    public Service getService() {
        return new ServiceHibImpl();
    }
}
