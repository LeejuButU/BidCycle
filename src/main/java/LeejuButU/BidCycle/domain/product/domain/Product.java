package LeejuButU.BidCycle.domain.product.domain;

import LeejuButU.BidCycle.domain.member.domain.Member;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;
    @Column(nullable = false)
    private Long startPrice;
    @Column(nullable = false)
    private Long currentPrice;
    @Column(nullable = false)
    private Long endPrice;
    @Column(nullable = false)
    private String state;
    private LocalDateTime tradeDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", updatable = false)
    Member buyer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", updatable = false, nullable = false)
    Member seller;
}
