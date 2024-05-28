package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo _raiz;

    private class Nodo {
        // Agregar atributos privados del Nodo
        private T valor;
        private Nodo izq;
        private Nodo der;

        // Crear Constructor del nodo
        private Nodo (T v) {
            this.valor = v;
            this.izq = null;
            this.der = null;
        }

        public T getValor() {
            return valor;
        }
        public Nodo getNodoIzq(){
            return izq;
        }
        public Nodo getNodoDer(){
            return der;
        }
        public int tamaño() {
            int suma = 0;
            if (this.izq != null) suma += 1+ this.izq.tamaño();
            if (this.der != null) suma += 1+ this.der.tamaño();
            return suma;
        }
        // Otra version 
        // public int tamaño(){
        //     int suma = 0;
        //     if (this.izq != null && this.der != null){
        //         suma = suma + 2 + this.izq.tamaño() + this.der.tamaño();
        //     } else if (this.izq != null){
        //         suma = suma + 1 + this.izq.tamaño();
        //     } else if (this.der != null) {
        //         suma = suma + 1 + this.der.tamaño();
        //     }
        //     return suma;
        // }
      
        public void insert(T elem){
            if (this.valor.compareTo(elem) > 0) {
                if (this.izq == null){
                    this.izq = new Nodo(elem);
                } else {
                    this.izq.insert(elem);
                } 
            } else if (this.valor.compareTo(elem) < 0){
                if (this.der == null){
                    this.der = new Nodo(elem);    
                } else {
                    this.der.insert(elem);
                }
            }
        }

        public boolean esta(T elem){
            if (this.valor.compareTo(elem) == 0) {
                return true;
            } else if (this.valor.compareTo(elem) > 0) {
                return this.izq != null && this.izq.esta(elem);
            } else {
                return this.der != null && this.der.esta(elem);
            }
        }

        // public void borrar(T elem, Nodo parent) {
        //     if (this.valor.compareTo(elem) > 0) {
        //         if (this.izq != null) {
        //             this.izq.borrar(elem, this);
        //         }
        //     } else if (this.valor.compareTo(elem) < 0) {
        //         if (this.der != null) {
        //             this.der.borrar(elem, this);
        //         }
        //     } else {
        //         if (this.izq != null && this.der != null) {
        //             this.valor = this.der.min();
        //             this.der.borrar(this.valor, this);
        //         } else if (parent == null) {
        //             if (this.izq != null) {
        //                 this.valor = this.izq.valor;
        //                 this.der = this.izq.getNodoDer();
        //                 this.izq = this.izq.getNodoIzq();
        //             } else if (this.der != null) {
        //                 this.valor = this.der.valor;
        //                 this.izq = this.der.getNodoIzq();
        //                 this.der = this.der.getNodoDer();
        //             } else {
        //                 // El árbol queda vacío
        //                 _raiz = null;
        //             }
        //         } else if (parent.izq == this) {
        //             parent.izq = (this.izq != null) ? this.izq : this.der;
        //         } else if (parent.der == this) {
        //             parent.der = (this.izq != null) ? this.izq : this.der;
        //         }
        //     }
        // }
    
        public void borrar(T elem){
            // Rama izquierda
            if (this.valor.compareTo(elem) > 0){
                if (this.izq != null && this.izq.valor.compareTo(elem)==0){
                    if (this.izq.getNodoIzq() == null && this.izq.getNodoDer() == null){
                        this.izq = null;
                    } else if (this.izq.getNodoIzq() == null){
                        this.izq = this.izq.getNodoDer();
                    } else if (this.izq.getNodoDer() == null){
                        this.izq = this.izq.getNodoIzq();
                    } else {
                        this.izq.valor = this.izq.sucesor();
                        this.izq.getNodoDer().borrar(this.izq.valor);
                    }
                } else if (this.izq != null) {
                    this.izq.borrar(elem);
                }
            // Rama derecha
            } else if (this.valor.compareTo(elem) < 0){
                if (this.der != null && this.der.valor.compareTo(elem)==0){
                    if (this.der.getNodoIzq() == null && this.der.getNodoDer() == null){
                        this.der = null;
                    } else if (this.der.getNodoIzq() == null){
                        this.der = this.der.getNodoDer();
                    } else if (this.der.getNodoDer() == null){
                        this.der = this.der.getNodoIzq();
                    } else {    
                        this.der.valor = this.der.sucesor();
                        this.der.getNodoDer().borrar(this.der.valor);
                    }
                } else if (this.der != null) {
                    this.der.borrar(elem);
                }
            // Cuando tengo que borrar la raiz del Arbol    
            } else {
                if (this.izq != null && this.der != null) {
                    this.valor = this.der.min();  // this.valor = this.sucesor() es lo mismo 
                    this.der.borrar(this.valor);
                } else if (this.izq != null){
                    this.valor = this.izq.valor;
                    this.der = this.izq.getNodoDer();
                    this.izq = this.izq.getNodoIzq();
                } else if (this.der != null){
                    this.valor = this.der.valor;
                    this.izq = this.der.getNodoIzq();
                    this.der = this.der.getNodoDer();
                }
            }
        } 

        // public void borrar(T elem) {
        //     if (this.valor.compareTo(elem) > 0) {
        //         if (this.izq != null) {
        //             if (this.izq.valor.compareTo(elem) == 0) {
        //                 this.izq = this.izq.borrarNodo();
        //             } else {
        //                 this.izq.borrar(elem);
        //             }
        //         }
        //     } else if (this.valor.compareTo(elem) < 0) {
        //         if (this.der != null) {
        //             if (this.der.valor.compareTo(elem) == 0) {
        //                 this.der = this.der.borrarNodo();
        //             } else {
        //                 this.der.borrar(elem);
        //             }
        //         }
        //     } else {
        //         borrarNodo();
        //     }
        // }

        // private Nodo borrarNodo() {
        //     if (this.izq == null && this.der == null) {
        //         return null;
        //     } else if (this.izq == null) {
        //         return this.der;
        //     } else if (this.der == null) {
        //         return this.izq;
        //     } else {
        //         this.valor = this.sucesor();
        //         this.der.borrar(this.valor);
        //         return this;
        //     }
        // }
        

        public T sucesor(){
            if (this.der != null){
                return this.der.min();
            }
            return null;   
        }

        public T min(){
            if (this.izq !=null){
                return this.izq.min();
            }
            return this.valor;
        }

        public T max(){
            if (this.der != null){
                return this.der.max();
            }
            return this.valor;
        }


        public <T extends Comparable<T>> int compareTo(T elem) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
        }

        public <T extends Comparable<T>> Object encontrarSucesor(T valor2) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'encontrarSucesor'");
        }

        public <T extends Comparable<T>> T encontrarSucesor(ABB<T>.Nodo _actual) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'encontrarSucesor'");
        }

    }
    
    public ABB() {
        this._raiz = null;
    }

    public int cardinal() {
        if (this._raiz == null) {
            return 0;
        } else {
            return 1 + this._raiz.tamaño();      
        }
    }

    public T minimo(){
        if (this._raiz != null){
            return this._raiz.min();
        }
        return null; // return this._raiz.getValor();
    }

    public T maximo(){
        if (this._raiz != null){
            return this._raiz.max();
        }
        return null; // return this._raiz.getValor();
    }

    public void insertar(T elem){
        if (this._raiz == null){
            this._raiz = new Nodo(elem);
        } else {     
            this._raiz.insert(elem);
        }    
    } 
 
    public boolean pertenece(T elem){
        if (this._raiz == null) {
            return false;
        } else {
            return this._raiz.esta(elem);
        }
    }
                                        //    elem1.compareTo(elem2) 
// elem1.compareTo(elem2) devuelve un entero. Si es > a 0, entonces elem1 > elem2
                                        //    Si es < a 0, entonces elem2 > elem1
    private T encontrarSucesor(T valor) {
        Nodo actual = _raiz;
        Nodo sucesor = null;
        while (actual != null) {
            if (valor.compareTo(actual.valor) < 0) { // si actual.valor > valor
                sucesor = actual;
                actual = actual.izq;
            } else {
                actual = actual.der;
            }
        }
        return sucesor != null ? sucesor.valor : null;  //if (sucesor!=null){return sucesor.valor}else{return null} 
    }


    // public void eliminar(T elem) {
    //     if (this._raiz != null && this._raiz.valor.compareTo(elem) == 0) {
    //         this._raiz = this._raiz.borrarNodo();
    //     } else if (this._raiz != null) {
    //         this._raiz.borrar(elem);
    //     }
    // }

    // public void eliminar(T elem) {
    //     if (this._raiz != null && this._raiz.valor.compareTo(elem) == 0) {
    //         if (this._raiz.izq == null && this._raiz.der == null) {
    //             this._raiz = null;
    //         } else if (this._raiz.izq == null) {
    //             this._raiz = this._raiz.der;
    //         } else if (this._raiz.der == null) {
    //             this._raiz = this._raiz.izq;
    //         } else {
    //             this._raiz.valor = this._raiz.sucesor();
    //             this._raiz.der.borrar(this._raiz.valor);
    //         }
    //     } else if (this._raiz != null) {
    //         this._raiz.borrar(elem);
    //     }
    // }    


    public void eliminar(T elem) {
        if (this.pertenece(elem)) {
            this._raiz.borrar(elem);
        }
    }

    // public void eliminar(T elem){
    //     if (this.pertenece(elem)) {
    //         if (this._raiz.valor.compareTo(elem) == 0) {
    //             if (this._raiz.izq == null && this._raiz.der == null) {
    //                 this._raiz = null;
    //             } else if (this._raiz.izq == null) {
    //                 this._raiz = this._raiz.der;
    //             } else if (this._raiz.der == null) {
    //                 this._raiz = this._raiz.izq;
    //             } else {
    //                 this._raiz.valor = this._raiz.sucesor();
    //                 this._raiz.der.borrar(this._raiz.valor);
    //             }
    //         } else {
    //             this._raiz.borrar(elem);
    //         }
    //     }
    // }
        // if (this._raiz != null) {
        //     if (this._raiz.valor.compareTo(elem) == 0 && this._raiz.izq == null && this._raiz.der == null) {
        //         this._raiz = null; // El árbol solo tenía un nodo
        //     } else {
        //         this._raiz.borrar(elem);
        //     }
        // }
      
// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2

    // public String toString(){
    //     StringBuffer buffer = new StringBuffer();
    //     buffer.append("{");
    //     T i = this.minimo();
    //     T j = this.maximo();

    //     while (i.compareTo(j) <=0){
    //         buffer.append(i.toString()+", ");
    //         i = encontrarSucesor(i) ;
    //     }
    //     buffer.append(i.toString()+"}");
    //     return buffer.toString();

    // }
    public String toString() {
        if (this._raiz == null) {
            return "{}";
        }

        StringBuilder buffer = new StringBuilder();
        buffer.append("{");

        T actual = this.minimo();
        T maximo = this.maximo();

        while (actual != null && actual.compareTo(maximo) <= 0) {
            buffer.append(actual.toString());
            actual = encontrarSucesor(actual);
            if (actual != null && actual.compareTo(maximo) <= 0) {
                buffer.append(",");
            }
        }

        buffer.append("}");
        return buffer.toString();
    }

    private class ABB_Iterador implements Iterador<T> {
        private Stack<Nodo> stack;
    
        public ABB_Iterador(Nodo raiz) {
            stack = new Stack<>();
            Nodo actual = raiz;
            while (actual != null) {
                stack.push(actual);
                actual = actual.izq;
            }
        }
    
        public boolean haySiguiente() {
            return !stack.isEmpty();
        }
    
        public T siguiente() {
            if (!haySiguiente()) {
                throw new NoSuchElementException("No hay más elementos en la colección");
            }
            
            Nodo actual = stack.pop(); // Extrae el nodo actual de la pila
            
            // Si hay un subárbol derecho, agrega todos los nodos del subárbol izquierdo de ese subárbol derecho a la pila
            if (actual.der != null) {
                actual = actual.der;
                while (actual != null) {
                    stack.push(actual);
                    actual = actual.izq;
                }
            }
            
            // Devuelve el valor del nodo actual
            return actual.getValor();
        }
    }
}


// package aed;

// import java.util.*;

// // Todos los tipos de datos "Comparables" tienen el método compareTo()
// // elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
// public class ABB<T extends Comparable<T>> implements Conjunto<T> {
//     // Agregar atributos privados del Conjunto
//     private Nodo _raiz;

//     private class Nodo {
//         // Agregar atributos privados del Nodo
//         private T valor;
//         private Nodo izq;
//         private Nodo der;

//         // Crear Constructor del nodo
//         private Nodo (T v) {
//             this.valor = v;
//             this.izq = null;
//             this.der = null;
//         }

//         public T getValor() {
//             return valor;
//         }
//         public Nodo getNodoIzq(){
//             return izq;
//         }
//         public Nodo getNodoDer(){
//             return der;
//         }
//         public int tamaño() {
//             int suma = 0;
//             if (this.izq != null) suma += 1+ this.izq.tamaño();
//             if (this.der != null) suma += 1+ this.der.tamaño();
//             return suma;
//         }
//         // Otra version 
//         // public int tamaño(){
//         //     int suma = 0;
//         //     if (this.izq != null && this.der != null){
//         //         suma = suma + 2 + this.izq.tamaño() + this.der.tamaño();
//         //     } else if (this.izq != null){
//         //         suma = suma + 1 + this.izq.tamaño();
//         //     } else if (this.der != null) {
//         //         suma = suma + 1 + this.der.tamaño();
//         //     }
//         //     return suma;
//         // }
      
//         public void insert(T elem){
//             if (this.valor.compareTo(elem) > 0) {
//                 if (this.izq == null){
//                     this.izq = new Nodo(elem);
//                 } else {
//                     this.izq.insert(elem);
//                 } 
//             } else if (this.valor.compareTo(elem) < 0){
//                 if (this.der == null){
//                     this.der = new Nodo(elem);    
//                 } else {
//                     this.der.insert(elem);
//                 }
//             }
//         }

//         public boolean esta(T elem){
//             if (this.valor.compareTo(elem) == 0) {
//                 return true;
//             } else if (this.valor.compareTo(elem) > 0) {
//                 return this.izq != null && this.izq.esta(elem);
//             } else {
//                 return this.der != null && this.der.esta(elem);
//             }
//         }

//         // public void borrar(T elem, Nodo parent) {
//         //     if (this.valor.compareTo(elem) > 0) {
//         //         if (this.izq != null) {
//         //             this.izq.borrar(elem, this);
//         //         }
//         //     } else if (this.valor.compareTo(elem) < 0) {
//         //         if (this.der != null) {
//         //             this.der.borrar(elem, this);
//         //         }
//         //     } else {
//         //         if (this.izq != null && this.der != null) {
//         //             this.valor = this.der.min();
//         //             this.der.borrar(this.valor, this);
//         //         } else if (parent == null) {
//         //             if (this.izq != null) {
//         //                 this.valor = this.izq.valor;
//         //                 this.der = this.izq.getNodoDer();
//         //                 this.izq = this.izq.getNodoIzq();
//         //             } else if (this.der != null) {
//         //                 this.valor = this.der.valor;
//         //                 this.izq = this.der.getNodoIzq();
//         //                 this.der = this.der.getNodoDer();
//         //             } else {
//         //                 // El árbol queda vacío
//         //                 _raiz = null;
//         //             }
//         //         } else if (parent.izq == this) {
//         //             parent.izq = (this.izq != null) ? this.izq : this.der;
//         //         } else if (parent.der == this) {
//         //             parent.der = (this.izq != null) ? this.izq : this.der;
//         //         }
//         //     }
//         // }
    
//         public void borrar(T elem){
//             // Rama izquierda
//             if (this.valor.compareTo(elem) > 0){
//                 if (this.izq != null && this.izq.valor.compareTo(elem)==0){
//                     if (this.izq.getNodoIzq() == null && this.izq.getNodoDer() == null){
//                         this.izq = null;
//                     } else if (this.izq.getNodoIzq() == null){
//                         this.izq = this.izq.getNodoDer();
//                     } else if (this.izq.getNodoDer() == null){
//                         this.izq = this.izq.getNodoIzq();
//                     } else {
//                         this.izq.valor = this.izq.sucesor();
//                         this.izq.getNodoDer().borrar(this.izq.valor);
//                     }
//                 } else if (this.izq != null) {
//                     this.izq.borrar(elem);
//                 }
//             // Rama derecha
//             } else if (this.valor.compareTo(elem) < 0){
//                 if (this.der != null && this.der.valor.compareTo(elem)==0){
//                     if (this.der.getNodoIzq() == null && this.der.getNodoDer() == null){
//                         this.der = null;
//                     } else if (this.der.getNodoIzq() == null){
//                         this.der = this.der.getNodoDer();
//                     } else if (this.der.getNodoDer() == null){
//                         this.der = this.der.getNodoIzq();
//                     } else {    
//                         this.der.valor = this.der.sucesor();
//                         this.der.getNodoDer().borrar(this.der.valor);
//                     }
//                 } else if (this.der != null) {
//                     this.der.borrar(elem);
//                 }
//             // Cuando tengo que borrar la raiz del Arbol    
//             } else {
//                 if (this.izq != null && this.der != null) {
//                     this.valor = this.der.min();  // this.valor = this.sucesor() es lo mismo 
//                     this.der.borrar(this.valor);
//                 } else if (this.izq != null){
//                     this.valor = this.izq.valor;
//                     this.der = this.izq.getNodoDer();
//                     this.izq = this.izq.getNodoIzq();
//                 } else if (this.der != null){
//                     this.valor = this.der.valor;
//                     this.izq = this.der.getNodoIzq();
//                     this.der = this.der.getNodoDer();
//                 }
//             }
//         } 

//         // public void borrar(T elem) {
//         //     if (this.valor.compareTo(elem) > 0) {
//         //         if (this.izq != null) {
//         //             if (this.izq.valor.compareTo(elem) == 0) {
//         //                 this.izq = this.izq.borrarNodo();
//         //             } else {
//         //                 this.izq.borrar(elem);
//         //             }
//         //         }
//         //     } else if (this.valor.compareTo(elem) < 0) {
//         //         if (this.der != null) {
//         //             if (this.der.valor.compareTo(elem) == 0) {
//         //                 this.der = this.der.borrarNodo();
//         //             } else {
//         //                 this.der.borrar(elem);
//         //             }
//         //         }
//         //     } else {
//         //         borrarNodo();
//         //     }
//         // }

//         // private Nodo borrarNodo() {
//         //     if (this.izq == null && this.der == null) {
//         //         return null;
//         //     } else if (this.izq == null) {
//         //         return this.der;
//         //     } else if (this.der == null) {
//         //         return this.izq;
//         //     } else {
//         //         this.valor = this.sucesor();
//         //         this.der.borrar(this.valor);
//         //         return this;
//         //     }
//         // }
        

//         public T sucesor(){
//             if (this.der != null){
//                 return this.der.min();
//             }
//             return null;   
//         }

//         public T min(){
//             if (this.izq !=null){
//                 return this.izq.min();
//             }
//             return this.valor;
//         }

//         public T max(){
//             if (this.der != null){
//                 return this.der.max();
//             }
//             return this.valor;
//         }


//         public <T extends Comparable<T>> int compareTo(T elem) {
//             // TODO Auto-generated method stub
//             throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
//         }

//         public <T extends Comparable<T>> Object encontrarSucesor(T valor2) {
//             // TODO Auto-generated method stub
//             throw new UnsupportedOperationException("Unimplemented method 'encontrarSucesor'");
//         }

//         public <T extends Comparable<T>> T encontrarSucesor(ABB<T>.Nodo _actual) {
//             // TODO Auto-generated method stub
//             throw new UnsupportedOperationException("Unimplemented method 'encontrarSucesor'");
//         }

//     }
    
//     public ABB() {
//         this._raiz = null;
//     }

//     public int cardinal() {
//         if (this._raiz == null) {
//             return 0;
//         } else {
//             return 1 + this._raiz.tamaño();      
//         }
//     }

//     public T minimo(){
//         if (this._raiz != null){
//             return this._raiz.min();
//         }
//         return null; // return this._raiz.getValor();
//     }

//     public T maximo(){
//         if (this._raiz != null){
//             return this._raiz.max();
//         }
//         return null; // return this._raiz.getValor();
//     }

//     public void insertar(T elem){
//         if (this._raiz == null){
//             this._raiz = new Nodo(elem);
//         } else {     
//             this._raiz.insert(elem);
//         }    
//     } 
 
//     public boolean pertenece(T elem){
//         if (this._raiz == null) {
//             return false;
//         } else {
//             return this._raiz.esta(elem);
//         }
//     }
//                                         //    elem1.compareTo(elem2) 
// // elem1.compareTo(elem2) devuelve un entero. Si es > a 0, entonces elem1 > elem2
//                                         //    Si es < a 0, entonces elem2 > elem1
//     private T encontrarSucesor(T valor) {
//         Nodo actual = _raiz;
//         Nodo sucesor = null;
//         while (actual != null) {
//             if (valor.compareTo(actual.valor) < 0) { // si actual.valor > valor
//                 sucesor = actual;
//                 actual = actual.izq;
//             } else {
//                 actual = actual.der;
//             }
//         }
//         return sucesor != null ? sucesor.valor : null;  //if (sucesor!=null){return sucesor.valor}else{return null} 
//     }


//     // public void eliminar(T elem) {
//     //     if (this._raiz != null && this._raiz.valor.compareTo(elem) == 0) {
//     //         this._raiz = this._raiz.borrarNodo();
//     //     } else if (this._raiz != null) {
//     //         this._raiz.borrar(elem);
//     //     }
//     // }

//     // public void eliminar(T elem) {
//     //     if (this._raiz != null && this._raiz.valor.compareTo(elem) == 0) {
//     //         if (this._raiz.izq == null && this._raiz.der == null) {
//     //             this._raiz = null;
//     //         } else if (this._raiz.izq == null) {
//     //             this._raiz = this._raiz.der;
//     //         } else if (this._raiz.der == null) {
//     //             this._raiz = this._raiz.izq;
//     //         } else {
//     //             this._raiz.valor = this._raiz.sucesor();
//     //             this._raiz.der.borrar(this._raiz.valor);
//     //         }
//     //     } else if (this._raiz != null) {
//     //         this._raiz.borrar(elem);
//     //     }
//     // }    


//     public void eliminar(T elem) {
//         if (this.pertenece(elem)) {
//             this._raiz.borrar(elem);
//         }
//     }

//     // public void eliminar(T elem){
//     //     if (this.pertenece(elem)) {
//     //         if (this._raiz.valor.compareTo(elem) == 0) {
//     //             if (this._raiz.izq == null && this._raiz.der == null) {
//     //                 this._raiz = null;
//     //             } else if (this._raiz.izq == null) {
//     //                 this._raiz = this._raiz.der;
//     //             } else if (this._raiz.der == null) {
//     //                 this._raiz = this._raiz.izq;
//     //             } else {
//     //                 this._raiz.valor = this._raiz.sucesor();
//     //                 this._raiz.der.borrar(this._raiz.valor);
//     //             }
//     //         } else {
//     //             this._raiz.borrar(elem);
//     //         }
//     //     }
//     // }
//         // if (this._raiz != null) {
//         //     if (this._raiz.valor.compareTo(elem) == 0 && this._raiz.izq == null && this._raiz.der == null) {
//         //         this._raiz = null; // El árbol solo tenía un nodo
//         //     } else {
//         //         this._raiz.borrar(elem);
//         //     }
//         // }
      
// // Todos los tipos de datos "Comparables" tienen el método compareTo()
// // elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2

//     // public String toString(){
//     //     StringBuffer buffer = new StringBuffer();
//     //     buffer.append("{");
//     //     T i = this.minimo();
//     //     T j = this.maximo();

//     //     while (i.compareTo(j) <=0){
//     //         buffer.append(i.toString()+", ");
//     //         i = encontrarSucesor(i) ;
//     //     }
//     //     buffer.append(i.toString()+"}");
//     //     return buffer.toString();

//     // }
//     public String toString() {
//         if (this._raiz == null) {
//             return "{}";
//         }

//         StringBuilder buffer = new StringBuilder();
//         buffer.append("{");

//         T actual = this.minimo();
//         T maximo = this.maximo();

//         while (actual != null && actual.compareTo(maximo) <= 0) {
//             buffer.append(actual.toString());
//             actual = encontrarSucesor(actual);
//             if (actual != null && actual.compareTo(maximo) <= 0) {
//                 buffer.append(",");
//             }
//         }

//         buffer.append("}");
//         return buffer.toString();
//     }

//     private class ABB_Iterador implements Iterador<T> {
//         private Stack<Nodo> stack;
    
//         public ABB_Iterador(Nodo raiz) {
//             stack = new Stack<>();
//             Nodo actual = raiz;
//             while (actual != null) {
//                 stack.push(actual);
//                 actual = actual.izq;
//             }
//         }
    
//         public boolean haySiguiente() {
//             return !stack.isEmpty();
//         }
    
//         public T siguiente() {
//             if (!haySiguiente()) {
//                 throw new NoSuchElementException("No hay más elementos en la colección");
//             }
            
//             Nodo actual = stack.pop(); // Extrae el nodo actual de la pila
            
//             // Si hay un subárbol derecho, agrega todos los nodos del subárbol izquierdo de ese subárbol derecho a la pila
//             if (actual.der != null) {
//                 actual = actual.der;
//                 while (actual != null) {
//                     stack.push(actual);
//                     actual = actual.izq;
//                 }
//             }
            
//             // Devuelve el valor del nodo actual
//             return actual.getValor();
//         }
//     }
// }

        // public T siguiente() {
        //     if (!haySiguiente()) {
        //         throw new NoSuchElementException();
        //     }
        
        //     Nodo actual = stack.pop();
        //     T valor = actual.getValor();
        
        //     // Si hay un subárbol derecho, agregamos todos los nodos del subárbol izquierdo de ese subárbol derecho a la pila
        //     if (actual.der != null) {
        //         actual = actual.der;
        //         while (actual != null) {
        //             stack.push(actual);
        //             actual = actual.izq;
        //         }
        //     }
        
        //     return valor;
        // }

        // public Iterador<T> iterador() {
        //     return new ABB_Iterador(_raiz);
        // }
    

    //     private Nodo _actual;

    //     public boolean haySiguiente() {            
    //             return (this._actual.encontrarSucesor(this._actual.getValor()) !=null);

    //     }
    
    //     public T siguiente() {
    //         return this._actual.encontrarSucesor(this._actual);
    //     }
    // }
  



// 1° Implementacion ----------------------------------------------------
// package aed;

// import java.util.*;

// // Todos los tipos de datos "Comparables" tienen el método compareTo()
// // elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
// public class ABB<T extends Comparable<T>> implements Conjunto<T> {
//     // Agregar atributos privados del Conjunto
//     private Nodo _raiz;
//     // private T valor;
//     // private Nodo izq;
//     // private Nodo der;

//     private class Nodo {
//         // Agregar atributos privados del Nodo
//         private T valor;
//         private Nodo izq;
//         private Nodo der;

//         // Crear Constructor del nodo
//         private Nodo (T v) {
//             this.valor = v;
//             this.izq = null;
//             this.der = null;
//         }
//         public int tamaño(){
//             int suma = 0;
//             if (this.izq != null && this.der != null){
//                 suma = suma + 2 + this.izq.tamaño() + this.der.tamaño();
//             } else if (this.izq != null){
//                 suma = suma + 1 + this.izq.tamaño();
//             } else if (this.der != null) {
//                 suma = suma + 1 + this.der.tamaño();
//             } else {
//                 suma = suma + 0;
//             }
//             return suma;
//         }
      
//         public void insert(T elem){
//             if (this.valor.compareTo(elem) > 0) {
//                 if (this.izq == null){
//                     this.izq = new Nodo(elem);
//                 } else {
//                     this.izq.insert(elem);
//                 } 
//             } else {
//                 if (this.der == null){
//                     this.der = new Nodo(elem);    
//                 } else {
//                     this.der.insert(elem);
//                 }
//             }
//         }

//         public boolean esta(T elem){
//             boolean res = false;
//             if (this.valor.compareTo(elem) > 0){
//                 if (this.izq == elem){
//                     res = true;
//                 } else {
//                     this.izq.esta(elem);
//                 }
//             } else {
//                 if (this.der == elem){
//                     res = true;
//                 } else {
//                     this.der.esta(elem);
//                 }
//             }
//             return res;
//         }
//         public <T extends Comparable<T>> int compareTo(T elem) {
//             // TODO Auto-generated method stub
//             throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
//         }

//     }
    

//     public ABB() {
//         this._raiz = null;
//     }

//     public int cardinal() {
//         if (this._raiz == null) {
//             return 0;
//         } else {
//             return 1 + this._raiz.tamaño();      
//         }
//     }

//     public void insertar(T elem){
//         if (this._raiz == null){
//             this._raiz = new Nodo(elem);
//         } else if (pertenece(elem)) {     
//             } else {
//                 this._raiz.insert(elem);
//             }
//         } 
 
//     public boolean pertenece(T elem){
//         if (this._raiz == null) {
//             return false;
//         } else if (this._raiz == elem) {
//             return true;
//         } else {
//             return this._raiz.esta(elem);
//         }
//     }


// 2° Implementacion
// funcion void borrar de la clase Nodo
// public void borrar(T elem){
//     // Rama izquierda
//     if (this.valor.compareTo(elem) > 0){
//         if (this.izq.valor.compareTo(elem)==0){
//             if (this.izq.getNodoIzq() == null && this.der.getNodoDer() == null){
//                 this.izq = null;
//             } else if (this.izq.getNodoIzq() == null){
//                 this.izq = this.izq.getNodoDer();
//             } else if (this.izq.getNodoDer() == null){
//                 this.izq = this.izq.getNodoIzq();
//             } else {


//             }
//         }
//     // Rama derecha
//     } else if (this.valor.compareTo(elem) < 0){
//         if (this.der.valor.compareTo(elem)==0){
//             if (this.izq.getNodoIzq() == null && this.der.getNodoDer() == null){
//                 this.der = null;
//             } else if (this.der.getNodoIzq() == null){
//                 this.der = this.der.getNodoDer();
//             } else if (this.der.getNodoDer() == null){
//                 this.der = this.der.getNodoIzq();
//             } else {    
//                 this.der = this.der.sucesor();
//             }
//             }
//         }
// }     