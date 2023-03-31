package store.dropthebeatbox.app.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import store.dropthebeatbox.app.web.dto.FriendResponseDto;

@RestController
public class FriendRestController {

    @GetMapping("/member/friends")
    public ResponseEntity<FriendResponseDto.FriendListDto> getFriendList() {
        return null;
    }
}
