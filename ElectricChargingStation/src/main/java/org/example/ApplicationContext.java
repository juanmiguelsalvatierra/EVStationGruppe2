package org.example;

public class ApplicationContext {
    public CustomerRepository customerRepo;
    public CustomerService customerService;

    public LocationRepository locationRepo;
    public LocationService locationService;

    public ApplicationContext() {
        this.customerRepo = new CustomerRepository();
        this.customerService = new CustomerService(customerRepo);

        this.locationRepo = new LocationRepository();
        this.locationService = new LocationService(locationRepo);
    }
}
