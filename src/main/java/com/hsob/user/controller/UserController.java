package com.hsob.user.controller;

import com.hsob.user.model.user.UserRequest;
import com.hsob.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(field, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handle204ValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(field, errorMessage);
        });
        return errors;
    }

    @PostMapping
    public ResponseEntity<UserRequest> saveUser(@RequestBody @Valid UserRequest userRequest, @RequestHeader String password, @RequestHeader String confirmPassword, UriComponentsBuilder uriBuilder){
        UserRequest userCreated = userService.saveUser(userRequest, password, confirmPassword);
        URI uri = uriBuilder.path("/user/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(uri).body(userCreated);

    }

    @GetMapping
    public ResponseEntity<List<UserRequest>> getAllUsers(){
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
