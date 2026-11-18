package br.com.fiap.territory_predictive_insights.domain;
import java.util.List;
public class PrevisaoComparacao extends EstrategiaPrevisao {
    public PrevisaoComparacao() { super(); }
    @Override public double prever(List<Medicao> historico) {
        if (historico.isEmpty()) throw new IllegalArgumentException("Histórico vazio.");
        double atual = super.ultimaLeitura(historico);
        return historico.size() < 2 ? atual : atual + (atual - historico.get(historico.size() - 2).getValor());
    }
}
