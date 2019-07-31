package training.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.model.entities.User;
import ua.training.model.entities.enums.Role;
import ua.training.model.entities.enums.Street;
import ua.training.model.entities.enums.Type;
import ua.training.model.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('Admin')")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());

        return "admin/userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "admin/userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam("userId") User user,
            @RequestParam Map<String, String> form
    ) {
        userService.updateUser(user, form);
        return "redirect:/user";
    }

    @PostMapping("{user}")
    public String couponAdd(
            @RequestParam double percent,
            @PathVariable User user
    ) {
        userService.addCoupon(percent, user);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/create")
    public String taxiCreate(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) Type type,
            @RequestParam(required = false) Street street,
            Model model
    ) {
        model.addAttribute("types", Type.values());
        model.addAttribute("streets", Street.values());
        model.addAttribute("type", type);
        model.addAttribute("street", street);
        return "admin/taxiCreate";
    }

    @PostMapping("/create")
    public String postTaxiCreate(
            @AuthenticationPrincipal User user,
            @RequestParam String number,
            @RequestParam String driverName,
            @RequestParam Type type
    ) {
        userService.createTaxi(number, driverName, type);
        return "redirect:/user/" + user.getId();
    }

}
