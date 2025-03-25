package com.csit228.graphstabada;


import java.io.Serializable;

public class Vertex implements Serializable {
    String text;
    double x_val, y_val;

    public Vertex(String text,double x_val, double y_val) {
        this.text = text;
        this.x_val = x_val;
        this.y_val = y_val;
    }
}
