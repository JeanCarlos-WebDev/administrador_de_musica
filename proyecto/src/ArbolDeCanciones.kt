package proyecto

/**
 * Clase que gestiona la colección de canciones utilizando un Árbol Binario de Búsqueda (BST).
 *
 * Descripción:
 * Esta clase encapsula la lógica de organización jerárquica de las canciones. Permite insertar,
 * buscar y convertir la estructura en una secuencia lineal. Implementa la lógica de ordenamiento
 * basada primero en el intérprete y luego en el título.
 *
 *
 * @param head: Referencia a la raíz del árbol. Inicialmente es null.
 */
class ArbolDeCanciones {
    var head: Node? = null

    /**
     * Descripción: Inserta una nueva canción en el árbol manteniendo la propiedad de orden del BST.
     *
     * Navega desde la raíz hasta encontrar la posición correcta (hoja) para la nueva canción.
     * Si el árbol está vacío, crea la raíz.
     *
     * @param nuevaCancion La instancia de 'Cancion' a insertar.
     * @throws IllegalStateException Si la canción ya existe en el árbol (mismo título e intérprete).
     *
     * Precondición:
     * - nuevaCancion debe ser una instancia válida y nuevaCancion != null.
     *
     * Postcondición:
     * - El árbol contiene un nodo más.
     */
    fun anadirCanciones(nuevaCancion: Cancion){
            if(head == null){
                head = Node(nuevaCancion)
                return
            }
        var actual: Node? = head!!
        var prev = actual
        while (actual != null){
            if(actual.key.obtenerInterprete() < nuevaCancion.obtenerInterprete()){
                prev = actual
                actual = actual.rightChild
            }
            else if(actual.key.obtenerInterprete() > nuevaCancion.obtenerInterprete()){
                prev = actual
                actual = actual.leftChild
            }
            // actual.key.obtenerInterprete() == nuevaCancion.obtenerInterprete()
            else{
                if(actual.key.obtenerTitulo() < nuevaCancion.obtenerTitulo()){
                    prev = actual
                    actual = actual.rightChild
                }
                else if(actual.key.obtenerTitulo() > nuevaCancion.obtenerTitulo()){
                    prev = actual
                    actual = actual.leftChild
                }
                // actual.key.obtenerTitulo() == nuevaCancion.obtenerTitulo()
                else {
                    //Lanzar Error
                    throw IllegalStateException("La canción ya existe en el árbol.")
                }
            }
        }
        // Insertamos
        val nuevoNodo = Node(nuevaCancion, prev)

        if (CompararCanciones(nuevaCancion, prev?.key!!)) {
            prev?.leftChild = nuevoNodo
        } else {
            prev?.rightChild = nuevoNodo
        }

    }

    /**
     * Descripción: Función auxiliar para comparar dos canciones según el criterio de ordenamiento dado.
     *
     *
     * @param cancion1 La primera canción a comparar .
     * @param cancion2 La segunda canción a comparar.
     * @return 'true' si cancion1 < cancion2, 'false' en caso contrario.
     *
     * Precondición: cancion1 != null && cancion2 != null .
     * Postcondición:
     */
    fun CompararCanciones(cancion1: Cancion, cancion2: Cancion): Boolean{
        if(cancion1.obtenerInterprete() < cancion2.obtenerInterprete()){
           return true
        }
        else if(cancion1.obtenerInterprete() > cancion2.obtenerInterprete()){
            return false
        }
        // actual.key.obtenerInterprete() == nuevaCancion.obtenerInterprete()
        else{
            if(cancion1.obtenerTitulo() < cancion2.obtenerTitulo()){
               return true
            }
            else if(cancion1.obtenerTitulo() > cancion2.obtenerTitulo()){
                return false
            }
            else { //Esto no debería pasar
                return false
            }
        }
    }
    /**
     * Descripción: Convierte el árbol en una Lista Doblemente Enlazada Circular ordenada.
     *
     * Utiliza un recorrido In-Orden para visitar los nodos de menor a mayor y agregarlos a la lista.
     *
     * Parámetros: No hay.
     * @return Una instancia de 'ListaDoblementeEnlazadaCircular' con todas las canciones ordenadas.
     *
     * Precondición: true.
     * Postcondición: Retorna una lista nueva, el árbol permanece intacto.
     */
    fun deArbolASecuencia(): ListaDoblementeEnlazadaCircular{
        val listaResultado = ListaDoblementeEnlazadaCircular()


        recorridoEnOrden(head, listaResultado)

        return listaResultado
    }

    /**
     * Descripción: Realiza un recorrido recursivo In-Orden.
     *
     * @param node El nodo actual que se está visitando.
     * @param lista La lista donde se irán agregando las canciones.
     *
     * Precondición: true.
     * Postcondición: El árbol no cambia.
     */
    fun recorridoEnOrden(node: Node?, lista: ListaDoblementeEnlazadaCircular) {
        if (node == null) {
            return
        }

        recorridoEnOrden(node.leftChild, lista)


        lista.agregarAlFinal(node.key)

        recorridoEnOrden(node.rightChild, lista)
    }


    /**
     * Descripción: Verifica recursivamente si el árbol cumple con la Invariante de Representación (Rep OK) de un BST.
     *
     * Asegura que para todo nodo, los valores en el subárbol izquierdo sean menores y en el derecho sean mayores,
     * respetando los límites (minCancion, maxCancion) heredados de los ancestros.
     *
     * @param nodo El nodo actual a verificar.
     * @param minCancion El límite inferior estricto para las claves en este subárbol (null si no hay límite).
     * @param maxCancion El límite superior estricto para las claves en este subárbol (null si no hay límite).
     * @return 'true' si el subárbol es válido, 'false' si se viola la propiedad de BST.
     *
     *Precondición: nodo, minCancion y maxCancion deben ser argumentos válidos
     *Postcondición: El árbol no es modificado
     *
     */
    fun esArbolDeBusqCancionR(nodo: Node?, minCancion: Cancion?, maxCancion: Cancion?): Boolean {
        if (nodo == null) return true

        val cancion = nodo.key!!

        if (minCancion != null && CompararCanciones(cancion, minCancion)) return false
        if (maxCancion != null && CompararCanciones(maxCancion, cancion)) return false


        val izquierdaValida = esArbolDeBusqCancionR(nodo.leftChild, minCancion, cancion)


        val derechaValida = esArbolDeBusqCancionR(nodo.rightChild, cancion, maxCancion)

        return izquierdaValida && derechaValida
    }

    /**
     * Descripción: Función pública para iniciar la verificación de la invariante del árbol.
     *
     * @return 'true' si todo el árbol es árbol binario de búsqueda válido.
     * Precondición: true
     * Postcondición: 
     */
    fun esArbolDeBusqCancion(): Boolean {
        return esArbolDeBusqCancionR(head, null, null)
    }


    // busca un nodo exacto en el árbol; retorna null si no existe
    fun buscar(i: String, t: String): Node?{
        var actual: Node? = head
        while (actual != null){
            if(actual.key.obtenerInterprete() < i){
                actual = actual.rightChild
            }
            else if(actual.key.obtenerInterprete() > i){
                actual = actual.leftChild
            }
            // actual.key.obtenerInterprete() == i
            else{
                if(actual.key.obtenerTitulo() < t){
                    actual = actual.rightChild
                }
                else if(actual.key.obtenerTitulo() > t){
                    actual = actual.leftChild
                }
                // actual.key.obtenerTitulo() == t
                else {
                    return actual
                }
            }
        }
        return null
        }


    fun minimo(x: Node): Node?{
        var actual: Node? = x
        while (actual?.leftChild != null){
            actual = actual.leftChild
        } 
        return actual
    }

    fun sucesor(x: Node?): Node?{
        if (x?.rightChild != null){
            return minimo(x?.rightChild!!)
        }
        var y: Node? = x?.parent

        var actual: Node? = x
        while (y != null && actual == y.rightChild){
            actual = y
            y = y.parent
        }
        return y
    }

    fun eliminar(i: String, t: String){
        var nodo: Node? = buscar(i, t)
        if (nodo == null){
            throw IllegalArgumentException("La cancion seleccionada no esta en la lista")
        }

        var y: Node? 
        var x: Node? 

        if (nodo.leftChild == null || nodo.rightChild == null){
            y = nodo
        }else{
            y = sucesor(nodo)
        }
        if (y?.leftChild != null){
            x = y?.leftChild
        } else { 
            x = y?.rightChild
        } 
        if( x !=  null  ){
            x?.parent = y?.parent 
        }
        if (y?.parent == null){ 
            this.head = x
        }else{
            if( y == y?.parent?.leftChild ){
                y?.parent?.leftChild = x
            } else { 
                y?.parent?.rightChild = x 
            }
        }
        if (y != nodo){
            // move y node into nodo's position instead of trying to reassign an immutable key
            // attach y? to nodo's parent
            y?.parent = nodo?.parent
            if (nodo?.parent == null) {
                this.head = y
            } else {
                if (nodo == nodo?.parent?.leftChild) {
                    nodo?.parent?.leftChild = y
                } else {
                    nodo?.parent?.rightChild = y
                }
            }

            // adopt nodo's left child
            y?.leftChild = nodo?.leftChild
            if (y?.leftChild != null) {
                y?.leftChild?.parent = y
            }

            // adopt nodo's right child
            y?.rightChild = nodo?.rightChild
            if (y?.rightChild != null) {
                y?.rightChild?.parent = y
            }
        }
    }
}
