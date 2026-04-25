package oop;

import javafx.scene.text.Font;

public class test {
    public static void main(String[] args) {
        System.out.println(
                Font.loadFont(getClass().getResourceAsStream("/com/example/fonts/ultimateserial-xlight.otf"), 10)
                        .getName());
    }
}