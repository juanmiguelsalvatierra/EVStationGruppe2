package org.example;

public class ApplicationContext {
    public CustomerRepository cr = new CustomerRepository();
    public CustomerService cs = new CustomerService(cr);

    public LocationRepository locationRepo = new LocationRepository();
    public LocationService locationService = new LocationService(locationRepo);

    public ApplicationContext(){
        this.cr = cr;
    }
}
