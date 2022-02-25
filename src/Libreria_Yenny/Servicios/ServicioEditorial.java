/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria_Yenny.Servicios;


import Libreria_Yenny.entidades.Editorial;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author RENZO
 */
public class ServicioEditorial {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("libreriaPU");
    static EntityManager em = emf.createEntityManager();
    static Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public static Editorial crearEditorial() throws Exception {

        try {
            Editorial editorial = new Editorial();
            System.out.println("Ingrese el nombre de la editorial");
            editorial.setNombreEditorial(leer.next());
            System.out.println("EDITORIAL AGREGADA CORRECTAMENTE");
            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();
            return editorial;
        } catch (Exception e) {
            throw new Exception("no se pudo crear libro");
        }

    }

    public static void modificarEditorial() {
        System.out.println("Ingrese el ID del Editorial a modificar");
        Editorial editorial = em.find(Editorial.class, leer.nextInt());
        System.out.println("Ingrese el nuevo nombre de Editorial");
        editorial.setNombreEditorial(leer.next());

        em.getTransaction().begin();
        em.merge(editorial); //modifica
        em.getTransaction().commit();
        System.out.println("LA EDITORIAL HA SIDO MODIFICADA");
    }

    public static void eliminarEditorial() {

        try {
            System.out.println("Ingrese el ID de la editorial que desea borrar");
            Editorial editorial = em.find(Editorial.class, leer.nextInt());
            if (editorial != null) {
                System.out.println("EDITORIAL " + editorial.getNombreEditorial() + " BORRADA EXITOSAMENTE");
            } else {
                System.out.println("EDITORIAL NO ENCONTRADA");
            }
            em.getTransaction().begin();
            em.remove(editorial); //borra
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }

    public static void DarAltaoBajaEditorial() {

        System.out.println("Daremos baja a una editorial");
        Editorial editorial = (Editorial) emf;

        if (editorial.getAlta() == false) {
            editorial.setAlta(Boolean.FALSE);
        } else {
            editorial.setAlta(Boolean.TRUE);
        }

    }

}
