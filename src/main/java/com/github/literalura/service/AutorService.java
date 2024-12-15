package com.github.literalura.service;

import com.github.literalura.model.Autor;
import com.github.literalura.model.Livro;
import com.github.literalura.repository.AutorRepository;
import com.github.literalura.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;
}
