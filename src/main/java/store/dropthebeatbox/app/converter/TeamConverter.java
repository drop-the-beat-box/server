package store.dropthebeatbox.app.converter;

import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.Team;
import store.dropthebeatbox.app.web.dto.TeamRequestDto;
import store.dropthebeatbox.app.web.dto.TeamResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TeamConverter {

    public static TeamResponseDto.CreateTeamDto toCreateTeamDto(Team team) {
        return TeamResponseDto.CreateTeamDto.builder()
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

    public static TeamResponseDto.DeleteTeamDto toDeleteTeamDto() {
        return TeamResponseDto.DeleteTeamDto.builder()
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static TeamResponseDto.TeamDto toTeamDto(Team team) {
        return TeamResponseDto.TeamDto.builder()
                .teamId(team.getId())
                .teamName(team.getName())
                .build();
    }

    private static List<TeamResponseDto.TeamDto> toTeamDtoList(List<Team> teamList) {
        return teamList.stream()
                .map(team -> toTeamDto(team))
                .collect(Collectors.toList());
    }

    public static TeamResponseDto.TeamListDto toTeamListDto(List<Team> teamList) {
        return TeamResponseDto.TeamListDto.builder()
                .teamDtoList(toTeamDtoList(teamList))
                .size(teamList.size())
                .build();
    }
}
