package Dominio;

import java.util.Arrays;

// Autores: Alexis Fabián Quesada Cordero C36202 y Jason Quesada Gómez C36213
public class Main {

    public static void main(String[] args) {

        EvaluadorPosfijo evaluadorPosfijo = new EvaluadorPosfijo();
        //System.out.println(Arrays.toString(evaluadorPosfijo.convertirAExpresionPosfija("((33+(7-2)*(4/(2+1)))-((5*2)-(8/2)))")));

        //String[] operacion = {"5","2","/"};


        System.out.println(evaluadorPosfijo.calcularPosfija(evaluadorPosfijo.convertirAExpresionPosfija("((33+(7-2)*(4/(23+1)))-((5*2)-(87/2)))")));

    }
}
