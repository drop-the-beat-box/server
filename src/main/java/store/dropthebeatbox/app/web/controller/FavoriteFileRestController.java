package store.dropthebeatbox.app.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.web.dto.FavoriteFileResponseDto;

@Tag(name = "FavoriteFile API", description = "중요 파일 조회, 추가, 삭제")
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
    public ResponseEntity<FavoriteFileResponseDto.CreateFavoriteFileDto> createFavoriteFile(@PathVariable(name = "fileId") Long fileId){
        return  null;
    }

    @DeleteMapping("/member/favorite/{fileId}")
    public ResponseEntity<FavoriteFileResponseDto.DeleteFavoriteFile> deleteFavoriteFile(@PathVariable(name = "fileId") Long fileId){
        return null;
    }
}
