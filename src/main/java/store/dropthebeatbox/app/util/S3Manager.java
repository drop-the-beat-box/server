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

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3Manager {
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private final String bucketName;

    /**
     * S3에 파일을 업로드 하는 메소드
     * @param keyName : S3 저장될 키
     * @param file : MultipartFile
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
        amazonS3.deleteObject(bucketName, keyName);
    }
}
