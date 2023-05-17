package store.dropthebeatbox.app.converter;

import org.springframework.http.ResponseEntity;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.web.dto.FileResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileConverter {

    public static FileResponseDto.FileDto toFileDto(File file) {
        return FileResponseDto.FileDto.builder()
                .fileId(file.getId())
                .name(file.getName())
                .url(file.getUrl())
                .description(file.getDescription())
                .createdAt(file.getCreatedAt())
                .updatedAt(file.getUpdatedAt())
                .build();
    }

    public static FileResponseDto.FileListDto toFileListDto(List<File> fileList) {
        List<FileResponseDto.FileDto> fileDtoList =
                fileList.stream()
                .map(file -> toFileDto(file))
                .collect(Collectors.toList());

        return FileResponseDto.FileListDto.builder()
                .fileDtoList(fileDtoList)
                .size(fileDtoList.size())
                .build();
    }

    public static FileResponseDto.CreateFileDto toCreateFileDto(File file) {
        return FileResponseDto.CreateFileDto.builder()
                .fileId(file.getId())
                .createdAt(file.getCreatedAt())
                .build();
    }

    public static FileResponseDto.UpdateFileDto toUpdateFileDto(File file) {
        return FileResponseDto.UpdateFileDto.builder()
                .fileId(file.getId())
                .updatedAt(file.getUpdatedAt())
                .build();
    }

    public static FileResponseDto.DeleteFileDto toDeleteFileDto(Long fileId) {
        return FileResponseDto.DeleteFileDto.builder()
                .fileId(fileId)
                .deletedAt(LocalDateTime.now())
                .build();
    }
}
