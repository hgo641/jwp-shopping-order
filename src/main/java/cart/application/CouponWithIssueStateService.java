package cart.application;

import cart.domain.coupon.Coupon;
import cart.domain.coupon.CouponRepository;
import cart.domain.member.Member;
import cart.domain.memberCoupon.MemberCoupon;
import cart.domain.memberCoupon.MemberCouponRepository;
import cart.dto.IssuableSearchCouponResponse;
import cart.util.ModelSortHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CouponWithIssueStateService {
    private final CouponRepository couponRepository;
    private final MemberCouponRepository memberCouponRepository;

    public CouponWithIssueStateService(CouponRepository couponRepository, MemberCouponRepository memberCouponRepository) {
        this.couponRepository = couponRepository;
        this.memberCouponRepository = memberCouponRepository;
    }

    @Transactional(readOnly = true)
    public List<IssuableSearchCouponResponse> findAll(Member member) {
        List<Coupon> coupons = couponRepository.findAll();
        ModelSortHelper.sortByIdInDescending(coupons);

        List<Coupon> couponsOfMember = memberCouponRepository.findMemberCouponsByMemberId(member.getId()).stream()
                .map(MemberCoupon::getCoupon)
                .collect(Collectors.toList());

        return getIssuableSearchCouponResponses(coupons, couponsOfMember);
    }

    private List<IssuableSearchCouponResponse> getIssuableSearchCouponResponses(List<Coupon> coupons, List<Coupon> couponsOfMember) {
        return coupons.stream()
                .map(coupon -> {
                    boolean issuable = isIssuable(coupon, couponsOfMember);
                    return IssuableSearchCouponResponse.of(coupon, issuable);
                }).collect(Collectors.toList());
    }

    private boolean isIssuable(Coupon coupon, List<Coupon> couponsOfMember) {
        return !couponsOfMember.contains(coupon);
    }
}
