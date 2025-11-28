package org.example;

import java.util.HashMap;

public class Charger {
    private int chargerId;
    private ChargerType chargerType;
    private Status status;

    public Charger(String chargerType, String status){
        ChargerType chargerTypeParsed = ChargerType.valueOf(chargerType);
        Status statusParsed = Status.valueOf(status);

        this.chargerType = chargerTypeParsed;
        this.status = statusParsed;
    }


    public void setId(int id){
        this.chargerId = id;
    }

    public ChargerType getChargerType(){
        return this.chargerType;
    }

    public Status getStatus(){
        return this.status;
    }

    @Override
    public String toString() {
        return chargerId + " - " + chargerType.toString() + " - " + status.toString();
    }
}
