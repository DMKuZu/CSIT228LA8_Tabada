module com.csit228.graphstabada {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.csit228.graphstabada to javafx.fxml;
    exports com.csit228.graphstabada;
}