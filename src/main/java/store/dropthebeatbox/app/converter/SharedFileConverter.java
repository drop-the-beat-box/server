package store.dropthebeatbox.app.converter;

import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.SharedFile;
import store.dropthebeatbox.app.web.dto.SharedFileResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SharedFileConverter {

    public static SharedFileResponseDto.SharedMemberDto toSharedMemberDto(Member member) {
        return SharedFileResponseDto.SharedMemberDto.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    public static SharedFileResponseDto.SharedMemberListDto toSharedMemberListDto(List<Member> memberList) {
        List<SharedFileResponseDto.SharedMemberDto> sharedMemberDtoList =
                memberList.stream()
                .map(member -> toSharedMemberDto(member))
                .collect(Collectors.toList());

        return SharedFileResponseDto.SharedMemberListDto.builder()
                .sharedMemberDtoList(sharedMemberDtoList)
                .size(sharedMemberDtoList.size())
                .build();
    }

    public static SharedFileResponseDto.CreateSharedFileDto toCreateSharedFileDto(SharedFile sharedFile) {
        return SharedFileResponseDto.CreateSharedFileDto.builder()
                .sharedFileId(sharedFile.getId())
                .fileId(sharedFile.getFile().getId())
                .memberId(sharedFile.getMember().getId())
                .createdAt(sharedFile.getCreatedAt())
                .build();
    }

    public static SharedFileResponseDto.DeleteSharedMemberDto toDeleteSharedMemberDto() {
        return SharedFileResponseDto.DeleteSharedMemberDto.builder()
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static SharedFile toSharedFile(File file, Member member) {
        return SharedFile.builder()
                .file(file)
                .member(member)
                .build();
    }
}
