/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria_Yenny;

import Libreria_Yenny.Servicios.ServicioAutor;
import Libreria_Yenny.Servicios.ServicioEditorial;
import Libreria_Yenny.Servicios.ServicioLibro;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.Scanner;

/**
 *
 * @author RENZO
 */
public class Libreria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AWTException, Exception {

        //ServicioAutor.crearAutor();
        //ServicioEditorial.crearEditorial();
        //ServicioLibro.crearLibro();
        //ServicioLibro.buscarLibroPorNombreAutor();
        //ServicioLibro.DarAltaoBajaLibro();
        Scanner leer = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("INGRESE OPCION\n");
            System.out.println("1 - REGISTRAR AUTOR");
            System.out.println("2 - REGISTRAR EDITORIAL");
            System.out.println("3 - REGISTRAR LIBRO");
            System.out.println("4 - MODIFICAR AUTOR POR ID");
            System.out.println("5 - MODIFICAR EDITORIAL POR ID");
            System.out.println("6 - MODIFICAR LIBRO POR ISBN");
            System.out.println("7 - BORRAR AUTOR POR ID");
            System.out.println("8 - BORRAR EDITORIAL POR ID");
            System.out.println("9 - BORRAR LIBRO POR ISBN");
            System.out.println("10 - Búsqueda de un Autor por nombre");
            System.out.println("11 - Búsqueda de un libro por ISBN");
            System.out.println("12 - Búsqueda de un libro por Título");
            System.out.println("13 - Búsqueda de un libro/s por nombre de Autor");
            System.out.println("14 - Búsqueda de un libro/s por nombre de Editorial");
            System.out.println("15 - Listar TODOS los libros");
            System.out.println("16 - SALIR");
            opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                    ServicioAutor.crearAutor();
                    break;
                case 2:
                    ServicioEditorial.crearEditorial();
                    break;
                case 3:
                    ServicioLibro.crearLibro();
                    break;
                case 4:
                    ServicioAutor.modificarAutor();
                    break;
                case 5:
                    ServicioEditorial.modificarEditorial();
                    break;
                case 6:
                    ServicioLibro.modificarLibro();
                    break;
                case 7:
                    ServicioAutor.eliminarAutor();
                    break;
                case 8:
                    ServicioEditorial.eliminarEditorial();
                    break;
                case 9:
                    ServicioLibro.eliminarLibro();
                    break;
                case 10:
                    ServicioAutor.buscarAutorPorNombre();
                    break;
                case 11:
                    ServicioLibro.buscarLibroPorISBN();
                    break;
                case 12:
                    ServicioLibro.buscarLibroPorTitulo();
                    break;
                case 13:
                    ServicioLibro.buscarLibroPorNombreAutor();
                    break;
                case 14:
                    ServicioLibro.buscarLibroPorNombreEditorial();
                    break;
                case 15:
                    ServicioLibro.verTodosLosLibros();
                    break;
                default:
                    System.out.println("GRACIAS POR USARME, ESTAMOS AGRADECIDOS");
                    return;
            }
            //limpiar();
        } while (opcion <= 15);

    }

    public static void limpiar() throws AWTException {
        //System.out.println("\nPresione Enter para continuar..."); //EN EL CASO QUE SE QUIERA USAR CON TECLA, NO USAR pressbot.delay() ya que es automÃ¡tico ese
        //new java.util.Scanner(System.in).nextLine();

        Robot pressbot = new Robot();
        pressbot.delay(3000);  //TIEMPO QUE DEJA LA PANTALLA ANTES DE COMENZAR LA OTRA (mili segundos) . SI SE USA ESTO, NO USAR LAS PRIMERAS DOS LINEAS YA QUE ES CON TECLA
        pressbot.setAutoDelay(1); //TIEMPO EN QUE SE ACTIVA DE NUEVO LA PANTALLA (mili segundos)
        pressbot.keyPress(17);//orden para apretar C10TRL key
        pressbot.keyPress(76);//orden para apretar L key
        pressbot.keyRelease(17); //orden para soltar CTRL key
        pressbot.keyRelease(76); //orden para soltar  key
    }

}
