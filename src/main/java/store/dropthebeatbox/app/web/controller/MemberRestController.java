package store.dropthebeatbox.app.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.auth.annotation.AuthUser;
import store.dropthebeatbox.app.converter.MemberConverter;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.service.MemberService;
import store.dropthebeatbox.app.web.dto.MemberResponseDto;
import store.dropthebeatbox.app.web.dto.MemberRequestDto;

import java.util.List;

@Tag(name = "Member API", description = "멤버 조회, 추가, 수정, 삭제")
@RestController
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @GetMapping("/team/member/{memberId}")
    public ResponseEntity<MemberResponseDto.MemberDto> getMemberByMemberId(@PathVariable(name = "memberId") Long memberId) {
        Member member = memberService.findById(memberId);
        return ResponseEntity.ok(MemberConverter.toMemberDto(member));
    }

    @GetMapping("/team/{teamId}/members")
    public ResponseEntity<MemberResponseDto.MemberListDto> getMemberListByTeamId(@PathVariable(name = "teamId") Long teamId) {
        List<Member> memberList = memberService.findAllByTeamId(teamId);
        return ResponseEntity.ok(MemberConverter.memberListDto(memberList));
    }

    @PostMapping("/team/{teamId}/member/{memberId}")
    public ResponseEntity<MemberResponseDto.JoinMemberDto> createMemberInTeam(@PathVariable(name = "teamId") Long teamId, @PathVariable(name = "memberId") Long memberId) {
        Member member = memberService.insertToTeam(memberId,teamId);
        return ResponseEntity.ok(MemberConverter.joinMemberDto(memberId, teamId));
    }

    @PatchMapping(value = "/team/member", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MemberResponseDto.UpdateMemberDto> updateMember(@ModelAttribute MemberRequestDto.UpdateMemberDto request, @AuthUser Member member) {
        Member updatedMember = memberService.update(member.getId(), request);
        return ResponseEntity.ok(MemberConverter.updateMemberDto(updatedMember));
    }

    @DeleteMapping("/team/member")
    public ResponseEntity<MemberResponseDto.DeleteMemberDto> deleteMember(@AuthUser Member member) {
        memberService.delete(member.getId());
        return ResponseEntity.ok(MemberConverter.deleteMemberDto());
    }
}
