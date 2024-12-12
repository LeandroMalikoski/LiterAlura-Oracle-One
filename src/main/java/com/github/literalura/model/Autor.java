package com.github.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nome;
    private Integer birth_year;
    private Integer death_year;

    public Autor(DadosBusca dadosBusca) {
        if (dadosBusca.results() != null && !dadosBusca.results().isEmpty()) {
            DadosLivro dadosLivro = dadosBusca.results().get(0);
            this.nome = dadosLivro.autor().get(0).nome();
            this.birth_year = dadosLivro.autor().get(0).nascimento();
            this.death_year = dadosLivro.autor().get(0).morte();
        } else {
            throw new IllegalArgumentException("O array 'results' está vazio ou é nulo.");
        }
    }



    @Override
    public String toString() {
        return "nome='" + nome + '\'' +
               ", birth_year=" + birth_year +
               ", death_year=" + death_year;
    }

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<Livro>();

    public Autor() {}

    public Autor(Long id, String nome, Integer birth_year, Integer death_year) {
        this.id = id;
        this.nome = nome;
        this.birth_year = birth_year;
        this.death_year = death_year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }
}
