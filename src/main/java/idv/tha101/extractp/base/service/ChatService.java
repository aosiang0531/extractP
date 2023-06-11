package idv.tha101.extractp.base.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private final RedisSubscriber redisSubscriber;
	private final RedisMessageListenerContainer redisMessageListener;
	private Map<Long, ChannelTopic> topics;

	@Autowired
	public ChatService(RedisSubscriber redisSubscriber, RedisMessageListenerContainer redisMessageListener) {
		this.redisSubscriber = redisSubscriber;
		this.redisMessageListener = redisMessageListener;
		this.topics = new HashMap<>();
	}

	public void enterChatRoom(Long id) {
		ChannelTopic topic = topics.get(id);
		if (topic == null) {
			topic = new ChannelTopic(id.toString());
			redisMessageListener.addMessageListener(redisSubscriber, topic);
			topics.put(id, topic);
		}
	}
}