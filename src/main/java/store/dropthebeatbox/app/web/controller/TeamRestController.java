package store.dropthebeatbox.app.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
}
