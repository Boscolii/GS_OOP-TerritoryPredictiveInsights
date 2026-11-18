package br.com.fiap.territory_predictive_insights;
import br.com.fiap.territory_predictive_insights.domain.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class PrevisaoTest {
    @Test void deveCalcularMediaMovel() { assertEquals(20, new PrevisaoMediaMovel().prever(List.of(new Medicao("CHUVA", 10), new Medicao("CHUVA", 20), new Medicao("CHUVA", 30)))); }
    @Test void deveProjetarTendenciaPorComparacao() { assertEquals(20, new PrevisaoComparacao().prever(List.of(new Medicao("CHUVA", 10), new Medicao("CHUVA", 15)))); }
}
