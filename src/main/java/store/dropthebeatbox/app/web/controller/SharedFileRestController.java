package store.dropthebeatbox.app.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.web.dto.SharedFileRequestDto;
import store.dropthebeatbox.app.web.dto.SharedFileResponseDto;

@RestController
public class SharedFileRestController {

    @GetMapping("/shared/{fileId}/members")
    public ResponseEntity<SharedFileResponseDto.sharedMemberListDto> getMemberListByShared(@PathVariable(name = "fileId") Long fileId){
        return null;
    }

    @PostMapping("/shared/{fileId}/member/{memberId}")
    public ResponseEntity<SharedFileRequestDto.CreateSharedFileDto> createSharedFile(@PathVariable(name = "fileId")Long fileId,@PathVariable(name = "memberId") Long memberId, @RequestBody SharedFileRequestDto.CreateSharedFileDto request){
        return null;
    }
}
