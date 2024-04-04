package LeejuButU.BidCycle.domain.qna.domain;

import LeejuButU.BidCycle.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Qna {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaId;

    @NonNull
    @Column(nullable = false, length = 100)
    private String title;

    @NonNull
    @Column(nullable = false, length = 500)
    private String content;

    @NonNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(length = 500)
    private String answer;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;
}
