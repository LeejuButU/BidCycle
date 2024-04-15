package LeejuButU.BidCycle.domain.product.repository;

import LeejuButU.BidCycle.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }