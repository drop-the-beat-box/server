package store.dropthebeatbox.app.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MemberRequestDto {
    @Getter @Setter
    public static class UpdateMemberDto {
        private String name;
        private MultipartFile profileImage;
    }

    @Getter @Setter
    public static class AddTeamMemberDto{
        private List<Long> memberIdList;
    }
}
