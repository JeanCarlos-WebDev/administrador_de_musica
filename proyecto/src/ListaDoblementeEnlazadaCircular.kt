package proyecto

class ListaDoblementeEnlazadaCircular : Iterable<Cancion> {
    val head: NodoList = NodoList(null).apply {
        prev = this
        next = this
    }

    /**
     * Agrega un nuevo nodo al frente de la lista (después del nodo centinela).
     *
     * La operación se realiza insertando el nuevo nodo entre 'head' y 'head.next'.
     * @param key El valor entero a almacenar en el nuevo nodo.
     */
    fun agregarAlFrente(cancion: Cancion ){
       val newNode: NodoList = NodoList(cancion, head, head.next)
        head.next?.prev = newNode
        head.next = newNode
    }

    /**
     * Agrega un nuevo nodo al final de la lista (justo antes del nodo centinela).
     *
     * La operación se realiza insertando el nuevo nodo entre 'head.prev' y 'head'.
     * @param key El valor entero a almacenar en el nuevo nodo.
     */
    fun agregarAlFinal(cancion: Cancion ) {
        val newNode: NodoList = NodoList(cancion, head.prev, head)
        head.prev?.next = newNode
        head.prev = newNode
    }

    /**
     * Busca un nodo que contenga una clave específica.
     *
     * La búsqueda comienza en el primer nodo de datos (head.next) y termina
     * cuando se encuentra el valor o se regresa al nodo centinela.
     * @param key El valor entero a buscar.
     * @return El NodoList que contiene la clave, o null si no se encuentra.
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
     * Elimina un nodo específico de la lista.
     *
     * La operación de eliminación re-enlaza los nodos adyacentes para que se
     * salten el nodo a eliminar. La complejidad es O(1) asumiendo que el nodo
     * a eliminar ya se conoce.
     * @param nodo El nodo a eliminar (no debe ser el nodo centinela).
     */
    fun eliminar(nodo: NodoList){
        nodo.prev?.next = nodo.next
        nodo.next?.prev = nodo.prev

    }

    inner class ListaIterator : Iterator<Cancion> {
        var actual = head.next  // comienza en el primer nodo real

        override fun hasNext(): Boolean = actual != head

        override fun next(): Cancion {
            if (!hasNext()) throw NoSuchElementException()
            val valor = actual!!.key!!
            actual = actual!!.next
            return valor
        }
    }

    override fun iterator(): Iterator<Cancion> = ListaIterator()
}


