package LeejuButU.BidCycle.domain.member.repository;

import LeejuButU.BidCycle.domain.member.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // CREATE
    public Long save(Member member) {
        em.persist(member);
        return member.getMemberId();
    }

    // READ
    public Optional<Member> findById(Long memberId) {
        Member member = em.find(Member.class, memberId);
        return Optional.ofNullable(member);
    }

    public Optional<Member> findByNickname(String nickname) {
        String jpql = "SELECT m FROM Member m WHERE m.nickname = :nickname";
        Member member = em.createQuery(jpql, Member.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        String jpql = "SELECT m FROM Member m";
        return em.createQuery(jpql, Member.class)
                .getResultList();
    }

    // UPDATE
    public void update(Member member){
        em.merge(member);
    }

    // DELETE
    public void delete(Member member){
        em.remove(member);
    }

    public void deleteById(Long memberId){
        Member member = findById(memberId).orElseThrow(IllegalArgumentException::new);
        em.remove(member);
    }
}
