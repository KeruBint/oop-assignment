package oop;

import java.util.Arrays;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class StockManagement {

    public static void display(Stage primaryStage, Scene menuScene, Product[] products, int totalProducts) {
        GridPane root = new GridPane();
        int counterx = 0;
        int countery = 0;

        javafx.scene.layout.VBox mainLayout = new javafx.scene.layout.VBox(10);
        javafx.scene.control.Button backBtn = new javafx.scene.control.Button("Back to Menu");
        backBtn.setOnAction(e -> primaryStage.setScene(menuScene));

        mainLayout.getChildren().addAll(backBtn, root);
        mainLayout.setPadding(new javafx.geometry.Insets(10));

        primaryStage.setScene(new Scene(mainLayout, 1220, 680));

        // Add borders to all individual cells
        root.setGridLinesVisible(true);

        // Make GridPane have consistent sizing for columns
        for (int i = 0; i < 11; i++) {
            javafx.scene.layout.ColumnConstraints cc = new javafx.scene.layout.ColumnConstraints();
            cc.setPercentWidth(100.0 / 11.0);
            root.getColumnConstraints().add(cc);
        }

        // Add header row
        String[] headers = { "No.", "Type", "Item Num", "Name", "Design/Screen", "Color/Res", "Capacity/Size",
                "Quantity", "Price", "Total Value", "Status" };
        for (int i = 0; i < headers.length; i++) {
            Label headerLabel = createCell(headers[i]);
            headerLabel.setStyle("-fx-padding: 5px; -fx-font-weight: bold; -fx-background-color: lightgray;");
            headerLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // To make background color fill the cell
            root.add(headerLabel, counterx++, countery);
        }
        countery++; // Move to the next row for products

        for (int i = 0; i < totalProducts; i++) {
            counterx = 0; // Reset column counter for the new row
            if (products[i] instanceof Refrigerator) {
                Refrigerator fridge = (Refrigerator) products[i];
                root.add(createCell(String.valueOf(countery)), counterx++, countery);
                root.add(createCell("Refrigerator"), counterx++, countery);
                root.add(createCell(fridge.getItemNumber()), counterx++, countery);
                root.add(createCell(fridge.getName()), counterx++, countery);
                root.add(createCell(fridge.getDoorDesign()), counterx++, countery);
                root.add(createCell(fridge.getColor()), counterx++, countery);
                root.add(createCell(String.valueOf(fridge.getCapacity())), counterx++, countery);
                root.add(createCell(String.valueOf(fridge.getQuantity())), counterx++, countery);
                root.add(createCell(String.valueOf(fridge.getPrice())), counterx++, countery);
                root.add(createCell(String.valueOf(fridge.getTotalValue())), counterx++, countery);
                root.add(createStatusCell(fridge.getStatus()), counterx++, countery);
                countery++;
            } else if (products[i] instanceof TV) {
                TV tv = (TV) products[i];
                root.add(createCell(String.valueOf(countery)), counterx++, countery);
                root.add(createCell("TV"), counterx++, countery);
                root.add(createCell(tv.getItemNumber()), counterx++, countery);
                root.add(createCell(tv.getName()), counterx++, countery);
                root.add(createCell(tv.getScreenType()), counterx++, countery);
                root.add(createCell(tv.getResolution()), counterx++, countery);
                root.add(createCell(tv.getDisplaySize()), counterx++, countery);
                root.add(createCell(String.valueOf(tv.getQuantity())), counterx++, countery);
                root.add(createCell(String.valueOf(tv.getPrice())), counterx++, countery);
                root.add(createCell(String.valueOf(tv.getTotalValue())), counterx++, countery);
                root.add(createStatusCell(tv.getStatus()), counterx++, countery);
                countery++;
            }
        }

    }

    public static void modifyStockUI(Stage primaryStage, Scene menuScene, Product[] products, int totalProducts, boolean isAdd) {
        GridPane root = new GridPane();
        int counterx = 0;
        int countery = 0;

        javafx.scene.layout.VBox mainLayout = new javafx.scene.layout.VBox(10);
        javafx.scene.control.Button backBtn = new javafx.scene.control.Button("Back to Menu");
        backBtn.setOnAction(e -> primaryStage.setScene(menuScene));

        javafx.scene.layout.HBox bottomBar = new javafx.scene.layout.HBox(10);
        bottomBar.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        javafx.scene.control.Button actionBtn = new javafx.scene.control.Button(isAdd ? "Confirm Add Stock" : "Confirm Deduct Stock");
        bottomBar.getChildren().add(actionBtn);

        mainLayout.getChildren().addAll(backBtn, root, bottomBar);
        mainLayout.setPadding(new javafx.geometry.Insets(10));

        primaryStage.setScene(new Scene(mainLayout, 1220, 680));

        root.setGridLinesVisible(true);

        for (int i = 0; i < 12; i++) {
            javafx.scene.layout.ColumnConstraints cc = new javafx.scene.layout.ColumnConstraints();
            cc.setPercentWidth(100.0 / 12.0);
            root.getColumnConstraints().add(cc);
        }

        String[] headers = { "No.", "Type", "Item Num", "Name", "Design/Screen", "Color/Res", "Capacity/Size",
                "Quantity", "Price", "Total Value", "Status", isAdd ? "Add Amount" : "Deduct Amount" };
        for (int i = 0; i < headers.length; i++) {
            Label headerLabel = createCell(headers[i]);
            headerLabel.setStyle("-fx-padding: 5px; -fx-font-weight: bold; -fx-background-color: lightgray;");
            headerLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            root.add(headerLabel, counterx++, countery);
        }
        countery++;

        javafx.scene.control.TextField[] inputFields = new javafx.scene.control.TextField[totalProducts];

        for (int i = 0; i < totalProducts; i++) {
            counterx = 0;
            Product p = products[i];
            
            String noStr = String.valueOf(countery);
            String typeStr = p instanceof Refrigerator ? "Refrigerator" : (p instanceof TV ? "TV" : "Unknown");
            String itemNum = p.getItemNumber();
            String name = p.getName();
            String spec1 = p instanceof Refrigerator ? ((Refrigerator) p).getDoorDesign() : ((TV) p).getScreenType();
            String spec2 = p instanceof Refrigerator ? ((Refrigerator) p).getColor() : ((TV) p).getResolution();
            String spec3 = p instanceof Refrigerator ? String.valueOf(((Refrigerator) p).getCapacity()) : ((TV) p).getDisplaySize();
            String qty = String.valueOf(p.getQuantity());
            String price = String.valueOf(p.getPrice());
            String total = String.valueOf(p.getTotalValue());
            
            root.add(createCell(noStr), counterx++, countery);
            root.add(createCell(typeStr), counterx++, countery);
            root.add(createCell(itemNum), counterx++, countery);
            root.add(createCell(name), counterx++, countery);
            root.add(createCell(spec1), counterx++, countery);
            root.add(createCell(spec2), counterx++, countery);
            root.add(createCell(spec3), counterx++, countery);
            root.add(createCell(qty), counterx++, countery);
            root.add(createCell(price), counterx++, countery);
            root.add(createCell(total), counterx++, countery);
            root.add(createStatusCell(p.getStatus()), counterx++, countery);
            
            javafx.scene.control.TextField inputField = new javafx.scene.control.TextField();
            inputField.setPromptText("0");
            if (!p.getStatus()) {
                inputField.setDisable(true);
            }
            inputFields[i] = inputField;
            root.add(inputField, counterx++, countery);
            
            countery++;
        }

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        bottomBar.getChildren().add(0, errorLabel); // Add error label to the left of the button

        actionBtn.setOnAction(e -> {
            boolean hasError = false;
            for (int i = 0; i < totalProducts; i++) {
                String text = inputFields[i].getText();
                if (text != null && !text.trim().isEmpty()) {
                    try {
                        int amount = Integer.parseInt(text.trim());
                        if (amount > 0) {
                            if (!products[i].getStatus()) {
                                hasError = true;
                                errorLabel.setText("Error: Cannot " + (isAdd ? "add to" : "deduct from") + " discontinued product " + products[i].getName() + ".");
                                return; // Stop and do not return to menu
                            }
                            if (isAdd) {
                                products[i].addQuantity(amount);
                            } else {
                                if (amount <= products[i].getQuantity()) {
                                    products[i].deductQuantity(amount);
                                } else {
                                    hasError = true;
                                    errorLabel.setText("Error: Cannot deduct " + amount + " from " + products[i].getName() + ". Only " + products[i].getQuantity() + " available.");
                                    return; // Stop and do not return to menu
                                }
                            }
                        }
                    } catch (NumberFormatException ex) {
                        hasError = true;
                        errorLabel.setText("Error: Invalid number entered.");
                        return; // Stop and do not return to menu
                    }
                }
            }
            
            if (!hasError) {
                primaryStage.setScene(menuScene);
            }
        });
    }

    private static Label createCell(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-padding: 5px;"); // Padding for visual spacing
        return label;
    }

    private static Label createStatusCell(boolean status) {
        Label label = new Label(String.valueOf(status));
        label.setStyle("-fx-padding: 5px; -fx-text-fill: " + (status ? "green" : "red") + "; -fx-font-weight: bold;");
        return label;
    }

    public static void discontinueUI(Stage primaryStage, Scene menuScene, Product[] products, int totalProducts) {
        GridPane root = new GridPane();
        int counterx = 0;
        int countery = 0;

        javafx.scene.layout.VBox mainLayout = new javafx.scene.layout.VBox(10);
        javafx.scene.control.Button backBtn = new javafx.scene.control.Button("Back to Menu");
        backBtn.setOnAction(e -> primaryStage.setScene(menuScene));

        javafx.scene.layout.HBox bottomBar = new javafx.scene.layout.HBox(10);
        bottomBar.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
        javafx.scene.control.Button actionBtn = new javafx.scene.control.Button("Confirm Discontinue");
        bottomBar.getChildren().add(actionBtn);

        mainLayout.getChildren().addAll(backBtn, root, bottomBar);
        mainLayout.setPadding(new javafx.geometry.Insets(10));

        primaryStage.setScene(new Scene(mainLayout, 1220, 680));

        root.setGridLinesVisible(true);

        for (int i = 0; i < 12; i++) {
            javafx.scene.layout.ColumnConstraints cc = new javafx.scene.layout.ColumnConstraints();
            cc.setPercentWidth(100.0 / 12.0);
            root.getColumnConstraints().add(cc);
        }

        String[] headers = { "No.", "Type", "Item Num", "Name", "Design/Screen", "Color/Res", "Capacity/Size",
                "Quantity", "Price", "Total Value", "Status", "Discontinue" };
        for (int i = 0; i < headers.length; i++) {
            Label headerLabel = createCell(headers[i]);
            headerLabel.setStyle("-fx-padding: 5px; -fx-font-weight: bold; -fx-background-color: lightgray;");
            headerLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            root.add(headerLabel, counterx++, countery);
        }
        countery++;

        javafx.scene.control.CheckBox[] checkBoxes = new javafx.scene.control.CheckBox[totalProducts];

        for (int i = 0; i < totalProducts; i++) {
            counterx = 0;
            Product p = products[i];
            
            String noStr = String.valueOf(countery);
            String typeStr = p instanceof Refrigerator ? "Refrigerator" : (p instanceof TV ? "TV" : "Unknown");
            String itemNum = p.getItemNumber();
            String name = p.getName();
            String spec1 = p instanceof Refrigerator ? ((Refrigerator) p).getDoorDesign() : ((TV) p).getScreenType();
            String spec2 = p instanceof Refrigerator ? ((Refrigerator) p).getColor() : ((TV) p).getResolution();
            String spec3 = p instanceof Refrigerator ? String.valueOf(((Refrigerator) p).getCapacity()) : ((TV) p).getDisplaySize();
            String qty = String.valueOf(p.getQuantity());
            String price = String.valueOf(p.getPrice());
            String total = String.valueOf(p.getTotalValue());
            
            root.add(createCell(noStr), counterx++, countery);
            root.add(createCell(typeStr), counterx++, countery);
            root.add(createCell(itemNum), counterx++, countery);
            root.add(createCell(name), counterx++, countery);
            root.add(createCell(spec1), counterx++, countery);
            root.add(createCell(spec2), counterx++, countery);
            root.add(createCell(spec3), counterx++, countery);
            root.add(createCell(qty), counterx++, countery);
            root.add(createCell(price), counterx++, countery);
            root.add(createCell(total), counterx++, countery);
            root.add(createStatusCell(p.getStatus()), counterx++, countery);
            
            javafx.scene.control.CheckBox checkBox = new javafx.scene.control.CheckBox();
            if (!p.getStatus()) {
                checkBox.setDisable(true);
            }
            javafx.scene.layout.HBox cbContainer = new javafx.scene.layout.HBox(checkBox);
            cbContainer.setAlignment(javafx.geometry.Pos.CENTER);
            cbContainer.setStyle("-fx-padding: 5px;");
            
            checkBoxes[i] = checkBox;
            root.add(cbContainer, counterx++, countery);
            
            countery++;
        }

        actionBtn.setOnAction(e -> {
            for (int i = 0; i < totalProducts; i++) {
                if (checkBoxes[i].isSelected()) {
                    products[i].setStatus(false);
                }
            }
            primaryStage.setScene(menuScene);
        });
    }
}
