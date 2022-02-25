/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria_Yenny.Servicios;

import Libreria_Yenny.entidades.Autor;
import static Libreria_Yenny.entidades.Autor_.nombreAutor;

import Libreria_Yenny.entidades.Editorial;
import static Libreria_Yenny.entidades.Editorial_.nombreEditorial;
import Libreria_Yenny.entidades.Libro;
import static Libreria_Yenny.entidades.Libro_.id;
import static Libreria_Yenny.entidades.Libro_.titulo;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author RENZO
 */
public class ServicioLibro {

    //private LibroDAO renzoDAO; eese se encarga de guardar, modificar, eliminar etc 
    static Scanner leer = new Scanner(System.in).useDelimiter("\n");
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("libreriaPU");
    static EntityManager em = emf.createEntityManager();

    public static void crearLibro() {

        Libro lib1 = new Libro();
        Editorial editorial = new Editorial();
        Autor autor = new Autor();
        try {

            System.out.println("Ingrese el titulo del Libro");
            lib1.setTitulo(leer.next());
            System.out.print("Ingrese año de publicación: ");
            lib1.setAnio(leer.nextInt());
            System.out.print("Ingrese cantidad de ejemplares: ");
            lib1.setEjemplares(leer.nextInt());
            System.out.print("Ingrese NOMBRE del AUTOR: ");
            lib1.setAutor(ServicioAutor.crearAutor());
            System.out.print("Ingrese nombre de la EDITORIAL: ");
            lib1.setEditorial(ServicioEditorial.crearEditorial());
            em.getTransaction().begin();
            em.persist(lib1); //graba
            em.getTransaction().commit();
            System.out.println("EL LIBRO HA SIDO CREADO");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void modificarLibro() throws Exception {
        try {
            Libro libro = new Libro();
            System.out.println("Ingrese el ID del libro que desea modificar");
            libro = em.find(Libro.class, leer.nextLong());

            System.out.println("Ingrese el nombre del titulo");
            libro.setTitulo(leer.next());
            System.out.println("Ingrese año");
            libro.setAnio(leer.nextInt());
            System.out.println("Cantidad de ejemplares?");
            libro.setEjemplares(leer.nextInt());

            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();

        } catch (Exception e) {
            throw e;
        }
        System.out.println("EL LIBRO HA SIDO MODIFICADO");

    }

    public static void eliminarLibro() {

        try {
            System.out.println("Ingrese el ID del libro que desea borrar");
            Libro libro = em.find(Libro.class, leer.nextInt());

            if (libro != null) {
                System.out.println("LIBRO " + libro.getTitulo() + " BORRADO EXITOSAMENTE");
            } else {
                System.out.println("NO HAY LIBROS CON ESE ID");
            }

            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }

    }

    public static void DarAltaoBajaLibro() {
        System.out.println("Daremos alta o baja a un libro");
        System.out.println("INGRESE CODIGO DEL LIBRO QUE DESEA DAR DE BAJA");
        Libro libro = em.find(Libro.class, leer.nextInt());

        if (libro.isAlta() == false) {
            libro.setAlta(Boolean.FALSE);
        } else {
            libro.setAlta(Boolean.TRUE);
        }

    }

    public static void buscarLibroPorNombreAutor() {
        try {
            System.out.println("Ingrese el nombre del autor del cual desea encontrar libros");
            String autor = leer.next();
            //List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN l.autor a WHERE a.NOMBREAUTOR=:autor").setParameter("autor",autor).getResultList();

            List<Libro> libros = (List<Libro>) em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombreAutor LIKE :autor").setParameter("autor", autor).getResultList();

            for (Libro aux : libros) {
                System.out.println(aux);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buscarLibroPorNombreEditorial() {
        System.out.println("INGRESE EL NOMBRE DE LA EDITORIAL DEL LIBRO A BUSCAR");
        String editorial = leer.next();

        List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e WHERE e.nombreEditorial LIKE :editorial").setParameter("editorial", editorial).getResultList();

        if (editoriales.isEmpty()) {
            System.out.println("NO HAY LIBROS CON ESA EDITORIAL");
        } else {
            System.out.println(editoriales);
        }

    }

    public static void buscarLibroPorISBN() {

        System.out.println("INGRESE EL ISBN DEL LIBRO A BUSCAR");
        Libro libro = em.find(Libro.class, leer.nextLong());

        List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.id = :id").setParameter("isbn", id).getResultList();

        if (libros.isEmpty()) {
            System.out.println("NO HAY LIBROS CON ESE ISBN");
        }

        for (Libro i : libros) {
            System.out.println(i);
        }
    }

    public static void buscarLibroPorTitulo() {
        try {
            System.out.println("INGRESE EL TITULO DEL LIBRO A BUSCAR");
            String titulo = leer.next();
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo").setParameter("titulo", titulo).getResultList();

            if (libros.isEmpty()) {
                System.out.println("No se encontro el libro en la DB");
            } else {
                System.out.println(libros.get(0));
            }

        } catch (Exception e) {
            throw e;
        }
    }

    public static void verTodosLosLibros() {
        System.out.println("VEAMOS TODOS LOS LIBROS!");
        List<Libro> libros = em.createQuery("SELECT l FROM Libro l").getResultList();
        if (libros.isEmpty()) {
            System.out.println("NO HAY LIBROS");
        } else {
            System.out.println(libros);
        }
    }
}
