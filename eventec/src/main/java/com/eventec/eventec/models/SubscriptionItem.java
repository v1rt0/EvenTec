package com.eventec.eventec.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "subscriptions")
public class SubscriptionItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subscriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserItem user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventItem event;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate = LocalDateTime.now();

    @Override
    public String toString() {
        return String.format(
                "Subscription{subscriptionId=%d, user=%s, event=%s, subscriptionDate=%s}",
                subscriptionId, user.getUserid(), event.getId(), subscriptionDate);

    }
}
