package br.com.fiap.territory_predictive_insights.domain;

import br.com.fiap.territory_predictive_insights.exception.FonteIndisponivelException;
import java.util.concurrent.ThreadLocalRandom;

abstract class SensorBase implements Sensor {
    private boolean disponivel = true;
    protected double gerar(double minimo, double maximo) {
        verificarFuncionamento();
        return ThreadLocalRandom.current().nextDouble(minimo, maximo);
    }
    @Override public void verificarFuncionamento() {
        if (!disponivel) throw new FonteIndisponivelException("Sensor " + retornarTipo() + " indisponível.");
    }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}
