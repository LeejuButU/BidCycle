package LeejuButU.BidCycle.domain.chat.message.repository;

import LeejuButU.BidCycle.domain.chat.message.domain.ChatMessage;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatMessageRepository {
    private final EntityManager em;

    public Long save(ChatMessage chatMessage) {
        em.persist(chatMessage);
        return chatMessage.getChatMessageId();
    }

    public Optional<ChatMessage> findById(Long chatMessageId) {
        ChatMessage chatMessage = em.find(ChatMessage.class, chatMessageId);
        return Optional.ofNullable(chatMessage);
    }

    public List<ChatMessage> findAll() {
        return em.createQuery("select m from ChatMessage m", ChatMessage.class)
                .getResultList();
    }

    public Long update(ChatMessage chatMessage) {
        em.merge(chatMessage);
        return chatMessage.getChatMessageId();
    }

    public void delete(ChatMessage chatMessage) {
        em.remove(chatMessage);
    }

    public void deleteById(Long chatMessageId) {
        ChatMessage chatMessage = findById(chatMessageId).orElseThrow(IllegalArgumentException::new);
        em.remove(chatMessage);
    }
}
