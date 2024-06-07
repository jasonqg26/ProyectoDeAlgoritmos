package Dominio;

import java.util.ArrayList;
import java.util.Stack;

public class EvaluadorPosfijo {



    public String[] convertirAExpresionPosfija(String expresion) {
        //Convierte a una exprecion posfija

        Stack<Character> pila = new Stack<>();
        ArrayList<String> exprecionResultante = new ArrayList<>();


        String almacen = "";

        for (int i = 0; i < expresion.length(); i++){
            char actual = expresion.charAt(i);
            if (esOperadorOParentesis(actual) && !almacen.isEmpty()){
                exprecionResultante.add(almacen);
                almacen = "";
            }

            if (!esOperadorOParentesis(actual)){
                almacen += actual;
            }
            else if (esParentesis(actual)){
                if (parentesisDeCierre(actual)){
                    while (pila.peek() != '('){
                        exprecionResultante.add(String.valueOf(pila.pop()));
                    }
                    pila.pop();
                }
                else{
                    pila.push(actual);
                }
            }
            else  {
                if (!almacen.isEmpty()){
                    exprecionResultante.add(almacen);
                    almacen = "";
                }

                if (pila.isEmpty()){
                    pila.push(actual);
                }
                else {
                    if (obtenerImportancia(pila.peek()) >= obtenerImportancia(actual)){
                        while (!pila.isEmpty() && obtenerImportancia(pila.peek()) >= obtenerImportancia(actual)){
                            exprecionResultante.add(String.valueOf(pila.pop()));
                        }
                        pila.push(actual);
                    }
                    else {
                        pila.push(actual);
                    }
                }
            }
            if (i+1 == expresion.length() && !almacen.isEmpty()){
                exprecionResultante.add(almacen);
                almacen = "";
            }

        }



        while (!pila.isEmpty()){
            exprecionResultante.add(String.valueOf(pila.pop()));
        }

        String [] arreglo = exprecionResultante.toArray(new String[0]);


        return arreglo;


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




    public double calcularPosfijaQ(String[] expresionPosfija) {
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
