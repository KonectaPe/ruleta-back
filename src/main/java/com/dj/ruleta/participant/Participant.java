package com.dj.ruleta.participant;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "participant")
@Entity(name = "Participant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, unique = true, length = 8)
    private String username;
    @Enumerated(EnumType.STRING)
    private ParticipantEnum status;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Participant(ParticipantRegisterDto participantRegisterDto) {
        this.username = participantRegisterDto.username();
        this.status = ParticipantEnum.PENDIENTE;
        this.createdAt = LocalDateTime.now();
    }
}
