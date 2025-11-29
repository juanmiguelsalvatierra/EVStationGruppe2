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
}
