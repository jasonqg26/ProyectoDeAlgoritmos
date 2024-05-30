module com.example.proyectodealgoritmos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectodealgoritmos to javafx.fxml;
    exports com.example.proyectodealgoritmos;
}