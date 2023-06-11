package idv.tha101.extractp.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.pojo.ChatMessagePayload;
import idv.tha101.extractp.base.service.ChatService;
import idv.tha101.extractp.base.service.RedisPublisher;

@RestController
public class PublisherController {

	@Autowired
	private RedisPublisher redisPublisher;

	@Autowired
	private ChatService chatService;

	@MessageMapping(value = "/chat/message")
	@Transactional
	public void sendMessage(ChatMessagePayload message) {
		String topic = message.getChatRoomId().toString();
		chatService.enterChatRoom(message.getChatRoomId());
		redisPublisher.publish(ChannelTopic.of(topic), message);
	}
}