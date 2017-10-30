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
 * A Node in a tree.
 */
public class NodeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public NodeDTO(Message m) {
    	this(m.getId(),m.getSubject());
    }
    public NodeDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;

	}

 	private Long id;

    @NotNull
    @Size(min = 2, max = 80)
    private String name;

    private Set<NodeDTO> children = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public NodeDTO name(String subject) {
        this.name = subject;
        return this;
    }

    public void setName(String subject) {
        this.name = subject;
    }



    public Set<NodeDTO> getChildren() {
        return children;
    }

    public NodeDTO children(Set<NodeDTO> messages) {
        this.children = messages;
        return this;
    }

    public NodeDTO addChild(NodeDTO message) {
        this.children.add(message);
        return this;
    }

    public NodeDTO removeChild(NodeDTO message) {
        this.children.remove(message);
        return this;
    }

    public void setChildren(Set<NodeDTO> messages) {
        this.children = messages;
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
        NodeDTO message = (NodeDTO) o;
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
            ", name='" + getName() + "'" +
            "}";
    }
}
