package org.example.Application;

import org.example.Database.Entities.ChargerType;
import org.example.Database.Entities.Charger;
import org.example.Database.ChargerRepository;
import org.example.Database.Entities.Status;

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
        int k = chargerId;
        return chargerRepository.findById(chargerId);
    }

    public List<Charger> getChargers() {
        return chargerRepository.getAllChargers();
    }

}
