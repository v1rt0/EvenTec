package com.eventec.eventec.controllers;

import com.eventec.eventec.models.EventItem;
import com.eventec.eventec.services.EventItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
public class EventFormController {
    @Autowired
    private EventItemService eventItemService;

    @PostMapping("/event")
    public String createEventItem(@RequestBody @Valid EventItem eventItem, BindingResult result, Model model) {
        EventItem item = new EventItem();
        item.setTitle(eventItem.getTitle());
        item.setDescription(eventItem.getDescription());
        item.setCategory(eventItem.getCategory());
        // item.setDateEvent(eventItem.());

        eventItemService.save(eventItem);
        return "redirect:/";
    }
}

