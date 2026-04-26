package oop;

import java.util.Arrays;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.time.LocalDate;

public class StockManagement {

    // Display the contents of the products in the array/list
    public static void displayProducts(Stage primaryStage, Scene menuScene, Product[] products, int totalProducts) {
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
            } else if (products[i] instanceof AirConditioner) {
                AirConditioner ac = (AirConditioner) products[i];
                root.add(createCell(String.valueOf(countery)), counterx++, countery);
                root.add(createCell("Air Conditioner"), counterx++, countery);
                root.add(createCell(ac.getItemNumber()), counterx++, countery);
                root.add(createCell(ac.getName()), counterx++, countery);
                root.add(createCell(ac.getCoolingCapacity()), counterx++, countery);
                root.add(createCell(ac.getEnergyRating()), counterx++, countery);
                root.add(createCell(ac.isInverter() ? "Yes" : "No"), counterx++, countery);
                root.add(createCell(String.valueOf(ac.getQuantity())), counterx++, countery);
                root.add(createCell(String.valueOf(ac.getPrice())), counterx++, countery);
                root.add(createCell(String.valueOf(ac.getTotalValue())), counterx++, countery);
                root.add(createStatusCell(ac.getStatus()), counterx++, countery);
                countery++;
            } else if (products[i] instanceof Fan) {
                Fan fan = (Fan) products[i];
                root.add(createCell(String.valueOf(countery)), counterx++, countery);
                root.add(createCell("Fan"), counterx++, countery);
                root.add(createCell(fan.getItemNumber()), counterx++, countery);
                root.add(createCell(fan.getName()), counterx++, countery);
                root.add(createCell(fan.getSpeed()), counterx++, countery);
                root.add(createCell(fan.getSize()), counterx++, countery);
                root.add(createCell(fan.isOscillation() ? "Yes" : "No"), counterx++, countery);
                root.add(createCell(String.valueOf(fan.getQuantity())), counterx++, countery);
                root.add(createCell(String.valueOf(fan.getPrice())), counterx++, countery);
                root.add(createCell(String.valueOf(fan.getTotalValue())), counterx++, countery);
                root.add(createStatusCell(fan.getStatus()), counterx++, countery);
                countery++;
            } else if (products[i] instanceof Microwave) {
                Microwave mw = (Microwave) products[i];
                root.add(createCell(String.valueOf(countery)), counterx++, countery);
                root.add(createCell("Microwave"), counterx++, countery);
                root.add(createCell(mw.getItemNumber()), counterx++, countery);
                root.add(createCell(mw.getName()), counterx++, countery);
                root.add(createCell(mw.getPower()), counterx++, countery);
                root.add(createCell(mw.getCapacity()), counterx++, countery);
                root.add(createCell(mw.isChildLock() ? "Yes" : "No"), counterx++, countery);
                root.add(createCell(String.valueOf(mw.getQuantity())), counterx++, countery);
                root.add(createCell(String.valueOf(mw.getPrice())), counterx++, countery);
                root.add(createCell(String.valueOf(mw.getTotalValue())), counterx++, countery);
                root.add(createStatusCell(mw.getStatus()), counterx++, countery);
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

    // Add stock values to each identified product
    public static void addStock(Stage primaryStage, Scene menuScene, Product[] products, int totalProducts) {
        modifyStockUI(primaryStage, menuScene, products, totalProducts, true);
    }

    // Deduct stock values to each identified product
    public static void deductStock(Stage primaryStage, Scene menuScene, Product[] products, int totalProducts) {
        modifyStockUI(primaryStage, menuScene, products, totalProducts, false);
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

    // Allow user to set the status of a product
    public static void setProductStatus(Stage primaryStage, Scene menuScene, Product[] products, int totalProducts) {
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

    // 1. Get the maximum number of products the user wishes to store
    public static void getMaxProducts(Stage primaryStage, Scene menuScene, String userId, Product[] productsArray, int[] totalProductsRef) {
        javafx.scene.layout.VBox nextRoot = new javafx.scene.layout.VBox(20);
        javafx.scene.layout.HBox buttons = new javafx.scene.layout.HBox();
        javafx.scene.control.Button yes = new javafx.scene.control.Button("Yes");
        javafx.scene.control.Button no = new javafx.scene.control.Button("no");
        Label welcomeLabel = new Label("Welcome, " + userId + "! do you wanna add new product?");
        javafx.scene.control.TextField inputamount = new javafx.scene.control.TextField("");

        buttons.getChildren().addAll(yes, no);
        buttons.setAlignment(javafx.geometry.Pos.CENTER);
        javafx.scene.layout.HBox.setMargin(yes, new javafx.geometry.Insets(0, 20, 0, 0));
        yes.setPrefSize(80, 40);
        no.setPrefSize(80, 40);
        yes.setStyle("-fx-background-color: green");
        no.setStyle("-fx-background-color: red");

        nextRoot.setAlignment(javafx.geometry.Pos.CENTER);
        nextRoot.getChildren().addAll(welcomeLabel, buttons);
        Scene nextScene = new Scene(nextRoot, 610, 340);
        primaryStage.setScene(nextScene);

        yes.setOnAction(event -> {
            welcomeLabel.setText("how many product do you wanna add?");
            nextRoot.getChildren().remove(buttons);
            nextRoot.getChildren().add(inputamount);

            inputamount.setOnAction(amountEvent -> {
                try {
                    int amount = Integer.parseInt(inputamount.getText());
                    if (amount > 0) {
                        addWhat(primaryStage, menuScene, productsArray, totalProductsRef, amount, 1);
                    }
                } catch (NumberFormatException ex) {
                    welcomeLabel.setText("Invalid number! how many product do you wanna add?");
                }
            });
        });

        no.setOnAction(noaction -> {
            primaryStage.setScene(menuScene);
        });
    }

    // 3. Display the menu of the system
    public static void displayMenu(javafx.scene.layout.VBox root, javafx.scene.layout.StackPane[] boxes, javafx.scene.control.Button[] buttonsarr, Label title, String userId, Stage primaryStage) {
        title.setText("Hi " + userId + ", Welcome to Stock Management System");
        
        // Add Exit Button to the top right of the menu page
        javafx.scene.layout.HBox topBar = new javafx.scene.layout.HBox();
        topBar.setAlignment(javafx.geometry.Pos.TOP_RIGHT);
        topBar.setPadding(new javafx.geometry.Insets(10));
        javafx.scene.control.Button exitBtn = new javafx.scene.control.Button("Exit");
        exitBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
        exitBtn.setOnAction(exitEvent -> primaryStage.close());
        topBar.getChildren().add(exitBtn);
        root.getChildren().add(0, topBar);

        boxes[0].getChildren().add(buttonsarr[0]);
        boxes[1].getChildren().add(buttonsarr[1]);
        boxes[2].getChildren().add(buttonsarr[2]);
        boxes[3].getChildren().add(buttonsarr[3]);
        for (javafx.scene.control.Button allbutton : buttonsarr) {
            allbutton.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }
    }

    // Execute the appropriate methods (switch-case equivalent)
    public static void executeMenuChoice(javafx.scene.control.Button[] buttonsarr, Stage primaryStage, Scene menuScene, Product[] products, int[] totalProductsRef) {
        buttonsarr[0].setOnAction(viewaction -> {
            displayProducts(primaryStage, menuScene, products, totalProductsRef[0]);
        });

        buttonsarr[1].setOnAction(addaction -> {
            addStock(primaryStage, menuScene, products, totalProductsRef[0]);
        });

        buttonsarr[2].setOnAction(deductaction -> {
            deductStock(primaryStage, menuScene, products, totalProductsRef[0]);
        });

        buttonsarr[3].setOnAction(discontinueaction -> {
            setProductStatus(primaryStage, menuScene, products, totalProductsRef[0]);
        });
    }

    // Allow user to add a refrigerator or TV product
    public static void addWhat(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration) {
        if (currentIteration > totalToAdd) {
            javafx.scene.layout.VBox doneBox = new javafx.scene.layout.VBox(20);
            javafx.scene.control.Button proceed = new javafx.scene.control.Button("proceed");
            doneBox.setAlignment(javafx.geometry.Pos.CENTER);
            doneBox.getChildren().addAll(new Label("Successfully added " + totalToAdd + " products!"), proceed);
            primaryStage.setScene(new Scene(doneBox, 610, 340));
            proceed.setOnAction(proceedaction -> {
                primaryStage.setScene(scene2);
            });
            return;
        }

        javafx.scene.layout.VBox layout = new javafx.scene.layout.VBox(10);
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        Label title = new Label("Product " + currentIteration + " of " + totalToAdd + " - Choose Type:");

        javafx.scene.control.Button btnFridge = new javafx.scene.control.Button("Refrigerator");
        javafx.scene.control.Button btnTV = new javafx.scene.control.Button("TV");
        javafx.scene.control.Button btnMicrowave = new javafx.scene.control.Button("Microwave");
        javafx.scene.control.Button btnAC = new javafx.scene.control.Button("Air Conditioner");
        javafx.scene.control.Button btnFan = new javafx.scene.control.Button("Fan");

        javafx.scene.layout.HBox typeBox = new javafx.scene.layout.HBox(10);
        typeBox.setAlignment(javafx.geometry.Pos.CENTER);
        typeBox.getChildren().addAll(btnFridge, btnTV, btnMicrowave, btnAC, btnFan);

        layout.getChildren().addAll(title, typeBox);
        Scene scene = new Scene(layout, 610, 340);
        primaryStage.setScene(scene);

        btnFridge.setOnAction(e -> addRefrigerator(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration));
        btnTV.setOnAction(e -> addTV(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration));
        btnMicrowave.setOnAction(e -> addMicrowave(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration));
        btnAC.setOnAction(e -> addAirConditioner(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration));
        btnFan.setOnAction(e -> addFan(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration));
    }

    // Add product for refrigerator
    public static void addRefrigerator(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration) {
        javafx.scene.layout.VBox layout = new javafx.scene.layout.VBox(10);
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        layout.setPadding(new javafx.geometry.Insets(20));
        Label title = new Label("Adding Refrigerator (" + currentIteration + "/" + totalToAdd + ")");

        javafx.scene.control.TextField fName = new javafx.scene.control.TextField();
        fName.setPromptText("Name");
        javafx.scene.control.TextField fItemNum = new javafx.scene.control.TextField();
        fItemNum.setPromptText("Item Number");
        javafx.scene.control.TextField fQty = new javafx.scene.control.TextField();
        fQty.setPromptText("Quantity");
        javafx.scene.control.TextField fPrice = new javafx.scene.control.TextField();
        fPrice.setPromptText("Price");
        javafx.scene.control.TextField fDoor = new javafx.scene.control.TextField();
        fDoor.setPromptText("Door Design");
        javafx.scene.control.TextField fColor = new javafx.scene.control.TextField();
        fColor.setPromptText("Color");
        javafx.scene.control.TextField fCap = new javafx.scene.control.TextField();
        fCap.setPromptText("Capacity (int)");

        javafx.scene.control.Button btnSave = new javafx.scene.control.Button("Save Refrigerator");

        layout.getChildren().addAll(title, fName, fItemNum, fQty, fPrice, fDoor, fColor, fCap, btnSave);
        Scene scene = new Scene(layout, 610, 340);
        primaryStage.setScene(scene);

        btnSave.setOnAction(e -> {
            try {
                String name = fName.getText();
                String itemNum = fItemNum.getText();
                int qty = Integer.parseInt(fQty.getText());
                double price = Double.parseDouble(fPrice.getText());
                String door = fDoor.getText();
                String color = fColor.getText();
                int cap = Integer.parseInt(fCap.getText());

                productsArray[totalProductsRef[0]] = new Refrigerator(itemNum, name, qty, price, door, color, cap);
                totalProductsRef[0]++;

                addWhat(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration + 1);
            } catch (Exception ex) {
                title.setText("Invalid input! Please check your numbers.");
            }
        });
    }

    // Add product for TV
    public static void addTV(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration) {
        javafx.scene.layout.VBox layout = new javafx.scene.layout.VBox(10);
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        layout.setPadding(new javafx.geometry.Insets(20));
        Label title = new Label("Adding TV (" + currentIteration + "/" + totalToAdd + ")");

        javafx.scene.control.TextField fName = new javafx.scene.control.TextField();
        fName.setPromptText("Name");
        javafx.scene.control.TextField fItemNum = new javafx.scene.control.TextField();
        fItemNum.setPromptText("Item Number");
        javafx.scene.control.TextField fQty = new javafx.scene.control.TextField();
        fQty.setPromptText("Quantity");
        javafx.scene.control.TextField fPrice = new javafx.scene.control.TextField();
        fPrice.setPromptText("Price");
        javafx.scene.control.TextField fScreen = new javafx.scene.control.TextField();
        fScreen.setPromptText("Screen Type");
        javafx.scene.control.TextField fRes = new javafx.scene.control.TextField();
        fRes.setPromptText("Resolution");
        javafx.scene.control.TextField fSize = new javafx.scene.control.TextField();
        fSize.setPromptText("Display Size");

        javafx.scene.control.Button btnSave = new javafx.scene.control.Button("Save TV");

        layout.getChildren().addAll(title, fName, fItemNum, fQty, fPrice, fScreen, fRes, fSize, btnSave);
        Scene scene = new Scene(layout, 610, 340);
        primaryStage.setScene(scene);

        btnSave.setOnAction(e -> {
            try {
                String name = fName.getText();
                String itemNum = fItemNum.getText();
                int qty = Integer.parseInt(fQty.getText());
                double price = Double.parseDouble(fPrice.getText());
                String screen = fScreen.getText();
                String res = fRes.getText();
                String size = fSize.getText();

                productsArray[totalProductsRef[0]] = new TV(itemNum, name, qty, price, screen, res, size);
                totalProductsRef[0]++;

                addWhat(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration + 1);
            } catch (Exception ex) {
                title.setText("Invalid input! Please check your numbers.");
            }
        });
    }

    public static void addMicrowave(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration) {
        javafx.scene.layout.VBox layout = new javafx.scene.layout.VBox(10);
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        layout.setPadding(new javafx.geometry.Insets(20));
        Label title = new Label("Adding Microwave (" + currentIteration + "/" + totalToAdd + ")");

        javafx.scene.control.TextField fName = new javafx.scene.control.TextField();
        fName.setPromptText("Name");
        javafx.scene.control.TextField fItemNum = new javafx.scene.control.TextField();
        fItemNum.setPromptText("Item Number");
        javafx.scene.control.TextField fQty = new javafx.scene.control.TextField();
        fQty.setPromptText("Quantity");
        javafx.scene.control.TextField fPrice = new javafx.scene.control.TextField();
        fPrice.setPromptText("Price");
        javafx.scene.control.TextField fPower = new javafx.scene.control.TextField();
        fPower.setPromptText("Power");
        javafx.scene.control.TextField fCap = new javafx.scene.control.TextField();
        fCap.setPromptText("Capacity");
        javafx.scene.control.CheckBox cbChildLock = new javafx.scene.control.CheckBox("Child Lock");

        javafx.scene.control.Button btnSave = new javafx.scene.control.Button("Save Microwave");

        layout.getChildren().addAll(title, fName, fItemNum, fQty, fPrice, fPower, fCap, cbChildLock, btnSave);
        Scene scene = new Scene(layout, 610, 400);
        primaryStage.setScene(scene);

        btnSave.setOnAction(e -> {
            try {
                String name = fName.getText();
                String itemNum = fItemNum.getText();
                int qty = Integer.parseInt(fQty.getText());
                double price = Double.parseDouble(fPrice.getText());
                String power = fPower.getText();
                String cap = fCap.getText();
                boolean childLock = cbChildLock.isSelected();

                productsArray[totalProductsRef[0]] = new Microwave(itemNum, name, qty, price, power, cap, childLock);
                totalProductsRef[0]++;

                addWhat(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration + 1);
            } catch (Exception ex) {
                title.setText("Invalid input! Please check your numbers.");
            }
        });
    }

    public static void addAirConditioner(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration) {
        javafx.scene.layout.VBox layout = new javafx.scene.layout.VBox(10);
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        layout.setPadding(new javafx.geometry.Insets(20));
        Label title = new Label("Adding Air Conditioner (" + currentIteration + "/" + totalToAdd + ")");

        javafx.scene.control.TextField fName = new javafx.scene.control.TextField();
        fName.setPromptText("Name");
        javafx.scene.control.TextField fItemNum = new javafx.scene.control.TextField();
        fItemNum.setPromptText("Item Number");
        javafx.scene.control.TextField fQty = new javafx.scene.control.TextField();
        fQty.setPromptText("Quantity");
        javafx.scene.control.TextField fPrice = new javafx.scene.control.TextField();
        fPrice.setPromptText("Price");
        javafx.scene.control.TextField fCoolingCap = new javafx.scene.control.TextField();
        fCoolingCap.setPromptText("Cooling Capacity");
        javafx.scene.control.TextField fEnergyRating = new javafx.scene.control.TextField();
        fEnergyRating.setPromptText("Energy Rating");
        javafx.scene.control.CheckBox cbInverter = new javafx.scene.control.CheckBox("Inverter");

        javafx.scene.control.Button btnSave = new javafx.scene.control.Button("Save Air Conditioner");

        layout.getChildren().addAll(title, fName, fItemNum, fQty, fPrice, fCoolingCap, fEnergyRating, cbInverter, btnSave);
        Scene scene = new Scene(layout, 610, 400);
        primaryStage.setScene(scene);

        btnSave.setOnAction(e -> {
            try {
                String name = fName.getText();
                String itemNum = fItemNum.getText();
                int qty = Integer.parseInt(fQty.getText());
                double price = Double.parseDouble(fPrice.getText());
                String coolingCap = fCoolingCap.getText();
                String energyRating = fEnergyRating.getText();
                boolean inverter = cbInverter.isSelected();

                productsArray[totalProductsRef[0]] = new AirConditioner(itemNum, name, qty, price, coolingCap, energyRating, inverter);
                totalProductsRef[0]++;

                addWhat(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration + 1);
            } catch (Exception ex) {
                title.setText("Invalid input! Please check your numbers.");
            }
        });
    }

    public static void addFan(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration) {
        javafx.scene.layout.VBox layout = new javafx.scene.layout.VBox(10);
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        layout.setPadding(new javafx.geometry.Insets(20));
        Label title = new Label("Adding Fan (" + currentIteration + "/" + totalToAdd + ")");

        javafx.scene.control.TextField fName = new javafx.scene.control.TextField();
        fName.setPromptText("Name");
        javafx.scene.control.TextField fItemNum = new javafx.scene.control.TextField();
        fItemNum.setPromptText("Item Number");
        javafx.scene.control.TextField fQty = new javafx.scene.control.TextField();
        fQty.setPromptText("Quantity");
        javafx.scene.control.TextField fPrice = new javafx.scene.control.TextField();
        fPrice.setPromptText("Price");
        javafx.scene.control.TextField fSpeed = new javafx.scene.control.TextField();
        fSpeed.setPromptText("Speed");
        javafx.scene.control.TextField fSize = new javafx.scene.control.TextField();
        fSize.setPromptText("Size");
        javafx.scene.control.CheckBox cbOscillation = new javafx.scene.control.CheckBox("Oscillation");

        javafx.scene.control.Button btnSave = new javafx.scene.control.Button("Save Fan");

        layout.getChildren().addAll(title, fName, fItemNum, fQty, fPrice, fSpeed, fSize, cbOscillation, btnSave);
        Scene scene = new Scene(layout, 610, 400);
        primaryStage.setScene(scene);

        btnSave.setOnAction(e -> {
            try {
                String name = fName.getText();
                String itemNum = fItemNum.getText();
                int qty = Integer.parseInt(fQty.getText());
                double price = Double.parseDouble(fPrice.getText());
                String speed = fSpeed.getText();
                String size = fSize.getText();
                boolean oscillation = cbOscillation.isSelected();

                productsArray[totalProductsRef[0]] = new Fan(itemNum, name, qty, price, speed, size, oscillation);
                totalProductsRef[0]++;

                addWhat(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration + 1);
            } catch (Exception ex) {
                title.setText("Invalid input! Please check your numbers.");
            }
        }); 
    }
}