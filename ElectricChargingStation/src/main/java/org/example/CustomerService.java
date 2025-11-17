package org.example;

public class CustomerService {
    public CustomerRepository repo;
    public String name;
    public String email;
    public int customerId = -1;

    public CustomerService(CustomerRepository repo){
        this.repo = repo;
    }

    public Customer createCustomer(String name, String email) {
        Customer customer = new Customer(name, email);
        repo.save(customer);
        return customer;
    }
}
