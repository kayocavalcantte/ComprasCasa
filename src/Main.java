import Exceptions.EntradaInvalidaException;
import Exceptions.ErroDeArquivoException;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner prompt = new Scanner(System.in);
        boolean sair = false;
        ArrayList<Produtos> produtos = new ArrayList<>();
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        ArrayList<Automovel> automovels = new ArrayList<>();

        String arquivo = "dados.txt"; // Nome do arquivo para armazenamento

        while (!sair) {
            try {
                System.out.println("1 - Adicionar morador");
                System.out.println("2 - Adicionar produto comprado");
                System.out.println("3 - Adicionar carro comprado");
                System.out.println("4 - Exibir dados do arquivo");
                System.out.println("5 - Remover dados do arquivo");
                System.out.print("Escolha uma opção: ");

                if (!prompt.hasNextInt()) {
                    throw new EntradaInvalidaException("Opção inválida! Digite um número válido.");
                }
                int caso = prompt.nextInt();
                prompt.nextLine();

                switch (caso) {
                    case 1:
                        try {
                            System.out.println("Digite o nome do morador:");
                            String nomeMorador = prompt.nextLine();

                            System.out.println("Digite a idade do morador:");
                            if (!prompt.hasNextInt()) {
                                throw new EntradaInvalidaException("A idade deve ser um número inteiro.");
                            }
                            int idadeMorador = prompt.nextInt();
                            prompt.nextLine();

                            System.out.println("O morador comprou algo ?");
                            boolean comprou = prompt.nextBoolean();

                            Morador morador = new Morador(nomeMorador, idadeMorador, comprou);
                            pessoas.add(morador);
                            salvarDados(arquivo, "Morador: " + morador.toString());
                            prompt.nextLine();
                            System.out.println("Morador adicionado com sucesso!");
                            System.out.println(morador.comprouAlgo());
                            System.out.println("***********************\n");
                        } catch (EntradaInvalidaException e) {
                            System.out.println("Erro de entrada: " + e.getMessage());
                            prompt.nextLine(); // Limpa o buffer
                        }
                        break;

                    case 2:
                        try {
                            System.out.println("Produto de Limpeza(1), Alimentação(2) ou Eletrodoméstico(3)");
                            int resp = prompt.nextInt();
                            prompt.nextLine();

                            System.out.println("Digite o nome do produto:");
                            String nomeProd = prompt.nextLine();

                            System.out.println("Digite a quantidade do produto:");
                            if (!prompt.hasNextInt()) {
                                throw new EntradaInvalidaException("A quantidade deve ser um número inteiro.");
                            }
                            int quantProd = prompt.nextInt();
                            prompt.nextLine();

                            Produtos produto = null;
                            if (resp == 1) {
                                produto = new ProdutoLimpeza(nomeProd, quantProd);
                            } else if (resp == 2) {
                                produto = new ProdutoAlimentacao(nomeProd, quantProd);
                            } else if (resp == 3) {
                                produto = new ProdutoEletro(nomeProd, quantProd);
                            }

                            if (produto != null) {
                                produtos.add(produto);
                                salvarDados(arquivo, "Produto: " + produto.toString());
                                System.out.println("Produto adicionado com sucesso!");
                                System.out.println("***********************\n");
                            }
                        } catch (EntradaInvalidaException e) {
                            System.out.println("Erro de entrada: " + e.getMessage());
                            prompt.nextLine(); // Limpa o buffer
                        }
                        break;

                    case 3:
                        System.out.println("Qual o modelo do carro?");
                        String modCarro = prompt.nextLine();

                        System.out.println("Digite a marca do carro:");
                        String marcCarro = prompt.nextLine();

                        System.out.println("Digite a placa do carro:");
                        String placCarro = prompt.nextLine();

                        System.out.println("Digite o número de lugares do carro:");
                        if (!prompt.hasNextInt()) {
                            throw new EntradaInvalidaException("O número de lugares deve ser um número inteiro.");
                        }
                        int lugCarro = prompt.nextInt();
                        prompt.nextLine();

                        Carro carro = new Carro(marcCarro, modCarro, placCarro, lugCarro);
                        automovels.add(carro);
                        salvarDados(arquivo, "Carro: " + carro.toString());
                        System.out.println("Carro adicionado com sucesso!");
                        System.out.println(carro.tantosLugares());
                        System.out.println("***********************\n");
                        break;

                    case 4:
                        try {
                            exibirDados(arquivo);
                        } catch (ErroDeArquivoException e) {
                            System.out.println("Erro ao exibir dados: " + e.getMessage());
                        }
                        break;

                    case 5:
                        try {
                            limparArquivo(arquivo);
                            System.out.println("Dados removidos do arquivo com sucesso!");
                            System.out.println("***********************\n");
                        } catch (ErroDeArquivoException e) {
                            System.out.println("Erro ao limpar o arquivo: " + e.getMessage());
                        }
                        break;

                    default:
                        throw new EntradaInvalidaException("Opção inválida! Escolha um número entre 1 e 5.");
                }
            } catch (EntradaInvalidaException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }

            System.out.println("Deseja sair? (Digite 'sim' para sair ou 'não' para continuar)");
            String resposta = prompt.nextLine();
            if (resposta.equalsIgnoreCase("sim")) {
                sair = true;
            }
        }
    }

    public static void salvarDados(String arquivo, String dado) throws ErroDeArquivoException {
        try (FileWriter writer = new FileWriter(arquivo, true)) {
            writer.write(dado + System.lineSeparator());
        } catch (IOException e) {
            throw new ErroDeArquivoException("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static void exibirDados(String arquivo) throws ErroDeArquivoException {
        try (Scanner scanner = new Scanner(new File(arquivo))) {
            System.out.println("\nDados no arquivo:");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
                System.out.println("*************************************\n");
            }
        } catch (FileNotFoundException e) {
            throw new ErroDeArquivoException("Arquivo não encontrado: " + e.getMessage());
        }
    }

    public static void limparArquivo(String arquivo) throws ErroDeArquivoException {
        try (FileWriter writer = new FileWriter(arquivo)) {
            writer.write("");
        } catch (IOException e) {
            throw new ErroDeArquivoException("Erro ao limpar o arquivo: " + e.getMessage());
        }
    }
}








//import java.io.*;
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        Scanner prompt = new Scanner(System.in);
//        List<Pessoa> moradores = new ArrayList<>();
//        List<Produtos> produtos = new ArrayList<>();
//        List<Automovel> carros = new ArrayList<>();
//        Map<String, List<Produtos>> comprasPorMorador = new HashMap<>();
//
//        carregarDados(moradores, produtos, carros);
//
//        boolean sair = false;
//        while (!sair) {
//            System.out.println("\n1 - Adicionar morador");
//            System.out.println("2 - Adicionar produto");
//            System.out.println("3 - Adicionar automóvel");
//            System.out.println("4 - Sair e salvar dados");
//            int opcao = prompt.nextInt();
//            prompt.nextLine();
//
//            switch (opcao) {
//                case 1:
//                    System.out.print("Nome: ");
//                    String nome = prompt.nextLine();
//                    System.out.print("Idade: ");
//                    int idade = prompt.nextInt();
//                    prompt.nextLine(); // Limpa o buffer
//                    moradores.add(new Morador(nome, idade));
//                    break;
//
//
//                case 2:
//                    System.out.println("1 - Limpeza, 2 - Alimentação, 3 - Eletrodoméstico:");
//                    int tipo = prompt.nextInt();
//                    prompt.nextLine();
//                    System.out.print("Nome do produto: ");
//                    String nomeProd = prompt.nextLine();
//                    System.out.print("Quantidade: ");
//                    int quantidade = prompt.nextInt();
//
//                    Produtos produto = switch (tipo) {
//                        case 1 -> new ProdutoLimpeza(nomeProd, quantidade);
//                        case 2 -> new ProdutoAlimentacao(nomeProd, quantidade);
//                        case 3 -> new ProdutoEletro(nomeProd, quantidade);
//                        default -> null;
//                    };
//
//                    if (produto != null) {
//                        produtos.add(produto);
//                    }
//                    break;
//
//                case 3:
//                    System.out.print("Marca: ");
//                    String marca = prompt.nextLine();
//                    System.out.print("Modelo: ");
//                    String modelo = prompt.nextLine();
//                    System.out.print("Placa: ");
//                    String placa = prompt.nextLine();
//                    System.out.print("Lugares: ");
//                    int lugares = prompt.nextInt();
//
//                    carros.add(new Carro(marca, modelo, placa, lugares));
//                    break;
//
//                case 4:
//                    salvarDados(moradores, produtos, carros);
//                    sair = true;
//                    break;
//
//            }
//        }
//    }
//
//    public static void salvarDados(List<Pessoa> moradores, List<Produtos> produtos, List<Automovel> carros) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dados.txt"))) {
//            writer.write("Moradores:\n");
//            for (Pessoa p : moradores) {
//                writer.write(p.toString() + "\n");
//            }
//            writer.write("\nProdutos:\n");
//            for (Produtos p : produtos) {
//                writer.write(p.toString() + "\n");
//            }
//            writer.write("\nCarros:\n");
//            for (Automovel c : carros) {
//                writer.write(c.toString() + "\n");
//            }
//        }
//    }
//
//    public static void carregarDados(List<Pessoa> moradores, List<Produtos> produtos, List<Automovel> carros) throws IOException {
//        File file = new File("dados.txt");
//        if (!file.exists()) return;
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String linha;
//            while ((linha = reader.readLine()) != null) {
//                System.out.println(linha); // Simples exibição; melhorar conforme necessidade.
//            }
//        }
//    }
//}













//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner prompt = new Scanner(System.in);
//        boolean sair = false;
//        List<Produtos> produtos = new ArrayList<>();
//        List<Pessoa> pessoas = new ArrayList<>();
//        List<Automovel> automovels = new ArrayList<>();
//
//        while (!sair) {
//            System.out.println("1 - Adicionar morador");
//            System.out.println("2 - Adicionar produto comprado");
//            System.out.println("3 - Adicionar carro comprado");
//            System.out.print("Escolha uma opção: ");
//            int caso = prompt.nextInt();
//            prompt.nextLine();
//
//            switch (caso) {
//                case 1:
//                    System.out.println("Digite o nome do morador:");
//                    String nomeMorador = prompt.nextLine();
//
//                    System.out.println("Digite a idade do morador:");
//                    String idadeMorador = prompt.nextLine();
//
//                    Morador morador = new Morador(nomeMorador, idadeMorador);
//                    pessoas.add(morador);
//
//                    System.out.println("Morador adicionado com sucesso!");
//                    break;
//
//                case 2:
//                    System.out.println("Produto de Limpeza(1), Alimentação(2) ou Eletrodomestico(3)");
//                    int resp = prompt.nextInt();
//                    prompt.nextLine();
//
//                    System.out.println("Digite o nome do produto:");
//                    String nomeProd = prompt.nextLine();
//
//                    System.out.println("Digite a quantidade do produto:");
//                    int quantProd = prompt.nextInt();
//                    prompt.nextLine();
//                    if (resp == 1) {
//                        ProdutoLimpeza produtoLimpeza = new ProdutoLimpeza(nomeProd, quantProd);
//                        produtos.add(produtoLimpeza);
//                    } else if (resp == 2) {
//                        ProdutoAlimentacao produtoAlimentacao = new ProdutoAlimentacao(nomeProd, quantProd);
//                        produtos.add(produtoAlimentacao);
//                    } else if (resp == 3) {
//                        ProdutoEletro produtoEletro = new ProdutoEletro(nomeProd, quantProd);
//                        produtos.add(produtoEletro);
//                    }
//                    break;
//
//                case 3:
//                    System.out.println("Qual o modelo do carro ?");
//                    String modCarro = prompt.nextLine();
//                    prompt.nextLine();
//
//                    System.out.println("Digite o marca do carro");
//                    String marcCarro = prompt.nextLine();
//
//                    System.out.println("Digite a placa do carro");
//                    String placCarro = prompt.nextLine();
//
//                    System.out.println("Digite o numero de lugares do carro");
//                    int lugCarro = prompt.nextInt();
//
//                    Carro carro = new Carro(marcCarro, modCarro, placCarro, lugCarro);
//                    automovels.add(carro);
//
//
//                default:
//                    System.out.println("Opção inválida!");
//            }
//
//            System.out.println("Deseja sair? (Digite 'sim' para sair ou 'não' para continuar)");
//            String resposta = prompt.nextLine();
//            if (resposta.equalsIgnoreCase("sim")) {
//                sair = true;
//            }
//        }
//
//        System.out.println("\nLista de moradores cadastrados:");
//        for (Pessoa pessoa : pessoas) {
//            System.out.println(pessoa.toString());
//        }
//
//        System.out.println("\nCarros cadastrados:");
//        for (Automovel automovel: automovels) {
//            System.out.println(automovel.toString());
//        }
//
//        System.out.println("\nProdutos cadastrados por categoria:");
//
//        System.out.println("Eletrodomésticos:");
//        for (Produtos produto : produtos) {
//            if (produto instanceof ProdutoEletro) {
//                System.out.println(produto.toString());
//            }
//        }
//
//        System.out.println("\nAlimentação:");
//        for (Produtos produto : produtos) {
//            if (produto instanceof ProdutoAlimentacao) {
//                System.out.println(produto.toString());
//            }
//        }
//
//        System.out.println("\nLimpeza:");
//        for (Produtos produto : produtos) {
//            if (produto instanceof ProdutoLimpeza) {
//                System.out.println(produto.toString());
//            }
//        }
//
//    }
//}
