package store.dropthebeatbox.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.SharedFile;
import store.dropthebeatbox.app.repository.SharedFileRepository;
import store.dropthebeatbox.app.service.SharedFileService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SharedFileServiceImpl implements SharedFileService {
    private final SharedFileRepository sharedFileRepository;

    @Override
    public List<Member> findByFileId(Long fileId) {
        return null;
    }

    @Override
    public SharedFile create(Long fileId, Long memberId) {
        return null;
    }

    @Override
    public void delete(Long fileId, Long memberId) {

    }
}
