package store.dropthebeatbox.app.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.web.dto.MemberResponseDto;
import store.dropthebeatbox.app.web.dto.MemberRequestDto;

@Tag(name = "Member API", description = "멤버 조회, 추가, 수정, 삭제")
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

    @PatchMapping(value = "/team/member", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MemberResponseDto.UpdateMemberDto> updateMember(@ModelAttribute MemberRequestDto.UpdateMemberDto request) {
        return null;
    }

    @DeleteMapping("/team/member")
    public ResponseEntity<MemberResponseDto.DeleteMemberDto> deleteMember() {
        return null;
    }
}
