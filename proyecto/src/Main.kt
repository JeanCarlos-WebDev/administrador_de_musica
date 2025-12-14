package proyecto

fun main() {
    println("=== Ejecutando Main.kt ===")

    val rutaMP3 = "/mnt/c/Users/jeans/OneDrive/Escritorio/Jean Carlos/College/Sophomore year/Sept-Dic/Lab de algos II/labs/proyecto/administrador_de_musica/proyecto/mp3Files/sample3.mp3"

    // ====================== Comprobación Cancion.kt ======================
    println("=== Comprobación Cancion.kt ===")
    val cancion: Cancion = try {
        Cancion("Sample 3", "Artista Prueba", rutaMP3).also {
            println("Canción creada correctamente: $it")
        }
    } catch (e: Exception) {
        println("Error al crear Canción: ${e.message}")
        return
    }

    // ====================== Comprobación Reproductor.kt ======================
    println("=== Comprobación Reproductor.kt ===")
    try {
        val reproductor = Reproductor(cancion)

        println("Reproduciendo canción...")
        reproductor.reproducir()
        Thread.sleep(2000) // reproducir 2 segundos para prueba

        println("Pausando canción...")
        reproductor.pausa()
        Thread.sleep(1000)

        println("Cargando misma canción nuevamente...")
        reproductor.cargarCancion(cancion)

        println("Reproduciendo de nuevo...")
        reproductor.reproducir()
        Thread.sleep(2000)

        println("Parando canción...")
        reproductor.parar()

        println("¿Está tocando canción?: ${reproductor.estaTocandoCancion()}")
    } catch (e: Exception) {
        println("Error al probar Reproductor: ${e.message}")
    }

    // ====================== Comprobación ArbolDeCanciones.kt ======================
    println("=== Comprobación ArbolDeCanciones.kt ===")
    try {
        val arbol = ArbolDeCanciones()
        val cancion2 = Cancion("Otra Sample", "Artista Prueba", rutaMP3)
        arbol.anadirCanciones(cancion)
        arbol.anadirCanciones(cancion2)

        println("Árbol creado y canciones agregadas correctamente")
        println("¿Es árbol de búsqueda válido?: ${arbol.esArbolDeBusqCancion()}")

        val listaSecuencia = arbol.deArbolASecuencia()
        println("Recorrido en secuencia del árbol:")
        for (c in listaSecuencia) {
            println("- ${c.obtenerTitulo()} de ${c.obtenerInterprete()}")
        }

    } catch (e: Exception) {
        println("Error al probar ArbolDeCanciones: ${e.message}")
    }

    println("=== Ejecución de Main.kt finalizada ===")
}

