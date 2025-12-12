package db.start.reciclaalegre.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import db.start.reciclaalegre.dto.SolicitacaoRequestDTO;
import db.start.reciclaalegre.dto.SolicitacaoResponseDTO;
import db.start.reciclaalegre.model.Material;
import db.start.reciclaalegre.model.Solicitacao;
import db.start.reciclaalegre.model.Usuario;
import db.start.reciclaalegre.model.enums.StatusSolicitacao;
import db.start.reciclaalegre.repository.MaterialRepository;
import db.start.reciclaalegre.repository.SolicitacaoRepository;
import db.start.reciclaalegre.utils.mapper.MaterialMapper;
import db.start.reciclaalegre.utils.mapper.SolicitacaoMapper;
import db.start.reciclaalegre.utils.usuario.UsuarioUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final MaterialRepository materialRepository;
    private final UsuarioUtils usuarioUtils;
    private final MaterialMapper materialMapper;
    private final SolicitacaoMapper solicitacaoMapper;

    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository, UsuarioUtils usuarioUtils,
            MaterialRepository materialRepository, MaterialMapper materialMapper, SolicitacaoMapper solicitacaoMapper) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.materialRepository = materialRepository;
        this.usuarioUtils = usuarioUtils;
        this.materialMapper = materialMapper;
        this.solicitacaoMapper = solicitacaoMapper;
    }

    @PreAuthorize("hasRole('GERADOR')")
    @Transactional
    public SolicitacaoResponseDTO criarSolicitacao(SolicitacaoRequestDTO dto, String email) {
        Usuario usuario = usuarioUtils.validarUsuario(email);
        Set<Material> materiais = materialRepository.saveAll(materialMapper.toEntity(dto.materiais())).stream()
                .collect(Collectors.toSet());

        Solicitacao solicitacao = solicitacaoMapper.toEntity(dto);

        solicitacao.setGerador(usuario.getPerfil());
        solicitacao.setEndereco(usuario.getPerfil().getEndereco());
        solicitacao.setSituacao(StatusSolicitacao.ATIVO);
        solicitacao.setMateriais(materiais);
        solicitacao.setDataCriacao(LocalDateTime.now());
        solicitacao.setColetor(null);
        solicitacaoRepository.save(solicitacao);

        return solicitacaoMapper.toDto(solicitacao);
    }

    @PreAuthorize("hasAnyRole('COLETOR', 'GERADOR', 'ADMINISTRADOR')")
    public List<SolicitacaoResponseDTO> carregarAtivas() {
        return solicitacaoRepository.findAllBySituacao(StatusSolicitacao.ATIVO)
                .stream()
                .map(sol -> solicitacaoMapper.toDto(sol)).toList();
    }

    @PreAuthorize("hasAnyRole('GERADOR', 'COLETOR')")
    public List<SolicitacaoResponseDTO> carregarPorUsuario(String email) {
        Usuario usuario = usuarioUtils.validarUsuario(email);
        return solicitacaoRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(sol -> solicitacaoMapper.toDto(sol)).toList();
    }

    @PreAuthorize("hasRole('COLETOR')")
    @Transactional
    public SolicitacaoResponseDTO requisitarColeta(String email, Long id) {
        Usuario usuario = usuarioUtils.validarUsuario(email);

        Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Solicitação não encontrada"));

        if (solicitacao.getSituacao() != StatusSolicitacao.ATIVO) {
            throw new EntityNotFoundException("Solictação indisponível!");
        } else {
            solicitacao.setColetor(usuario.getPerfil());
            solicitacao.setSituacao(StatusSolicitacao.PENDENTE);
            usuario.getPerfil().adicionarSolicitacao(solicitacao);
            solicitacaoRepository.save(solicitacao);
            return solicitacaoMapper.toDto(solicitacao);
        }
    }

    @Transactional 
    @PreAuthorize("hasRole('GERADOR')") 
    public SolicitacaoResponseDTO atualizarSolicitacao(Long id, SolicitacaoRequestDTO dto, String email) {
       Usuario usuario = usuarioUtils.validarUsuario(email); 
         Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Solicitação não encontrada"));
        if (!solicitacao.getGerador().getUsuario().getId().equals(usuario.getId())) {

            throw new RuntimeException();
             
        } else {
            solicitacao.setDescricao(dto.descricao());
            Set<Material> materiaisAtualizados = dto.materiais().stream()
                    .map(materialMapper::toEntity)
                    .collect(Collectors.toSet());
            solicitacao.setMateriais(materiaisAtualizados); 
            materialRepository.saveAll(materiaisAtualizados);                  
            solicitacaoRepository.save(solicitacao);
            return solicitacaoMapper.toDto(solicitacao);
        }


    }
}