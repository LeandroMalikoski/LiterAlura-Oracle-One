package com.github.literalura.repository;

import com.github.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    boolean existsByTitulo(String titulo);
}
