package oop;

public class Fan extends Product {
    private String speed;
    private String size;
    private boolean oscillation;

    public Fan(String itemNumber, String name, int quantity, double price, String speed, String size, boolean oscillation) {
        super(itemNumber, name, quantity, price);
        this.speed = speed;
        this.size = size;
        this.oscillation = oscillation;
    }

    public String getSpeed() {
        return speed;
    }

    public String getSize() {
        return size;
    }

    public boolean isOscillation() {
        return oscillation;
    }

    // Setters
    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setOscillation(boolean oscillation) {
        this.oscillation = oscillation;
    }

    @Override
    public String toString() {
        return "Item number : " + getItemNumber() +
                "\nProduct name : " + getName() +
                "\nSpeed : " + speed +
                "\nSize : " + size +
                "\nOscillation : " + (oscillation ? "Yes" : "No") +
                "\nQuantity available: " + getQuantity() +
                "\nPrice (RM) : " + getPrice() +
                "\nInventory value (RM): " + getTotalValue() +
                "\nProduct status : " + (getStatus() ? "Active" : "Discontinued");
    }

}
