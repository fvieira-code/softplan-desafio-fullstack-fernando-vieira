package com.justica.processo.service;

import com.justica.processo.converter.UsuarioConverter;
import com.justica.processo.exception.ObjetoDuplicadoException;
import com.justica.processo.exception.ObjetoNaoEncontradoException;
import com.justica.processo.model.Usuario;
import com.justica.processo.model.domain.DominioStatusObjeto;
import com.justica.processo.model.dto.rest.request.UsuarioRequestDTO;
import com.justica.processo.model.dto.rest.response.UsuarioResponseDTO;
import com.justica.processo.repository.UsuarioRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.justica.processo.constants.MensagemConstants.MSG_SERVICE_USUARIO_NOT_EMPTY;

@Log4j2
@Service
@Validated
public class UsuarioService extends GenericService<UsuarioRepository, Usuario, String> {
    private final ModelMapper modelMapper;
    private final UsuarioConverter UsuarioConverter;

    @Autowired
    public UsuarioService(UsuarioRepository repository, ModelMapper modelMapper, UsuarioConverter UsuarioConverter) {
        super(repository);
        this.modelMapper = modelMapper;
        this.UsuarioConverter = UsuarioConverter;
    }

    public UsuarioResponseDTO consultarUsuario(@NotNull(message = MSG_SERVICE_USUARIO_NOT_EMPTY)UsuarioRequestDTO  usuarioRequestDTO) {
        log.info("Iniciando consulta do Usuario [{}]...", usuarioRequestDTO.getNomeUsuario());
            Optional<Usuario> optionalUsuario = this.repository.findByNomeUsuarioLikeAndStatusUsuario(usuarioRequestDTO.getNomeUsuario().concat("%") 
                    , DominioStatusObjeto.ATIVO);

        log.info("Finalizando consulta do Usuario [{}]...", usuarioRequestDTO.getNomeUsuario());

        return this.UsuarioConverter.converterEntidadeParaResponseDTO(optionalUsuario.get());
    }

    @Transactional
    public UsuarioResponseDTO incluirUsuario(@NotNull(message = MSG_SERVICE_USUARIO_NOT_EMPTY) UsuarioRequestDTO usuarioRequestDTO){

        log.info("Iniciando inclusão do Usuario [{}]...", usuarioRequestDTO.getNomeUsuario());

        if (this.existeUsuario(usuarioRequestDTO.getNomeUsuario())) {
            throw new ObjetoDuplicadoException(usuarioRequestDTO.getNomeUsuario());
        }

        log.info("Usuario [{}] incluído", usuarioRequestDTO.getNomeUsuario());
        return salvarUsuario(usuarioRequestDTO);
    }

    @Transactional
    public UsuarioResponseDTO alterarUsuario(@NotNull(message =MSG_SERVICE_USUARIO_NOT_EMPTY)
                                                         UsuarioRequestDTO usuarioRequestDTO, DominioStatusObjeto dominioStatusObjeto) {
        log.info("Iniciando alteração do Usuario [{}]...", usuarioRequestDTO.getNomeUsuario());

        if (this.existeUsuario(usuarioRequestDTO.getNomeUsuario())) {

            Usuario usuario =  this.repository.findByNomeUsuarioLikeAndStatusUsuario(usuarioRequestDTO.getNomeUsuario().concat("%"), 
                    dominioStatusObjeto).orElseThrow(() ->
                    new ObjetoNaoEncontradoException("service.objeto.notnull.message", usuarioRequestDTO.getNomeUsuario()));

            super.repository.save(usuario);

        } else {
            throw new ObjetoNaoEncontradoException("service.pessoa.notnull.message", usuarioRequestDTO.getNomeUsuario());
        }

        return salvarUsuario(usuarioRequestDTO);
    }

    @Transactional
    public UsuarioResponseDTO excluirUsuario(@NotNull(message = MSG_SERVICE_USUARIO_NOT_EMPTY)  UsuarioRequestDTO usuarioRequestDTO, 
                                             DominioStatusObjeto dominioStatusObjeto){
        log.info("Iniciando exclusão do usuário [{}]...", usuarioRequestDTO.getNomeUsuario());

        Usuario usuario =  this.repository.findByNomeUsuarioLikeAndStatusUsuario(usuarioRequestDTO.getNomeUsuario().concat("%"), 
                DominioStatusObjeto.ATIVO).orElseThrow(() ->
                new ObjetoNaoEncontradoException("service.objeto.notnull.message", usuarioRequestDTO.getNomeUsuario()));

        log.info("Usuario [{}] excluído", usuarioRequestDTO.getNomeUsuario());
        return this.atualizaSituacaoUsuario(usuario, dominioStatusObjeto);
    }

    private UsuarioResponseDTO salvarUsuario(@NotNull(message = "service.objeto.notnull.message") UsuarioRequestDTO usuarioRequestDTO) {
            Usuario usuario = this.UsuarioConverter.converterDTOParaEntidade(usuarioRequestDTO);
            return this.UsuarioConverter.converterEntidadeParaResponseDTO(super.repository.save(usuario));
    }

    private UsuarioResponseDTO atualizaSituacaoUsuario(Usuario usuario, DominioStatusObjeto dominioStatusObjeto ) {
        log.info("Atualizando do status do Usuario [{}] para [{}]", usuario.getNomeUsuario(), dominioStatusObjeto);
        usuario.setStatusUsuario(dominioStatusObjeto);
        log.debug("Atualização do status do usuário realizado com sucesso...");
        return this.UsuarioConverter.converterEntidadeParaResponseDTO(super.repository.save(usuario));
    }

    public boolean existeUsuario(@NotEmpty(message = MSG_SERVICE_USUARIO_NOT_EMPTY) String nomeUsuario) {
        return super.repository.existsByEmailUsuarioLike(nomeUsuario.concat("%"));
    }
}
