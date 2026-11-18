package br.com.fiap.territory_predictive_insights.service;
import br.com.fiap.territory_predictive_insights.exception.DadosInvalidosException;
import java.util.HashMap;
import java.util.Map;
public final class ConfiguracaoSistema {
    public record Limiares(double atencao, double alerta, double critico) { }
    private static final ConfiguracaoSistema INSTANCIA = new ConfiguracaoSistema();
    private final Map<String, Limiares> limites = new HashMap<>();
    private ConfiguracaoSistema() {
        limites.put("CHUVA", new Limiares(50, 75, 95));
        limites.put("NIVEL_RIO", new Limiares(2, 3, 4));
        limites.put("UMIDADE_SOLO", new Limiares(60, 80, 95));
    }
    public static ConfiguracaoSistema getInstancia() { return INSTANCIA; }
    public Limiares getLimiares(String tipo) {
        Limiares resultado = limites.get(tipo);
        if (resultado == null) throw new DadosInvalidosException("Sensor desconhecido: " + tipo);
        return resultado;
    }
    public void configurar(String tipo, double atencao, double alerta, double critico) {
        getLimiares(tipo);
        if (!Double.isFinite(atencao) || !Double.isFinite(alerta) || !Double.isFinite(critico) || atencao < 0 || atencao >= alerta || alerta >= critico)
            throw new DadosInvalidosException("Limiares devem ser finitos, não negativos e crescentes.");
        limites.put(tipo, new Limiares(atencao, alerta, critico));
    }
}
