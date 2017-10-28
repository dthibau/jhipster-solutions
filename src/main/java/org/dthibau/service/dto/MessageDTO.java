package org.dthibau.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.dthibau.domain.Message;
import org.dthibau.domain.Topic;
import org.dthibau.domain.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Message.
 */
public class MessageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public MessageDTO(Message m) {
    	this(m.getId(),m.getSubject(),m.getContent(),m.getPostDate(),m.getAuteur(),m.getTopic());
    }
    public MessageDTO(Long id, String subject, String content, ZonedDateTime postDate, 
			User auteur, Topic topic) {
		super();
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.postDate = postDate;
		this.auteur = auteur;
		this.topic = topic;
	}

 	private Long id;

    @NotNull
    @Size(min = 2, max = 80)
    private String subject;

    @Column(name = "content")
    private String content;

    @Column(name = "post_date")
    private ZonedDateTime postDate;

    private Set<MessageDTO> responses = new HashSet<>();

    private User auteur;

    private Topic topic;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public MessageDTO subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public MessageDTO content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getPostDate() {
        return postDate;
    }

    public MessageDTO postDate(ZonedDateTime postDate) {
        this.postDate = postDate;
        return this;
    }

    public void setPostDate(ZonedDateTime postDate) {
        this.postDate = postDate;
    }

    public Set<MessageDTO> getResponses() {
        return responses;
    }

    public MessageDTO responses(Set<MessageDTO> messages) {
        this.responses = messages;
        return this;
    }

    public MessageDTO addResponses(MessageDTO message) {
        this.responses.add(message);
        return this;
    }

    public MessageDTO removeResponses(MessageDTO message) {
        this.responses.remove(message);
        return this;
    }

    public void setResponses(Set<MessageDTO> messages) {
        this.responses = messages;
    }

    public User getAuteur() {
        return auteur;
    }

    public MessageDTO auteur(User user) {
        this.auteur = user;
        return this;
    }

    public void setAuteur(User user) {
        this.auteur = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public MessageDTO topic(Topic topic) {
        this.topic = topic;
        return this;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

     // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageDTO message = (MessageDTO) o;
        if (message.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), message.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Message{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", content='" + getContent() + "'" +
            ", postDate='" + getPostDate() + "'" +
            "}";
    }
}
