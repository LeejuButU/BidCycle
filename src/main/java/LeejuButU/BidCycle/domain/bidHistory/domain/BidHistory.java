package LeejuButU.BidCycle.domain.bidHistory.domain;

import LeejuButU.BidCycle.domain.member.domain.Member;
import LeejuButU.BidCycle.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BidHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidHistoryId;

    @NonNull
    @Column(nullable = false)
    private Long bidPrice;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime bidTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bidder_id", nullable = false)
    @NonNull
    private Member bidder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @NonNull
    private Product product;
}
