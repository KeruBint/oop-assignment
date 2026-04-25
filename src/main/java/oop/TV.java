package oop;

public class TV extends Product {
    private String screenType;
    private String resolution;
    private String displaySize;

    public TV(String itemNumber, String name, int quantity, double price, String screenType, String resolution,
            String displaySize) {
        super(itemNumber, name, quantity, price);
        this.screenType = screenType;
        this.resolution = resolution;
        this.displaySize = displaySize;

    }

    public String getScreenType() {
        return screenType;
    }

    public String getResolution() {
        return resolution;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    // Setters
    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    @Override
    public String toString() {
        return "Item number : " + getItemNumber() +
                "\nProduct name : " + getName() +
                "\nScreen type : " + screenType +
                "\nResolution : " + resolution +
                "\nDisplay size : " + displaySize +
                "\nQuantity available: " + getQuantity() +
                "\nPrice (RM) : " + getPrice() +
                "\nInventory value (RM): " + getTotalValue() +
                "\nProduct status : " + (getStatus() ? "Active" : "Discontinued");
    }

}