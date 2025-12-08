package db.start.reciclaalegre.service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Transactional
    public SolicitacaoResponseDTO criarSolicitacao(SolicitacaoRequestDTO dto, String email) {
        Usuario usuario = usuarioUtils.validarUsuario(email);
        Set<Material> materiais = materialRepository.saveAll(materialMapper.toEntity(dto.materiais())).stream().collect(Collectors.toSet());

        Solicitacao solicitacao = solicitacaoMapper.toEntity(dto);

        solicitacao.setGerador(usuario.getPerfil());
        solicitacao.setEndereco(usuario.getPerfil().getEndereco());
        solicitacao.setSituacao(StatusSolicitacao.ATIVO);
        solicitacao.setMateriais(materiais);
        solicitacao.setDataCriacao(LocalDateTime.now());
        solicitacaoRepository.save(solicitacao);

        return solicitacaoMapper.toDto(solicitacao);
    }

}
