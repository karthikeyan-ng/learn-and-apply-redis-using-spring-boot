package com.techstack.redis.service;

import com.techstack.redis.repository.ProgrammerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProgrammerServiceImpl implements ProgrammerService {

    private final ProgrammerRepository repository;

    @Override
    public void setProgrammerAsString(String idKey, String programmer) {
        repository.setProgrammerAsString(idKey, programmer);
    }

    @Override
    public String getProgrammerAsString(String idKey) {
        return repository.getProgrammerAsString(idKey);
    }
}
