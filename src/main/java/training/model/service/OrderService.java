package training.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.training.model.entities.Coupon;
import ua.training.model.entities.Order;
import ua.training.model.entities.Taxi;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Status;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;
import ua.training.model.repos.CouponRepo;
import ua.training.model.repos.OrderRepo;
import ua.training.model.repos.TaxiRepo;
import ua.training.model.repos.UserRepo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static ua.training.model.service.Formulas.calculateDistance;
import static ua.training.model.service.Formulas.calculatePrice;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaxiRepo taxiRepo;

    @Autowired
    private CouponRepo couponRepo;

    public void updateOrder(User user, Order order, Map<String, String> form) {
//        order.setIdCoupon(couponRepo.findById(Long.valueOf(form.get("coupon")))
//                .orElse(null));
//        if (!(order.getType() == Type.valueOf(form.get("type")))) {
//            order.setType(Type.valueOf(form.get("type")));
//
//        }
//        order.setCost(calculatePrice(
//                user.getMoneySpent(),
//                order.getType().getPrice(),
//                order.getDistance(),
//                order.getIdCoupon()));
    }

    public boolean createOrder(User user, Map<String, String> form) {
        Street startStreet = Street.valueOf(form.get("startStreet"));
        int startHouse = Integer.parseInt(form.get("startHouse"));
        Street endStreet = Street.valueOf(form.get("endStreet"));
        int endHouse = Integer.parseInt(form.get("endHouse"));
        if (startStreet.equals(endStreet) && startHouse == endHouse) {
            return false;
        }

        Type type = Type.valueOf(form.get("type"));
        Coupon coupon = couponRepo.findById(Long.parseLong(form.get("coupon")))
                .orElse(null);

        List<Taxi> taxiList = taxiRepo.findByTypeAndBusy(type, false);
        if (taxiList.isEmpty()) {
            return false;
        }
        Taxi taxi = taxiList.get(new Random().nextInt(taxiList.size()));

        int distance = calculateDistance(startHouse, endHouse, startStreet.getCordinate(), endStreet.getCordinate());
        int waitingDistance = calculateDistance(startHouse, taxi.getLocationHouse(), startStreet.getCordinate(), taxi.getLocationStreet().getCordinate());

        Order order = Order.builder()
                .startStreet(startStreet)
                .startHouse(startHouse)
                .endStreet(endStreet)
                .endHouse(endHouse)
                .distance(distance)
                .type(type)
                .idCoupon(coupon)
                .idTaxi(taxi)
                .idUser(user)
                .orderDate(new Date())
                .status(Status.Active)
                .waitingTime(((double) waitingDistance) / type.getSpeed())
                .drivingTime(((double) distance) / type.getSpeed())
                .cost(calculatePrice(user.getMoneySpent(), type.getPrice(), distance, coupon))
                .build();

        Optional.ofNullable(coupon)
                .ifPresent(coupon1 -> coupon1.setOrder(order));

        orderRepo.save(order);
        return true;
    }

    public void deleteOrder(Order order) {
        Optional.ofNullable(order.getIdCoupon())
                .ifPresent(coupon -> coupon.setOrder(null));
        orderRepo.delete(order);
    }

    public List<Coupon> getFreeCoupons(User user) {
        return couponRepo.findByIdUserAndOrderNull(user);
    }


    public void confirmOrder(Order order, User user) {
        user.setMoneySpent(user.getMoneySpent() + order.getCost());
        order.setStatus(Status.Done);
        order.getIdTaxi().setLocationHouse(order.getEndHouse());
        order.getIdTaxi().setLocationStreet(order.getEndStreet());
//        order.getIdTaxi().setBusy(false);
        userRepo.save(user);
        orderRepo.save(order);
    }
}
