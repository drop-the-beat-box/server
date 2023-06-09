package store.dropthebeatbox.app.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.auth.annotation.AuthUser;
import store.dropthebeatbox.app.converter.MemberConverter;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.service.MemberService;
import store.dropthebeatbox.app.validation.annotation.ExistMember;
import store.dropthebeatbox.app.validation.annotation.ExistTeam;
import store.dropthebeatbox.app.web.dto.MemberResponseDto;
import store.dropthebeatbox.app.web.dto.MemberRequestDto;

import java.util.List;

@Tag(name = "Member API", description = "멤버 조회, 추가, 수정, 삭제")
@RestController
@Validated
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @Operation(summary = "멤버 한명 단건 조회", description = "멤버 아이디를 통해 한명의 정보 조회")
    @Parameters({
            @Parameter(name = "memberId", description = "조회를 희망하는 멤버의 아이디 입니다."),
            @Parameter(name = "member", hidden = true)
    })
    @GetMapping("/team/member/{memberId}")
    public ResponseEntity<MemberResponseDto.MemberDto> getMemberByMemberId(@PathVariable(name = "memberId") @ExistMember Long memberId , @AuthUser Member member) {
        Member targetMember = memberService.findById(memberId);
        return ResponseEntity.ok(MemberConverter.toMemberDto(targetMember, member));
    }

    @Operation(summary = "하나의 팀, 그룹에 속한 모든 멤버 조회", description = "팀 아이디를 통해 속한 멤버를 조회합니다.")
    @Parameters({
            @Parameter(name = "teamId", description = "조회를 희망하는 팀 아이디입니다."),
            @Parameter(name = "member", hidden = true)
    })
    @GetMapping("/team/{teamId}/members")
    public ResponseEntity<MemberResponseDto.MemberListDto> getMemberListByTeamId(@PathVariable(name = "teamId") @ExistTeam Long teamId, @AuthUser Member member) {
        List<Member> memberList = memberService.findAllByTeamId(teamId);
        return ResponseEntity.ok(MemberConverter.toMemberListDto(memberList, member));
    }

    @Operation(summary = "팀에 선택한 멤버 여러명 추가하기", description = "팀 아이디를 통해 팀에 멤버를 추가, body에 memberIdList로 멤버 아이디 포함")
    @Parameters({
            @Parameter(name = "member", hidden = true),
            @Parameter(name = "teamId", description = "멤버를 추가 할 팀 아이디입니다.")
    })
    @PostMapping("/team/{teamId}/member")
    public ResponseEntity<MemberResponseDto.JoinMemberListDto> createMemberInTeam(@RequestBody MemberRequestDto.AddTeamMemberDto request ,@PathVariable(name = "teamId") @ExistTeam Long teamId, @AuthUser Member member) {
        List<Member> teamMembers = memberService.insertToTeam(teamId, request);
        return ResponseEntity.ok(MemberConverter.toJoinMemberListDto(teamMembers, teamId));
    }

    @Operation(summary = "멤버 정보 수정하기", description = "멤버 이름과 프로필 사진의 수정을 하는 API입니다.")
    @Parameters({
            @Parameter(name = "member", hidden = true)
    })
    @PatchMapping(value = "/team/member", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MemberResponseDto.UpdateMemberDto> updateMember(@ModelAttribute MemberRequestDto.UpdateMemberDto request, @AuthUser Member member) {
        Member updatedMember = memberService.update(member.getId(), request);
        return ResponseEntity.ok(MemberConverter.toUpdateMemberDto(updatedMember));
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴입니다.")
    @Parameters(
            @Parameter(name = "member", hidden = true)
    )
    @DeleteMapping("/team/member")
    public ResponseEntity<MemberResponseDto.DeleteMemberDto> deleteMember(@AuthUser Member member) {
        memberService.delete(member.getId());
        return ResponseEntity.ok(MemberConverter.toDeleteMemberDto());
    }

    @Operation(summary = "키워드로 검색을 합니다.", description = "keyword를 쿼리 스트링으로 합니다.")
    @GetMapping("/team/member/search")
    public ResponseEntity<MemberResponseDto.MemberListDto> searchMember(@RequestParam String keyword, @AuthUser Member member){
        List<Member> searchMemberList = memberService.search(keyword);
        return ResponseEntity.ok(MemberConverter.toMemberListDto(searchMemberList, member));
    }
}
