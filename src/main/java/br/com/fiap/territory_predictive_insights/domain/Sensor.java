package br.com.fiap.territory_predictive_insights.domain;

public interface Sensor {
    double lerValor();
    void verificarFuncionamento();
    String retornarTipo();
}
