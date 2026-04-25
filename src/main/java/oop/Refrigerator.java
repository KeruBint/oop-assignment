package oop;

public class Refrigerator extends Product {
    private String doorDesign;
    private String color;
    private int capacity;

    public Refrigerator(String itemNumber, String name, int quantity, double price, String doorDesign, String color,
            int capacity) {
        super(itemNumber, name, quantity, price);
        this.doorDesign = doorDesign;
        this.color = color;
        this.capacity = capacity;
    }

    // getter
    public String getDoorDesign() {
        return doorDesign;
    }

    public String getColor() {
        return color;
    }

    public int getCapacity() {
        return capacity;
    }

    // setter
    public void setDoorDesign(String doorDesign) {
        this.doorDesign = doorDesign;

    }

    public void setColor(String color) {
        this.color = color;

    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Item number : " + itemNumber() +
                "\nProduct name : " + getName() +
                "\nDoor design : " + doorDesign +
                "\nColor : " + color +
                "\nCapacity (in Litres): " + capacity +
                "\nQuantity available: " + getQuantity() +
                "\nPrice (RM) : " + getPrice() +
                "\nInventory value (RM): " + getTotalValue() +
                "\nProduct status : " + (getStatus() ? "Active" : "Discontinued");
    }

}