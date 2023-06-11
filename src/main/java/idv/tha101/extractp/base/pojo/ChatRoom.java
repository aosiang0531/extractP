package idv.tha101.extractp.base.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 34789214329287934L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(value = AccessLevel.PRIVATE)
    private String name;

    @Setter(value = AccessLevel.PRIVATE)
    @Enumerated(value = EnumType.ORDINAL)
    private ChatRoomStatus status = ChatRoomStatus.ACTIVE;
    
    @JsonIgnore
    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private List<ChatMessage> messages = new ArrayList<>();

    public void addMessage(ChatMessage message) {
        messages.add(message);
    }

    public static ChatRoomBuilder getBuilder() {
        return new ChatRoomBuilder();
    }

    public static class ChatRoomBuilder {
        private String name;

        public ChatRoomBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ChatRoom build() {
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setName(name);
            return chatRoom;
        }
    }
}