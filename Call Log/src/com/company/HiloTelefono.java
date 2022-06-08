package com.company;

import javax.management.loading.MLetContent;
import java.io.*;
import java.util.ArrayList;

public class HiloTelefono extends Thread {

    //
    private ArrayList<Empleado> listaEmpleados = new ArrayList();
    private int primerFichero;
    private int ultimoFichero;


    //CONSTRUCTOR PARA CREACIÓN DE HILOS EN EL MAIN
    public HiloTelefono(ArrayList<Empleado> listaEmpleados, int primerFichero, int ultimoFichero) {
        this.listaEmpleados = listaEmpleados;
        this.primerFichero = primerFichero;
        this.ultimoFichero = ultimoFichero;
    }


    int recuento;  //VARIABLE ACUMULADOR DE RECUENTOS


    //CODIGO QUE RECORREN LOS HILOS
    public void run() {
        //BUCLE CON FILTRO PRIMER Y ULT NUMERO
        for (int i = primerFichero; i <= ultimoFichero; i++) {


            //CAPTURA EXCEPCIONES
            try {
                //FILEREADER LEE EL ARCHIVO TXT
                int acumTxt = primerFichero; //ACUMULADOR PARA EL CAMBIO DE ARCHIVO .txt
                String addTxt = acumTxt + ".txt"; //AGREGA NUMERO + .txt AL FINAL DE LA RUTA
                FileReader lectorRegistro = new FileReader("D:\\Principales\\Documentos\\RegistroLlamadas\\" + addTxt);

                //BUFFEREDREADER LEE LINEA POR LINEA
                BufferedReader lector = new BufferedReader(lectorRegistro);


                String ln = lector.readLine();
                //BUCLE LEE LINEAS DEL TXT MIENTRAS NO SEA NULL
                for (; ln != null;) {

                    String[] nombreEMP = ln.split(","); //DIVIDE CADA LINEA EN DOS A PARTIR DE LA COMA
                    Empleado emp = searcher(nombreEMP[0]);//RECOGE LA PARTE IZQUIERDA DE LA COMA (NOMBRE)
                    int min = Integer.parseInt(nombreEMP[1]); // RECOGE PARTE DERECHA DE LA COMA (MINUTOS)


                    //CREA EMPLEADO SI NO EXISTE
                    if (emp == null) {
                        Empleado empleado = new Empleado(nombreEMP[0], min);
                        this.listaEmpleados.add(empleado);
                    }


                    //SI EL EMPLEADO YA EXISTE LE SUMA LOS MINUTOS QUE ENCUENTRA
                    if (emp != null) {
                        synchronized (this) {
                            recuento = emp.getNumMin(); //recuento SE IGUALA A numMin (Los minutos del empleado)
                            emp.setNumMin(recuento + min); // Y SE SUMA CON UN SETTER LAS DOS VARIABLES
                        }
                    }
                    //SIG LINEA
                    ln = lector.readLine();
                    acumTxt ++;
                }
                //CIERRE DE LECTORES
                lector.close();
                lectorRegistro.close();


                //CAPTURA DE EXCEPCIONES
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //BUSCA AL EMPLEADO A PARTIR DE LA PARTE IZQUIERDA DE LA COMA DE LOS .txt
    private Empleado searcher(String busquedaNombre) {
        Empleado devuelve = null; //INICIALIZACION
        for (int i = 0; i < this.listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i) != null &&
                    listaEmpleados.get(i).getNombreYApellido().equals(busquedaNombre)) {
                devuelve = listaEmpleados.get(i);
            }
        }
        return devuelve;
    }


}


