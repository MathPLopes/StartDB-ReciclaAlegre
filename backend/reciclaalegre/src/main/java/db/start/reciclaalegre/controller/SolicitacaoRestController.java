package db.start.reciclaalegre.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import db.start.reciclaalegre.dto.SolicitacaoRequestDTO;
import db.start.reciclaalegre.dto.SolicitacaoResponseDTO;
import db.start.reciclaalegre.model.Usuario;
import db.start.reciclaalegre.service.SolicitacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/solicitacoes")
public class SolicitacaoRestController {

    private final SolicitacaoService solicitacaoService;

    public SolicitacaoRestController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @PostMapping
    public ResponseEntity<SolicitacaoResponseDTO> criarSolicitacao(@RequestBody SolicitacaoRequestDTO dto,
            @AuthenticationPrincipal Usuario usuario) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(solicitacaoService.criarSolicitacao(dto, usuario.getEmail()));
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoResponseDTO>> carregarSolicitacoes() {
        List<SolicitacaoResponseDTO> solicitacoesDto = solicitacaoService.carregarAtivas();
        return ResponseEntity.ok(solicitacoesDto);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<SolicitacaoResponseDTO>> carregarSolicitacoesPorUsuario(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(solicitacaoService.carregarPorUsuario(usuario.getEmail()));
    }

    @PostMapping("/requisitar/{id}")
    public ResponseEntity<?> requisitarColeta(@AuthenticationPrincipal Usuario usuario, @PathVariable Long id) {
        System.out.println("SOLICITAR COLETA " + id);
        return ResponseEntity.ok(solicitacaoService.requisitarColeta(usuario.getEmail(), id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<SolicitacaoResponseDTO> atualizarSolicitacao(@PathVariable Long id,
            @RequestBody SolicitacaoRequestDTO dto, @AuthenticationPrincipal Usuario usuario) {
        SolicitacaoResponseDTO atualizado = solicitacaoService.atualizarSolicitacao(id, dto, usuario.getEmail());
        return ResponseEntity.ok(atualizado);
    }

}
