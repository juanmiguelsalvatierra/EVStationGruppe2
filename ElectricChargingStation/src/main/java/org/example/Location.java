package org.example;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;

public class Location {
    private int locationId;
    private String name;
    private String address;
    public HashMap<Integer, Charger> chargersRepo = new HashMap<>();
    public HashMap<Integer, Price> priceList = new HashMap<>();

    public Location(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.locationId;
    }

    public void setId(int id) {
        this.locationId = id;
    }

    public Charger createCharger(String chargerType, String status) {
        int newId = chargersRepo.size() + 1;
        chargersRepo.put(newId, new Charger(chargerType, status));
        chargersRepo.get(newId).setId(newId);
        return chargersRepo.get(newId);
    }

    public String getAllChargersAsString() {
        StringBuilder actualOutput = new StringBuilder();
        for (Charger charger : chargersRepo.values()) {
            actualOutput.append(charger.toString()).append("\n");
        }
        return actualOutput.toString();
    }

    public void createPrices(double price_per_kWh_AC, double price_per_kWh_DC, double parking_price_AC, double parking_price_DC, LocalDateTime valid_From) {
        if (price_per_kWh_DC < 0||price_per_kWh_AC < 0||parking_price_AC < 0||parking_price_DC < 0) {
            throw new IllegalArgumentException("Exeption - Invalid price");
        }
        int newId = priceList.size() + 1;
        priceList.put(newId, new Price(newId, price_per_kWh_AC, price_per_kWh_DC, parking_price_AC, parking_price_DC, valid_From));
    }

    public Price getCurrentPrice() {
        return priceList.get(priceList.size());
    }


    public String getPricesAsString() {
        Price mostRecentPrice = priceList.get(priceList.size());
        StringBuilder actualOutput = new StringBuilder();

        actualOutput.append(mostRecentPrice.toString());
        actualOutput.insert(0, "---"+this.getName()+ " at " + mostRecentPrice.valid_From +"---\n");

        return actualOutput.toString();
    }

    public void deleteCharger(int id) {
        if (chargersRepo.containsKey(id)){
            chargersRepo.remove(id);
        } else{
            throw new IllegalArgumentException("Exception - Charger does not exist");
        }
    }

    public void updateCharger(int id, String type, String status){

        ChargerType typeparsed = ChargerType.valueOf(type);
        Status statusparsed = Status.valueOf(status);
        if (chargersRepo.containsKey(id)){
            chargersRepo.get(id).setStatus(statusparsed);
            chargersRepo.get(id).setType(typeparsed);
        } else{
            throw new IllegalArgumentException("Exception - Charger does not exist");
        }
    }

    @Override
    public String toString() {
        return locationId + " - " + name + " - " + address;
    }
}
