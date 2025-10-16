CREATE TABLE ranking (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(100),
    organization_id INT NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT fk_organization_id FOREIGN KEY (organization_id) REFERENCES organization(id)
);