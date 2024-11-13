import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner prompt = new Scanner(System.in);
        boolean sair = false;
        List<Produtos> produtos = new ArrayList<>();
        List<Pessoa> pessoas = new ArrayList<>();

        while (!sair) {
            System.out.println("1 - Adicionar morador");
            System.out.println("2 - Adicionar produto comprado");
            System.out.print("Escolha uma opção: ");
            int caso = prompt.nextInt();
            prompt.nextLine();

            switch (caso) {
                case 1:
                    System.out.println("Digite o nome do morador:");
                    String nomeMorador = prompt.nextLine();

                    System.out.println("Digite a idade do morador:");
                    String idadeMorador = prompt.nextLine();

                    Morador morador = new Morador(nomeMorador, idadeMorador);
                    pessoas.add(morador);

                    System.out.println("Morador adicionado com sucesso!");
                    break;

                case 2:
                    System.out.println("Produto de Limpeza(1), Alimentação(2) ou Eletrodomestico(3)");
                    int resp = prompt.nextInt();
                    prompt.nextLine();

                    System.out.println("Digite o nome do produto:");
                    String nomeProd = prompt.nextLine();

                    System.out.println("Digite a quantidade do produto:");
                    int quantProd = prompt.nextInt();
                    prompt.nextLine();
                    if (resp == 1) {
                        ProdutoLimpeza produtoLimpeza = new ProdutoLimpeza(nomeProd, quantProd);
                        produtos.add(produtoLimpeza);
                    } else if (resp == 2) {
                        ProdutoAlimentacao produtoAlimentacao = new ProdutoAlimentacao(nomeProd, quantProd);
                        produtos.add(produtoAlimentacao);
                    } else if (resp == 3) {
                        ProdutoEletro produtoEletro = new ProdutoEletro(nomeProd, quantProd);
                        produtos.add(produtoEletro);
                    }
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

            System.out.println("Deseja sair? (Digite 'sim' para sair ou 'não' para continuar)");
            String resposta = prompt.nextLine();
            if (resposta.equalsIgnoreCase("sim")) {
                sair = true;
            }
        }

        System.out.println("Lista de moradores cadastrados:");
        for (Pessoa pessoa : pessoas) {
            System.out.println("Nome: " + pessoa.getNome() + ", Idade: " + pessoa.getIdade());
        }

        System.out.println("\nProdutos cadastrados por categoria:");

        System.out.println("Eletrodomésticos:");
        for (Produtos produto : produtos) {
            if (produto instanceof ProdutoEletro) {
                System.out.println("Nome: " + produto.getNome() + ", Quantidade: " + produto.getQuantidade());
            }
        }

        System.out.println("\nAlimentação:");
        for (Produtos produto : produtos) {
            if (produto instanceof ProdutoAlimentacao) {
                System.out.println("Nome: " + produto.getNome() + ", Quantidade: " + produto.getQuantidade());
            }
        }

        System.out.println("\nLimpeza:");
        for (Produtos produto : produtos) {
            if (produto instanceof ProdutoLimpeza) {
                System.out.println("Nome: " + produto.getNome() + ", Quantidade: " + produto.getQuantidade());
            }
        }
    }
}
