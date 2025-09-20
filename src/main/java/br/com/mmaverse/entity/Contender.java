package br.com.mmaverse.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contender")
public class Contender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "nickname", length = 50, nullable = false)
    private String nickname;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate birthDate;

    private Integer age;

    @Column(name = "weight_class", nullable = false, length = 50)
    private String weightClass;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private Double reach;

    private int win;

    private int lose;

    private int draw;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "contender_gym",
            joinColumns = @JoinColumn(name = "contender_id"),
            inverseJoinColumns = @JoinColumn(name = "gym_id")
    )
    private List<Gym> gyms;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
