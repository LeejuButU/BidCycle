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

    public Optional<BidHistory> findById(Long id) {
        BidHistory bidHistory = entityManager.find(BidHistory.class, id);
        if (bidHistory == null)
            return Optional.empty();
        return Optional.of(bidHistory);
    }

    public Long save(BidHistory bidHistory) {
        entityManager.persist(bidHistory);
        return bidHistory.getBidHistoryId();
    }

    public List<BidHistory> findAll() {
        return entityManager
                .createQuery("select h from BidHistory h", BidHistory.class)
                .getResultList();
    }

    public void delete(BidHistory bidHistory) {
        entityManager.remove(bidHistory);
    }

    public void deleteById(Long historyId) {
        BidHistory history = findById(historyId).orElseThrow(IllegalArgumentException::new);
        entityManager.remove(history);
    }
}
