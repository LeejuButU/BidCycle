package LeejuButU.BidCycle.domain.member.repository;
// findByNickname

import LeejuButU.BidCycle.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이제 find by Nickname 을 만들겡
    // 여러가지 방식이 있긴한데, 그냥 간단하게 메서드 이름으로 만들어볼겡
    Optional<Member> findByNickname(String nickname); // 왜 이럴깡 흠
}