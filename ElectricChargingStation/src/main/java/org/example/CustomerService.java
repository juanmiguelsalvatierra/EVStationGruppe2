package org.example;

public class CustomerService {
    public CustomerRepository repo;

    public CustomerService(CustomerRepository repo){
        this.repo = repo;
    }

    public Customer createCustomer(String name, String email) {
        Customer customer = new Customer(name, email);
        repo.save(customer);
        return customer;
    }
}
