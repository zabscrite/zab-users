package com.zab.zabusers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teams")
public class TeamApiController {

    @GetMapping
    public Team list() {
        Team team = new Team();
        team.setName("Hello Team!");
        return team;
    }
}
