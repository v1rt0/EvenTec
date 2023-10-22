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

        String userEmail = eventItem.getUser().getEmail();
        String userPassword = eventItem.getUser().getPassword();
        Optional<UserItem> userOpt = userItemService.getByEmailAndPassword(userEmail, userPassword);

        if(userOpt.isPresent()) {
            UserItem user = userOpt.get();
            eventItem.setUser(user);

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
    @GetMapping("/allEvents")
    public ResponseEntity<?> getAllEvents() {
        List<EventItem> events = eventItemService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/pendingEvents")
    public ResponseEntity<?> getPendingEvents(@RequestParam String email, @RequestParam String password) {

        System.out.println("Email recebido: " + email);
        System.out.println("Senha recebida: " + password);

        Optional<UserItem> user = userItemService.getByEmailAndPassword(email, password);

        if (!user.isPresent()) {
            System.out.println("Usuário não encontrado ou credenciais incorretas.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }

        if (user.get().getUserType() != UserItem.UserType.diretor) {
            System.out.println("Usuário não é um diretor.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não é um diretor.");
        }

        String unidade = user.get().getUnidade();
        if (unidade == null || unidade.trim().isEmpty()) {
            System.out.println("Unidade do diretor não especificada.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unidade do diretor não especificada.");
        }

        List<EventItem> pendingEvents = eventItemService.getPendingEventsByAddress(unidade);

        System.out.println("Eventos pendentes encontrados: " + pendingEvents.size());
        return ResponseEntity.ok(pendingEvents);
    }


    @PutMapping("/approveEvent/{id}")
    public ResponseEntity<?> approveEvent(@PathVariable Long id, @RequestParam String email, @RequestParam String password) {
        Optional<UserItem> user = userItemService.getByEmailAndPassword(email, password);

        if (user.isPresent() && user.get().getUserType() == UserItem.UserType.diretor) {
            Optional<EventItem> event = eventItemService.findById(id);
            if (event.isPresent() && event.get().getAddress().equals(user.get().getUnidade())) {
                EventItem eventItem = event.get();
                eventItem.setApproved(true);
                eventItemService.save(eventItem);
                return ResponseEntity.ok("Evento aprovado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não encontrado ou não pertence à mesma unidade.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado ou não é um diretor.");
        }
    }

    @GetMapping("/approvedEvents")
    public ResponseEntity<?> getApprovedEvents() {
        List<EventItem> approvedEvents = eventItemService.getApprovedEvents();
        return ResponseEntity.ok(approvedEvents);
    }

}