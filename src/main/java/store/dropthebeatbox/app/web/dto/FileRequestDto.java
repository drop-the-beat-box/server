package store.dropthebeatbox.app.web.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

public class FileRequestDto {

    @Getter @Setter
    public static class CreateFileDto{
        private MultipartFile file;
        private String description;
    }

    @Getter
    public static class UpdateFileDto{
        private String description;
    }
}
