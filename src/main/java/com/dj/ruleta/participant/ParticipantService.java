package com.dj.ruleta.participant;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public Participant createParticipant(ParticipantRegisterDto participantRegisterDto) {
        Participant participant = new Participant(participantRegisterDto);
        return participantRepository.save(participant);
    }

    public List<Participant> getParticipants(String status) {
        return participantRepository.findParticipantByStatus(ParticipantEnum.valueOf(status));
    }

    public Participant updateParticipantStatus(String username, ParticipantStatusDto participantStatusDto) {
        Participant participant = participantRepository.findByUsername(username);
        if (participant == null) {
            throw new RuntimeException("Participant not found");
        }
        participant.setStatus(participantStatusDto.status());
        return participantRepository.save(participant);
    }
}
