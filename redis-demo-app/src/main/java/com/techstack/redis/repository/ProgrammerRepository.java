package com.techstack.redis.repository;

import com.techstack.redis.model.Programmer;

import java.util.List;

public interface ProgrammerRepository {

    // String
    void setProgrammerAsString(String idKey, String programmer);

    String getProgrammerAsString(String idKey);

    // List
    void addToProgrammersList(Programmer programmer);

    List<Programmer> getProgrammersListMembers();

    Long getProgrammersListCount();
}
