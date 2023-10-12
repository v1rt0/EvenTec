package com.eventec.eventec.controllers;

import com.eventec.eventec.models.EventItem;
import com.eventec.eventec.models.UserItem;
import com.eventec.eventec.services.EventItemService;
import com.eventec.eventec.services.UserItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class EventFormController {

    @Autowired
    private EventItemService eventItemService;

    @Autowired
    private UserItemService userItemService;

    @PostMapping("/event")
    public ResponseEntity<?> createEventItem(@RequestBody @Valid EventItem eventItem) {

        // Obtendo detalhes do usuário logado através do localStorage. Isso deve ser modificado de acordo com sua implementação exata.
        String userEmail = eventItem.getUser().getEmail();
        String userPassword = eventItem.getUser().getPassword();
        Optional<UserItem> userOpt = userItemService.getByEmailAndPassword(userEmail, userPassword);

        if(userOpt.isPresent()) {
            // Associar o Usuário ao Evento
            UserItem user = userOpt.get();
            eventItem.setUser(user); // associa o usuário ao evento

            eventItemService.save(eventItem);
            return ResponseEntity.ok("Evento criado com sucesso!");
        } else {
            // Lide com o cenário em que o usuário não foi encontrado, talvez retornando um erro.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado.");
        }
    }

    @GetMapping("/myEvents")
    public ResponseEntity<?> getMyEvents(@RequestParam String email, @RequestParam String password) {
        System.out.println("Buscando eventos para o usuário: " + email); // Log para diagnóstico

        Optional<UserItem> user = userItemService.getByEmailAndPassword(email, password);

        if (user.isPresent()) {
            List<EventItem> myEvents = eventItemService.getEventsByUser(user.get());

            System.out.println("Eventos encontrados: " + myEvents.size()); // Log para diagnóstico

            return ResponseEntity.ok(myEvents);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }
    }
    @DeleteMapping("/event/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        if (eventItemService.existsById(id)) {
            eventItemService.deleteById(id);
            return ResponseEntity.ok("Evento excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não encontrado.");
        }
    }

    @PutMapping("/eventEdit/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody @Valid EventItem updatedEvent) {
        // Verifique se o evento com o ID especificado existe no banco de dados
        Optional<EventItem> existingEventOpt = eventItemService.findById(id);

        if (existingEventOpt.isPresent()) {
            EventItem existingEvent = existingEventOpt.get();

            existingEvent.setDateEvent(updatedEvent.getDateEvent());

            eventItemService.save(existingEvent);

            return ResponseEntity.ok("Evento atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não encontrado.");
        }
    }


}