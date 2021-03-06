package com.techstack.redis.service;

import com.techstack.redis.model.Programmer;
import com.techstack.redis.repository.ProgrammerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Override
    public void addToProgrammersSet(Programmer... programmers) {
        repository.addToProgrammersSet(programmers);
    }

    @Override
    public Set<Programmer> getProgrammersSetMembers() {
        return repository.getProgrammersSetMembers();
    }

    @Override
    public boolean isSetMember(Programmer programmer) {
        return repository.isSetMember(programmer);
    }

    @Override
    public void saveHash(Programmer programmer) {
        repository.saveHash(programmer);
    }

    @Override
    public void updateHash(Programmer programmer) {
        repository.updateHash(programmer);
    }

    @Override
    public Map<Integer, Programmer> findAllHash() {
        return repository.findAllHash();
    }

    @Override
    public Programmer findInHash(int id) {
        return repository.findInHash(id);
    }

    @Override
    public void deleteHash(int id) {
        repository.deleteHash(id);
    }
}
