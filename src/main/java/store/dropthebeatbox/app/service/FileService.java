package store.dropthebeatbox.app.service;

import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.web.dto.FileRequestDto;

import java.io.IOException;
import java.util.List;

public interface FileService {
    File findByFileId(Long fileId);

    List<File> findByMember(Member member);

    File create(FileRequestDto.CreateFileDto request, Member member);

    File update(Long fileId, FileRequestDto.UpdateFileDto request);

    void delete(Long fileId);

    void throwFile(Long fileId);
}
