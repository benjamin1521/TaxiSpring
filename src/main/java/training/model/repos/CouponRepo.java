package training.model.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.model.entities.Coupon;
import ua.training.model.entities.User;

import java.util.List;
import java.util.Optional;

public interface CouponRepo extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findById(Long id);
    List<Coupon> findByIdUserAndOrderNull(User user);
}
