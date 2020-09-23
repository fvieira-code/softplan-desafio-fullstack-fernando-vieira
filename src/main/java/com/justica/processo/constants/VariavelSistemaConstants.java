package com.justica.processo.constants;

public interface VariavelSistemaConstants {
    /*URI*/
    String URI_PESSOA_V1 = "/api/v1/pessoa";
    String URI_PESSOA_CONSULTAR_PATH = "/consultar/{cpfcnpjpes}/{tipopes}";
    String URI_PESSOA_INCLUIR_PATH = "/incluir";
    String URI_PESSOA_ALTERAR_PATH = "/alterar";
    String URI_PESSOA_REMOVER_PATH = "/remover";

    String URI_ASSUNTO_V1 = "/api/v1/assunto";
    String URI_ASSUNTO_CONSULTAR_PATH = "/consultar/{descricaoassunto}";
    String URI_ASSUNTO_INCLUIR_PATH = "/incluir";
    String URI_ASSUNTO_ALTERAR_PATH = "/alterar";
    String URI_ASSUNTO_REMOVER_PATH = "/remover";

    String URI_USUARIO_V1 = "/api/v1/usuario";
    String URI_USUARIO_CONSULTAR_PATH = "/consultar/{emailusuario}";
    String URI_USUARIO_INCLUIR_PATH = "/incluir";
    String URI_USUARIO_ALTERAR_PATH = "/alterar";
    String URI_USUARIO_REMOVER_PATH = "/remover";

    /*Headers*/
    String HEADER_AUTHORIZATION_KEY = "Authorization";
    String HEADER_CORRELATION_ID = "X-Correlation-Id";

    /* Documentação */

}
