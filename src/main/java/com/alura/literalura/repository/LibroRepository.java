package com.alura.literalura.repository;

import org.springframework.data.repository.CrudRepository;
import com.alura.literalura.model.Libro;
import java.util.List;

public interface LibroRepository extends CrudRepository<Libro, Long> {
    List<Libro> findByIdioma(String idioma);

    boolean existsByTitulo(String titulo);
}
