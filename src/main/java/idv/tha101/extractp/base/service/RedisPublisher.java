package idv.tha101.extractp.base.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.base.dao.ChatMessageRepository;
import idv.tha101.extractp.base.dao.ChatRoomRepository;
import idv.tha101.extractp.base.pojo.ChatMessage;
import idv.tha101.extractp.base.pojo.ChatMessagePayload;
import idv.tha101.extractp.base.pojo.ChatRoom;

@Service
public class RedisPublisher {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@Autowired
	private ChatRoomRepository chatRoomRepository;

	public void publish(ChannelTopic topic, ChatMessagePayload message) {
		ChatRoom chatRoom = chatRoomRepository.findById(message.getChatRoomId())
				.orElseGet(() -> ChatRoom.getBuilder().withName(UUID.randomUUID().toString()).build());

		ChatMessage publishedMessage = ChatMessage.getBuilder().withChatRoom(chatRoom).withContent(message.getContent())
				.withSender(message.getSender()).withType(message.getMessageType()).build();

		chatRoom.addMessage(publishedMessage);
		chatMessageRepository.save(publishedMessage);
		Long l = redisTemplate.convertAndSend(topic.getTopic(), message);
		System.out.println("topic:" + topic.getTopic());
		System.out.println("result:" + l);
	}
}