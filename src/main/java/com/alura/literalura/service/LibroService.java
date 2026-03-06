package com.alura.literalura.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alura.literalura.dto.DatosLibro;
import com.alura.literalura.dto.DatosRespuesta;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroService {
    private final ConsumoApiService consumoApi;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @Transactional
    public void buscarLibroPorTitulo(String titulo) {

        try {
            String response = consumoApi.obtenerDatos(titulo);

            DatosRespuesta responsejson = mapper.readValue(response, DatosRespuesta.class);

            if (responsejson.resultados() == null || responsejson.resultados().isEmpty()) {
                System.out.println("No se encontraron resultados para: " + titulo);
                return;
            }
            DatosLibro datos = responsejson.resultados().getFirst();

            if (libroRepository.existsByTitulo(datos.titulo())) {
                System.out.println("No se puede añadir el mismo libro");
                return;
            }

            System.out.printf("""

                    📖 ----- LIBRO ENCONTRADO -----
                    Título    : %s
                    Autor     : %s
                    Idioma    : %s
                    Descargas : %.0f
                    -------------------------------
                    """, datos.titulo(), datos.autores().getFirst().nombre(),
                    datos.idiomas().getFirst(), datos.descargas());
            Autor autor = new Autor();
            autor.setNombre(datos.autores().getFirst().nombre());
            autor.setNacimiento(datos.autores().getFirst().nacimiento());
            autor.setFallecimiento(datos.autores().getFirst().fallecimiento());

            autorRepository.save(autor);

            Libro libro = new Libro();
            libro.setTitulo(datos.titulo());
            libro.setIdioma(datos.idiomas().getFirst());
            libro.setDescargas(datos.descargas());
            libro.setAutor(autor);

            libroRepository.save(libro);

            System.out.println("Guardado.");

        } catch (Exception e) {
            System.out.println("Error al buscar libro.");
        }
    }

    @Transactional(readOnly = true)
    public void listarLibros() {
        libroRepository.findAll()
                .forEach(l -> System.out.println(l.getTitulo()));
    }

    @Transactional(readOnly = true)
    public void listarAutores() {
        autorRepository.findAll()
                .forEach(a -> System.out.println(a.getNombre()));
    }

    @Transactional(readOnly = true)
    public void listarPorIdioma(String idioma) {
        var libros = libroRepository.findByIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("\nNo tenemos libros registrados en el idioma: '" + idioma + "'");
        } else {
            System.out.println("\n--- Libros en idioma '" + idioma + "' ---");
            libros.forEach(
                    l -> System.out.println("- " + l.getTitulo() + " (Autor: " + l.getAutor().getNombre() + ")"));
            System.out.println("----------------------------------\n");
        }
    }

    @Transactional(readOnly = true)
    public void autoresVivos(Integer year) {

        var autores = autorRepository.autoresVivos(year);

        if (autores.isEmpty()) {
            System.out.printf("No hay autores vivos en el año: %d\n", year);
        } else {
            System.out.println("\n--- Autores vivos en el año " + year + " ---");
            autores.forEach(a -> {
                String fallecimiento = (a.getFallecimiento() != null) ? a.getFallecimiento().toString() : "Actualidad";
                System.out.println("- " + a.getNombre() + " (" + a.getNacimiento() + " - " + fallecimiento + ")");
            });
            System.out.println("--------------------------------------\n");
        }
    }
}
