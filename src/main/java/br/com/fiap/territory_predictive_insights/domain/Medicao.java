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
        if (valor < 0) throw new DadosInvalidosException("Medição não pode ser negativa.");
        this.tipoSensor = tipoSensor; this.valor = valor; this.dataHora = LocalDateTime.now();
    }
    public Long getId() { return id; } public String getTipoSensor() { return tipoSensor; }
    public double getValor() { return valor; } public LocalDateTime getDataHora() { return dataHora; }
}
