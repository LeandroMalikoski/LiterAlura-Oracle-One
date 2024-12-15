package com.github.literalura.main;

import com.github.literalura.model.*;
import com.github.literalura.service.AutorService;
import com.github.literalura.service.ConsumoAPI;
import com.github.literalura.service.ConverteDados;
import com.github.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                    6 - Listar 10 livros mais baixados
                    7 - Listar estatísticas de downloads do banco de dados 
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
                    listarAutoresVivosEmDeterminadoAno();
                    break;
                case 5:
                    listarLivrosEmDeterminadoIdioma();
                    break;
                case 6:
                    listarTop10();
                    break;
                case 7:
                    listarEstatisticas();
                    break;
            }
        }
    }

    private void listarEstatisticas() {
        List<Livro> livros = livroService.BuscarEstatisticas();
        DoubleSummaryStatistics stats = livros.stream()
                .collect(Collectors.summarizingDouble(livro -> livro.getDownloads()));
        System.out.println("Quantia de livros   = " + stats.getCount());
        System.out.println("Soma de downloads   = " + stats.getSum());
        System.out.println("Média de downloads  = " + stats.getAverage());
        System.out.println("Livro menos baixado = " + stats.getMin());
        System.out.println("Livro mais baixado  = " + stats.getMax());
    }

    private void listarTop10() {
        System.out.println(livroService.listarTop10BancoDados(10));
    }

    private void listarLivrosEmDeterminadoIdioma() {
        System.out.println("Digite o idioma escolhido: ");
        System.out.println("es = espanhol");
        System.out.println("en = inglês");
        System.out.println("fr = francês");
        System.out.println("pt = português");
        String idioma = scanner.nextLine();
        System.out.println(livroService.listarlivrosPorIdioma(idioma));
    }

    private void listarAutoresVivosEmDeterminadoAno() {
        System.out.println("Digite o ano escolhido: ");
        var ano = scanner.nextInt();
        System.out.println(autorService.listarAutoresVivosAno(ano));
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