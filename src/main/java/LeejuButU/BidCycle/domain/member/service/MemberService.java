package LeejuButU.BidCycle.domain.member.service;

import LeejuButU.BidCycle.domain.member.domain.Member;
import LeejuButU.BidCycle.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMemberId();

    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByNickname(member.getNickname())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 한 명 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    // 회원 정보 변경
    public void updateMember(Long memberId, Member updatedMember) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        // 변경할 정보를 새로운 정보로 업데이트
        member.updatePassword(updatedMember.getPassword());
        member.updateNickname(updatedMember.getNickname());
        member.updateTown(updatedMember.getTown());

        // 변경된 정보를 저장
        memberRepository.save(member);
    }

    // 회원 탈퇴
    public void withdrawMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        memberRepository.delete(member);
    }

}
