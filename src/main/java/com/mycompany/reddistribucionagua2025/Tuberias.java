/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reddistribucionagua2025;

/**
 *
 * @author ivoel
 */

//Temas aprendidos: Como usar ENUM.

public class Tuberias {
    
    //ENUM ----------------------------------------
    private enum EstadoTuberia {
        ACTIVO,
        EN_REPARACION,
        EN_DISENO,
        INACTIVO
    }
    //-----------------------------------------------
    
    //Variables ---------------------------------------------------------------
    private String nomenclatura;
    private float caudalMaximo;
    private float caudalMinimo;
    private float diametro;
    private EstadoTuberia estado;
    //--------------------------------------------------------------------------
    // verificar si string y float son realmente apropiados para estos datos.

    public Tuberias(String nomenc, float cMaximo, float cMinimo, float dim, String elEstado) {
            nomenclatura = nomenc;
            caudalMaximo = cMaximo;
            caudalMinimo = cMinimo;
            diametro = dim;
            aEnum(elEstado);
    }

    public boolean validarEstado(String estadoIntroducido) {
        boolean resultado;
        estadoIntroducido = formato(estadoIntroducido);
        switch(EstadoTuberia.valueOf(estadoIntroducido)) {
            case ACTIVO,
                 EN_REPARACION,
                 EN_DISENO,
                 INACTIVO: {
                    resultado = true;
                    break;
                 }
            default:
                resultado = false;
        }
        return resultado;
    }
    
    private void aEnum(String estadoIntroducido) {
        estadoIntroducido = formato(estadoIntroducido);
        this.estado = EstadoTuberia.valueOf(estadoIntroducido);
    }
    
    private String formato(String estadoIntroducido) {
        estadoIntroducido = estadoIntroducido.trim();
        estadoIntroducido = estadoIntroducido.replace(" ", "_");
        estadoIntroducido = sacarAcentos(estadoIntroducido);
        estadoIntroducido = estadoIntroducido.toUpperCase();
        return estadoIntroducido;
    }
    
        private String sacarAcentos(String texto) {
    return texto.replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ñ", "n")
                .replace("ü", "u")
                .replace("ç", "c");
    }
    
    public String getNomenclatura() {
        return nomenclatura;
    }

    public float getCaudalMaximo() {
        return caudalMaximo;
    }

    public float getCaudalMinimo() {
        return caudalMinimo;
    }

    public float getDiametro() {
        return diametro;
    }

    public String getEstado() {
        return estado.toString();
    }

    public void setCaudalMaximo(float elMaximo) {
        caudalMaximo = elMaximo;
    }

    public void setCaudalMinimo(float elMinimo) {
        caudalMinimo = elMinimo;
    }

    public void setDiametro(float elDiametro) {
        diametro = elDiametro;
    }

    public void setEstado(String elEstado) {
        if (validarEstado(elEstado)) {
            aEnum(elEstado);
        }
    }
    
    public String toString() {
        String resultado = String.format("Tuberia: %s, caudalMax: %f - caudalMin %f; diametro: %f. ESTADO: %s", nomenclatura,caudalMaximo,caudalMinimo,diametro,estado.toString());
        return resultado;
    }
}
