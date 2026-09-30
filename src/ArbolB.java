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

    /**
     * Método auxiliar recursivo para localizar y eliminar una llave en un subárbol.
     * @param nodo Nodo actual en proceso de eliminación.
     * @param x Llave numérica a eliminar.
     * @return Verdadero si la llave fue encontrada y eliminada; falso en caso contrario.
     */
    private boolean eliminarAuxiliar(NodoArbolB nodo, int x) {
        int indice = 0; // Inicializa índice para buscar la posición de la llave.
        while (indice < nodo.llaves.size() && x > nodo.llaves.get(indice)) { // Busca la posición correspondiente.
            indice++; // Incrementa posición dentro de la lista de llaves.
        }
        if (indice < nodo.llaves.size() && nodo.llaves.get(indice) == x) { // Si la llave está presente en el nodo actual.
            if (nodo.esHoja) { // Si el nodo es una hoja.
                nodo.llaves.remove(indice); // Elimina directamente la llave de la hoja.
                return true; // Retorna verdadero indicando éxito.
            } else { // Si la llave está en un nodo interno.
                NodoArbolB hijoIzq = nodo.hijos.get(indice); // Obtiene el subárbol izquierdo adyacente.
                NodoArbolB hijoDer = nodo.hijos.get(indice + 1); // Obtiene el subárbol derecho adyacente.
                if (hijoIzq.llaves.size() > 1) { // Si el hijo izquierdo tiene más del mínimo de llaves.
                    NodoArbolB actual = hijoIzq; // Inicia navegación para buscar el predecesor.
                    while (!actual.esHoja) { // Desciende hasta llegar a la hoja más a la derecha.
                        actual = actual.hijos.get(actual.hijos.size() - 1); // Avanza al último hijo.
                    }
                    int predecesor = actual.llaves.get(actual.llaves.size() - 1); // Obtiene la mayor llave del subárbol izquierdo.
                    nodo.llaves.set(indice, predecesor); // Reemplaza la llave eliminada por su predecesor.
                    eliminarAuxiliar(hijoIzq, predecesor); // Elimina recursivamente el predecesor de su ubicación original.
                    repararSubocupacion(nodo, indice); // Restaura el balance del subárbol si ocurrió subocupación.
                }
                else if (hijoDer.llaves.size() > 1) { // Si el hijo derecho tiene más del mínimo de llaves.
                    NodoArbolB actual = hijoDer; // Inicia navegación para buscar el sucesor.
                    while (!actual.esHoja) { // Desciende hasta llegar a la hoja más a la izquierda.
                        actual = actual.hijos.get(0); // Avanza al primer hijo.
                    }
                    int sucesor = actual.llaves.get(0); // Obtiene la menor llave del subárbol derecho.
                    nodo.llaves.set(indice, sucesor); // Reemplaza la llave eliminada por su sucesor.
                    eliminarAuxiliar(hijoDer, sucesor); // Elimina recursivamente el sucesor de su ubicación original.
                    repararSubocupacion(nodo, indice + 1); // Restaura el balance del subárbol tras la eliminación.
                }
                else { // Si ambos hijos tienen solo una llave.
                    hijoIzq.llaves.add(nodo.llaves.remove(indice)); // Mueve la llave del nodo padre al hijo izquierdo.
                    hijoIzq.llaves.addAll(hijoDer.llaves); // Une todas las llaves del hijo derecho en el izquierdo.
                    if (!hijoIzq.esHoja) { // Si no son hojas, combina también las listas de hijos.
                        hijoIzq.hijos.addAll(hijoDer.hijos); // Copia las referencias de los hijos del lado derecho.
                    }
                    nodo.hijos.remove(indice + 1); // Elimina el hijo derecho que fue fusionado.
                    eliminarAuxiliar(hijoIzq, x); // Elimina recursivamente la llave desde el nodo fusionado.
                }
                return true; // Retorna verdadero al completar la eliminación en nodo interno.
            }
        }
        if (nodo.esHoja) { // Si es una hoja y la llave no fue encontrada.
            return false; // Retorna falso indicando ausencia del elemento.
        }
        boolean encontrado = eliminarAuxiliar(nodo.hijos.get(indice), x); // Busca recursivamente en el hijo correspondiente.
        if (encontrado) { // Si la llave fue encontrada y eliminada en niveles inferiores.
            repararSubocupacion(nodo, indice); // Repara posibles violaciones de subocupación en los hijos.
        }
        return encontrado; // Retorna el resultado del proceso de eliminación.
    }

    /**
     * Repara la subocupación en un nodo hijo mediante redistribución de llaves o fusión con un hermano.
     * @param padre Nodo padre del subárbol afectado.
     * @param indiceHijo Índice del hijo que presenta déficit de llaves.
     */
    private void repararSubocupacion(NodoArbolB padre, int indiceHijo) {
        NodoArbolB hijo = padre.hijos.get(indiceHijo); // Obtiene la referencia al nodo hijo bajo revisión.
        if (hijo.llaves.size() >= 1) { // Verifica si el hijo cumple con la cantidad mínima de llaves.
            return; // No requiere reparación.
        }
        if (indiceHijo > 0) { // Evalúa si existe un hermano izquierdo.
            NodoArbolB hermanoIzq = padre.hijos.get(indiceHijo - 1); // Obtiene la referencia al hermano izquierdo.
            if (hermanoIzq.llaves.size() > 1) { // Si el hermano izquierdo puede prestar una llave.
                hijo.llaves.add(0, padre.llaves.get(indiceHijo - 1)); // Baja la llave del padre al hijo en primera posición.
                padre.llaves.set(indiceHijo - 1, hermanoIzq.llaves.remove(hermanoIzq.llaves.size() - 1)); // Sube la mayor llave del hermano al padre.
                if (!hermanoIzq.esHoja) { // Si no son hojas, transfiere la referencia del hijo extremo.
                    hijo.hijos.add(0, hermanoIzq.hijos.remove(hermanoIzq.hijos.size() - 1)); // Reubica el último hijo del hermano al inicio del hijo.
                }
                return; // Finaliza tras la redistribución exitosa.
            }
        }
        if (indiceHijo < padre.hijos.size() - 1) { // Evalúa si existe un hermano derecho.
            NodoArbolB hermanoDer = padre.hijos.get(indiceHijo + 1); // Obtiene la referencia al hermano derecho.
            if (hermanoDer.llaves.size() > 1) { // Si el hermano derecho puede prestar una llave.
                hijo.llaves.add(padre.llaves.get(indiceHijo)); // Baja la llave delimitadora del padre al hijo.
                padre.llaves.set(indiceHijo, hermanoDer.llaves.remove(0)); // Sube la menor llave del hermano al padre.
                if (!hermanoDer.esHoja) { // Transfiere referencias de hijos si no es hoja.
                    hijo.hijos.add(hermanoDer.hijos.remove(0)); // Reubica el primer hijo del hermano al final del hijo.
                }
                return; // Finaliza tras la redistribución.
            }
        }
        if (indiceHijo > 0) { // Si no fue posible la redistribución, efectúa fusión con el hermano izquierdo.
            NodoArbolB hermanoIzq = padre.hijos.get(indiceHijo - 1); // Obtiene el hermano izquierdo.
            hermanoIzq.llaves.add(padre.llaves.remove(indiceHijo - 1)); // Remueve la llave del padre y la agrega al hermano izquierdo.
            hermanoIzq.llaves.addAll(hijo.llaves); // Une las llaves restantes del hijo en el hermano izquierdo.
            if (!hijo.esHoja) { // Si no son hojas, une sus subárboles.
                hermanoIzq.hijos.addAll(hijo.hijos); // Transfiere todas las referencias de hijos.
            }
            padre.hijos.remove(indiceHijo); // Desvincula el nodo hijo vacío de la lista del padre.
        } else { // Efectúa fusión con el hermano derecho si no hay hermano izquierdo.
            NodoArbolB hermanoDer = padre.hijos.get(indiceHijo + 1); // Obtiene el hermano derecho.
            hijo.llaves.add(padre.llaves.remove(indiceHijo)); // Remueve la llave del padre y la transfiere al hijo.
            hijo.llaves.addAll(hermanoDer.llaves); // Junta las llaves del hermano derecho en el hijo.
            if (!hermanoDer.esHoja) { // Transfiere referencias de hijos si aplica.
                hijo.hijos.addAll(hermanoDer.hijos); // Copia los hijos del hermano derecho al hijo.
            }
            padre.hijos.remove(indiceHijo + 1); // Desvincula la referencia del hermano derecho.
        }
    }

    /**
     * Realiza e imprime un recorrido por niveles del Árbol B formateando los nodos.
     */
    public void imprimirPorNiveles() {
        if (raiz == null) { // Verifica si el árbol se encuentra vacío.
            System.out.println("Nivel 0 -> null"); // Imprime indicación de árbol nulo.
            return; // Finaliza la ejecución.
        }
        Queue<NodoArbolB> cola = new LinkedList<>(); // Instancia una cola para el recorrido en anchura (BFS) por niveles.
        cola.add(raiz); // Agrega la raíz como elemento inicial en la cola.
        int nivel = 0; // Inicializa el contador del nivel actual.
        System.out.println("\n-------------- Árbol B actual ---------------"); // Imprime cabecera decorativa del árbol.
        while (!cola.isEmpty()) { // Procesa cada nivel mientras existan nodos en la cola.
            int nodosEnNivel = cola.size(); // Obtiene el número total de nodos presentes en el nivel actual.
            System.out.print("Nivel " + nivel + ": "); // Imprime la etiqueta del nivel.
            for (int i = 0; i < nodosEnNivel; i++) { // Recorre todos los nodos pertenecientes al nivel actual.
                NodoArbolB actual = cola.poll(); // Extrae el siguiente nodo de la cola.
                StringBuilder repNodo = new StringBuilder("["); // Construye la representación de corchetes.
                for (int j = 0; j < actual.llaves.size(); j++) { // Recorre las llaves contenidas dentro del nodo.
                    repNodo.append(actual.llaves.get(j)); // Añade la llave al formateador textual.
                    if (j < actual.llaves.size() - 1) { // Comprueba si existen más llaves en el nodo para añadir separador.
                        repNodo.append(" | "); // Agrega la barra separadora entre llaves.
                    }
                }
                repNodo.append("]"); // Cierra los corchetes del nodo actual.
                System.out.print(repNodo.toString() + " "); // Imprime el bloque del nodo con espacio.
                if (!actual.esHoja) { // Si el nodo procesado posee hijos.
                    cola.addAll(actual.hijos); // Agrega todos sus hijos a la cola para el siguiente nivel.
                }
            }
            System.out.println(); // Realiza un salto de línea tras terminar el nivel.
            nivel++; // Incrementa el contador de nivel.
        }
        System.out.println("----------------------------------------------"); // Imprime el separador de cierre.
    }

    /**
     * Valida que el Árbol B cumpla rigurosamente con todas sus propiedades e invariantes.
     * @return Verdadero si la estructura es un Árbol B válido; falso en caso contrario.
     */
    public boolean validarArbol() {
        if (raiz == null) { // Verifica si el árbol está vacío.
            System.out.println("Árbol B -> Vacío pero válido"); // Informa validez de árbol vacío.
            return true; // Un árbol vacío es considerado válido.
        }
        int[] nivelHojas = {-1}; // Arreglo mutable de 1 posición para registrar el nivel uniforme de las hojas.
        boolean esValido = validarNodoAuxiliar(raiz, 0, nivelHojas, true); // Inicia validación recursiva desde la raíz.
        if (esValido) { // Evalúa el resultado final de la validación.
            System.out.println("Árbol B -> Válido, cumple con los límites de m = 4, r = 3, q = 1 la cantidad exacta de hijos por cada nodo, llaves ordenadas en cada nodo y todas las hojas al mismo nivel"); // Notifica éxito en la comprobación.
        } else { // Si se halló alguna incongruencia estructural.
            System.out.println("Árbol B -> Inválido, hubo un incumplimiento en las reglas de los árboles B dentro del árbol construido"); // Notifica fallo en las reglas.
        }
        return esValido; // Retorna el estado global de validez.
    }
}