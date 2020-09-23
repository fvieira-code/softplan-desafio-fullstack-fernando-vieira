CREATE TABLE usuario (
    id              BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nomeusus        VARCHAR(150) NOT NULL,
    emailusu        VARCHAR(150) NOT NULL,
    senhausu        VARCHAR(150) NOT NULL,
    statususu       VARCHAR(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (nomeusus, emailusu, senhausu, statususu) values ('FERNANDO VIEIRA', 'fvieira.java@gmail.com', 'luiz05012013', 'ATIVO');

CREATE TABLE assunto (
	codigoass       BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricaoass    VARCHAR(100) NOT NULL,
    statusass       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO assunto (descricaoass, statusass, codigousu, codigocid) values ('DIREITO CIVIL, NÃO INFORMADO', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE instituicao (
	codigoins BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricaoins VARCHAR(100) NOT NULL,
    statusins       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO instituicao (descricaoins, statusins, codigousu, codigocid) values ('BANCO DO BRASIL S.A.', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE competencia (
	codigocom BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricaocom VARCHAR(100) NOT NULL,
    statuscom       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO competencia (descricaocom, statuscom, codigousu, codigocid) values ('CIVEL E COMERCIO - ASSISTENCIA', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE classe (
	codigocla BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricaocla VARCHAR(100) NOT NULL,
    statuscla       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO classe (descricaocla, codigousu, statuscla, codigocid) values ('MONITÓRIA', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE gabinete (
	codigogab BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricaogab VARCHAR(100) NOT NULL,
    statusgab       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO gabinete (descricaogab, codigousu, statusgab, codigocid) values ('GABINETE DA 13a VARA CÍVEL E EMPRESARIAL DE BELÉM', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE secretaria (
	codigosec BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricaosec VARCHAR(100) NOT NULL,
    statussec       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO secretaria (descricaosec, statussec, codigousu, codigocid) values ('SECRETARIA DA 13a VARA CÍVEL E EMPRESARIAL DE BELÉM', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE pessoa (
	codigopes       BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nomepes         VARCHAR(100) NOT NULL,
	cpfcnpjpes      VARCHAR(20) NOT NULL,
	tipopes         VARCHAR(15) NOT NULL,
    statuspes       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nomepes, cpfcnpjpes, tipopes, statuspes, codigousu, codigocid) values ('FERNANDO TEIXEIRA VIEIRA', '57589488291', 'FISICA', 'ATIVO','fvieira', 'SOLICITACAO_USUARIO');
INSERT INTO pessoa (nomepes, cpfcnpjpes, tipopes, statuspes, codigousu, codigocid) values ('BANCO DO BRASIL S.A.', '68387851000334', 'JURIDICA', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');
INSERT INTO pessoa (nomepes, cpfcnpjpes, tipopes, statuspes, codigousu, codigocid) values ('MARIA FILOMENA DE ALMEIDA BUARQUE', '52556287485', 'FISICA', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');
INSERT INTO pessoa (nomepes, cpfcnpjpes, tipopes, statuspes, codigousu, codigocid) values ('MARCIA REGINA GONCALVES MENEZES', '84556269874', 'FISICA', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');
INSERT INTO pessoa (nomepes, cpfcnpjpes, tipopes, statuspes, codigousu, codigocid) values ('MARIA DE FATIMA ROCHA DA ROCHA', '47158752385', 'FISICA', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');
INSERT INTO pessoa (nomepes, cpfcnpjpes, tipopes, statuspes, codigousu, codigocid) values ('MUNDO VERDE COMERCIO DE MADEIRAS LTDA', '74852896000198', 'JURIDICA', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE magistrado (
	codigomag BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigopes BIGINT(20) NOT NULL,
	descricaomag varchar(100) NOT NULL,
    statusmag       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO magistrado (codigopes, descricaomag, statusmag, codigousu, codigocid) values (3, 'JUIZ DA 1a INSTANCIA', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');


CREATE TABLE vara (
	codigovar BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricaovar VARCHAR(100) NOT NULL,
    statusvar       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO vara (descricaovar, statusvar, codigousu, codigocid) values ('13a VARA CÍVEL E EMPRESARIAL DE BELÉM', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE area (
	codigoare BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricaoare VARCHAR(100) NOT NULL,
    statusare       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO area (descricaoare, statusare, codigousu, codigocid) values ('CÍVEL', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE instancia (
	codigoint BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricaoint VARCHAR(100) NOT NULL,
    statusint       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO instancia (descricaoint, statusint, codigousu, codigocid) values ('1o GRAU', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');
INSERT INTO instancia (descricaoint, statusint, codigousu, codigocid) values ('2o GRAU', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE situacao (
	codigosit BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricaosit VARCHAR(100) NOT NULL,
    statussit       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO situacao (descricaosit, statussit, codigousu, codigocid) values ('EM ANDAMENTO', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');
INSERT INTO situacao (descricaosit, statussit, codigousu, codigocid) values ('ARQUIVADO', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');
INSERT INTO situacao (descricaosit, statussit, codigousu, codigocid) values ('ENCERRADO', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');


CREATE TABLE processo (
	codigopro BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigocnj VARCHAR(35) NOT NULL,
	codigosit BIGINT(20) NOT NULL,
	numeroinqpol VARCHAR(50),
	codigopes BIGINT(20) NOT NULL,
	descricaopar varchar(100) NOT NULL,
    statuspro       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO processo (codigocnj, codigosit, numeroinqpol, codigopes, descricaopar, statuspro, codigousu, codigocid)
values (1, 1, '10092020', sysdate(), 'ANALISE DE DOCUMENTOS', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE movimentacao (
	codigomov BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigopro BIGINT(20) NOT NULL,
	datamov DATETIME NOT NULL,
	descricaomov VARCHAR(100) NOT NULL,
    statusmov       VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO movimentacao (codigopro, datamov, descricaomov, statusmov, codigousu, codigocid) values (1, sysdate(), 'ANALISE DE DOCUMENTOS', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE partes (
	codigopar BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigopes BIGINT(20) NOT NULL,
	codigopro BIGINT(20) NOT NULL,
	descricaopar varchar(100) NOT NULL,
    statuspar        VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO partes (codigopes, codigopro, descricaopar, statuspar, codigousu, codigocid) values (1, 1, 'AUTOR', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');
INSERT INTO partes (codigopes, codigopro, descricaopar, statuspar, codigousu, codigocid) values (4, 1, 'ADVOGADO', 'ATIVO','fvieira', 'SOLICITACAO_USUARIO');
INSERT INTO partes (codigopes, codigopro, descricaopar, statuspar, codigousu, codigocid) values (5, 1, 'ADVOGADO', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');
INSERT INTO partes (codigopes, codigopro, descricaopar, statuspar, codigousu, codigocid) values (6, 1, 'REU', 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE causa (
	codigocau BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	codigopro BIGINT(20) NOT NULL,
	datacau DATETIME NOT NULL,
	valorcau NUMERIC(10,2) NOT NULL,
    statuscau        VARCHAR(15) NOT NULL,
    codigousu       VARCHAR(255) NOT NULL,
    codigocid       VARCHAR(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO causa (codigopro, datacau, valorcau, statuscau, codigousu, codigocid) values (1, sysdate(), 9000.00, 'ATIVO', 'fvieira', 'SOLICITACAO_USUARIO');

CREATE TABLE controlemensagem (
    codigouid           VARCHAR(255)  PRIMARY KEY,
    codigousu          VARCHAR(255)  NOT NULL,
    codigocid           VARCHAR(255)  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO controlemensagem (codigouid, codigousu, codigocid) values ('softplandesafio2020','fvieira', 'SOLICITACAO_USUARIO');

