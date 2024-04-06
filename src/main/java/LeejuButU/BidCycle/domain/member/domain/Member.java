package LeejuButU.BidCycle.domain.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue private Long memberId;

    @NonNull
    @Column(nullable = false, updatable = false, length = 20)
    private String loginId;

    @NonNull
    @Column(nullable = false, length = 20)
    private String password;

    @NonNull
    @Column(nullable = false, length = 20)
    private String nickname;

    @NonNull
    @Column(nullable = false, length = 100)
    private String town;
}
