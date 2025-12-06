package db.start.reciclaalegre.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import db.start.reciclaalegre.dto.UsuarioRequestDTO;
import db.start.reciclaalegre.dto.UsuarioResponseDTO;
import db.start.reciclaalegre.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/usuarios")
@SecurityRequirement(name = "bearer-key")   
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody @Valid UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuario = usuarioService.adicionarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponseDTO> perfilDeUsuario() {
        String usuarioEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(usuarioEmail);
        return ResponseEntity.ok(usuarioService.perfilDeUsuario(usuarioEmail));
    }
    
}
