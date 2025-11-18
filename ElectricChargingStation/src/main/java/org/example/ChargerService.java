package org.example;

public class ChargerService {
    private ChargerRepository chargerRepository;

    public ChargerService(ChargerRepository chargerRepository) {
        this.chargerRepository = chargerRepository;
    }


    public Charger createCharger(int locationId, ChargerType type, Status status, Double powerRate, Double pricePerHour) {
        Charger charger = new Charger(locationId, type, status, powerRate, pricePerHour);
        chargerRepository.save(charger);
        return charger;
    }



}
