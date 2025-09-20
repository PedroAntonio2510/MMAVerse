CREATE TABLE gym (
    id serial primary key,
    name varchar(100) NOT NULL,
    location varchar(100) NOT NULL,
    foundation INTEGER NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);