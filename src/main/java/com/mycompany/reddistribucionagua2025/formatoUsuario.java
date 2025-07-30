package com.mycompany.reddistribucionagua2025;

/**
 * Ofrece a todas las clases un formato en común para manejar la entrada del
 * usuario. Formato: todo minusculas, sin espacios, sin acentos. Ejemplo: Buenos
 * Aires -> buenosaires
 *
 * @author hamst
 */

public class formatoUsuario {

    private formatoUsuario() {

    }

// Manejo Entrada del Usuario ------------------------------------------------
    //Estoy practicando el javadocs...
    /**
     * Provee el formato establecido: minusculas, sin espacios, sin acentos
     * @param nombreSinFormato nombre sin formato.
     * @return String con la entrada formateada
     * <pre>
     * Ejemplo:
     * Buenos Aires -> buenosaires
     * Córdoba -> cordoba
     * </pre>
     */
    public static String formatoNombre(String nombreSinFormato) {
        String devuelto = nombreSinFormato.replace(" ", "").trim().toLowerCase();
        // Caso acentos
        devuelto = sacarAcentos(devuelto);
        return devuelto;
    }
    
    /**
     * Remueve todos los acentos mas comunes
     * @param texto texto con acentos
     * @return String sin acentos
     * <pre>
     * Ejemplo:
     * Córdoba -> Cordoba
     * plaça -> placa
     * 
     */
    public static String sacarAcentos(String texto) {
        return texto.replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ñ", "n")
                .replace("ü", "u")
                .replace("ç", "c");
    }
// --------------------------------------------------------------------------
    
}
