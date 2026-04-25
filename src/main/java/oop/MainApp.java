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
        // declaration of login scene
        Font.loadFont(getClass().getResourceAsStream("fonnts.com-ultimate_serial-xlight.otf"), 14);
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
                StockManagement.getMaxProducts(primaryStage, login, userInfo.getID(), productsArray, totalProductsRef);
                
                root.getChildren().removeAll(name, inputname);

                StackPane[] boxesArr = {box1, box2, box3, box4};
                StockManagement.displayMenu(root, boxesArr, buttonsarr, title, userInfo.getID(), primaryStage);
                
                StockManagement.executeMenuChoice(buttonsarr, primaryStage, login, productsArray, totalProductsRef);
            }
        });
    }



    public static void main(String[] args) {
        launch(args);
    }
}