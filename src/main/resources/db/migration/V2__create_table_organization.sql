create table organization (
    id serial PRIMARY KEY,
    name VARCHAR(100),
    cnpj VARCHAR(15) UNIQUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);