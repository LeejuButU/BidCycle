package LeejuButU.BidCycle.domain.interestProduct.domain;

import LeejuButU.BidCycle.domain.member.domain.Member;
import LeejuButU.BidCycle.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestProduct {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestProductId;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private Member member;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;
}
