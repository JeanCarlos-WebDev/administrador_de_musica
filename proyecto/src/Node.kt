package proyecto
/**
 * Clase que representa un nodo fundamental en una estructura de árbol binario de canciones.
 *
 * Descripción:
 * Cada nodo almacena un elemento de datos ('key') y mantiene las referencias necesarias
 * para estructurar el árbol: el nodo padre, y las ramas de los hijos izquierdo y derecho.
 *
 * @param key La clave de datos de tipo 'Cancion' almacenada en el nodo. Es inmutable (val).
 * @param parent Referencia opcional al nodo padre. Es mutable (var) y anulable (Node?).
 * @param rightChild Referencia opcional al nodo hijo derecho. Es mutable (var) y anulable (Node?).
 * @param leftChild Referencia opcional al nodo hijo izquierdo. Es mutable (var) y anulable (Node?).
 *
 * Precondición:
 * - k = Cancion .
 *
 * Postcondición:
 * 'parent' , 'rightChild', 'leftChild' se inicializan a 'null' por defecto
 */
class Node (val key: Cancion, var parent: Node? = null, var rightChild: Node? = null, var leftChild: Node? = null ){

}
