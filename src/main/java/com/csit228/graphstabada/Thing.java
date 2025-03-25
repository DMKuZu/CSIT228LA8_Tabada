package com.csit228.graphstabada;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Thing extends Node {
    StackPane sp;

    public Thing(String text,double x_val, double y_val) {
        Circle circle = new Circle();
        Text name = new Text(text);
        sp = new StackPane(circle,name);

        sp.setLayoutX(x_val);
        sp.setLayoutY(y_val);
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
