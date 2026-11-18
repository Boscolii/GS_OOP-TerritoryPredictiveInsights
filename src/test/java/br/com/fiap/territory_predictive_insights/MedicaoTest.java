package br.com.fiap.territory_predictive_insights;
import br.com.fiap.territory_predictive_insights.domain.Medicao;
import br.com.fiap.territory_predictive_insights.exception.DadosInvalidosException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class MedicaoTest {
    @Test void deveCriarMedicaoValida() { Medicao medicao = new Medicao("CHUVA", 10); assertEquals(10, medicao.getValor()); }
    @Test void deveRejeitarMedicaoNegativa() { assertThrows(DadosInvalidosException.class, () -> new Medicao("CHUVA", -1)); }
    @Test void deveRejeitarUmidadeAcimaDeCem() { assertThrows(DadosInvalidosException.class, () -> new Medicao("UMIDADE_SOLO", 101)); }
    @Test void deveRejeitarValorNaoFinito() { assertThrows(DadosInvalidosException.class, () -> new Medicao("NIVEL_RIO", Double.NaN)); }
}
