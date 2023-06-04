package store.dropthebeatbox.app.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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

    @Operation(summary = "파일을 공유하는 멤버 목록 조회", description = "해당 파일을 공유하는 멤버 목록을 조회합니다.")
    @Parameters({
            @Parameter(name = "fileId", description = "조회하고자 하는 파일의 id")
    })
    @GetMapping("/shared/{fileId}/members")
    public ResponseEntity<SharedFileResponseDto.SharedMemberListDto> getMemberListByShared(@PathVariable(name = "fileId") @ExistFile Long fileId){
        List<Member> memberList = sharedFileService.findByFileId(fileId);
        return ResponseEntity.ok(SharedFileConverter.toSharedMemberListDto(memberList));
    }

    @Operation(summary = "특정 파일을 다른 멤버에게 공유", description = "특정 파일을 다른 멤버에게 공유합니다.")
    @Parameters({
            @Parameter(name = "fileId", description = "공유하고자 하는 파일의 id"),
            @Parameter(name = "memberId", description = "공유하고 싶은 멤버의 id")
    })
    @PostMapping("/shared/{fileId}/member/{memberId}")
    public ResponseEntity<SharedFileResponseDto.CreateSharedFileDto> createSharedFile(@PathVariable(name = "fileId") @ExistFile Long fileId,
                                                                                      @PathVariable(name = "memberId") @ExistMember Long memberId){
        SharedFile sharedFile = sharedFileService.create(fileId, memberId);
        return ResponseEntity.ok(SharedFileConverter.toCreateSharedFileDto(sharedFile));
    }

    @Operation(summary = "공유 멤버 목록에서 삭제", description = "공유하던 멤버를 없애고 싶을 때 사용합니다.")
    @Parameters({
            @Parameter(name = "fileId", description = "파일의 id"),
            @Parameter(name = "memberId", description = "공유를 중단할 다른 멤버의 id")
    })
    @DeleteMapping("/shared/{fileId}/member/{memberId}")
    public ResponseEntity<SharedFileResponseDto.DeleteSharedMemberDto> deleteSharedFile(@PathVariable(name = "fileId") @ExistFile Long fileId,
                                                                                        @PathVariable(name = "memberId") @ExistMember Long memberId){
        sharedFileService.delete(fileId, memberId);
        return ResponseEntity.ok(SharedFileConverter.toDeleteSharedMemberDto());
    }
}
