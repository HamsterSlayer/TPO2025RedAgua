/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reddistribucionagua2025.Hash;

import com.mycompany.reddistribucionagua2025.readtxt.formatoUsuario;

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
    private String nomenclatura1;
    private String nomenclatura2;
    private float caudalMaximo;
    private float caudalMinimo;
    private float diametro;
    private EstadoTuberia estado;
    // --------------------------------------------------------------------------

    public Tuberias(String nomenc, String nomenc2, float cMaximo, float cMinimo, float dim, String elEstado) {
        nomenclatura1 = nomenc;
        nomenclatura2 = nomenc2;
        caudalMaximo = cMaximo;
        caudalMinimo = cMinimo;
        diametro = dim;
        aEnum(elEstado);
    }

    public boolean validarEstado(String estadoIntroducido) {
        boolean resultado;
        estadoIntroducido = formatoEnum(estadoIntroducido);
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
        estadoIntroducido = formatoEnum(estadoIntroducido);
        this.estado = EstadoTuberia.valueOf(estadoIntroducido);
    }

    private String formatoEnum(String texto) {
        String resultado = texto.trim().replace(" ", "_");
        resultado = formatoUsuario.sacarAcentos(resultado.toLowerCase());
        resultado = resultado.toUpperCase();
        return resultado;
    }

    public String getNomenclatura1() {
        return nomenclatura1;
    }

    public String getNomenclatura2() {
        return nomenclatura2;
    }

    public String getAmbasNomenclaturas() {
        return nomenclatura1 + "-" + nomenclatura2;
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
                nomenclatura1 + "-" + nomenclatura2, caudalMaximo, caudalMinimo, diametro, estado.toString());
        return resultado;
    }
}
