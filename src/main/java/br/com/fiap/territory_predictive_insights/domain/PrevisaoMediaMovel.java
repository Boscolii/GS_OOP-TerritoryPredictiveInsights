package br.com.fiap.territory_predictive_insights.domain;
import java.util.List;
public class PrevisaoMediaMovel extends EstrategiaPrevisao {
    public PrevisaoMediaMovel() { super(); }
    @Override public double prever(List<Medicao> historico) {
        if (historico.isEmpty()) throw new IllegalArgumentException("Histórico vazio.");
        int inicio = Math.max(0, historico.size() - 5);
        return historico.subList(inicio, historico.size()).stream().mapToDouble(Medicao::getValor).average().orElseThrow();
    }
}
