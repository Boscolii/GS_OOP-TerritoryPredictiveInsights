package br.com.fiap.territory_predictive_insights.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Alerta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String tipoSensor;
    private double valorPrevisto;
    @Enumerated(EnumType.STRING) private NivelAlerta nivel;
    private String motivo;
    private LocalDateTime dataHora;
    protected Alerta() { }
    public Alerta(String tipoSensor, double valorPrevisto, NivelAlerta nivel, String motivo) {
        this.tipoSensor = tipoSensor; this.valorPrevisto = valorPrevisto; this.nivel = nivel; this.motivo = motivo; this.dataHora = LocalDateTime.now();
    }
    public Long getId() { return id; } public String getTipoSensor() { return tipoSensor; }
    public double getValorPrevisto() { return valorPrevisto; } public NivelAlerta getNivel() { return nivel; }
    public String getMotivo() { return motivo; } public LocalDateTime getDataHora() { return dataHora; }
}
