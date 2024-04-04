package LeejuButU.BidCycle.domain.photo.domain;

import LeejuButU.BidCycle.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;

@Entity
@Getter
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;

    @NonNull
    @Column(updatable = false, nullable = false)
    private String imageURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @NonNull
    private Product product;
}
