package com.github.literalura.main;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.github.literalura.model.*;
import com.github.literalura.repository.AutorRepository;
import com.github.literalura.repository.LivroRepository;
import com.github.literalura.service.AutorService;
import com.github.literalura.service.ConsumoAPI;
import com.github.literalura.service.ConverteDados;
import com.github.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main {
    Scanner scanner = new Scanner(System.in);

    ConsumoAPI consumoAPI = new ConsumoAPI();

    ConverteDados converteDados = new ConverteDados();

    private String endereco = "https://gutendex.com/books/?search=";

    @Autowired
    private LivroService livroService;
    @Autowired
    private AutorService autorService;

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
            scanner.nextLine();
            switch (op) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
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

    private void listarAutoresRegistrados() {
        System.out.println(livroService.listarAutores());
    }

    private void listarLivrosRegistrados() {
        System.out.println(livroService.listarLivros());
    }

    private void buscarLivro() {
        System.out.println("Digite o nome do livro a ser buscado: ");
        String buscaLivro = scanner.nextLine().trim();
        scanner.nextLine();
        System.out.println("Busca" + buscaLivro);
        var json = consumoAPI.chamarAPI(endereco + buscaLivro.replace(" ", "+"));
        DadosBusca dadosBusca = converteDados.obterDados(json, DadosBusca.class);
        System.out.println(dadosBusca);
        if (dadosBusca.results() != null && !dadosBusca.results().isEmpty()) {
            for (DadosLivro dadosLivro : dadosBusca.results()) {
                System.out.println(dadosLivro);
                livroService.salvarLivro(dadosLivro);
            }
        }
    }


}