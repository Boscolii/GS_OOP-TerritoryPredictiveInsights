package br.com.fiap.territory_predictive_insights.domain;
public class SensorUmidadeSolo extends SensorBase { public double lerValor() { return gerar(0, 100); } public String retornarTipo() { return "UMIDADE_SOLO"; } }
