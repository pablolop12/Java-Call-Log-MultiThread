package com.company;

public class Empleado{

    //ATRIBUTOS
    private String nombreYApellido;
    private int numMin;


    public Empleado(String nombreYApellido, int numMin) {
        this.nombreYApellido = nombreYApellido;
        this.numMin = numMin;
    }

    //GETTER NOMBRE COMPLETO
    public String getNombreYApellido() {
        return nombreYApellido;
    }


    //GETTER Y SETTER NUMERO MINIMO
    public int getNumMin() {
        return numMin;
    }

    public void setNumMin(int numMin) {
        this.numMin = numMin;
    }

}

