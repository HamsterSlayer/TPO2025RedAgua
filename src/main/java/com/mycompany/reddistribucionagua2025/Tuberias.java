/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reddistribucionagua2025;

/**
 *
 * @author ivoel
 */
public class Tuberias {
    private String nomenclatura;
    private float caudalMaximo;
    private float caudalMinimo;
    private float diametro;
    private String estado;
    // verificar si string y float son realmente apropiados para estos datos.

    public Tuberias(String nomenc, float cMaximo, float cMinimo, float dim, String elEstado) {
        nomenclatura = nomenc;
        caudalMaximo = cMaximo;
        caudalMinimo = cMinimo;
        diametro = dim;
        estado = elEstado;
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
        return estado;
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
        estado = elEstado;
    }

}
