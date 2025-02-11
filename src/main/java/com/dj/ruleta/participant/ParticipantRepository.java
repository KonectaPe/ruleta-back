package com.dj.ruleta.participant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, String> {
    Participant findByUsername(String username);

    @Query("SELECT p FROM Participant p WHERE p.status = :status  ORDER BY p.createdAt ASC")
    List<Participant> findParticipantByStatus(ParticipantEnum status);
}
