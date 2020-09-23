package com.justica.processo.constants;

public interface MensagemConstants {
    /*Gerais*/
    String MSG_DUPLICATED_MESSAGE = "service.controleMensagem.duplicated.message";
    String MSG_PROPRIEDADE_DESCONHECIDA = "message.property_unrecognized";
    String MSG_REQUEST_BODY_MISSING = "requestBody.invalid.message";
    String MSG_REQUEST_PARAM_MISSING = "requestParam.missing.message";
    String MISSING_HEADER_MESSAGE = "exception.missing.header.message";
    String MSG_CONST_ERRO = "exception.objetonaoencontrado.error.message";
    String MSG_EMPRESA_SERVICE_NOT_NULL = "service.empresa.notnull.message";
    String MSG_LOG_COMPONENT_CORRELATION_ID_NOT_EMPTY = "log.component.correlationid.notempty.message";
    String MSG_LOG_COMPONENT_CORRELATION_ID_ERROR = "log.component.correlationid.error.message";
    String MSG_LOG_COMPONENT_CORRELATION_ID_RECOVERY_ERROR = "log.component.correlationid.recovery.error.message";
    String MSG_LOG_COMPONENT_CLEAR_MDC = "log.component.clear.mdc";
    String MSG_API_DICT_RESPONSE_ENTITY_NOT_NULL = "response.notnull.message";
    String MSG_TIPO_CHAVE_INVALIDA = "dto.chaverequest.tipochave.invalid.message";
    String MSG_TIPO_ENUM_INVALIDO = "validation.enum.generic.invalid.message";
    String MSG_CONVERTER_CLAIM_NOT_NULL = "converter.claim.notnull.message";
    String MSG_UUID_PATTERN_INVALID = "regex.uuid.pattern.invalid.message";

    /*Services*/
    String MSG_SERVICE_PESSOA_NOT_EMPTY = "service.pessoa.notempty.message";
    String MSG_SERVICE_ASSUNTO_NOT_EMPTY = "service.assunto.notempty.message";
    String MSG_SERVICE_USUARIO_NOT_EMPTY = "service.usuario.notempty.message";

    /*DTO*/
    String MSG_DTO_PESSOA_NOT_NULL = "dto.pessoa.notnull.message";
    String MSG_DTO_PESSOA_TIPO_PESSOA_NOT_NULL = "dto.pessoa.tipopessoa.notnull.message";
    String MSG_DTO_PESSOA_STATUS_PESSOA_NOT_NULL = "dto.pessoa.statuspessoa.notnull.message";
    String MSG_DTO_PESSOA_NOME_SIZE_INVALID = "dto.pessoa.nome.size.message";
    String MSG_DTO_PESSOA_NOME_NOT_EMPTY = "dto.pessoa.nome.notempty.message";
    String MSG_DTO_PESSOA_DOCUMENTO_NOT_EMPTY = "dto.pessoa.numerodocumento.notempty.message";

    /*Exception*/
    String MSG_EXCEPTION_VALOR_INVALIDO = "exception.valorinvalido.message";

    /*Entidades*/
    String MSG_ENTITY_PESSOA_NOT_NULL = "entity.pessoa.notnull.message";
    String MSG_ENTITY_CORRELATION_ID_NOT_EMPTY = "entity.correlationId.notempty";
    String MSG_ENTITY_CODIGO_USUARIO_NOT_EMPTY = "entity.codigoUsuario.notempty";
    String MSG_ENTITY_PROPRIETARIO_NOT_NULL = "entity.pessoa.notnull.message";
    String MSG_ENTITY_OBJECT_MENSAGEM_NOT_NULL = "entity.object.notnull";
    String MSG_ENTITY_PESSOA_MENSAGEM_NOT_EMPTY = "entity.pessoa.mensagem.notempty";

    String MSG_ENTITY_ASSUNTO_MENSAGEM_NOT_EMPTY = "entity.assunto.mensagem.notempty";
    String MSG_ENTITY_ASSUNTO_NOT_NULL = "entity.pessoa.notnull.message";

    String MSG_ENTITY_USUARIO_MENSAGEM_NOT_EMPTY = "entity.usuario.mensagem.notempty";
    String MSG_ENTITY_USUARIO_NOT_NULL = "entity.usuario.notnull.message";


    /*Controllers*/
    String MSG_REST_REQUEST_BODY_NOT_NULL = "rest.body.notnull.message";
    String MSG_REST_HEADER_CORRELATIONID_NOT_EMPTY = "rest.correlationid.notempty.message";
    String MSG_REST_PESSOA_ESCLUIDA_SUCESSO = "rest.pessoa.excluida.sucesso.message";

    /*Enum*/

    /*Strings repetidas Log*/
    String MSG_LOG_PROCESSO_REQUEST = "Request: [{}]";
    String MSG_LOG_PROCESSO_RESPONSE = "Response: [{}]";
}
