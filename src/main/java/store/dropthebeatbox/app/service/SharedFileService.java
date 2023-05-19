package store.dropthebeatbox.app.service;

import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.SharedFile;

import java.util.List;

public interface SharedFileService {
    List<Member> findByFileId(Long fileId);

    SharedFile create(Long fileId, Long memberId);

    void delete(Long fileId, Long memberId);
}
