package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //CREACION DE COLECCION QUE ALMACENA EMPLEADOS Y MINUTOS
        ArrayList <Empleado> emp = new ArrayList<>();


        //INSTANCIA DE CLASE HILOTELEFONO, CREACION E INICIALIZACION: HILO 1
        HiloTelefono hiloPrimero = new HiloTelefono(emp,1, 10);
        Thread hilo1 = new Thread(hiloPrimero);

        //INSTANCIA DE CLASE HILOTELEFONO, CREACION E INICIALIZACION: HILO 2
        HiloTelefono hiloSegundo = new HiloTelefono(emp,11, 20);
        Thread hilo2 = new Thread(hiloSegundo);
        
        //INSTANCIA DE CLASE HILOTELEFONO, CREACION E INICIALIZACION: HILO 3
        HiloTelefono hiloTercero = new HiloTelefono(emp,21,30);
        Thread hilo3 = new Thread(hiloTercero);
        
		hilo1.start();
		hilo2.start();
		hilo3.start();
		
		hilo1.join()
		hilo2.join()
		hilo3.join()


        //SOUT DE LOS EMPLEADOS ORGANIZADO
        System.out.println("\nRECUENTO DE MINUTOS DE TELÉFONO\n===============================\n");
        for (int j = 0; j<emp.size(); j++){
            System.out.println("► Empleado: "+emp.get(j).getNombreYApellido()+"\n   → Minutos: " + emp.get(j).getNumMin() +
                     " (" +(emp.get(j).getNumMin())/60 + " horas)\n");
        }

    }
}
