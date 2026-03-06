package com.alura.literalura;

import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.alura.literalura.service.LibroService;

@Component
public class Main {
    private final LibroService libroService;
    private Scanner keyboard = new Scanner(System.in);

    public Main(LibroService libroService) {
        this.libroService = libroService;
    }

    public void mostrarMenu() {
        int option = -1;

        while (option != 0) {
            System.out.println("""
                    1 - Buscar libro por título
                    2 - Listar libros
                    3 - Listar autores
                    4 - Listar libros por idioma
                    5 - Autores vivos en un año
                    0 - Salir
                    """);

            option = keyboard.nextInt();
            keyboard.nextLine();

            switch (option) {
                case 1 -> buscarLibro();
                case 2 -> libroService.listarLibros();
                case 3 -> libroService.listarAutores();
                case 4 -> listarPorIdioma();
                case 5 -> autoresVivos();
                default -> System.out.println("Opción no valida");
            }
        }
    }

    private void buscarLibro() {
        System.out.println("Ingrese título:");
        String titulo = keyboard.nextLine();
        libroService.buscarLibroPorTitulo(titulo);
    }

    private void listarPorIdioma() {
        System.out.println("Ingrese idioma (ej: en, es):");
        boolean condition = true;
        while (condition) {
            String language = keyboard.nextLine();
            if (language.equals("en") || language.equals("es")) {
                condition = false;
                libroService.listarPorIdioma(language);
            } else {
                System.out.println("Error ingrese un idioma correcto (ej: en, es):");
            }
        }
    }

    private void autoresVivos() {
        System.out.println("Ingrese año:");
        try {
            Integer year = keyboard.nextInt();
            keyboard.nextLine();
            libroService.autoresVivos(year);
        } catch (Exception e) {
            System.out.println("Error: ingrese un año válido");
            keyboard.nextLine();
            autoresVivos();
        }
    }
}
