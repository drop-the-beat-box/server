package store.dropthebeatbox.app.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.web.dto.FileRequestDto;
import store.dropthebeatbox.app.web.dto.FileResponseDto;

@RestController
public class FileRestController {

    @GetMapping("/member/file/{fileId}")
    public ResponseEntity<FileResponseDto.FileDto> getFileByFileId(@PathVariable(name = "fileId") Long fileId){
        return null;
    }

    @GetMapping("/member/files")
    public ResponseEntity<FileResponseDto.FileListDto> getFileListByFiledId(){
        return null;
    }

    @PostMapping(value = "/member/file", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<FileResponseDto.CreateFileDto> createFile(@ModelAttribute FileRequestDto.CreateFileDto request){
        return null;
    }

    @PatchMapping("/member/file")
    public ResponseEntity<FileResponseDto.UpdateFileDto> updateFile(@RequestBody FileRequestDto.UpdateFileDto request){
        return null;
    }

    @DeleteMapping("/member/file/{fileId}")
    public ResponseEntity<FileResponseDto.DeleteFileDto> deleteFile(@PathVariable(name = "fileId") Long fileId){
        return null;
    }
}
