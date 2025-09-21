CREATE TABLE fighting (
    id serial primary key,
    event_id INT NOT NULL,
    contender_red_corner_id INTEGER NOT NULL ,
    contender_blue_corner_id INTEGER NOT NULL ,
    contender_winner INTEGER,
    method_of_victory VARCHAR(50),
    end_round INTEGER,
    end_time VARCHAR(50),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_event_fighting FOREIGN KEY (event_id) REFERENCES event(id),
    CONSTRAINT fk_contender_red_corner FOREIGN KEY (contender_red_corner_id) REFERENCES contender(id),
    CONSTRAINT fk_contender_blue_corner FOREIGN KEY (contender_blue_corner_id) REFERENCES contender(id),
    CONSTRAINT fk_winner FOREIGN KEY (contender_winner) REFERENCES contender(id)
);
