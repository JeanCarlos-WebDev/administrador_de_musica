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
}
