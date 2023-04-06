package store.dropthebeatbox.app.web.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class MemberRequestDto {
    @Getter
    public static class UpdateMemberDto {
        private String name;
        private MultipartFile profileImage;
    }
}
