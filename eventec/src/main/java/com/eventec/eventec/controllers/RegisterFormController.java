package com.eventec.eventec.controllers;

import com.eventec.eventec.models.UserItem;
import com.eventec.eventec.repositories.UserItemRepository;
import com.eventec.eventec.services.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/users")
public class RegisterFormController {

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";
    private static final String USER_NOT_FOUND_MESSAGE = "User not found";
    private static final String CROSS_ORIGIN_URL = "http://localhost:3000";

    @Autowired
    private UserItemService userItemService;
    private final UserItemRepository userItemRepository;

    @GetMapping("/register")
    public String showCreatedForm(UserItem userItem) {
        return "login";
    }

    @Autowired
    public RegisterFormController(UserItemRepository userItemRepository) {
        this.userItemRepository = userItemRepository;
    }

    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @GetMapping("/myAccount")
    public ResponseEntity<?> getAccountDetails(@RequestParam String email, @RequestParam String password) {
        try {
            Optional<UserItem> userItemOpt = userItemRepository.findUserByEmailAndPassword(email, password);
            if (userItemOpt.isPresent()) {
                return new ResponseEntity<>(userItemOpt.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(USER_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @DeleteMapping("/deleteAccount")
    public ResponseEntity<?> deleteAccount(@RequestParam String email, @RequestParam String password) {
        try {
            Optional<UserItem> userItemOpt = userItemRepository.findUserByEmailAndPassword(email, password);
            if (userItemOpt.isPresent()) {
                UserItem userItem = userItemOpt.get();
                userItemRepository.delete(userItem);
                return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(USER_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam String email, @RequestParam String currentPassword, @RequestParam String newPassword) {
        try {
            Optional<UserItem> userItemOpt = userItemRepository.findUserByEmailAndPassword(email, currentPassword);
            if (userItemOpt.isPresent()) {
                UserItem userItem = userItemOpt.get();
                userItem.setPassword(newPassword);
                userItemRepository.save(userItem);
                return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid current password", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PostMapping("/create")
    public @ResponseBody ResponseEntity<UserItem> createUser(@RequestBody UserItem userItem) {
        UserItem createdUser = userItemService.createUserItem(userItem);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
