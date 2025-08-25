package Dhiraj.sPortfolio.My_Portfolio.mapper;

import Dhiraj.sPortfolio.My_Portfolio.dto.MessageDto;
import Dhiraj.sPortfolio.My_Portfolio.entity.Message;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MessageMapper {

    public static MessageDto toDto(Message message) {
        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setName(message.getName());
        dto.setEmail(message.getEmail());
        dto.setMessage(message.getMessage());
        return dto;
    }

    public static Message toEntity(MessageDto dto) {
        Message message = new Message();
        message.setId(dto.getId());
        message.setName(dto.getName());
        message.setEmail(dto.getEmail());
        message.setMessage(dto.getMessage());
        return message;
    }
}