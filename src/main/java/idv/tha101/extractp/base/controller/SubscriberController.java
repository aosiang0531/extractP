package idv.tha101.extractp.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.dao.ChatMessageRepository;
import idv.tha101.extractp.base.dao.ChatRoomRepository;
import idv.tha101.extractp.base.pojo.ChatRoom;
import idv.tha101.extractp.base.service.RedisSubscriber;

@RestController
@RequestMapping(path = "/chat")
public class SubscriberController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private RedisSubscriber redisSubscriber;

    @Autowired
    private RedisMessageListenerContainer redisMessageListener;

    @PostMapping
    @Transactional
    public ChatRoom createChatRoom(@RequestParam String name) {
        ChatRoom newChatRoom = ChatRoom.getBuilder()
                .withName(name)
                .build();
        ChatRoom chatRoom = chatRoomRepository.save(newChatRoom);
        ChannelTopic topic = ChannelTopic.of(chatRoom.getId().toString());
        redisMessageListener.addMessageListener(redisSubscriber, topic);
        return chatRoom;
    }

    @GetMapping
    @Transactional
    public List<ChatRoom> getChatRooms() {
        return chatRoomRepository.findAll();
    }

    @GetMapping(path = "/rooms/{chatRoomId}/messages")
    @Transactional
    public ResponseEntity<?> getChatMessages(@PathVariable("chatRoomId") Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElse(null);

        if (chatRoom != null) {
            return new ResponseEntity<>(chatRoom.getMessages(), HttpStatus.OK);
        }
        System.out.printf("Error message - chat room not found with id: {}", chatRoomId);
        return new ResponseEntity<>("Error message - chat room not found", HttpStatus.NOT_FOUND);
    }
}