package com.zab.zabusers.team.domain.signup;

import com.zab.zabusers.team.domain.Team;
import com.zab.zabusers.team.domain.TeamRepository;
import com.zab.zabusers.team.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamGeneratorService {

    @Autowired
    private TeamRepository teamRepository;

    public Team establishTeamFor(User owner) {
        Team team = new Team();
        team.setOwner(owner);
        team.setName(extractTeamNameFromUsername(owner));
        teamRepository.save(team);

        return team;
    }

    private String extractTeamNameFromUsername(User owner) {
        return owner.getUsername().split("@")[0];
    }
}
