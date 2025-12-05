package org.example;

public class ChargingItem extends InvoiceItem {
    private int duration;
    private int priceId;
    private int locationId;
    private int chargerId;
    private ChargerType chargerType;

    public ChargingItem(int id, int duration, int priceId, int locationId, int chargerId, ChargerType chargerType) {
        super(id);
        this.duration = duration;
        this.priceId = priceId;
        this.locationId = locationId;
        this.chargerId = chargerId;
        this.chargerType = chargerType;
    }

    public int getChargingTime(){
        return this.duration;
    }

    @Override
    public double getInvoiceValue() {
        Location location = LocationManager.locationRepo.get(locationId);
        Price price = location.priceList.get(priceId);
        Charger charger = location.chargersRepo.get(chargerId);

        double chargingCost = calculateChargingCost(price, charger);
        double parkingCost = calculateParkingCosts(price);

        return (chargingCost + parkingCost) * -1;
    }

    private double calculateParkingCosts(Price price){
        double parkingcost = 0;

        if(chargerType == ChargerType.AC){
            parkingcost = ((double) duration / 60) * price.parking_pricePerHour_AC;
        }
        else if(chargerType == ChargerType.DC){
            parkingcost = ((double) duration / 60) * price.parking_pricePerHour_DC;
        }

        return  parkingcost;
    }

    private double calculateChargingCost(Price price, Charger charger){
        double chargingcost = 0;

        if(chargerType == ChargerType.AC){
            chargingcost = ((double) duration / 60) * charger.getAC_Kw() * price.price_per_kWh_AC;
        }
        else if(chargerType == ChargerType.DC){
            chargingcost = ((double) duration / 60) * charger.getDC_Kw() * price.price_per_kWh_DC;
        }

        return  chargingcost;
    }
}
