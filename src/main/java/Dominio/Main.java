package Dominio;

import java.util.Arrays;

// Autores: Alexis Fabián Quesada Cordero C36202 y Jason Quesada Gómez C36213
public class Main {

    public static void main(String[] args) {

        EvaluadorPosfijo evaluadorPosfijo = new EvaluadorPosfijo();
        ValidarExpresion validarExpresion = new ValidarExpresion();

        //ystem.out.println(validarExpresion.validarParentesis("(8000+2)/46*3"));
        //System.out.println(validarExpresion.validarParentesis("(8000+2)/46*3)")); //false

        //System.out.println(Arrays.toString(evaluadorPosfijo.convertirAExpresionPosfija("(8000+2)/46*3")));
        String[] r = evaluadorPosfijo.convertirAExpresionPosfija("(2)2");
        for (int i = 0; i < r.length; i++){
            System.out.println(r[i]);
        }
        System.out.println(evaluadorPosfijo.calcularPosfija(r));



    }
}
