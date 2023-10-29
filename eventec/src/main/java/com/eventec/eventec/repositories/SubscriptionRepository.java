package com.eventec.eventec.repositories;

import com.eventec.eventec.models.SubscriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionItem, Long> {
    List<SubscriptionItem> findByUser_Userid(Long userId);
    List<SubscriptionItem> findAllByEvent_Id(Long eventId);

}
