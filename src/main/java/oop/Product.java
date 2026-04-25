package oop;

public abstract class Product {
    private String name;
    private double price;
    private int quantity;
    private String itemNumber;
    private boolean status = true;

    public Product() {
        name = "product";
        price = 0;
        quantity = 0;
        itemNumber = "invalid";
        status = false;
    }

    public Product(String itemNumber, String name, int quantity, double price) {
        this.itemNumber = itemNumber;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // getter methods
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public boolean getStatus() {
        return status;
    }

    // setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // methods
    public double getTotalValue() {
        return (getQuantity() * getPrice());
    }

    public void addQuantity(int quantity) {
        if (getStatus() == false) {
            System.out.println("product discontinued！ （adding failed)");
            return;
        } else {
            setQuantity(getQuantity() + quantity);
        }
    }

    public void deductQuantity(int quantity) {
        if (getStatus() == false) {
            System.out.println("product discontinued！ deducting failed)");
            return;
        } else if (quantity > getQuantity()) {
            return;
        } else {
            setQuantity(getQuantity() - quantity);
        }
    }

    @Override
    public String toString() {
        return "Item number           : " + this.itemNumber + "\n" +
                "Product name          : " + this.name + "\n" +
                "Quantity available    : " + this.quantity + "\n" +
                "Price (RM)            : " + this.price + "\n" +
                "Inventory value (RM)  : " + (this.price * this.quantity) + "\n" +
                "Product status        : " + this.status;
    }

}
