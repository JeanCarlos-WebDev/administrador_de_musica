package proyecto


/**
 * Implementación de un Tipo Abstracto de Dato (TAD) de una Lista Doblemente Enlazada Circular.
 *
 * Descripción:
 * Utiliza un nodo centinela ('head')
 * para estandarizar y simplificar las operaciones de inserción y eliminación en los extremos
 * (frente y final) de la lista, ya que siempre se opera sobre los mismos nodos. La lista es iterable.
 *
 * Parámetros: No aplica.
 *
 * Precondición: No aplica.
 *
 * Postcondición: (Después de ser instanciado)
 * - Se crea una instancia de la lista con un nodo centinela ('head').
 * - head.prev = head y head.next = head
 * - head.key = null
 */
class ListaDoblementeEnlazadaCircular : Iterable<Cancion> {
    val head: NodoList = NodoList(null).apply {
        prev = this
        next = this
    }

    /**
     * Descripción: Agrega un nuevo nodo con la canción proporcionada al frente de la lista.
     *
     * La operación inserta el nuevo nodo después del nodo centinela ('head') y *antes* del primer nodo de datos.
     *
     * @param cancion El objeto 'Cancion' a almacenar en el nuevo nodo.
     *
     * Precondición:
     * - cancion != null .
     *
     * Postcondición:
     * - head.next = Nodo(cancion)
     * - Nodo(cancion).prev = head
     */
    fun agregarAlFrente(cancion: Cancion ){
       val newNode: NodoList = NodoList(cancion, head, head.next)
        head.next?.prev = newNode
        head.next = newNode
    }

    /**
     * Descripción: Agrega un nuevo nodo con la canción proporcionada al final de la lista.
     *
     * La operación inserta el nuevo nodo antes del nodo centinela ('head') y después del último nodo de datos.
     *
     * @param cancion El objeto 'Cancion' a almacenar en el nuevo nodo.
     *
     *
     * Precondición:
     * - cancion != null
     *
     * Postcondición:
     * - head.next = Nodo(cancion)
     * - Nodo(cancion).prev = head
     */
    fun agregarAlFinal(cancion: Cancion ) {
        val newNode: NodoList = NodoList(cancion, head.prev, head)
        head.prev?.next = newNode
        head.prev = newNode
    }

    /**
     * Descripción: Busca el primer nodo que contenga una canción específica.
     *
     * La búsqueda es una iteración que comienza en el primer nodo de datos (head.next)
     * y continúa hasta encontrar el valor o hasta que se alcanza el nodo centinela ('head').
     *
     * @param cancion La 'Cancion' (clave) a buscar dentro de los nodos de la lista.
     * @return El 'NodoList' que contiene la clave, o 'null' si la canción no se encuentra.
     *
     * Precondición:
     * - cancion != null.
     *
     * Postcondición: @return = null || @return = cancion
     */
    fun buscar(cancion: Cancion): NodoList? {
        var x = head.next
        while( x != null && x != head ){
            if(x.key == cancion){
                return x
            }
            x = x.next
        }

        return null
    }

    /**
     * Descripción: Elimina un nodo específico de la lista.
     *
     * La operación de eliminación re-enlaza los nodos adyacentes para que se salten
     * el nodo a eliminar.
     *
     * @param nodo El 'NodoList' a eliminar (debe ser un nodo de datos, no el nodo centinela 'head').
     *
     * Precondición: nodo debe pertenecer a la lista
     *
     * Postcondición:
     * - Buscar(nodo) = null
     *
     */
    fun eliminar(nodo: NodoList){
        nodo.prev?.next = nodo.next
        nodo.next?.prev = nodo.prev

    }

    /**
     * Clase interna que implementa la interfaz 'Iterator<Cancion>'.
     *
     * Descripción:
     * Permite recorrer la secuencia de las canciones almacenadas en la lista,
     * comenzando en el primer nodo de datos (head.next) y terminando justo antes del nodo centinela.
     * La clase es interna ('inner') para tener acceso a la propiedad 'head' de la clase contenedora.
     *
     * Parámetros: No hay.
     *
     * Precondición: true.
     *
     * Postcondición:
     * @return Iterator<Cancion>
     */
    inner class ListaIterator : Iterator<Cancion> {
        var actual = head.next  // comienza en el primer nodo real

        /**
         * Descripción: Verifica si queda un siguiente elemento de datos (Cancion) por iterar.
         *
         * Parámetros: No hay.
         *
         * @return true si el nodo actual no es el nodo centinela, lo que indica
         * que hay datos pendientes de leer; false en caso contrario.
         *
         * Precondición: true.
         *
         * Postcondición: true.
         */
        override fun hasNext(): Boolean = actual != head

        /**
         * Descripción: Devuelve el valor ('Cancion') del nodo actual y avanza el iterador al siguiente nodo.
         *
         * Parámetros: No hay.
         *
         * @return El valor 'Cancion' almacenado en el nodo que se acaba de visitar.
         * @throws NoSuchElementException Si se llama cuando 'hasNext()' es 'false' (se ha llegado al final de la lista).
         *
         * Precondición:
         * - true
         *
         * Postcondición:
         * - actual = actual.next
         */
        override fun next(): Cancion {
            if (!hasNext()) throw NoSuchElementException()
            val valor = actual!!.key!!
            actual = actual!!.next
            return valor
        }
    }

    /**
     * Descripción: Implementa el contrato 'Iterable<Cancion>' devolviendo una nueva instancia del iterador.
     *
     * Parámetros: No hay.
     *
     * @return Una instancia de 'ListaIterator' para permitir el recorrido de la lista.
     *
     * Precondición: true.
     *
     * Postcondición: true.
     */
    override fun iterator(): Iterator<Cancion> = ListaIterator()
}


