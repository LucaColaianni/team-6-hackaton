CREATE TABLE certification (
    id BIGSERIAL PRIMARY KEY,
    blob_id_pdf VARCHAR(255),
    openbadge_json JSON NOT NULL,
    hash VARCHAR(64) NOT NULL,
    issued_at TIMESTAMP,
    blockchain_tx_id VARCHAR(100) UNIQUE,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    release_date DATE
);