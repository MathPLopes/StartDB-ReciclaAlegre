package db.start.reciclaalegre.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import db.start.reciclaalegre.dto.UsuarioRequestDTO;
import db.start.reciclaalegre.dto.UsuarioResponseDTO;
import db.start.reciclaalegre.model.Usuario;
import db.start.reciclaalegre.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/usuarios")   
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

    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponseDTO> perfilDeUsuario(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(usuarioService.perfilDeUsuario(usuario.getEmail()));
    }

    @SecurityRequirement(name = "bearer-key")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listaDeUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping
    public ResponseEntity<?> desabilitarUsuario(@AuthenticationPrincipal Usuario usuario){
        usuarioService.desabilitarUsuario(usuario.getEmail());
        return ResponseEntity.noContent().build();
    }

    
    
    
}
