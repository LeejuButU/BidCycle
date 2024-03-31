package LeejuButU.BidCycle.domain.chat.message.domain;

import LeejuButU.BidCycle.domain.chat.room.domain.ChatRoom;
import LeejuButU.BidCycle.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageId;

    @Column(updatable = false)
    private String message;

    @Column(updatable = false)
    private String imageURL;

    @Column(updatable = false, nullable = false)
    private LocalDateTime sendTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", updatable = false, nullable = false)
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", updatable = false, nullable = false)
    private ChatRoom chatRoom;
}
