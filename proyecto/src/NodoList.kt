package proyecto
/**
 * Clase que representa un nodo en una Lista Doblemente Enlazada Circular.
 *
 * Descripción:
 * Cada nodo almacena una clave de datos ('key') y mantiene referencias a su predecesor
 * ('prev') y a su sucesor ('next'), esenciales para la navegación en la estructura de lista doble.
 *
 * @param key La clave de datos de tipo 'Cancion' almacenada en el nodo. Puede ser 'null' si es el nodo centinela.
 * @param prev Referencia mutable al nodo predecesor. Inicializado a 'this' si es null en el bloque 'init'.
 * @param next Referencia mutable al nodo sucesor. Inicializado a 'this' si es null en el bloque 'init'.
 *
 * Precondición:
 * - El objeto 'key' debe ser una instancia válida de 'Cancion' o 'null' para el centinela.
 * Postcondición:
 * - Se crea una nueva instancia de 'NodoList'.
 * - Si 'prev' o 'next' no se proporcionan, se inicializan para apuntar al propio nodo.
 */
class NodoList(val key: Cancion?, var prev: NodoList? = null, var next: NodoList? = null) {
    init {

        if (prev == null) prev = this
        if (next == null) next = this
    }
}
