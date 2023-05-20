package store.dropthebeatbox.app.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.converter.SharedFileConverter;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.SharedFile;
import store.dropthebeatbox.app.service.SharedFileService;
import store.dropthebeatbox.app.validation.annotation.ExistFile;
import store.dropthebeatbox.app.validation.annotation.ExistMember;
import store.dropthebeatbox.app.web.dto.SharedFileResponseDto;

import java.util.List;

@Tag(name = "SharedFile API", description = "공유 파일 조회, 추가, 삭제")
@Validated
@RestController
@RequiredArgsConstructor
public class SharedFileRestController {

    private final SharedFileService sharedFileService;

    @GetMapping("/shared/{fileId}/members")
    public ResponseEntity<SharedFileResponseDto.SharedMemberListDto> getMemberListByShared(@PathVariable(name = "fileId") @ExistFile Long fileId){
        List<Member> memberList = sharedFileService.findByFileId(fileId);
        return ResponseEntity.ok(SharedFileConverter.toSharedMemberListDto(memberList));
    }

    @PostMapping("/shared/{fileId}/member/{memberId}")
    public ResponseEntity<SharedFileResponseDto.CreateSharedFileDto> createSharedFile(@PathVariable(name = "fileId") @ExistFile Long fileId,
                                                                                      @PathVariable(name = "memberId") @ExistMember Long memberId){
        SharedFile sharedFile = sharedFileService.create(fileId, memberId);
        return ResponseEntity.ok(SharedFileConverter.toCreateSharedFileDto(sharedFile));
    }

    @DeleteMapping("/shared/{fileId}/member/{memberId}")
    public ResponseEntity<SharedFileResponseDto.DeleteSharedMemberDto> deleteSharedFile(@PathVariable(name = "fileId") @ExistFile Long fileId,
                                                                                        @PathVariable(name = "memberId") @ExistMember Long memberId){
        sharedFileService.delete(fileId, memberId);
        return ResponseEntity.ok(SharedFileConverter.toDeleteSharedMemberDto());
    }
}
