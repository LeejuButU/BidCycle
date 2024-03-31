package LeejuButU.BidCycle.domain.product.domain;

import LeejuButU.BidCycle.domain.member.domain.Member;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String title;
    private String category;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long startPrice;
    private Long currentPrice;
    private Long endPrice;
    private String state;
    private LocalDateTime tradeDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", updatable = false, nullable = false)
    Member buyer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", updatable = false, nullable = false)
    Member seller;
}
