package LeejuButU.BidCycle.domain.chat.room.repository;

import LeejuButU.BidCycle.domain.chat.room.domain.ChatRoom;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository {
    private final EntityManager em;

    public Long save(ChatRoom chatRoom) {
        em.persist(chatRoom);
        return chatRoom.getChatRoomId();
    }

    public Optional<ChatRoom> findById(Long chatRoomId) {
        ChatRoom chatRoom = em.find(ChatRoom.class, chatRoomId);
        return Optional.ofNullable(chatRoom);
    }

    public List<ChatRoom> findAll() {
        return em.createQuery("select r from ChatRoom r", ChatRoom.class)
                .getResultList();
    }

    public Long update(ChatRoom chatRoom) {
        em.merge(chatRoom);
        return chatRoom.getChatRoomId();
    }

    public void delete(ChatRoom chatRoom) {
        em.remove(chatRoom);
    }

    public void deleteById(Long chatRoomId) {
        ChatRoom chatRoom = em.find(ChatRoom.class, chatRoomId);
        em.remove(chatRoom);
    }
}
