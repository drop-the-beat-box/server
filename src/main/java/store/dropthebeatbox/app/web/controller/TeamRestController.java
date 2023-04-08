package store.dropthebeatbox.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.auth.annotation.AuthUser;
import store.dropthebeatbox.app.converter.TeamConverter;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.Team;
import store.dropthebeatbox.app.service.TeamService;
import store.dropthebeatbox.app.web.dto.TeamRequestDto;
import store.dropthebeatbox.app.web.dto.TeamResponseDto;

@RestController
@RequiredArgsConstructor
public class TeamRestController {

    private final TeamService teamService;

    @GetMapping("/member/team/{teamId}")
    public ResponseEntity<TeamResponseDto.TeamDto> getTeamByTeamId(@PathVariable(name = "teamId") Long teamId) {
        return null;
    }

    @GetMapping("/member/{memberId}/teams")
    public ResponseEntity<TeamResponseDto.TeamListDto> getTeamListByMemberId(@PathVariable(name = "memberId") Long memberId) {
        return null;
    }

    @PostMapping("/member/team")
    public ResponseEntity<TeamResponseDto.CreateTeamDto> createTeam(@RequestBody TeamRequestDto.CreateTeamDto request, @AuthUser Member member) {
        Team team = teamService.create(request, member);
        return ResponseEntity.ok(TeamConverter.toCreateTeamDto(team));
    }

    @PatchMapping("/member/team/{teamId}")
    public ResponseEntity<TeamResponseDto.UpdateTeamDto> updateTeam(@PathVariable(name = "teamId") Long teamId, @RequestBody TeamRequestDto.UpdateTeamDto request, @AuthUser Member member) {
        Team team = teamService.update(teamId, request);
        return ResponseEntity.ok(TeamConverter.toUpdateTeamDto(team));
    }

    @DeleteMapping("/member/team/{teamId}")
    public ResponseEntity<TeamResponseDto.DeleteTeamDto> deleteTeam(@PathVariable(name = "teamId") Long teamId) {
        return null;
    }
}
