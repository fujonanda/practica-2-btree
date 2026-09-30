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

    /**
     * Clase interna estática ResultadoSplit
     * Representa el resultado de la división o split de un nodo
     */
    private static class ResultadoSplit {
        int mediana; //Variable de tipo int que representa el valor de la llave considerada como mediana
        NodoArbolB hijoIzquierdo; //Objeto de tipo NodoArbolB que representa al nuevo nodo izquierdo formado después de la división del nodo original
        NodoArbolB hijoDerecho; //Objeto de tipo NodoArbolB que representa al nuevo nodo derecho formado después de la división del nodo original
        /**
         * Constructor por parámetros de la clase ResultadoSplit
         * @param mediana
         * @param hijoIzquierdo
         * @param hijoDerecho
         */
        public ResultadoSplit(int mediana, NodoArbolB hijoIzquierdo, NodoArbolB hijoDerecho) {
            this.mediana = mediana;
            this.hijoIzquierdo = hijoIzquierdo;
            this.hijoDerecho = hijoDerecho;
        }
    }
    
    private NodoArbolB raiz; //Objeto de tipo NodoArbolB que representa al nodo raíz del árbol
    /**
     * Constructor por omisión de la clase ArbolB
     */
    public ArbolB() {
        this.raiz = null; //Asigna un valor vacío a la raíz del árbol recién inicializado
    }

    /**
     * Método buscar
     * Realiza una operación de búsqueda recorriendo nodos a lo largo de la estructura del árbol hasta encontrar la llave especificada en el parámetro e imprime si se encontró o no
     * @param x: Variable de tipo int que representa la llave a encontrar
     */
    public void buscar(int x) {
        //Define el caso si se implementa la búsqueda en un árbol vacío
        if (raiz == null) {
            System.out.println("Llave " + x + " -> NOT_FOUND"); //Imprime un mensaje que indica que la llave no fue encontrada
            return;
        }
        NodoArbolB actual = raiz; //Objeto de tipo NodoArbolB que indica el nodo en el que se está buscando a la llave, inicia en la raíz
        while (actual != null) { //Da la instrucción de continuar la búsqueda mientras existan nodos no vacíos donde buscar
            int i = 0; //Variable de tipo int que representa el índice de comparación de llaves
            while (i < actual.llaves.size() && x > actual.llaves.get(i)) { //Da la instrucción de avanzar entre las llaves de un nodo mientras la llaves buscada sea mayor a la llave en la que nos encontramos
                i++; //Aumenta el índice dentro del nodo actual
            }
            //Define el caso en el que se encuentre la llave dentro del nodo
            if (i < actual.llaves.size() && actual.llaves.get(i) == x) {
                System.out.println("Llave " + x + " -> FOUND"); //Imprime el mensaje que indica que la llave fue encontrada
                return; //Detiene la búsqueda
            }
            //Define el caso en el que se llega a una hoja y la llave aún no se ha encontrado
            if (actual.esHoja) {
                System.out.println("Llave " + x + " -> NOT_FOUND"); //Imprime el mensaje que indica que la llave no fue encontrada
                return; //Detiene la búsqueda
            }
            actual = actual.hijos.get(i); //Cambia el nodo en el que se efectúa la búsqueda por uno cuyas llaves coincidan con el rango de la llave a encontrar
        }
        System.out.println("Llave " + x + " -> NOT_FOUND"); //Indica que la llave no se encontró al terminar el ciclo
        return; //Termina la ejecución del método
    }

    /**
     * Método insertar
     * Inserta una llave nueva en la raíz del árbol y responde a promociones y splits en caso de ser necesario
     * @param x: Variable de tipo int que representa la llave a insertar
     */
    public void insertar(int x) {
        //Define el caso en el que el árbol esté vacío
        if (raiz == null) {
            raiz = new NodoArbolB(true); //Crea un objeto de tipo NodoArbolB para ser la raíz sin hijos del árbol
            raiz.llaves.add(x); //Añade la llave insertada como único elemento de la lista de llaves de la raíz
            return; //Termina la ejecución del método
        }
        ResultadoSplit split = insertarAuxiliar(raiz, x); //Crea un objeto de tipo ResultadoSplit y manda a llamar un método auxiliar para encargarse de un split en un caso necesario
        //Define el caso en el que el split haya ocurrido a nivel de la raíz
        if (split != null) {
            NodoArbolB nuevaRaiz = new NodoArbolB(false); //Crea un objeto de tipo NodoArbolB que representa un nuevo nodo raíz
            nuevaRaiz.llaves.add(split.mediana); //Inserta la mediana de la raíz anterior como único elemento de a lista de llaves de la nueva raíz
            nuevaRaiz.hijos.add(split.hijoIzquierdo); //Añade al hijo izquierdo formado por el split a la lista de hijos de la nueva raíz
            nuevaRaiz.hijos.add(split.hijoDerecho); //Añade al hijo derecho formado por el split a la lista de hijos de la nueva raíz
            raiz = nuevaRaiz; //Actualiza la referencia de la raíz del árbol para que corresponda con la nueva raíz
        }
        System.out.println("Llave " + x + " -> Insertada"); //Imprime un mensaje que indica la nueva llave que se insertó
    }

    /**
     * Método auxiliar insertarAuxiliar
     * Inserta llaves en nodos distintos a la raíz
     * @param nodo: objeto de tipo NodoArbol que representa el nodo en el que se evalúa la inserción
     * @param x: Variable de tipo int que representa la variable a insertar
     * @return Objeto de tipo ResultadoSplit si ocurre un desbordamiento en el nodo o null si no debe haber split
     */
    private ResultadoSplit insertarAuxiliar(NodoArbolB nodo, int x) {
        //Define el caso en el que el nodo considerado para la inserción es una hoja
        if (nodo.esHoja) {
            //Define el caso en el que la llave ya existe en el nodo
            if (nodo.llaves.contains(x)){
                return null; //No hace la inserción ni indica que haya splits para que no haya llaves duplicadas
            }
            int pos = 0; //Variable de tipo int que representa la posición donde se insertará la llave
            while (pos < nodo.llaves.size() && nodo.llaves.get(pos) < x) {
                pos++; // Busca la posición correcta según orden ascendente
            }
            nodo.llaves.add(pos, x); //Inserta la llave en la posición calculada
            //Define el caso en el que el nodo alcanza 4 llaves
            if (nodo.llaves.size() == 4) {
                return dividir(nodo); //Ordena la ejecución de la división del nodo
            }
            return null; //Indica que el nodo no se desbordó
        //Define el caso en el que el nodo sea interno
        } else {
            if (nodo.llaves.contains(x)) {
                return null; //Evita inserción si la llave está presente en el nodo interno
            }
            int i = 0; //Inicializa índice para seleccionar el hijo adecuado
            while (i < nodo.llaves.size() && x > nodo.llaves.get(i)) { //Encuentra la rama correspondiente para descender
                i++;
            }
            ResultadoSplit splitHijo = insertarAuxiliar(nodo.hijos.get(i), x); //Llama recursivamente sobre el hijo
            //Dedine el caso en el que el hijo requirió división tras la inserción
            if (splitHijo != null) {
                nodo.llaves.add(i, splitHijo.mediana); //Inserta la mediana devuelta en las llaves del nodo actual
                nodo.hijos.set(i, splitHijo.hijoIzquierdo); //Reemplaza la referencia al hijo por el nodo izquierdo
                nodo.hijos.add(i + 1, splitHijo.hijoDerecho); //Agrega la referencia al nuevo nodo derecho
                if (nodo.llaves.size() == 4) return dividir(nodo); // Divide el nodo si alcanza 4 llaves
            }
            return null;
        }
    }

    /**
     * Método dividir
     * Divide un nodo que ha alcanzado el límite de 4 llaves en dos nodos hermanos y promueve su mediana
     * @param nodo Nodo desbordado a dividir
     * @return Objeto ResultadoSplit con la mediana y los dos nuevos subárboles
     */
    private ResultadoSplit dividir(NodoArbolB nodo) {
        int mediana = nodo.llaves.get(2); //Obtiene el elemento mediano ubicado en el índice 2
        NodoArbolB izquierda = new NodoArbolB(nodo.esHoja); //Crea el nuevo nodo para la mitad izquierda
        NodoArbolB derecha = new NodoArbolB(nodo.esHoja); //Crea el nuevo nodo para la mitad derecha
        izquierda.llaves.addAll(nodo.llaves.subList(0, 2)); //Asigna las primeras dos llaves al nodo izquierdo
        derecha.llaves.addAll(nodo.llaves.subList(3, 4)); //Asigna la última llave al nodo derecho
        //Define el caso en el que si el nodo a dividir no es una hoja entonces redistribuye los hijos
        if (!nodo.esHoja) {
            izquierda.hijos.addAll(nodo.hijos.subList(0, 3)); //Copia los primeros 3 hijos al nodo izquierdo
            derecha.hijos.addAll(nodo.hijos.subList(3, 5)); //Copia los 2 hijos restantes al nodo derecho
        }
        return new ResultadoSplit(mediana, izquierda, derecha); // Retorna los datos de la división
    }

    /**
     * Método eliminar
     * Elimina una llave del Árbol B y ajusta la raíz si queda vacía
     * @param x Llave a eliminar del árbol
     */
    public void eliminar(int x) {
        // Define el caso en el que árbol está vacío
        if (raiz == null) {
            System.out.println("El árbol está vacío"); //Imprime el mensaje que indica que el árbol está vacío
            return; //Termina el método sin eliminaciones
        }
        boolean encontrado = eliminarAuxiliar(raiz, x); //Ejecuta el procedimiento de eliminación recursiva
        // Define el caso en el que la llave no fue localizada durante el recorrido
        if (!encontrado) {
            System.out.println("Llave " + x + " -> NOT_FOUND y no puede eliminarse"); //Mensaje que indica que la llave no se encontró y no se pudo eliminar
            return; //Termina el método sin eliminaciones
        }
        //Define el caso en el que la llave termina vacía tras la eliminación
        if (raiz.llaves.isEmpty()) {
            //Define el caso en el que la raíz tiene al menos un hijo no vacío
            if (!raiz.esHoja && !raiz.hijos.isEmpty()) {
                raiz = raiz.hijos.get(0); //Promueve al hijo como la raíz
            //Define el caso en el que la raíz no tenga hijos
            } else {
                raiz = null; //El árbol pasa a estar vacío
            }
        }
        System.out.println("Llave " + x + " -> Eliminada"); // Imprime un mensaje que indica la llave eliminada
    }
}