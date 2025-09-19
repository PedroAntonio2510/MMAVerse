CREATE TABLE event_streaming (
    event_id INT,
    streaming_id INT,
    CONSTRAINT fk_event_streaming_event FOREIGN KEY (event_id) REFERENCES event(id),
    CONSTRAINT fk_event_streaming_streaming FOREIGN KEY (streaming_id) REFERENCES streaming(id)
);