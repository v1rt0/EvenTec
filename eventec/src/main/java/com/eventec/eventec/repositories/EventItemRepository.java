package com.eventec.eventec.repositories;

import com.eventec.eventec.models.EventItem;
import com.eventec.eventec.models.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventItemRepository extends JpaRepository<EventItem, Long> {
    List<EventItem> findByUser(UserItem user);

    List<EventItem> findAll();

}
