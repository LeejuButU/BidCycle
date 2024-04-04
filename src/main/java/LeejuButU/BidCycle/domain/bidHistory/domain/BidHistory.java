package LeejuButU.BidCycle.domain.bidHistory.domain;

import LeejuButU.BidCycle.domain.member.domain.Member;
import LeejuButU.BidCycle.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BidHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidHistoryId;

    @NonNull
    @Column(nullable = false, updatable = false)
    private Long bidPrice;

    @NonNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime bidTime;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bidder_id", nullable = false, updatable = false)
    private Member bidder;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;
}
