package org.example;

import java.util.List;

public class ChargerService {
    private ChargerRepository chargerRepository;

    public ChargerService(ChargerRepository chargerRepository) {
        this.chargerRepository = chargerRepository;
    }


    public Charger createCharger(ChargerType type, Status status, Double powerRate, Double pricePerHour) {
        Charger charger = new Charger(type, status, powerRate, pricePerHour);
        chargerRepository.save(charger);
        return charger;
    }

    public Charger getCharger(int chargerId) {
        return chargerRepository.findById(chargerId);
    }

    public List<Charger> getChargers() {
        return chargerRepository.getAllChargers();
    }

}
