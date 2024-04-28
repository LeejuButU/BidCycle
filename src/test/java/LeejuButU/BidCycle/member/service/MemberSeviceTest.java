package LeejuButU.BidCycle.member.service;

import LeejuButU.BidCycle.domain.member.domain.Member;
import LeejuButU.BidCycle.domain.member.repository.MemberRepository;
import LeejuButU.BidCycle.domain.member.service.MemberService;
import LeejuButU.BidCycle.fixture.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberSeviceTest {

    @Mock
    private MemberRepository memberRepository;

    private Member member;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        member = Fixture.generateMember("leeju");
    }

    @Test
    @DisplayName("회원 가입 테스트")
    public void joinTest(){

        //given
        when(memberRepository.existsById(anyLong())).thenReturn(false);
        when(memberRepository.save(any())).thenReturn(member);

        //when
        Long memberId = memberService.join(member);

        //then
        verify(memberRepository, times(1)).save(member);
    }

}
