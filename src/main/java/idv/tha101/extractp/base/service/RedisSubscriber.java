package idv.tha101.extractp.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import idv.tha101.extractp.base.pojo.ChatMessagePayload;

@Service
public class RedisSubscriber implements MessageListener {

	private final ObjectMapper objectMapper;
	private final RedisTemplate<String, Object> redisTemplate;
	private final SimpMessageSendingOperations messagingTemplate;

	@Autowired
	public RedisSubscriber(ObjectMapper objectMapper, RedisTemplate<String, Object> redisTemplate,
			SimpMessageSendingOperations messagingTemplate) {
		this.objectMapper = objectMapper;
		this.redisTemplate = redisTemplate;
		this.messagingTemplate = messagingTemplate;
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			String publishedMessage = redisTemplate.getStringSerializer().deserialize(message.getBody());
			System.out.println("publishedMessage:" + publishedMessage);

			ChatMessagePayload chatMessage = objectMapper.readValue(publishedMessage, ChatMessagePayload.class);

			messagingTemplate.convertAndSend("/sub/chat/rooms/" + chatMessage.getChatRoomId().toString(), chatMessage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}