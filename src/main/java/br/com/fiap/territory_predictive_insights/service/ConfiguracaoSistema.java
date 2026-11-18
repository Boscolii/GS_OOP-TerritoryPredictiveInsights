package br.com.fiap.territory_predictive_insights.service;

public final class ConfiguracaoSistema {
    private static final ConfiguracaoSistema INSTANCIA = new ConfiguracaoSistema();
    private double atencao = 50; private double alerta = 75; private double critico = 95;
    private ConfiguracaoSistema() { }
    public static ConfiguracaoSistema getInstancia() { return INSTANCIA; }
    public void configurar(double atencao, double alerta, double critico) {
        if (atencao < 0 || atencao >= alerta || alerta >= critico) throw new IllegalArgumentException("Limiares inválidos.");
        this.atencao = atencao; this.alerta = alerta; this.critico = critico;
    }
    public double getAtencao() { return atencao; } public double getAlerta() { return alerta; } public double getCritico() { return critico; }
}
