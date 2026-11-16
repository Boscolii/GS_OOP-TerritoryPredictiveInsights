package br.com.fiap.territory_predictive_insights.domain;

public abstract class ComponenteTerritorial {
    private final Long id;
    private final String nome;
    private final String regiao;
    private boolean ativo;

    protected ComponenteTerritorial(Long id, String nome, String regiao, boolean ativo) {
        this.id = id; this.nome = nome; this.regiao = regiao; this.ativo = ativo;
    }
    public abstract String verificarStatus();
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getRegiao() { return regiao; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
