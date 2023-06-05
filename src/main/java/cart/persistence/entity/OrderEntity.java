package cart.persistence.entity;

import java.util.Objects;

public class OrderEntity {
    private final Long id;
    private final Integer originalPrice;
    private final Integer discountPrice;
    private final Boolean confirmState;
    private final Long usedCouponId;
    private final Long memberId;

    public OrderEntity(Long id, Integer originalPrice, Integer discountPrice, Boolean confirmState, Long usedCouponId, Long memberId) {
        this.id = id;
        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.confirmState = confirmState;
        this.usedCouponId = usedCouponId;
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public Long getUsedCouponId() {
        return usedCouponId;
    }

    public Boolean getConfirmState() {
        return confirmState;
    }

    public Long getMemberId() {
        return memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
