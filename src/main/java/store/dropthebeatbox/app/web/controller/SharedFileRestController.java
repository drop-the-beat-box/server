package store.dropthebeatbox.app.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import store.dropthebeatbox.app.web.dto.SharedFileResponseDto;

@RestController
public class SharedFileRestController {

    @GetMapping("/shared/{fileId}/members")
    public ResponseEntity<SharedFileResponseDto.sharedMemberListDto> getMemberListByShared(@PathVariable(name = "fileId") Long fileId){
        return null;
    }
}
