package br.com.fiap.territory_predictive_insights.service;

import br.com.fiap.territory_predictive_insights.domain.Alerta;
import br.com.fiap.territory_predictive_insights.domain.DadosTerritorio;
import br.com.fiap.territory_predictive_insights.domain.EstacaoMonitoramento;
import br.com.fiap.territory_predictive_insights.domain.Medicao;
import br.com.fiap.territory_predictive_insights.domain.Sensor;
import br.com.fiap.territory_predictive_insights.domain.SensorChuva;
import br.com.fiap.territory_predictive_insights.domain.SensorNivelRio;
import br.com.fiap.territory_predictive_insights.domain.SensorUmidadeSolo;
import br.com.fiap.territory_predictive_insights.exception.DadosInvalidosException;
import br.com.fiap.territory_predictive_insights.exception.FonteIndisponivelException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class MenuConsole {

    private static final String CODIGO_TERRITORIO = "FIAP2026";

    private final ServicoMonitoramento servico;
    private final Scanner scanner = new Scanner(System.in);

    private final Map<String, Sensor> sensores = Map.of(
            "1", new SensorChuva(),
            "2", new SensorNivelRio(),
            "3", new SensorUmidadeSolo()
    );

    private final EstacaoMonitoramento estacao = new EstacaoMonitoramento(
            1L, "Estação Vila Esperança", "Vila Esperança", true
    );

    private final DadosTerritorio territorio = new DadosTerritorio(
            "Vila Esperança", 12_000, CODIGO_TERRITORIO
    );

    public MenuConsole(ServicoMonitoramento servico) {
        this.servico = servico;
        territorio.alterarLimiares(CODIGO_TERRITORIO, 50, 75, 95);
    }

    public void iniciar() {
        String opcao = "";

        while (!"0".equals(opcao)) {
            try {
                System.out.print(
                        "\n1-Verificar sensores | 2-Registrar leitura | 3-Prever "
                                + "| 4-Histórico | 5-Alertas | 6-Gerenciar território "
                                + "| 7-Status completo | 0-Sair: "
                );

                opcao = scanner.nextLine();
                executar(opcao);
            } catch (DadosInvalidosException
                     | FonteIndisponivelException
                     | IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void executar(String opcao) {
        switch (opcao) {
            case "1" -> verificarSensores();
            case "2" -> ler();
            case "3" -> prever();
            case "4" -> historico();
            case "5" -> listarAlertas();
            case "6" -> gerenciarTerritorio();
            case "7" -> statusCompleto();
            case "0" -> System.out.println("Até logo.");
            default -> System.out.println("Opção inválida.");
        }
    }

    private void verificarSensores() {
        System.out.println(
                "Status da " + estacao.getNome() + ": " + estacao.verificarStatus()
        );

        sensores.values().forEach(sensor -> {
            sensor.verificarFuncionamento();
            System.out.println("- " + sensor.retornarTipo() + ": disponível");
        });
    }

    private Sensor sensorEscolhido() {
        System.out.print("1-Chuva 2-Nível do rio 3-Umidade do solo: ");
        Sensor sensor = sensores.get(scanner.nextLine());

        if (sensor == null) {
            throw new IllegalArgumentException("Sensor inválido.");
        }

        return sensor;
    }

    private void ler() {
        Medicao medicao = servico.registrar(sensorEscolhido());

        System.out.printf(
                "Medição salva: %s = %.2f%n",
                medicao.getTipoSensor(),
                medicao.getValor()
        );
    }

    private void prever() {
        Sensor sensor = sensorEscolhido();

        System.out.print("Estratégia (MEDIA/COMPARACAO): ");
        String estrategia = scanner.nextLine();

        double previsao = servico.prever(sensor.retornarTipo(), estrategia);

        System.out.printf("Previsão: %.2f%n", previsao);
    }

    private void historico() {
        Sensor sensor = sensorEscolhido();

        servico.historico(sensor.retornarTipo()).forEach(medicao ->
                System.out.printf(
                        "%s | %.2f%n",
                        medicao.getDataHora(),
                        medicao.getValor()
                )
        );
    }

    private void listarAlertas() {
        List<Alerta> alertas = servico.listarAlertas();

        if (alertas.isEmpty()) {
            System.out.println("Nenhum alerta emitido.");
        } else {
            alertas.forEach(alerta ->
                    System.out.println(
                            alerta.getNivel() + " | "
                                    + alerta.getTipoSensor() + " | "
                                    + alerta.getMotivo()
                    )
            );
        }
    }

    private void gerenciarTerritorio() {
        System.out.println(
                "Território: " + territorio.getRegiao()
                        + " | população estimada: " + territorio.getPopulacao()
        );

        Sensor sensor = sensorEscolhido();
        System.out.print("Código de acesso: ");
        String codigo = scanner.nextLine();

        System.out.print(
                "Atenção, alerta, crítico (separados por vírgula): "
        );
        String[] valores = scanner.nextLine().split(",");

        if (valores.length != 3) {
            throw new DadosInvalidosException(
                    "Informe três limiares separados por vírgula. "
                            + "Exemplo: 50,75,95"
            );
        }

        double atencao = Double.parseDouble(valores[0].trim());
        double alerta = Double.parseDouble(valores[1].trim());
        double critico = Double.parseDouble(valores[2].trim());

        territorio.alterarLimiares(codigo, atencao, alerta, critico);
        ConfiguracaoSistema.getInstancia().configurar(sensor.retornarTipo(), atencao, alerta, critico);

        System.out.println("Limiares do território atualizados com sucesso.");
    }

    private void statusCompleto() {
        System.out.println("--- STATUS COMPLETO ---");
        System.out.println(estacao.getNome() + " - " + estacao.verificarStatus());

        System.out.println(
                "Região: " + territorio.getRegiao()
                        + " | população estimada: " + territorio.getPopulacao()
        );

        sensores.values().forEach(sensor -> {
            ConfiguracaoSistema.Limiares l = ConfiguracaoSistema.getInstancia().getLimiares(sensor.retornarTipo());
            System.out.printf("%s: atenção %.2f | alerta %.2f | crítico %.2f%n", sensor.retornarTipo(), l.atencao(), l.alerta(), l.critico());
        });

        System.out.println("Medições persistidas: " + servico.totalMedicoes());
        System.out.println("Alertas emitidos: " + servico.totalAlertas());

        verificarSensores();
    }
}
