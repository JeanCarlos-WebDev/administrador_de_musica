package proyecto

import java.util.Scanner
import java.lang.Thread
import java.io.FileInputStream
import PausablePlayer

class Reproductor (var cancion: Cancion){
    private var isPlaying: Boolean = false
    private var player: PausablePlayer? = null
    private var inputStream: FileInputStream? = null


    fun cargarCancion(cancionNueva: Cancion){
        // Si había una canción sonando, detenerla primero
        if (isPlaying) {
            parar()      // Detener la canción anterior
        }
        cancion = cancionNueva
    }

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

    fun estaTocandoCancion(): Boolean{
        return isPlaying
    }

}

