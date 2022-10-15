package com.hsob.user.controller;

import com.hsob.user.dto.user.UserDto;
import com.hsob.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto, @RequestHeader String password, @RequestHeader String confirmPassword,UriComponentsBuilder uriBuilder){
        UserDto userCreated = userService.saveUser(userDto, password, confirmPassword);
        URI uri = uriBuilder.path("/user/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(uri).body(userCreated);

    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("validatePassword")
    public ResponseEntity<Boolean> validatePassword(@RequestParam String document, @RequestParam String password){
         return ResponseEntity.ok(userService.validatePassword(document, password));
    }

    @GetMapping("validateAuthPass")
    public ResponseEntity<Boolean> validateAuthPass(@RequestParam String document, @RequestParam String password){
        return ResponseEntity.ok(userService.validateAuthpass(document, password));
    }
}
