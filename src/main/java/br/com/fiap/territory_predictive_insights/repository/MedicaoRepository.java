package br.com.fiap.territory_predictive_insights.repository;
import br.com.fiap.territory_predictive_insights.domain.Medicao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MedicaoRepository extends JpaRepository<Medicao, Long> { List<Medicao> findByTipoSensorOrderByDataHoraAsc(String tipoSensor); }
