package com.csit228.graphstabada;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

public class HelloController {
    public AnchorPane apPane;
    public TextField tfName;

    private List<Vertex> vertices;

    private double offsetX, offsetY;

    private void setSPListener(StackPane sp){
        sp.setOnMouseClicked((MouseEvent event) ->{
            /*if(event.getButton() == MouseButton.SECONDARY)*/ onVertexClick(event);
        });

        sp.setOnMousePressed((MouseEvent event) ->{
            offsetX = event.getSceneX() - sp.getLayoutX();
            offsetY = event.getSceneY() - sp.getLayoutY();
        });

        sp.setOnMouseDragged((MouseEvent event) ->{
            sp.setLayoutX(event.getSceneX() - offsetX);
            sp.setLayoutY(event.getSceneY() - offsetY);
        });


    }
    public void initialize(){
        vertices = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pane.txt"))){
            vertices = (List<Vertex>) ois.readObject();

            System.out.println("SERIALIZED "+vertices.size()+" vertices");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getClass());
        }

        for(Vertex v : vertices){
            Thing sp = new Thing(v.text,v.x_val, v.y_val);
            apPane.getChildren().add(sp.sp);
            setSPListener(sp.sp);
        }
    }

    public void onSaveClicked() {
        /*for(StackPane sp:  apPane.getChildren()){
            double x = sp.getLayoutX();
            double y = sp.getLayoutY();
            String text = sp.getChildren().get(1).getText();

            vertices.add(new Vertex(text,x,y));
        }*/

        vertices.clear(); // Clear list to avoid duplicates

        for(Node sp: apPane.getChildren()){
            StackPane stackPane = (StackPane) sp;
            double x = sp.getLayoutX();
            double y = sp.getLayoutY();

            Text text = (Text) stackPane.getChildren().get(1);
            vertices.add(new Vertex(text.getText(), x, y));
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pane.txt"))){
            oos.writeObject(vertices);
            System.out.println("SAVED "+vertices.size()+" vertices");
        } catch (IOException e) {
            System.err.println(e.getClass());
        }
    }

    public void onCreateClicked(ActionEvent event){
        String name = tfName.getText();
        if(name.isEmpty()) name = "A";
        Thing sp = new Thing(name,250,250);

        apPane.getChildren().add(sp.sp);

        setSPListener(sp.sp);
    }

    public void onVertexClick(MouseEvent mouseEvent) {
        TextInputDialog dialog = new TextInputDialog("Name?");
        dialog.showAndWait().ifPresent(new Consumer<String>() {
            @Override
            public void accept(String s) {
                StackPane sp = (StackPane) mouseEvent.getSource();
                Text text = (Text) sp.getChildren().get(1);
                text.setText(s);
            }
        });
    }
}