package org.example;

public class ApplicationContext {
    public CustomerRepository customerRepo;
    public CustomerService customerService;

    public LocationRepository locationRepo;
    public LocationService locationService;

    public ChargerRepository chargerRepository;
    public ChargerService chargerService;

    public ApplicationContext() {
        this.customerRepo = new CustomerRepository();
        this.customerService = new CustomerService(customerRepo);

        this.locationRepo = new LocationRepository();
        this.locationService = new LocationService(locationRepo);

        this.chargerRepository = new ChargerRepository();
        this.chargerService = new ChargerService(chargerRepository);
    }
}
