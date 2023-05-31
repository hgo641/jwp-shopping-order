package cart.domain.coupon;

import cart.domain.Order;

public class PercentCoupon extends Coupon {

    public PercentCoupon(Long id, String name, Double discountPercent) {
        super(id, name, DiscountType.PERCENTAGE, discountPercent, 0);
    }

    public PercentCoupon(Long id, String name, Double discountPercent, Integer minimumPrice) {
        super(id, name, DiscountType.PERCENTAGE, discountPercent, 0, minimumPrice);
    }

    @Override
    public int discount(Order order) {
        int price = order.getOriginalPrice();
        return price - (int) (price * getDiscountPercent());
    }
}
