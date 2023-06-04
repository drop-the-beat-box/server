package store.dropthebeatbox.app.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.auth.annotation.AuthUser;
import store.dropthebeatbox.app.converter.TeamConverter;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.Team;
import store.dropthebeatbox.app.service.TeamService;
import store.dropthebeatbox.app.validation.annotation.ExistTeam;
import store.dropthebeatbox.app.web.dto.TeamRequestDto;
import store.dropthebeatbox.app.web.dto.TeamResponseDto;

import java.util.List;

@Tag(name = "Team API", description = "팀 조회, 추가, 수정, 삭제")
@Validated
@RestController
@RequiredArgsConstructor
public class TeamRestController {

    private final TeamService teamService;

    @Operation(summary = "팀 하나의 정보 조회", description = "팀의 이름을 조회합니다.")
    @Parameters(
            @Parameter(name = "teamId", description = "조회를 희망하는 팀 이름입니다.")
    )
    @GetMapping("/member/team/{teamId}")
    public ResponseEntity<TeamResponseDto.TeamDto> getTeamByTeamId(@PathVariable(name = "teamId") @ExistTeam Long teamId) {
        Team team = teamService.findById(teamId);
        return ResponseEntity.ok(TeamConverter.toTeamDto(team));
    }

    @Operation(summary = "내가 속한 팀들을 조회", description = "로그인 한 사용자가 속한 팀 목록을 조회합니다.")
    @Parameters(
            @Parameter(name = "member", hidden = true)
    )
    @GetMapping("/member/teams")
    public ResponseEntity<TeamResponseDto.TeamListDto> getTeamListByMemberId(@AuthUser Member member) {
        List<Team> teamList = teamService.findByMember(member);
        return ResponseEntity.ok(TeamConverter.toTeamListDto(teamList));
    }

    @Operation(summary = "팀 생성", description = "팀 생성 API입니다. 이름만 설정합니다.")
    @Parameters(
            @Parameter(name = "member", hidden = true)
    )
    @PostMapping("/member/team")
    public ResponseEntity<TeamResponseDto.CreateTeamDto> createTeam(@RequestBody TeamRequestDto.CreateTeamDto request, @AuthUser Member member) {
        Team team = teamService.create(request, member);
        return ResponseEntity.ok(TeamConverter.toCreateTeamDto(team));
    }


    @Operation(summary = "팀 이름 수정", description = "팀 아이디를 통해 팀 이름을 수정합니다.")
    @Parameters({
            @Parameter(name = "teamId", description = "팀 이름 수정을 희망하는 팀 아이디입니다."),
            @Parameter(name = "member", hidden = true)
        }
    )
    @PatchMapping("/member/team/{teamId}")
    public ResponseEntity<TeamResponseDto.UpdateTeamDto> updateTeam(@PathVariable(name = "teamId") @ExistTeam Long teamId, @RequestBody TeamRequestDto.UpdateTeamDto request, @AuthUser Member member) {
        Team team = teamService.update(teamId, request);
        return ResponseEntity.ok(TeamConverter.toUpdateTeamDto(team));
    }

    @Operation(summary = "팀 삭제", description = "팀 아이디를 통해 팀을 삭제합니다.")
    @Parameters(
            @Parameter(name = "teamId", description = "삭제를 희망하는 팀 아이디입니다.")
    )
    @DeleteMapping("/member/team/{teamId}")
    public ResponseEntity<TeamResponseDto.DeleteTeamDto> deleteTeam(@PathVariable(name = "teamId") @ExistTeam Long teamId) {
        teamService.delete(teamId);
        return ResponseEntity.ok(TeamConverter.toDeleteTeamDto());
    }
}
