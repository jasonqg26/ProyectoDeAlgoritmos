package Dominio;

import java.util.Stack;

public class EvaluadorPosfijo {

    public  StringBuilder convertirAExpresionPosfija(String expresion) {

        StringBuilder exprecionResultante = new StringBuilder();
        Stack<Character> pila = new Stack<>();
        for (int i = 0; i < expresion.length(); i++){
            char actual = expresion.charAt(i);
            if (!esOperadorOParentesis(actual)){
                exprecionResultante.append(actual);
            }
            else if (esParentesis(actual)){
                if (parentesisDeCierre(actual)){
                    while (pila.peek() != '('){
                        exprecionResultante.append(pila.pop());
                    }
                    pila.pop();
                }
                else{
                    pila.push(actual);
                }
            }
            else  {
                if (pila.isEmpty()){
                    pila.push(actual);
                }
                else {
                    if (obtenerImportancia(pila.peek()) >= obtenerImportancia(actual)){
                        while (!pila.isEmpty() && obtenerImportancia(pila.peek()) >= obtenerImportancia(actual)){
                            exprecionResultante.append(pila.pop());
                        }
                        pila.push(actual);
                    }
                    else {
                        pila.push(actual);
                    }
                }
            }
        }

        while (!pila.isEmpty()){
            exprecionResultante.append(pila.pop());
        }

        return exprecionResultante;


    }

    private  boolean esOperadorOParentesis(char token) {
        return token == '+' || token == '-' || token == '*' || token == '/' || token == ')' || token == '(';
    }

    public boolean esParentesis(char token){
        return token == ')' || token == '(';
    }

    public boolean parentesisDeCierre(char token){
        return token == ')';
    }

    public int obtenerImportancia(char token){
        switch (token) {
            case '-': return  1;
            case '+': return 2;
            case '/': return 3;
            case '*': return 4;
            default: return -1;
        }

    }

    public double calcularPosfija(String[] expresionPosfija) {
        Stack<Double> operandos = new Stack<>();

        for (int i = 0; i < expresionPosfija.length; i++) {
            String caracter = expresionPosfija[i];

            if (caracter.equals("/") || caracter.equals("*") || caracter.equals("+") ||caracter.equals("-")) {
                // Si es un operador, aplicamos la operación correspondiente
                double operando2 = operandos.pop();
                double operando1 = operandos.pop();
                double resultado = calculo(caracter, operando1, operando2);
                operandos.push(resultado);

            } else {
                /// Si es un número, lo convertimos a double y lo agregamos a la pila
                operandos.push(Double.parseDouble(caracter));
            }
        }

        return operandos.pop();
    }



    private static double calculo (String operador, double operando1, double operando2) {
        if (operador.equals("+")) {
            return operando1 + operando2;
        } else if (operador.equals("-")) {
            return operando1 - operando2;
        } else if (operador.equals("*")) {
            return operando1 * operando2;
        } else if (operador.equals("/")) {
            if (operando2 == 0) {
                throw new ArithmeticException("Error, no se puede dividir entre 0");
            }
            return operando1 / operando2;
        } else {
            throw new IllegalArgumentException("Operador desconocido: " + operador);
        }
    }


}
