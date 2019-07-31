package training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.model.entities.Coupon;
import ua.training.model.entities.Order;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;
import ua.training.model.service.OrderService;

import java.util.Map;

@Controller
@RequestMapping("/orders")
public class ClientController {
    @Autowired
    OrderService orderService;

    @GetMapping("/{user}")
    public String getOrders(
            @PathVariable User user,
            @RequestParam(required = false) Order order,
            Model model
    ) {
        model.addAttribute("orders", user.getOrders());
        model.addAttribute("order", order);
        return "client/orderList";
    }

    @GetMapping("/create")
    public String getCreateOrder(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) Type type,
            @RequestParam(required = false) Street street,
            @RequestParam(required = false) Coupon coupon,
            Model model
    ) {
        model.addAttribute("types", Type.values());
        model.addAttribute("type", type);
        model.addAttribute("streets", Street.values());
        model.addAttribute("street", street);
        model.addAttribute("coupons", orderService.getFreeCoupons(user));
        model.addAttribute("coupon", coupon);
        return "client/orderCreate";
    }

    @PostMapping("/create")
    public String postCreateOrder(
            @AuthenticationPrincipal User user,
            @RequestParam Map<String, String> form,
            Model model
    ) {
        if (!orderService.createOrder(user, form)) {
            model.addAttribute("unavailable", "Unavailable to order");
            return getCreateOrder(user, null, null, null, model);
        }
        return "redirect:/orders/" + user.getId();
    }

    @GetMapping("/edit/{order}")
    public String getEditOrder(
            @PathVariable Order order,
            Model model
    ) {

        model.addAttribute("order", order);

        return "client/orderEdit";
    }

    @PostMapping("/edit/{order}")
    public String postEditOrder(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Order order,
            @RequestParam Map<String, String> form
    ) {

        orderService.updateOrder(currentUser, order, form);

        return "redirect:/orders/" + currentUser.getId();
    }

    @GetMapping("/delete/{order}")
    public String getDeleteOrder(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Order order
    ) {
        orderService.deleteOrder(order);

        return "redirect:/orders/" + currentUser.getId();
    }

    @GetMapping("/confirm/{order}")
    public String getConfirmOrder(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Order order
    ) {
        orderService.confirmOrder(order,currentUser);
        return "redirect:/orders/" + currentUser.getId();
    }

}
