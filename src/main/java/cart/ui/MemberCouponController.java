package cart.ui;

import cart.application.MemberCouponService;
import cart.domain.member.Member;
import cart.dto.MemberCouponRequest;
import cart.dto.MemberCouponResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users/coupons")
public class MemberCouponController {
    private final MemberCouponService memberCouponService;

    public MemberCouponController(MemberCouponService memberCouponService) {
        this.memberCouponService = memberCouponService;
    }

    @PostMapping
    public ResponseEntity<Void> addMemberCoupon(Member member, @RequestBody MemberCouponRequest memberCouponRequest) {
        memberCouponService.add(member, memberCouponRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<MemberCouponResponse>> getAllMemberCoupons(Member member) {
        return ResponseEntity.ok(memberCouponService.findMemberCouponsByMemberId(member.getId()));
    }
}
