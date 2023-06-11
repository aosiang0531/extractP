package idv.tha101.extractp.base.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.tha101.extractp.base.pojo.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	Optional<ChatRoom> findById(Long id);
}