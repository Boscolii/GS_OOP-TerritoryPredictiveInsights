package br.com.fiap.territory_predictive_insights.domain;
public class SensorNivelRio extends SensorBase { public double lerValor() { return gerar(0.3, 5); } public String retornarTipo() { return "NIVEL_RIO"; } }
