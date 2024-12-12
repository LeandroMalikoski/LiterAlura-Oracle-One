package com.github.literalura.main;

import com.github.literalura.model.*;
import com.github.literalura.repository.AutorRepository;
import com.github.literalura.repository.LivroRepository;
import com.github.literalura.service.ConsumoAPI;
import com.github.literalura.service.ConverteDados;

import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);

    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private ConverteDados converteDados = new ConverteDados();

    private String endereco = "http://gutendex.com/books/?search=";

    LivroRepository repositoryLivro;

    AutorRepository repositoryAutor;

    public Main(LivroRepository repository, AutorRepository repositoryAutor) {
        this.repositoryLivro = repository;
        this.repositoryAutor = repositoryAutor;
    }

    public void Menu() {
        int op = -1;
        while (op != 0) {
            System.out.printf("""
                                        
                                        
                       
                    *********** Menu ***********
                                    
                    1 - Buscar livro pelo titulo
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    0 - Sair
                                    
                    Insira a opção desejada:            
                    """);
            op = scanner.nextInt();
            switch (op) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    //metodo
                    break;
                case 3:
                    //metodo
                    break;
                case 4:
                    //metodo
                    break;
                case 5:
                    //metodo
                    break;
            }
        }
    }

    private void buscarLivro() {
//      System.out.println("Digite o nome do livro a ser buscado: ");
//      String buscaLivro = scanner.nextLine();
//      scanner.nextLine();
        endereco += "dom+casmurro";
        var json = consumoAPI.chamarAPI(endereco.replace(" ", "+"));
        DadosBusca dadosBusca = converteDados.obterDados(json, DadosBusca.class);
        Livro livro = new Livro(dadosBusca);
        Autor autor = new Autor(dadosBusca);
        System.out.println(autor);
        if (repositoryLivro.existsByTitulo(livro.getTitulo())) {
            System.out.println("**** " + livro.getTitulo() + " ****");
            System.out.println("Autor = " + autor.getNome());
            System.out.println("Idioma = " + livro.getLinguagem());
            System.out.println("Downloads = " + livro.getDownloads());
            System.out.println("Este livro já está cadastrado");
        } else {
            System.out.println("**** " + livro.getTitulo() + " ****");
            System.out.println("Autor = " + autor.getNome());
            System.out.println("Idioma = " + livro.getLinguagem());
            System.out.println("Downloads = " + livro.getDownloads());
            repositoryAutor.save(autor);
            livro.setAutor(autor);
            repositoryLivro.save(livro);
        }
    }
}