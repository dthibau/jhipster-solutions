package org.dthibau.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.dthibau.domain.Message;
import org.dthibau.repository.MessageRepository;
import org.dthibau.service.FluxService;
import org.dthibau.web.rest.errors.BadRequestAlertException;
import org.dthibau.service.dto.MessageDTO;
import org.dthibau.service.mapper.MessageMapper;
import org.dthibau.web.rest.util.HeaderUtil;
import org.dthibau.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing Message.
 */
@RestController
@RequestMapping("/api")
public class MessageResource {

    private final Logger log = LoggerFactory.getLogger(MessageResource.class);

    private static final String ENTITY_NAME = "message";

    private final MessageRepository messageRepository;

    private final MessageMapper messageMapper;

    private final FluxService fluxService;
    
    public MessageResource(MessageRepository messageRepository, MessageMapper messageMapper, FluxService fluxService) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.fluxService = fluxService;
    }

    /**
     * POST  /messages : Create a new message.
     *
     * @param message the message to create
     * @return the ResponseEntity with status 201 (Created) and with body the new message, or with status 400 (Bad Request) if the message has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/messages")
    @Timed
    public ResponseEntity<Message> createMessage(@Valid @RequestBody Message message) throws URISyntaxException {
        log.debug("REST request to save Message : {}", message);
        if (message.getId() != null) {
            throw new BadRequestAlertException("A new message cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Message result = messageRepository.save(message);
        return ResponseEntity.created(new URI("/api/messages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /messages : Updates an existing message.
     *
     * @param message the message to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated message,
     * or with status 400 (Bad Request) if the message is not valid,
     * or with status 500 (Internal Server Error) if the message couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/messages")
    @Timed
    public ResponseEntity<Message> updateMessage(@Valid @RequestBody Message message) throws URISyntaxException {
        log.debug("REST request to update Message : {}", message);
        if (message.getId() == null) {
            return createMessage(message);
        }
        Message result = messageRepository.save(message);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, message.getId().toString()))
            .body(result);
    }

    /**
     * GET  /messages : get all the messages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of messages in body
     */
    @GetMapping("/messages")
    @Timed
    public ResponseEntity<List<Message>> getAllMessages(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Messages");
        Page<Message> page = messageRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/messages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /messages/:id : get the "id" message.
     *
     * @param id the id of the message to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the message, or with status 404 (Not Found)
     */
    @GetMapping("/messages/{id}")
    @Timed
    public ResponseEntity<Message> getMessage(@PathVariable Long id) {
        log.debug("REST request to get Message : {}", id);
        Message message = messageRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(message));
    }

    /**
     * DELETE  /messages/:id : delete the "id" message.
     *
     * @param id the id of the message to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/messages/{id}")
    @Timed
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        log.debug("REST request to delete Message : {}", id);
        messageRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /messages : get all the messages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of messages in body
     */
    @GetMapping("/flux")
    @Timed
    public ResponseEntity<List<MessageDTO>> getAllFlux(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Messages");
        Page<Message> page = messageRepository.findAll(pageable);
        
        List<Message> messages = fluxService.loadFlux(page.getContent());
        List<MessageDTO> messagesDTO = messageMapper.messagesToMessageDTOs(messages);
        
        
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/flux");
        return new ResponseEntity<>(messagesDTO, headers, HttpStatus.OK);
    }

}
