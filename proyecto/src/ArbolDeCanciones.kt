package proyecto

class ArbolDeCanciones {
    var head: Node? = null

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
    //cancion1 < cancion2
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
    fun deArbolASecuencia(): ListaDoblementeEnlazadaCircular{
        val listaResultado = ListaDoblementeEnlazadaCircular()


        recorridoEnOrden(head, listaResultado)

        return listaResultado
    }

    fun recorridoEnOrden(node: Node?, lista: ListaDoblementeEnlazadaCircular) {
        if (node == null) {
            return
        }

        recorridoEnOrden(node.leftChild, lista)


        lista.agregarAlFinal(node.key)

        recorridoEnOrden(node.rightChild, lista)
    }

    fun esArbolDeBusqCancionR(nodo: Node?, minCancion: Cancion?, maxCancion: Cancion?): Boolean {
        if (nodo == null) return true

        val cancion = nodo.key!!

        if (minCancion != null && CompararCanciones(cancion, minCancion)) return false
        if (maxCancion != null && CompararCanciones(maxCancion, cancion)) return false


        val izquierdaValida = esArbolDeBusqCancionR(nodo.leftChild, minCancion, cancion)


        val derechaValida = esArbolDeBusqCancionR(nodo.rightChild, cancion, maxCancion)

        return izquierdaValida && derechaValida
    }

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
