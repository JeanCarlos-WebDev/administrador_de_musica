package proyecto

import java.util.Scanner
import java.lang.Thread
import java.io.FileInputStream
import PausablePlayer

/**
 * Clase que simula un Tipo Abstracto de Dato (TAD) para un Reproductor de Música simple.
 *
 * Descripción:
 * Gestiona la carga, reproducción, pausa y detención de una única canción en formato de archivo.
 * Utiliza un objeto 'PausablePlayer' (asumido) para manejar el flujo de audio.
 *
 * @param cancion La instancia inicial de 'Cancion' que el reproductor debe cargar. Es mutable (var).
 *
 * Precondición:
 * - cancion != null && cancion.ubicacion es válida.
 *
 */
class Reproductor (var cancion: Cancion){
    private var isPlaying: Boolean = false
    private var player: PausablePlayer? = null
    private var inputStream: FileInputStream? = null

    /**
     * Descripción: Carga una nueva canción en el reproductor.
     *
     * Si actualmente se está reproduciendo una canción, esta se detiene antes de cargar la nueva.
     *
     * @param cancionNueva La nueva instancia de 'Cancion' a cargar.
     *
     * Precondición:
     * - cancionNueva debe ser una instancia válida de Cancion.
     *
     * Postcondición:
     * - cancion = cancionNueva
     */
    fun cargarCancion(cancionNueva: Cancion){
        // Si había una canción sonando, detenerla primero
        if (isPlaying) {
            parar()
            isPlaying = falses
        // Detener la canción anterior
        }
        cancion = cancionNueva
    }

    /**
     * Descripción: Inicia o reanuda la reproducción de la canción actualmente cargada.
     *
     * Crea un nuevo flujo de entrada de archivo si es necesario, inicializa el reproductor
     * y comienza la reproducción.
     *
     * Parámetros: No hay.
     *
     * Precondición:
     * - this.cancion.ubicacion es una ruta absoluta válida.
     *
     * Postcondición:
     */
    fun reproducir(){

        try{
            val inputStream = FileInputStream(cancion.ubicacion)
            player = PausablePlayer(inputStream!!)
            player?.play()
            isPlaying = true
        } catch (e: Exception){
            println("No se pudo reproducir la canción: ${e.message}")
        }
    }

    /**
     * Descripción: Detiene completamente la reproducción de la canción actual.
     *
     * Cierra el flujo de entrada de archivo y libera los recursos del reproductor.
     *
     * Parámetros: No hay.
     *
     * Precondición: true.
     *
     * Postcondición:
     * isPlaying = false
     */
    fun parar(){
        
        try {
        player?.stop()
        inputStream?.close()
        
        } catch (e: Exception) {
            println("Error al parar: ${e.message}")
        }
        player = null
        inputStream = null
        isPlaying = false
        println("Reproducción detenida")

    }


    /**
     * Descripción: Pausa la reproducción de la canción actual.
     *
     * La pausa solo es efectiva si actualmente hay una canción en estado de reproducción.
     *
     * Parámetros: No hay.
     *
     * Precondición:
     * - player != null
     * - isPlaying = true.
     *
     * Postcondición:
     *  - isPlaying = false
     */
    fun pausa(){
        if (player != null && isPlaying) {
            try {
                player?.pause()
                isPlaying = false
                } catch (e: Exception) {
                    println("Error al parar: ${e.message}")
                }
            println("Reproducción pausada")
        }else {
                println("No hay canción reproduciéndose")
            }
    }

    /**
     * Descripción: Consulta el estado actual del reproductor.
     *
     * Parámetros: No hay.
     *
     * @return 'true' si una canción se está reproduciendo activamente; 'false' en caso contrario.
     *
     * Precondición: true.
     *
     * Postcondición: true.
     */
    fun estaTocandoCancion(): Boolean{
        return isPlaying
    }

}

