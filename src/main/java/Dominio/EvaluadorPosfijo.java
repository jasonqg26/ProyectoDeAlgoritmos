package Dominio;

import java.util.Stack;

public class EvaluadorPosfijo {

    public String[] convertirAExpresionPosfija(String expresion) {
        StringBuilder expresionResultante = new StringBuilder();
        Stack<Character> pila = new Stack<>();
        StringBuilder numero = new StringBuilder();

        for (int i = 0; i < expresion.length(); i++) {
            char actual = expresion.charAt(i);

            if (Character.isDigit(actual)) {
                numero.append(actual); // Acumular dígitos de números
            } else {
                if (numero.length() > 0) {
                    expresionResultante.append(numero.toString()).append(" ");
                    numero.setLength(0); // Limpiar acumulador de números
                }
                if (esOperadorOParentesis(actual)) {
                    if (actual == '(') {
                        pila.push(actual);
                    } else if (actual == ')') {
                        while (!pila.isEmpty() && pila.peek() != '(') {
                            expresionResultante.append(pila.pop()).append(" ");
                        }
                        pila.pop(); // Eliminar el '(' de la pila
                    } else {
                        while (!pila.isEmpty() && obtenerImportancia(pila.peek()) >= obtenerImportancia(actual)) {
                            expresionResultante.append(pila.pop()).append(" ");
                        }
                        pila.push(actual);
                    }
                }
            }
        }

        if (numero.length() > 0) {
            expresionResultante.append(numero.toString()).append(" ");
        }

        while (!pila.isEmpty()) {
            expresionResultante.append(pila.pop()).append(" ");
        }

        return expresionResultante.toString().trim().split("\\s+");
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
