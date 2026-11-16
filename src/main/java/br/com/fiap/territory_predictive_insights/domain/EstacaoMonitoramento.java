package br.com.fiap.territory_predictive_insights.domain;

public class EstacaoMonitoramento extends ComponenteTerritorial {
    public EstacaoMonitoramento(Long id, String nome, String regiao, boolean ativo) { super(id, nome, regiao, ativo); }
    @Override public String verificarStatus() { return isAtivo() ? "Estação operacional" : "Estação indisponível"; }
}
