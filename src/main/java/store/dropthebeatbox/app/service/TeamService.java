package store.dropthebeatbox.app.service;

import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.Team;
import store.dropthebeatbox.app.web.dto.TeamRequestDto;

public interface TeamService {
    Team create(TeamRequestDto.CreateTeamDto request, Member member);

    Team update(Long teamId, TeamRequestDto.UpdateTeamDto request);
}
