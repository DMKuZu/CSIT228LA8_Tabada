package com.csit228.graphstabada;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Thing extends Node {
    StackPane sp;

    public Thing(String text,double x_val, double y_val) {
        LinearGradient paint = new LinearGradient(
                0.0, 0.3318, 1.0, 1.0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, new Color(1.0, 0.0, 0.0, 0.729)),
                new Stop(1.0, new Color(1.0, 1.0, 1.0, 1.0)));

        Circle circle = new Circle(33.0,paint);
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
