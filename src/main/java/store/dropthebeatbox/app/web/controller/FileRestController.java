package store.dropthebeatbox.app.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
}
