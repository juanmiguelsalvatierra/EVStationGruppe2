package org.example;

public class ApplicationContext {
    public CustomerRepository cr = new CustomerRepository();
    public CustomerService cs = new CustomerService(cr);

    public ApplicationContext(){
        this.cr = cr;
    }
}
