package com.zab.zabusers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamApiController {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping
    public List<Team> list() {
        return teamRepository.findAll();
    }
}
