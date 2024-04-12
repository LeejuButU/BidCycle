package LeejuButU.BidCycle.domain.product.repository;

import LeejuButU.BidCycle.domain.product.domain.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public Long save(Product product){
        em.persist(product);
        return product.getProductId();
    }

    public Optional<Product> findById(Long productId){
        Product product = em.find(Product.class, productId);
        return Optional.ofNullable(product);
    }

    public List<Product> findAll(){
        return em.createQuery("select m from Product m", Product.class)
                .getResultList();
    }

    public void delete(Product product){
        em.remove(product);
    }

    public void deleteById(Long productId){
        Product product = findById(productId).orElseThrow(IllegalArgumentException::new);
        em.remove(product);
    }
}
