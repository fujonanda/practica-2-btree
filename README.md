Equipo: TQM Enoc
Integrantes:
- Correa Merino Fernanda
- Pérez Hernández Francisco Gadiel

Preguntas:

1. ¿Por qué al insertar una llave nueva no podemos decidir el hijo únicamente comparando con la primera llave del nodo?
Porque al ser un árbol de orden m = 4 cada nodo puede tener hasta tres llaves y un máximo de cuatro hijos, lo que significa que si la llave a insertar es mayor que la primera llave del nodo padre pueden llegar a haber hasta tres espacios posibles para insertarla ya que solo el primer hijo del nodo está pensado para almacenar llaves menores a la primera llave del padre. Es necesario comparar las otras dos llaves del nodo padre como criterio de desempate para determinar el intervalo correcto en el que se encuentra la llave a insertar y así evitar ambigüedades en su posicionamiento dentro del árbol, es por eso también que cada nodo hijo representa un intervalo determinado con relación a los valores de las llaves del nodo padre

2. ¿Por qué una búsqueda no debe recorrer todos los hijos de un nodo?
Porque así se perdería la eficiencia que se busca al implementar un árbol B ya que recorrer varias partes del árbol implica un mayor costo para la operación, además de ser innecesario porque las llaves dentro del árbol se distribuyen en intervalos determinados con una ruta fácilmente trazable

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

Detalles de la práctica

1. Lenguaje utilizado:
JAVA

2. Instrucciones para ejecutar el programa:
   - Copia el contenido del archivo ArbolB en un documento de texto y guárdalo con el nombre de ArbolB.java dentro de la carpeta que desees. En lugar de este 
     procedimiento se puede descargar el archivo con el código fuente directamente del repositorio y guardarlo en la carpeta de tu elección
   - Abre la terminal y realiza los comandos necesarios para dirigirte a la carpeta donde guardaste el archivo
   - Escribe el comando "javac ArbolB.java" (sin las comillas) en la terminal para generar un archivo ArbolB.class que servirá como ejecutable y que se guardará
     automáticamente en la misma ruta que el archivo ArbolB.java
   - Escribe el comando "java ArbolB" (sin las comillas) en la terminal para ejecutar el archivo de tipo class. Como el archivo ArbolB no contiene ningún método main
     no pasará nada más en la terminal y no se pondrá en acción el contenido del archivo, una opción es modificar el contenido del archivo ArbolB.java y añadir un
     bloque para el método main con instrucciones definidas para las operaciones que se decidan hacer para poner a prueba el programa, después hay que repetir el
     proceso de generar el archivo .class y ejecutarlo. En su defecto también se puede utilizar el archivo correspondiente a las pruebas adjunto en el repositorio

3. Instrucciones para ejecutar los casos de prueba:
   - Copia el contenido del archivo Pruebas en un documento de texto y guárdalo con el nombre Pruebas.java en la misma carpeta donde está guardado el archivo
     ArbolB.class. En lugar de copiar y pegar el contenido puedes optar por descargar el archivo fuente directo del repositorio y guardarlo en la misma carpeta donde
     esté el archivo ArbolB.class
   - Abre la terminal y realiza los comandos necesarios para entrar a la carpeta donde están guardados los archivos Prueba.java y ArbolB.class
   - Escribe el comando "javac Pruebas.java" (sin las comillas) en la terminal para generar el archivo ejecutable Pruebas.class dentro de la carpeta donde se
     encuentran los archivos ArbolB.class y Pruebas.java
   - Escribe el comando "java Pruebas" (adivinaste, sin las comillas) para ejecutar el archivo de tipo class y en la terminal aparecerán los resultados de las
     operaciones realizadas usando lo que se puede construir con la clase ArbolB a través de llamadas a métodos dentro de Pruebas
   - En caso de querer modificar, añadir o eliminar alguna prueba del funcionamiento de ArbolB se debe entrar al archivo Pruebas.java y escribir nuevos bloques de
     código en los que se incluyan los métodos deseados junto a los datos con los que el programa deberá trabajar, así como también reescribir bloques de código
     existentes con nuevos datos o simplemente borrarlos, dependiendo de las necesidades que se tengan. Una vez estando satisfecho con el contenido de las pruebas
     guarda los cambios del archivo y repite los pasos necesarios para generar el archivo ArbolB.class
     
4. Explicación breve de la representación de un nodo:
Cada nodo del árbol es un objeto de la clase NodoArbolB. Los nodos constan de tres atributos:
   - List<Integer> llaves: Una lista para guardar los valores de las llaves almacenadas en el nodo
   - List<NodoArbolB> hijos: Otra lista que guarda las referencias a los hijos del nodo
   - boolean esHoja: Una variable de tipo boolean cuyo valor es true si el nodo se trata de una hoja o false en el caso contrario

5. Explicación de qué significa m = 4 y por qué cada nodo admite máximo tres llaves:
La expresión m = 4 indica el orden del árbol, dicho orden define la cantidad máxima de hijos que puede tener cada nodo del árbol junto con la cantidad máxima y mínima de llaves que puede haber dentro de cada nodo. El límite de hijos por nodo es igual al orden del árbol, en este caso donde m = 4 tenemos que la cantidad máxima de hijos que puede tener un nodo es cuatro. La cantidad máxima de llaves por nodo se denota como r = m - 1, que en el caso de este árbol es r = 3 lo que limita al árbol a poder guardar máximo tres llaves por nodo

6. Explicación de cómo se decide qué hijo seguir durante una búsqueda:
Durante una búsqueda se toma la llave que se desea a buscar y se compara su valor con el de las llaves del nodo actual en el que se efectúa la búsqueda, si el valor es igual a alguna de las llaves la búsqueda termina pero si no lo es entonces se determina a qué intervalo pertenece con base en los valores de las llaves del nodo.
Los Árboles Binarios B tienen una lógica propia al momento de acomodar las llaves en los nodos del árbol que consiste en ordenar las llaves dentro de un mismo nodo de menor a mayor, es decir que la primera llave dentro de un nodo siempre será la menor y la última la mayor, y las llaves de los hijos de ese nodo representarán intervalos definidos por los valores de las llaves del padre. El primer hijo de un nodo, aquel que esté más hacia la izquierda, albergará las llaves con un valor menor al de la primera llave del padre; el siguiente hijo contendrá las llaves cuyos valores sean mayores a los de la primera llave del padre y menores a los de la segunda llave del padre; el hijo que le sigue tendrá dentro las llaves que tengan valores mayores a la segunda llave del padre y menores a la última llave del padre; el último hijo, el que se encuentre más hacia la derecha, almacenará las llaves que representen valores mayores al valor de la última llave del padre.
La función búsqueda considera esta lógica al momento de comparar el valor de la llave buscada con los valores de las llaves del nodo actual, entonces una vez que determina a cual intervalo pertenece dicha llave (menor al de las llaves del nodo, intermedio o mayor) decide a cuál de los hijos del nodo trasladarse para que sea seguro que está siguiendo un camino que lo llevará a la llave en lugar de buscar en algún lugar donde no pudo haberse guardado en primer lugar

7. Explicación de qué ocurre cuando un nodo alcanza cuatro llaves:
Ocurre algo llamado desbordamiento, es decir que el nodo alberga más llaves de las establecidas por el valor r del árbol. Como r = 3 y en el nodo hay cuatro llaves se debe comenzar un proceso en el que el nodo se divide (split) para dar origen a dos nodos hijos y a la promoción de la tercera llave del nodo desbordado hacia un nodo padre, el primer nodo hijo guarda dos llaves que son menores a la llave ascendida y el último nodo hijo almacena una llave que tenga un valor mayor al de la llave ascendida. Si el nodo al que llegó la llave ascendida termina albergando cuatro llaves el mismo proceso de split y promoción se repite con las llaves del nuevo nodo desbordado, dichas operaciones pueden propagarse hasta llegar a la raíz de ser necesario para que todos los nodos contengan una cantidad de llaves no mayor al límite definido por r

8. Explicación de la convención de promoción usada en la práctica:
Por convención, la llave que se toma de un nodo desbordado para ser ascendida es la llave central o mediana. En caso de haber dos llaves consideradas como centrales, que es lo que ocurre en los desbordamientos de esta práctica ya que suceden con la presencia de cuatro llaves en un mismo nodo, entonces se toma como mediana a la segunda llave del centro que en este caso sería la tercera llave del nodo

9. Explicación breve de redistribución y fusión:
Ambas operaciones se usan cuando se necesita reparar el árbol después de que se haya dado un caso de subocupación (o underflow) en un nodo después de implementar la función de eliminación. Un nodo subocupado es aquel que almacena menos llaves de las permitidas por el límite mínimo de llaves para cada nodo denotado como q = (m/2)-1, que en el caso de la práctica se define como q = 1.
La redistribución consiste en tomar una llave del nodo padre para pasarla al nodo hijo subocupado y hacer que se solucione el problema de subocupación. La llave tomada del nodo padre debe ser aquella que separa al nodo hijo subocupado de otro de sus nodos hijos, el nodo hijo distinto al nodo subocupado debe tener forzosamente más de una llave para poder tomar una de sus llaves y pasarla al padre como reemplazo de la llave que pasó a ser parte del nodo hijo subocupado
La fusión se usa cuando todos los nodos hermanos del nodo subocupado tienen solo una llave y no se puede llevar a cabo la redistribución, así que en su lugar uno de los nodos hermanos del nodo subocupado pasa a ser parte de este último al ser absorbida su única llave por el nodo subocupado, además de que el nodo padre le pasa una llave al nuevo nodo que sería la llave que separaba a los nodos que formaron parte de la fusión. Si se da el caso de que el nodo padre se encuentre en un estado de desocupación después de transferir la llave al nodo fusionado entonces se debe realizar una de las dos operaciones de reparación al nodo padre con ayuda de uno de sus hermanos y su propio nodo padre, la reparación de un árbol puede propagarse hasta la raíz
