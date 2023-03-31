package store.dropthebeatbox.app.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.web.dto.TeamRequestDto;
import store.dropthebeatbox.app.web.dto.TeamResponseDto;

@RestController
public class TeamRestController {

    @GetMapping("/member/team/{teamId}")
    public ResponseEntity<TeamResponseDto.TeamDto> getTeamByTeamId(@PathVariable(name = "teamId") Long teamId) {
        return null;
    }

    @GetMapping("/member/{memberId}/teams")
    public ResponseEntity<TeamResponseDto.TeamListDto> getTeamListByMemberId(@PathVariable(name = "memberId") Long memberId) {
        return null;
    }

    @PostMapping("/member/team")
    public ResponseEntity<TeamResponseDto.CreateTeamDto> createTeam(@RequestBody TeamRequestDto.CreateTeamDto request) {
        return null;
    }
}
