package proyecto

import java.io.File

/**
* Clase que representa un Tipo Abstracto de Dato (TAD) para una Canción.
*
* Descripción:
* Encapsula la información esencial de una pieza musical: su título, intérprete y la
* ruta de acceso a su archivo físico (ubicación).
*
* @param titulo El título de la canción. Es inmutable (val).
* @param interprete El nombre del artista o intérprete de la canción. Es inmutable (val).
* @param ubicacion La ruta del archivo físico de la canción. Es mutable (var).
*
* Precondición:
* - titulo != null && interprete != null %% ubicacion != null
 * - ubicacion tiene la dirección absoluta válida a un archivo mp3
*
* Postcondición:
*/
class Cancion (val titulo: String, val interprete: String, var ubicacion: String){
    init {
        if (!esUbicacionValida(ubicacion)){
             throw IllegalArgumentException("ERROR: La ubicación '$ubicacion' no es válida o no es un archivo MP3.")
        }
    }

    /**
     * Descripción: Devuelve el título de la canción.
     *
     * Parámetros: No hay.
     *
     * @return El título de la canción.
     *
     * Precondición: La instancia de Cancion debe estar inicializada.
     *
     * Postcondición: Devuelve el valor inmutable del título.
     */
    fun obtenerTitulo() : String{
        return titulo
    }

    /**
     * Descripción: Devuelve el intérprete de la canción.
     *
     * Parámetros: No hay.
     *
     * @return El intérprete de la canción.
     *
     * Precondición: true.
     *
     * Postcondición: Devuelve el nombre del interprete.
     */
    fun obtenerInterprete(): String{
        return interprete
    }

    /**
     * Descripción: Devuelve la ubicación (ruta del archivo) de la canción.
     *
     * Parámetros: No hay.
     *
     * @return La ubicación del archivo.
     *
     * Precondición: true.
     *
     * Postcondición: Devuelve el valor actual de la ubicación.
     */
    fun obtenerubicacion(): String{
        return ubicacion
    }
    /**
     * Descripción: Proporciona una representación en cadena del objeto Cancion.
     *
     * Parámetros: No hay.
     *
     * @return Una cadena formateada que incluye el título y el intérprete.
     *
     * Precondición: true.
     *
     * Postcondición: Devuelve una cadena de formato.
     */
    override fun toString(): String {
        return "----------------------\nTítulo: $titulo \nInterprete: $interprete"
    }
    
    
    /**
     * Descripción: Función auxiliar privada que verifica si la ruta de la ubicación es válida.
     *
     * @param ruta La cadena que contiene la ruta de la ubicación a verificar.
     * @return 'true' si la ruta es absoluta, apunta a un archivo existente y tiene extensión '.mp3'; 'false' en caso contrario.
     *
     * Precondición: La 'ruta' debe ser una cadena no nula.
     *
     * Postcondición: No modifica el estado del objeto.
     */
    private fun esUbicacionValida(ruta: String): Boolean{
        val file = File(ruta)
        return file.isAbsolute && file.exists() && file.isFile && ruta.lowercase().endsWith(".mp3")

    }
}