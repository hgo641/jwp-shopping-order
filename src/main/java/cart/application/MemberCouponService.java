package cart.application;

import cart.domain.coupon.Coupon;
import cart.domain.coupon.CouponRepository;
import cart.domain.member.Member;
import cart.domain.memberCoupon.MemberCoupon;
import cart.domain.memberCoupon.MemberCouponRepository;
import cart.dto.MemberCouponRequest;
import cart.dto.MemberCouponResponse;
import cart.util.ModelSortHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class MemberCouponService {
    private final MemberCouponRepository memberCouponRepository;
    private final CouponRepository couponRepository;

    public MemberCouponService(MemberCouponRepository memberCouponRepository, CouponRepository couponRepository) {
        this.memberCouponRepository = memberCouponRepository;
        this.couponRepository = couponRepository;
    }

    public long add(Member member, MemberCouponRequest memberCouponRequest) {
        Coupon coupon = couponRepository.findById(memberCouponRequest.getId());
        return memberCouponRepository.add(new MemberCoupon(member, coupon));
    }

    @Transactional(readOnly = true)
    public MemberCouponResponse findById(Long id) {
        MemberCoupon memberCoupon = memberCouponRepository.findById(id);
        return MemberCouponResponse.from(memberCoupon);
    }

    @Transactional(readOnly = true)
    public List<MemberCouponResponse> findMemberCouponsByMemberId(Long memberId) {
        List<MemberCoupon> memberCoupons = memberCouponRepository.findMemberCouponsByMemberId(memberId);
        ModelSortHelper.sortByIdInDescending(memberCoupons);
        return memberCoupons.stream()
                .map(MemberCouponResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MemberCouponResponse> findAll() {
        List<MemberCoupon> memberCoupons = memberCouponRepository.findAll();
        ModelSortHelper.sortByIdInDescending(memberCoupons);
        return memberCoupons.stream()
                .map(MemberCouponResponse::from)
                .collect(Collectors.toList());
    }
}
