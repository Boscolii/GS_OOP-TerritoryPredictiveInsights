package br.com.fiap.territory_predictive_insights;
import br.com.fiap.territory_predictive_insights.domain.Medicao;
import br.com.fiap.territory_predictive_insights.repository.*;
import br.com.fiap.territory_predictive_insights.service.ServicoMonitoramento;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.ArgumentCaptor;
import br.com.fiap.territory_predictive_insights.domain.Alerta;
import br.com.fiap.territory_predictive_insights.domain.NivelAlerta;
class ServicoMonitoramentoTest {
    @Test void deveSalvarAlertaDeNivelRioComLimiteCorreto() {
        MedicaoRepository medicoes = mock(MedicaoRepository.class);
        AlertaRepository alertas = mock(AlertaRepository.class);
        when(medicoes.findByTipoSensorOrderByDataHoraAsc("NIVEL_RIO")).thenReturn(List.of(new Medicao("NIVEL_RIO", 4.5)));
        new ServicoMonitoramento(medicoes, alertas).prever("NIVEL_RIO", "MEDIA");
        ArgumentCaptor<Alerta> captor = ArgumentCaptor.forClass(Alerta.class);
        verify(alertas).save(captor.capture());
        assertEquals(NivelAlerta.CRITICO, captor.getValue().getNivel());
        assertEquals(4.5, captor.getValue().getValorPrevisto());
        assertTrue(captor.getValue().getMotivo().contains("4.0"));
    }
    @Test void deveConsultarRepositorioParaPrever() {
        MedicaoRepository medicoes = mock(MedicaoRepository.class); AlertaRepository alertas = mock(AlertaRepository.class);
        when(medicoes.findByTipoSensorOrderByDataHoraAsc("CHUVA")).thenReturn(List.of(new Medicao("CHUVA", 20)));
        assertEquals(20, new ServicoMonitoramento(medicoes, alertas).prever("CHUVA", "MEDIA"));
        verify(medicoes).findByTipoSensorOrderByDataHoraAsc("CHUVA");
    }
}
