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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Product[] productsArray = new Product[100];
    private int[] totalProductsRef = {0};

    @Override
    public void start(Stage primaryStage) {
        try {
            Font.loadFont(getClass().getResourceAsStream("fonnts.com-ultimate_serial-xlight.otf"), 14);
        } catch (Exception e) {
            System.out.println("Could not load custom font, using default.");
        }

        VBox root = new VBox(30); 
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f4f7f6;");
        Label title = new Label("Stock Management System");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 2);");

        HBox boxes = new HBox(20); 
        boxes.setAlignment(Pos.CENTER);
        
        StackPane box1 = createCard("Chan Tim How");
        StackPane box2 = createCard("Chew Wei Jiun");
        StackPane box3 = createCard("Leong Kaiwen");
        StackPane box4 = createCard("Wong Yu Feng");
        
        boxes.getChildren().addAll(box1, box2, box3, box4);
        VBox loginArea = new VBox(10);
        loginArea.setAlignment(Pos.CENTER);
        loginArea.setPadding(new Insets(20, 0, 0, 0));

        Label nameLabel = new Label("Enter your name to begin:");
        nameLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");

        TextField inputname = new TextField();
        inputname.setPromptText("e.g. John Doe");
        inputname.setMaxWidth(300);
        inputname.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-background-radius: 20px; -fx-border-radius: 20px; -fx-border-color: #bdc3c7; -fx-border-width: 1px; -fx-background-color: white;");

        loginArea.getChildren().addAll(nameLabel, inputname);

        root.getChildren().addAll(title, boxes, loginArea);
        
        Scene login = new Scene(root, 900, 500); 
        
        title.getStyleClass().add("title");
        boxes.getStyleClass().add("person");
        try {
            login.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Could not load style.css");
        }

        primaryStage.setScene(login);
        primaryStage.setTitle("Stock Management System");
        primaryStage.setResizable(false);
        primaryStage.show();

        Button view = createMenuButton("View Data");
        Button add = createMenuButton("Add Stock");
        Button deduct = createMenuButton("Deduct Stock");
        Button discontinue = createMenuButton("Discontinue");
        Button[] buttonsarr = { view, add, deduct, discontinue };

        UserInfo userInfo = new UserInfo();
        inputname.setOnAction(e -> {
            String enteredName = inputname.getText().trim();
            if (!enteredName.isEmpty()) {
                userInfo.InputUserInfo(enteredName);
                System.out.println("User ID set to: " + userInfo.getID());

                StockManagement.getMaxProducts(primaryStage, login, userInfo.getID(), productsArray, totalProductsRef);
                
                root.getChildren().remove(loginArea); // Remove the whole login area
                title.setText("Welcome, " + userInfo.getID() + "!");

                StackPane[] boxesArr = {box1, box2, box3, box4};
                StockManagement.displayMenu(root, boxesArr, buttonsarr, title, userInfo.getID(), primaryStage);
                StockManagement.executeMenuChoice(buttonsarr, primaryStage, login, productsArray, totalProductsRef);
            }
        });
    }


    private StackPane createCard(String text) {
        StackPane card = new StackPane();
        card.setPrefSize(180, 180);
        card.setStyle("-fx-background-color: white; " +
                      "-fx-background-radius: 15px; " +
                      "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 5, 5);"); 
        
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #34495e;");
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        
        card.getChildren().add(label);
        return card;
    }

    private Button createMenuButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: transparent; " +
                     "-fx-font-size: 20px; " +
                     "-fx-font-weight: bold; " +
                     "-fx-text-fill: #2980b9; " +
                     "-fx-cursor: hand;");
        return btn;
    }

    public static void main(String[] args) {
        launch(args);
    }
}