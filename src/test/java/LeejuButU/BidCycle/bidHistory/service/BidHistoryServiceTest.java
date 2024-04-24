package LeejuButU.BidCycle.bidHistory.service;

import LeejuButU.BidCycle.domain.bidHistory.domain.BidHistory;
import LeejuButU.BidCycle.domain.bidHistory.repository.BidHistoryRepository;
import LeejuButU.BidCycle.domain.bidHistory.service.BidHistoryService;
import LeejuButU.BidCycle.domain.member.domain.Member;
import LeejuButU.BidCycle.domain.member.repository.MemberRepository;
import LeejuButU.BidCycle.domain.product.domain.Product;
import LeejuButU.BidCycle.domain.product.repository.ProductRepository;
import LeejuButU.BidCycle.fixture.Fixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BidHistoryServiceTest {
    @Mock
    private BidHistoryRepository bidHistoryRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private ProductRepository productRepository;

    private Member bidder, seller;
    private Product product;

    @InjectMocks
    private BidHistoryService bidHistoryService;

    @BeforeEach
    public void setUp() {
        bidder = Fixture.generateMember("buyer");
        seller = Fixture.generateMember("seller");
        product = Fixture.generateProduct(LocalDateTime.now(), bidder, seller);
    }

    @Test
    @DisplayName("신규 입찰 테스트")
    public void bidProductTest() {
        // given
        when(memberRepository.findById(anyLong()))
                .thenReturn(Optional.of(seller));
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.of(product));

        // when
        BidHistory createdBidHistory = bidHistoryService.bidProduct(1L, 1L, 2000);

        // then
        verify(bidHistoryRepository, times(1)).save(createdBidHistory);
    }

    @Test
    @DisplayName("입찰 취소 테스트")
    public void deleteBidHistoryTest() {
        // given & when
        bidHistoryService.deleteBidHistory(1L);

        // then
        verify(bidHistoryRepository, times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("특정 상품의 모든 입찰 내역 조회 테스트")
    public void findAllBidHistoryOfProductTest() {
        // given
        BidHistory bidHistory = Fixture.generateBidHistory(bidder, product);
        when(bidHistoryRepository.findAllByProduct(product))
                .thenReturn(List.of(bidHistory, bidHistory));
        // when
        List<BidHistory> bidHistories = bidHistoryService.findAllBidHistoryOfProduct(product);

        // then
        Assertions.assertThat(bidHistories.size()).isEqualTo(2);
    }
}
