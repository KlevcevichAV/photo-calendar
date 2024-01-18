package com.klevtcevichav.photocalendar.s3;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
@AllArgsConstructor
public class S3Service {

    private final S3Client s3Client;
    @Value("${aws.bucket-name}")
    private final String bucketName;

    public void putObject(String key, byte[] file) {
        PutObjectRequest objectRequest = PutObjectRequest
                .builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.putObject(objectRequest, RequestBody.fromBytes(file));
    }

    public byte[] getObject(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest
                .builder()
                .bucket(bucketName)
                .key(key)
                .build();
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(getObjectRequest);

        try {
            return response.readAllBytes();
        } catch (IOException e) {
            // TODO add exception
            throw new RuntimeException("Can not getting file");
        }
    }
}
