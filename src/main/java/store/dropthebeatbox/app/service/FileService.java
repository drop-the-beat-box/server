package store.dropthebeatbox.app.service;

import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.web.dto.FileRequestDto;

import java.util.List;

public interface FileService {
    File findByFileId(Long fileId);

    List<File> findByMember(Member member);

    File create(FileRequestDto.CreateFileDto request);

    File update(FileRequestDto.UpdateFileDto request);

    void delete(Long fileId);
}
