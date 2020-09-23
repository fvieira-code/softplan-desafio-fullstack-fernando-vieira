package com.justica.processo.rest.controller.v1;

import com.justica.processo.model.domain.DominioControleMensagem;
import com.justica.processo.model.domain.DominioStatusPessoa;
import com.justica.processo.model.domain.DominioTipoPessoa;
import com.justica.processo.model.dto.rest.PessoaDTO;
import com.justica.processo.model.dto.rest.response.PessoaPFResponseDTO;
import com.justica.processo.service.ControleMensagemService;
import com.justica.processo.service.PessoaService;
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

import static com.justica.processo.constants.LiteralConstants.REGEX_RAZAO_OPER_CONSULTA_PESSOA;
import static com.justica.processo.constants.MensagemConstants.*;
import static com.justica.processo.constants.VariavelSistemaConstants.*;
import static org.springframework.http.ResponseEntity.ok;

@Log4j2
@Validated
@RestController
@RequestMapping(URI_PESSOA_V1)
public class PessoaController extends AbstractController{

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(ControleMensagemService controleMensagemService,
                            PessoaService pessoaService) {
        super(controleMensagemService);
        this.pessoaService = pessoaService;
    }

    @GetMapping(path = {URI_PESSOA_CONSULTAR_PATH}, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE, "*/*;charset=UTF-8"})
    @ApiOperation(value = "Consultar Pessoa", authorizations = {@Authorization(value = "Authorization")})

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Correlation-Id", value = "Código transação",
                    required = true, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name =  "Authorization", value = "Token autorização", required = true,
                    paramType = "header", dataTypeClass = String.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro de validação", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 401, message = "Não autorizado", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 403, message = "Acesso proibido ao usuário", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 404, message = "Não encontrado", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro interno", response = PessoaPFResponseDTO.class)
    })
    public ResponseEntity<PessoaDTO>  consultarPessoa(@Valid @NotNull(message = MSG_ENTITY_PESSOA_NOT_NULL) @ApiParam(value = "Documento da Pessoa", required = true) @PathVariable(value = "cpfcnpjpes") String cpfcnpjpes,
                                                      @PathVariable(value = "tipopes")DominioTipoPessoa tipopes,
                                                      @NotEmpty(message = MSG_REST_HEADER_CORRELATIONID_NOT_EMPTY) @RequestHeader(value = HEADER_CORRELATION_ID) String correlationId,
                                                      @RequestHeader HttpHeaders headers) {

        PessoaDTO requestDTO = new PessoaDTO();
        requestDTO.setCorrelationId(correlationId);
        requestDTO.setCodigoUsuario(super.getUsuario());
        requestDTO.setNumeroDocumento(cpfcnpjpes);
        requestDTO.setTipoPessoa(tipopes);
        requestDTO.setStatusPessoa(DominioStatusPessoa.ATIVO);

        String uri = String.format("%s/%s/%s", URI_PESSOA_CONSULTAR_PATH, requestDTO.getNumeroDocumento(), REGEX_RAZAO_OPER_CONSULTA_PESSOA);
        this.salvarControleMensagem(requestDTO, headers, HttpMethod.GET, uri, DominioControleMensagem.INICIADO);
        PessoaDTO response = this.pessoaService.consultarPessoa(requestDTO);
        this.salvarControleMensagem(requestDTO, headers, HttpMethod.GET, uri, DominioControleMensagem.PROCESSADO);
        return ok(response);
    }

    @PostMapping(produces = {MimeTypeUtils.APPLICATION_JSON_VALUE, "*/*;charset=UTF-8"})
    @ApiOperation(value = "Incluir Pessoa", authorizations = {@Authorization(value = "Authorization")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Correlation-Id", value = "Código transação",
                    required = true, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name =  "Authorization", value = "Token autorização", required = true,
                    paramType = "header", dataTypeClass = String.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro de validação", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 401, message = "Não autorizado", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 403, message = "Acesso proibido ao usuário", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 404, message = "Não encontrado", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro interno", response = PessoaPFResponseDTO.class)
    })
    public ResponseEntity<PessoaDTO> incluirPessoa(@NotNull(message = MSG_REST_REQUEST_BODY_NOT_NULL) @RequestBody PessoaDTO pessoaPFRequestDTO,
                                                   @NotEmpty(message = MSG_REST_HEADER_CORRELATIONID_NOT_EMPTY) @RequestHeader(value = HEADER_CORRELATION_ID) String correlationId,
                                                   @RequestHeader HttpHeaders headers) {
        log.info("Iniciando inclusão da pessoa [{}]...");

        pessoaPFRequestDTO.setCorrelationId(correlationId);
        pessoaPFRequestDTO.setCodigoUsuario(super.getUsuario());

        String uri = this.getUriNormalizada(URI_PESSOA_V1.concat(URI_PESSOA_INCLUIR_PATH));
        this.salvarControleMensagem(pessoaPFRequestDTO, headers, HttpMethod.POST, uri, DominioControleMensagem.INICIADO);
        PessoaDTO response = this.pessoaService.incluirPessoa(pessoaPFRequestDTO);
        return ok(response);
    }

    @PutMapping(produces = {MimeTypeUtils.APPLICATION_JSON_VALUE, "*/*;charset=UTF-8"})
    @ApiOperation(value = "Alterar Pessoa", authorizations = {@Authorization(value = "Authorization")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Correlation-Id", value = "Código transação",
                    required = true, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name =  "Authorization", value = "Token autorização", required = true,
                    paramType = "header", dataTypeClass = String.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro de validação", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 401, message = "Não autorizado", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 403, message = "Acesso proibido ao usuário", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 404, message = "Não encontrado", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro interno", response = PessoaPFResponseDTO.class)
    })
    public ResponseEntity<PessoaDTO>  alterarPessoa(@NotNull(message = MSG_REST_REQUEST_BODY_NOT_NULL) @RequestBody PessoaDTO pessoaDTO,
                                                              @NotEmpty(message = MSG_REST_HEADER_CORRELATIONID_NOT_EMPTY) @RequestHeader(value = HEADER_CORRELATION_ID) String correlationId,
                                                              @RequestHeader HttpHeaders headers) {
        log.info("Iniciando inclusão da pessoa [{}]...");
        pessoaDTO.setCorrelationId(correlationId);
        pessoaDTO.setCodigoUsuario(super.getUsuario());
        String uri = this.getUriNormalizada(URI_PESSOA_ALTERAR_PATH);
        this.salvarControleMensagem(pessoaDTO, headers, HttpMethod.PUT, uri, DominioControleMensagem.INICIADO);
        PessoaDTO response =  this.pessoaService.alterarPessoa(pessoaDTO);
        this.salvarControleMensagem(response, headers, HttpMethod.PUT, uri, DominioControleMensagem.PROCESSADO);
        return ok(response);
    }

    @DeleteMapping(path = {URI_PESSOA_REMOVER_PATH},  produces = {MimeTypeUtils.APPLICATION_JSON_VALUE, "*/*;charset=UTF-8"})
    @ApiOperation(value = "Excluir Pessoa", authorizations = {@Authorization(value = "Authorization")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Correlation-Id", value = "Código transação",
                    required = true, paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name =  "Authorization", value = "Token autorização", required = true,
                    paramType = "header", dataTypeClass = String.class)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "Operação realizada com sucesso", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 400, message = "Erro de validação", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 401, message = "Não autorizado", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 403, message = "Acesso proibido ao usuário", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 404, message = "Não encontrado", response = PessoaPFResponseDTO.class),
            @ApiResponse(code = 500, message = "Erro interno", response = PessoaPFResponseDTO.class)
    })
    public ResponseEntity<PessoaDTO> excluirPessoa(@NotNull(message = MSG_REST_REQUEST_BODY_NOT_NULL) @RequestBody PessoaDTO pessoaDTO,
                                                              @NotEmpty(message = MSG_REST_HEADER_CORRELATIONID_NOT_EMPTY) @RequestHeader(value = HEADER_CORRELATION_ID) String correlationId,
                                                              @RequestHeader HttpHeaders headers) {
        log.info("Iniciando exclusão da pessoa [{}]...");
        pessoaDTO.setCorrelationId(correlationId);
        pessoaDTO.setCodigoUsuario(super.getUsuario());
        String uri = this.getUriNormalizada(URI_PESSOA_REMOVER_PATH);
        this.salvarControleMensagem(pessoaDTO, headers, HttpMethod.DELETE, uri, DominioControleMensagem.INICIADO);
        PessoaDTO response = this.pessoaService.excluirPessoa(pessoaDTO);
        this.salvarControleMensagem(pessoaDTO, headers, HttpMethod.DELETE, uri, DominioControleMensagem.PROCESSADO);
        return ok(response);
    }
}
