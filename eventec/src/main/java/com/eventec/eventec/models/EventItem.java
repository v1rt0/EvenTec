package com.eventec.eventec.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "event_items")
public class EventItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String category;
    @Column(name = "date_event")
    private LocalDateTime dateEvent;

    @Override
    public String toString() {
        return String.format(
                "EventItem{id=%d, title='%s', description='%s', category='%s', dateEvent='%s'}",
                id, title, description, category, dateEvent);
    }
}