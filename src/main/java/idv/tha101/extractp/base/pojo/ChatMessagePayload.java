package idv.tha101.extractp.base.pojo;

import java.awt.TrayIcon.MessageType;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessagePayload {

	@NotBlank
	private String content;

	@NotBlank
	private String sender;

	@NotBlank
	@JsonProperty(value = "message_type")
	private MessageType messageType;

	@NotNull
	@JsonProperty(value = "chat_room_id")
	private Long chatRoomId;
}