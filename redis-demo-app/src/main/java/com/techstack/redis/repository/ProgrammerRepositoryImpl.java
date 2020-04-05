package com.techstack.redis.repository;

import com.techstack.redis.model.Programmer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Repository
public class ProgrammerRepositoryImpl implements ProgrammerRepository {

    private static final String REDIS_LIST_KEY = "ProgrammerList";
    private static final String REDIS_SET_KEY = "ProgrammerSet";
    private static final String REDIS_HASH_KEY = "ProgrammerHash";

    private final RedisTemplate<String, Object> redisTemplate;
    private final @Qualifier("listOperations") ListOperations<String, Programmer> listOperations;
    private final @Qualifier("setOperations") SetOperations<String, Programmer> setOperations;
    private final @Qualifier("hashOperations") HashOperations<String, Integer, Programmer> hashOperations;

    /** ============= String =========== */

    @Override
    public void setProgrammerAsString(String idKey, String programmer) {
        redisTemplate.opsForValue().set(idKey, programmer);
        redisTemplate.expire(idKey, 10, TimeUnit.SECONDS);
    }

    @Override
    public String getProgrammerAsString(String idKey) {
        return (String) redisTemplate.opsForValue().get(idKey);
    }

    /** ============= List =========== */

    @Override
    public void addToProgrammersList(Programmer programmer) {
        listOperations.leftPush(REDIS_LIST_KEY, programmer);
    }

    @Override
    public List<Programmer> getProgrammersListMembers() {
        return listOperations.range(REDIS_LIST_KEY, 0, -1);
    }

    @Override
    public Long getProgrammersListCount() {
        return listOperations.size(REDIS_LIST_KEY);
    }

    @Override
    public void addToProgrammersSet(Programmer... programmers) {
        setOperations.add(REDIS_SET_KEY, programmers);
    }

    @Override
    public Set<Programmer> getProgrammersSetMembers() {
        return setOperations.members(REDIS_SET_KEY);
    }

    @Override
    public boolean isSetMember(Programmer programmer) {
        return setOperations.isMember(REDIS_SET_KEY, programmer);
    }

    @Override
    public void saveHash(Programmer programmer) {
        hashOperations.put(REDIS_HASH_KEY, programmer.getId(), programmer);
    }

    @Override
    public void updateHash(Programmer programmer) {
        hashOperations.put(REDIS_HASH_KEY, programmer.getId(), programmer);
    }

    @Override
    public Map<Integer, Programmer> findAllHash() {
        return hashOperations.entries(REDIS_HASH_KEY);
    }

    @Override
    public Programmer findInHash(int id) {
        return hashOperations.get(REDIS_HASH_KEY, id);
    }

    @Override
    public void deleteHash(int id) {
        hashOperations.delete(REDIS_HASH_KEY, id);
    }
}
