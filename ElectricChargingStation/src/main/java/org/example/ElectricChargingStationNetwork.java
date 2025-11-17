package org.example;

public class ElectricChargingStationNetwork {
    public static void main(String[] args) {
        ApplicationContext ac = new ApplicationContext();
        Customer c = ac.cs.createCustomer("Juan", "jj@gmail.com");
        System.out.println(c);
        Customer c1 = ac.cs.createCustomer("Julian", "jjj@gmail.com");
        System.out.println(c1);
    }
}
