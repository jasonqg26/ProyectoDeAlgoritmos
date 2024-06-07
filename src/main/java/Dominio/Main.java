package Dominio;
// Autores: Alexis Fabián Quesada Cordero C36202 y Jason Quesada Gómez C36213
public class Main {

    public static void main(String[] args) {

        EvaluadorPosfijo evaluadorPosfijo = new EvaluadorPosfijo();
        System.out.println(evaluadorPosfijo.convertirAExpresionPosfija("((3+(7-2)*(4/(2+1)))-((5*2)-(8/2))"));




        String[] operacion = {"8", "4", "3", "*", "-", "1", "3", "/", "+"};

        System.out.println(evaluadorPosfijo.calcularPosfija(operacion));

    }
}
