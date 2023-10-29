package com.eventec.eventec.repositories;

import com.eventec.eventec.models.CertificationItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eventec.eventec.models.SubscriptionItem;

import java.util.List;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationItem, Long> {

}
