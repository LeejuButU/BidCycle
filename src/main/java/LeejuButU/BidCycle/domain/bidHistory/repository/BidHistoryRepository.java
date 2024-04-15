package LeejuButU.BidCycle.domain.bidHistory.repository;

import LeejuButU.BidCycle.domain.bidHistory.domain.BidHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidHistoryRepository extends JpaRepository<BidHistory, Long> {

}