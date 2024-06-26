package LeejuButU.BidCycle.member.repository;

import LeejuButU.BidCycle.domain.member.domain.Member;
import LeejuButU.BidCycle.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static LeejuButU.BidCycle.fixture.Fixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository repository;

    @Test
    @DisplayName("멤버 저장")
    public void test_save(){
        //given
        Member member = generateMember("alice99");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getMemberId()).orElseThrow(IllegalArgumentException::new);
        assertThat(result).usingRecursiveComparison().isEqualTo(member);
    }

    @Test
    @DisplayName("아이디로 멤버 찾기")
    public void test_findById(){
        //given
        Member member = generateMember("alice99");
        repository.save(member);

        //when
        Member result = repository.findById(member.getMemberId()).orElseThrow();

        //then
        assertThat(result.getMemberId()).isEqualTo(member.getMemberId());
    }

    @Test
    @DisplayName("닉네임으로 멤버 찾기")
    public void test_findByNickname(){
        //given
        Member member = generateMember("alice99");
        repository.save(member);

        //when
        Member result = repository.findByNickname(member.getNickname()).orElseThrow();

        //then
        assertThat(result).usingRecursiveComparison().isEqualTo(member);
    }

    @Test
    @DisplayName("모든 멤버 조회")
    public void test_findAll(){
        //given
        Member member1 = generateMember("alice99");
        Member member2 = generateMember("alice88");
        repository.save(member1);
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("멤버 정보 업데이트")
    public void test_update(){
        //given
        Member member = generateMember("alice99");
        repository.save(member);

        //when
        member.updatePassword("0000");
        member.updateNickname("Bob");
        member.updateTown("부산");

        //then
        Member result = repository.findById(member.getMemberId()).orElseThrow();
        assertThat(result).usingRecursiveComparison().isEqualTo(member);
    }

    @Test
    @DisplayName("멤버 삭제")
    public void test_delete(){
        //given
        Member member = generateMember("alice99");
        repository.save(member);

        //when
        repository.delete(member);

        //then
        Optional<Member> result = repository.findById(member.getMemberId());
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("아이디로 멤버 삭제")
    public void test_deleteById(){
        //given
        Member member = generateMember("alice99");
        repository.save(member);

        //when
        repository.deleteById(member.getMemberId());

        //then
        Optional<Member> result = repository.findById(member.getMemberId());
        Assertions.assertTrue(result.isEmpty());
    }
}
