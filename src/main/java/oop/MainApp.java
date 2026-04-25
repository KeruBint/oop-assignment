package oop;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class MainApp extends Application {
    private Product[] productsArray = new Product[100];
    private int totalProducts = 0;

    @Override
    public void start(Stage primaryStage) {
        // declaration of login scene
        Font customFont = Font.loadFont(getClass().getResourceAsStream("fonnts.com-ultimate_serial-xlight.otf"), 14);
        VBox root = new VBox();
        Label title = new Label("welcome to stock management system");
        HBox boxes = new HBox();
        Label name = new Label("name");
        TextField inputname = new TextField();
        StackPane box1 = new StackPane();
        StackPane box2 = new StackPane();
        StackPane box3 = new StackPane();
        StackPane box4 = new StackPane();
        Label timname = new Label("chan tim how");
        Label weiname = new Label("chew wei jiun");
        Label kainame = new Label("leong kai wen");
        Label wongname = new Label("wong yu feng");
        // children
        VBox.setMargin(boxes, new Insets(50, 0, 0, 0));
        boxes.getChildren().addAll(box1, box2, box3, box4);
        box1.setStyle("-fx-border-width:0.25;-fx-border-color:black; ");
        box1.setPrefSize(152, 152);
        box2.setStyle("-fx-border-width:0.25;-fx-border-color:black;");
        box2.setPrefSize(152, 152);
        box3.setStyle("-fx-border-width:0.25;-fx-border-color:black;");
        box3.setPrefSize(152, 152);
        box4.setStyle("-fx-border-width:0.25;-fx-border-color:black;");
        box4.setPrefSize(152, 152);
        box1.getChildren().add(timname);
        box2.getChildren().add(weiname);
        box3.getChildren().add(kainame);
        box4.getChildren().add(wongname);

        // root
        root.getChildren().addAll(title, boxes, name, inputname);
        root.setPrefHeight(340);
        root.setPrefWidth(610);
        root.setAlignment(Pos.CENTER);
        // scene
        Scene login = new Scene(root);
        // style
        title.getStyleClass().add("title");
        login.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        boxes.getStyleClass().add("person");
        timname.setStyle("-fx-text-fill:black");
        weiname.setStyle("-fx-text-fill:black");
        kainame.setStyle("-fx-text-fill:black");
        wongname.setStyle("-fx-text-fill:black");
        // stage
        primaryStage.setScene(login);
        primaryStage.setTitle("SMS");
        primaryStage.show();

        // managementsystem prompt declaration
        Button view = new Button("view");
        Button add = new Button("add");
        Button deduct = new Button("deduct");
        Button discontinue = new Button("discontinue");
        Button[] buttonsarr = { view, add, deduct, discontinue };

        // action
        UserInfo userInfo = new UserInfo();
        inputname.setOnAction(e -> {
            userInfo.InputUserInfo(inputname.getText());
            System.out.println("User ID set to: " + userInfo.getID());

            // Once the ID is set, create and switch to the next scene
            if (userInfo.getID() != null && !userInfo.getID().isEmpty()) {
                // declaration
                VBox nextRoot = new VBox(20);
                HBox buttons = new HBox();
                Button yes = new Button("Yes");
                Button no = new Button("no");
                Label welcomeLabel = new Label("Welcome, " + userInfo.getID() + "! do you wanna add new product?");
                TextField inputamount = new TextField("");

                // children
                buttons.getChildren().addAll(yes, no);
                buttons.setAlignment(Pos.CENTER);
                HBox.setMargin(yes, new Insets(0, 20, 0, 0));
                yes.setPrefSize(80, 40);
                no.setPrefSize(80, 40);
                yes.setStyle("-fx-background-color: green");
                no.setStyle("-fx-background-color: red");

                // root
                nextRoot.setAlignment(Pos.CENTER);
                nextRoot.getChildren().addAll(welcomeLabel, buttons);
                // scene
                Scene nextScene = new Scene(nextRoot, 610, 340);
                // stage
                primaryStage.setScene(nextScene);

                yes.setOnAction(event -> {
                    welcomeLabel.setText("how many product do you wanna add?");
                    nextRoot.getChildren().remove(buttons);
                    nextRoot.getChildren().add(inputamount);

                    inputamount.setOnAction(amountEvent -> {
                        try {
                            int amount = Integer.parseInt(inputamount.getText());
                            if (amount > 0) {
                                startAddingProducts(primaryStage, login, amount, 1);
                            }
                        } catch (NumberFormatException ex) {
                            welcomeLabel.setText("Invalid number! how many product do you wanna add?");
                        }

                    });

                }

                );
                no.setOnAction(noaction -> {
                    primaryStage.setScene(login);

                });
                title.setText("Hi " + userInfo.getID() + ", Welcome to Stock Management System");
                root.getChildren().removeAll(name, inputname);

                // Add Exit Button to the top right of the menu page
                HBox topBar = new HBox();
                topBar.setAlignment(Pos.TOP_RIGHT);
                topBar.setPadding(new Insets(10));
                Button exitBtn = new Button("Exit");
                exitBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
                exitBtn.setOnAction(exitEvent -> primaryStage.close());
                topBar.getChildren().add(exitBtn);
                root.getChildren().add(0, topBar);

                box1.getChildren().add(view);
                box2.getChildren().add(add);
                box3.getChildren().add(deduct);
                box4.getChildren().add(discontinue);
                for (Button allbutton : buttonsarr) {
                    allbutton.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
                }

                view.setOnAction(viewaction -> {
                    StockManagement.display(primaryStage, login, productsArray, totalProducts);
                });

                add.setOnAction(addaction -> {
                    StockManagement.modifyStockUI(primaryStage, login, productsArray, totalProducts, true);
                });

                deduct.setOnAction(deductaction -> {
                    StockManagement.modifyStockUI(primaryStage, login, productsArray, totalProducts, false);
                });

                discontinue.setOnAction(discontinueaction -> {
                    StockManagement.discontinueUI(primaryStage, login, productsArray, totalProducts);
                });

            }
        });
    }

    private void startAddingProducts(Stage primaryStage, Scene scene2, int totalToAdd, int currentIteration) {
        if (currentIteration > totalToAdd) {
            VBox doneBox = new VBox(20);
            Button proceed = new Button("proceed");
            doneBox.setAlignment(Pos.CENTER);
            doneBox.getChildren().addAll(new Label("Successfully added " + totalToAdd + " products!"), proceed);
            primaryStage.setScene(new Scene(doneBox, 610, 340));
            proceed.setOnAction(proceedaction -> {
                primaryStage.setScene(scene2);
            });
            return;

        }

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        Label title = new Label("Product " + currentIteration + " of " + totalToAdd + " - Choose Type:");

        Button btnFridge = new Button("Refrigerator");
        Button btnTV = new Button("TV");

        HBox typeBox = new HBox(10);
        typeBox.setAlignment(Pos.CENTER);
        typeBox.getChildren().addAll(btnFridge, btnTV);

        layout.getChildren().addAll(title, typeBox);
        Scene scene = new Scene(layout, 610, 340);
        primaryStage.setScene(scene);

        btnFridge.setOnAction(e -> showFridgeForm(primaryStage, scene2, totalToAdd, currentIteration));
        btnTV.setOnAction(e -> showTVForm(primaryStage, scene2, totalToAdd, currentIteration));
    }

    private void showFridgeForm(Stage primaryStage, Scene scene2, int totalToAdd, int currentIteration) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        Label title = new Label("Adding Refrigerator (" + currentIteration + "/" + totalToAdd + ")");

        TextField fName = new TextField();
        fName.setPromptText("Name");
        TextField fItemNum = new TextField();
        fItemNum.setPromptText("Item Number");
        TextField fQty = new TextField();
        fQty.setPromptText("Quantity");
        TextField fPrice = new TextField();
        fPrice.setPromptText("Price");
        TextField fDoor = new TextField();
        fDoor.setPromptText("Door Design");
        TextField fColor = new TextField();
        fColor.setPromptText("Color");
        TextField fCap = new TextField();
        fCap.setPromptText("Capacity (int)");

        Button btnSave = new Button("Save Refrigerator");

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

                productsArray[totalProducts] = new Refrigerator(itemNum, name, qty, price, door, color, cap);
                totalProducts++;

                startAddingProducts(primaryStage, scene2, totalToAdd, currentIteration + 1);
            } catch (Exception ex) {
                title.setText("Invalid input! Please check your numbers.");
            }
        });
    }

    private void showTVForm(Stage primaryStage, Scene scene2, int totalToAdd, int currentIteration) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        Label title = new Label("Adding TV (" + currentIteration + "/" + totalToAdd + ")");

        TextField fName = new TextField();
        fName.setPromptText("Name");
        TextField fItemNum = new TextField();
        fItemNum.setPromptText("Item Number");
        TextField fQty = new TextField();
        fQty.setPromptText("Quantity");
        TextField fPrice = new TextField();
        fPrice.setPromptText("Price");
        TextField fScreen = new TextField();
        fScreen.setPromptText("Screen Type");
        TextField fRes = new TextField();
        fRes.setPromptText("Resolution");
        TextField fSize = new TextField();
        fSize.setPromptText("Display Size");

        Button btnSave = new Button("Save TV");

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

                productsArray[totalProducts] = new TV(itemNum, name, qty, price, screen, res, size);
                totalProducts++;

                startAddingProducts(primaryStage, scene2, totalToAdd, currentIteration + 1);
            } catch (Exception ex) {
                title.setText("Invalid input! Please check your numbers.");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}