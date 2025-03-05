import java.util.Scanner;

public class InterfaceUsuario {
    private GerenciadorVendas gerenciadorVendas;
    private Scanner scanner;

    public InterfaceUsuario() {
        this.gerenciadorVendas = new GerenciadorVendas();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            processarOpcao(opcao);
        } while (opcao != 5);
    }

    private void exibirMenu() {
        System.out.println("1. Registrar venda");
        System.out.println("2. Consultar vendas");
        System.out.println("3. Excluir venda");
        System.out.println("4. Exibir balanço financeiro");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                registrarVenda();
                break;
            case 2:
                consultarVendas();
                break;
            case 3:
                excluirVenda();
                break;
            case 4:
                exibirBalanco();
                break;
            case 5:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void registrarVenda() {
        System.out.print("Digite a quantidade de itens: ");
        int quantidadeItens = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        double valorTotal = 0;
        Venda venda = new Venda(gerenciadorVendas.gerarSiglaVenda());

        for (int i = 0; i < quantidadeItens; i++) {
            System.out.print("Digite o tipo de produto (1 - Água com gás, 2 - Água sem gás, 3 - Pipoca): ");
            int tipo = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            System.out.print("Digite o valor do item: ");
            double valor = scanner.nextDouble();
            scanner.nextLine(); // Consumir a nova linha

            venda.adicionarItem(tipo, valor);
            valorTotal += valor;
        }

        System.out.print("Escolha a forma de pagamento (1 - Dinheiro, 2 - Pix, 3 - Débito, 4 - Crédito): ");
        int pagamento = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        venda.setPagamento(pagamento);
        gerenciadorVendas.registrarVenda(venda);
        System.out.println("Venda registrada com sucesso! Valor total: R$ " + valorTotal);
    }

    private void consultarVendas() {
        gerenciadorVendas.exibirVendas();
    }

    private void excluirVenda() {
        System.out.print("Digite a sigla da venda para excluir: ");
        String sigla = scanner.nextLine();
        gerenciadorVendas.excluirVenda(sigla);
    }

    private void exibirBalanco() {
        gerenciadorVendas.exibirBalancoFinanceiro();
    }
}