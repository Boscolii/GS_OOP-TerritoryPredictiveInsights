package br.com.fiap.territory_predictive_insights.domain;

import java.util.List;
public abstract class EstrategiaPrevisao {
    public abstract double prever(List<Medicao> historico);
    protected double ultimaLeitura(List<Medicao> historico) { return historico.get(historico.size() - 1).getValor(); }
}
