package LeejuButU.BidCycle.domain.bidHistory.service;

import LeejuButU.BidCycle.domain.bidHistory.domain.BidHistory;
import LeejuButU.BidCycle.domain.bidHistory.repository.BidHistoryRepository;
import LeejuButU.BidCycle.domain.member.domain.Member;
import LeejuButU.BidCycle.domain.member.repository.MemberRepository;
import LeejuButU.BidCycle.domain.product.domain.Product;
import LeejuButU.BidCycle.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BidHistoryService {
    private final BidHistoryRepository bidHistoryRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    // 입찰하기, 가장 최근 입찰보다 반드시 높은 가격을 불러야 함
    @Transactional
    public BidHistory bidProduct(Long bidderId, Long productId, long price) {
        Member bidder = memberRepository.findById(bidderId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 멤버입니다.")
        );
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 상품입니다.")
        );

        validateBidPriceIsGreaterThanBefore(product, price);

        BidHistory bidHistory = BidHistory.builder()
                .bidder(bidder)
                .product(product)
                .bidPrice(price)
                .bidTime(LocalDateTime.now())
                .build();

        product.updatePrice(price);
        bidHistoryRepository.save(bidHistory);
        return bidHistory;
    }

    // 입찰 취소
    public void deleteBidHistory(Long bidHistoryId) {
        bidHistoryRepository.deleteById(bidHistoryId);
    }

    // 상품 하나에 대한 입찰 내역 데이터 조회
    public List<BidHistory> findAllBidHistoryOfProduct(Product product) {
        return bidHistoryRepository.findAllByProduct(product);
    }

    private void validateBidPriceIsGreaterThanBefore(Product product, long price) {
        bidHistoryRepository.findMostHighPriceBidHistoryInProduct(product)
                .ifPresentOrElse(bidHistory -> {
                    if (price <= bidHistory.getBidPrice()) {
                        throw new IllegalArgumentException("가격은 반드시 이전 가격보다 높아야 합니다.");
                    }
                }, () -> {
                    if (price < product.getStartPrice()) {
                        throw new IllegalArgumentException("입찰 가격은 product에서 설정된 시작 가격보다 높아야 합니다.");
                    }
                });
    }
}
