package store.dropthebeatbox.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dropthebeatbox.app.converter.FavoriteFileConverter;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.FavoriteFile;
import store.dropthebeatbox.app.repository.FavoriteFileRepository;
import store.dropthebeatbox.app.repository.FileRepository;
import store.dropthebeatbox.app.service.FavoriteFileService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FavoriteFileServiceImpl implements FavoriteFileService {
    private final FavoriteFileRepository favoriteFileRepository;
    private final FileRepository fileRepository;

    @Override
    public List<File> findByMember(Member member) {
        return fileRepository.findFavoriteFilesByMember(member);
    }

    @Transactional
    @Override
    public FavoriteFile create(Long fileId, Member member) {
        File file = fileRepository.findById(fileId).get();
        FavoriteFile favoriteFile = FavoriteFileConverter.toFavoriteFile(file, member);
        return favoriteFileRepository.save(favoriteFile);
    }

    @Transactional
    @Override
    public void delete(Long fileId, Member member) {
        favoriteFileRepository.deleteByFile_IdAndMember(fileId, member);
        return;
    }
}
