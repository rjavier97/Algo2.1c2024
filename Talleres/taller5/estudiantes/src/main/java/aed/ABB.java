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
        private Nodo(T v) {
            this.valor = v;
            this.izq = null;
            this.der = null;
        }

        public T getValor() {
            return valor;
        }
        // public Nodo getNodoIzq(){
        //     return izq;
        // }
        // public Nodo getNodoDer(){
        //     return der;
        // }

        public int tamaño(){
            int suma = 0;
            if (this.izq != null && this.der != null){
                suma = suma + 2 + this.izq.tamaño() + this.der.tamaño();
            } else if (this.izq != null){
                suma = suma + 1 + this.izq.tamaño();
            } else if (this.der != null) {
                suma = suma + 1 + this.der.tamaño();
            }
            return suma;
        }

        // Otra version
        // public int tamaño() {
        //     int suma = 0;
        //     if (this.izq != null) {
        //         suma = suma + 1 + this.izq.tamaño();
        //     }
        //     if (this.der != null) {
        //         suma = suma + 1 + this.der.tamaño();
        //     }
        //     return suma;
        // }

        // Otra version
        // public int tamaño() {
        //     int suma = 0;
        //     if (this.izq != null) suma += 1+ this.izq.tamaño();
        //     if (this.der != null) suma += 1+ this.der.tamaño();
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

        public void borrar(T elem) {
            // Verificar si estamos en la raíz
            if (this.valor.compareTo(elem) > 0) {
                if (this.izq != null) {
                    if (this.izq.valor.compareTo(elem) == 0) {
                        this.izq = this.izq.eliminarNodo();
                    } else {
                        this.izq.borrar(elem);
                    }
                }
            } else if (this.valor.compareTo(elem) < 0) {
                if (this.der != null) {
                    if (this.der.valor.compareTo(elem) == 0) {
                        this.der = this.der.eliminarNodo();
                    } else {
                        this.der.borrar(elem);
                    }
                }
            } else {
                Nodo nodo = eliminarNodo();
                if (nodo != null) {
                    this.valor = nodo.valor;
                    this.izq = nodo.izq;
                    this.der = nodo.der;
                }
            }
        }
        
        private Nodo eliminarNodo() {
            if (this.izq == null && this.der == null) {
                return null;
            } else if (this.izq == null) {
                return this.der;
            } else if (this.der == null) {
                return this.izq;
            } else {
                T sucesorValor = this.der.min();
                this.valor = sucesorValor;
                this.der = this.der.borrarMin();
                return this;
            }
        }
        
        private Nodo borrarMin() {
            if (this.izq == null) {
                return this.der;
            } else {
                this.izq = this.izq.borrarMin();
                return this;
            }
        }


        // public T sucesor(){
        //     if (this.der != null){
        //         return this.der.min();
        //     }
        //     return null;   
        // }

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
        return this._raiz.getValor();
        // return null; 
    }

    public T maximo(){
        if (this._raiz != null){
            return this._raiz.max();
        }
        return this._raiz.getValor();
        //  return null;
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

    public void eliminar(T elem) {
        if (this._raiz != null) {
            if (this._raiz.valor.compareTo(elem) == 0) {
                this._raiz = this._raiz.eliminarNodo();
            } else {
                this._raiz.borrar(elem);
            }
        }
    }

    // public void eliminar(T elem) {
    //     if (this.pertenece(elem)) {
    //         this._raiz.borrar(elem);
    //     }
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
        private Stack<Nodo> pila;

        public ABB_Iterador(Nodo raiz) {
            pila = new Stack<>();
            Nodo actual = raiz;
            while (actual != null) {
                pila.push(actual);
                actual = actual.izq;
            }
        }

        public boolean haySiguiente() {
            return !pila.isEmpty();
        }

        public T siguiente() {         
            Nodo actual = pila.pop(); // Extrae el nodo actual de la pila

            T valor = actual.getValor(); // Obtiene el valor del nodo actual

            
            // Si hay un subárbol derecho, agrega todos los nodos del subárbol izquierdo de ese subárbol derecho a la pila
            if (actual.der != null) {
                actual = actual.der;
                while (actual != null) {
                    pila.push(actual);
                    actual = actual.izq;
                }
            }
            // Devuelve el valor del nodo actual
            return valor;
            // return actual.getValor();
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador(_raiz);
    }
}









//-------- Implementaciones y distintos intentos para la funcion borrar, ordenados de forma reciente(arriba) a mas antigua(abajo)
  

// --- Ultimo intento de borrar
// public void borrar(T elem){
//     // Rama izquierda
//     if (this.valor.compareTo(elem) > 0){
//         if (this.izq != null && this.izq.valor.compareTo(elem)==0){
//             if (this.izq.getNodoIzq() == null && this.izq.getNodoDer() == null){
//                 this.izq = null;
//             } else if (this.izq.getNodoIzq() == null){
//                 this.izq = this.izq.getNodoDer();
//             } else if (this.izq.getNodoDer() == null){
//                 this.izq = this.izq.getNodoIzq();
//             } else {
//                 T sucesor = this.izq.sucesor();
//                 this.izq.borrar(sucesor);
//                 this.izq.valor = sucesor; // Actualizamos el valor del nodo izquierdo con su sucesor
//             }
//         } else if (this.izq != null) {
//             this.izq.borrar(elem);
//         }
//     // Rama derecha
//     } else if (this.valor.compareTo(elem) < 0){
//         if (this.der != null && this.der.valor.compareTo(elem)==0){
//             if (this.der.getNodoIzq() == null && this.der.getNodoDer() == null){
//                 this.der = null;
//             } else if (this.der.getNodoIzq() == null){
//                 this.der = this.der.getNodoDer();
//             } else if (this.der.getNodoDer() == null){
//                 this.der = this.der.getNodoIzq();
//             } else {    
//                 T sucesor = this.der.sucesor();
//                 this.der.borrar(sucesor);
//                 this.der.valor = sucesor; // Actualizamos el valor del nodo derecho con su sucesor
//             }
//         } else if (this.der != null) {
//             this.der.borrar(elem);
//         }
//     // Cuando tengo que borrar la raiz del Arbol    
//     } else {
//         if (this.izq != null && this.der != null) {
//             this.valor = this.der.min();  // this.valor = this.sucesor() es lo mismo 
//             this.der.borrar(this.valor);
//         } else if (this.izq != null){
//             this.valor = this.izq.valor;
//             this.der = this.izq.getNodoDer();
//             this.izq = this.izq.getNodoIzq();
//         } else if (this.der != null){
//             this.valor = this.der.valor;
//             this.izq = this.der.getNodoIzq();
//             this.der = this.der.getNodoDer();
//         }
//     }
// }




 // public void borrar(T elem){
        //     // Rama izquierda
        //     if (this.valor.compareTo(elem) > 0){
        //         if (this.izq != null && this.izq.valor.compareTo(elem)==0){
        //             if (this.izq.getNodoIzq() == null && this.izq.getNodoDer() == null){
        //                 this.izq = null;
        //             } else if (this.izq.getNodoIzq() == null){
        //                 this.izq = this.izq.getNodoDer();
        //             } else if (this.izq.getNodoDer() == null){
        //                 this.izq = this.izq.getNodoIzq();
        //             } else {
        //                 T sucesor = this.izq.sucesor();
        //                 this.izq.borrar(sucesor);
        //                 this.valor = sucesor;
        //             }
        //         } else if (this.izq != null) {
        //             this.izq.borrar(elem);
        //         }
        //     // Rama derecha
        //     } else if (this.valor.compareTo(elem) < 0){
        //         if (this.der != null && this.der.valor.compareTo(elem)==0){
        //             if (this.der.getNodoIzq() == null && this.der.getNodoDer() == null){
        //                 this.der = null;
        //             } else if (this.der.getNodoIzq() == null){
        //                 this.der = this.der.getNodoDer();
        //             } else if (this.der.getNodoDer() == null){
        //                 this.der = this.der.getNodoIzq();
        //             } else {    
        //                 T sucesor = this.der.sucesor();
        //                 this.der.borrar(sucesor);
        //                 this.valor = sucesor;
        //             }
        //         } else if (this.der != null) {
        //             this.der.borrar(elem);
        //         }
        //     // Cuando tengo que borrar la raiz del Arbol    
        //     } else {
        //         if (this.izq != null && this.der != null) {
        //             this.valor = this.der.min();  // this.valor = this.sucesor() es lo mismo 
        //             this.der.borrar(this.valor);
        //         } else if (this.izq != null){
        //             this.valor = this.izq.valor;
        //             this.der = this.izq.getNodoDer();
        //             this.izq = this.izq.getNodoIzq();
        //         } else if (this.der != null){
        //             this.valor = this.der.valor;
        //             this.izq = this.der.getNodoIzq();
        //             this.der = this.der.getNodoDer();
        //         }
        //     }
        // }



        // public void borrar(T elem){
        //     // Rama izquierda
        //     if (this.valor.compareTo(elem) > 0){
        //         if (this.izq != null && this.izq.valor.compareTo(elem)==0){
        //             if (this.izq.getNodoIzq() == null && this.izq.getNodoDer() == null){
        //                 this.izq = null;
        //             } else if (this.izq.getNodoIzq() == null){
        //                 this.izq = this.izq.getNodoDer();
        //             } else if (this.izq.getNodoDer() == null){
        //                 this.izq = this.izq.getNodoIzq();
        //             } else {
        //                 // this.valor = this.izq.sucesor();
        //                 // this.izq.borrar(this.valor);
        //                 this.izq.valor = this.izq.sucesor();
        //                 this.izq.getNodoDer().borrar(this.izq.valor);
        //             }
        //         } else if (this.izq != null) {
        //             this.izq.borrar(elem);
        //         }
        //     // Rama derecha
        //     } else if (this.valor.compareTo(elem) < 0){
        //         if (this.der != null && this.der.valor.compareTo(elem)==0){
        //             if (this.der.getNodoIzq() == null && this.der.getNodoDer() == null){
        //                 this.der = null;
        //             } else if (this.der.getNodoIzq() == null){
        //                 this.der = this.der.getNodoDer();
        //             } else if (this.der.getNodoDer() == null){
        //                 this.der = this.der.getNodoIzq();
        //             } else {    
        //                 // this.valor = this.der.sucesor();
        //                 // this.der.borrar(this.valor);
        //                 this.der.valor = this.der.sucesor();
        //                 this.der.getNodoDer().borrar(this.der.valor);
        //             }
        //         } else if (this.der != null) {
        //             this.der.borrar(elem);
        //         }
        //     // Cuando tengo que borrar la raiz del Arbol    
        //     } else {
        //         if (this.izq != null && this.der != null) {
        //             this.valor = this.der.min();  // this.valor = this.sucesor() es lo mismo 
        //             this.der.borrar(this.valor);
        //         } else if (this.izq != null){
        //             this.valor = this.izq.valor;
        //             this.der = this.izq.getNodoDer();
        //             this.izq = this.izq.getNodoIzq();
        //         } else if (this.der != null){
        //             this.valor = this.der.valor;
        //             this.izq = this.der.getNodoIzq();
        //             this.der = this.der.getNodoDer();
        //         }
        //     }
        // } 