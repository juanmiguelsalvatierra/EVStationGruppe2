package org.example.Presentation;

import org.example.Application.ApplicationContext;
import org.example.Database.Entities.Charger;
import org.example.Database.Entities.Customer;
import org.example.Database.Entities.Location;

import static org.example.Database.Entities.ChargerType.AC;
import static org.example.Database.Entities.ChargerType.DC;
import static org.example.Database.Entities.Status.FREE;
import static org.example.Database.Entities.Status.OCCUPIED;

public class ElectricChargingStationNetwork {
    public static void main(String[] args) {
        ApplicationContext ac = new ApplicationContext();
        Customer c = ac.customerService.createCustomer("Juan", "jj@gmail.com");
        System.out.println(c);
        Customer c1 = ac.customerService.createCustomer("Julian", "jjj@gmail.com");
        System.out.println(c1);

        Location l = ac.locationService.createLocation("Thaliastraße", "Wien");
        System.out.println(l);
        Location l1 = ac.locationService.createLocation("Hochstaedtplatz", "Wien");
        System.out.println(l1);

        Charger ch = ac.chargerService.createCharger(AC, FREE, 22.0, 22.0);
        System.out.println(ch);
        Charger ch1 = ac.chargerService.createCharger(DC, OCCUPIED, 22.0, 22.0);
        System.out.println(ch1);
    }
}
