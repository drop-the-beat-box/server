package store.dropthebeatbox.app.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.auth.annotation.AuthUser;
import store.dropthebeatbox.app.converter.FileConverter;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.service.FileService;
import store.dropthebeatbox.app.web.dto.FileRequestDto;
import store.dropthebeatbox.app.web.dto.FileResponseDto;

import java.util.List;

@Tag(name = "File API", description = "파일 조회, 등록, 수정, 삭제")
@RestController
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;

    @GetMapping("/member/file/{fileId}")
    public ResponseEntity<FileResponseDto.FileDto> getFileByFileId(@PathVariable(name = "fileId") Long fileId) {
        File file = fileService.findByFileId(fileId);
        return ResponseEntity.ok(FileConverter.toFileDto(file));
    }

    @GetMapping("/member/files")
    public ResponseEntity<FileResponseDto.FileListDto> getFileListByFiledId(@AuthUser Member member) {
        List<File> fileList = fileService.findByMember(member);
        return ResponseEntity.ok(FileConverter.toFileListDto(fileList));
    }

    @PostMapping(value = "/member/file", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<FileResponseDto.CreateFileDto> createFile(@ModelAttribute FileRequestDto.CreateFileDto request){
        File file = fileService.create(request);
        return ResponseEntity.ok(FileConverter.toCreateFileDto(file));
    }

    @PatchMapping("/member/file")
    public ResponseEntity<FileResponseDto.UpdateFileDto> updateFile(@RequestBody FileRequestDto.UpdateFileDto request){
        File file = fileService.update(request);
        return ResponseEntity.ok(FileConverter.toUpdateFileDto(file));
    }

    @DeleteMapping("/member/file/{fileId}")
    public ResponseEntity<FileResponseDto.DeleteFileDto> deleteFile(@PathVariable(name = "fileId") Long fileId){
        fileService.delete(fileId);
        return ResponseEntity.ok(FileConverter.toDeleteFileDto(fileId));
    }
}
