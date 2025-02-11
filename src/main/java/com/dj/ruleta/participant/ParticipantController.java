package com.dj.ruleta.participant;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<ParticipantResponseDto> createParticipant(@RequestBody @Valid ParticipantRegisterDto participantRegisterDto, UriComponentsBuilder uriComponentsBuilder) {
        Participant participant = participantService.createParticipant(participantRegisterDto);
        ParticipantResponseDto participantResponseDto = new ParticipantResponseDto(participant);
        URI url = uriComponentsBuilder.path("/participant/{id}").buildAndExpand(participant.getId()).toUri();
        return ResponseEntity.created(url).body(participantResponseDto);
    }

    @GetMapping("/list/{status}")
    public ResponseEntity<List<ParticipantResponseDto>> findAll(@PathVariable String status) {
        List<Participant> participants = participantService.getParticipants(status);
        List<ParticipantResponseDto> response = participants.stream().map(ParticipantResponseDto::new).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/status/{username}")
    @Transactional
    public ResponseEntity<ParticipantResponseDto> updateParticipantStatus(@PathVariable String username, @RequestBody @Valid ParticipantStatusDto participantStatusDto) {
        System.out.println("username = " + username);
        System.out.println("participantStatusDto = " + participantStatusDto.status());
        Participant participant = participantService.updateParticipantStatus(username, participantStatusDto);
        ParticipantResponseDto participantResponseDto = new ParticipantResponseDto(participant);
        return ResponseEntity.ok(participantResponseDto);
    }
}
