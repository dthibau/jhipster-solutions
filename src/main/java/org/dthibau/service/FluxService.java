package org.dthibau.service;

import java.util.ArrayList;
import java.util.List;

import org.dthibau.domain.Message;
import org.dthibau.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FluxService {

	private final Logger log = LoggerFactory.getLogger(FluxService.class);

	@Autowired
	MessageRepository messageRepository;

	public Message loadFlux(Message message) {

		return _fullLoad(message);

	}

	public List<Message> loadFlux(List<Message> messages) {

		List<Message> ret = new ArrayList<>();
		for (Message m : messages) {
			ret.add(_fullLoad(m));
		}
		return ret;

	}

	private Message _fullLoad(Message root) {

		root = messageRepository.loadFull(root);

		for (Message child : root.getResponses()) {
			_fullLoad(child);
		}
		return root;
	}

}
