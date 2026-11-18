package br.com.fiap.territory_predictive_insights.service;

import br.com.fiap.territory_predictive_insights.domain.*;
import java.util.Map;

public class FabricaPrevisao {
    private static final Map<String, EstrategiaPrevisao> ESTRATEGIAS = Map.of("MEDIA", new PrevisaoMediaMovel(), "COMPARACAO", new PrevisaoComparacao());
    public EstrategiaPrevisao criar(String nome) {
        EstrategiaPrevisao estrategia = ESTRATEGIAS.get(nome.toUpperCase());
        if (estrategia == null) throw new IllegalArgumentException("Estratégia inválida: " + nome);
        return estrategia;
    }
}
