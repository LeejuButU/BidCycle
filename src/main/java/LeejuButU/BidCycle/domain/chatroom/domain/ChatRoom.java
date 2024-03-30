package LeejuButU.BidCycle.domain.chatroom.domain;

import jakarta.persistence.*;
import org.springframework.data.util.Lazy;

@Entity
public class ChatRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", updatable = false, nullable = false)
    Member seller;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", updatable = false, nullable = false)
    Member buyer;
}
