package com.alura.literalura.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.alura.literalura.model.Autor;

public interface AutorRepository extends CrudRepository<Autor, Long> {
    @Query("""
            SELECT a FROM autor a
            WHERE a.nacimiento <= :year
            AND (a.fallecimiento IS NULL OR a.fallecimiento >= :year)
            """)
    List<Autor> autoresVivos(Integer year);
}
