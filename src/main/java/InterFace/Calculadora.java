package InterFace;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class Calculadora {
    private boolean hayUnOperador = true;

    public boolean getHayUnOperador() {
        return hayUnOperador;
    }

    public void setHayUnOperador(boolean hayUnOperador) {
        this.hayUnOperador = hayUnOperador;
    }


    public Scene crearInterfaz() { // Método para crear la interfaz gráfica de la calculadora
    //------------------------------------------------------------------------------------------------------------------
        VBox contenedorPrincipal = new VBox(15); // Contenedor Padre
        contenedorPrincipal.setAlignment(Pos.CENTER);
    //------------------------------------------------------------------------------------------------------------------
        HBox expresionHBox = new HBox();//Etiqueta donde se vera las expresiones
        expresionHBox.setAlignment(Pos.CENTER_LEFT);

        Label expresionLabel = new Label();
        expresionLabel.setMaxHeight(5);
        expresionLabel.setAlignment(Pos.CENTER_LEFT);
        expresionLabel.setPadding(new Insets(0, 0, 0, 59));
    //------------------------------------------------------------------------------------------------------------------
        HBox resultadoHBox = new HBox();//Etiqueta donde se vera los resultados
        resultadoHBox .setAlignment(Pos.CENTER_RIGHT);

        Label resultadoLabel = new Label();
        resultadoLabel.setMaxHeight(5);
        resultadoLabel.setAlignment(Pos.CENTER_RIGHT);
        resultadoLabel.setPadding(new Insets(0, 59, 0, 0));
    //------------------------------------------------------------------------------------------------------------------
        // Agrega los Labels a sus respectivos contenedores
        expresionHBox.getChildren().add(expresionLabel);
        resultadoHBox .getChildren().add(resultadoLabel);

        // Agrega los contenedores y la cuadrícula de botones al contenedor principal
        contenedorPrincipal.getChildren().addAll(expresionHBox,resultadoHBox ,Botones(expresionLabel,resultadoLabel));
        return new Scene(contenedorPrincipal, 400, 600);
    }

    public GridPane Botones(Label exprecion, Label resultado){
        //Lo que mostrara cada boton
        String[] etiquetasBotones = {"AC","⬅","(",")","7","8","9","/","4","5","6","*","1","2","3","-","0",",","=","+"};

        GridPane gridBotones = new GridPane();
        gridBotones.setAlignment(Pos.CENTER);
        gridBotones.setVgap(2);
        gridBotones.setHgap(2);

        //Crea los botones
        for (int i = 0; i < 20; i++) {
            Button btn = new Button(etiquetasBotones[i]);
            btn.setMinSize(70, 70);// Tamaño mínimo de los botones
            btn.getProperties().put("tipo",etiquetasBotones[i]);//Guarda como propiedad de cada botton el elemto que representa
            gridBotones.add(btn, i % 4,  i / 4); // Coloca etiquetas Botones en la cuadrícula
            btn.setOnAction(actionEvent -> manejarEventoBoton(btn, exprecion, resultado));//Maneja el evento del boton
        }

        return gridBotones;
    }

    public void manejarEventoBoton(Button btn, Label expresion, Label resultado) {
        // Método para manejar el evento de clic en los botones

        switch (btn.getProperties().get("tipo").toString()) {
            case "AC" -> resetearExpresion(expresion);
            case "⬅" -> borrarUltimoCaracter(expresion);
            case "=" -> mostrarResultado(expresion, resultado);
            default -> agregarCaracter(btn,expresion);
        }
    }
    public void resetearExpresion(Label expresion) {
        expresion.setText("");
        setHayUnOperador(true);// Reinicia el estado
    }

    public void borrarUltimoCaracter(Label exprecion){
        exprecion.setText(exprecion.getText().substring(0,exprecion.getText().length() -1)); // Obtiene el texto actual de la expresión

        String ultimoElemento = obtenerUltimoCaracter(exprecion.getText());
        if ( ultimoElemento.equals("/") || ultimoElemento.equals("*") || ultimoElemento.equals("+") ||ultimoElemento.equals("-")){
            setHayUnOperador(true);
        }
        else {
            setHayUnOperador(false);
        }
    }
    public void mostrarResultado(Label expresion, Label resultado) {
        System.out.println(expresion.getText());
        resultado.setText("Función no disponible");
    }

    public void agregarCaracter(Button btn, Label exprecion){
        String tipoBoton = btn.getProperties().get("tipo").toString();// Obtiene la etiqueta del botón
        if (getHayUnOperador()){
            // Si el botón no es un operador
            if ( !(tipoBoton.equals("/") || tipoBoton.equals("*") || tipoBoton.equals("+") ||tipoBoton.equals("-"))){
                exprecion.setText(exprecion.getText() + btn.getProperties().get("tipo"));// Agrega el carácter a la expresión
                setHayUnOperador(false);
            }
        }
        else{
            if ( tipoBoton.equals("/") || tipoBoton.equals("*") || tipoBoton.equals("+") ||tipoBoton.equals("-")){
                exprecion.setText(exprecion.getText() + btn.getProperties().get("tipo"));
                setHayUnOperador(true);
            }
            else {
                exprecion.setText(exprecion.getText() + btn.getProperties().get("tipo"));
                setHayUnOperador(false);
            }
        }
    }

    public String obtenerUltimoCaracter(String texto){
        if (texto.isEmpty()){
            return texto;
        }
        else return texto.substring(texto.length() -1, texto.length());

    }






}

