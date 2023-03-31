package store.dropthebeatbox.app.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.web.dto.FavoriteFileRequestDto;
import store.dropthebeatbox.app.web.dto.FavoriteFileResponseDto;

@RestController
public class FavoriteFileRestController {

    @GetMapping("/member/favorite")
    public ResponseEntity<FavoriteFileResponseDto.FavoriteFileDto> getFavoriteFileByMemberId(){
        return null;
    }

    @GetMapping("/member/favorites")
    public ResponseEntity<FavoriteFileResponseDto.FavoriteFileListDto> getFavoriteFileListByMemberId(){
        return null;
    }

    @PostMapping("/member/favorite/{fileId}")
    public ResponseEntity<FavoriteFileRequestDto.CreateFavoriteFileDto> createFavoriteFile(@PathVariable(name = "fileId") Long fileId, @RequestBody FavoriteFileRequestDto.CreateFavoriteFileDto request){
        return  null;
    }
}