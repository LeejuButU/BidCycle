package LeejuButU.BidCycle.domain.photo.domain;

import LeejuButU.BidCycle.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;

    @NonNull
    @Column(nullable = false, updatable = false, length = 200)
    private String imageURL;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    @Builder
    public Photo(@NonNull String imageURL, @NonNull Product product) {
        this.imageURL = imageURL;
        this.product = product;
    }
}
