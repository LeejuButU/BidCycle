package LeejuButU.BidCycle.domain.member.service;

import LeejuButU.BidCycle.domain.member.domain.Member;
import LeejuButU.BidCycle.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원ID로 회원 조회 (회원이 존재하지 않으면 예외 발생)
    private Member findByIdOrThrow(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
    }

    //회원 가입
    @Transactional
    public Long join(Member member){
        if(memberRepository.existsById(member.getMemberId())){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        memberRepository.save(member);
        return member.getMemberId();
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 한 명 조회
    public Member findOne(Long memberId){
        return findByIdOrThrow(memberId);
    }

    // 회원 정보 변경
    @Transactional
    public void updateMember(Long memberId, Member updatedMember) {
        Member member = findByIdOrThrow(memberId);

        // 변경할 정보를 새로운 정보로 업데이트
        member.updatePassword(updatedMember.getPassword());
        member.updateNickname(updatedMember.getNickname());
        member.updateTown(updatedMember.getTown());

        // 변경된 정보를 저장
        memberRepository.save(member);
    }

    // 회원 탈퇴
    @Transactional
    public void withdrawMember(Long memberId) {
        Member member = findByIdOrThrow(memberId);
        memberRepository.delete(member);
    }

}
