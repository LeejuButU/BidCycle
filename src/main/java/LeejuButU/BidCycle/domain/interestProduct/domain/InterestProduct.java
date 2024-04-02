package LeejuButU.BidCycle.domain.interestProduct.domain;

import LeejuButU.BidCycle.domain.member.domain.Member;
import LeejuButU.BidCycle.domain.product.domain.Product;
import jakarta.persistence.*;

@Entity
public class InterestProduct {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestProductId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", updatable = false, nullable = false)
    Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", updatable = false, nullable = false)
    Product product;
}
