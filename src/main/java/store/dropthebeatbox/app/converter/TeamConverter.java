package store.dropthebeatbox.app.converter;

import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.Team;
import store.dropthebeatbox.app.web.dto.TeamRequestDto;
import store.dropthebeatbox.app.web.dto.TeamResponseDto;

public class TeamConverter {

    public static TeamResponseDto.CreateTeamDto toCreateTeamDto(Team team) {
        return  TeamResponseDto.CreateTeamDto.builder()
                .teamId(team.getId())
                .createdAt(team.getCreatedAt())
                .build();
    }

    public static Team toTeam(TeamRequestDto.CreateTeamDto request, Member member) {
        return Team.builder()
                .name(request.getName())
                .member(member)
                .build();
    }

    public static TeamResponseDto.UpdateTeamDto toUpdateTeamDto(Team team) {
        return TeamResponseDto.UpdateTeamDto.builder()
                .teamId(team.getId())
                .updatedAt(team.getUpdatedAt())
                .build();
    }
}
