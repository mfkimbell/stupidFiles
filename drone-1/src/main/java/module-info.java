module com.example.drone1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.drone1 to javafx.fxml;
    exports com.example.drone1;
}