package com.example.petstore.controller;

import com.example.petstore.entity.User;
import com.example.petstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

        @Autowired
        private UserRepository userRepository;


        @PostMapping
        @ResponseBody
        public ResponseEntity<User> createUser(@RequestBody User user) {
                User createdUser = userRepository.save(user);
                return ResponseEntity.ok(createdUser);
        }


        @GetMapping("/{username}")
        @ResponseBody
        public ResponseEntity<User> findByUsername(@PathVariable String username) {
                Optional<User> byUsername = userRepository.findByUsername(username);
                if (byUsername.isPresent()) {
                        User user = byUsername.get();
                        return ResponseEntity.ok(user);
                }
                return ResponseEntity.notFound().build();
        }


        @PutMapping("/{username}")
        @ResponseBody
        public ResponseEntity<Void> update(@PathVariable String username, @RequestBody User user) {
                Optional<User> optionalUser = userRepository.findByUsername(username);

                if (optionalUser.isPresent()) {
                        User existingUser = optionalUser.get();
                        existingUser.setUsername(user.getUsername());
                        existingUser.setFirstName(user.getFirstName());
                        existingUser.setLastName(user.getLastName());

                        userRepository.save(existingUser);
                        return ResponseEntity.ok().build();
                }
                return ResponseEntity.badRequest().build();
        }

        @DeleteMapping("/{username}")
        @ResponseBody
        public ResponseEntity<Void> delete(@PathVariable String username) {
                Optional<User> optionalUser = userRepository.findByUsername(username);
                if (optionalUser.isPresent()) {
                        User user = optionalUser.get();
                        userRepository.delete(user);
                        return ResponseEntity.ok().build();
                }
                return ResponseEntity.badRequest().build();
        }

}
