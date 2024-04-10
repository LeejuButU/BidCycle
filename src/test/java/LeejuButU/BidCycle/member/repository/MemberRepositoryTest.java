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
    public void test_save(){
        //given
        Member member = generateMember("alice99");

        //when
        repository.save(member);

        //then
        Optional<Member> result = repository.findById(member.getMemberId());
        assertThat(result).usingRecursiveComparison().isEqualTo(member);
    }

    @Test
    public void test_findById(){
        //given
        Member member = generateMember("alice99");
        Long memberId = repository.save(member);

        //when
        Member result = repository.findById(member.getMemberId()).orElseThrow();

        //then
        assertThat(result.getMemberId()).isEqualTo(memberId);
    }

    @Test
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
    public void test_update(){
        //given
        Member member = generateMember("alice99");
        repository.save(member);
        member.updatePassword("0000");
        member.updateNickname("Bob");
        member.updateTown("부산");

        //when
        repository.update(member);

        //then
        Member result = repository.findById(member.getMemberId()).orElseThrow();
        assertThat(result).usingRecursiveComparison().isEqualTo(member);
    }

    @Test
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
