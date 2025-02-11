package com.dj.ruleta.participant;

public record ParticipantResponseDto(
        String id,
        String username,
        String status
) {
    public ParticipantResponseDto(Participant participant) {
        this(participant.getId(), participant.getUsername(), participant.getStatus().name());
    }
}
