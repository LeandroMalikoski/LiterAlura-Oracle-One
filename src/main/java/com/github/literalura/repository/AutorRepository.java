package com.github.literalura.repository;

import com.github.literalura.model.Autor;
import com.github.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    String existsByName(String name);

    @Query("SELECT a FROM Autor a WHERE a.name = :name AND a.birth_year = :birthYear AND a.death_year = :deathYear")
    Optional<Autor> findByNameAndBirthYearAndDeathYear(@Param("name") String name, @Param("birthYear") Integer birthYear, @Param("deathYear") Integer deathYear);

    @Query("SELECT l FROM Livro l JOIN l.autores a WHERE a.id = :autorId")
    List<Livro> findLivrosByAutorId(@Param("autorId") Long autorId);

    Optional<Autor> findByName(String autorNome);

    @Query("SELECT a FROM Autor a WHERE a.birth_year <= :ano AND (a.death_year >= :ano OR a.death_year IS NULL)")
    List<Autor> findByAge(int ano);
}
