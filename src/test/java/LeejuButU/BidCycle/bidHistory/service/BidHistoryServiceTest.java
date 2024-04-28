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
    private BidHistory lastBidHistory;

    @InjectMocks
    private BidHistoryService bidHistoryService;

    @BeforeEach
    public void setUp() {
        bidder = Fixture.generateMember("buyer");
        seller = Fixture.generateMember("seller");
        product = Fixture.generateProduct(LocalDateTime.now(), bidder, seller);
        lastBidHistory = Fixture.generateBidHistory(bidder, product);
    }

    @Test
    @DisplayName("최초 입찰 시 product에서 설정한 입찰가보다 높은 가격을 제시해야 한다.")
    public void bidProductTest() {
        // given
        when(memberRepository.findById(anyLong()))
                .thenReturn(Optional.of(seller));
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.of(product));

        // when
        BidHistory createdBidHistory = bidHistoryService.bidProduct(1L, 1L, 1500000);

        // then
        verify(bidHistoryRepository, times(1)).save(createdBidHistory);
    }

    @Test
    @DisplayName("최초 입찰 시 product에서 설정한 입찰 시작가보다 낮은 가격을 제시하면 에러가 발생한다.")
    public void bidProductWithLowerPriceThanStartPriceTest() {
        // given
        when(memberRepository.findById(anyLong()))
                .thenReturn(Optional.of(seller));
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.of(product));

        // when & then
        Assertions.assertThatThrownBy(
                () -> {bidHistoryService.bidProduct(1L, 1L, 15000);})
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("추가 입찰 시 기존 입찰가보다 낮은 가격을 제시하면 에러가 발생한다.")
    public void bidProductWithLowerPriceTest() {
        // given
        when(memberRepository.findById(anyLong()))
                .thenReturn(Optional.of(seller));
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.of(product));
        when(bidHistoryRepository.findMostHighPriceBidHistoryInProduct(any()))
                .thenReturn(Optional.of(lastBidHistory));

        // when & then
        Assertions.assertThatThrownBy(() -> {
            bidHistoryService.bidProduct(1L, 1L, lastBidHistory.getBidPrice());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("추가 입찰 시 기존 입찰가와 같은 가격을 제시하면 에러가 발생한다.")
    public void bidProductWithSamePriceTest() {
        // given
        when(memberRepository.findById(anyLong()))
                .thenReturn(Optional.of(seller));
        when(productRepository.findById(anyLong()))
                .thenReturn(Optional.of(product));
        when(bidHistoryRepository.findMostHighPriceBidHistoryInProduct(any()))
                .thenReturn(Optional.of(lastBidHistory));

        // when & then
        Assertions.assertThatThrownBy(() -> {
            BidHistory createdBidHistory = bidHistoryService.bidProduct(1L, 1L, 2000);
        }).isInstanceOf(IllegalArgumentException.class);
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
