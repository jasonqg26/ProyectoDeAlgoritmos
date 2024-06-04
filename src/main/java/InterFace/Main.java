package InterFace;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

// Autores: Alexis Fabián Quesada Cordero C36202 y Jason Quesada Gómez C36213

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

       Calculadora calculadora = new Calculadora();


        stage.getIcons().add(new Image("logoCalculadora.png"));
        stage.setTitle("Calculadora");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(calculadora.crearInterfaz());
        stage.show();
        stage.centerOnScreen();



    }

    public static void main(String[] args) {
        launch();
    }
}