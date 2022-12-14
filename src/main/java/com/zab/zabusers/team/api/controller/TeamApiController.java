package com.zab.zabusers.team.api.controller;

import com.zab.zabusers.team.domain.entity.Team;
import com.zab.zabusers.team.domain.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamApiController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping
    public List<Team> list() {
        return teamRepository.findAll();
    }
}
