package store.dropthebeatbox.app.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.web.dto.SharedFileResponseDto;

@Tag(name = "SharedFile API", description = "공유 파일 조회, 추가, 삭제")
@RestController
public class SharedFileRestController {

    @GetMapping("/shared/{fileId}/members")
    public ResponseEntity<SharedFileResponseDto.SharedMemberListDto> getMemberListByShared(@PathVariable(name = "fileId") Long fileId){
        return null;
    }

    @PostMapping("/shared/{fileId}/member/{memberId}")
    public ResponseEntity<SharedFileResponseDto.CreateSharedFileDto> createSharedFile(@PathVariable(name = "fileId")Long fileId, @PathVariable(name = "memberId") Long memberId){
        return null;
    }

    @DeleteMapping("/shared/{fileId}/member/{memberId}")
    public ResponseEntity<SharedFileResponseDto.DeleteSharedMemberDto> deleteSharedFile(@PathVariable(name = "fileId") Long fileId, @PathVariable(name = "memberId") Long memberId){
        return null;
    }
}
