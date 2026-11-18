package br.com.fiap.territory_predictive_insights.service;

import br.com.fiap.territory_predictive_insights.domain.*;
import br.com.fiap.territory_predictive_insights.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicoMonitoramento {
    private final MedicaoRepository medicoes; private final AlertaRepository alertas;
    public ServicoMonitoramento(MedicaoRepository medicoes, AlertaRepository alertas) { this.medicoes = medicoes; this.alertas = alertas; }
    public Medicao registrar(Sensor sensor) { return medicoes.save(new Medicao(sensor.retornarTipo(), sensor.lerValor())); }
    public double prever(String tipoSensor, String estrategia) {
        double previsto = new FabricaPrevisao().criar(estrategia).prever(medicoes.findByTipoSensorOrderByDataHoraAsc(tipoSensor));
        emitirAlerta(tipoSensor, previsto); return previsto;
    }
    private void emitirAlerta(String tipo, double valor) {
        ConfiguracaoSistema c = ConfiguracaoSistema.getInstancia();
        NivelAlerta nivel = valor >= c.getCritico() ? NivelAlerta.CRITICO : valor >= c.getAlerta() ? NivelAlerta.ALERTA : valor >= c.getAtencao() ? NivelAlerta.ATENCAO : null;
        if (nivel != null) alertas.save(new Alerta(tipo, valor, nivel, "Previsão " + valor + " ultrapassou o limite " + nivel));
    }
    public List<Medicao> historico(String tipoSensor) { return medicoes.findByTipoSensorOrderByDataHoraAsc(tipoSensor); }
    public List<Alerta> listarAlertas() { return alertas.findAll(); }
    public long totalMedicoes() { return medicoes.count(); }
    public long totalAlertas() { return alertas.count(); }
}
