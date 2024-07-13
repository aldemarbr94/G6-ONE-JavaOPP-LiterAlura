package com.aluracursos.literaluraSpring.principal;

import com.aluracursos.literaluraSpring.model.Autores;
import com.aluracursos.literaluraSpring.model.DatosApi;
import com.aluracursos.literaluraSpring.model.DatosLibros;
import com.aluracursos.literaluraSpring.model.Libro;
import com.aluracursos.literaluraSpring.repository.LibroRepository;
import com.aluracursos.literaluraSpring.service.ConsumoApi;
import com.aluracursos.literaluraSpring.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private static final String URL_API_BASE = "https://gutendex.com/books/";
    private static final String URL_API_INTERMEDIO = "?search=";
    ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository repositorio;
    private List<Libro> libros;
    private List<Libro> librosAutoresRegistrados;
    private List<Autores> autores;
    private List<Libro> librosAutoresVivos;
    private List<Libro> lenguajesLibros;




    //Constructor
    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }


    public void muestraElMenu() {

        System.out.println("\n============================== LITERALURA ==============================");

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n------------------------------------------------------------------------
                    
                    ---> Por favor, elija una opcion a traves del numero [1,2,3,4,5 o 0]:
                    
                    1 - Buscar libro por titulo desde la WEB
                    2 - Listar libros registrados en la BBDD
                    3 - Listar autores registrados en la BBDD
                    4 - Listar autores vivos en un determinado año (desde la BBDD)
                    5 - Listar libros por idioma (desde la BBDD)
                                        
                    0 - Salir
                    
                    ------------------------------------------------------------------------
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    mostrarAutoresVivos();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("\nCerrando la aplicacion...");
                    break;
                default:
                    System.out.println("""
                                        \n*****************************
                                        ****** OPCION INVALIDA ******
                                        *****************************
                                        """);
            }

        }

    }



    //Opcion 1 - Buscar libro por titulo desde la WEB
    private void buscarLibroWeb() {

        mostrarLibrosRegistrados();

        System.out.println("\n---> Por favor, escribe el nombre del libro que deseas buscar");
        var nombreLibroUsuario = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_API_BASE + URL_API_INTERMEDIO + nombreLibroUsuario.replace(" ", "+") );
        DatosApi datosApi = conversor.obtenerDatos(json, DatosApi.class);


        Optional<DatosLibros> libroEncontrado = datosApi.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibroUsuario.toUpperCase()))
                .findFirst();

        if (libroEncontrado.isPresent()){
            System.out.println("\n***** LIBRO ENCONTRADO *****");

            Libro libro = datosApi.libros().stream()
                    .map(d -> new Libro(d))
                    .findFirst()
                    .orElse(null);
            repositorio.save(libro);
            System.out.println(libro.toString());

            System.out.println("****************************");

        }else{
            System.out.println("\n***** LIBRO NO ENCONTRADO *****");
        }
    }



    //Opcion 2 - Mostrar libros guardados en la BBDD
    private void mostrarLibrosRegistrados() {
        libros = repositorio.findAll();

        System.out.println("\n***** Libros Registrados en la BBDD *****");

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);

        System.out.println("\n***************************************");
    }



    //Opcion 3 - Listar autores registrados en la BBDD
    private void mostrarAutoresRegistrados() {

        libros = repositorio.autoresRegistrados();

        autores = libros.stream()
                .map( l -> new Autores( l, repositorio.librosRegistradosPorAutor(l.getAutor()) ) )
                .collect(Collectors.toList());

        System.out.println("\n***** Autores Registrados en la BBDD *****");
        autores.forEach(System.out::println);
        System.out.println("\n******************************************");
    }




    //Opcion 4 - Listar autores vivos en un determinado año (desde la BBDD)
    private void mostrarAutoresVivos() {

        System.out.println("\n---> Por favor, ingrese el año en el que desea conocer cuales autores estaban vivos");
        var anioAutorVivoUsuario = teclado.nextInt();

        librosAutoresVivos = repositorio.autoresVivos(anioAutorVivoUsuario);

        autores = librosAutoresVivos.stream()
                .map( l -> new Autores( l, repositorio.librosRegistradosPorAutor(l.getAutor()) ) )
                .collect(Collectors.toList());

        System.out.println("\n************ Autores Vivos ************");
        autores.forEach(System.out::println);
        System.out.println("\n***************************************");

    }



    //Opcion 5 - Listar libros por idioma (desde la BBDD)
    private void mostrarLibrosPorIdioma() {

        System.out.println("""
                            \nIDIOMAS
                            es - español
                            en - ingles
                            fr - frances
                            pt - portugues
                            ---> Por favor, ingrese el idioma del libro [es, en, fr, pt]""");
        var lenguajeLibroUsuario = teclado.nextLine();

        lenguajesLibros = repositorio.lenguajeLibros(lenguajeLibroUsuario);

        autores = lenguajesLibros.stream()
                .map( l -> new Autores( l, repositorio.librosRegistradosPorAutor(l.getAutor()) ) )
                .collect(Collectors.toList());

        System.out.println("\n************ Libros ************");
        autores.forEach(System.out::println);
        System.out.println("\n***************************************");

    }


}

