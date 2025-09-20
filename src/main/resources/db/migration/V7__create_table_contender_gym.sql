CREATE TABLE contender_gym (
    contender_id INT,
    gym_id INT,
    CONSTRAINT fk_contender_gym_contender FOREIGN KEY (contender_id) REFERENCES contender(id),
    CONSTRAINT fk_contender_gym_gym FOREIGN KEY (gym_id) REFERENCES gym(id)
);