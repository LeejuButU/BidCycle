package LeejuButU.BidCycle.domain.product.domain;

import LeejuButU.BidCycle.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

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

    @Builder
    public Product(@NonNull String title, @NonNull String category, @NonNull String description, @NonNull LocalDateTime startDate, @NonNull LocalDateTime endDate, @NonNull Long startPrice, @NonNull Long currentPrice, @NonNull Long endPrice, @NonNull String state, LocalDateTime tradeDate, Member buyer, @NonNull Member seller) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startPrice = startPrice;
        this.currentPrice = currentPrice;
        this.endPrice = endPrice;
        this.state = state;
        this.tradeDate = tradeDate;
        this.buyer = buyer;
        this.seller = seller;
    }

    public void updatePrice(long price) {
        this.currentPrice = price;
    }
}
