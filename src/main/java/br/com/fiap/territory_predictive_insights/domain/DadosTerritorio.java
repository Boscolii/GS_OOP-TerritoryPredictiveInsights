package br.com.fiap.territory_predictive_insights.domain;

import br.com.fiap.territory_predictive_insights.exception.DadosInvalidosException;

public class DadosTerritorio {
    private final String regiao;
    private final int populacao;
    private final String codigoAcesso;
    private double limiteAtencao;
    private double limiteAlerta;
    private double limiteCritico;
    public DadosTerritorio(String regiao, int populacao, String codigoAcesso) {
        if (populacao < 0) throw new DadosInvalidosException("População não pode ser negativa.");
        this.regiao = regiao; this.populacao = populacao; this.codigoAcesso = codigoAcesso;
    }
    public void alterarLimiares(String codigo, double atencao, double alerta, double critico) {
        if (!codigoAcesso.equals(codigo)) throw new DadosInvalidosException("Código de acesso inválido.");
        if (!Double.isFinite(atencao) || !Double.isFinite(alerta) || !Double.isFinite(critico) || atencao < 0 || atencao >= alerta || alerta >= critico) throw new DadosInvalidosException("Limiares devem ser finitos, não negativos e crescentes.");
        limiteAtencao = atencao; limiteAlerta = alerta; limiteCritico = critico;
    }
    public String getRegiao() { return regiao; } public int getPopulacao() { return populacao; }
    public double getLimiteAtencao() { return limiteAtencao; } public double getLimiteAlerta() { return limiteAlerta; } public double getLimiteCritico() { return limiteCritico; }
}
