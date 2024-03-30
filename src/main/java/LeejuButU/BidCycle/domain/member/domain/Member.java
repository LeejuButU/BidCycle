package LeejuButU.BidCycle.domain.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long memberId;

    private String id;
    private String password;
    private String nickname;
    private String town;
}
