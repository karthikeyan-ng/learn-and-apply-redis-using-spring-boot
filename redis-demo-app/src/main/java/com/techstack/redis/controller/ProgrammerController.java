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
}
