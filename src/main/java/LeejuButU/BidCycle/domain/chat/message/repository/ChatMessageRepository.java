package LeejuButU.BidCycle.domain.chat.message.repository;

import LeejuButU.BidCycle.domain.chat.message.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}