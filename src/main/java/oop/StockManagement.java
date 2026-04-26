package oop;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StockManagement {

    // UI Styling
    private static final String BG_COLOR = "-fx-background-color: #f4f7f6;";
    private static final String CARD_STYLE = "-fx-background-color: white; -fx-background-radius: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 5);";
    private static final String BTN_PRIMARY = "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand; -fx-padding: 8px 15px;";
    private static final String BTN_SUCCESS = "-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand; -fx-padding: 8px 15px;";
    private static final String BTN_DANGER = "-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5px; -fx-cursor: hand; -fx-padding: 8px 15px;";
    private static final String INPUT_STYLE = "-fx-padding: 8px; -fx-background-radius: 5px; -fx-border-radius: 5px; -fx-border-color: #bdc3c7; -fx-border-width: 1px; -fx-background-color: white;";

    // Main Menu

    public static void displayMenu(VBox root, StackPane[] boxes, Button[] buttonsarr, Label title, String userId, Stage primaryStage) {
        title.setText("Hi " + userId + ",\nWhat would you like to do?");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-text-alignment: center;");
        
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_RIGHT);
        topBar.setPadding(new Insets(10));
        Button exitBtn = new Button("Exit Application");
        exitBtn.setStyle(BTN_DANGER);
        exitBtn.setOnAction(exitEvent -> primaryStage.close());
        topBar.getChildren().add(exitBtn);
        root.getChildren().add(0, topBar);

        for (int i = 0; i < 4; i++) {
            boxes[i].getChildren().clear(); 
            boxes[i].getChildren().add(buttonsarr[i]);
            buttonsarr[i].setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
            buttonsarr[i].setStyle("-fx-background-color: transparent; -fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2980b9; -fx-cursor: hand;");
        }
    }

    public static void executeMenuChoice(Button[] buttonsarr, Stage primaryStage, Scene menuScene, Product[] products, int[] totalProductsRef) {
        buttonsarr[0].setOnAction(e -> displayProducts(primaryStage, menuScene, products, totalProductsRef[0]));
        buttonsarr[1].setOnAction(e -> addStock(primaryStage, menuScene, products, totalProductsRef[0]));
        buttonsarr[2].setOnAction(e -> deductStock(primaryStage, menuScene, products, totalProductsRef[0]));
        buttonsarr[3].setOnAction(e -> setProductStatus(primaryStage, menuScene, products, totalProductsRef[0]));
    }

    public static void getMaxProducts(Stage primaryStage, Scene menuScene, String userId, Product[] productsArray, int[] totalProductsRef) {
        VBox mainBg = new VBox();
        mainBg.setStyle(BG_COLOR);
        mainBg.setAlignment(Pos.CENTER);

        VBox card = new VBox(20);
        card.setStyle(CARD_STYLE);
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(400);

        Label welcomeLabel = new Label("Welcome, " + userId + "!\nDo you want to add new products?");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-text-alignment: center; -fx-font-weight: bold;");
        welcomeLabel.setAlignment(Pos.CENTER);
        welcomeLabel.setWrapText(true); 

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        Button yes = new Button("Yes, Add Items");
        Button no = new Button("No, Go to Menu");
        yes.setStyle(BTN_SUCCESS);
        no.setStyle(BTN_PRIMARY);
        buttons.getChildren().addAll(yes, no);

        TextField inputamount = new TextField();
        inputamount.setPromptText("Enter amount...");
        inputamount.setStyle(INPUT_STYLE);
        inputamount.setMaxWidth(150);

        card.getChildren().addAll(welcomeLabel, buttons);
        mainBg.getChildren().add(card);
        
        primaryStage.setScene(new Scene(mainBg, 900, 500));

        yes.setOnAction(event -> {
            welcomeLabel.setText("How many products do you want to add?");
            card.getChildren().remove(buttons);
            card.getChildren().add(inputamount);

            inputamount.setOnAction(amountEvent -> {
                try {
                    int amount = Integer.parseInt(inputamount.getText());
                    if (amount > 0) {
                        addWhat(primaryStage, menuScene, productsArray, totalProductsRef, amount, 1);
                    }
                } catch (NumberFormatException ex) {
                    welcomeLabel.setText("Invalid number! How many products?");
                    welcomeLabel.setStyle("-fx-font-size: 16px; -fx-text-alignment: center; -fx-font-weight: bold; -fx-text-fill: red;");
                }
            });
        });

        no.setOnAction(e -> primaryStage.setScene(menuScene));
    }


    // Products
    public static void addWhat(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration) {
        if (currentIteration > totalToAdd) {
            VBox mainBg = new VBox();
            mainBg.setStyle(BG_COLOR);
            mainBg.setAlignment(Pos.CENTER);
            
            VBox card = new VBox(20);
            card.setStyle(CARD_STYLE);
            card.setPadding(new Insets(40));
            card.setAlignment(Pos.CENTER);

            Label successLabel = new Label("Successfully added " + totalToAdd + " products!");
            successLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #27ae60;");
            
            Button proceed = new Button("Proceed to Menu");
            proceed.setStyle(BTN_PRIMARY);
            proceed.setOnAction(e -> primaryStage.setScene(scene2));

            card.getChildren().addAll(successLabel, proceed);
            mainBg.getChildren().add(card);
            primaryStage.setScene(new Scene(mainBg, 900, 500));
            return;
        }

        VBox mainBg = new VBox();
        mainBg.setStyle(BG_COLOR);
        mainBg.setAlignment(Pos.CENTER);

        VBox card = new VBox(20);
        card.setStyle(CARD_STYLE);
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER);

        Label title = new Label("Product " + currentIteration + " of " + totalToAdd + "\nChoose Product Type:");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-alignment: center;");

        HBox typeBox = new HBox(15);
        typeBox.setAlignment(Pos.CENTER);
        
        Button btnFridge = new Button("Refrigerator");
        Button btnTV = new Button("TV");
        Button btnMicrowave = new Button("Microwave");
        Button btnAC = new Button("Air Conditioner");
        Button btnFan = new Button("Fan");

        Button[] typeBtns = {btnFridge, btnTV, btnMicrowave, btnAC, btnFan};
        for(Button btn : typeBtns) {
            btn.setStyle(BTN_PRIMARY);
        }

        typeBox.getChildren().addAll(typeBtns);
        card.getChildren().addAll(title, typeBox);
        mainBg.getChildren().add(card);
        
        primaryStage.setScene(new Scene(mainBg, 900, 500));

        btnFridge.setOnAction(e -> addRefrigerator(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration));
        btnTV.setOnAction(e -> addTV(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration));
        btnMicrowave.setOnAction(e -> addMicrowave(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration));
        btnAC.setOnAction(e -> addAirConditioner(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration));
        btnFan.setOnAction(e -> addFan(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration));
    }

    //check constraint for each field, if valid add to array and move to next product, else show error message and stay on same product
    private static void showProductForm(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration, String productType, String[] fieldPrompts, FormSubmitAction submitAction) {
        VBox mainBg = new VBox();
        mainBg.setStyle(BG_COLOR);
        mainBg.setAlignment(Pos.CENTER);

        VBox card = new VBox(15);
        card.setStyle(CARD_STYLE);
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(500);

        Label title = new Label("Adding " + productType + " (" + currentIteration + "/" + totalToAdd + ")");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        TextField fName = createStyledTextField("Product Name");
        TextField fItemNum = createStyledTextField("Item Number");
        TextField fQty = createStyledTextField("Quantity");
        TextField fPrice = createStyledTextField("Price");

        card.getChildren().addAll(title, fName, fItemNum, fQty, fPrice);

        TextField[] customFields = new TextField[fieldPrompts.length];
        for (int i = 0; i < fieldPrompts.length; i++) {
            if (!fieldPrompts[i].startsWith("CHECKBOX:")) {
                customFields[i] = createStyledTextField(fieldPrompts[i]);
                card.getChildren().add(customFields[i]);
            }
        }

        CheckBox customCheck = new CheckBox();
        boolean hasCheckbox = false;
        for (String prompt : fieldPrompts) {
            if (prompt.startsWith("CHECKBOX:")) {
                customCheck.setText(prompt.replace("CHECKBOX:", ""));
                customCheck.setStyle("-fx-font-size: 14px;");
                card.getChildren().add(customCheck);
                hasCheckbox = true;
            }
        }

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");

        Button btnSave = new Button("Save " + productType);
        btnSave.setStyle(BTN_SUCCESS);
        card.getChildren().addAll(errorLabel, btnSave);
        mainBg.getChildren().add(card);

        primaryStage.setScene(new Scene(mainBg, 900, 600));

        final boolean checkVal = hasCheckbox;
        btnSave.setOnAction(e -> {
         
            if (fName.getText().trim().isEmpty() || fItemNum.getText().trim().isEmpty() || 
                fQty.getText().trim().isEmpty() || fPrice.getText().trim().isEmpty()) {
                errorLabel.setText("Error: All standard fields must be filled out!");
                return; 
            }
            for (TextField customField : customFields) {
                if (customField != null && customField.getText().trim().isEmpty()) {
                    errorLabel.setText("Error: All specification fields must be filled out!");
                    return; 
                }
            }
            
            try {
                String name = fName.getText().trim();
                String itemNum = fItemNum.getText().trim();
                int qty = Integer.parseInt(fQty.getText().trim());
                double price = Double.parseDouble(fPrice.getText().trim());
                
                submitAction.submit(name, itemNum, qty, price, customFields, checkVal ? customCheck.isSelected() : false);
                
                totalProductsRef[0]++;
                addWhat(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration + 1);
            } catch (NumberFormatException ex) {

                errorLabel.setText("Error: Quantity and Price must be valid numbers.");
            } catch (Exception ex) {
                errorLabel.setText("Invalid input! Please check your values.");
            }
    });
    }
    

    interface FormSubmitAction {
        void submit(String name, String itemNum, int qty, double price, TextField[] customFields, boolean checkStatus);
    }

    private static TextField createStyledTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setStyle(INPUT_STYLE);
        tf.setMaxWidth(300);
        return tf;
    }

    public static void addRefrigerator(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration) {
        showProductForm(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration, "Refrigerator", 
            new String[]{"Door Design", "Color", "Capacity (int)"}, 
            (name, itemNum, qty, price, fields, check) -> productsArray[totalProductsRef[0]] = new Refrigerator(itemNum, name, qty, price, fields[0].getText(), fields[1].getText(), Integer.parseInt(fields[2].getText())));
    }

    public static void addTV(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration) {
         showProductForm(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration, "TV", 
            new String[]{"Screen Type", "Resolution", "Display Size"}, 
            (name, itemNum, qty, price, fields, check) -> productsArray[totalProductsRef[0]] = new TV(itemNum, name, qty, price, fields[0].getText(), fields[1].getText(), fields[2].getText()));
    }

    public static void addMicrowave(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration) {
        showProductForm(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration, "Microwave", 
            new String[]{"Power", "Capacity", "CHECKBOX:Child Lock"}, 
            (name, itemNum, qty, price, fields, check) -> productsArray[totalProductsRef[0]] = new Microwave(itemNum, name, qty, price, fields[0].getText(), fields[1].getText(), check));
    }

    public static void addAirConditioner(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration) {
        showProductForm(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration, "Air Conditioner", 
            new String[]{"Cooling Capacity", "Energy Rating", "CHECKBOX:Inverter"}, 
            (name, itemNum, qty, price, fields, check) -> productsArray[totalProductsRef[0]] = new AirConditioner(itemNum, name, qty, price, fields[0].getText(), fields[1].getText(), check));
    }

    public static void addFan(Stage primaryStage, Scene scene2, Product[] productsArray, int[] totalProductsRef, int totalToAdd, int currentIteration) {
         showProductForm(primaryStage, scene2, productsArray, totalProductsRef, totalToAdd, currentIteration, "Fan", 
            new String[]{"Speed", "Size", "CHECKBOX:Oscillation"}, 
            (name, itemNum, qty, price, fields, check) -> productsArray[totalProductsRef[0]] = new Fan(itemNum, name, qty, price, fields[0].getText(), fields[1].getText(), check));
    }

    
    // Diaplay Tables
    public static void displayProducts(Stage primaryStage, Scene menuScene, Product[] products, int totalProducts) {
        VBox mainLayout = new VBox(25); 
        mainLayout.setStyle(BG_COLOR);
        mainLayout.setPadding(new Insets(20));
        
        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: #f4f7f6;");

        Button backBtn = new Button("← Back to Menu");
        backBtn.setStyle(BTN_PRIMARY);
        backBtn.setOnAction(e -> primaryStage.setScene(menuScene));
        mainLayout.getChildren().add(backBtn);

        GridPane fridgeTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Door Design", "Color", "Capacity", "Qty", "Price", "Total Value", "Status"});
        GridPane tvTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Screen Type", "Resolution", "Display Size", "Qty", "Price", "Total Value", "Status"});
        GridPane acTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Cooling Cap", "Energy Rating", "Inverter", "Qty", "Price", "Total Value", "Status"});
        GridPane fanTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Speed", "Size", "Oscillation", "Qty", "Price", "Total Value", "Status"});
        GridPane mwTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Power", "Capacity", "Child Lock", "Qty", "Price", "Total Value", "Status"});

        int fRow = 1, tRow = 1, aRow = 1, fanRow = 1, mRow = 1;

        for (int i = 0; i < totalProducts; i++) {
            if (products[i] instanceof Refrigerator) {
                Refrigerator p = (Refrigerator) products[i];
                addTableRow(fridgeTable, fRow++, p.getItemNumber(), p.getName(), p.getDoorDesign(), p.getColor(), String.valueOf(p.getCapacity()), p.getQuantity(), p.getPrice(), p.getTotalValue(), p.getStatus());
            } else if (products[i] instanceof TV) {
                TV p = (TV) products[i];
                addTableRow(tvTable, tRow++, p.getItemNumber(), p.getName(), p.getScreenType(), p.getResolution(), p.getDisplaySize(), p.getQuantity(), p.getPrice(), p.getTotalValue(), p.getStatus());
            } else if (products[i] instanceof AirConditioner) {
                AirConditioner p = (AirConditioner) products[i];
                addTableRow(acTable, aRow++, p.getItemNumber(), p.getName(), p.getCoolingCapacity(), p.getEnergyRating(), p.isInverter() ? "Yes" : "No", p.getQuantity(), p.getPrice(), p.getTotalValue(), p.getStatus());
            } else if (products[i] instanceof Fan) {
                Fan p = (Fan) products[i];
                addTableRow(fanTable, fanRow++, p.getItemNumber(), p.getName(), p.getSpeed(), p.getSize(), p.isOscillation() ? "Yes" : "No", p.getQuantity(), p.getPrice(), p.getTotalValue(), p.getStatus());
            } else if (products[i] instanceof Microwave) {
                Microwave p = (Microwave) products[i];
                addTableRow(mwTable, mRow++, p.getItemNumber(), p.getName(), p.getPower(), p.getCapacity(), p.isChildLock() ? "Yes" : "No", p.getQuantity(), p.getPrice(), p.getTotalValue(), p.getStatus());
            }
        }

        if (fRow > 1) addTableToLayout(mainLayout, "Refrigerators", fridgeTable);
        if (tRow > 1) addTableToLayout(mainLayout, "Televisions", tvTable);
        if (aRow > 1) addTableToLayout(mainLayout, "Air Conditioners", acTable);
        if (fanRow > 1) addTableToLayout(mainLayout, "Fans", fanTable);
        if (mRow > 1) addTableToLayout(mainLayout, "Microwaves", mwTable);

        primaryStage.setScene(new Scene(scrollPane, 1220, 680));
    }

    public static void modifyStockUI(Stage primaryStage, Scene menuScene, Product[] products, int totalProducts, boolean isAdd) {
        VBox mainLayout = new VBox(25);
        mainLayout.setStyle(BG_COLOR);
        mainLayout.setPadding(new Insets(20));

        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: #f4f7f6;");

        HBox topBar = new HBox();
        Button backBtn = new Button("← Back to Menu");
        backBtn.setStyle(BTN_PRIMARY);
        backBtn.setOnAction(e -> primaryStage.setScene(menuScene));
        topBar.getChildren().add(backBtn);

        Label title = new Label(isAdd ? "Add Stock to Existing Products" : "Deduct Stock from Existing Products");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        mainLayout.getChildren().addAll(topBar, title);

        String actionCol = isAdd ? "Add Amount" : "Deduct Amount";
        
        GridPane fridgeTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Door Design", "Color", "Capacity", "Qty", "Price", "Total Value", "Status", actionCol});
        GridPane tvTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Screen Type", "Resolution", "Display Size", "Qty", "Price", "Total Value", "Status", actionCol});
        GridPane acTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Cooling Cap", "Energy Rating", "Inverter", "Qty", "Price", "Total Value", "Status", actionCol});
        GridPane fanTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Speed", "Size", "Oscillation", "Qty", "Price", "Total Value", "Status", actionCol});
        GridPane mwTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Power", "Capacity", "Child Lock", "Qty", "Price", "Total Value", "Status", actionCol});

        TextField[] inputFields = new TextField[totalProducts];
        int fRow = 1, tRow = 1, aRow = 1, fanRow = 1, mRow = 1;

        for (int i = 0; i < totalProducts; i++) {
            Product p = products[i];
            
            TextField inputField = new TextField();
            inputField.setPromptText("0");
            inputField.setStyle(INPUT_STYLE);
            if (!p.getStatus()) inputField.setDisable(true);
            inputFields[i] = inputField;

            if (p instanceof Refrigerator) {
                Refrigerator r = (Refrigerator) p;
                addTableRow(fridgeTable, fRow, r.getItemNumber(), r.getName(), r.getDoorDesign(), r.getColor(), String.valueOf(r.getCapacity()), r.getQuantity(), r.getPrice(), r.getTotalValue(), r.getStatus());
                fridgeTable.add(inputField, 10, fRow++);
            } else if (p instanceof TV) {
                TV t = (TV) p;
                addTableRow(tvTable, tRow, t.getItemNumber(), t.getName(), t.getScreenType(), t.getResolution(), t.getDisplaySize(), t.getQuantity(), t.getPrice(), t.getTotalValue(), t.getStatus());
                tvTable.add(inputField, 10, tRow++);
            } else if (p instanceof AirConditioner) {
                AirConditioner a = (AirConditioner) p;
                addTableRow(acTable, aRow, a.getItemNumber(), a.getName(), a.getCoolingCapacity(), a.getEnergyRating(), a.isInverter() ? "Yes" : "No", a.getQuantity(), a.getPrice(), a.getTotalValue(), a.getStatus());
                acTable.add(inputField, 10, aRow++);
            } else if (p instanceof Fan) {
                Fan f = (Fan) p;
                addTableRow(fanTable, fanRow, f.getItemNumber(), f.getName(), f.getSpeed(), f.getSize(), f.isOscillation() ? "Yes" : "No", f.getQuantity(), f.getPrice(), f.getTotalValue(), f.getStatus());
                fanTable.add(inputField, 10, fanRow++);
            } else if (p instanceof Microwave) {
                Microwave m = (Microwave) p;
                addTableRow(mwTable, mRow, m.getItemNumber(), m.getName(), m.getPower(), m.getCapacity(), m.isChildLock() ? "Yes" : "No", m.getQuantity(), m.getPrice(), m.getTotalValue(), m.getStatus());
                mwTable.add(inputField, 10, mRow++);
            }
        }

        if (fRow > 1) addTableToLayout(mainLayout, "Refrigerators", fridgeTable);
        if (tRow > 1) addTableToLayout(mainLayout, "Televisions", tvTable);
        if (aRow > 1) addTableToLayout(mainLayout, "Air Conditioners", acTable);
        if (fanRow > 1) addTableToLayout(mainLayout, "Fans", fanTable);
        if (mRow > 1) addTableToLayout(mainLayout, "Microwaves", mwTable);

        HBox bottomBar = new HBox(15);
        bottomBar.setAlignment(Pos.CENTER_RIGHT);
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold; -fx-font-size: 14px;");
        Button actionBtn = new Button(isAdd ? "Confirm Add Stock" : "Confirm Deduct Stock");
        actionBtn.setStyle(BTN_SUCCESS);
        bottomBar.getChildren().addAll(errorLabel, actionBtn);
        
        mainLayout.getChildren().add(bottomBar);
        primaryStage.setScene(new Scene(scrollPane, 1220, 680));

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
                                errorLabel.setText("Error: Cannot modify discontinued product.");
                                return; 
                            }
                            if (isAdd) {
                                products[i].addQuantity(amount);
                            } else {
                                if (amount <= products[i].getQuantity()) {
                                    products[i].deductQuantity(amount);
                                } else {
                                    hasError = true;
                                    errorLabel.setText("Error: Exceeds available quantity for " + products[i].getName());
                                    return; 
                                }
                            }
                        }
                    } catch (NumberFormatException ex) {
                        hasError = true;
                        errorLabel.setText("Error: Invalid number entered.");
                        return;
                    }
                }
            }
            if (!hasError) primaryStage.setScene(menuScene);
        });
    }

    public static void setProductStatus(Stage primaryStage, Scene menuScene, Product[] products, int totalProducts) {
        VBox mainLayout = new VBox(25);
        mainLayout.setStyle(BG_COLOR);
        mainLayout.setPadding(new Insets(20));

        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: #f4f7f6;");

        HBox topBar = new HBox();
        Button backBtn = new Button("← Back to Menu");
        backBtn.setStyle(BTN_PRIMARY);
        backBtn.setOnAction(e -> primaryStage.setScene(menuScene));
        topBar.getChildren().add(backBtn);

        Label title = new Label("Discontinue Products");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        mainLayout.getChildren().addAll(topBar, title);

        String actionCol = "Discontinue";
        GridPane fridgeTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Door Design", "Color", "Capacity", "Qty", "Price", "Total Value", "Status", actionCol});
        GridPane tvTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Screen Type", "Resolution", "Display Size", "Qty", "Price", "Total Value", "Status", actionCol});
        GridPane acTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Cooling Cap", "Energy Rating", "Inverter", "Qty", "Price", "Total Value", "Status", actionCol});
        GridPane fanTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Speed", "Size", "Oscillation", "Qty", "Price", "Total Value", "Status", actionCol});
        GridPane mwTable = createProductTable(new String[]{"No.", "Item Num", "Name", "Power", "Capacity", "Child Lock", "Qty", "Price", "Total Value", "Status", actionCol});

        CheckBox[] checkBoxes = new CheckBox[totalProducts];
        int fRow = 1, tRow = 1, aRow = 1, fanRow = 1, mRow = 1;

        for (int i = 0; i < totalProducts; i++) {
            Product p = products[i];
            
            CheckBox checkBox = new CheckBox();
            if (!p.getStatus()) checkBox.setDisable(true);
            checkBoxes[i] = checkBox;
            
            HBox cbContainer = new HBox(checkBox);
            cbContainer.setAlignment(Pos.CENTER);
            cbContainer.setPadding(new Insets(5));

            if (p instanceof Refrigerator) {
                Refrigerator r = (Refrigerator) p;
                addTableRow(fridgeTable, fRow, r.getItemNumber(), r.getName(), r.getDoorDesign(), r.getColor(), String.valueOf(r.getCapacity()), r.getQuantity(), r.getPrice(), r.getTotalValue(), r.getStatus());
                fridgeTable.add(cbContainer, 10, fRow++);
            } else if (p instanceof TV) {
                TV t = (TV) p;
                addTableRow(tvTable, tRow, t.getItemNumber(), t.getName(), t.getScreenType(), t.getResolution(), t.getDisplaySize(), t.getQuantity(), t.getPrice(), t.getTotalValue(), t.getStatus());
                tvTable.add(cbContainer, 10, tRow++);
            } else if (p instanceof AirConditioner) {
                AirConditioner a = (AirConditioner) p;
                addTableRow(acTable, aRow, a.getItemNumber(), a.getName(), a.getCoolingCapacity(), a.getEnergyRating(), a.isInverter() ? "Yes" : "No", a.getQuantity(), a.getPrice(), a.getTotalValue(), a.getStatus());
                acTable.add(cbContainer, 10, aRow++);
            } else if (p instanceof Fan) {
                Fan f = (Fan) p;
                addTableRow(fanTable, fanRow, f.getItemNumber(), f.getName(), f.getSpeed(), f.getSize(), f.isOscillation() ? "Yes" : "No", f.getQuantity(), f.getPrice(), f.getTotalValue(), f.getStatus());
                fanTable.add(cbContainer, 10, fanRow++);
            } else if (p instanceof Microwave) {
                Microwave m = (Microwave) p;
                addTableRow(mwTable, mRow, m.getItemNumber(), m.getName(), m.getPower(), m.getCapacity(), m.isChildLock() ? "Yes" : "No", m.getQuantity(), m.getPrice(), m.getTotalValue(), m.getStatus());
                mwTable.add(cbContainer, 10, mRow++);
            }
        }

        if (fRow > 1) addTableToLayout(mainLayout, "Refrigerators", fridgeTable);
        if (tRow > 1) addTableToLayout(mainLayout, "Televisions", tvTable);
        if (aRow > 1) addTableToLayout(mainLayout, "Air Conditioners", acTable);
        if (fanRow > 1) addTableToLayout(mainLayout, "Fans", fanTable);
        if (mRow > 1) addTableToLayout(mainLayout, "Microwaves", mwTable);

        HBox bottomBar = new HBox(15);
        bottomBar.setAlignment(Pos.CENTER_RIGHT);
        Button actionBtn = new Button("Confirm Discontinue");
        actionBtn.setStyle(BTN_DANGER);
        bottomBar.getChildren().add(actionBtn);

        mainLayout.getChildren().add(bottomBar);
        primaryStage.setScene(new Scene(scrollPane, 1220, 680));

        actionBtn.setOnAction(e -> {
            for (int i = 0; i < totalProducts; i++) {
                if (checkBoxes[i].isSelected()) {
                    products[i].setStatus(false);
                }
            }
            primaryStage.setScene(menuScene);
        });
    }

    public static void addStock(Stage primaryStage, Scene menuScene, Product[] products, int totalProducts) {
        modifyStockUI(primaryStage, menuScene, products, totalProducts, true);
    }

    public static void deductStock(Stage primaryStage, Scene menuScene, Product[] products, int totalProducts) {
        modifyStockUI(primaryStage, menuScene, products, totalProducts, false);
    }

    private static GridPane createProductTable(String[] headers) {
        GridPane root = new GridPane();
        root.setGridLinesVisible(true);
        root.setStyle("-fx-background-color: white; -fx-border-color: #bdc3c7;");
        for (int i = 0; i < headers.length; i++) {
            ColumnConstraints cc = new ColumnConstraints();
 
            cc.setHgrow(Priority.ALWAYS);
            cc.setMinWidth(80); 
            root.getColumnConstraints().add(cc);

            Label headerLabel = new Label(headers[i]);
            headerLabel.setStyle("-fx-padding: 8px; -fx-font-weight: bold; -fx-background-color: #ecf0f1; -fx-text-fill: #2c3e50;");
            headerLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            headerLabel.setAlignment(Pos.CENTER);
            root.add(headerLabel, i, 0);
        }
        return root;
    }

    private static void addTableRow(GridPane table, int rowIndex, String itemNum, String name, String spec1, String spec2, String spec3, int qty, double price, double total, boolean status) {
        table.add(createCell(String.valueOf(rowIndex)), 0, rowIndex);
        table.add(createCell(itemNum), 1, rowIndex);
        table.add(createCell(name), 2, rowIndex);
        table.add(createCell(spec1), 3, rowIndex);
        table.add(createCell(spec2), 4, rowIndex);
        table.add(createCell(spec3), 5, rowIndex);
        table.add(createCell(String.valueOf(qty)), 6, rowIndex);
        table.add(createCell(String.format("$%.2f", price)), 7, rowIndex);
        table.add(createCell(String.format("$%.2f", total)), 8, rowIndex);
        table.add(createStatusCell(status), 9, rowIndex);
    }

    private static Label createCell(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-padding: 8px; -fx-text-fill: #34495e;"); 
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private static Label createStatusCell(boolean status) {
        Label label = new Label(status ? "Active" : "Discontinued");
        label.setStyle("-fx-padding: 8px; -fx-font-weight: bold; -fx-text-fill: " + (status ? "#27ae60" : "#e74c3c") + ";");
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private static void addTableToLayout(VBox layout, String title, GridPane table) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-padding: 10px 0px 5px 0px;");
        VBox card = new VBox(10, titleLabel, table);
        card.setStyle(CARD_STYLE);
        layout.getChildren().add(card);
    }
}