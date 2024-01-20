package com.klevtcevichav.photocalendar.s3;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class S3Service {

    private final S3Client s3Client;
    @Value("${aws.bucket-name}")
    private final String bucketName;

    public void putObject(String key, byte[] file) {

        log.info("Start load file to S3 with key: {}", key);
        PutObjectRequest objectRequest = PutObjectRequest
                .builder()
                .bucket(bucketName)
                .key(key)
                .build();
        s3Client.putObject(objectRequest, RequestBody.fromBytes(file));

        log.info("File loaded with key:{}!", key);
    }

    public byte[] getObject(String key) {

        log.info("Start getting file from S3 with key: {}", key);
        GetObjectRequest getObjectRequest = GetObjectRequest
                .builder()
                .bucket(bucketName)
                .key(key)
                .build();
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(getObjectRequest);

        try {
            byte[] responseFile = response.readAllBytes();
            log.info("File with key:{} found", key);
            return responseFile;
        } catch (IOException e) {
            // TODO add exception
            throw new RuntimeException("Can not getting file");
        }
    }
}
