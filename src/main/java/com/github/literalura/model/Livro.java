package com.github.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String titulo;
    @Column(unique = true, nullable = false)
    private String autor;
    private String linguagem;
    private Long downloads;

    public Livro() {
    }

    @ManyToOne
    private Autor autores;

    public Livro(DadosBusca dadosBusca) {
        if (dadosBusca.results() != null && !dadosBusca.results().isEmpty()) {
            DadosLivro dadosLivro = dadosBusca.results().get(0);
            this.titulo = dadosLivro.titulo();
            this.autor = String.valueOf(dadosLivro.autor());
            this.linguagem = String.valueOf(dadosLivro.linguagem());
            this.downloads = dadosLivro.downloads();
        } else {
            throw new IllegalArgumentException("O array 'results' está vazio ou é nulo.");
        }
    }

    @Override
    public String toString() {
        return "*********** " + titulo + " ***********" +
               "\n Autor = " + autor +
               "\n Idioma = " + linguagem +
               "\n Downloads = " + downloads;
    }

    public Livro(String titulo, List<DadosAutor> autor, List<String> linguagem, Long downloads) {
    }

    public Livro(Long id, String titulo, String autor, String linguagem, Long downloads) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.linguagem = linguagem;
        this.downloads = downloads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = String.valueOf(autor);
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }
}
