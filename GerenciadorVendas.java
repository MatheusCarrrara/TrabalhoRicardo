import java.util.ArrayList;
import java.util.List;

public class GerenciadorVendas {
    private List<Venda> vendas;
    private int idVenda;

    public GerenciadorVendas() {
        this.vendas = new ArrayList<>();
        this.idVenda = 1; // ID inicial
    }

    public String gerarSiglaVenda() {
        return "V" + String.format("%03d", idVenda++);
    }

    public void registrarVenda(Venda venda) {
        vendas.add(venda);
    }

    public void exibirVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }

    public void excluirVenda(String sigla) {
        boolean encontrou = false;
        for (Venda venda : vendas) {
            if (venda.getSigla().equals(sigla)) {
                vendas.remove(venda);
                System.out.println("Venda " + sigla + " excluída com sucesso!");
                encontrou = true;
                break;
            }
        }
        if (!encontrou) {
            System.out.println("Venda não encontrada.");
        }
    }

    public void exibirBalancoFinanceiro() {
        double totalDinheiro = 0;
        double totalPix = 0;
        double totalDebito = 0;
        double totalCredito = 0;

        for (Venda venda : vendas) {
            switch (venda.getPagamento()) {
                case 1:
                    totalDinheiro += venda.getValorTotal();
                    break;
                case 2:
                    totalPix += venda.getValorTotal();
                    break;
                case 3:
                    totalDebito += venda.getValorTotal();
                    break;
                case 4:
                    totalCredito += venda.getValorTotal();
                    break;
            }
        }

        System.out.println("Balanço Financeiro:");
        System.out.println("Dinheiro: " + totalDinheiro);
        System.out.println("Pix: " + totalPix);
        System.out.println("Débito: " + totalDebito);
        System.out.println("Crédito: " + totalCredito);
        System.out.println("Total: " + (totalDinheiro + totalPix + totalDebito + totalCredito));
    }
}