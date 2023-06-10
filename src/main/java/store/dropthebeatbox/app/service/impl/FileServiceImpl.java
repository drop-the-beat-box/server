package store.dropthebeatbox.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import store.dropthebeatbox.app.converter.FileConverter;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.Uuid;
import store.dropthebeatbox.app.repository.FileRepository;
import store.dropthebeatbox.app.service.FileService;
import store.dropthebeatbox.app.util.S3Manager;
import store.dropthebeatbox.app.web.dto.FileRequestDto;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;
    private final S3Manager s3Manager;

    @Override
    public File findByFileId(Long fileId) {
        File file = fileRepository.findById(fileId).get();
        return file;
    }

    @Override
    public List<File> findByMember(Member member) {
        List<File> fileList = fileRepository.findByMember(member);
        return fileList;
    }

    @Transactional
    @Override
    public File create(FileRequestDto.CreateFileDto request, Member member) {
        MultipartFile requestFile = request.getFile();
        Uuid uuid = s3Manager.createUUID();
        String keyName = s3Manager.generateKeyName(uuid, requestFile.getOriginalFilename());
        try {
            String fileUrl = s3Manager.uploadFile(keyName, request.getFile());
            File fileEntity = FileConverter.toFile(request, fileUrl, uuid, member);
            return fileRepository.save(fileEntity);
        } catch (IOException ex) {
            // todo : try-catch 없앨거임.
        }
        return null; // todo : 이 코드도 없어질거임. 예외처리 하면서
    }

    @Transactional
    @Override
    public File update(Long fileId, FileRequestDto.UpdateFileDto request) {
        File file = fileRepository.findById(fileId).get();
        file.setDescription(request.getDescription());
        return file;
    }


    /**
     * 파일을 Hard Delete 하는 메소드<br>
     * 배치 프로그램 개발 이후에는 soft delete로 전환 예정
     * @param fileId
     */
    @Transactional
    @Override
    public void delete(Long fileId) {
        File file = fileRepository.findById(fileId).get();
        String keyName = s3Manager.toKeyName(file);
        s3Manager.deleteFile(keyName);
        fileRepository.delete(file);
    }

    @Override
    @Transactional
    public void throwFile(Long fileId) {
        LocalDateTime deletedAt = LocalDateTime.now();
        File targetFile = fileRepository.findById(fileId).get();
        targetFile.setDeleted(deletedAt);
    }
}
