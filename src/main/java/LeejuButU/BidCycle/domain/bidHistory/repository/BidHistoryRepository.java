package LeejuButU.BidCycle.domain.bidHistory.repository;

import LeejuButU.BidCycle.domain.bidHistory.domain.BidHistory;
import LeejuButU.BidCycle.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BidHistoryRepository extends JpaRepository<BidHistory, Long> {
    @Query("select b " +
            " from BidHistory b " +
            "where b.product = :product " +
            "order by b.bidPrice desc " +
            "limit 1")
    BidHistory findMostHighPriceBidHistoryInProduct(@Param("product") Product product);

    List<BidHistory> findAllByProduct(Product product);
}