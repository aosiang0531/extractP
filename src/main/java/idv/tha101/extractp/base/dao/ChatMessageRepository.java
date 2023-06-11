package idv.tha101.extractp.base.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.tha101.extractp.base.pojo.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findAllByChatRoomId(Long id);
}