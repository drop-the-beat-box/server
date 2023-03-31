package store.dropthebeatbox.app.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.web.dto.MemberRequestDto;
import store.dropthebeatbox.app.web.dto.MemberResponseDto;

@RestController
public class MemberRestController {

    @GetMapping("/team/member/{memberId}")
    public ResponseEntity<MemberResponseDto.MemberDto> getMemberByMemberId(@PathVariable(name = "memberId") Long memberId) {
        return null;
    }

    @GetMapping("/team/{teamId}/members")
    public ResponseEntity<MemberResponseDto.MemberListDto> getMemberListByTeamId(@PathVariable(name = "teamId") Long teamId) {
        return null;
    }

    @PostMapping("/team/{teamId}/member/{memberId}")
    public ResponseEntity<MemberResponseDto.CreateMemberDto> createMemberInTeam(@PathVariable(name = "teamId") Long teamId, @PathVariable(name = "memberId") Long memberId) {
        return null;
    }

    @PatchMapping("/team/member")
    public ResponseEntity<MemberResponseDto.UpdateMemberDto> updateMember(@RequestBody MemberRequestDto.UpdateMemberDto request) {
        return null;
    }

    @DeleteMapping("/team/member")
    public ResponseEntity<MemberResponseDto.DeleteMemberDto> deleteMember() {
        return null;
    }
}
