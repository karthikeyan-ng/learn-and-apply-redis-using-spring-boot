package com.techstack.redis.service;

import com.techstack.redis.model.Programmer;
import com.techstack.redis.repository.ProgrammerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void addToProgrammersList(Programmer programmer) {
        repository.addToProgrammersList(programmer);
    }

    @Override
    public List<Programmer> getProgrammersListMembers() {
        return repository.getProgrammersListMembers();
    }

    @Override
    public Long getProgrammersListCount() {
        return repository.getProgrammersListCount();
    }
}
