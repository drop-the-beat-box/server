package store.dropthebeatbox.app.service;

import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.FavoriteFile;

import java.util.List;

public interface FavoriteFileService {
    List<File> findByMember(Member member);

    FavoriteFile create(Long fileId, Member member);

    void delete(Long fileId, Member member);
}
