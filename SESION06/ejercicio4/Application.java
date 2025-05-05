package SESION06.ejercicio4;

import SESION06.ejercicio1.StackLink;
import SESION06.ejercicio1.ExceptionIsEmpty;

public class Application {
    public static boolean symbolBalancing(String s) {
        StackLink<Character> stack = new StackLink<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // Si es un corchete de apertura, lo apilamos
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } 
            // Si es un corchete de cierre
            else if (c == ')' || c == ']' || c == '}') {
                try {
                    char top = stack.pop();
                    
                    // Verificamos que coincida con el corchete de apertura
                    if ((c == ')' && top != '(') || 
                        (c == ']' && top != '[') || 
                        (c == '}' && top != '{')) {
                        return false;
                    }
                } catch (ExceptionIsEmpty e) {
                    // Si la pila está vacía cuando encontramos un cierre
                    return false;
                }
            }
        }
        
        // Al final, la pila debe estar vacía
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // Pruebas
        System.out.println(symbolBalancing("()()()[()]()"));  // true
        System.out.println(symbolBalancing("((()))[]"));      // true
        System.out.println(symbolBalancing("([])[]("));      // false
        System.out.println(symbolBalancing("([{)]}"));       // false
        System.out.println(symbolBalancing("["));            // false
        System.out.println(symbolBalancing("[][][]{{{}}}")); // true
    }
}
