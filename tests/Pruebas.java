public class Pruebas{
    public static void main(String[] args){
        ArbolB arbol = new ArbolB();
        System.out.println();
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(10);
        arbol.insertar(30);
        arbol.insertar(50);
        arbol.insertar(60);
        arbol.insertar(70);
        arbol.insertar(5);
        arbol.insertar(15);
        arbol.insertar(25);
        arbol.insertar(35);
        arbol.insertar(45);
        arbol.imprimirPorNiveles();
        System.out.println();
        arbol.buscar(35);
        arbol.buscar(99);
        System.out.println();
        arbol.eliminar(25);
        arbol.eliminar(10);
        arbol.eliminar(70);
        arbol.eliminar(5);
        arbol.imprimirPorNiveles();
        System.out.println();
        arbol.buscar(25);
        arbol.buscar(35);
        System.out.println();
        arbol.validarArbol();
    }
}