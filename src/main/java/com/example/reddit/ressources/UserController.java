package com.example.reddit.ressources;

import com.example.reddit.DTO.UserCreateDTO;
import com.example.reddit.DTO.UserDTO;
import com.example.reddit.DTO.UserUpdateDTO;
import com.example.reddit.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(
        UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id) {
        UserDTO user = this.userService.get(Long.parseLong(id));
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> postUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserDTO user = this.userService.create(userCreateDTO);
        return ResponseEntity.ok(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> putUser(@PathVariable String id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        UserDTO user = this.userService.update(Long.parseLong(id), userUpdateDTO);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable String id) {
        UserDTO user = this.userService.delete(Long.parseLong(id));
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(user);
    }
}
