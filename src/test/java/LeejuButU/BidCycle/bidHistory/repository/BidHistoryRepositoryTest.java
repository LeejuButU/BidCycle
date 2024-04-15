package LeejuButU.BidCycle.bidHistory.repository;

import LeejuButU.BidCycle.domain.bidHistory.domain.BidHistory;
import LeejuButU.BidCycle.domain.bidHistory.repository.BidHistoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BidHistoryRepositoryTest {
    @Autowired
    private BidHistoryRepository repository; // 빈으로 등록이 왜 안돼징

    @Test
    @DisplayName("없는 ID를 조회하면 오류가 발생한다.")
    void test_find_by_unknown_id() {
        Assertions.assertThatThrownBy(()->{
            BidHistory bidHistory = repository.findById(1L).orElseThrow(IllegalArgumentException::new);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("객체를 저장하면 저장한 객체의 Id를 돌려준다.")
    void test_save() {
        // 아직 BidHistory 생성자가 없음.
//        BidHistory bidHistory = new BidHistory();
//        Long historyId = repository.save(bidHistory);
//        Assertions.assertThat(historyId).isEqualTo(bidHistory.getBidHistoryId());
    }
}