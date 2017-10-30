package org.dthibau.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.dthibau.domain.Message;
import org.dthibau.service.dto.MessageDTO;
import org.dthibau.service.dto.NodeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity User and its DTO called MessageDTO.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Service
public class NodeMapper {

    public NodeDTO messageToNodeDTO(Message message) {
    	NodeDTO ret= new NodeDTO(message);
        
        for (Message m : message.getResponses() ) {
        	NodeDTO mTO = messageToNodeDTO(m);
        	ret.addChild(mTO);
        }
        
        return ret;
    }

    public List<NodeDTO> messagesToNodeDTOs(List<Message> messages) {
        return messages.stream()
            .filter(Objects::nonNull)
            .map(this::messageToNodeDTO)
            .collect(Collectors.toList());
    }

    public Page<NodeDTO> messagesToMessageDTOs(Page<Message> messages) {
        List<NodeDTO> messagesDTO = messagesToNodeDTOs(messages.getContent());
        
        return new PageImpl<NodeDTO>(messagesDTO);
        
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
