package db.start.reciclaalegre.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import db.start.reciclaalegre.dto.LoginDto;
import db.start.reciclaalegre.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<String> loginAuth(@RequestBody LoginDto loginDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.gerarToken(loginDto));
    }
}
