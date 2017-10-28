package org.dthibau.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.dthibau.domain.Message;
import org.dthibau.service.dto.MessageDTO;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity User and its DTO called MessageDTO.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class MessageMapper {

    public MessageDTO messageToMessageDTO(Message message) {
        MessageDTO ret= new MessageDTO(message);
        
        for (Message m : message.getResponses() ) {
        	MessageDTO mTO = messageToMessageDTO(m);
        	ret.addResponses(mTO);
        }
        
        return ret;
    }

    public List<MessageDTO> messagesToMessageDTOs(List<Message> messages) {
        return messages.stream()
            .filter(Objects::nonNull)
            .map(this::messageToMessageDTO)
            .collect(Collectors.toList());
    }

    public Message messageFromId(Long id) {
        if (id == null) {
            return null;
        }
        Message message = new Message();
        message.setId(id);
        return message;
    }

}
