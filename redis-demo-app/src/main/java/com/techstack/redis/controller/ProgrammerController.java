package com.techstack.redis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techstack.redis.model.Programmer;
import com.techstack.redis.service.ProgrammerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
public class ProgrammerController {

    private final ProgrammerService programmerService;

    @PostMapping("/programmer-string")
    public ResponseEntity addTopic(@RequestBody Programmer programmer) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        programmerService.setProgrammerAsString(
                String.valueOf(programmer.getId()), mapper.writeValueAsString(programmer));
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/programmer-string/{id}")
    public ResponseEntity<String> readString(@PathVariable String id) {
        return ResponseEntity.ok().body(programmerService.getProgrammerAsString(id));
    }

    @PostMapping("/programmers-list")
    public ResponseEntity addToProgrammerList(@RequestBody Programmer programmer) {
        programmerService.addToProgrammersList(programmer);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/programmers-list")
    public ResponseEntity<List<Programmer>> addToProgrammerList() {
        return ResponseEntity.ok().body(programmerService.getProgrammersListMembers());
    }

    @GetMapping("/programmers-list/count")
    public ResponseEntity<Long> getProgrammersListCount() {
        return ResponseEntity.ok().body(programmerService.getProgrammersListCount());
    }

    @PostMapping("/programmers-set")
    public ResponseEntity addToProgrammerSet(@RequestBody Programmer... programmer) {
        programmerService.addToProgrammersSet(programmer);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/programmers-set")
    public ResponseEntity<Set<Programmer>> addToProgrammerSet() {
        return ResponseEntity.ok().body(programmerService.getProgrammersSetMembers());
    }

    @PostMapping("/programmers-set/member")
    public ResponseEntity<Boolean> getProgrammersListCount(@RequestBody Programmer programmer) {
        return ResponseEntity.ok().body(programmerService.isSetMember(programmer));
    }
}
