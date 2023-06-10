package store.dropthebeatbox.app.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.FileType;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.Uuid;
import store.dropthebeatbox.app.repository.FileRepository;
import store.dropthebeatbox.app.repository.FileTypeRepository;
import store.dropthebeatbox.app.web.dto.FileRequestDto;
import store.dropthebeatbox.app.web.dto.FileResponseDto;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FileConverter {

    private final FileTypeRepository fileTypeRepository;

    private static FileTypeRepository staticFileTypeRepository;

    @PostConstruct
    public void init() {
        staticFileTypeRepository = this.fileTypeRepository;
    }

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

    public static File toFile(FileRequestDto.CreateFileDto request, String fileUrl, Uuid uuid, Member member) {

        MultipartFile requestFile = request.getFile();
        String extension = StringUtils.getFilenameExtension(requestFile.getOriginalFilename());
        String nameWithoutExtension = requestFile.getOriginalFilename().substring(0, requestFile.getOriginalFilename().lastIndexOf('.'));
        FileType fileType;
        if(staticFileTypeRepository.existsByName(extension)) {
            fileType = staticFileTypeRepository.findByName(extension).get();
        } else {
            fileType = staticFileTypeRepository.save(FileType.builder().name(extension).build());
        }

        return File.builder()
                .name(nameWithoutExtension)
                .url(fileUrl)
                .description(request.getDescription())
                .isDeleted(Boolean.FALSE)
                .deletedAt(null)
                .uuid(uuid)
                .fileType(fileType)
                .member(member)
                .build();
    }

    public static FileResponseDto.trashFileDto toTrashFileDto(File file){

        Duration duration = Duration.between(file.getDeletedAt(), LocalDateTime.now());
        return FileResponseDto.trashFileDto.builder()
                .fileId(file.getId())
                .remainDay(duration.toDays())
                .build();
    }

    public static FileResponseDto.trashFileListDto toTrashFileListDto(List<File> fileList){
        List<FileResponseDto.trashFileDto> trashFileDtoList = fileList.stream()
                .map((file -> toTrashFileDto(file)))
                .collect(Collectors.toList());
        return FileResponseDto.trashFileListDto.builder()
                .trashFileDtoList(trashFileDtoList)
                .size(fileList.size())
                .build();
    }
}
