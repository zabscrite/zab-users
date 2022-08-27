package com.zab.zabusers.team.domain.repository;

import com.zab.zabusers.team.domain.entity.Team;
import com.zab.zabusers.team.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    List<Team> findAll();

    Optional<Team> findByOwner(User user);

}
