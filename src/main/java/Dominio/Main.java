package Dominio;

public class Main {

    public static void main(String[] args) {

        EvaluadorPosfijo evaluadorPosfijo = new EvaluadorPosfijo();
        System.out.println(evaluadorPosfijo.convertirAExpresionPosfija("((15/(7-(1+1)))*3)-(2+(1+1))"));



    }
}
