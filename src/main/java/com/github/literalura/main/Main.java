package com.github.literalura.main;

import com.github.literalura.model.DadosLivro;
import com.github.literalura.service.ConsumoAPI;
import com.github.literalura.service.ConverteDados;

import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);

    private ConsumoAPI consumoAPI = new ConsumoAPI();

    private ConverteDados converteDados = new ConverteDados();



    public void Menu(){
        int op = -1;
        while(op != 0) {
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
        System.out.println("Digite o nome do livro a ser buscado: ");
        String buscaLivro = scanner.nextLine();
        scanner.nextLine();
        var json = consumoAPI.chamarAPI(buscaLivro);
        System.out.println(json);
        DadosLivro livro = converteDados.obterDados(json, DadosLivro.class);
        System.out.println(livro);
    }
}
