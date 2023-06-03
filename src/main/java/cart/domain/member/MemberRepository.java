package cart.domain.member;

import cart.domain.member.Member;

import java.util.List;

public interface MemberRepository {
    List<Member> findAll();

    Member findById(Long id);

    Member findByEmail(String email);

    Long add(Member member);

    Long update(Member member);

    void delete(Long id);
}
