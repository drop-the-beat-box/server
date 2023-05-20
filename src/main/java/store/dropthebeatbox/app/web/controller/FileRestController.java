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
import store.dropthebeatbox.app.converter.FileConverter;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.service.FileService;
import store.dropthebeatbox.app.validation.annotation.ExistFile;
import store.dropthebeatbox.app.web.dto.FileRequestDto;
import store.dropthebeatbox.app.web.dto.FileResponseDto;

import java.util.List;

@Tag(name = "File API", description = "파일 조회, 등록, 수정, 삭제")
@Validated
@RestController
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;

    @Operation(summary = "파일 단건 조회", description = "")
    @GetMapping("/member/file/{fileId}")
    public ResponseEntity<FileResponseDto.FileDto> getFileByFileId(@PathVariable(name = "fileId") @ExistFile Long fileId) {
        File file = fileService.findByFileId(fileId);
        return ResponseEntity.ok(FileConverter.toFileDto(file));
    }

    @Operation(summary = "파일 목록 조회", description = "내가 업로드 한 파일 목록을 조회합니다.")
    @Parameters({
            @Parameter(name = "member", hidden = true)
    })
    @GetMapping("/member/files")
    public ResponseEntity<FileResponseDto.FileListDto> getFileListByFiledId(@AuthUser Member member) {
        List<File> fileList = fileService.findByMember(member);
        return ResponseEntity.ok(FileConverter.toFileListDto(fileList));
    }


    @Operation(summary = "파일 추가", description = "")
    @Parameters({
            @Parameter(name = "member", hidden = true)
    })
    @PostMapping(value = "/member/file", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<FileResponseDto.CreateFileDto> createFile(@ModelAttribute FileRequestDto.CreateFileDto request, @AuthUser Member member){
        File file = fileService.create(request, member);
        return ResponseEntity.ok(FileConverter.toCreateFileDto(file));
    }

    @PatchMapping("/member/file/{fileId}")
    public ResponseEntity<FileResponseDto.UpdateFileDto> updateFile(@PathVariable(name = "fileId") @ExistFile Long fileId, @RequestBody FileRequestDto.UpdateFileDto request){
        File file = fileService.update(fileId, request);
        return ResponseEntity.ok(FileConverter.toUpdateFileDto(file));
    }

    @DeleteMapping("/member/file/{fileId}")
    public ResponseEntity<FileResponseDto.DeleteFileDto> deleteFile(@PathVariable(name = "fileId") @ExistFile Long fileId){
        fileService.delete(fileId);
        return ResponseEntity.ok(FileConverter.toDeleteFileDto(fileId));
    }
}
