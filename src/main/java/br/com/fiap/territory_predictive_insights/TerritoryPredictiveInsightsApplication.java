package br.com.fiap.territory_predictive_insights;

<<<<<<< HEAD
import br.com.fiap.territory_predictive_insights.service.MenuConsole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
=======
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
>>>>>>> 1ae5b152f1fbe86aaa138886c224bbbd2dd15d54

@SpringBootApplication
public class TerritoryPredictiveInsightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerritoryPredictiveInsightsApplication.class, args);
	}

<<<<<<< HEAD
	@Bean
	CommandLineRunner executar(MenuConsole menu) {
		return args -> menu.iniciar();
	}

=======
>>>>>>> 1ae5b152f1fbe86aaa138886c224bbbd2dd15d54
}
