package org.example;

import java.util.HashMap;

public class ChargerRepository {
    private HashMap<Integer, Charger> chargers = new HashMap<>();

    public void save(Charger charger) {
        int newId = chargers.size() +1;
        charger.chargerId = newId;
        chargers.put(newId, charger);
    }
}
