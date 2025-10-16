ALTER TABLE contender ADD COLUMN ranking_id BIGINT;
ALTER TABLE contender ADD CONSTRAINT fk_contender_ranking FOREIGN KEY (ranking_id) REFERENCES ranking(id);
