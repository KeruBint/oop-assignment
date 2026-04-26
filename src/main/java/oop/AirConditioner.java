package oop;

public class AirConditioner extends Product {
    private String coolingCapacity;
    private String energyRating;
    private boolean inverter;

    public AirConditioner(String itemNumber, String name, int quantity, double price, String coolingCapacity, String energyRating, boolean inverter) {
        super(itemNumber, name, quantity, price);
        this.coolingCapacity = coolingCapacity;
        this.energyRating = energyRating;
        this.inverter = inverter;
    }

    public String getCoolingCapacity() {
        return coolingCapacity;
    }

    public String getEnergyRating() {
        return energyRating;
    }

    public boolean isInverter() {
        return inverter;
    }

    // Setters
    public void setCoolingCapacity(String coolingCapacity) {
        this.coolingCapacity = coolingCapacity;
    }

    public void setEnergyRating(String energyRating) {
        this.energyRating = energyRating;
    }

    public void setInverter(boolean inverter) {
        this.inverter = inverter;
    }

    @Override
    public String toString() {
        return "Item number : " + getItemNumber() +
                "\nProduct name : " + getName() +
                "\nCooling capacity : " + coolingCapacity +
                "\nEnergy rating : " + energyRating +
                "\nInverter : " + (inverter ? "Yes" : "No") +
                "\nQuantity available: " + getQuantity() +
                "\nPrice (RM) : " + getPrice() +
                "\nInventory value (RM): " + getTotalValue() +
                "\nProduct status : " + (getStatus() ? "Active" : "Discontinued");
    }

}
