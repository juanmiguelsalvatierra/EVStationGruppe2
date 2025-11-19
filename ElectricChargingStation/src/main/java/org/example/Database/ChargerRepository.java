package org.example.Database;

import org.example.Database.Entities.Charger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChargerRepository {
    private HashMap<Integer, Charger> chargers = new HashMap<>();

    public void save(Charger charger) {
        int newId = chargers.size() +1;
        charger.chargerId = newId;
        chargers.put(newId, charger);
    }
    public Charger findById(int chargerId) {
        return chargers.get(chargerId);
    }

    public List<Charger> getAllChargers() {
        return new ArrayList<>(chargers.values());
    }
}
