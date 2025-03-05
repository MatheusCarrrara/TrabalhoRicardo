import java.util.ArrayList;
import java.util.List;

public class Venda {
    private String sigla;  // Sigla única para identificar a venda
    private List<ItemVenda> itens;
    private int pagamento;

    public Venda(String sigla) {
        this.sigla = sigla;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(int tipo, double valor) {
        this.itens.add(new ItemVenda(tipo, valor));
    }

    public String getSigla() {
        return sigla;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public double getValorTotal() {
        return itens.stream().mapToDouble(ItemVenda::getValor).sum();
    }

    public void setPagamento(int pagamento) {
        this.pagamento = pagamento;
    }

    public int getPagamento() {
        return pagamento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venda ").append(sigla).append(" - Itens: ");

        // Exibir todos os itens em uma linha
        for (int i = 0; i < itens.size(); i++) {
            sb.append(itens.get(i));
            if (i < itens.size() - 1) sb.append(", "); // Adiciona uma vírgula entre os itens, mas não no último
        }

        sb.append("\nTotal: R$ ").append(getValorTotal()).append(" | ");
        sb.append("Pagamento: ").append(getMetodoPagamento());

        return sb.toString();
    }

    private String getMetodoPagamento() {
        switch (pagamento) {
            case 1: return "Dinheiro";
            case 2: return "Pix";
            case 3: return "Débito";
            case 4: return "Crédito";
            default: return "Desconhecido";
        }
    }
}

class ItemVenda {
    private int tipo;
    private double valor;

    public ItemVenda(int tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        String tipoProduto = switch (tipo) {
            case 1 -> "Água com gás";
            case 2 -> "Água sem gás";
            case 3 -> "Pipoca";
            default -> "Desconhecido";
        };
        return tipoProduto + " - R$ " + valor;
    }
}