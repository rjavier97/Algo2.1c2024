package aed;

class Funciones {
    int cuadrado(int x) {
        int res ;
        res = x*x ;
        return res;
    }

    double distancia(double x, double y) {
        double res ;
        res = Math.sqrt(x*x + y*y) ;
        return res;
    }

    boolean esPar(int n) {
        boolean res = (n % 2 == 0);
        return res; 
    }

    boolean esBisiesto(int n) {
        boolean res = ( (n % 4 == 0) && (n % 100 != 0 ) ) || (n % 400 == 0) ;
        return res;
    }

    int factorialIterativo(int n) {
        if ( n==0 ) return 1 ;
        int res = 1 ;
        for (int i=1 ; i<=n ; i++) {
            res = res*i;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        int res = 1;
        if (n == 0) { 
            return res ;
        } else {
            res = n*(factorialRecursivo(n-1));
        }
        return res;
    }

    boolean esPrimo(int n) {
        boolean res;
        int cantDivisores = 0;
        for (int i = 1 ; i<= n/2; i++) {
            if (n % i == 0) 
            cantDivisores = cantDivisores + 1 ;
        }
        res = cantDivisores == 1;
        return res;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for (int i : numeros) {
            res = res + i ;  
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int res = -1 ;
        for (int i = 0; i<numeros.length; i++) {
            if (numeros[i] == buscado) {
                res = i ;
            }
        }
        return res;
    }

    boolean tienePrimo(int[] numeros) {
        boolean res = false ;
        for (int i : numeros) {
            if (esPrimo(i)) {
                res = true;
            }
        }
        return res;
    }

    boolean todosPares(int[] numeros) {
        boolean res = true;
        for (int i : numeros) {
            if (! esPar(i)) {
                res = false ;
            } 
        }
        return res;
    }

    boolean esPrefijo(String s1, String s2) {
        boolean res = false;
        if (s1.length() <= s2.length()) {
            res = true;
            for (int i = 0 ; i<s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    res = false ;
                }
            }  
        }    
        return res;
    }

    boolean esSufijo(String s1, String s2) {
        boolean res = false ;
        String newString = "";
        if (s1.length() <= s2.length()) {
            for (int i = s2.length()-s1.length(); i<s2.length(); i++) {
                newString = newString+s2.charAt(i) ;
            }
        }
        res = esPrefijo(s1, newString);
            return res;
    }
}    
