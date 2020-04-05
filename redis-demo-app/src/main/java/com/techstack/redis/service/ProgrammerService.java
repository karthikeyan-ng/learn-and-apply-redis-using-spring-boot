package com.techstack.redis.service;

public interface ProgrammerService {

    void setProgrammerAsString(String idKey, String programmer);

    String getProgrammerAsString(String idKey);

}
