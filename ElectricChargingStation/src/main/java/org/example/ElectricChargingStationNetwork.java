package org.example;

public class ElectricChargingStationNetwork {
    public static void main(String[] args) {
        ApplicationContext ac = new ApplicationContext();
        Customer c = ac.customerService.createCustomer("Juan", "jj@gmail.com");
        System.out.println(c);
        Customer c1 = ac.customerService.createCustomer("Julian", "jjj@gmail.com");
        System.out.println(c1);
    }
}
