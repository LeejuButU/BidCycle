package LeejuButU.BidCycle.fixture;

import LeejuButU.BidCycle.domain.bidHistory.domain.BidHistory;
import LeejuButU.BidCycle.domain.chat.message.domain.ChatMessage;
import LeejuButU.BidCycle.domain.chat.room.domain.ChatRoom;
import LeejuButU.BidCycle.domain.interestProduct.domain.InterestProduct;
import LeejuButU.BidCycle.domain.member.domain.Member;
import LeejuButU.BidCycle.domain.photo.domain.Photo;
import LeejuButU.BidCycle.domain.product.domain.Product;
import LeejuButU.BidCycle.domain.qna.domain.Qna;

import java.time.LocalDateTime;

public class Fixture {

    /* Member */
    public static Member generateMember(String loginId){
        return new Member(
                loginId,
                "1234",
                "Alice",
                "서울"
        );
    }

    /* Product */
    public static Product generateProduct(Member buyer, Member seller){
        return new Product(
                "노트북 팔아요.",
                "디지털기기",
                "미개봉 제품입니다. 색상은 화이트 입니다.",
                LocalDateTime.of(2024, 5, 9, 10, 40),
                LocalDateTime.of(2024, 10, 13, 0, 0),
                1500000L,
                2000000L,
                2500000L,
                "낙찰 완료",
                LocalDateTime.of(2024, 6, 8, 8, 5),
                buyer,
                seller
        );
    }

    /* Photo */
    public static Photo generatePhoto(Product product){
        return new Photo(
                "https://source.unsplash.com/featured/?laptop",
                product
        );
    }

    /* Q&A */
    public static Qna generateQna(Product product){
        return new Qna(
                "몇인치인가요?",
                "사진만 봐서는 잘 모르겠는데 몇인치인지 알려주실 수 있나요?",
                LocalDateTime.of(2024, 5, 15, 3, 20),
                "14인치입니다!",
                product
        );
    }

    /* Interest Product */
    public static InterestProduct generateInterestProduct(Member member, Product product){
        return new InterestProduct(member, product);
    }

    /* BidHistory */
    public static BidHistory generateBidHistory(Member bidder, Product product){
        return new BidHistory(
            300000L,
            LocalDateTime.of(2024, 8, 25, 7, 40),
            bidder,
            product
        );
    }

    /* ChatRoom */
    public static ChatRoom generateChatRoom(Member seller, Member buyer){
        return new ChatRoom(seller, buyer);
    }

    /* ChatMessage */
    public static ChatMessage generateChatMessage(Member seller, ChatRoom chatRoom){
        return new ChatMessage(
                "안녕하세요.",
                "https://source.unsplash.com/featured/?computer",
                LocalDateTime.of(2024, 5, 25, 9, 40),
                seller,
                chatRoom
        );
    }

}
