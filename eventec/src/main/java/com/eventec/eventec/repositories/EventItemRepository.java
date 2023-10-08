package com.eventec.eventec.repositories;

import com.eventec.eventec.models.EventItem;
import com.eventec.eventec.models.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventItemRepository extends JpaRepository<EventItem, Long> {
    Optional<EventItem> findByTitleAndId(String title, Long id);
}
