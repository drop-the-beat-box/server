package store.dropthebeatbox.app.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
}
