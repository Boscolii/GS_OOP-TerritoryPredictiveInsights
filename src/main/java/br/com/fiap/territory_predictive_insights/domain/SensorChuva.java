package br.com.fiap.territory_predictive_insights.domain;
public class SensorChuva extends SensorBase { public double lerValor() { return gerar(0, 120); } public String retornarTipo() { return "CHUVA"; } }
