package store.dropthebeatbox.app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.service.FileService;
import store.dropthebeatbox.app.web.dto.FileRequestDto;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FileServiceImpl implements FileService {

    @Override
    public File findByFileId(Long fileId) {
        return null;
    }

    @Override
    public List<File> findByMember(Member member) {
        return null;
    }

    @Override
    public File create(FileRequestDto.CreateFileDto request) {
        return null;
    }

    @Override
    public File update(FileRequestDto.UpdateFileDto request) {
        return null;
    }

    @Override
    public void delete(Long fileId) {

    }
}
