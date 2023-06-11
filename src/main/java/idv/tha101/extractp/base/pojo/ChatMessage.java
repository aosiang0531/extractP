package idv.tha101.extractp.base.pojo;
import java.awt.TrayIcon.MessageType;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(value = AccessLevel.PRIVATE)
    @Column(nullable = false)
    private String content;

    @Setter(value = AccessLevel.PRIVATE)
    @Column(nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private MessageType type;

    @Setter(value = AccessLevel.PRIVATE)
    @Column(name = "is_read", nullable = false)
    private boolean isRead = false;

    @Setter(value = AccessLevel.PRIVATE)
    @Column(nullable = false)
    private String sender;

    @Setter(value = AccessLevel.PRIVATE)
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    public static ChatMessageBuilder getBuilder() {
        return new ChatMessageBuilder();
    }

    public static class ChatMessageBuilder {
        private String content;
        private MessageType type;
        private String sender;
        private ChatRoom chatRoom;

        public ChatMessageBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public ChatMessageBuilder withType(MessageType type) {
            this.type = type;
            return this;
        }

        public ChatMessageBuilder withSender(String sender) {
            this.sender = sender;
            return this;
        }

        public ChatMessageBuilder withChatRoom(ChatRoom chatRoom) {
            this.chatRoom = chatRoom;
            return this;
        }

        public ChatMessage build() {
            ChatMessage message = new ChatMessage();
            message.setContent(content);
            message.setType(type);
            message.setSender(sender);
            message.setChatRoom(chatRoom);
            return message;
        }
    }
}