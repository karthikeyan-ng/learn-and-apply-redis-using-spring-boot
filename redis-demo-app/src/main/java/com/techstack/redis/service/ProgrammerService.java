package com.techstack.redis.service;

import com.techstack.redis.model.Programmer;

import java.util.List;

public interface ProgrammerService {

    void setProgrammerAsString(String idKey, String programmer);

    String getProgrammerAsString(String idKey);

    void addToProgrammersList(Programmer programmer);

    List<Programmer> getProgrammersListMembers();

    Long getProgrammersListCount();
}
