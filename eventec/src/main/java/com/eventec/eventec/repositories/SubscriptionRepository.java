package com.eventec.eventec.repositories;

import com.eventec.eventec.models.SubscriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionItem, Long> {
    // Métodos específicos de consulta, se necessário
}
