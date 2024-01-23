package com.klevtcevichav.photocalendar.s3;

import com.klevtcevichav.photocalendar.core.exception.NotFoundException;
import com.klevtcevichav.photocalendar.exception.CalendarBusinessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class S3Service {

    private final S3Client s3Client;
    private final S3Buckets s3Buckets;

    @Transactional
    public void putObject(String key, byte[] file) {

        log.info("Start load file to S3 with key: {}", key);
        PutObjectRequest objectRequest = PutObjectRequest
                .builder()
                .bucket(s3Buckets.getPhoto())
                .key(key)
                .build();
        try {
            s3Client.putObject(objectRequest, RequestBody.fromBytes(file));
        } catch (AwsServiceException e) {
            throw new CalendarBusinessException("Can not upload file. Try later");
        }

        log.info("File loaded with key:{}!", key);
    }

    public byte[] getObject(String key) {

        log.info("Start getting file from S3 with key: {}", key);
        GetObjectRequest getObjectRequest = GetObjectRequest
                .builder()
                .bucket(s3Buckets.getPhoto())
                .key(key)
                .build();

        try {
            ResponseInputStream<GetObjectResponse> response = s3Client.getObject(getObjectRequest);
            byte[] responseFile = response.readAllBytes();
            log.info("File with key:{} found", key);
            return responseFile;
        } catch (NoSuchKeyException e) {
            throw new NotFoundException("Can not getting file from bucket!");
        } catch (IOException | AwsServiceException e) {
            throw new CalendarBusinessException("Can not getting file");
        }
    }
}
