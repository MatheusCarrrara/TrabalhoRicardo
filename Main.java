import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {


    static class Venda {
        Map<String, Integer> itens;
        double total;
        String formaPagamento;


        Venda(Map<String, Integer> itens, double total, String formaPagamento) {
            this.itens = itens;
            this.total = total;
            this.formaPagamento = formaPagamento;
        }


        @Override
        public String toString() {
            return String.format("Venda %d: R$%.2f | Forma de Pagamento: %s",
                    Main.contadorVenda++, total, formaPagamento);
        }
    }


    static int contadorVenda = 1;


    static final double PRECO_AGUA_COM_GAS = 5.0;
    static final double PRECO_AGUA_SEM_GAS = 5.0;
    static final double PRECO_PIPOCA = 15.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Venda> vendas = new ArrayList<>();

        while (true) {
            exibirMenu();
            int opcao = obterOpcao(scanner);

            switch (opcao) {
                case 1:
                    registrarVenda(scanner, vendas, "Água com Gás", PRECO_AGUA_COM_GAS);
                    break;
                case 2:
                    registrarVenda(scanner, vendas, "Água sem Gás", PRECO_AGUA_SEM_GAS);
                    break;
                case 3:
                    registrarVenda(scanner, vendas, "Pipocas", PRECO_PIPOCA);
                    break;
                case 4:
                    registrarMultiplo(scanner, vendas);
                    break;
                case 5:
                    consultarVendas(vendas);
                    break;
                case 6:
                    excluirVenda(scanner, vendas);
                    break;
                case 7:
                    exibirBalanco(vendas);  // Exibe o balanço
                    break;
                case 8:
                    if (finalizarPrograma(scanner)) return;
                    break;
                default:
                    System.out.println("Opção inválida! Digite um número de 1 a 8.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n=======================");
        System.out.println("Menu de Vendas");
        System.out.println("=======================");
        System.out.println("1 - Registrar Água com Gás vendidas");
        System.out.println("2 - Registrar Água sem Gás vendidas");
        System.out.println("3 - Registrar Pipocas vendidas");
        System.out.println("4 - Registrar múltiplos itens vendidos");
        System.out.println("5 - Consultar Vendas");
        System.out.println("6 - Excluir Venda");
        System.out.println("7 - Balanço");
        System.out.println("8 - Finalizar Programa");
        System.out.print("Escolha uma opção: ");
    }


    private static int obterOpcao(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida! Digite um número de 1 a 8.");
            return -1;
        }
    }


    private static void registrarVenda(Scanner scanner, ArrayList<Venda> vendas, String nomeProduto, double precoUnitario) {
        System.out.print("Quantidade de " + nomeProduto + " vendidas: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        String formaPagamento = escolherFormaPagamento(scanner);

        vendas.add(new Venda(criarItens(nomeProduto, quantidade), precoUnitario * quantidade, formaPagamento));
    }

    private static void registrarMultiplo(Scanner scanner, ArrayList<Venda> vendas) {
        System.out.println("\nRegistrar múltiplos itens vendidos:");

        Map<String, Integer> itens = new HashMap<>();
        double total = 0;


        while (true) {
            System.out.print("Escolha o produto (" +
                    "1 - Água com Gás, " +
                    "2 - Água sem Gás, " +
                    "3 - Pipocas) ou 0 para finalizar: ");
            int tipoProduto = scanner.nextInt();

            if (tipoProduto == 0) {
                break;
            }

            System.out.print("Quantidade vendida: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            double preco = 0.0;
            String nomeProduto = "";

            switch (tipoProduto) {
                case 1:
                    nomeProduto = "Água com Gás";
                    preco = PRECO_AGUA_COM_GAS;
                    break;
                case 2:
                    nomeProduto = "Água sem Gás";
                    preco = PRECO_AGUA_SEM_GAS;
                    break;
                case 3:
                    nomeProduto = "Pipocas";
                    preco = PRECO_PIPOCA;
                    break;
                default:
                    System.out.println("Opção inválida de produto.");
                    continue;
            }


            itens.put(nomeProduto, itens.getOrDefault(nomeProduto, 0) + quantidade);
            total += preco * quantidade;
        }


        String formaPagamento = escolherFormaPagamento(scanner);


        vendas.add(new Venda(itens, total, formaPagamento));
    }


    private static String escolherFormaPagamento(Scanner scanner) {
        System.out.println("\nEscolha a forma de pagamento:");
        System.out.println("1 - Dinheiro");
        System.out.println("2 - Pix");
        System.out.println("3 - Débito");
        System.out.println("4 - Crédito");
        System.out.print("Escolha uma opção: ");

        int opcaoPagamento = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoPagamento) {
            case 1: return "Dinheiro";
            case 2: return "Pix";
            case 3: return "Débito";
            case 4: return "Crédito";
            default:
                System.out.println("Opção inválida! Considerando como 'Dinheiro'.");
                return "Dinheiro";
        }
    }


    private static void consultarVendas(ArrayList<Venda> vendas) {
        System.out.println("\nVendas realizadas:");

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {

            for (int i = 0; i < vendas.size(); i++) {
                Venda venda = vendas.get(i);
                System.out.println("Venda " + (i + 1) + ": R$" + venda.total + " | Forma de Pagamento: " + venda.formaPagamento);

                StringBuilder resumo = new StringBuilder();
                for (Map.Entry<String, Integer> entry : venda.itens.entrySet()) {
                    String produto = entry.getKey();
                    int quantidade = entry.getValue();
                    if (produto.equals("Água com Gás")) {
                        resumo.append("AG: ").append(quantidade).append(" / ");
                    } else if (produto.equals("Água sem Gás")) {
                        resumo.append("ASG: ").append(quantidade).append(" / ");
                    } else if (produto.equals("Pipocas")) {
                        resumo.append("P: ").append(quantidade).append(" / ");
                    }
                }

                System.out.println("Itens vendidos: " + resumo.toString().replaceAll(" / $", ""));
            }
        }
    }


    private static void excluirVenda(Scanner scanner, ArrayList<Venda> vendas) {
        System.out.println("\nDigite o número da venda para excluir (1 para a primeira venda, 2 para a segunda, etc.): ");
        int indice = scanner.nextInt() - 1;
        scanner.nextLine();

        if (indice >= 0 && indice < vendas.size()) {
            vendas.remove(indice);
            System.out.println("Venda excluída com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }


    private static void exibirBalanco(ArrayList<Venda> vendas) {
        double totalDinheiro = 0, totalPix = 0, totalDebito = 0, totalCredito = 0;
        double totalGeral = 0;

        for (Venda venda : vendas) {
            double totalVenda = venda.total;
            totalGeral += totalVenda;

            switch (venda.formaPagamento) {
                case "Dinheiro":
                    totalDinheiro += totalVenda;
                    break;
                case "Pix":
                    totalPix += totalVenda;
                    break;
                case "Débito":
                    totalDebito += totalVenda;
                    break;
                case "Crédito":
                    totalCredito += totalVenda;
                    break;
            }
        }

        System.out.println("\nBalanço de Vendas:");
        System.out.printf("Total em Dinheiro: R$%.2f\n", totalDinheiro);
        System.out.printf("Total em Pix: R$%.2f\n", totalPix);
        System.out.printf("Total em Débito: R$%.2f\n", totalDebito);
        System.out.printf("Total em Crédito: R$%.2f\n", totalCredito);
        System.out.printf("Total Geral de Vendas: R$%.2f\n", totalGeral);
    }


    private static boolean finalizarPrograma(Scanner scanner) {
        System.out.print("\nVocê realmente deseja finalizar o programa? (S/N): ");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("S")) {
            System.out.println("Programa finalizado.");
            scanner.close();
            return true;
        }
        return false;
    }


    private static Map<String, Integer> criarItens(String nomeProduto, int quantidade) {
        Map<String, Integer> itens = new HashMap<>();
        itens.put(nomeProduto, quantidade);
        return itens;
}
}