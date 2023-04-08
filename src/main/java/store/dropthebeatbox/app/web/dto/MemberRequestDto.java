package store.dropthebeatbox.app.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class MemberRequestDto {
    @Getter @Setter
    public static class UpdateMemberDto {
        private String name;
        private MultipartFile profileImage;
    }
}
