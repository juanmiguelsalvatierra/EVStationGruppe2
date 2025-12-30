package org.example;

import java.util.HashMap;
import java.util.Map;

public class CustomerManager {
    public Map<Integer, Customer> customerRepo = new HashMap<>();

    public void createCustomer(String name, String email) {
        if(!checkDupes(name, email)){
            int newId = customerRepo.size() + 1;
            customerRepo.put(newId, new Customer(name, email));
            customerRepo.get(newId).setId(newId);
        }
    }

    public boolean checkDupes(String name, String address) {
        for (Map.Entry<Integer, Customer> entry : customerRepo.entrySet()) {
            if (entry.getValue().getName().equals(name) || entry.getValue().getEmail().equals(address)) {
                return true;
            }
        }
        return false;
    }

    public String getAllCustomersAsString(){
        StringBuilder actualOutput = new StringBuilder();
        for (Customer customer : customerRepo.values()) {
            actualOutput.append(customer.toString()).append("\n");
        }
        return actualOutput.toString();
    }

    public void updateCustomerName(int id, String name){
        if (customerRepo.containsKey(id)){
            customerRepo.get(id).setName(name);
        } else{
            throw new IllegalArgumentException("Exception - Customer not found");
        }
    }

    public void updateCustomerEmail(int id, String email){
        if (customerRepo.containsKey(id)){
            customerRepo.get(id).setEmail(email);
        } else{
            throw new IllegalArgumentException("Exception - Customer not found");
        }
    }

    public void deleteCustomer(int id) {
        if (customerRepo.containsKey(id)){
            customerRepo.remove(id);
        } else{
            throw new IllegalArgumentException("Exception - Customer not found");
        }
    }

    public String viewAllInvoices() {
        StringBuilder sb = new StringBuilder();

        boolean first = true;
        for (Customer customer : customerRepo.values()) {
            if (!first) {
                sb.append("\n"); // nur ein Zeilenumbruch zwischen Kunden
            }
            first = false;

            sb.append("Customer: ").append(customer.getName()).append("\n");
            sb.append(customer.getAllInvoiceItemsAsString());
        }

        return sb.toString().trim();
    }
}
