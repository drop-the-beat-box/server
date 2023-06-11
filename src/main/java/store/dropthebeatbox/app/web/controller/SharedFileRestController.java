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
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.SharedFile;
import store.dropthebeatbox.app.service.SharedFileService;
import store.dropthebeatbox.app.validation.annotation.ExistFile;
import store.dropthebeatbox.app.validation.annotation.ExistMember;
import store.dropthebeatbox.app.validation.annotation.ExistTeam;
import store.dropthebeatbox.app.web.dto.SharedFileResponseDto;

import java.util.List;

@Tag(name = "SharedFile API", description = "공유 파일 조회, 추가, 삭제")
@Validated
@RestController
@RequiredArgsConstructor
public class SharedFileRestController {

    private final SharedFileService sharedFileService;



    @Operation(summary = "특정 파일을 팀 내 공유", description = "특정 파일을 팀에서 공유합니다. 팀에서 파일을 공유하고 싶으면 파일 업로드 API와 이 API를 사용하면 됩니다.")
    @Parameters({
            @Parameter(name = "fileId", description = "공유하고자 하는 파일의 id"),
            @Parameter(name = "teamId", description = "공유하고 싶은 팀의 id")
    })
    @PostMapping("/shared/{fileId}/team/{teamId}")
    public ResponseEntity<SharedFileResponseDto.CreateSharedFileDto> createSharedFile(@PathVariable(name = "fileId") @ExistFile Long fileId,
                                                                                      @PathVariable(name = "teamId") @ExistTeam Long teamId){
        SharedFile sharedFile = sharedFileService.create(fileId, teamId);
        return ResponseEntity.ok(SharedFileConverter.toCreateSharedFileDto(sharedFile));
    }

    @Operation(summary = "특정 팀에서 공유하는 파일 목록 조회", description = "특정 팀에서 공유하는 파일 목록을 조회합니다.")
    @Parameters({
            @Parameter(name = "teamId", description = "조회하고자 하는 팀의 id")
    })
    @GetMapping("/shared/files/team/{teamId}")
    public ResponseEntity<SharedFileResponseDto.SharedFileListDto> getFilesByTeam(@PathVariable(name = "teamId") @ExistTeam Long teamId) {
        List<File> fileList = sharedFileService.findByTeam(teamId);
        return ResponseEntity.ok(SharedFileConverter.toSharedFileListDto(fileList));

    }
}


