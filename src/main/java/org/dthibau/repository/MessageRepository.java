package org.dthibau.repository;

import org.dthibau.domain.Message;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the Message entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select message from Message message where message.auteur.login = ?#{principal.username}")
    List<Message> findByAuteurIsCurrentUser();
    
    @Query("select m from Message m left join fetch m.responses where m = ?1")
    Message loadFull(Message m);

}
