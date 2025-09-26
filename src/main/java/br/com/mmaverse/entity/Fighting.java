package br.com.mmaverse.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fighting")
public class Fighting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "contender_red_corner_id", nullable = false)
    private Contender contenderRedCorner;

    @ManyToOne
    @JoinColumn(name = "contender_blue_corner_id", nullable = false)
    private Contender contenderBlueCorner;

    @ManyToOne
    @JoinColumn(name = "contender_winner")
    private Contender winner;

    @Column(name = "method_of_victory", length = 50)
    private String methodOfVictory;

    @Column(name = "end_round")
    private Integer endRound;

    @Column(name = "end_time")
    private String endTime;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
