package org.example.Application;

import org.example.Database.Entities.Customer;
import org.example.Database.CustomerRepository;

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
