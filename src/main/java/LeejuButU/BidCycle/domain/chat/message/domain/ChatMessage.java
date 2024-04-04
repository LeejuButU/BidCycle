package LeejuButU.BidCycle.domain.chat.message.domain;

import LeejuButU.BidCycle.domain.chat.room.domain.ChatRoom;
import LeejuButU.BidCycle.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageId;

    @Column(updatable = false, length = 500)
    private String message;

    @Column(updatable = false, length = 200)
    private String imageURL;

    @NonNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime sendTime;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false, updatable = false)
    private Member sender;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false, updatable = false)
    private ChatRoom chatRoom;
}
