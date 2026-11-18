package br.com.fiap.territory_predictive_insights;
import br.com.fiap.territory_predictive_insights.domain.Medicao;
import br.com.fiap.territory_predictive_insights.repository.*;
import br.com.fiap.territory_predictive_insights.service.ServicoMonitoramento;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class ServicoMonitoramentoTest {
    @Test void deveConsultarRepositorioParaPrever() {
        MedicaoRepository medicoes = mock(MedicaoRepository.class); AlertaRepository alertas = mock(AlertaRepository.class);
        when(medicoes.findByTipoSensorOrderByDataHoraAsc("CHUVA")).thenReturn(List.of(new Medicao("CHUVA", 20)));
        assertEquals(20, new ServicoMonitoramento(medicoes, alertas).prever("CHUVA", "MEDIA"));
        verify(medicoes).findByTipoSensorOrderByDataHoraAsc("CHUVA");
    }
}
