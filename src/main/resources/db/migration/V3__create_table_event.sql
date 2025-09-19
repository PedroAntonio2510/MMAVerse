CREATE TABLE  event (
    id serial primary key,
    name varchar(100) NOT NULL,
    location varchar(100),
    start_date TIMESTAMP,
    organization_id INTEGER,
    FOREIGN KEY (organization_id) REFERENCES organization(id)
);

