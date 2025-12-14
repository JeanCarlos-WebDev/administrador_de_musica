package proyecto

import java.io.File
import java.io.BufferedReader

class LR {
    var contenido: ArbolDeCanciones =  ArbolDeCanciones()

    fun agregarLista(nombreArchivo:String){
        val file: File = File(nombreArchivo)
        val bufferedReader: BufferedReader = file.bufferedReader()
        var linea = bufferedReader.readLine()
        try {
            while (linea != null) {
                val partes = linea.split(";").map { it.trim() }
                if (partes.size >= 3) { // evitar IndexOutOfBound
            contenido.anadirCanciones(Cancion(partes[1], partes[0], partes[2]))
        }
            linea = bufferedReader.readLine()
        }
        } finally {
            bufferedReader.close()  // cerramos el archivo manualmente  
        }
    }

    fun eliminarCancion(i: String, t: String){
        contenido.eliminar(i, t)
    }

    fun obtenerLR(): ListaDoblementeEnlazadaCircular{
        return contenido.deArbolASecuencia()
    }

    fun mostrarLR(){
        val lista: ListaDoblementeEnlazadaCircular = contenido.deArbolASecuencia()
        for (cancion in lista){
            println(cancion)
        }

    }
}
