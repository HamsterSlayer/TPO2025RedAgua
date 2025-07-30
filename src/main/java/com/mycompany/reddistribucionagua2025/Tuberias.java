/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reddistribucionagua2025;

/**
 *
 * @author ivoel
 */

// Temas aprendidos: Como usar ENUM.

public class Tuberias {

    // ENUM ----------------------------------------
    private enum EstadoTuberia {
        ACTIVO,
        EN_REPARACION,
        EN_DISENO,
        INACTIVO
    }
    // -----------------------------------------------

    // Variables ---------------------------------------------------------------
    private String nomenclatura;
    private float caudalMaximo;
    private float caudalMinimo;
    private float diametro;
    private EstadoTuberia estado;
    // --------------------------------------------------------------------------

    public Tuberias(String nomenc, float cMaximo, float cMinimo, float dim, String elEstado) {
        nomenclatura = nomenc;
        caudalMaximo = cMaximo;
        caudalMinimo = cMinimo;
        diametro = dim;
        aEnum(elEstado);
    }    

    public boolean validarEstado(String estadoIntroducido) {
        boolean resultado;
        estadoIntroducido = formatoUsuario.formatoNombre(estadoIntroducido);
        switch (EstadoTuberia.valueOf(estadoIntroducido)) {
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
        estadoIntroducido = formatoUsuario.formatoNombre(estadoIntroducido);
        this.estado = EstadoTuberia.valueOf(estadoIntroducido);
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

    public boolean setEstado(String elEstado) {
        boolean exito = validarEstado(elEstado);
        if (exito) {
            aEnum(elEstado);
        }
        return exito;

    }

    public String toString() {
        String resultado = String.format("Tuberia: %s, caudalMax: %f - caudalMin %f; diametro: %f. ESTADO: %s",
                nomenclatura, caudalMaximo, caudalMinimo, diametro, estado.toString());
        return resultado;
    }
}
