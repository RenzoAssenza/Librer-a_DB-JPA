/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria_Yenny.Servicios;

import static Libreria_Yenny.Servicios.ServicioLibro.leer;
import Libreria_Yenny.entidades.Autor;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author RENZO
 */
public class ServicioAutor {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("libreriaPU");
    static EntityManager em = emf.createEntityManager();
    static Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public static Autor crearAutor() {
        Autor a1 = new Autor();
        System.out.println("Ingrese el nombre del Autor");
        a1.setNombreAutor(leer.next());
        em.getTransaction().begin();
        em.persist(a1); //graba
        em.getTransaction().commit();
        System.out.println("EL AUTOR HA SIDO CREADO");
        return a1;

    }

    public static void modificarAutor() {

        System.out.println("Ingrese el ID del Autor a modificar");
        Autor a1 = em.find(Autor.class, leer.nextInt());
        System.out.println("Ingrese el nuevo nombre de Autor");
        a1.setNombreAutor(leer.next());

        em.getTransaction().begin();
        em.merge(a1); //modifica
        em.getTransaction().commit();
        System.out.println("EL AUTOR HA SIDO MODIFICADO");
    }

    public static void eliminarAutor() {

        System.out.println("Ingrese el ID del Autor a eliminar");
        Autor a1 = em.find(Autor.class, leer.nextInt());
        em.getTransaction().begin();
        em.remove(a1); //elimina
        em.getTransaction().commit();
        System.out.println("EL AUTOR HA SIDO ELIMINADO");
    }

    public static void DarAltaoBajaAutor() {

        Autor autor = em.find(Autor.class, leer.nextInt());

        if (autor.getAlta() == false) {
            autor.setAlta(Boolean.FALSE);
        } else {
            autor.setAlta(Boolean.TRUE);
        }

    }

    public static void buscarAutorPorNombre() {
        try {
           System.out.println("Ingrese el nombre del autor a buscar");
           String autor = leer.next();
          //List<Libro> libros = em.createQuery("SELECT l FROM Libro l JOIN l.autor a WHERE a.NOMBREAUTOR=:autor").setParameter("autor",autor).getResultList();
       
         List<Autor> autores = (List <Autor>)em.createQuery("SELECT a FROM Autor a WHERE a.nombreAutor LIKE :autor").setParameter("autor", autor).getResultList();
            
          for (Autor autore : autores) {
                System.out.println(autore);
            }
            
            
//            System.out.println("INGRESE EL ID DEL AUTOR A BUSCAR");
//            Integer nom = leer.nextInt();
//           // Autor autor = (Autor) em.createQuery("SELECT a FROM autor a WHERE a.NOMBREAUTOR LIKE : nom").setParameter("nom",nom);
//           Autor autor = em.find(Autor.class, nom);
//            System.out.println(autor);
        } catch (Exception e) {
            throw e;
        }
    }
}
