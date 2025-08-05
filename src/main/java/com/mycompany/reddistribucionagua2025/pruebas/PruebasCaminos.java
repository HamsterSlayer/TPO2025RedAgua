package com.mycompany.reddistribucionagua2025.pruebas;

public class PruebasCaminos {

    public static void main(String[] args) {

        System.out.println("Unidad de test para los métodos de Caudal Pleno y de Camino Más corto");
        System.out.println("Existen dos caminos posibles desde Panama a Tijuana. \n" +
                "1: Panama-Puebla-Tijuana, con 3 vertices y caudal total 1010\n" +
                "2: Panama-Tegucipalpa, con 2 vertices y caudal total 1730 \n" +
                "Si los métodos funcionan correctamente, el método Caudal pleno deberia retornar el camino 1," +
                " y el metodo camino más corto deberia retornar el camino 2");
        System.out.println("Caudal pleno de Panama a Tijuana");
        // caudalPleno(new Ciudad("Panama"),new Ciudad("Tijuana"))
        System.out.println("Camino más corto de Panama a Tijuana");
        // caminoMasCorto(new Ciudad("Panama"),new Ciudad("Tijuana"))
        System.out.println("Ingreso una ciudad que no existe en el gráfico," +
                "   intentando llegar a una ciudad que existe: Canberra a Panama");
        System.out.println("Caudal Pleno de Canberra a Panama");
        // caudalPleno(new Ciudad("Canberra"), new Ciudad("Panama"))
        System.out.println("Camino más corto de Canberra a Panama");
        // caminoMasCorto(new Ciudad("Canberra"), new Ciudad("Panama"))
        System.out.println("Ingreso una ciudad que existe en el gráfico," +
                "   intentando llegar a una ciudad que no existe: Panama a Canberra");
        System.out.println("Caudal Pleno de Panama a Canberra");
        // caudalPleno(new Ciudad("Canberra"), new Ciudad("Panama"))
        System.out.println("Camino más corto de Panama a Canberra");
        // caminoMasCorto(new Ciudad("Canberra"), new Ciudad("Panama"))
        System.out.println("Ingreso dos ciudades que no existen en el gráfico," +
                " intentando llegar de Canberra a Osaka");
        System.out.println("Caudal Pleno de Canberra a Osaka");
        // caudalPleno(new Ciudad("Canberra"), new Ciudad("Osaka"))
        System.out.println("Camino más corto de Canberra a Osaka");
        // caminoMasCorto(new Ciudad("Canberra"), new Ciudad("Osaka"))
        System.out.println("Ingreso dos ciudades que tienen unicamente un camino posible: Monterey a Santo Domingo");
        System.out.println("Caudal Pleno de Monterey a Santo Domingo");
        // caudalPleno(new Ciudad("Monterey"), new Ciudad("Santo Domingo"))
        System.out.println("Camino más corto de Monterey a Santo Domingo");
        // caminoMasCorto(new Ciudad("Monterey"), new Ciudad("Santo Domingo"))
    }

}
