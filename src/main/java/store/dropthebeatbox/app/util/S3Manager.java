package store.dropthebeatbox.app.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Uuid;
import store.dropthebeatbox.app.repository.UuidRepository;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Manager {
    private final AmazonS3 amazonS3;
    private final UuidRepository uuidRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.s3.folder}")
    private String folderName;

    /**
     * S3에 파일을 업로드 하는 메소드
     * @param keyName S3 저장될 키
     * @param file MultipartFile
     * @return 저장된 S3 Url
     * @throws IOException
     */
    public String uploadFile(String keyName, MultipartFile file) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        amazonS3.putObject(new PutObjectRequest(bucketName, keyName, file.getInputStream(), metadata));

        // S3 파일 URL 생성
        return amazonS3.getUrl(bucketName, keyName).toString();
    }

    public S3Object downloadFile(String keyName) {
        return amazonS3.getObject(new GetObjectRequest(bucketName, keyName));
    }

    public void deleteFile(String keyName) {
        System.out.println("KEY NAME : " + keyName);
        amazonS3.deleteObject(bucketName, keyName);
    }

    /**
     * 새로운 파일 저장시 keyName을 생성
     * @param originalFilename 원본 파일명
     * @return ex) dtbb/123e4567-e89b-12d3-a456-426614174000_example.jpg
     */
    public String generateKeyName(Uuid uuid, String originalFilename) {
        return folderName + '/' + uuid.getUuid() + '_' + originalFilename;
    }

    /**
     * 이미 저장되어 있는 파일의 정보를 바탕으로 keyName을 생성
     * @param file 파일 엔티티
     * @return ex) dtbb/123e4567-e89b-12d3-a456-426614174000_example.jpg
     */
    public String toKeyName(File file) {
        return folderName + '/' + file.getUuid().getUuid() + '_' + file.getName() + '.' + file.getFileType().getName();
    }

    public Uuid createUUID() {
        Uuid savedUuid = null;
        String candidate = UUID.randomUUID().toString();
        if (uuidRepository.existsByUuid(candidate)) {
            savedUuid = createUUID();
        }
        savedUuid = uuidRepository.save(Uuid.builder().uuid(candidate).build());
        return savedUuid;
    }

}
