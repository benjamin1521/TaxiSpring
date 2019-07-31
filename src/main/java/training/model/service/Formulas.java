package training.model.service;

import ua.training.model.entities.Coupon;

import java.util.Optional;

public class Formulas {
    public static long calculatePrice(long moneySpent, int price, double distance, Coupon coupon) {
        long result = (long) (calculateLoyalty(moneySpent) * distance * price + 0.5);

        return Optional.ofNullable(coupon)
                .map(coupon1 -> (long) (coupon1.getDiscountPercent() * result))
                .orElse(result);
    }

    public static int calculateDistance(int startH, int endH, int startS, int endS) {
        return Math.abs(startH - endH) + Math.abs(startS - endS);
    }

    public static double calculateLoyalty(long moneySpent) {
        if (moneySpent > 10000) {
            return 0.8;
        } else if (moneySpent > 5000) {
            return 0.9;
        } else {
            return 1.0;
        }
    }
}
