package org.example;

import java.util.HashMap;

public class Charger {
    private int chargerId;
    private ChargerType chargerType;
    private Status status;
    private int DC_Kw = 50;
    private int AC_Kw = 10;

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

    public int getChargerId() {
        return chargerId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus(){
        return this.status;
    }

    public int getDC_Kw(){
        return this.DC_Kw;
    }

    public int getAC_Kw(){
        return this.AC_Kw;
    }

    @Override
    public String toString() {
        return chargerId + " - " + chargerType.toString() + " - " + status.toString();
    }
}
