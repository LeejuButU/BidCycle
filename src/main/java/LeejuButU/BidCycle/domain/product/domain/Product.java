package LeejuButU.BidCycle.domain.product.domain;

import LeejuButU.BidCycle.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NonNull
    @Column(nullable = false, length = 100)
    private String title;

    @NonNull
    @Column(nullable = false, length = 20)
    private String category;

    @NonNull
    @Column(nullable = false, length = 500)
    private String description;

    @NonNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime startDate;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime endDate;

    @NonNull
    @Column(nullable = false, updatable = false)
    private Long startPrice;

    @NonNull
    @Column(nullable = false)
    private Long currentPrice;

    @NonNull
    @Column(nullable = false)
    private Long endPrice;

    @NonNull
    @Column(nullable = false, length = 20)
    private String state;

    private LocalDateTime tradeDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", updatable = false)
    private Member buyer;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false, updatable = false)
    private Member seller;
}
