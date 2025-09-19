package br.com.mmaverse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {

    @Id
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @ManyToMany
    @JoinTable(name = "event_streaming",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "streaming_id")
    )
    private List<Streaming> streaming;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
