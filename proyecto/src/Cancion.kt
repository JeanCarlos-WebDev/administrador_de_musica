package proyecto

import java.io.File

class Cancion (val titulo: String, val interprete: String, var ubicacion: String){
    init {
        if (!esUbicacionValida(ubicacion)){
             throw IllegalArgumentException("ERROR: La ubicación '$ubicacion' no es válida o no es un archivo MP3.")
        }
    }

    fun obtenerTitulo() : String{
        return titulo
    }

    fun obtenerInterprete(): String{
        return interprete
    }

    fun obtenerubicacion(): String{
        return ubicacion
    }

    override fun toString(): String {
        return "----------------------\nTítulo: $titulo \nInterprete: $interprete"
    }
    
    /** 
     * Verifica que la ubicación sea:
     * - una ruta absoluta
     * - un archivo existente
     * - un archivo con extensión .mp3
     */

    private fun esUbicacionValida(ruta: String): Boolean{
        val file = File(ruta)
        return file.isAbsolute && file.exists() && file.isFile && ruta.lowercase().endsWith(".mp3")

    }
}