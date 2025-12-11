CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ativo BOOLEAN ,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(20)
);

CREATE TABLE perfil (
    id BIGINT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    cep VARCHAR(255),
    logradouro VARCHAR(255),
    numero VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    pais VARCHAR(255),
    CONSTRAINT fk_perfil_usuario FOREIGN KEY (id) REFERENCES usuario (id)
);

CREATE TABLE solicitacao (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  data_criacao DATETIME(6) NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  bairro VARCHAR(255),
  cep VARCHAR(255) NOT NULL,
  cidade VARCHAR(255) NOT NULL,
  estado VARCHAR(255),
  logradouro VARCHAR(255) NOT NULL,
  numero VARCHAR(255) NOT NULL,
  pais VARCHAR(255),
  situacao VARCHAR(255),
  coletor_id BIGINT,
  gerador_id BIGINT,
  CONSTRAINT fk_solicitacao_gerador FOREIGN KEY (gerador_id) REFERENCES perfil (id),
  CONSTRAINT fk_solicitacao_coletor FOREIGN KEY (coletor_id) REFERENCES perfil (id)
);

CREATE TABLE material (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  quantidade_material VARCHAR(40),
  tipo_material VARCHAR(40)
);

CREATE TABLE solicitacao_materiais (
  solicitacao_id BIGINT NOT NULL,
  materiais_id BIGINT NOT NULL,
  PRIMARY KEY (solicitacao_id, materiais_id),
  CONSTRAINT fk_solicitacoes_materiais_material FOREIGN KEY (materiais_id) REFERENCES material (id),
  CONSTRAINT fk_solicitacoes_materiais_solicitacao FOREIGN KEY (solicitacao_id) REFERENCES solicitacao (id)
);
