package br.com.fiap.territory_predictive_insights;

import br.com.fiap.territory_predictive_insights.service.MenuConsole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TerritoryPredictiveInsightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerritoryPredictiveInsightsApplication.class, args);
	}

	@Bean
	CommandLineRunner executar(MenuConsole menu) {
		return args -> menu.iniciar();
	}
}