CREATE TABLE produtos (
    id            BIGSERIAL PRIMARY KEY,
    sku           VARCHAR(50) NOT NULL UNIQUE,
    nome          VARCHAR(255) NOT NULL,
    descricao     TEXT,
    preco         NUMERIC(10,2) NOT NULL,
    criado_em     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
