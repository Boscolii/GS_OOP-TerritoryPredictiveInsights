package br.com.fiap.territory_predictive_insights.domain;

import br.com.fiap.territory_predictive_insights.exception.DadosInvalidosException;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Medicao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String tipoSensor;
    private double valor;
    private LocalDateTime dataHora;
    protected Medicao() { }
    public Medicao(String tipoSensor, double valor) {
        double maximo = switch (tipoSensor == null ? "" : tipoSensor) {
            case "CHUVA" -> 120;
            case "NIVEL_RIO" -> 5;
            case "UMIDADE_SOLO" -> 100;
            default -> throw new DadosInvalidosException("Tipo de sensor inválido.");
        };
        if (!Double.isFinite(valor) || valor < 0 || valor > maximo) throw new DadosInvalidosException("Medição fora da faixa de " + tipoSensor + ".");
        this.tipoSensor = tipoSensor; this.valor = valor; this.dataHora = LocalDateTime.now();
    }
    public Long getId() { return id; } public String getTipoSensor() { return tipoSensor; }
    public double getValor() { return valor; } public LocalDateTime getDataHora() { return dataHora; }
}
