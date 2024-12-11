package com.github.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer birth_year;
    private Integer death_year;

    @Override
    public String toString() {
        return "id=" + id +
               ", nome='" + nome + '\'' +
               ", birth_year=" + birth_year +
               ", death_year=" + death_year;
    }

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
