package training.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.model.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
