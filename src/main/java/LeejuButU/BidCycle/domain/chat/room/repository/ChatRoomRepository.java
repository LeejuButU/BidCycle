package LeejuButU.BidCycle.domain.chat.room.repository;

import LeejuButU.BidCycle.domain.chat.room.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {}