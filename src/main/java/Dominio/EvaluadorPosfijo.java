package Dominio;

import java.util.Stack;

public class EvaluadorPosfijo {

    public  StringBuilder convertirAExpresionPosfija(String expresion) {

        StringBuilder exprecionResultante = new StringBuilder();
        Stack<Character> pila = new Stack<>();
        for (int i = 0; i < expresion.length(); i++){
            char actual = expresion.charAt(i);
            if (!esOperadorOhParentesis(actual)){
                exprecionResultante.append(actual);
            }
            else if (esUnParentesis(actual)){
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

    private  boolean esOperadorOhParentesis(char token) {
        return token == '+' || token == '-' || token == '*' || token == '/' || token == ')' || token == '(';
    }

    public boolean esUnParentesis(char token){
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
}
