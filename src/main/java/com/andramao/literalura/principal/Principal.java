package com.andramao.literalura.principal;

import com.andramao.literalura.DTO.DatosAutor;
import com.andramao.literalura.DTO.DatosLibro;
import com.andramao.literalura.DTO.DatosRespuesta;
import com.andramao.literalura.model.Autor;
import com.andramao.literalura.model.Libro;
import com.andramao.literalura.repository.AutorRepository;
import com.andramao.literalura.repository.LibroRepository;
import com.andramao.literalura.service.ConsumoAPI;
import com.andramao.literalura.service.ConversorDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner lectura = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConversorDatos conversor = new ConversorDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private LibroRepository libroRepo;
    private AutorRepository autorRepo;

    public Principal(LibroRepository lRepo, AutorRepository aRepo) {
        this.libroRepo = lRepo;
        this.autorRepo = aRepo;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            System.out.println("""
                \n--- CATALOGO LITERALURA ---
                1- Buscar libro por titulo
                2- Listar libros registrados
                3- Listar autores registrados
                4- Autores vivos en determinado año
                5- Listar libros por idioma
                6- Ver Top 10 libros
                7- Buscar autor por nombre
                0- Salir
                """);
            try {
                opcion = lectura.nextInt();
                lectura.nextLine();
                switch (opcion) {
                    case 1 -> buscarLibroWeb();
                    case 2 -> listarLibros();
                    case 3 -> listarAutores();
                    case 4 -> buscarAutoresVivos();
                    case 5 -> estadísticasPorIdioma();
                    case 6 -> mostrarTop10();
                    case 7 -> buscarAutorPorNombre();
                    case 0 -> System.out.println("Cerrando...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Entrada no válida.");
                lectura.nextLine();
            }
        }
    }

    private void buscarLibroWeb() {
        System.out.println("Ingrese el título del libro:");
        var nombreLibro = lectura.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        var datosBusqueda = conversor.convertirDatos(json, DatosRespuesta.class);

        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream().findFirst();

        if (libroBuscado.isPresent()) {
            DatosLibro datosLibro = libroBuscado.get();
            DatosAutor datosAutor = datosLibro.autor().get(0);


            // 1. Buscamos al autor (ahora devuelve una lista)
            List<Autor> autoresEncontrados = autorRepo.findByNombreContainsIgnoreCase(datosAutor.nombre());

            Autor autor;
            if (!autoresEncontrados.isEmpty()) {
                // Si la lista no está vacía, tomamos el autor que ya existe
                autor = autoresEncontrados.get(0);
            } else {
                // Si está vacía, creamos y guardamos el nuevo autor
                autor = autorRepo.save(new Autor(datosAutor));
            }

            // 2. Manejar el Libro
            if (libroRepo.findByTituloContainsIgnoreCase(datosLibro.titulo()).isPresent()) {
                System.out.println("El libro ya está registrado en la base de datos.");
            } else {
                Libro libro = new Libro(datosLibro);
                libro.setAutor(autor);
                libroRepo.save(libro);
                System.out.println("¡Libro guardado con éxito!");
                System.out.println(libro);
            }
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void listarLibros() {
        libroRepo.findAll().forEach(System.out::println);
    }

    //private void listarAutores() {
        //autorRepo.findAll().forEach(a -> System.out.println(a.getNombre()));
   //}

    private void listarAutores() {
        List<Autor> autores = autorRepo.findAll();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos.");
        } else {
            // Al imprimir 'a', Java llama automáticamente al toString() que modificamos arriba
            autores.forEach(System.out::println);
        }
    }

    private void buscarAutoresVivos() {
        System.out.println("Ingrese año:");
        var anio = lectura.nextInt();
        autorRepo.buscarAutoresVivosEnAnio(anio).forEach(System.out::println);
    }

    private void estadísticasPorIdioma() {
        System.out.println("""
            Ingrese el idioma que desea buscar:
            es - Español
            en - Inglés
            fr - Francés
            pt - Portugués
            """);
        var idiomaSeleccionado = lectura.nextLine().toLowerCase().trim();

        // 1. Validamos que el código tenga 2 letras y esté en nuestra lista permitida
        List<String> codigosValidos = List.of("es", "en", "fr", "pt");

        if (!codigosValidos.contains(idiomaSeleccionado)) {
            System.out.println("Error: Código de idioma no válido. Use 'es', 'en', 'fr' o 'pt'.");
            return; // Salimos del método sin consultar la BD
        }

        // 2. Si es válido, procedemos con la consulta
        List<Libro> librosPorIdioma = libroRepo.findByIdioma(idiomaSeleccionado);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros registrados en el idioma seleccionado.");
        } else {
            System.out.println("\n---------- LIBROS EN [" + idiomaSeleccionado.toUpperCase() + "] ----------");
            librosPorIdioma.forEach(System.out::println);
            System.out.println("-------------------------------------------");
            System.out.println("Total de libros encontrados: " + librosPorIdioma.size());
            System.out.println("-------------------------------------------\n");
        }
    }

    private void mostrarTop10() {
        libroRepo.findTop10ByOrderByNumeroDeDescargasDesc().forEach(l ->
                System.out.println(l.getTitulo() + " [" + l.getNumeroDeDescargas() + "]"));
    }

    private void buscarAutorPorNombre() {
        System.out.println("Escriba el nombre del autor que desea buscar:");
        var nombreAutor = lectura.nextLine();

        // Reutilizamos el método que ahora devuelve una lista
        List<Autor> autoresEncontrados = autorRepo.findByNombreContainsIgnoreCase(nombreAutor);

        if (autoresEncontrados.isEmpty()) {
            System.out.println("No se encontró ningún autor registrado con el nombre: " + nombreAutor);
        } else {
            System.out.println("\n--- RESULTADOS ENCONTRADOS ---");
            autoresEncontrados.forEach(a -> {
                System.out.println("Autor: " + a.getNombre());
                System.out.println("Año de nacimiento: " + a.getFechaDeNacimiento());
                // Mostramos los títulos de sus libros asociados
                String libros = a.getLibros().stream()
                        .map(Libro::getTitulo)
                        .collect(Collectors.joining(", "));
                System.out.println("Libros registrados: [ " + libros + " ]");
                System.out.println("-----------------------------");
            });
        }
    }
}
