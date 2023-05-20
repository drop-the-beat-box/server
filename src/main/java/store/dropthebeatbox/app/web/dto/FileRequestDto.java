package store.dropthebeatbox.app.web.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

public class FileRequestDto {

    @Getter @Setter
    public static class CreateFileDto{
        private MultipartFile file;

        @Size(max = 300, message = "설명은 최대 300자입니다.")
        private String description;
    }

    @Getter
    public static class UpdateFileDto{
        @Size(max = 300, message = "설명은 최대 300자입니다.")
        private String description;
    }
}
