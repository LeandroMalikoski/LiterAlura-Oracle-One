package com.github.literalura.repository;

import com.github.literalura.model.Autor;
import com.github.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    boolean existsByTitulo(String titulo);

    @Query("SELECT l FROM Livro l JOIN FETCH l.autores WHERE l.titulo LIKE %:id%")
    List<Livro> findByTitulo(@Param("id") Long id);

    @Query("SELECT l FROM Livro l JOIN l.autores a WHERE a.id = :autorId")
    List<Livro> findLivrosByAutorId(@Param("autorId") Long autorId);

    @Query("SELECT l FROM Livro l WHERE l.linguagem LIKE %:idioma%")
    List<Livro> findByLinguagem(String idioma);

    @Query("SELECT l FROM Livro l ORDER BY l.downloads DESC")
    List<Livro> findByDownload(Integer download);
}
