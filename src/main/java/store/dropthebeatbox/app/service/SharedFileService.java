package store.dropthebeatbox.app.service;

import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.SharedFile;

import java.util.List;

public interface SharedFileService {

    SharedFile create(Long fileId, Long teamId);

    void delete(Long fileId, Long teamId);

    List<File> findByTeam(Long teamId);
}
