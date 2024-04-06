package LeejuButU.BidCycle.domain.bidHistory.repository;

import LeejuButU.BidCycle.domain.bidHistory.domain.BidHistory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BidHistoryRepository {
    private final EntityManager entityManager;

    public Long save(BidHistory bidHistory) {
        entityManager.persist(bidHistory);
        return bidHistory.getBidHistoryId();
    }

    public Optional<BidHistory> findById(Long bidHistoryId) {
        BidHistory bidHistory = entityManager.find(BidHistory.class, bidHistoryId);
        return Optional.ofNullable(bidHistory);
    }

    public List<BidHistory> findAll() {
        return entityManager
                .createQuery("select h from BidHistory h", BidHistory.class)
                .getResultList();
    }

    public Long update(BidHistory bidHistory) {
        entityManager.merge(bidHistory);
        return bidHistory.getBidHistoryId();
    }

    public void delete(BidHistory bidHistory) {
        entityManager.remove(bidHistory);
    }

    public void deleteById(Long bidHistoryId) {
        BidHistory history = findById(bidHistoryId).orElseThrow(IllegalArgumentException::new);
        entityManager.remove(history);
    }
}
