package store.dropthebeatbox.app.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.auth.annotation.AuthUser;
import store.dropthebeatbox.app.converter.FavoriteFileConverter;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.FavoriteFile;
import store.dropthebeatbox.app.service.FavoriteFileService;
import store.dropthebeatbox.app.web.dto.FavoriteFileResponseDto;

import java.util.List;

@Tag(name = "FavoriteFile API", description = "중요 파일 조회, 추가, 삭제")
@RestController
@RequiredArgsConstructor
public class FavoriteFileRestController {

    private final FavoriteFileService favoriteFileService;

    @GetMapping("/member/favorites")
    public ResponseEntity<FavoriteFileResponseDto.FavoriteFileListDto> getFavoriteFileListByMemberId(@AuthUser Member member) {
        List<File> fileList = favoriteFileService.findByMember(member);
        return ResponseEntity.ok(FavoriteFileConverter.toFavoriteFileListDto(fileList));
    }

    @PostMapping("/member/favorite/{fileId}")
    public ResponseEntity<FavoriteFileResponseDto.CreateFavoriteFileDto> createFavoriteFile(@PathVariable(name = "fileId") Long fileId, @AuthUser Member member){
        FavoriteFile favoriteFile = favoriteFileService.create(fileId, member);
        return ResponseEntity.ok(FavoriteFileConverter.toCreateFavoriteFileDto(favoriteFile));
    }

    @DeleteMapping("/member/favorite/{fileId}")
    public ResponseEntity<FavoriteFileResponseDto.DeleteFavoriteFile> deleteFavoriteFile(@PathVariable(name = "fileId") Long fileId, @AuthUser Member member){
        favoriteFileService.delete(fileId, member);
        return ResponseEntity.ok(FavoriteFileConverter.toDeleteFavoriteFile());
    }
}
