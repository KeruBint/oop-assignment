package oop;

public class Microwave extends Product {
    private String power;
    private String capacity;
    private boolean childLock;

    public Microwave(String itemNumber, String name, int quantity, double price, String power, String capacity, boolean childLock) {
        super(itemNumber, name, quantity, price);
        this.power = power;
        this.capacity = capacity;
        this.childLock = childLock;
    }

    public String getPower() {
        return power;
    }

    public String getCapacity() {
        return capacity;
    }

    public boolean isChildLock() {
        return childLock;
    }

    // Setters
    public void setPower(String power) {
        this.power = power;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setChildLock(boolean childLock) {
        this.childLock = childLock;
    }

    @Override
    public String toString() {
        return "Item number : " + getItemNumber() +
                "\nProduct name : " + getName() +
                "\nPower : " + power +
                "\nCapacity : " + capacity +
                "\nChild lock : " + (childLock ? "Enabled" : "Disabled") +
                "\nQuantity available: " + getQuantity() +
                "\nPrice (RM) : " + getPrice() +
                "\nInventory value (RM): " + getTotalValue() +
                "\nProduct status : " + (getStatus() ? "Active" : "Discontinued");
    }

}
