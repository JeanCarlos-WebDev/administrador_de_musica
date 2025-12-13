package proyecto

fun main() {
    println("=== Ejecutando Main.kt ===")

    // Ruta absoluta correcta
    val rutaMP3 = "/home/familia/proyecto/mp3Files/sample3.mp3"

    // ====================== Comprobación Cancion.kt ======================
    println("=== Comprobación Cancion.kt ===")
    try {
        val cancion = Cancion("Sample 3", "Artista Prueba", rutaMP3)
        println("Canción creada correctamente: $cancion")
    } catch (e: Exception) {
        println("Error al crear Canción: ${e.message}")
    }

    // ====================== Comprobación ListaDoblementeEnlazadaCircular.kt ======================
    println("=== Comprobación ListaDoblementeEnlazadaCircular.kt ===")
    try {
        val lista = ListaDoblementeEnlazadaCircular()
        val cancion = Cancion("Sample 3", "Artista Prueba", rutaMP3)
        lista.agregarAlFinal(cancion)
        println("Lista creada y canción agregada correctamente")
    } catch (e: Exception) {
        println("Error al probar Lista: ${e.message}")
    }

    // ====================== Comprobación ArbolDeCanciones.kt ======================
    println("=== Comprobación ArbolDeCanciones.kt ===")
    try {
        val arbol = ArbolDeCanciones()
        val cancion = Cancion("Sample 3", "Artista Prueba", rutaMP3)
        arbol.anadirCanciones(cancion)
        println("Árbol creado y canción agregada correctamente")
    } catch (e: Exception) {
        println("Error al probar ArbolDeCanciones: ${e.message}")
    }

    // ====================== Comprobación Reproductor.kt ======================
    println("=== Comprobación Reproductor.kt ===")
    try {
        val cancion = Cancion("Sample 3", "Artista Prueba", rutaMP3)
        val reproductor = Reproductor(cancion)
        println("Reproductor creado correctamente con la canción: ${cancion.obtenerTitulo()}")
        // Se puede probar reproducir, pausar, y parar si se desea
        // reproductor.reproducir()
        // reproductor.pausa()
        // reproductor.parar()
    } catch (e: Exception) {
        println("Error al probar Reproductor: ${e.message}")
    }

    // ====================== Comprobación NodoList.kt ======================
    println("=== Comprobación NodoList.kt ===")
    try {
        val cancion = Cancion("Sample 3", "Artista Prueba", rutaMP3)
        val nodo = NodoList(cancion)
        println("NodoList creado correctamente con la canción: ${nodo.key?.obtenerTitulo()}")
    } catch (e: Exception) {
        println("Error al probar NodoList: ${e.message}")
    }

    println("=== Ejecución de Main.kt finalizada ===")
}

