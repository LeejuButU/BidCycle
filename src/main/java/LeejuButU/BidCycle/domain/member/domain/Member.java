package LeejuButU.BidCycle.domain.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

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

    @Builder
    public Member(@NonNull String loginId, @NonNull String password, @NonNull String nickname, @NonNull String town) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.town = town;
    }

    public void updatePassword(String password){
        this.password = password;
    }

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

    public void updateTown(String town){
        this.town = town;
    }
}
