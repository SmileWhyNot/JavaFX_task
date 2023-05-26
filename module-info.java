module com.example.app6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.app6 to javafx.fxml;
    exports com.example.app6;
    opens Plants;
}