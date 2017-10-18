package org.dthibau.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Message.
 */
@Entity
@Table(name = "message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 2, max = 80)
    @Column(name = "subject", length = 80, nullable = false)
    private String subject;

    @Column(name = "content")
    private String content;

    @Column(name = "post_date")
    private ZonedDateTime postDate;

    @OneToMany(mappedBy = "message")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Message> responses = new HashSet<>();

    @ManyToOne
    private User auteur;

    @ManyToOne
    private Topic topic;

    @ManyToOne
    private Message message;

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

    public Message subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public Message content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getPostDate() {
        return postDate;
    }

    public Message postDate(ZonedDateTime postDate) {
        this.postDate = postDate;
        return this;
    }

    public void setPostDate(ZonedDateTime postDate) {
        this.postDate = postDate;
    }

    public Set<Message> getResponses() {
        return responses;
    }

    public Message responses(Set<Message> messages) {
        this.responses = messages;
        return this;
    }

    public Message addResponses(Message message) {
        this.responses.add(message);
        message.setMessage(this);
        return this;
    }

    public Message removeResponses(Message message) {
        this.responses.remove(message);
        message.setMessage(null);
        return this;
    }

    public void setResponses(Set<Message> messages) {
        this.responses = messages;
    }

    public User getAuteur() {
        return auteur;
    }

    public Message auteur(User user) {
        this.auteur = user;
        return this;
    }

    public void setAuteur(User user) {
        this.auteur = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public Message topic(Topic topic) {
        this.topic = topic;
        return this;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Message getMessage() {
        return message;
    }

    public Message message(Message message) {
        this.message = message;
        return this;
    }

    public void setMessage(Message message) {
        this.message = message;
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
        Message message = (Message) o;
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
