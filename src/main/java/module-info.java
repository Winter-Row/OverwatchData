module com.example.overwatchdata {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.overwatchdata to javafx.fxml;
    exports com.example.overwatchdata;
}