package training.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.model.entities.Taxi;
import ua.training.model.entities.enums.Type;

import java.util.List;

public interface TaxiRepo extends JpaRepository<Taxi, Long> {
    List<Taxi> findByTypeAndBusy(Type type, boolean busy);
}
