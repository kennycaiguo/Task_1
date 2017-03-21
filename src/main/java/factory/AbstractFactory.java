package factory;

import service.Service;


public abstract class AbstractFactory {

    private static AbstractFactory abstractFactory;

    public abstract Service getService();


    public static AbstractFactory getFactory(String factoryName){
        if (abstractFactory==null) {
            switch (factoryName) {
                case "jdbc":
                    return new FactoryJDBC();
                case "hibernate":
                    return new FactoryHib();
                default:
                    return null;
            }
        }
        return abstractFactory;
    }
}


