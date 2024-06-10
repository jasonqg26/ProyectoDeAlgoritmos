package InterFace;
import Dominio.EvaluadorPosfijo;
import Dominio.ValidarExpresion;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.File;

public class Calculadora {
    // Instancias de las clases que validan y evalúan expresiones
    ValidarExpresion validarExpresion = new ValidarExpresion();
    EvaluadorPosfijo evaluadorPosfijo = new EvaluadorPosfijo();
    private boolean hayUnOperador = true;

    // obtiene el estado del operador
    public boolean getHayUnOperador() {
        return hayUnOperador;
    }
    // establece el estado del operador
    public void setHayUnOperador(boolean hayUnOperador) {
        this.hayUnOperador = hayUnOperador;
    }


    public Scene crearInterfaz() { // Crea la interfaz gráfica de la calculadora

        // Crea el contenedor principal de la interfaz
        VBox contenedorPrincipal = crearContenedorPrincipal();
        // Crea y configura el contenedor para la etiqueta de la expresión
        HBox expresionHBox = crearContenedorExpresion();
        HBox resultadoHBox = crearContenedorResultado();

        Label expresionLabel = crearLabel(Pos.CENTER_LEFT, new Insets(0, 0, 0, 16));
        Label resultadoLabel = crearLabel(Pos.CENTER_RIGHT, new Insets(0, 20, 0, 0));
        // Añade las etiquetas a sus contenedores respectivos
        expresionHBox.getChildren().add(expresionLabel);
        resultadoHBox.getChildren().add(resultadoLabel);
        // Añade los contenedores y los botones al contenedor principal
        contenedorPrincipal.getChildren().addAll(expresionHBox, resultadoHBox, crearBotones(expresionLabel, resultadoLabel));

        return new Scene(contenedorPrincipal, 340, 570);
    }

    public VBox crearContenedorPrincipal() {//Crea el contenedor principal
        VBox contenedorPrincipal = new VBox(15);

        Image img_fondo = new Image("Background.png");
        BackgroundImage bImg = new BackgroundImage(img_fondo,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(340, 570, true, true, true, true));
        Background bGround = new Background(bImg);
        contenedorPrincipal.setBackground(bGround);
        contenedorPrincipal.setAlignment(Pos.CENTER);
        return contenedorPrincipal;
    }


    public HBox crearContenedorExpresion() { // Crea el contenedor de la expresión
        HBox expresionHBox = new HBox();
        expresionHBox.setAlignment(Pos.CENTER_LEFT);
        return expresionHBox;
    }

    public HBox crearContenedorResultado() {// Crea el contenedor del resultado
        HBox resultadoHBox = new HBox();
        resultadoHBox.setAlignment(Pos.CENTER_RIGHT);
        return resultadoHBox;
    }


    public Label crearLabel(Pos alignment, Insets padding) { // Método para crear una etiqueta configurada
        Label label = new Label();
        label.setAlignment(alignment);
        label.setPadding(padding);
        label.setStyle("-fx-text-fill: #FFFFFF;");
        label.setFont(Font.loadFont(new File("src/main/resources/PixelOperator.ttf").toURI().toString(), 35));
        return label;
    }

    public GridPane crearBotones(Label expresion, Label resultado){
        // Método para crear y configurar los botones de la calculadora
        // Etiquetas de los botones
        String[] etiquetasBotones = {"AC","<-","(",")","7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};


        // Configuración del GridPane para los botones
        GridPane gridBotones = new GridPane();
        gridBotones.setAlignment(Pos.CENTER);


        // Crea los botones y les asigna eventos
        for (int i = 0; i < 20; i++) {
            Button btn = new Button(etiquetasBotones[i]);
            btn.setMinSize(80, 80);// Tamaño mínimo de los botones
            btn.setMaxSize(80, 80);
            btn.setStyle(
                    "-fx-background-radius: 30px;"+ "-fx-border-color: black; " +
                    "-fx-border-width: 1px; " +
                    "-fx-border-radius: 28px;");
            btn.setFont(Font.loadFont(new File("src/main/resources/PixelOperator.ttf").toURI().toString(), 35));
            btn.getProperties().put("tipo",etiquetasBotones[i]);//Guarda como propiedad de cada botton el elemto que representa
            gridBotones.add(btn, i % 4,  i / 4); // Coloca etiquetas Botones en la cuadrícula
            btn.setOnAction(actionEvent -> manejarEventoBoton(btn, expresion, resultado));//Maneja el evento del boton
        }

        return gridBotones;
    }

    public void manejarEventoBoton(Button btn, Label expresion, Label resultado) {
        // Método para manejar el evento de clic en los botones
        btn.setRotate(10);
        btn.setStyle("-fx-background-color: #4e5454;"+ "-fx-background-radius: 30px;"+ "-fx-border-color: black; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 28px;");
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
        pauseTransition.setOnFinished(actionEvent -> {
            // Después del tiempo se vuelve a la imagen anterior
            btn.setStyle("-fx-background-color: #ffffff;"+ "-fx-background-radius: 30px;"+ "-fx-border-color: black; " +
                    "-fx-border-width: 1px; " +
                    "-fx-border-radius: 28px;");
            btn.setRotate(0);
        }); // end de qué hacer después de los dos segundos
        // Inicia el tiempo


        pauseTransition.play();
        switch (btn.getProperties().get("tipo").toString()) {
            case "AC" -> resetearExpresion(expresion,resultado);
            case "<-" -> borrarUltimoCaracter(expresion,resultado);
            case "=" -> mostrarResultado(expresion, resultado);
            default -> agregarCaracter(btn,expresion);
        }
    }


    public void resetearExpresion(Label expresion,Label resultado) { // Resetear la expresión y el resultado
        expresion.setText("");
        resultado.setText("");
        setHayUnOperador(true);// Reinicia el estado
    }

    public void borrarUltimoCaracter(Label expresion,Label resultado){// Método para borrar el último carácter de la expresión
        if (!resultado.getText().isEmpty()){
            resultado.setText("");
        }
        if (!expresion.getText().isEmpty()) {
            expresion.setText(expresion.getText().substring(0, expresion.getText().length() - 1)); // Obtiene el texto actual de la expresión
        }
        setHayUnOperador(esUltimoCaracterOperador(expresion.getText()));

    }

    public boolean esUltimoCaracterOperador(String texto) {
        // Método para verificar si el último carácter es un operador
        boolean esCaracter;
        if (!texto.isEmpty()) {
            char ultimoCaracter = texto.charAt(texto.length() - 1);
            esCaracter = "/-*+".indexOf(ultimoCaracter) != -1;
        }
        else{esCaracter = true;}

        return esCaracter;

    }
    public void mostrarResultado(Label expresion, Label resultado) {
        // Método para mostrar el resultado de la expresión
        String textoExpresion = expresion.getText();
        String ultimoElemento = obtenerUltimoCaracter(textoExpresion);

        if(esOperador(ultimoElemento) || esExpresionInvalida(textoExpresion) ){
            resultado.setText("Expresión inválida");
        }else {
            resultado.setText(String.valueOf(evaluadorPosfijo.calcularPosfija(evaluadorPosfijo.convertirAExpresionPosfija(expresion.getText()))));
        }


    }

    public boolean esExpresionInvalida(String textoExpresion) {
        // Método para verificar si la expresión es inválida
        return textoExpresion.equals("0/0") || !validarExpresion.validarParentesis(textoExpresion);
    }



    //Agrega un carácter a la expresión
    public void agregarCaracter(Button btn, Label expresion){
        String tipoBoton = btn.getProperties().get("tipo").toString();// Obtiene la etiqueta del botón

        if (getHayUnOperador()){
            // Si el botón no es un operador
            if ( !esOperador(tipoBoton)){
                expresion.setText(expresion.getText() + tipoBoton);// Agrega el carácter a la expresión
                setHayUnOperador(false);
            }
        }
        else{
            expresion.setText(expresion.getText() + tipoBoton);
            setHayUnOperador(esOperador(tipoBoton));

        }
    }

    public boolean esOperador(String tipoBoton) {
        return "/-*+".contains(tipoBoton);
    }

    public String obtenerUltimoCaracter(String texto){
        if (texto.isEmpty()){
            return texto;
        }
        else return texto.substring(texto.length() -1);

    }






}

