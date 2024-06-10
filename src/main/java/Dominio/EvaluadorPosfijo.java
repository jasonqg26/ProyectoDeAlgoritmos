package Dominio;

import java.util.ArrayList;
import java.util.Stack;

public class EvaluadorPosfijo {



    public String[] convertirAExpresionPosfija(String expresion) {
        Stack<Character> pila = new Stack<>();
        ArrayList<String> expresionResultante = new ArrayList<>();
        StringBuilder almacen = new StringBuilder();

        for (int i = 0; i < expresion.length(); i++) {
            char actual = expresion.charAt(i);



            if (esOperadorOParentesis(actual) && !almacen.isEmpty()) {
                if (esParentesis(actual) && !esParentesisDeCierre(actual)){
                    manejarOperador('*',pila,expresionResultante,almacen);
                }
                else {
                    expresionResultante.add(almacen.toString());
                    almacen.setLength(0);
                }
            }

            if (!esOperadorOParentesis(actual)) {
                almacen.append(actual);
            } else if (esParentesis(actual)) {
                manejarParentesis(actual, pila, expresionResultante);
            } else {
                manejarOperador(actual, pila, expresionResultante, almacen);
            }
            if (esParentesisDeCierre(actual) && i + 1 < expresion.length() &&!esOperadorOParentesis(expresion.charAt(i + 1))){
                manejarOperador('*',pila,expresionResultante,almacen);
            }

            //Si ya es el último for lo que tenga el almacén que se agregue;
            if (i + 1 == expresion.length() && !almacen.isEmpty()) {
                expresionResultante.add(almacen.toString());
                almacen.setLength(0);
            }
        }

        while (!pila.isEmpty()) {
            expresionResultante.add(String.valueOf(pila.pop()));
        }

        return expresionResultante.toArray(new String[0]);
    }

    public void manejarParentesis(char actual, Stack<Character> pila, ArrayList<String> expresionResultante) {
        //Si el parentesi es de cierre se saca los elementos hasta encontrar a la pareja del parentesi
        if (esParentesisDeCierre(actual)) {
            while (pila.peek() != '(') {
                expresionResultante.add(String.valueOf(pila.pop()));
            }
            pila.pop();//elimina (
        } else { //Si no es de cierre es (

            pila.push(actual);
        }
    }

    public void manejarOperador(char actual, Stack<Character> pila, ArrayList<String> expresionResultante, StringBuilder almacen) {
        //Al encontrar un operador el almacén se agrega
        if (!almacen.isEmpty()) {
            expresionResultante.add(almacen.toString());
            almacen.setLength(0);
        }

        //Si el que está en la pila tiene mayor importancia que el que entra sale
        while (!pila.isEmpty() && obtenerImportancia(pila.peek()) >= obtenerImportancia(actual)) {
            expresionResultante.add(String.valueOf(pila.pop()));
        }
        pila.push(actual);
    }

    public boolean esOperadorOParentesis(char elemento) {
        return "+-*/()".indexOf(elemento) != -1;
    }

    public boolean esParentesis(char elemento) {
        return elemento == '(' || elemento == ')';
    }

    public boolean esParentesisDeCierre(char elemento) {
        return elemento == ')';
    }

    public int obtenerImportancia(char elemento) {
        return switch (elemento) {
            case '-'-> 1;
            case '+'-> 2;
            case '/'-> 3;
            case '*'-> 4;
            default -> -1;
        };
    }




    public double calcularPosfija(String[] expresionPosfija) {
        Stack<Double> operandos = new Stack<>();


        for (int i = 0; i < expresionPosfija.length; i++) {
            String caracter = expresionPosfija[i];
            if (esOperador(caracter)) {
                // Si es un operador, aplicamos la operación correspondiente
                double operando2 = operandos.pop();
                double operando1 = operandos.pop();
                double resultado = realizarCalculo(caracter, operando1, operando2);
                operandos.push(resultado);

            } else {
                /// Si es un número, lo convertimos a double y lo agregamos a la pila
                operandos.push(Double.parseDouble(caracter));
            }
        }

        return operandos.pop();
    }

    public double realizarCalculo(String operador, double operando1, double operando2) {
        double resultado;
         switch (operador) {
            case "+" -> resultado = operando1 + operando2;
            case "-" -> resultado = operando1 - operando2;
            case "*" -> resultado = operando1 * operando2;
            case "/" -> resultado = operando1 / operando2;
            default -> throw new IllegalArgumentException("Operador desconocido: " + operador);
        }

         return resultado;
    }

    public boolean esOperador(String caracter) {
        return "+-*/".contains(caracter);
    }


}
