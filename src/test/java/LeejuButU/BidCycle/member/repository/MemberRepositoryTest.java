package LeejuButU.BidCycle.member.repository;

import LeejuButU.BidCycle.domain.member.domain.Member;
import LeejuButU.BidCycle.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static LeejuButU.BidCycle.fixture.Fixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository repository;

    @Test
    public void save(){
        Member member = generateMember("alice99");

        repository.save(member);
        Member result = repository.findById(member.getMemberId()).orElse(null);
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findById(){
        Member member = generateMember("alice99");

        Long memberId = repository.save(member);
        Member result = repository.findById(member.getMemberId()).orElse(null);
        assert result != null;
        assertThat(result.getMemberId()).isEqualTo(memberId);
    }

    @Test
    public void findByNickname(){
        Member member = generateMember("alice99");

        repository.save(member);
        Member result = repository.findByNickname("Alice").orElse(null);
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findAll(){
        Member member1 = generateMember("alice99");
        Member member2 = generateMember("alice88");

        repository.save(member1);
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void update(){
        Member member = generateMember("alice99");

        repository.save(member);
        member.updatePassword("0000");
        member.updateNickname("Bob");
        member.updateTown("부산");

        repository.update(member);
        Member result = repository.findById(member.getMemberId()).orElse(null);
        assert result != null;
        assertThat(result.getPassword()).isEqualTo(member.getPassword());
        assertThat(result.getNickname()).isEqualTo(member.getNickname());
        assertThat(result.getTown()).isEqualTo(member.getTown());
    }

    @Test
    public void delete(){
        Member member = generateMember("alice99");

        repository.save(member);
        repository.delete(member);

        Member result = repository.findById(member.getMemberId()).orElse(null);
        assert result == null;
    }

    @Test
    public void deleteById(){
        Member member = generateMember("alice99");

        repository.save(member);
        repository.deleteById(member.getMemberId());

        Member result = repository.findById(member.getMemberId()).orElse(null);
        assert result == null;
    }
}
