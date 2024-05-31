package InterFace;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

       Calculadora calculadora = new Calculadora();



        stage.setTitle("Calculadora");
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(calculadora.crearInterfaz());
        stage.show();
        stage.centerOnScreen();



    }

    public static void main(String[] args) {
        launch();
    }
}