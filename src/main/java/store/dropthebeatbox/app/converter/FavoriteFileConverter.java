package store.dropthebeatbox.app.converter;

import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.mapping.FavoriteFile;
import store.dropthebeatbox.app.web.dto.FavoriteFileResponseDto;
import store.dropthebeatbox.app.web.dto.FileResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FavoriteFileConverter {

    public static FavoriteFileResponseDto.FavoriteFileDto toFavoriteFileDto(File file) {
        return FavoriteFileResponseDto.FavoriteFileDto.builder()
                .fileId(file.getId())
                .name(file.getName())
                .url(file.getUrl())
                .description(file.getDescription())
                .createdAt(file.getCreatedAt())
                .updatedAt(file.getUpdatedAt())
                .build();
    }

    public static FavoriteFileResponseDto.FavoriteFileListDto toFavoriteFileListDto(List<File> fileList) {
        List<FavoriteFileResponseDto.FavoriteFileDto> fileDtoList =
                fileList.stream()
                        .map(file -> toFavoriteFileDto(file))
                        .collect(Collectors.toList());

        return FavoriteFileResponseDto.FavoriteFileListDto.builder()
                .favoriteFileDtoList(fileDtoList)
                .size(fileDtoList.size())
                .build();
    }

    public static FavoriteFileResponseDto.CreateFavoriteFileDto toCreateFavoriteFileDto(FavoriteFile favoriteFile) {
        return FavoriteFileResponseDto.CreateFavoriteFileDto.builder()
                .favoriteFileId(favoriteFile.getId())
                .fileId(favoriteFile.getFile().getId())
                .memberId(favoriteFile.getMember().getId())
                .createdAt(favoriteFile.getCreatedAt())
                .build();
    }

    public static FavoriteFileResponseDto.DeleteFavoriteFile toDeleteFavoriteFile() {
        return FavoriteFileResponseDto.DeleteFavoriteFile.builder()
                .deletedAt(LocalDateTime.now())
                .build();
    }
}
