package training.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.model.entities.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
    Order findById(Order order);
}
