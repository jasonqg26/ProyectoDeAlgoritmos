package Dominio;

import java.util.Stack;

public class DominioCalculadora {

    public static boolean validarParentesis(String expresion) {
        boolean correcta = true;

        Stack<Character> datos = new Stack<>();

        int i = 0;
        while (correcta && i < expresion.length()) {
            char actual = expresion.charAt(i);
            if (actual == '(') {
                datos.push(actual);
            } else if (actual == ')') {
                if (!datos.isEmpty()) {
                    char top = datos.peek();
                    if (top == '(') {
                        datos.pop(); // Eliminar el paréntesis correspondiente
                    } else {
                        correcta = false;
                    }
                } else {
                    correcta = false;
                }
            }
            // Se ignoran otros caracteres
            i++;
        }

        if (!datos.isEmpty()) {
            correcta = false;
        }

        return correcta;
    }

}





