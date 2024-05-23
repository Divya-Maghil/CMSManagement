package org.contentmanagement.digimenucms.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.contentmanagement.digimenucms.error.AwsImageUploadFailedException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class Aws {


    AWSCredentials awsCredentials(String ACCESS_KEY , String SECRET_KEY){
        return new BasicAWSCredentials(
                ACCESS_KEY,
                SECRET_KEY
        );
    }

    AmazonS3 amazonS3ClientBuilder(String ACCESS_KEY , String SECRET_KEY){
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.AP_SOUTHEAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials( ACCESS_KEY , SECRET_KEY)))
                .build();
    }

    public boolean uploadToS3(String fileName , byte[] imageData , String mimetype , String ACCESS_KEY , String SECRET_KEY , String BUCKET_NAME) throws AwsImageUploadFailedException {

        AmazonS3 s3 = amazonS3ClientBuilder(ACCESS_KEY , SECRET_KEY);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageData);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(imageData.length);
        metadata.setContentType(mimetype);
        try {
            PutObjectRequest request = new PutObjectRequest(BUCKET_NAME, fileName, byteArrayInputStream, metadata);
            s3.putObject(request);
            return true;
        } catch (AmazonS3Exception e) {
            throw new AwsImageUploadFailedException("File upload failed to Aws Bucket" , e);
        }
    }


    public String getImageUrl(String ACCESS_KEY ,String SECRET_KEY , String fileName , String bucketName){
        AmazonS3 s3 = amazonS3ClientBuilder(ACCESS_KEY , SECRET_KEY);
        try{
            String url = s3.getUrl(bucketName,fileName).toString();
            return url;
        }catch (Exception e){
            return "Image not present";
        }
    }
}
