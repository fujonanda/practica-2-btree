import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Clase ArbolB
 * Representa la estructura de un árbol B de orden m = 4.
 * Permite realizar operaciones de búsqueda, inserción, eliminación, impresión por niveles y validar sus propiedades
 */
public class ArbolB {

    /**
     * Clase interna estática NodoArbolB
     * Representa la estructura de un nodo dentro de un árbol B
     */
    private static class NodoArbolB {
        List<Integer> llaves; //Lista que almacena los valores numéricos de las llaves dentro de un nodo
        List<NodoArbolB> hijos; //Lista que almacena las referencias a objetos de tipo NodoArbolB que representan a los hijos de un nodo
        boolean esHoja; //Variable de tipo boolean que indica si un nodo es una hoja o es un nodo con hijos
        /**
         * Constructor por parámetros de la clase NodoArbolB
         * @param esHoja
         */
        public NodoArbolB(boolean esHoja) {
            this.esHoja = esHoja; //Asigna el estado de hoja al nodo recién inicializado
            this.llaves = new ArrayList<>(); //Asigna la lista de llaves vacía
            this.hijos = new ArrayList<>(); //Asigna la lista de hijos vacía
        }
    }
    
    private NodoArbolB raiz; //Objeto de tipo NodoArbolB que representa al nodo raíz del árbol
    /**
     * Constructor por omisión de la clase ArbolB
     */
    public ArbolB() {
        this.raiz = null; //Asigna un valor vacío a la raíz del árbol recién inicializado
    }
}