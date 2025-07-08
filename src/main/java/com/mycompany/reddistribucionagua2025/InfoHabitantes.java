package com.mycompany.reddistribucionagua2025;

public class InfoHabitantes {
    private float promedioAnual;
    private Lista habitantesPorMes;

    // Clase creada para que sea posible la consulta de promedio anual y tambien de
    // consultas para cada mes
    // como pide el inciso 4

    public InfoHabitantes(Lista habitantesMensuales) {
        habitantesPorMes = habitantesMensuales;
        calcularPromedioAnual();
    }

    private void calcularPromedioAnual() {
        int sumaHabitantes = 0;
        int i = 1, n = habitantesPorMes.longitud();
        while (i <= n) {
            // ? en algun momento transformar esto en una excepcion como para que llore si
            // ? el elemento de la lista no es un int??
            sumaHabitantes += (int) habitantesPorMes.recuperar(i);
            i++;
        }
        promedioAnual = sumaHabitantes / 12;
    }

    public float getPromedioAnual() {
        return promedioAnual;
    }

    public Lista getHabitantesPorMes() {
        return habitantesPorMes;
    }

}
