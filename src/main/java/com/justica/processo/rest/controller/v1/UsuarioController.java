package com.justica.processo.rest.controller.v1;

import com.justica.processo.model.domain.DominioControleMensagem;
import com.justica.processo.model.domain.DominioStatusObjeto;
import com.justica.processo.model.dto.rest.request.AssuntoRequestDTO;
import com.justica.processo.model.dto.rest.request.UsuarioRequestDTO;
import com.justica.processo.model.dto.rest.response.UsuarioResponseDTO;
import com.justica.processo.service.ControleMensagemService;
import com.justica.processo.service.UsuarioService;
import com.justica.processo.service.UsuarioService;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.justica.processo.constants.LiteralConstants.REGEX_RAZAO_OPER_CONSULTA_OBJETO;
import static com.justica.processo.constants.MensagemConstants.*;
import static com.justica.processo.constants.VariavelSistemaConstants.*;
import static org.springframework.http.ResponseEntity.ok;

@Log4j2
@Validated
@RestController
@RequestMapping(URI_USUARIO_V1)
public class UsuarioController extends AbstractController{

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(ControleMensagemService controleMensagemService,
                             UsuarioService usuarioService) {
        super(controleMensagemService);
        this.usuarioService = usuarioService;
    }

    @GetMapping(path = {URI_USUARIO_CONSULTAR_PATH}, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE, "*/*;charset=UTF-8"})
    @ApiOperation(value = "Consultar Usuário", authorizations = {@Authorization(value = "Authorization")})

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Correlation-Id", value = "Código transação",
                    required = true, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name =  "Authorization", value = "Token autorização", required = true,
                    paramType = "header", dataTypeClass = String.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro de validação", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 401, message = "Não autorizado", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 403, message = "Acesso proibido ao usuário", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 404, message = "Não encontrado", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro interno", response = UsuarioResponseDTO.class)
    })
    public ResponseEntity<UsuarioResponseDTO>  consultarUsuario(@Valid @NotNull(message = MSG_ENTITY_USUARIO_NOT_NULL)
                                                                    @ApiParam(value = "Nome do Usuário", required = true)
                                                                    @PathVariable(value = "nomeusuario") String nomeUsuario,
                                                      @NotEmpty(message = MSG_REST_HEADER_CORRELATIONID_NOT_EMPTY) @RequestHeader(value = HEADER_CORRELATION_ID) String correlationId,
                                                      @RequestHeader HttpHeaders headers) {

        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        usuarioRequestDTO.setNomeUsuario(nomeUsuario);
        usuarioRequestDTO.setStatusUsuario(DominioStatusObjeto.ATIVO);

        String uri = String.format("%s/%s/%s", URI_ASSUNTO_CONSULTAR_PATH, usuarioRequestDTO.getNomeUsuario(), REGEX_RAZAO_OPER_CONSULTA_OBJETO);
        this.salvarControleMensagem(null, headers, HttpMethod.GET, uri, DominioControleMensagem.INICIADO);
        UsuarioResponseDTO response = this. usuarioService.consultarUsuario(usuarioRequestDTO);
        this.salvarControleMensagem(null, headers, HttpMethod.GET, uri, DominioControleMensagem.PROCESSADO);
        return ok(response);
    }

    @PostMapping(produces = {MimeTypeUtils.APPLICATION_JSON_VALUE, "*/*;charset=UTF-8"})
    @ApiOperation(value = "Incluir Usuário", authorizations = {@Authorization(value = "Authorization")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Correlation-Id", value = "Código transação",
                    required = true, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name =  "Authorization", value = "Token autorização", required = true,
                    paramType = "header", dataTypeClass = String.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro de validação", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 401, message = "Não autorizado", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 403, message = "Acesso proibido ao usuário", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 404, message = "Não encontrado", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro interno", response = UsuarioResponseDTO.class)
    })
    public ResponseEntity<UsuarioResponseDTO> incluirUsuario(@NotNull(message = MSG_REST_REQUEST_BODY_NOT_NULL) @RequestBody UsuarioRequestDTO usuarioRequestDTO,
                                                   @NotEmpty(message = MSG_REST_HEADER_CORRELATIONID_NOT_EMPTY) @RequestHeader(value = HEADER_CORRELATION_ID) String correlationId,
                                                   @RequestHeader HttpHeaders headers) {
        log.info("Iniciando inclusão do assunto [{}]...");
        usuarioRequestDTO.setStatusUsuario(DominioStatusObjeto.ATIVO);

        String uri = this.getUriNormalizada(URI_ASSUNTO_V1.concat(URI_ASSUNTO_INCLUIR_PATH));
        this.salvarControleMensagem(null, headers, HttpMethod.POST, uri, DominioControleMensagem.INICIADO);
        UsuarioResponseDTO response = this.usuarioService.incluirUsuario(usuarioRequestDTO);
        return ok(response);
    }

    @PutMapping(produces = {MimeTypeUtils.APPLICATION_JSON_VALUE, "*/*;charset=UTF-8"})
    @ApiOperation(value = "Alterar Usuário", authorizations = {@Authorization(value = "Authorization")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Correlation-Id", value = "Código transação",
                    required = true, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name =  "Authorization", value = "Token autorização", required = true,
                    paramType = "header", dataTypeClass = String.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro de validação", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 401, message = "Não autorizado", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 403, message = "Acesso proibido ao usuário", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 404, message = "Não encontrado", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro interno", response = UsuarioResponseDTO.class)
    })
    public ResponseEntity<UsuarioResponseDTO>  alterarUsuario(@NotNull(message = MSG_REST_REQUEST_BODY_NOT_NULL) @RequestBody UsuarioRequestDTO usuarioRequestDTO,
                                                              @NotEmpty(message = MSG_REST_HEADER_CORRELATIONID_NOT_EMPTY) @RequestHeader(value = HEADER_CORRELATION_ID) String correlationId,
                                                              @RequestHeader HttpHeaders headers) {
        log.info("Iniciando alteração do usuário [{}]...");
        usuarioRequestDTO.setStatusUsuario(DominioStatusObjeto.ALTERADO);
        String uri = this.getUriNormalizada(URI_ASSUNTO_ALTERAR_PATH);
        this.salvarControleMensagem(null, headers, HttpMethod.PUT, uri, DominioControleMensagem.INICIADO);
        UsuarioResponseDTO response =  this.usuarioService.alterarUsuario(usuarioRequestDTO, DominioStatusObjeto.ALTERADO);
        this.salvarControleMensagem(null, headers, HttpMethod.PUT, uri, DominioControleMensagem.PROCESSADO);
        return ok(response);
    }

    @DeleteMapping(path = {URI_USUARIO_REMOVER_PATH},  produces = {MimeTypeUtils.APPLICATION_JSON_VALUE, "*/*;charset=UTF-8"})
    @ApiOperation(value = "Excluir Usuário", authorizations = {@Authorization(value = "Authorization")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Correlation-Id", value = "Código transação",
                    required = true, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name =  "Authorization", value = "Token autorização", required = true,
                    paramType = "header", dataTypeClass = String.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro de validação", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 401, message = "Não autorizado", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 403, message = "Acesso proibido ao usuário", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 404, message = "Não encontrado", response = UsuarioResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro interno", response = UsuarioResponseDTO.class)
    })
    public ResponseEntity<UsuarioResponseDTO> excluirUsuario(@NotNull(message = MSG_REST_REQUEST_BODY_NOT_NULL) @RequestBody UsuarioRequestDTO usuarioRequestDTO,
                                                              @NotEmpty(message = MSG_REST_HEADER_CORRELATIONID_NOT_EMPTY) @RequestHeader(value = HEADER_CORRELATION_ID) String correlationId,
                                                              @RequestHeader HttpHeaders headers) {
        log.info("Iniciando exclusão do usuário [{}]...");
        usuarioRequestDTO.setStatusUsuario(DominioStatusObjeto.EXCLUIDO);
        String uri = this.getUriNormalizada(URI_ASSUNTO_REMOVER_PATH);
        this.salvarControleMensagem(null, headers, HttpMethod.DELETE, uri, DominioControleMensagem.INICIADO);
        UsuarioResponseDTO response = this.usuarioService.excluirUsuario(usuarioRequestDTO, DominioStatusObjeto.EXCLUIDO);
        this.salvarControleMensagem(null, headers, HttpMethod.DELETE, uri, DominioControleMensagem.PROCESSADO);
        return ok(response);
    }
}
