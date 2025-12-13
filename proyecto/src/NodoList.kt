package proyecto

class NodoList(val key: Cancion?, var prev: NodoList? = null, var next: NodoList? = null) {
    init {

        if (prev == null) prev = this
        if (next == null) next = this
    }
}
